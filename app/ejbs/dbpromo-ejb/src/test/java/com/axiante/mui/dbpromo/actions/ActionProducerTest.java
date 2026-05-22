package com.axiante.mui.dbpromo.actions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ActionProducerTest {

    @InjectMocks
    private ActionProducer producer;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() {
        producer.init();
        verifyLoadedActions();
    }

    @Test
    public void find_givenNullCodiceAzione_shouldThrowException() {
        exception.expect(NullPointerException.class);
        producer.find(null);
    }

    @Test
    public void find_givenNonExistingCodiceAzione_shouldReturnNull() {
        assertNull(producer.find("foo"));
    }

    @Test
    public void find_givenExistingCodiceAzione_shouldReturnAction() {
        final Action action = producer.find("renderFasciaOraria");
        assertNotNull(action);
    }

    @SuppressWarnings("unchecked")
    private void verifyLoadedActions() {
        try {
            final Field field = producer.getClass().getDeclaredField("actions");
            field.setAccessible(true);
            final Set<Action> actions = (Set<Action>) field.get(producer);
            //feature #5153: mappatura di una azione che non funziona sui form
            assertEquals(ActionEnum.values().length-1, actions.size());
            //feature #5153: mappatura di una azione che non funziona sui form: rimuovo
            List<ActionEnum> actualActions = Arrays.stream(ActionEnum.values()).filter(a -> a != ActionEnum.EDIT_SHOP_WHILE_EXECUTION_IN_PROGRESS).collect(Collectors.toList());
            assertEquals(actualActions.stream().sorted(Comparator.comparing(ActionEnum::getName)).collect(Collectors.toList()),
                    actions.stream().map(Action::getCode).sorted(Comparator.comparing(ActionEnum::getName)).collect(Collectors.toList()));
        } catch (NoSuchFieldException | IllegalAccessException ex) {
            fail(ex.getMessage());
        }
    }
}