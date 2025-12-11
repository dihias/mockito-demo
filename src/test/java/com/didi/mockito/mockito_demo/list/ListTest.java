package com.didi.mockito.mockito_demo.list;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ListTest {
    @Test
    void test(){
        List listmock = mock(List.class);
        when(listmock.size()).thenReturn(3);
        assertEquals(3,listmock.size());
    }

    @Test
    void testMulstipleReturns(){
        List listmock = mock(List.class);
        when(listmock.size()).thenReturn(3).thenReturn(2);
        assertEquals(3,listmock.size());
        assertEquals(2,listmock.size());
        assertEquals(2,listmock.size());//the last return is persistent
        assertEquals(2,listmock.size());
    }

    @Test
    void testSpecificParameters(){
        List listmock = mock(List.class);
        when(listmock.get(0)).thenReturn("Some String");
        assertEquals("Some String",listmock.get(0));
        assertEquals(null,listmock.get(1));//by default the result is null, since we didn't specify for 1
    }

    @Test
    void testGenericParameters(){
        List listmock = mock(List.class);
        when(listmock.get(Mockito.anyInt())).thenReturn("Some String");
        assertEquals("Some String",listmock.get(0));
        assertEquals("Some String",listmock.get(1));
    }
}
