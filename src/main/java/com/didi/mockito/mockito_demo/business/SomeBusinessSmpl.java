package com.didi.mockito.mockito_demo.business;

public class SomeBusinessSmpl {
    private DataService dataService;

    public SomeBusinessSmpl(DataService dataService) {
        this.dataService = dataService;
    }

    public int findTheGreatestFromAllData(){
       int[] data= dataService.retreiveAllData();
       int greatestValue = Integer.MIN_VALUE;
       for(int value:data){
           if(value>greatestValue)

               greatestValue = value;
           }
       return greatestValue;
    }
}

