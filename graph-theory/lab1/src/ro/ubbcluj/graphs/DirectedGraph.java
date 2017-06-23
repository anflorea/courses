package ro.ubbcluj.graphs;

import ro.ubbcluj.Pair;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Created by Flo on 15-Mar-16.
 */
public class DirectedGraph {
    private HashMap<Integer, LinkedList<Integer>> dictIn;
    private HashMap<Integer, LinkedList<Integer>> dictOut;
    private int noVertex;

    /*
    Description: Constructor for the Directed Graph with no Costs
    Input: n -> the number of vertices
     */
    public DirectedGraph(int n) {
        this.noVertex = n;
        dictIn = new HashMap<>();
        dictOut = new HashMap<>();
        for(int i = 0; i < n; i++) {
            this.dictIn.put(i, new LinkedList<>());
            this.dictOut.put(i, new LinkedList<>());
        }
    }

    /*
        Description: Reads a graph from a file
        Input: filename (String) -> the name of the file
        Output: theGraph -> the graph represented in memory
     */
    public static DirectedGraph readGraphFromFile(String filename) throws IOException {
        Scanner scanner = new Scanner(new File(filename));
        try {
            int n = scanner.nextInt();
            int m = scanner.nextInt();
            DirectedGraph theGraph = new DirectedGraph(n);
            int x, y;
            for (int i = 0; i < m; i++) {
                x = scanner.nextInt();
                y = scanner.nextInt();
                int cost = scanner.nextInt();
                theGraph.addEdge(x, y);
            }
            return theGraph;
        } finally {
            scanner.close();
        }
    }

    /*
        Description: Adds a new vertex to the graph (at the end)
     */
    public void addVertex() {
        this.dictIn.put(noVertex, new LinkedList<>());
        this.dictOut.put(noVertex, new LinkedList<>());
        this.noVertex++;
    }

    /*
        Description: Adds a new edge to the graph (if it doesn't already exist.
     */
    public int addEdge(int vertex1, int vertex2) {
        if (vertex1 < this.getNoVertices() && vertex2 < this.getNoVertices() && !this.dictIn.get(vertex2).contains(vertex1))
        {
            this.dictOut.get(vertex1).push(vertex2);
            this.dictIn.get(vertex2).push(vertex1);
            return 1;
        }
        else
            return 0;
    }

    /*
        Description: Removes an edge from the Graph (if it exists)
     */
    public int removeEdge(int vertex1, int vertex2) {
        if (this.dictIn.get(vertex2).contains(vertex1)) {
            this.dictIn.get(vertex2).remove(this.dictIn.get(vertex2).indexOf(vertex1));
            this.dictOut.get(vertex1).remove(this.dictOut.get(vertex1).indexOf(vertex2));
            return 1;
        }
        else
            return 0;
    }

    /*
        Description: Checks weather an edge exists or not in the graph
     */
    public boolean existsEdge(int vertex1, int vertex2) {
        return this.dictOut.get(vertex1).contains(vertex2);
    }

    /*
        Description: Returns the number of vertices in the graph
     */
    public int getNoVertices() {
        return this.noVertex;
    }

    /*
        Description: Returns an iterable Set of all the vertices in the Graph
     */
    public Set<Integer> getIterableVertices() {
        return this.dictOut.keySet();
    }

    /*
        Description: Returns the in degree of a given vertex
     */
    public int getInDegree(int vertex) {
        return this.dictIn.get(vertex).size();
    }

    /*
        Description: Returns the out degree of a given vertex
     */
    public int getOutDegree(int vertex) {
        return this.dictOut.get(vertex).size();
    }

    /*
        Description: Returns an iterable list of all the in neighbours of a given vertex
     */
    public LinkedList<Integer> getIterableInForVertex(int vertex) {
        return this.dictIn.get(vertex);
    }

    /*
        Description: Returns an iterable list of all the out neighbours of a given vertex
     */
    public LinkedList<Integer> getIterableOutForVertex(int vertex) {
        return this.dictOut.get(vertex);
    }

    /*
        Description: Returns a list of the lowest length path between two vertices (if the path exists) using a backwards breath-first search
        Input: vertex1, vertex2
        Output: theList (if the path exists) / null (otherwise)
     */
    public LinkedList<Integer> bfs(int vertex1, int vertex2, boolean step) {
        LinkedList<Integer> queue = new LinkedList<Integer>();
        LinkedList<Integer> theList = new LinkedList<Integer>();
        HashMap<Integer, Integer> visited = new HashMap<Integer, Integer>();
        int s;

        visited.put(vertex2, -1);
        queue.add(vertex2);
        if (step)
            System.out.print("The steps are: ");

        while(queue.size() != 0 || !visited.containsKey(vertex1)) {
            s = queue.poll();
            if (step)
                System.out.print(s + " ");

            LinkedList<Integer> list = this.getIterableInForVertex(s);
            int i = 0;
            while (i < list.size()) {
                int n = list.get(i);
                if (!visited.containsKey(n)) {
                    visited.put(n, s);
                    queue.add(n);
                }
                i++;
            }
        }
        if (!visited.containsKey(vertex1))
            return null;
        s = vertex1;
        while (s != -1) {
            theList.add(s);
            s = visited.get(s);
        }
        return theList;
    }

}
