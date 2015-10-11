import algothread.AlgoRunnable;
import algothread.TetraCallable;
import math.Utils;
import org.paukov.combinatorics.Generator;
import org.paukov.combinatorics.ICombinatoricsVector;
import tetranucleotides.Code;
import utils.BigIntegerUtils;
import utils.Combination;
import utils.GeneratorUtils;
import utils.TetraBuffer;

import java.lang.System;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.Predicate;

//l1: 12
//l2: 168
//l3: 1408
//l4: 11728
//l5: 76312
//l6: 475 168 ~~

public class Main {

    public static void main(String[] args) {

//        int lOneSize = TetraBuffer.getInstance().getLOne().size();
//        int l = 2;
//
//        System.out.println("Beginning !");
//
//        long time = System.currentTimeMillis();
//
//        Generator<String> allCodes = Combination.generateSet(l);
//
//
//        System.out.println("CODES SIZE : " + Utils.combinationSize(lOneSize, l));
//
//        BigInteger nbSelfComp = BigInteger.ZERO;
//
//        for (ICombinatoricsVector<String> curCode : allCodes) {
//            Code coco = new Code(curCode.getVector(), 4);
//            if (coco.isCircular() && coco.isSelfComplementary()) {
//                nbSelfComp = nbSelfComp.add(BigInteger.ONE);
////                System.out.println("Found " + nbSelfComp + " Code " + coco.getTetranucleotides());
//            }
//        }
//
//        System.out.println("Self circular found ===> " + nbSelfComp);
//
//        long ellapsedTime = (System.currentTimeMillis() - time) / 1000;
//
//        System.out.println("Elapsed time : " + ellapsedTime +  " seconds");


// With THREAD
        System.out.println("Beginning !");

        int lOneSize = TetraBuffer.getInstance().getLOne().size();
        int maxL = 4;

        Generator<String> allCodes = Combination.generateSet(maxL);
//        BigInteger threadSize = new BigInteger("2000000");
        BigInteger threadSize = new BigInteger("1500000");

        long time = System.currentTimeMillis();

        ExecutorService pool = Executors.newFixedThreadPool(20);

        ArrayList<Future<BigInteger>> futures = new ArrayList<Future<BigInteger>>();

        BigInteger combinSize = Utils.combinationSize(lOneSize, maxL);
            System.out.println("L"+ maxL +" :: Code Size : " + combinSize);

        BigInteger bi = BigInteger.ZERO;
        BigInteger biSum = BigInteger.ZERO;


        while(BigIntegerUtils.lte(bi, combinSize)){
            List<ICombinatoricsVector<String>> codesToanalyze = GeneratorUtils.generateObjectsRange(allCodes, bi, bi.add(threadSize));
            futures.add(pool.submit(new TetraCallable(codesToanalyze, maxL)));
            codesToanalyze = null;
            bi = bi.add(threadSize);
        }

        while (futures.size() > 0){
            for(int i = 0; i < futures.size();i++){
                Future<BigInteger> f = futures.get(i);
                if(f != null && f.isDone()){
                    try{
                        biSum = biSum.add(f.get());
                        futures.set(i, null);
                    } catch (Exception e){
                        System.out.print(e);
                    }
                }
            }

            futures.removeIf(new Predicate<Future<BigInteger>>() {
                @Override
                public boolean test(Future<BigInteger> bigIntegerFuture) {
                    return bigIntegerFuture == null;
                }
            });
            try {
                Thread.sleep(1);
            } catch (Exception e){
                System.out.print(e);
            }
        }


//        for(int l = 1; l <= maxL; l++){
//            Generator<String> allCodes = Combination.generateSet(l);
//            BigInteger combinSize = Utils.combinationSize(lOneSize, l);
//            System.out.println("L"+ l +" :: Code Size : " + combinSize);
//            BigInteger nbSelfComp = BigInteger.ZERO;
//
//            List<ICombinatoricsVector<String>> codesToAnalyze = GeneratorUtils.generateObjectsRange(allCodes, BigInteger.ZERO, combinSize);
//
//            Thread t = new Thread(new AlgoRunnable(codesToAnalyze));
//            t.start();
//        }
        long ellapsedTime = (System.currentTimeMillis() - time) / 1000;

        System.out.println("Elapsed time : " + ellapsedTime + " seconds");
        System.out.println("Result : " + biSum);
        pool.shutdown();
//        END THREAD


//        System.out.println("Beginning !");
//
//        long time = System.currentTimeMillis();
//
//        for(int l = 1; l <= 60;l++){
//            Generator<String> allCodes = Combination.generateSet(l);
//
//            BigInteger nbSelfComp = BigInteger.ZERO;
//
//            for (ICombinatoricsVector<String> curCode : allCodes) {
//                Code coco = new Code(curCode.getVector(), 4);
//                if (coco.isCircular() && coco.isSelfComplementary()) {
//                    nbSelfComp = nbSelfComp.add(BigInteger.ONE);
//                }
//            }
//
//            System.out.println("L" + l + " : " + nbSelfComp + " in " + ((System.currentTimeMillis() - time) / 1000) + " seconds.");
//
//        }
//        long ellapsedTime = (System.currentTimeMillis() - time) / 1000;
//
//        System.out.println("Elapsed time : " + ellapsedTime +  " seconds");

    }

}