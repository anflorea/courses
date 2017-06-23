package ro.ubb.l5.server;


import org.springframework.context.annotation.AnnotationConfigApplicationContext;



public class ServerApp {
    public static void main(String[] args) {

        new AnnotationConfigApplicationContext("ro.ubb.l5.server.config");
        System.out.println("hello");


    }
}
