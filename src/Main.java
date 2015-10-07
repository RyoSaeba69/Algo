import tetranucleotides.Code;
import utils.Combination;
import utils.GraphUtils;

import java.lang.System;
import java.util.ArrayList;

//l2: 168
//l3: 1408
//l4: 11728
//l5: 76312
//l6: 475 168 ~~

public class Main {

    public static void main(String[] args) {
        System.out.println("Beginning !");

        ArrayList<String> testArr = new ArrayList<String>();
        testArr.add("A");
        testArr.add("G");
        testArr.add("C");
        testArr.add("T");

        ArrayList<String> allTetranucleotids = Combination.generate(4, "", testArr, new ArrayList<String>());

        int i = 0;
        for (String str : allTetranucleotids) {
            ArrayList<String> curCode = new ArrayList<String>();
            curCode.add(str);
            Code coco = new Code(curCode, 4);
            if(coco.isCircular() && coco.isSelfComplementary()){
                System.out.println(coco.getTetranucleotides());
                i++;
            }
        }
//        System.out.println("Length " + allTetranucleotids.size());
//
//        System.out.println("Geut one : "+i);

//        ArrayList<String> testCode = new ArrayList<String>();

//        testCode.add("AG");
//        testCode.add("CG");
//        testCode.add("GA");
//        testCode.add("TC");
//        testCode.add("TT");
//
//        testCode.add("AATG");
//        testCode.add("AGTT");
//        testCode.add("GTGT");
//        testCode.add("GTTT");
//
//
//        System.out.println(new Code(testCode));


//        ArrayList<String> figure_three = new ArrayList<String>();
//        figure_three.add("ATG");
//        figure_three.add("CAC");
//        figure_three.add("CAT");
//        figure_three.add("GTG");
//
//        Code c = new Code(figure_three, 3);
//
//        System.out.println("Graph Figure 3 => " + c.isSelfComplementary());


    }

}