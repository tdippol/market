package com.axiante.tm1.json;

import com.axiante.tm1.json.objects.ForceSuppressColumnsHelper;
import com.axiante.tm1.json.objects.Table;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.atomic.AtomicInteger;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AgGridInMemoryInputStream extends InputStream{
	private AgGridDataProducer producer;
	private Table table;
	private char[] buffer;
	private AtomicInteger bufferIndex;
	private AtomicInteger tableIndex;
	private int tableSize;
	private boolean streamConsumed = false;
	private final ForceSuppressColumnsHelper suppressColumnsHelper = new ForceSuppressColumnsHelper();
	public AgGridInMemoryInputStream(final Table table, boolean forceSuppressCols){
		this.table = table;
		// the table headers start at 0, data starts at 1
		tableIndex = new AtomicInteger(0);
		bufferIndex = new AtomicInteger(-1);
		producer = new AgGridDataProducer(null);

		String s = "{\"maxrows\":false,\"data\": [";
		buffer = s.toCharArray();
		this.tableSize = table.getRowSize();
		this.suppressColumnsHelper.setApplyForceSuppression(forceSuppressCols);
		if ( forceSuppressCols)
			table.getHeaders().stream().forEach(h->{						
				suppressColumnsHelper.put(h.getName(), Boolean.FALSE);
			});
	}
	@Deprecated
	public AgGridInMemoryInputStream(final Table table){
		this(table, false);
	}
	
	@Override
	public int read() throws IOException {
		if (buffer != null &&  bufferIndex.get() < buffer.length-1) {
			return this.buffer[bufferIndex.incrementAndGet()];
		} else { 
			// fille the buffer with a new chunk
			bufferIndex.set(-1);
			nextBuffer();
			if ( this.buffer != null ) {
				return this.buffer[bufferIndex.incrementAndGet()];
			} else {
				return -1;
			}
		}
	}
	@Override
    public int read(byte b[], int off, int len) throws IOException {
        if (b == null) {
            throw new NullPointerException();
        } else if (off < 0 || len < 0 || len > b.length - off) {
            throw new IndexOutOfBoundsException();
        } else if (len == 0) {
            return 0;
        }

        int c = read();
        if (c == -1) {
            return -1;
        }
        b[off] = (byte)c;

        int i = 1;
        try {
            for (; i < len ; i++) {
                c = read();
                if (c == -1) {
                    break;
                } else if (c < 32 || c > 255) {
                	log.debug("skipping control char " + c);
                	continue;
                } else { 
                	b[off + i] = (byte)c;
                }
            }
        } catch (IOException ee) {
        }
        return i;
    }

	
	private void nextBuffer() {
		if ( table.isError()) {
			this.buffer = "]}".toCharArray();
			log.debug("empty table");
			streamConsumed = true;
		} else { 
			if ( tableIndex.get() < tableSize ) {
				// there's data that needs to be read
				StringBuilder builder;
				try {
					builder = producer.transform(table.getRow(tableIndex.incrementAndGet()), table.getName(), table.getActualDataIndex(), suppressColumnsHelper);
					if ( tableIndex.get() != 1) {
						builder.insert(0,","); // comma at the beginning
					} else {
						// it's the first row, nothing to do
					}
					// now check if this was the last row
					if (tableIndex.get() == tableSize ) {
						builder.append("]}");
						log.debug("last row read");
						streamConsumed = true;
					}
					this.buffer = new char[builder.length()];
					builder.getChars(0, builder.length(), this.buffer, 0);
					bufferIndex.set(-1); // reads are made by increment and get
				} catch (IllegalAccessException|JsonProcessingException e) {
					this.buffer = null;
					log.error("error reading next data", e);
				}
			} else if ( tableSize == 0 && !streamConsumed) {
					if ( suppressColumnsHelper.isApplyForceSuppression()) {
						this.buffer = ( 
								("]" + suppressColumnsHelper.produceSuppressJsonArray() + "}")
						).toCharArray();
					} else { 
						this.buffer = "]}".toCharArray();
					}
					log.debug("empty table");
					streamConsumed = true;
			} else { 
				if ( streamConsumed ) {
					this.buffer = null;
				} else {
					log.error("stream should be consumed as the table has been read !");
				}
			}
		}
	}

}
