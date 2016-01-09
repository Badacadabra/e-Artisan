package graph;

/**
 * This class describes an association. An association is a pair vertex/level.
 * 
 * @author Macky Dieng
 * @author Baptiste Vannesson
 *
 * @param <V> The type of vertex.
 */
public class Association<V> {
    
    /**
     * The vertex in the association.
     */
    private V vertex;
    
    /**
     * The level in the association.
     */
    private int level;
    
    /**
     * Constructor of an association.
     * 
     * @param vertex The vertex in the association.
     * @param level The level in the association.
     */
    public Association(V vertex, int level) {
        this.vertex = vertex;
        this.level = level;
    }

    /**
     * Get the vertex in the association.
     * 
     * @return The vertex in the association.
     */
    public V getVertex() {
        return vertex;
    }

    /**
     * Set the vertex in the association.
     * 
     * @param vertex The new vertex.
     */
    public void setVertex(V vertex) {
        this.vertex = vertex;
    }

    /**
     * Get the level in the association.
     * 
     * @return The level in the association.
     */
    public int getLevel() {
        return level;
    }

    /**
     * Set the level in the association.
     * 
     * @param level The new level.
     */
    public void setLevel(int level) {
        this.level = level;
    }
    
}
