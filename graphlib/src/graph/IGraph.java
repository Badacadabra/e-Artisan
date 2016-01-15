package graph;

import java.util.List;

/**
 * Interface which gives a means to crawl a graph using a BFS algorithm.
 * 
 * @author Macky Dieng
 * @author Baptiste Vannesson
 *
 * @param <V> The type of vertex.
 */
public interface IGraph<V> {

    /**
     * This method defines a means to crawl a graph using a BFS algorithm.
     * 
     * @param vertex The vertex from which the crawl starts.
     * @return Cycles in the graph, using shortest paths.
     */
    List<V> bfs(V vertex);
    
}
