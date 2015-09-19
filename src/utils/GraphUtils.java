package utils;

import org.jgrapht.DirectedGraph;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleDirectedGraph;

import java.util.ArrayList;

/**
 * Created by antoine on 9/18/15.
 */
public class GraphUtils {

    public static DirectedGraph<String, DefaultEdge> codeToGraph(ArrayList<String> code) {


        DirectedGraph<String, DefaultEdge> resGraph = new SimpleDirectedGraph<String, DefaultEdge>(DefaultEdge.class);

//        By default i = 2 and n - i = 2
        int i = 2;

        for(String tetranucleotide : code){

            int n = tetranucleotide.length();

            String first = tetranucleotide.substring(0, i);
            String last = tetranucleotide.substring(i, n);

            if(!resGraph.containsVertex(first)){
                resGraph.addVertex(first);
            }

            if(!resGraph.containsVertex(last)){
                resGraph.addVertex(last);
            }

            try{
                resGraph.addEdge(first, last);
            } catch (Exception e){
                return null;
            }

        }

        return resGraph;
    }
}
