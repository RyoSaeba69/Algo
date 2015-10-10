import org.paukov.combinatorics.Generator;
import org.paukov.combinatorics.ICombinatoricsVector;
import tetranucleotides.Code;
import utils.Combination;
import utils.GraphUtils;

import java.lang.System;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Set;

//l1: 12
//l2: 168
//l3: 1408
//l4: 11728
//l5: 76312
//l6: 475 168 ~~

public class Main {

    public static void main(String[] args) {
        System.out.println("Beginning !");

        long time = System.currentTimeMillis();

        Generator<String> allCodes = Combination.generateSet(2);

        BigInteger nbSelfComp = BigInteger.ZERO;

        for (ICombinatoricsVector<String> curCode : allCodes) {
            Code coco = new Code(curCode.getVector(), 4);
            if (coco.isCircular() && coco.isSelfComplementary()) {
//                System.out.println("One More !");
                nbSelfComp = nbSelfComp.add(BigInteger.ONE);

            }
        }

        System.out.println("Self circular found ===> " + nbSelfComp);

        long ellapsedTime = (System.currentTimeMillis() - time) / 1000;

        System.out.println("Elapsed time : " + ellapsedTime +  " seconds");

    }

}