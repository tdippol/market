package com.axiante.mui.webapp.views.content;

import com.axiante.tm1.mdx.objects.Query;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertNull;

@RunWith(MockitoJUnitRunner.class)
public class ErrorViewTest {

    private final ErrorView underTest = new ErrorView();

    @Test
    public void updateView_shouldNotThrow() {
        underTest.updateView();
    }

    @Test
    public void updateViewWithGrid_shouldNotThrow() {
        underTest.updateView("grid");
    }

    @Test
    public void getCatalogProducer_shouldReturnNull() {
        assertNull(underTest.getCatalogProducer());
    }

    @Test
    public void getCatalogReducer_shouldReturnNull() {
        assertNull(underTest.getCatalogReducer());
    }

    @Test
    public void prepareFilteredQuery_shouldReturnNull() {
        Query result = underTest.prepareFilteredQuery("grid");
        assertNull(result);
    }
}