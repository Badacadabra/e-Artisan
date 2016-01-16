package graph;

import java.util.List;

/**
 * Interface which defines a graph.
 * 
 * @author Macky Dieng
 * @author Baptiste Vannesson
 *
 * @param <V> The type of vertex.
 */
public interface IGraph<V> {

    /**
     * Add a new vertex to the graph.
     * 
     * @param vertex The vertex to add to the graph.
     */
    void addVertex(V vertex);
    
    /**
     * Add a new edge between two vertices.
     * 
     * @param vertex1 The origin of the edge.
     * @param vertex2 The destination of the edge.
     */
    void addEdge(V vertex1, V vertex2); 
    
    /**
     * Get all successors of a given vertex in the graph.
     * 
     * @param vertex The vertex for which we want to get the successors.
     * @return A list of successors.
     */
    List<V> getSuccessors(V vertex);
    
    /**
     * Get all predecessors of a given vertex in the graph.
     * 
     * @param vertex The vertex for which we want to get the predecessors.
     * @return A list of predecessors.
     */
    List<V>getPredecessors(V vertex);
    
}
