package com.axiante.mui.business;

import com.axiante.mui.dbpromo.business.enumeration.ModalitaMarchioPrivato;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class ModalitaMarchioPrivatoConverterTest {
    @InjectMocks
    private ModalitaMarchioPrivatoConverter converter;

    @Test
    public void convertToDatabaseColumn_whenAttibuteIsNull_shouldReturnMinusOne() {
        assertEquals(-1, (long) converter.convertToDatabaseColumn(null));
    }

    @Test
    public void convertToDatabaseColumn_whenAttibuteIsNO_shouldReturnMinusOne() {
        assertEquals(-1, (long) converter.convertToDatabaseColumn(ModalitaMarchioPrivato.NO));
    }

    @Test
    public void convertToDatabaseColumn_whenAttibuteIsSI_shouldReturnZero() {
        assertEquals(0, (long) converter.convertToDatabaseColumn(ModalitaMarchioPrivato.SI));
    }

    @Test
    public void convertToDatabaseColumn_whenAttibuteIsAUTOMATICO_shouldReturnOne() {
        assertEquals(1, (long) converter.convertToDatabaseColumn(ModalitaMarchioPrivato.AUTOMATICO));
    }

    @Test
    public void convertToDatabaseColumn_whenAttibuteIsESTESO_shouldReturnTwo() {
        assertEquals(2, (long) converter.convertToDatabaseColumn(ModalitaMarchioPrivato.ESTESO));
    }

    @Test
    public void convertToEntityAttribute_whenDbDataIsNull_shouldReturnNO() {
        assertEquals(ModalitaMarchioPrivato.NO, converter.convertToEntityAttribute(null));
    }

    @Test
    public void convertToEntityAttribute_whenDbDataIsNotNull_shouldReturnMappedValue() {
        assertEquals(ModalitaMarchioPrivato.ESTESO, converter.convertToEntityAttribute(2));
    }
}