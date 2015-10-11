package algothread;

import org.paukov.combinatorics.ICombinatoricsVector;
import tetranucleotides.Code;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by antoine on 10/10/15.
 */
public class AlgoRunnable implements Runnable {

    private List<ICombinatoricsVector<String>> codes;

    public AlgoRunnable(List<ICombinatoricsVector<String>> codes) {
        this.codes = codes;
    }

    @Override
    public void run() {

        BigInteger nbSelfComp = BigInteger.ZERO;

        for (ICombinatoricsVector<String> curCode : this.codes) {
            Code coco = new Code(curCode.getVector(), 4);
            if (coco.isCircular() && coco.isSelfComplementary()) {
                nbSelfComp = nbSelfComp.add(BigInteger.ONE);
            }
        }

//        System.out.println("Number found : " + nbSelfComp);

    }


    public List<ICombinatoricsVector<String>> getCodes() {
        return codes;
    }

    public void setCodes(List<ICombinatoricsVector<String>> codes) {
        this.codes = codes;
    }
}
