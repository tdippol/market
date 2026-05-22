package com.axiante.mui.webapp.servlet;

import com.axiante.mui.backing.CellSetCache;
import com.axiante.mui.backing.MuiToken;
import com.axiante.mui.model.TableProducer;
import com.axiante.mui.utils.ApplicationConfiguration;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.enterprise.inject.Instance;
import javax.enterprise.inject.spi.CDI;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

/**
 * Servlet implementation class AgGridDataStream
 */
@Slf4j
@WebServlet(urlPatterns = "/agGridDataStream")
public class AgGridDataStream extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @Inject
    transient private Instance<TableProducer> tableProducerInstances;

    @Inject
    CellSetCache cellSetCache;

    @Inject ApplicationConfiguration configuration;
    //	protected static final Map<WorkerKey, StreamWorker> activeWorkers = new ConcurrentHashMap<WorkerKey, StreamWorker>();

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    @Override
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        log.debug("called get method of the servlet");
        doPost(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */

    @Override
    @SuppressWarnings("unchecked")
    protected void doPost(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        log.debug("called post method of the servlet");
        response.setContentType("application/json;charset=ISO-8859-15");
        response.setCharacterEncoding("ISO-8859-15");
        final String clienttoken = request.getParameter("axremote");
        Map<WorkerKey, StreamWorker> activeWorkers = null;
        if (request.getSession().getAttribute("workMap") != null) {
            activeWorkers = (Map<WorkerKey, StreamWorker>) request.getSession().getAttribute("workMap");
        } else {
            activeWorkers = new ConcurrentHashMap<>();
        }
        if (clienttoken != null) {
            final MuiToken token = cellSetCache.get(clienttoken);
            if (token != null) {
                StreamWorker worker = null;
                TableProducer tableProducer = null;
                Boolean reload = Boolean.FALSE;
                if (request.getParameter("reload") != null) {
                    reload = Boolean.valueOf(request.getParameter("reload"));
                }
                try {
                    // if reloading and an instance already running, ignore
                    final WorkerKey key = new WorkerKey(request.getRemoteUser(), request.getParameter("gridId"));
                    if (activeWorkers.get(key) != null) {
                        // we already have a job for the same user and grid
                        if (reload) {
                            log.debug("user " + request.getRemoteUser() + " is asking to reload "
                                    + request.getParameter("gridId")
                                    + " but a job for that same pair is already running");
                            return;
                        } else {
                            // if not reloading and an instance already running, stop previous and run this
                            worker = activeWorkers.get(key);
                            log.debug("user " + request.getRemoteUser() + " is reloading page with grid "
                                    + request.getParameter("gridId")
                                    + " so I am trying to stop the previous operation");
                            worker.stopProcessing();
                            log.debug("removing job from active jobs");
                            activeWorkers.remove(key);
                        }
                    }

                    if (tableProducerInstances != null) {
                        tableProducer = tableProducerInstances.get();
                    } else {
                        log.warn(
                                "something happened with table producer instances, trying to manually instantiate one");
                        tableProducer = CDI.current().select(TableProducer.class).get();
                    }

                    final Integer overallTmeout = configuration.getOverallOperationTimeout();
                    worker = new StreamWorker(response, token, tableProducer,configuration.getConnectionTimeout(),
                            request.getRemoteUser(), request.getParameter("gridId"),
                            overallTmeout);
                } catch (final Exception e) {
                    log.error("error creating worker", e);
                } finally {
                    if (worker != null) {
                        activeWorkers.put(worker.getKey(), worker);
                        try{
                            log.debug("Thread start: " + worker.getKey() + " - Time: " + System.nanoTime());
                            new Thread(worker).run();
                        } finally {
                            log.debug("Thread close: " + worker.getKey() + " - Time: " + System.nanoTime());
                            activeWorkers.remove(worker.getKey());
                        }
                    } else {
                        response.sendError(500, "Error communicating with TM1 server! Please contact support");
                    }
                }
            }
        } else {
            log.error("missing client token");
            response.getOutputStream().print("{\"error\":\"Connection lost ! Please reload page\"}");
        }
        request.getSession().setAttribute("workMap", activeWorkers);
    }
}
