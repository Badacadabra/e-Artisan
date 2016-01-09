package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * This class describes a graph with useful methods to crawl it.
 * 
 * @author Macky Dieng
 * @author Baptiste Vannesson
 *
 * @param <V> The type of vertex.
 */
public class Graph<V> implements IGraph<V> {

    /**
     * The list of all vertices of the graph.
     */
    private List<V> vertices;
    
    /**
     * The list of all edges of the graph.
     */
    private List<Edge> edges;
    
    /**
     * Constructor of an empty graph.
     */
    public Graph() {
        vertices = new ArrayList<>();
        edges = new ArrayList<>();
    }
    
    /**
     * Add a new vertex to the graph.
     * 
     * @param vertex The vertex to add.
     */
    public void addVertex(V vertex) {
        vertices.add(vertex);
    }
    
    /**
     * Add a new edge to the graph.
     * 
     * @param vertex1 First vertex connected to an edge.
     * @param vertex2 Second vertex connected to an edge.
     */
    public void addEdge(V vertex1, V vertex2) {
        Edge tmp = new Edge(); 
        tmp.origin = vertex1;
        tmp.destination = vertex2;
        edges.add(tmp);
    }
    
    /**
     * Get the list of all vertices of the graph.
     * 
     * @return The list of all vertices of the graph.
     */
    public List<V> getVertices() {
        return vertices;
    }
    
    /**
     * Get the list of all successors of the specified vertex.
     * 
     * @param vertex The vertex for which we want to know the successors.
     * @return The list of successors of the specified vertex.
     */
    public List<V> getSuccessors(V vertex) {
        List<V> successors = new ArrayList<>();
        for (Edge e : edges) {
            if (e.origin.equals(vertex)) {
                successors.add(e.destination);
            }
        }
        return successors;
    }
    
    /**
     * Get the list of all predecessors of the specified vertex.
     * 
     * @param vertex The vertex for which we want to know the predecessors.
     * @return The list of predecessors of the specified vertex.
     */
    public List<V> getPredecessors(V vertex) {
        List<V> predecessors = new ArrayList<>();
        for (Edge e : edges) {
            if (e.destination.equals(vertex)) {
                predecessors.add(e.origin);
            }
        }
        return predecessors;
    }
    
    /**
     * An inner class which describes an edge. An edge is a link between two vertices.
     * 
     * @author Macky Dieng
     * @author Baptiste Vannesson
     */
    protected class Edge {
        
        /**
         * The vertex which represents the origin of the edge.
         */
        private V origin;
        
        /**
         * The vertex which represents the destination of the edge.
         */
        private V destination;
        
    }

    /**
     *  This method provides a means to crawl a graph using a BFS algorithm.
     *  
     *  @param The vertex from which the crawl starts.
     *  @return Cycles in the graph, using shortest paths.
     */
    @Override
    public boolean bfs(V startVertex) {
        // BFS with a stack would give DFS
        // Each vertex references a lit of associations (vertex/level)
        Map<V,List<Association<V>>> map = new HashMap<>();
        List<Association<V>> associations = new ArrayList<>();
        int level = 0;
        associations.add(new Association<V>(null, level));
        map.put(startVertex, associations);
        // We create a queue and we add the specified vertex to this queue
        Queue<V> queue = new LinkedList<>();
        queue.add(startVertex);
        // We iterate through the graph
        while(map.size() != getVertices().size()) {
            V currentVertex = (V) queue.poll();
            level++;
            for (V vertexSuccessor : getSuccessors(currentVertex)) {
                // Handle
                if (vertexSuccessor.equals(startVertex)) { // A cycle is found
                    return true;
                } else if (map.get(vertexSuccessor) == null) { // We find an unknown vertex
                    queue.add(vertexSuccessor);
                    associations.add(new Association<V>(currentVertex, level));
                    map.put(vertexSuccessor, associations);
                // We find an unknown vertex, but it is not added to the queue
                } else if (map.get(vertexSuccessor).get(0).getLevel() == map.get(startVertex).get(0).getLevel() + 1) {
                    associations.add(new Association<V>(currentVertex, level));
                    map.put(vertexSuccessor, associations);
                }
            }
        }
        return false;
    }

}
