package math;

import java.math.BigInteger;

/**
 * Created by antoine on 10/11/15.
 */
public class Utils {

    public static BigInteger factorial(long n){
        BigInteger res = BigInteger.ONE;
        for(long i = 2; i <= n; i++){
            res = res.multiply(BigInteger.valueOf(i));
        }
        return res;
    }

    public static BigInteger combinationSize(long n, long k){

        BigInteger res = factorial(n).divide(factorial(k).multiply(factorial(n - k)));

        return res;
    }

}
