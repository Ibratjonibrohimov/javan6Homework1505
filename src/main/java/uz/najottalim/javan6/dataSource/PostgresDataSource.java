package uz.najottalim.javan6.dataSource;

import lombok.Data;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Data
@Configuration
public class PostgresDataSource {
    @Bean
    DataSource getDataSource(){
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.url("jdbc:postgresql://localhost:5432/Homework1505");
        dataSourceBuilder.username("postgres");
        dataSourceBuilder.password("p@ssw0rd");
        DataSource build = dataSourceBuilder.build();
        return build;
    }
}
