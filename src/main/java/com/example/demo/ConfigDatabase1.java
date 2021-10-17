package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
@PropertySource({ "classpath:application.properties" })
@EnableJpaRepositories(
  basePackages = "com.example.demo.firstRepo",
  entityManagerFactoryRef = "userEntityManager",
  transactionManagerRef = "userTransactionManager"
)
public class ConfigDatabase1 {

  @Autowired
  private Environment env;

  @Primary
  @Bean
  public LocalContainerEntityManagerFactoryBean userEntityManager() {
    LocalContainerEntityManagerFactoryBean em
      = new LocalContainerEntityManagerFactoryBean();
    em.setDataSource(productDataSource());
    em.setPackagesToScan("com.example.demo.firstentity");

    HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
    em.setJpaVendorAdapter(vendorAdapter);
    HashMap<String, Object> properties = new HashMap<>();
    properties.put("hibernate.dialect",
      env.getProperty("org.hibernate.dialect.MySQL5Dialect"));
    em.setJpaPropertyMap(properties);

    return em;
  }

  @Primary
  @Bean
  @ConfigurationProperties(prefix="spring.first.datasource")
  public DataSource productDataSource() {
      return DataSourceBuilder.create().build();
  }

  @Primary
  @Bean
  public PlatformTransactionManager userTransactionManager() {

    JpaTransactionManager transactionManager
      = new JpaTransactionManager();
    transactionManager.setEntityManagerFactory(
      userEntityManager().getObject());
    return transactionManager;
  }
}
