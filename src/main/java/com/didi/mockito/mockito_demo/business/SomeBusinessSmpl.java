package com.didi.mockito.mockito_demo.business;

public class SomeBusinessSmpl {
    private DateService dateService;

    public SomeBusinessSmpl(DateService dateService) {
        this.dateService = dateService;
    }

    public int findTheGreatestFromAllData(){
       int[] data= dateService.retreiveAllData();
       int greatestValue = Integer.MIN_VALUE;
       for(int value:data){
           if(value>greatestValue)

               greatestValue = value;
           }
       return greatestValue;
    }
}

interface DateService{
    int[] retreiveAllData();
}