package com.didi.mockito.mockito_demo.business;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SomeBusinessSmplTest {

    @Test
    void findTheGreatestFromAllData_basic() {
        DataServiceStub dataServiceStub = new DataServiceStub();
        SomeBusinessSmpl someBusinessSmpl = new SomeBusinessSmpl(dataServiceStub);
        int result = someBusinessSmpl.findTheGreatestFromAllData();
        assertEquals(25,result);
    }
}

class DataServiceStub implements DateService{
    @Override
    public int[] retreiveAllData() {
        return new int[]{25,15,5};
    }
}