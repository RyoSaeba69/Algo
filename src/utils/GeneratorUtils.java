package utils;

import org.paukov.combinatorics.Factory;
import org.paukov.combinatorics.Generator;
import org.paukov.combinatorics.ICombinatoricsVector;
import org.paukov.combinatorics.combination.simple.SimpleCombinationGenerator;
import utils.BigIntegerUtils;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * Created by antoine on 10/11/15.
 */
public class GeneratorUtils {

    // compareto : -1 lower than
//                  0 equal
//                  1 greater than
    public static <T> List<ICombinatoricsVector<T>> generateObjectsRange(Generator<T> gen, BigInteger startIndex, BigInteger stopIndex) {

        List<ICombinatoricsVector<T>> list = new ArrayList<ICombinatoricsVector<T>>();
        Iterator<ICombinatoricsVector<T>> iterator = gen.iterator();
        BigInteger index = BigInteger.ONE;
        while (iterator.hasNext()) {
            if (BigIntegerUtils.gte(index, startIndex) && BigIntegerUtils.lte(index, stopIndex)) {
                list.add(iterator.next());
            } else if (BigIntegerUtils.gt(index, stopIndex)) {
                return list;
            } else {
                iterator.next();
            }
            index = index.add(BigInteger.ONE);
        }

//        while (iterator.hasNext() && BigIntegerUtils.lte(index, startIndex.subtract(BigInteger.ONE))) {
//            iterator.next();
//            index = index.add(BigInteger.ONE);
//        }
//
//        while (iterator.hasNext() && BigIntegerUtils.lte(index, stopIndex)) {
//            list.add(iterator.next());
//            index = index.add(BigInteger.ONE);
//        }
        return list;
    }
}
