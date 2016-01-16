package graph;

import java.util.List;

/**
 * Interface which defines a means to crawl a graph using a DFS algorithm.
 * 
 * @author Macky Dieng
 * @author Baptiste Vannesson
 *
 * @param <V> The type of vertex.
 */
public interface DFS<V> {

    /**
     * This method defines a means to crawl a graph using a DFS algorithm.
     * 
     * @param vertex The vertex from which the crawl starts.
     * @return Cycles in the graph, using shortest paths.
     */
    List<V> dfs(V vertex);
    
}
