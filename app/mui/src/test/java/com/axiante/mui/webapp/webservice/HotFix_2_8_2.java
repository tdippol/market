package com.axiante.mui.webapp.webservice;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.Assert;
import org.junit.Test;

public class HotFix_2_8_2 {


    @Test
    public void testTroppiArticoli(){
        int limit = 5001;
        int maxSize = 1000;
        test(limit, maxSize, 5);
    }
    @Test
    public void testPochiArticoli(){
        int limit = 900;
        int maxSize = 1000;
        test(limit, maxSize, 0);
    }
    @Test
    public void testZeroArticoli(){
        int limit = 0;
        int maxSize = 1000;
        test(limit, maxSize, 0);
    }

    public void test(int limit, int maxSize, int expectedPass) {
        List<Dummy> codiciItems = Stream.iterate(1, n -> n + 1).limit(limit).map(n -> new Dummy(n.toString(), "descrizione_" + n)).collect(Collectors.toList());
        Assert.assertEquals(limit, codiciItems.size());
        int start = 0;
        int size = maxSize;
        int rem = codiciItems.size() % size;
        int pass = (codiciItems.size()-rem)/size;
        Assert.assertEquals(expectedPass, pass);
        final List<String> compratoriPresenti = new ArrayList<>();
        for ( int i = 0; i < pass ; ++ i){
            start = i * size;
            List<Dummy> subList = codiciItems.subList(start, start + size);
            Assert.assertEquals(maxSize, subList.size());
            compratoriPresenti.addAll(subList.stream().map(Dummy::getCodice).collect(Collectors.toList()));
        }
        if (rem > 0) {
            start = pass * size;
            List<Dummy> subList = codiciItems.subList(start, start + rem);
            Assert.assertEquals(rem, subList.size());
            compratoriPresenti.addAll(subList.stream().map(Dummy::getCodice).collect(Collectors.toList()));
        }
        Assert.assertEquals(limit, compratoriPresenti.size());
    }


    @Data
    @AllArgsConstructor
    class Dummy {
        String codice;
        String descrizione;
    }

}
