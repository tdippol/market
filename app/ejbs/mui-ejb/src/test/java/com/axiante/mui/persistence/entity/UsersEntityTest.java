package com.axiante.mui.persistence.entity;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

import com.axiante.mui.filter.IngridFilter;

public class UsersEntityTest {

    @Test
    public void testConstructor() {
        assertNotNull(new UsersEntity());
        RolesEntity role = mock(RolesEntity.class);
        assertNotNull(new UsersEntity(1,"test",role));
    }

    @Test
    public void testEquals() {
        UsersEntity e = new UsersEntity();
        assertThat(e, equalTo(e));
        assertThat(e, not(equalTo(null)));

        RolesEntity role = mock(RolesEntity.class);
        e = new UsersEntity(1,"test",role);
        assertThat(e, equalTo(e));
        assertThat(e, not(equalTo(null)));
        assertThat(e, not(new UsersEntity()));
    }

    @Test
    public void testHashCode() {
        UsersEntity e = new UsersEntity();
        assertThat(e.hashCode(), equalTo(e.hashCode()));

        RolesEntity role = mock(RolesEntity.class);
        e = new UsersEntity(1,"test",role);
        assertThat(e.hashCode(), equalTo(e.hashCode()));
        assertThat(e, not(new UsersEntity().hashCode()));
    }

    @Test
    public void testCamLogin() {
        UsersEntity e = new UsersEntity();
        assertFalse(e.getCamLogin());
        e.setCamLogin(true);
        assertTrue(e.getCamLogin());
        e.setCamLogin(false);
        assertFalse(e.getCamLogin());
    }
    @Test
    public void testNamespaceLogin() {
        UsersEntity e = new UsersEntity();
        assertFalse(e.getNamespaceLogin());
        e.setNamespaceLogin(true);
        assertTrue(e.getNamespaceLogin());
        e.setNamespaceLogin(false);
        assertFalse(e.getNamespaceLogin());
    }

    @Test public void testUpdateIngridFilterWithAMapReturnsCorrectJson() {
        List<IngridFilter> filters = new ArrayList<>();
        for (int i = 0; i < 3 ; ++ i) {
            IngridFilter f = new IngridFilter();
            f.setDimension("dimension_"+ i);
            f.setAttribute("attribute_"+i);
            List<String> selectedValues = new ArrayList<>();
            for ( int j =0; j < 3; ++ j) {
                selectedValues.add("selected_value_"+i+"_"+j);
            }
            f.setSelectedValues(selectedValues);
            filters.add(f);
        }
        Map<String, List<IngridFilter>> map = new HashMap<>();
        map.put("grid_1", filters);
        map.put("grid_2", filters);

        UsersEntity e = new  UsersEntity();
        e.updateIngridFilters(map);

        assertNotNull(e.getIngridFilters());
        assertThat(e.getIngridFilters(), CoreMatchers.not(CoreMatchers.equalTo("{}")));
        assertThat(e.getIngridFilters(), CoreMatchers.containsString("grid_1"));
        assertThat(e.getIngridFilters(), CoreMatchers.containsString("grid_2"));
        assertThat(e.getIngridFilters(), CoreMatchers.containsString("attribute_1"));
        assertThat(e.getIngridFilters(), CoreMatchers.containsString("dimension_2"));
        assertThat(e.getIngridFilters(), CoreMatchers.containsString("selected_value_2_0"));
    }

    @Test public void testUpdateIngridFilterWithAnEmptyMapReturnsCorrectJson() {
        Map<String, List<IngridFilter>> map = new HashMap<>();
        UsersEntity e = new  UsersEntity();
        e.updateIngridFilters(map);

        assertNotNull(e.getIngridFilters());
        assertThat(e.getIngridFilters(), CoreMatchers.equalTo("{}"));
    }

    @Test public void testUpdateIngridFilterWithANullMapReturnsCorrectJson() {
        UsersEntity e = new  UsersEntity();
        e.updateIngridFilters(null);

        assertNotNull(e.getIngridFilters());
        assertThat(e.getIngridFilters(), CoreMatchers.equalTo("{}"));
    }

    @Test public void testWhenFilterHasDataGetIngridFilterAsMapReturnsAMap() {
        String json = "{'grid_1':[{'dimension':'dimension_0','attribute':'attribute_0','selected_values':['selected_value_0_0','selected_value_0_1','selected_value_0_2']},{'dimension':'dimension_1','attribute':'attribute_1','selected_values':['selected_value_1_0','selected_value_1_1','selected_value_1_2']},{'dimension':'dimension_2','attribute':'attribute_2','selected_values':['selected_value_2_0','selected_value_2_1','selected_value_2_2']}],'grid_2':[{'dimension':'dimension_0','attribute':'attribute_0','selected_values':['selected_value_0_0','selected_value_0_1','selected_value_0_2']},{'dimension':'dimension_1','attribute':'attribute_1','selected_values':['selected_value_1_0','selected_value_1_1','selected_value_1_2']},{'dimension':'dimension_2','attribute':'attribute_2','selected_values':['selected_value_2_0','selected_value_2_1','selected_value_2_2']}]}";
        json = json.replace("\'", "\"");
        UsersEntity e = new  UsersEntity();
        e.setIngridFilters(json);
        Map<String, List<IngridFilter>> map = e.getIngridFilterAsMap();
        assertNotNull(map);
        assertThat(map.size(),CoreMatchers.equalTo(2));
        assertNotNull(map.get("grid_1"));
        assertThat(map.get("grid_2").size(), CoreMatchers.equalTo(3));
        List<IngridFilter> list = map.get("grid_2");
        assertNotNull(list.get(1));
        IngridFilter f = list.get(1);
        assertThat(f.getDimension(), CoreMatchers.equalTo("dimension_1"));
        assertThat(f.getAttribute(), CoreMatchers.equalTo("attribute_1"));
        assertEquals(f.getSelectedValues(), Arrays.asList("selected_value_1_0","selected_value_1_1","selected_value_1_2"));
    }

    @Test public void testWhenFilterHasNoDataGetIngridFilterAsMapReturnsAnEmptyMap() {
        String json = "{}";
        UsersEntity e = new  UsersEntity();
        e.setIngridFilters(json);
        Map<String, List<IngridFilter>> map = e.getIngridFilterAsMap();
        assertNotNull(map);
        assertThat(map.size(),CoreMatchers.equalTo(0));
        e.setIngridFilters(null);
        map = e.getIngridFilterAsMap();
        assertNotNull(map);
        assertThat(map.size(),CoreMatchers.equalTo(0));
    }

}
