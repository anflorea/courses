package ro.ubbcluj;

import ro.ubbcluj.graphs.DirectedGraph;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Vector;

/**
 * Created by Flo on 28-Mar-16.
 */
public class Main2 {

    private static void printMenu() {
        System.out.println("#=================== Menu =================#");
        System.out.println("#                                          #");
        System.out.println("#  1. Add a new edge                       #");
        System.out.println("#  2. Remove an edge                       #");
        System.out.println("#  3. Verify if an edge exists             #");
        System.out.println("#  4. List all vertices                    #");
        System.out.println("#  5. Print a list of all the in edges     #");
        System.out.println("#  6. Print a list of all the out edges    #");
        System.out.println("#  7. Print all the in edges for a vertex  #");
        System.out.println("#  8. Print all the out edges for a vertex #");
        System.out.println("#  9. Find a lowest length path            #");
        System.out.println("#  10. Find the lowest lengths path length #");
        System.out.println("#  11. Find the lowest lengths path        #");
        System.out.println("#                 and print all the steps  #");
        System.out.println("#  0. Exit                                 #");
        System.out.println("#                                          #");
        System.out.println("#==========================================#");
    }

    private static void menu(DirectedGraph theGraph) {
        int     cmd;

        while (true) {
            Main2.printMenu();
            Scanner inScanner = new Scanner(System.in);
            try {
                cmd = inScanner.nextInt();
                if (cmd == 0) {
                    System.exit(0);
                }
                else if (cmd == 1) {
                    System.out.println("Insert the 2 vertices and the cost: ");
                    int res = theGraph.addEdge(inScanner.nextInt(), inScanner.nextInt());
                    if (res == 1)
                        System.out.println("Added successfully");
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
                        System.out.println("Failed. ");
                }
                else if (cmd == 3) {
                    System.out.println("Insert the 2 vertices: ");
                    boolean res = theGraph.existsEdge(inScanner.nextInt(), inScanner.nextInt());
                    if (res) {
                        System.out.println("The edge exists.");
                    }
                    else {
                        System.out.println("The edge does not exist. ");
                    }
                }
                else if (cmd == 4) {
                    System.out.println("The vertices are: \n" + theGraph.getIterableVertices());
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
                    System.out.println("Insert the vertex no: ");
                    int no = inScanner.nextInt();
                    if (no < theGraph.getNoVertices() && no >= 0) {
                        System.out.println(no + ":" + theGraph.getIterableInForVertex(no));
                    }
                    else
                        System.out.println("Vertex does not exist.");
                }
                else if (cmd == 8) {
                    System.out.println("Insert the vertex no: ");
                    int no = inScanner.nextInt();
                    if (no < theGraph.getNoVertices() && no >= 0) {
                        System.out.println(no + ":" + theGraph.getIterableOutForVertex(no));
                    }
                    else
                        System.out.println("Vertex does not exist.");
                }
                else if (cmd == 9) {
                    System.out.print("Insert the 2 vertices: ");
                    LinkedList<Integer> list = theGraph.bfs(inScanner.nextInt(), inScanner.nextInt(), false);
                    if (list != null) {
                        System.out.println("The path is: " + list);
                    }
                    else
                        System.out.println("There is no path between the 2 vertices.");
                }
                else if (cmd == 10) {
                    System.out.print("Insert the 2 vertices: ");
                    LinkedList<Integer> list = theGraph.bfs(inScanner.nextInt(), inScanner.nextInt(), false);
                    if (list != null) {
                        System.out.println("The path length is: " + list.size());
                    }
                    else
                        System.out.println("There is no path between the 2 vertices.");
                }
                else if (cmd == 11) {
                    System.out.print("Insert the 2 vertices: ");
                    LinkedList<Integer> list = theGraph.bfs(inScanner.nextInt(), inScanner.nextInt(), true);
                    if (list != null) {
                        System.out.println("\nThe path is: " + list);
                    }
                    else
                        System.out.println("There is no path between the 2 vertices.");
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
        DirectedGraph theGraph = null;
        try {
            theGraph = DirectedGraph.readGraphFromFile("exampleTopoSort1");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Main2.menu(theGraph);
    }
}
