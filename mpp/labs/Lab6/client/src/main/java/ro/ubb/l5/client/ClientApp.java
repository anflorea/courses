package ro.ubb.l5.client;



import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ro.ubb.l5.client.service.ClientService;
import ro.ubb.l5.client.ui.UI;
import ro.ubb.l5.common.*;
import ro.ubb.l5.common.domain.Movie;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author radu.
 */
public class ClientApp {
    public static void main(String[] args) {
        /*
        ApplicationContext context = new AnnotationConfigApplicationContext(
                "ro.ubb.l5.client" +
                        ".config");
        ClientService serviceClient = context.getBean(ClientService.class);
        for (Movie movie : serviceClient.getAllMovies()) {

            System.out.println(movie.toString());
        }*/


        IService service = new ClientService();
        UI ui = new UI(service);
        ui.runUi();

        /*
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        TcpClient tcpClient = new TcpClient(IService.SERVICE_HOST, IService.SERVICE_PORT);
        IService clientService = new IService(executorService, tcpClient);
        MovieService movieService = new MovieServiceClient(executorService, tcpClient);
        UI ui = new UI(movieService, clientService);
        ui.runUi();
        executorService.shutdownNow();
       */
    }
}
