package ro.ubb.l5.server.config;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.Driver;


/**
 * Created by andrapop on 2017-04-01.
 */
@Configuration
public class JdbcConfig {

    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }

    @Bean
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(Driver.class.getName());
        //dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/MovieDB");
        dataSource.setUsername("postgres");
        //dataSource.setPassword(System.getProperty("postgres"));
        dataSource.setPassword("Ramses19");
        dataSource.setInitialSize(2);
        dataSource.setMaxActive(5);
        return dataSource;

    }
}
