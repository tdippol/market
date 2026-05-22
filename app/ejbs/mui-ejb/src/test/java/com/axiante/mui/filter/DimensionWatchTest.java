package com.axiante.mui.filter;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import java.util.Objects;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import com.axiante.mui.filter.DimensionWatch.Watch;

@RunWith(MockitoJUnitRunner.class)
public class DimensionWatchTest {
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test 
    public void testConstructorSetsCorrectValues() {
        final String dimension = "dimension";
        final String lastSchemaUpdate = DimensionWatch.LastSchemaUpdate;
        final String lastDataUpdate = DimensionWatch.LastDataUpdate;

        final DimensionWatch watch = spy(new DimensionWatch(dimension, lastSchemaUpdate, lastDataUpdate));
        assertNotNull(watch);
        final Watch test = watch.getWatch();
        assertNotNull(test);
        assertThat(lastSchemaUpdate, equalTo(test.getLastSchemaUpdate()));
        assertThat(lastDataUpdate, equalTo(test.getLastDataUpdate()));
    }
    @Test
    public void testCompareToReturnsTrueWhenDimensionAndDatesMatch() {
        final String dimension = "dimension";
        final String lastSchemaUpdate = DimensionWatch.LastSchemaUpdate;
        final String lastDataUpdate = DimensionWatch.LastDataUpdate;
        final DimensionWatch test = spy(new DimensionWatch(dimension, lastSchemaUpdate, lastDataUpdate));
        final DimensionWatch expected = mock(DimensionWatch.class);
        when(expected.getDimension()).thenReturn(dimension);
        final Watch mockedWatch = mock(Watch.class);
        when(mockedWatch.getLastDataUpdate()).thenReturn(lastDataUpdate);
        when(mockedWatch.getLastSchemaUpdate()).thenReturn(lastSchemaUpdate);
        when(expected.getWatch()).thenReturn(mockedWatch);
        assertNotNull(test);
        assertThat(test.compareTo(expected), equalTo(0));
    }
    @Test public void testCompareToShortCircuitsWhenDimensionDiffers() {
        final String dimension = "dimension";
        final String lastSchemaUpdate = DimensionWatch.LastSchemaUpdate;
        final String lastDataUpdate = DimensionWatch.LastDataUpdate;
        final DimensionWatch test = spy(new DimensionWatch(dimension, lastSchemaUpdate, lastDataUpdate));
        final DimensionWatch expected = mock(DimensionWatch.class);
        when(expected.getDimension()).thenReturn("somethingelse");
        assertThat(test.compareTo(expected), not(equalTo(0)));
    }
    @Test public void testCompareToThrowsExceptionWithNullParameter() {
        expectedException.expect(NullPointerException.class);
        final String dimension = "dimension";
        final String lastSchemaUpdate = DimensionWatch.LastSchemaUpdate;
        final String lastDataUpdate = DimensionWatch.LastDataUpdate;
        final DimensionWatch test = spy(new DimensionWatch(dimension, lastSchemaUpdate, lastDataUpdate));
        test.compareTo(null);
    }

    @Test public void testEqualsWithItselfReturnsTrue() {
        final String dimension = "dimension";
        final String lastSchemaUpdate = DimensionWatch.LastSchemaUpdate;
        final String lastDataUpdate = DimensionWatch.LastDataUpdate;
        final DimensionWatch test = new DimensionWatch(dimension, lastSchemaUpdate, lastDataUpdate);
        assertThat(test, equalTo(test));
    }

    @Test public void testEqualToWithSameDataReturnsTrue() {
        final String dimension = "dimension";
        final String lastSchemaUpdate = DimensionWatch.LastSchemaUpdate;
        final String lastDataUpdate = DimensionWatch.LastDataUpdate;
        final DimensionWatch test = new DimensionWatch(dimension, lastSchemaUpdate, lastDataUpdate);
        final DimensionWatch expect = new DimensionWatch(dimension, lastSchemaUpdate, lastDataUpdate);
        assertThat(test, equalTo(expect));
    }
    @Test public void testEqualToWithDifferentDataReturnsFalse() {
        final String dimension = "dimension";
        final String lastSchemaUpdate = DimensionWatch.LastSchemaUpdate;
        final String lastDataUpdate = DimensionWatch.LastDataUpdate;
        final DimensionWatch test = new DimensionWatch(dimension, lastSchemaUpdate, lastDataUpdate);
        DimensionWatch expect = new DimensionWatch(dimension, lastSchemaUpdate+test, lastDataUpdate);
        assertThat(test, not(equalTo(expect)));
        expect = new DimensionWatch(dimension+test, lastSchemaUpdate, lastDataUpdate);
        assertThat(test, not(equalTo(expect)));
    }
    @Test public void testEqualsWithDifferentObjectReturnsFalse() {
        final String dimension = "dimension";
        final String lastSchemaUpdate = DimensionWatch.LastSchemaUpdate;
        final String lastDataUpdate = DimensionWatch.LastDataUpdate;
        final DimensionWatch test = new DimensionWatch(dimension, lastSchemaUpdate, lastDataUpdate);
        assertThat(test.equals(dimension), is(false)); 
    }
    @Test public void testWatchHashCode() {
        final String dimension = "dimension";
        final String lastSchemaUpdate = DimensionWatch.LastSchemaUpdate;
        final String lastDataUpdate = DimensionWatch.LastDataUpdate;
        final DimensionWatch test = new DimensionWatch(dimension, lastSchemaUpdate, lastDataUpdate);
        final Watch watch = test.getWatch();
        assertThat(watch.hashCode(), is ( Objects.hash(lastSchemaUpdate, lastDataUpdate)));
    }

    @Test public void testWatchEqualsItself() {
        final String dimension = "dimension";
        final String lastSchemaUpdate = DimensionWatch.LastSchemaUpdate;
        final String lastDataUpdate = DimensionWatch.LastDataUpdate;
        final DimensionWatch test = new DimensionWatch(dimension, lastSchemaUpdate, lastDataUpdate);
        final Watch watch = test.getWatch();
        assertThat(watch, equalTo(watch));
    }

    @Test public void testWatchNotEqualsSomethingElse() {
        final String dimension = "dimension";
        final String lastSchemaUpdate = DimensionWatch.LastSchemaUpdate;
        final String lastDataUpdate = DimensionWatch.LastDataUpdate;
        final DimensionWatch test = new DimensionWatch(dimension, lastSchemaUpdate, lastDataUpdate);
        final Watch watch = test.getWatch();
        assertThat(watch, not(equalTo(dimension)));
    }
    @Test public void testWatchEqualsAnotherWatchWithSameData() {
        final String dimension = "dimension";
        final String lastSchemaUpdate = DimensionWatch.LastSchemaUpdate;
        final String lastDataUpdate = DimensionWatch.LastDataUpdate;
        final DimensionWatch dimensionWatch = new DimensionWatch(dimension, lastSchemaUpdate, lastDataUpdate);
        final Watch test = dimensionWatch.getWatch();
        final Watch expected = new DimensionWatch(dimension, lastSchemaUpdate, lastDataUpdate).getWatch();
        assertThat(test, equalTo(expected));
    }
    @Test public void testWatchNotEqualsAnotherWatchWithDifferentData() {
        final String dimension = "dimension";
        final String lastSchemaUpdate = DimensionWatch.LastSchemaUpdate;
        final String lastDataUpdate = DimensionWatch.LastDataUpdate;
        final DimensionWatch dimensionWatch = new DimensionWatch(dimension, lastSchemaUpdate, lastDataUpdate);
        final Watch test = dimensionWatch.getWatch();
        Watch expected = new DimensionWatch(dimension, lastSchemaUpdate+"test", lastDataUpdate).getWatch();
        assertThat(test, not(equalTo(expected)));
        expected = new DimensionWatch(dimension, lastSchemaUpdate, lastDataUpdate+"test").getWatch();
        assertThat(test, not(equalTo(expected)));
    }

}
