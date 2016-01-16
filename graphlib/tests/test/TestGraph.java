package test;

import graph.Graph;

/**
 * A class for running some basic tests on classes which implement interfaces IGraph or BFS.
 * 
 * @author Macky Dieng
 * @author Baptiste Vannesson
 */
public class TestGraph {

    public static void main(String[] args) {
        
        Graph<String> graph = new Graph<String>();
        graph.setName("Test");
        
        String str1 = "Toto";
        String str2 = "Tata";
        String str3 = "Titi";
        String str4 = "Tutu";
        String str5 = "Tete";
        
        graph.addVertex(str1);
        graph.addVertex(str2);
        graph.addVertex(str3);
        graph.addVertex(str4);
        graph.addVertex(str5);
        
        graph.addEdge(str1, str2);
        graph.addEdge(str2, str3);
        graph.addEdge(str3, str4);
        graph.addEdge(str4, str5);
        assert graph.bfs(str1) == null : "BFS de « " + str1 + " » a trouvé un cycle alors qu'il n'aurait pas dû sur le graphe « " + graph + " »";
        graph.addEdge(str5, str1);
        assert graph.bfs(str1) != null : "BFS de « " + str1 + " » n'a pas trouvé de cycle alors qu'il aurait dû sur le graphe « " + graph + " »";
        
    }

}
