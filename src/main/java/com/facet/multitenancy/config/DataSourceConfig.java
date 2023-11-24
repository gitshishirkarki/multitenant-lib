package com.facet.multitenancy.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.util.Properties;

@Configuration
public class DataSourceConfig {
    @Value("${DATASOURCE_SERVER}")
    private String server;

    @Value("${DATASOURCE_PORT:3306}")
    private int port;
    @Value("${DATASOURCE_USERNAME}")
    private String username;
    @Value("${DATASOURCE_PASSWORD}")
    private String password;

    @Bean
    public DriverManagerDataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://" + server + ":" + port);
        dataSource.setUsername(username);
        dataSource.setPassword(password);

        Properties properties = new Properties();
        properties.setProperty("spring.jpa.hibernate.ddl-auto", "update");
        properties.setProperty("spring.jpa.properties.hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect");
        dataSource.setConnectionProperties(properties);

        return dataSource;
    }
}

