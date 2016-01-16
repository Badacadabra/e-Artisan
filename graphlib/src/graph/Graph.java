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
public class Graph<V> implements IGraph<V>, BFS<V>, DFS<V> {

    /** The name of the graph. */
    private String name;
    
    /** The list of all vertices of the graph. */
    private List<V> vertices;
    
    /** The list of all edges of the graph. */
    private List<Edge> edges;
    
    /** 
     * Constructor of an empty graph.
     */
    public Graph() {
        name = "Graph n°1";
        vertices = new ArrayList<>();
        edges = new ArrayList<>();
    }
    
    /**
     * Add a new vertex to the graph.
     * 
     * @param vertex The vertex to add.
     */
    @Override
    public void addVertex(V vertex) {
        vertices.add(vertex);
    }
    
    /**
     * Add a new edge to the graph.
     * 
     * @param vertex1 First vertex connected to an edge.
     * @param vertex2 Second vertex connected to an edge.
     */
    @Override
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
    @Override
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
    @Override
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
    public List<V> bfs(V startVertex) {
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
        while(!queue.isEmpty()) {
            V currentVertex = (V) queue.poll();
            level = getCurrentLevel(map, currentVertex) + 1;
            for (V vertexSuccessor : getSuccessors(currentVertex)) {
                // Handle
                if (vertexSuccessor.equals(startVertex)) { // A cycle is found
                    return computeCycle(map, startVertex, currentVertex);
                } else if (map.get(vertexSuccessor) == null) { // We find an unknown vertex
                    queue.add(vertexSuccessor);
                    List<Association<V>> currentAssociations = new ArrayList<>();
                    addAssociation(currentAssociations, currentVertex, level);
                    map.put(vertexSuccessor, currentAssociations);
                // We find a well-known vertex and it is not added to the queue (already in)
                // Useful to generate several cycles
                } else if (getCurrentLevel(map, vertexSuccessor) == level) {
                    List<Association<V>> successorsAssociations = map.get(vertexSuccessor);
                    addAssociation(successorsAssociations, currentVertex, level);
                }
            }
        }
        return null;
    }
    
    /**
     *  This method provides a means to crawl a graph using a DFS algorithm.
     *  (Not implemented yet.)
     *  
     *  @param The vertex from which the crawl starts.
     *  @return Cycles in the graph, using shortest paths.
     */
    @Override
    public List<V> dfs(V vertex) {
        return null;
    }

    /**
     * Helper method that computes cycles.
     * 
     * @param map A map which links a vertex with a list of associations (vertex/level).
     * @param startVertex The vertex from which the crawl started.
     * @param currentVertex The current vertex in the crawl process.
     * @return The computed cycles.
     */
    private List<V> computeCycle(Map<V,List<Association<V>>> map, V startVertex, V currentVertex) {
        V v = currentVertex;
        LinkedList<V> list = new LinkedList<>();
        while (v != startVertex) {
            list.addFirst(v);
            v = map.get(v).get(0).getVertex();
        }
        list.addFirst(v); // = startVertex 
        return list;
    }
    
    /**
     * Helper method which allows to access the current level.
     * 
     * @param map A map which links a vertex with a list of associations (vertex/level).
     * @param vertex The reference vertex.
     * @return The current level.
     */
    private int getCurrentLevel(Map<V,List<Association<V>>> map, V vertex) {
        return map.get(vertex).get(0).getLevel();
    }
    
    /**
     * Helper method which allows to add a new association (vertex/level) in a list.
     * 
     * @param list A list of associations.
     * @param vertex The vertex of the new association.
     * @param level The level of the new association.
     */
    private void addAssociation(List<Association<V>> list, V vertex, int level) {
        list.add(new Association<V>(vertex, level));
    }
    
    /**
     * Set the graph name.
     * 
     * @param name The graph name.
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * Get the graph name only.
     */
    public String toString() {
        return name; 
    }
    
}
