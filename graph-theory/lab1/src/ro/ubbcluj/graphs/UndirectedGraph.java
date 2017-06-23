package ro.ubbcluj.graphs;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Set;

/**
 * Created by Flo on 28-Mar-16.
 */
public class UndirectedGraph {
    private HashMap<Integer, LinkedList<Integer>> dict;
    private int noVertex;

    public UndirectedGraph(int n) {
        this.noVertex = n;
        this.dict = new HashMap<>();
        for (int i = 0; i < n; i++) {
            this.dict.put(i, new LinkedList<>());
        }
    }

    public int addEdge(int vertex1, int vertex2) {
        if (vertex1 < noVertex && vertex2 < noVertex && !this.dict.get(vertex1).contains(vertex2)) {
            this.dict.get(vertex1).push(vertex2);
            this.dict.get(vertex2).push(vertex1);
            return 1;
        }
        else
            return 0;
    }

    public int removeEdge(int vertex1, int vertex2) {
        if (vertex1 < noVertex && vertex2 < noVertex && this.dict.get(vertex1).contains(vertex2)) {
            this.dict.get(vertex1).remove(this.dict.get(vertex1).indexOf(vertex2));
            this.dict.get(vertex2).remove(this.dict.get(vertex2).indexOf(vertex1));
            return 1;
        }
        return 0;
    }

    public boolean existsEdge(int vertex1, int vertex2) {
        return this.dict.get(vertex1).contains(vertex2);
    }

    public int getNoVertices() {
        return this.noVertex;
    }

    public Set<Integer> getIterableVertices() {
        return this.dict.keySet();
    }

    public LinkedList<Integer> getIterableForVertex(int vertex) {
        return this.dict.get(vertex);
    }
}
