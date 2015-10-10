package utils;

import org.paukov.combinatorics.Factory;
import org.paukov.combinatorics.Generator;
import org.paukov.combinatorics.ICombinatoricsVector;

import java.util.*;

/**
 * Created by antoine on 9/18/15.
 */
public class Combination {

    public static ArrayList<String> generate(int maxLength, String current, ArrayList<String> elems, ArrayList<String> res) {
        if (current.length() == maxLength) {
            res.add(current);
        } else {
            for (String s : elems) {
                String oldCurrent = current;
                current += s;
                generate(maxLength, current, elems, res);
                current = oldCurrent;
            }
        }

        return res;
    }

    public static Generator<String> generateSet(int l) {

        ArrayList<String> lOne = TetraBuffer.getInstance().getLOne();


        ICombinatoricsVector<String> initialVector = Factory.createVector(lOne);

        Generator<String> gen = Factory.createSimpleCombinationGenerator(initialVector, l);

        return gen;
    }

}
