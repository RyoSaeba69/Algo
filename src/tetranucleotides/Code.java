package tetranucleotides;

import org.jgrapht.DirectedGraph;
import org.jgrapht.alg.CycleDetector;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import utils.GraphUtils;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by antoine on 9/18/15.
 */
public class Code {

    private ArrayList<String> tetranucleotides;

    private DirectedGraph<String, DefaultEdge> graph;


    public Code(ArrayList<String> tetranucleotides) {
        this.tetranucleotides = tetranucleotides;
        this.graph = GraphUtils.codeToGraph(this.tetranucleotides);
    }

    public boolean isCircular() {
        return this.graph != null
                && !new CycleDetector(this.graph).detectCycles();
    }

    public boolean isSelfComplementary() {

        boolean isSelfComplementary = true;

        for(int i = 0; i < this.tetranucleotides.size() && isSelfComplementary;i++){
            String tetranucleotide = this.tetranucleotides.get(0);
            isSelfComplementary = this.tetranucleotides.contains(reverseComplementary(tetranucleotide));
        }

        return isSelfComplementary;
    }

    public String toString() {

        String separator = ", ";

        String str = "Code : {";

        for(String tetranucleotide : tetranucleotides){
            str += tetranucleotide + separator;
        }

        if(tetranucleotides.size() > 0) {
            str = str.substring(0, str.length() - separator.length());
        }

        str += "} \n";

        if(this.graph == null){
            str += "Not a directed graph";
        } else {
            str += "Graph : " + this.graph.toString();
        }

        return str;
    }

    public static String reverseComplementary(String dna){
        HashMap<String, String> compMap = new HashMap<String, String>();
        compMap.put("A", "T");
        compMap.put("T", "A");
        compMap.put("G", "C");
        compMap.put("C", "G");

        String reverseDna = new StringBuilder(dna).reverse().toString();
        String res = "";

        for(int i = 0; i < reverseDna.length();i++){
            res +=  compMap.get(reverseDna.substring(i, i+1));
        }
        return res;
    }

    public ArrayList<String> getTetranucleotides() {
        return tetranucleotides;
    }

    public void setTetranucleotides(ArrayList<String> tetranucleotides) {
        this.tetranucleotides = tetranucleotides;
    }

    public DirectedGraph<String, DefaultEdge> getGraph() {
        return graph;
    }

    public void setGraph(DirectedGraph<String, DefaultEdge> graph) {
        this.graph = graph;
    }
}
