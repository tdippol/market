package com.axiante.mui.webapp.views.content;

import com.axiante.tm1.mdx.objects.Query;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class SimpleViewTest {

    private final SimpleView underTest = new SimpleView();

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

    @Test
    public void classShouldExtendAbstractMuiView() {
        assertThat(underTest instanceof com.axiante.mui.webapp.views.AbstractMuiView, equalTo(true));
    }
}