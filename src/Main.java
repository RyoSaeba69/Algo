import utils.Combination;

import java.lang.System;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        System.out.println("Beginning !");

        ArrayList<String> testArr = new ArrayList<String>();
        testArr.add("A");
        testArr.add("G");
        testArr.add("C");
        testArr.add("T");

        ArrayList<String> allTetranucleotids = Combination.generate(4, "", testArr, new ArrayList<String>());

        for (String str : allTetranucleotids) {
            System.out.println(str);
        }

        System.out.println("Length " + allTetranucleotids.size());

    }

}