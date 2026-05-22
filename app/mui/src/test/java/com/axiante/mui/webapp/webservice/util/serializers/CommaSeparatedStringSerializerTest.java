package com.axiante.mui.webapp.webservice.util.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collections;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

public class CommaSeparatedStringSerializerTest {
    private CommaSeparatedStringSerializer serializer;

    @Mock
    private JsonGenerator gen;

    @Mock
    private SerializerProvider provider;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        serializer = new CommaSeparatedStringSerializer();
    }

    @Test
    public void serialize_whenValueIsNull_shouldWriteNull() throws Exception {
        serializer.serialize(null, gen, provider);
        verify(gen).writeNull();
        verify(gen, never()).writeString(org.mockito.ArgumentMatchers.anyString());
    }

    @Test
    public void serialize_whenValueIsEmpty_shouldWriteNull() throws Exception {
        serializer.serialize(Collections.emptyList(), gen, provider);
        verify(gen).writeNull();
        verify(gen, never()).writeString(org.mockito.ArgumentMatchers.anyString());
    }

    @Test
    public void serialize_whenValueContainsOneElement_shouldWriteSingleValue() throws Exception {
        serializer.serialize(Collections.singletonList("test"), gen, provider);
        verify(gen).writeString("test");
        verify(gen, never()).writeNull();
    }

    @Test
    public void serialize_whenValueContainsMultipleElements_shouldWriteCommaSeparatedString() throws Exception {
        serializer.serialize(Arrays.asList("one", "two", "three"), gen, provider);
        verify(gen).writeString("one,two,three");
        verify(gen, never()).writeNull();
    }
}