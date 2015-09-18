package tetranucleotides;

import org.jgrapht.DirectedGraph;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import utils.GraphUtils;

import java.util.ArrayList;

/**
 * Created by antoine on 9/18/15.
 */
public class Code {

    private ArrayList<String> trinucleotides;

    public Code(ArrayList<String> trinucleotides) {
        this.trinucleotides = trinucleotides;
    }

    public DirectedGraph<String, DefaultEdge> genGraph() {
        return GraphUtils.codeToGraph(this.trinucleotides);
    }

}
