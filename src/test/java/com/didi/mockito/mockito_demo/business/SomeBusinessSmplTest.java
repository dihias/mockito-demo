package com.didi.mockito.mockito_demo.business;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SomeBusinessSmplTest {
    @Mock
    private DataService dataService;

    @InjectMocks
    private SomeBusinessSmpl someBusinessSmpl;

    @Test
    void findTheGreatestFromAllData_basic() {
        when(dataService.retreiveAllData()).thenReturn(new int[]{5,25,13});
        assertEquals(25, someBusinessSmpl.findTheGreatestFromAllData());
    }

    @Test
    void findTheGreatestFromAllData_OneValue() {
        when(dataService.retreiveAllData()).thenReturn(new int[]{13});
        assertEquals(13, someBusinessSmpl.findTheGreatestFromAllData());
    }

    @Test
    void findTheGreatestFromAllData_EmptyArray() {
        when(dataService.retreiveAllData()).thenReturn(new int[]{});
        assertEquals(Integer.MIN_VALUE, someBusinessSmpl.findTheGreatestFromAllData());
    }
}