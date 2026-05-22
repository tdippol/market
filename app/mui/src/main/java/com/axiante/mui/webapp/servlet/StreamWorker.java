package com.axiante.mui.webapp.servlet;

import com.axiante.mui.backing.MuiToken;
import com.axiante.mui.model.TableProducer;
import com.axiante.tm1.json.AgGridOnTheFlyInputStream;
import com.axiante.tm1.json.objects.Table;
import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.Timer;
import java.util.TimerTask;
import javax.servlet.http.HttpServletResponse;
import lombok.Getter;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StreamWorker implements Runnable {
	private final MuiToken token;
	private final TableProducer tableProducer;
	final Double DEFAULT_CONNECTION_TIMEOUT = 5d;
	private Double connectionTimeout;
	@Getter
	private WorkerKey key = null;
	@Getter
	private String errorMessage = null;
	@Getter
	private Boolean copySuccess = true;
	private boolean aborted = false;
	private boolean timedout = false;
	private boolean operationCompleted = false;
	HttpServletResponse response;
	private Integer streamTimeout = null;

	public StreamWorker(final HttpServletResponse response, @NonNull final MuiToken token,
			@NonNull final TableProducer tableProducer, final Double connectionTimeout, @NonNull final String username,
			@NonNull final String gridId, final Integer streamTimeout) throws IOException {
		log.debug("creating new stream worker instance");
		this.response = response;
		this.token = token;
		this.tableProducer = tableProducer;
		this.connectionTimeout = connectionTimeout;
		if (this.connectionTimeout == null) {
			this.connectionTimeout = DEFAULT_CONNECTION_TIMEOUT;
		}

		key = new WorkerKey(username, gridId);
		this.streamTimeout = streamTimeout;
	}

	public static synchronized WorkerKey create(final String username, final String gridId) {
		return new WorkerKey(username, gridId);
	}

	@Override
	public void run() {
		try {
			log.debug("starting stream worker");
			startTimeout();
			serveStream();
		} catch (final Exception e) {
			log.error("error running asynch worker", e);
			try {
				if ((response != null) && (response.getOutputStream() != null)) {
					if (!response.isCommitted()) {
						try {
							response.reset();
						} catch (final Exception ex) {
							log.error("cannot reset response", ex);
						} finally {
							response.getOutputStream().print("{\"error\":\"Connection lost ! Please reload page\"}");
						}
					} else {
						log.warn("trapped exception but response already committed: client could be mishbehaving");
					}
				}
			} catch (final IOException e1) {
				log.error("error getting response from asynch context", e1);

			}
		}
	}

	public void stopProcessing() {
		aborted = true;
	}

	void serveStream() {
		Table skelton = null;
		try {
			log.debug("serving data on thread " + this + " and flushing on response " + response.getOutputStream());
			if (!aborted) {
				skelton = tableProducer.produceTableHeaders(token, null, token.getCellsetId());
			}
		} catch (final Exception debugged) {
			log.debug("something happened setting up the skelton:", debugged);
		}
		if (skelton == null) {
			log.error("mui token expired while creating skelton");
			errorMessage = "{\"error\":\"Connection timeout, please refresh\"}";
			copySuccess = false;
		} else {
			copySuccess = true;
			if (aborted) {
				return;
			}
			try (AgGridOnTheFlyInputStream agGridStream = new AgGridOnTheFlyInputStream(token.getConfiguration(),
					token.getCellsetId(), token.getPassport(), skelton, token.getCookie(), connectionTimeout)) {
				token.addConnectionClosingListener(agGridStream);
				if (!aborted) {
					// scrivi
					final byte[] buffer = new byte[8192];
					log.debug("starting to read the input stream and serving the data");
					int bytesRead = agGridStream.read(buffer);
					while (bytesRead != -1) {
						response.getOutputStream().write(buffer, 0, bytesRead);
						if (aborted && !agGridStream.isStreamClosing() && !agGridStream.isReadAbort()) {
							log.debug("abort called while serving data");
							agGridStream.abort();
							// finish the rest of the stream and exit, stream will close itself
						}
						bytesRead = agGridStream.read(buffer);
					}
					log.debug("done serving data");
				} else {
					// il timeout e' arrivato prima della lettura quindi TM1 sta ancora facendo
					// l'MDX
					agGridStream.abort();
				}
				token.removeConnectionClosingListener(agGridStream);
			} catch (final Exception e) {
				log.error("Error serving table stream ", e);
				copySuccess = false;
				if (e.getCause() instanceof SocketTimeoutException) {
					errorMessage = "{\"error\":\"Timeout error waiting for TM1 response\"}";
				} else if (e.getCause() instanceof IOException) {
					errorMessage = "{\"error\":\"" + e.getMessage() + "\"}";
				} else {
					// add your handler here
				}
			} finally {
				try {
					complete();
				} catch (final IOException e) {
					log.error("error committing work", e);
				}
			}
		}
	}

	private void complete() throws IOException {
		log.debug("house cleaning after completing worker job");
		if (!aborted) {
			if (!copySuccess && (errorMessage != null)) {
				// there was an issue and I need to notify the other end of the stream
				try {
					response.getOutputStream().write(errorMessage.getBytes());
				} catch (final Exception e) {
					log.error("error writing error message to client : ", e);
				} finally {
					try {
						if (response.isCommitted()) {
							log.error("response already committed: client may be misbehaving");
						} else {
							response.getOutputStream().flush();
						}
					} catch (final Exception e) {
						log.error("error flushing to client", e);
					}
				}
			}
		} else {
			if (timedout) {
				if (response.isCommitted()) {
					log.debug("stream alredy written, cannot send error the usual way");
				} else {
					response.sendError(503, "Il server TM1 ha impiegato un tempo maggiore di " + streamTimeout
							+ " secondi a resituire una risposta. Si prega di aggiungere filtri");
				}
			}
		}
		operationCompleted = true;
	}

	private void startTimeout() {
		if (streamTimeout != null) {
			final TimerTask timer = new TimerTask() {
				@Override
				public void run() {
					if (!operationCompleted) {
						timedout = true;
						log.debug("operation timed out after " + streamTimeout + " seconds");
						StreamWorker.this.stopProcessing();
					}
				}
			};

			new Timer(true).schedule(timer, 1000 * streamTimeout);
		}
	}
}
