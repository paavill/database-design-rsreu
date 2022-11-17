package ru.rsreu.database_design_rsreu.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;

@Configuration
public class JdbcConfiguration {
    @Value("${datasource.url}")
    private String datasourceUrl;

    @Value("${datasource.username}")
    private String datasourceUser;

    @Value("${datasource.password}")
    private String datasourcePassword;

    @Value("${datasource.driver-class-name}")
    private String datasourceDriver;

    @Bean
    public Connection connection() throws SQLException {
        DriverManager.registerDriver(Objects.requireNonNull(getDriverClass()));
        return DriverManager.getConnection(datasourceUrl, datasourceUser, datasourcePassword);
    }

    private Driver getDriverClass() {
        try {
            return (Driver) Class.forName(datasourceDriver).newInstance();
        } catch (Exception exception) {
            return null;
        }
    }
}
