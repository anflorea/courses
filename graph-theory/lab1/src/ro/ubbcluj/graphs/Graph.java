package ro.ubbcluj.graphs;

import java.util.LinkedList;
import java.util.Set;

/**
 * Created by Flo on 27-Mar-16.
 */
/*
    Description: An interface for a Directed Graph with Costs
 */
public interface Graph {
    int addEdge(int vertex1, int vertex2, int cost);

    boolean existsEdge(int vertex1, int vertex2);

    int getNoVertices();

    Set<Integer> getIterableVertices();

    int getInDegree(int vertex);

    int getOutDegree(int vertex);

    LinkedList<Integer> getIterableInForVertex(int vertex);

    LinkedList<Integer> getIterableOutForVertex(int vertex);
}
