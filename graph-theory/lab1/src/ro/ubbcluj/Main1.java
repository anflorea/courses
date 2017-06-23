package ro.ubbcluj;

import ro.ubbcluj.graphs.DirectedGraphCosts;

import java.io.IOException;
import java.util.*;

/**
 * Created by Flo on 15-Mar-16.
 */
public class Main1 {

    private static void printMenu() {
        System.out.println("#=================== Menu =================#");
        System.out.println("#                                          #");
        System.out.println("#  1. Add a new edge                       #");
        System.out.println("#  2. Remove an edge                       #");
        System.out.println("#  3. Verify if an edge exists             #");
        System.out.println("#  4. List all vertices                    #");
        System.out.println("#  5. Print a list of all the in edges     #");
        System.out.println("#  6. Print a list of all the out edges    #");
        System.out.println("#  7. Print the cost of an edge            #");
        System.out.println("#  8. Update the cost of an edge           #");
        System.out.println("#  9. Add a new vertex                     #");
        System.out.println("#  10. Dijkstra                            #");
        System.out.println("#  11. Dijkstra path                       #");
        System.out.println("#  12. Topological sort                    #");
        System.out.println("#  13. Highest cost path                   #");
        System.out.println("#  0. Exit                                 #");
        System.out.println("#                                          #");
        System.out.println("#==========================================#");
    }

    public static void menu(DirectedGraphCosts theGraph) {
        int     cmd;

        while (true) {
            Main1.printMenu();
            Scanner inScanner = new Scanner(System.in);
            try {
                cmd = inScanner.nextInt();
                if (cmd == 0) {
                    System.exit(0);
                }
                else if (cmd == 1) {
                    System.out.println("Insert the 2 vertices and the cost: ");
                    int res = theGraph.addEdge(inScanner.nextInt(), inScanner.nextInt(), inScanner.nextInt());
                    if (res == 1)
                        System.out.println("Added successfuly");
                    else
                        System.out.println("Failed.");
                }
                else if (cmd == 2) {
                    System.out.println("Insert the 2 vertices: ");
                    int res = theGraph.removeEdge(inScanner.nextInt(), inScanner.nextInt());
                    if (res == 1) {
                        System.out.println("Remove successful");
                    }
                    else
                        System.out.println("Failed.");
                }
                else if (cmd == 3) {
                    System.out.println("Insert the 2 vertices: ");
                    boolean res = theGraph.existsEdge(inScanner.nextInt(), inScanner.nextInt());
                    if (res) {
                        System.out.println("The edge exists.");
                    }
                    else {
                        System.out.println("The edge does not exist.");
                    }
                }
                else if (cmd == 4) {
                    System.out.println("The vertices are:\n" + theGraph.getIterableVertices());
                }
                else if (cmd == 5) {
                    for (int i = 0; i < theGraph.getNoVertices(); i++) {
                        System.out.println(i + ": " + theGraph.getIterableInForVertex(i));
                    }
                }
                else if (cmd == 6) {
                    for (int i = 0; i < theGraph.getNoVertices(); i++) {
                        System.out.println(i + ": " + theGraph.getIterableOutForVertex(i));
                    }
                }
                else if (cmd == 7) {
                    System.out.println("Insert the 2 vertices:");
                    int res = theGraph.getCost(inScanner.nextInt(), inScanner.nextInt());
                    if (res != -2000000000) {
                        System.out.println("The cost is: " + res);
                    }
                    else
                        System.out.println("The edge does not exist");
                }
                else if (cmd == 8) {
                    System.out.println("Insert the 2 vertices and the cost: ");
                    int res = theGraph.updateCost(inScanner.nextInt(), inScanner.nextInt(), inScanner.nextInt());
                    if (res == 1) {
                        System.out.println("Update successful");
                    }
                    else
                        System.out.println("Failed.");
                }
                else if (cmd == 9) {
                    theGraph.addVertex();
                }
                else if (cmd == 10) {
                    System.out.println("Please insert the starting and ending vertex: ");
                    int s = inScanner.nextInt();
                    int t = inScanner.nextInt();
                    HashMap<Integer, Integer> prev = new HashMap<>();
                    HashMap<Integer, Integer> dist = theGraph.dijkstra(s, t, prev);
                    if (dist.containsKey(t))
                        System.out.println("The cost is: " + dist.get(t));
                    else
                        System.out.println("There is no walk between the two vertices.");
                }
                else if (cmd == 11) {
                    System.out.println("Please insert the starting and ending vertex: ");
                    int s = inScanner.nextInt();
                    int t = inScanner.nextInt();
                    HashMap<Integer, Integer> prev = new HashMap<>();
                    HashMap<Integer, Integer> dist = theGraph.dijkstra(s, t, prev);
                    if (dist.containsKey(t)) {
                        Vector<Integer> list = new Vector<>();
                        int x = t;
                        list.add(x);
                        while (prev.get(x) != null) {
                            list.add(prev.get(x));
                            x = prev.get(x);
                        }
                        Collections.reverse(list);
                        System.out.print("The walk is: ");
                        for (int y: list) {
                            System.out.print(y + " ");
                        }
                        System.out.println();
                    }
                    else
                        System.out.println("There is no walk between the two vertices.");
                }
                else if (cmd == 12) {
                    Vector<Integer> sorted = theGraph.topoSort();
                    if (sorted != null) {
                        System.out.println(sorted);
                    }
                    else
                        System.out.println("Error! The graph is not a DAG. It has cycles.");
                }
                else if (cmd == 13) {
                    System.out.print("Please insert the starting and ending vertices: ");
                    int s = inScanner.nextInt();
                    int t = inScanner.nextInt();
                    HashMap<Integer, Integer> prev = theGraph.longestPath(s, t);
                    if (prev == null)
                        System.out.println("Error! The graph is not a DAG. It has cycles.");
                    else if (prev.get(-1) == Integer.MIN_VALUE) {
                        System.out.println("There is no walk between the two vertices");
                    }
                    else {
                        System.out.print("Length: ");
                        System.out.println(prev.get(-1));
                        Vector<Integer> theVector = new Vector<>();
                        int x = t;
                        while (x != s) {
                            theVector.add(x);
                            x = prev.get(x);
                        }
                        theVector.add(x);
                        Collections.reverse(theVector);
                        System.out.print("Path  : ");
                        System.out.println(theVector);
                    }
                }
                else {
                    throw new Exception("Bad input.");
                }
            }
            catch (Exception e) {
                System.out.println("Bad input or Unknown command.");
            }
        }
    }

    public static void main(String[] args) {
        DirectedGraphCosts theGraph = null;
        try {
            theGraph = DirectedGraphCosts.readGraphFromFile("exampleTopoSort1");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Main1.menu(theGraph);
    }
}
