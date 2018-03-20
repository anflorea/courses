package ui;

import controller.StoreController;
import model.Product;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Vlad on 17-Mar-16.
 */
public class StoreUI {
    public StoreController ctrl;
    Scanner in;
    public StoreUI(StoreController ctrl){
        this.ctrl=ctrl;
        this.in=new Scanner(System.in);
    }
    public void printMenu() {
        String menu;
        menu = "These are the available commands:\n";
        menu += "\t 1 - Add a new Product \n";
        menu += "\t 2 - Displays all products from category\n";
        menu += "\t 3 - Stock situation for all productst \n";
        menu += "\t 4 - Stock situation for a certain product \n";
        menu += "\t 0 - to exit; \n";
        System.out.println(menu);
    }

    public void AddNewProduct()
    {
        System.out.println("Give the Product code:");
        int pCode = Integer.parseInt(in.nextLine());

        System.out.println("Give the product name:");
        String pName = in.nextLine();

        System.out.println("Give the product category:");
        String pCategory = in.nextLine();

        System.out.println("Give the quantity:");
        int pQunatity = Integer.parseInt(in.nextLine());

        Product p = new Product(pCode,pName,pCategory,pQunatity);
        ctrl.addProduct(p);
        System.out.println("Product Added");
    }

    public void DisplayCategory()
    {
        System.out.println("Give category");
        String cat=in.nextLine();
        ArrayList<Product> temp = ctrl.getProductsCategory(cat);

        for (Product p : temp) {
            System.out.println(p.toString());
        }
    }

    public void DisplayStock()
    {

        ArrayList<Product> temp = ctrl.stockSituation();

        for (Product p : temp) {
            System.out.println(p.toString());
        }
    }

    public void DisplayStockFor()
    {
        System.out.println("Give product name");
        String cat=in.nextLine();
        ArrayList<Product> temp = ctrl.stockSituationProduct(cat);

        for (Product p : temp) {
            System.out.println(p.toString());
        }
    }

    public int readCommand() {
        System.out.println("Give a command: ");
        int c = 0;
        c = Integer.parseInt(in.nextLine());
        return c;
    }

    public void executeCommand(int c) {
        switch(c) {
            case 1:
                AddNewProduct();
                break;
            case 2:
                DisplayCategory();
                break;
            case 3:
                DisplayStock();
                break;
            case 4:
                DisplayStockFor();
                break;
            default:
                System.out.println("B'bye now!...\n");
                break;
        }
    }

    public void run() {
        int c;
        do {
            printMenu();
            c = readCommand();
            executeCommand(c);
        } while(c != 0);

    }



}
