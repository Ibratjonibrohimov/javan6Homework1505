package uz.najottalim.javan6.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import uz.najottalim.javan6.dataSource.PostgresDataSource;

import javax.sql.DataSource;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Configuration
public class NamedJDBCConfig {
    @Autowired
    DataSource dataSource;
    @Bean
    public NamedParameterJdbcTemplate getBean(){
        return new NamedParameterJdbcTemplate(dataSource);
    }
}
