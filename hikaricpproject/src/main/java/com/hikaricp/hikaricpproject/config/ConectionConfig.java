package com.hikaricp.hikaricpproject.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;




@Configuration
public class ConectionConfig {

     @Value("${spring.datasource.url}")
     private String url;
     @Value("${spring.datasource.username}")
     private String username;
     @Value("${spring.datasource.password}")
     private String password;
     @Value("${spring.datasource.driver-class-name}")
     private String driverClassName;
   
     
     @Value("${spring.datasource.hikari.maximum-pool-size}")
     private int hikariMaximumPoolSize;
     
     @Value("${spring.datasource.hikari.minimum-idle}")
     private int hikariMinimumIdle;
     
     @Value("${spring.datasource.hikari.connection-timeout}")
     private long hikariConnectionTimeout;
     
     @Value("${spring.datasource.hikari.idle-timeout}")
     private long hikariIdleTimeout;
     
     @Value("${spring.datasource.hikari.max-lifetime}")
     private long hikariMaxLifeTime;
   
     @Value("${spring.datasource.hikari.pool-name}")
     private String hikariPoolName;
     
   
 
     @Bean
    
     public DataSource datasource() {
        HikariConfig config = new HikariConfig();
        config.setUsername(username);
        config.setPassword(password);
        config.setDriverClassName(driverClassName);
        config.setJdbcUrl(url);
        
        config.setMaximumPoolSize(hikariMaximumPoolSize); 
        config.setMinimumIdle(hikariMinimumIdle);
        config.setConnectionTimeout(hikariConnectionTimeout);
        config.setMaxLifetime(hikariMaxLifeTime);
        config.setIdleTimeout(hikariIdleTimeout);
        //config.setConnectionTestQuery(hikariConnectionTestQuery);
        config.setPoolName(hikariPoolName);
        // log.info("Data source created");
      
        HikariDataSource dataSource = new HikariDataSource(config);
        return dataSource;
     }
 
 

}