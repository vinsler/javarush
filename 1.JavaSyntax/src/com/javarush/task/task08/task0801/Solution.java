package com.javarush.task.task08.task0801;

/* 
HashSet из растений
*/


import java.util.HashSet;
import java.lang.String;

public class Solution {
    public static void main(String[] args) throws Exception {

        HashSet<String> set = new HashSet<>();

        set.add("арбуз");
        set.add("банан");
        set.add("вишня");
        set.add("груша");
        set.add("дыня");
        set.add("ежевика");
        set.add("жень-шень");
        set.add("земляника");
        set.add("ирис");
        set.add("картофель");

        for (java.lang.String text : set)
            System.out.println(text);
    }
}
