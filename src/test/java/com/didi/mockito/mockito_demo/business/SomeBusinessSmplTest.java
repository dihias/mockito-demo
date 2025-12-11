package com.didi.mockito.mockito_demo.business;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SomeBusinessSmplTest {

    @Test
    void findTheGreatestFromAllData_basic() {
        DataService dataService= mock(DataService.class);
        when(dataService.retreiveAllData()).thenReturn(new int[]{5,25,13});
        SomeBusinessSmpl someBusinessSmpl = new SomeBusinessSmpl(dataService);
        assertEquals(25, someBusinessSmpl.findTheGreatestFromAllData());
    }

    @Test
    void findTheGreatestFromAllData_OneValue() {
        DataService dataService= mock(DataService.class);
        when(dataService.retreiveAllData()).thenReturn(new int[]{13});
        SomeBusinessSmpl someBusinessSmpl = new SomeBusinessSmpl(dataService);
        assertEquals(13, someBusinessSmpl.findTheGreatestFromAllData());
    }
}