package com.rb.auction.configuration;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Configuration
@ComponentScan("com.rb.auction")
public class AppConfiguration implements WebMvcConfigurer {

    private static final String[] CLASSPATH_RESOURCE_LOCATIONS = {
            "classpath:/META-INF/resources/",
            "classpath:/resources/",
            "classpath:/static/",
            "classpath:/public/"

    };

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry resourceHandlerRegistry) {
        resourceHandlerRegistry.addResourceHandler("/**")
                .addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS);
    }

    @Bean
    public Connection connection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("From bean connection");
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/estore", "root", "root");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    /*
    @Bean
    public org.hibernate.cfg.Configuration configurationHibernate() {
        return new org.hibernate.cfg.Configuration()
                .addClass()
                .addClass()
                .setProperty("hibernate.dialect")
                .setProperty("hibernate.connection.datasource", )
                .setProperty("hibernate.order_update", "true")
    }
    */

    @Bean
    public SessionFactory sessionFactory()  {
        return new org.hibernate.cfg.Configuration().configure().buildSessionFactory();
    }
}

