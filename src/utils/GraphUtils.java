package utils;

import org.jgrapht.DirectedGraph;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleDirectedGraph;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by antoine on 9/18/15.
 */
public class GraphUtils {

    public static DirectedGraph<String, DefaultEdge> codeToGraph(List<String> code, int nbNucleotide) {


        DirectedGraph<String, DefaultEdge> resGraph = new SimpleDirectedGraph<String, DefaultEdge>(DefaultEdge.class);

//        By default i = 2 and n - i = 2
//        int i = 2;

//        int nbNucleotide = 4;

//        return null;
        for(String tetranucleotide : code){

            int n = tetranucleotide.length();

            for(int i = 1; i < nbNucleotide; i ++){
                String first = tetranucleotide.substring(0, i);
                String last = tetranucleotide.substring(i, n);

                if(!resGraph.containsVertex(first)){
                    resGraph.addVertex(first);
                }

                if(!resGraph.containsVertex(last)){
                    resGraph.addVertex(last);
                }

                resGraph.addEdge(first, last);

//                try{
//                    if(resGraph.addEdge(first, last) == null){
//                        System.out.println("EXCEPTION");
//
//                        return null;
//                    }
//                } catch (Exception e){
//                    System.out.println("EXCEPTION");
//                    return null;
//                }
            }
        }

//        System.out.println("Res graph => " + resGraph);
        return resGraph;

    }
}
