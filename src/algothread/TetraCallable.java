package algothread;

import org.paukov.combinatorics.Generator;
import org.paukov.combinatorics.ICombinatoricsVector;
import tetranucleotides.Code;
import utils.BigIntegerUtils;
import utils.TetraBuffer;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * Created by antoine on 10/11/15.
 */
public class TetraCallable implements Callable<BigInteger> {

    private List<ICombinatoricsVector<String>> codesToAnalyze;
    private int l;
    private Generator<String> gen;
    private BigInteger startIndex;
    private BigInteger stopIndex;



    public TetraCallable(Generator<String> gen, BigInteger startIndex, BigInteger stopIndex , int l) {
        this.gen = gen;
        this.l = l;
        this.startIndex = startIndex;
        this.stopIndex = stopIndex;
    }

    @Override
    public BigInteger call() throws Exception {

//System.out.println("Begin");

        Iterator<ICombinatoricsVector<String>> iterator = gen.iterator();
        BigInteger currentIndex = BigInteger.ZERO;

        while(iterator.hasNext() && BigIntegerUtils.lt(currentIndex, this.startIndex)){
            iterator.next();
            currentIndex = currentIndex.add(BigInteger.ONE);
        }

        BigInteger nbSelfComp = BigInteger.ZERO;

        while(iterator.hasNext() && BigIntegerUtils.lt(currentIndex, this.stopIndex)){
            ICombinatoricsVector<String> curCode = iterator.next();
//
            Code coco = new Code(curCode.getVector(), 4);
            if (coco.isCircular() && coco.isSelfComplementary()) {
                nbSelfComp = nbSelfComp.add(BigInteger.ONE);
//                System.out.println("Code => " + coco.getTetranucleotides());
            }
            currentIndex = currentIndex.add(BigInteger.ONE);
        }

//        System.out.println("Number found : " + nbSelfComp + "\n\n");
        return nbSelfComp;
    }

//    public TetraCallable(List<ICombinatoricsVector<String>> codesToAnalyze, int l) {
//        this.codesToAnalyze = codesToAnalyze;
//        this.l = l;
//    }
//
//    @Override
//    public BigInteger call() throws Exception {
//
////System.out.println("Begin");
//        BigInteger nbSelfComp = BigInteger.ZERO;
//
//        for (ICombinatoricsVector<String> curCode : this.codesToAnalyze) {
//            Code coco = new Code(curCode.getVector(), 4);
//            if (coco.isCircular() && coco.isSelfComplementary()) {
//                nbSelfComp = nbSelfComp.add(BigInteger.ONE);
////                System.out.println("Code => " + coco.getTetranucleotides());
//            }
//
//        }
//
//
//
//        this.codesToAnalyze = null;
////        System.out.println("Number found : " + nbSelfComp + "\n\n");
//        return nbSelfComp;
//    }
}
