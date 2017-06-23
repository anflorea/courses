package ro.ubb.l5.client;



import ro.ubb.l5.client.service.ClientService;
import ro.ubb.l5.client.tcp.TcpClient;
import ro.ubb.l5.client.ui.UI;
import ro.ubb.l5.common.*;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author radu.
 */
public class ClientApp {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        TcpClient tcpClient = new TcpClient(IService.SERVICE_HOST, IService.SERVICE_PORT);
        IService service = new ClientService(executorService, tcpClient);
        UI ui = new UI(service);
        ui.runUi();
        executorService.shutdownNow();
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
