package utils;

import java.math.BigInteger;

/**
 * Created by antoine on 10/11/15.
 */
public class BigIntegerUtils {


    public static boolean gt(BigInteger a, BigInteger b){
        return a.compareTo(b) == 1;
    }

    public static boolean lt(BigInteger a, BigInteger b){
        return a.compareTo(b) == -1;
    }

    public static boolean equals(BigInteger a, BigInteger b){
        return a.compareTo(b) == 0;
    }

    public static boolean lte(BigInteger a, BigInteger b){
        return !gt(a, b);
    }

    public static boolean gte(BigInteger a, BigInteger b){
        return !lt(a, b);
    }

}
