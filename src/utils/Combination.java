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

    public static ArrayList<ArrayList<String>> generateSet(int l) {

        ArrayList<String> nucleotids = new ArrayList<String>();
        nucleotids.add("A");
        nucleotids.add("G");
        nucleotids.add("C");
        nucleotids.add("T");
        ArrayList<String> lOne = generate(4, "", nucleotids, new ArrayList<String>());

        ArrayList<ArrayList<String>> resultSet = new ArrayList<ArrayList<String>>();

        for(String tetranucleotid : lOne){
            ArrayList<String> newCode = new ArrayList<String>();
            newCode.add(tetranucleotid);
            resultSet.add(newCode);
        }


        for(int i = 2; i <= l; i++){
            ArrayList<ArrayList<String>> currentResultSet = new ArrayList<ArrayList<String>>();
            for(ArrayList<String> code : resultSet){
                for (String nNucleotids : lOne) {
                    ArrayList<String> codeToAdd = new ArrayList<String>(code);
                    codeToAdd.add(nNucleotids);
                    currentResultSet.add(codeToAdd);
                }
            }
            resultSet = currentResultSet;
        }

        return resultSet;
    }

}
