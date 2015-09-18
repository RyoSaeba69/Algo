package utils;

import java.util.ArrayList;

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

}
