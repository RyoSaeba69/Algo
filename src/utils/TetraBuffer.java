package utils;

import java.util.ArrayList;

/**
 * Created by antoine on 10/10/15.
 */
public class TetraBuffer {

    private static TetraBuffer tb = null;
    private ArrayList<String> lOne = null;

    public TetraBuffer() {}

    public static TetraBuffer getInstance() {
        if(tb == null){
            tb = new TetraBuffer();
        }
        return tb;
    }

    public ArrayList<String>  getLOne(){

        if(this.lOne == null){
            ArrayList<String> nucleotids = new ArrayList<String>();
            nucleotids.add("A");
            nucleotids.add("G");
            nucleotids.add("C");
            nucleotids.add("T");
            lOne = Combination.generate(4, "", nucleotids, new ArrayList<String>());

        }

        return this.lOne;

    }

}
