package ro.ubb.l5.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiServiceExporter;
import ro.ubb.l5.common.IService;
import ro.ubb.l5.common.domain.Client;
import ro.ubb.l5.common.domain.MovieClient;
import ro.ubb.l5.common.domain.validators.ClientValidator;
import ro.ubb.l5.common.domain.validators.MovieClientValidator;
import ro.ubb.l5.common.domain.validators.MovieValidator;
import ro.ubb.l5.common.domain.validators.Validator;
import ro.ubb.l5.server.repository.ClientDBRepository;
import ro.ubb.l5.server.repository.MovieDBRepository;
import ro.ubb.l5.server.repository.RentDBRepository;
import ro.ubb.l5.server.service.ServerService;

/**
 * Created by andrapop on 2017-03-31.
 */

@Configuration
public class ServerServiceConfig {
    @Bean
    public IService service(){

        return new ServerService();
    }

    @Bean
    public ClientDBRepository clientDBRepository(){

        return new ClientDBRepository(new ClientValidator());
    }


    @Bean
    public MovieDBRepository movieDBRepository(){

        return new MovieDBRepository(new MovieValidator());
    }

    @Bean
    public RentDBRepository rentDBRepository(){

        return new RentDBRepository(new MovieClientValidator());
    }

    @Bean
    public RmiServiceExporter rmiServiceExporter(){
        RmiServiceExporter exporter = new RmiServiceExporter();
        exporter.setServiceName("IService");
        exporter.setServiceInterface(IService.class);
        exporter.setService(service());
        return exporter;
    }
}
