package com.axiante.mui.webapp.utils.exception;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerFactory;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class AxExceptionHandlerFactoryTest {

    @Rule
    public ExpectedException ex = ExpectedException.none();

    @Test
    public void getExceptionHandler_shouldReturnWrappedHandlerWhenFactoryIsUsed() {
        ExceptionHandlerFactory parent = mock(ExceptionHandlerFactory.class);
        ExceptionHandler handler = mock(ExceptionHandler.class);
        AxExceptionHandlerFactory underTest = new AxExceptionHandlerFactory(parent) {
            @Override
            public ExceptionHandler getExceptionHandler() {
                return handler;
            }
        };
        assertThat(underTest.getExceptionHandler(), equalTo(handler));
    }

    @Test
    public void constructor_shouldCreateFactoryWithoutThrowing() {
        ExceptionHandlerFactory parent = mock(ExceptionHandlerFactory.class);
        AxExceptionHandlerFactory underTest = new AxExceptionHandlerFactory(parent);
        assertThat(underTest != null, equalTo(true));
    }
}