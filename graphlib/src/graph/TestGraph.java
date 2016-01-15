package graph;

public class TestGraph {

    public static void main(String[] args) {
        
        Graph<String> graph = new Graph<String>();
        
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
        graph.addEdge(str5, str1);
        
        System.out.println(graph.bfs(str1));
    }

}
