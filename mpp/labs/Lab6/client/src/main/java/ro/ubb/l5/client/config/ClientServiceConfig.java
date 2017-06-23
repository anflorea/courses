package ro.ubb.l5.client.config;

/**
 * Created by andrapop on 2017-03-31.
 */

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;
import org.springframework.remoting.rmi.RmiServiceExporter;
import ro.ubb.l5.client.service.ClientService;
import ro.ubb.l5.common.IService;

@Configuration
@ComponentScan("ro.ubb.l5.client.service")
public class ClientServiceConfig {
    @Bean
    RmiProxyFactoryBean IService(){
        RmiProxyFactoryBean factoryBean = new RmiProxyFactoryBean();
        factoryBean.setServiceUrl("rmi://localhost:1099/IService");
        factoryBean.setServiceInterface(IService.class);
        return factoryBean;
    }

    @Bean
    public ClientService clientService() {
        ClientService clientService = new ClientService();
        return clientService;
    }
}
