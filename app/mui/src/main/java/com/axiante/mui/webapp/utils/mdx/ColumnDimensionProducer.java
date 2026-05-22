package com.axiante.mui.webapp.utils.mdx;

import com.axiante.tm1.mdx.objects.Dimension;
import com.axiante.tm1.mdx.objects.Dimension.Type;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import javax.enterprise.context.Dependent;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Dependent
public class ColumnDimensionProducer implements Serializable{


    private static final long serialVersionUID = 1926616421330988501L;

    public List<Dimension> getFromStringConfiguration(final String string) {
        if ( string == null ) {
            return null;
        }
        StringBuffer partBuffer = new StringBuffer();
        final StringBuffer buffer = new StringBuffer("(");
        final String[] split1 = string.split(Pattern.quote("$$"));
        boolean invalidFormat = false;

        for ( final String element:split1) {
            final String[] split2 = element.split(Pattern.quote("$"));
            if ( (split2.length % 2) != 0) {
                invalidFormat = true;
            } else { 
                for ( int i = 0 ; i < split2.length ; ++i ) {
                    partBuffer.append("[").append(split2[i]).append("]");
                    if ( (i % 2) == 0) {
                        partBuffer.append(".");
                    } 
                }
                partBuffer.append(",");
            }
        }
        if ( invalidFormat ) {
            log.error("invalid column format string " + string);
            return null;
        } else {
            partBuffer.deleteCharAt(partBuffer.length()-1);
            buffer.append(partBuffer);
            partBuffer.delete(0, partBuffer.length());
            buffer.append(")");

            final List<Dimension> list = new ArrayList<>();
            list.add(new Dimension(buffer.toString(), null, Type.COLUMNS));
            partBuffer=null;
            return list;
        }
    }
}
