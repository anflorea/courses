package ro.ubbcluj.graphs;

import java.io.*;
import java.util.*;

import ro.ubbcluj.Pair;
import ro.ubbcluj.PriorityQueue;

/**
 * Created by Flo on 15-Mar-16.
 */
public class DirectedGraphCosts implements Graph {
    private HashMap<Integer, LinkedList<Integer>> dictIn;
    private HashMap<Integer, LinkedList<Integer>> dictOut;
    private HashMap<Pair, Integer> costs;
    private int noVertex;

    /*
    Description: Constructor for the Directed Graph with Costs
    Input: n -> the number of vertices
     */
    public DirectedGraphCosts(int n) {
        this.noVertex = n;
        dictIn = new HashMap<>();
        dictOut = new HashMap<>();
        costs = new HashMap<>();
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
    public static DirectedGraphCosts readGraphFromFile(String filename) throws IOException {
        Scanner scanner = new Scanner(new File(filename));
        try {
            int n = scanner.nextInt();
            int m = scanner.nextInt();
            DirectedGraphCosts theGraph = new DirectedGraphCosts(n);
            int x, y, c;
            for (int i = 0; i < m; i++) {
                x = scanner.nextInt();
                y = scanner.nextInt();
                c = scanner.nextInt();
                theGraph.addEdge(x, y, c);
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
    public int addEdge(int vertex1, int vertex2, int cost) {
        if (vertex1 < this.getNoVertices() && vertex2 < this.getNoVertices() && !this.dictIn.get(vertex2).contains(vertex1))
        {
            this.dictOut.get(vertex1).push(vertex2);
            this.dictIn.get(vertex2).push(vertex1);
            this.costs.put(new Pair(vertex1, vertex2), cost);
            return 1;
        }
        else
            return 0;
    }

    /*
        Description: Removes an edge from the Graph (if it exists)
     */
    public int removeEdge(int vertex1, int vertex2) {
        Pair aPair = new Pair(vertex1, vertex2);
        if (this.costs.containsKey(aPair)) {
            this.costs.remove(aPair);
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
        Description: Returns the cost of a given edge (if it exists), or returns -2000000000 (if it does not exist)
     */
    public int getCost(int vertex1, int vertex2) {
        Pair aPair = new Pair(vertex1, vertex2);
        if (this.costs.containsKey(aPair)) {
            return (this.costs.get(aPair));
        }
        else
            return -2000000000;
    }

    /*
        Description: Modifies the cost of a given edge(if it exists)
     */
    public int updateCost(int vertex1, int vertex2, int cost) {
        Pair aPair = new Pair(vertex1, vertex2);
        if (vertex1 < this.noVertex && vertex2 < this.noVertex && this.costs.containsKey(aPair)) {
            this.costs.put(aPair, cost);
            return 1;
        }
        return 0;
    }

    /*
        Description: Finds a lowest length path between two vertices using the dijkstra algorithm
        Input: s, prev
            Precondition: s is the starting vertex
                          prev is a Dictionary that will help us reconstruct the path
        Output: dist
            Postcondition: dist is a Dictionary that will save the distances between the starting vertex and all the other vertices
     */
    public HashMap<Integer, Integer> dijkstra(int s, int t, HashMap<Integer, Integer> prev) {
        PriorityQueue q = new PriorityQueue();
        HashMap<Integer, Integer> dist = new HashMap<Integer, Integer>();
        q.add(s, 0);
        dist.put(s, 0);
        HashSet<Integer> visited = new HashSet<Integer>();
        visited.add(s);
        boolean ok = true;
        while (!q.isEmpty() && ok) {
            int x = q.pop();
            for (int y: this.getIterableOutForVertex(x)) {
                if ((!visited.contains(y)) || (dist.get(y) > dist.get(x) + this.getCost(x, y)) ) {
                    dist.put(y, dist.get(x) + this.getCost(x, y));
                    visited.add(y);
                    if (y == t) {
                        ok = false;
                        break;
                    }
                    q.add(y, dist.get(y));
                    prev.put(y, x);
                }

            }
        }
        return (dist);
    }

    /*
        Description: Recursive DFS to perform a topological sorting
     */
    private boolean topoSortRec(int x, Vector<Integer> sorted, HashSet<Integer> visited1, HashSet<Integer> visited2) {
        visited1.add(x);
        boolean ok;
        for (int y: this.getIterableInForVertex(x)) {
            if (!visited1.contains(y)) {
                ok = topoSortRec(y, sorted, visited1, visited2);
                if (!ok)
                    return false;
            }
            else if (!visited2.contains(y))
                return false;
        }
        visited2.add(x);
        sorted.add(x);
        return true;
    }

    /*
        Description: Performs a topological sorting on a DAG
     */
    public Vector<Integer> topoSort() {
        HashSet<Integer> visited1 = new HashSet<>();
        HashSet<Integer> visited2 = new HashSet<>();
        Vector<Integer> sorted = new Vector<>();
        for (int x: this.getIterableVertices()) {
            if (!visited1.contains(x)) {
                boolean ok = topoSortRec(x, sorted, visited1, visited2);
                if (!ok)
                    return null;
            }
        }
        return sorted;
    }

    /*
        Description: find the highest length path in a DAG
        Input: s, t
            Precondition: the starting and ending vertex
        Output: HashMap<Integer, Integer>
            Postcondition: the hashmap keeps the previous of each vertex and on position -1, it keeps the length
                            -returns null in case the graph is not a DAG
     */
    public HashMap<Integer, Integer> longestPath(int s, int t) {
        Vector<Integer> topoSort = this.topoSort();
        if (topoSort == null)
            return null;
        HashMap<Integer, Integer> prev = new HashMap<>();
        HashMap<Integer, Integer> costs = new HashMap<>();
        if (topoSort.indexOf(s) > topoSort.indexOf(t)) {
            int aux = s;
            s = t;
            t = aux;
        }
        boolean start = false;
        costs.put(s, 0);
        for(int x: topoSort) {
            if (x == s)
                start = true;
            else if (!start)
                costs.put(x, Integer.MIN_VALUE);
            else {
                costs.put(x, Integer.MIN_VALUE);
                for (int y: this.getIterableInForVertex(x)) {
                    if (costs.get(y) > Integer.MIN_VALUE) {
                        if (costs.get(y) + this.getCost(y, x) > costs.get(x))
                            prev.put(x, y);
                        costs.put(x, Integer.max(costs.get(x), costs.get(y) + this.getCost(y, x)));
                    }
                }
                if (x == t)
                    break;
            }
        }
        prev.put(-1, costs.get(t));
        return prev;
    }
}
