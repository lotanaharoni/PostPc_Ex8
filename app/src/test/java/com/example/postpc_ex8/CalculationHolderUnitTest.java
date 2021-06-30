package com.example.postpc_ex8;

import junit.framework.TestCase;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.android.controller.ActivityController;
import org.robolectric.annotation.Config;

@RunWith(RobolectricTestRunner.class)
@Config(sdk = 28)
public class CalculationHolderUnitTest extends TestCase {

    MyCalculatorApp app;

    @Before
    public void setup(){

        ActivityController<MainActivity> activityController = Robolectric.buildActivity(MainActivity.class);
        MainActivity activityUnderTest = activityController.get();
        activityController.create().start().resume();
        app = new MyCalculatorApp();
    }

    @Test
    public void when_Item_is_created_with_number_ThenTheNumberIsReturnsCorrectly() {

        CalculationItem item = new CalculationItem(12);
        assertEquals(12, item.getNumber());
    }

    @Test
    public void when_Item_is_created_ThenTheNumberIsReturnsWithTheCorrectStatus() {

        CalculationItem item = new CalculationItem(12);
        assertEquals("currently_calculation", item.getStatus());
    }

    @Test
    public void when_CalculationHolder_is_created_ThenItsCreatesWithZeroItems() {

        CalculationHolder holder = app.getHolder();
        assertEquals(0, holder.getItems().size());
    }
}
