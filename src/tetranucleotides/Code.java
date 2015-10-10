package tetranucleotides;

import org.jgrapht.DirectedGraph;
import org.jgrapht.alg.CycleDetector;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import utils.GraphUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by antoine on 9/18/15.
 */
public class Code {

    private List<String> tetranucleotides;

    private DirectedGraph<String, DefaultEdge> graph;

    private int nbNucleotides;


    public Code(List<String> tetranucleotides, int nbNucleotides) {
        this.tetranucleotides = tetranucleotides;
        this.nbNucleotides = nbNucleotides;
        this.graph = GraphUtils.codeToGraph(this.tetranucleotides, nbNucleotides);
    }

    public boolean isCircular() {
        return this.graph != null
                && !new CycleDetector(this.graph).detectCycles();
    }

    public boolean isSelfComplementary() {

        boolean isSelfComplementary = true;

        for(int i = 0; i < this.tetranucleotides.size() && isSelfComplementary;i++){
            String tetranucleotide = this.tetranucleotides.get(i);
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

    public static List<String> reverseArray(List<String> code){

        for(int i = 0; i < code.size();i++){
            code.set(i, reverseComplementary(code.get(i)));
        }
        return code;
    }

    public List<String> getTetranucleotides() {
        return tetranucleotides;
    }

    public void setTetranucleotides(List<String> tetranucleotides) {
        this.tetranucleotides = tetranucleotides;
    }

    public DirectedGraph<String, DefaultEdge> getGraph() {
        return graph;
    }

    public void setGraph(DirectedGraph<String, DefaultEdge> graph) {
        this.graph = graph;
    }
}
