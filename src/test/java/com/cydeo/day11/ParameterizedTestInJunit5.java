package com.cydeo.day11;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class ParameterizedTestInJunit5 {

    @ParameterizedTest
    @ValueSource(ints = {1,2,3,4,5,6,8,9})
    public void testMultipleNumbers(int number){
        System.out.println("number = " + number);
       // Assertions.assertTrue(number>5);
    }

    @ParameterizedTest
    @ValueSource(strings = {"john","abbas","ali","TJ"})
    public void testMultipleNames(String name) {
        System.out.println("name = " + name);
    }



}

