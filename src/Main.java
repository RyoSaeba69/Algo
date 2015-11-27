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
import java.util.Collection;
import java.util.List;
import java.util.concurrent.*;
import java.util.function.Predicate;

//l1: 12
//l2: 168
//l3: 1408
//l4: 11728
//l5: 76312
//l6: 475 168 ~~

public class Main {

    public static void main(String[] args) throws InterruptedException {
//        ArrayList<String> myLOne = new ArrayList<String>();

//        for (String tetra : TetraBuffer.getInstance().getLOne()) {
//            if(!myLOne.contains(Code.complementary(tetra))){
//                myLOne.add(tetra);
//                System.out.println("reduceLone.add(\""+tetra+"\")");
//            }
//        }

//        System.out.println("MYLONE LENGTH " + myLOne.size());

// With THREAD
        System.out.println("Beginning !");

        int nbThread = 16;

        int lOneSize = TetraBuffer.getInstance().getLOne().size();
//        int maxL = 3;

        for (int maxL = 1; maxL <= 5; maxL++) {


            Generator<String> allCodes = Combination.generateSet(maxL);
//        BigInteger threadSize = new BigInteger("2000000");
//            BigInteger threadSize = new BigInteger("15000000");
//            BigInteger threadSize = new BigInteger("1500000");

            long time = System.currentTimeMillis();

            ExecutorService pool = Executors.newFixedThreadPool(nbThread);

            ArrayList<Future<BigInteger>> futures = new ArrayList<Future<BigInteger>>();

            BigInteger combinSize = Utils.combinationSize(lOneSize, maxL);
            System.out.println("L" + maxL + " :: Code Size : " + combinSize);

            BigInteger threadSize = combinSize.divide(new BigInteger(nbThread + ""));
            if(threadSize.compareTo(BigInteger.ZERO) == 0){
                threadSize = combinSize;
            }


            BigInteger bi = BigInteger.ZERO;
            BigInteger biSum = BigInteger.ZERO;

            List<TetraCallable> callables = new ArrayList<TetraCallable>();

            long j = 0;
            boolean done = false;
            while(!done){
                BigInteger startIndex = threadSize.multiply(BigInteger.valueOf(j));
                BigInteger stopIndex = startIndex.add(threadSize);

                if(BigIntegerUtils.gte(stopIndex, combinSize)){
                    stopIndex = combinSize;
                    done = true;
                }
                callables.add(new TetraCallable(allCodes, startIndex, stopIndex, maxL));
//                futures.add(pool.submit(new TetraCallable(allCodes, startIndex, stopIndex, maxL)));
                j++;
            }

             futures.addAll(pool.invokeAll(callables));

//            while (BigIntegerUtils.lte(bi, combinSize)) {
////                List<ICombinatoricsVector<String>> codesToanalyze = GeneratorUtils.generateObjectsRange(allCodes, bi, bi.add(threadSize));
////                System.out.println("LENGTH " + codesToanalyze.size());
//                futures.add(pool.submit(new TetraCallable(allCodes, bi, bi.add(threadSize), maxL)));
////                codesToanalyze = null;
//                bi = bi.add(threadSize);
//            }

            while (futures.size() > 0) {
//                Thread.sleep(1000);
                for (int i = 0; i < futures.size(); i++) {
                    Future<BigInteger> f = futures.get(i);
                    if (f != null && f.isDone()) {
                        try {
                            biSum = biSum.add(f.get());

                            futures.set(i, null);
                        } catch (Exception e) {
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
//                try {
//                    Thread.sleep(1);
//                } catch (Exception e) {
//                    System.out.print(e);
//                }
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
        }
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