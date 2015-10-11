package algothread;

import org.paukov.combinatorics.ICombinatoricsVector;
import tetranucleotides.Code;

import java.math.BigInteger;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * Created by antoine on 10/11/15.
 */
public class TetraCallable implements Callable<BigInteger> {

    private List<ICombinatoricsVector<String>> codesToAnalyze;
    private int l;

    public TetraCallable(List<ICombinatoricsVector<String>> codesToAnalyze, int l) {
        this.codesToAnalyze = codesToAnalyze;
        this.l = l;
    }

    @Override
    public BigInteger call() throws Exception {

//System.out.println("Begin");
        BigInteger nbSelfComp = BigInteger.ZERO;

        for (ICombinatoricsVector<String> curCode : this.codesToAnalyze) {
            Code coco = new Code(curCode.getVector(), 4);
            if (coco.isCircular() && coco.isSelfComplementary()) {
                nbSelfComp = nbSelfComp.add(BigInteger.ONE);
            }
        }

        this.codesToAnalyze = null;
//        System.out.println("Number found : " + nbSelfComp + "\n\n");
        return nbSelfComp;
    }
}
