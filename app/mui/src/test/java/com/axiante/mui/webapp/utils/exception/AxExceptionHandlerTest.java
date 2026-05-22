package com.axiante.mui.webapp.utils.exception;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import javax.faces.context.ExceptionHandler;
import javax.faces.event.ExceptionQueuedEvent;
import java.util.Collections;
import java.util.Iterator;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class AxExceptionHandlerTest {
    @Rule
    public ExpectedException ex = ExpectedException.none();

    @Test
    public void getWrapped_shouldReturnWrappedHandler() {
        ExceptionHandler wrapped = mock(ExceptionHandler.class);
        AxExceptionHandler underTest = new AxExceptionHandler(wrapped);
        assertThat(underTest.getWrapped(), equalTo(wrapped));
    }

    @Test
    public void handle_shouldDelegateToWrappedHandlerWhenNoUnhandledEventsExist() {
        ExceptionHandler wrapped = mock(ExceptionHandler.class);
        AxExceptionHandler underTest = new AxExceptionHandler(wrapped) {
            @Override
            public Iterable<ExceptionQueuedEvent> getUnhandledExceptionQueuedEvents() {
                return Collections.emptyList();
            }
        };
        underTest.handle();
        verify(wrapped).handle();
    }

    @Test
    public void handle_shouldIterateOverUnhandledEventsAndStillDelegateToWrappedHandler() {
        ExceptionHandler wrapped = mock(ExceptionHandler.class);
        AxExceptionHandler underTest = new AxExceptionHandler(wrapped) {
            @Override
            public Iterable<ExceptionQueuedEvent> getUnhandledExceptionQueuedEvents() {
                return new Iterable<ExceptionQueuedEvent>() {
                    @Override
                    public Iterator<ExceptionQueuedEvent> iterator() {
                        return Collections.<ExceptionQueuedEvent>emptyList().iterator();
                    }
                };
            }
        };
        underTest.handle();
        verify(wrapped).handle();
    }
}