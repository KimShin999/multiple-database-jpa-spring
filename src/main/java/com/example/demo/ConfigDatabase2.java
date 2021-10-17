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
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
@PropertySource({ "classpath:application.properties" })
@EnableJpaRepositories(
  basePackages = "com.example.demo.secondRepo",
  entityManagerFactoryRef = "userSecondEntityManager",
  transactionManagerRef = "userSecondTransactionManager"
)
public class ConfigDatabase2 {
  @Autowired
  private Environment env;

  @Primary
  @Bean
  public LocalContainerEntityManagerFactoryBean userSecondEntityManager() {
  LocalContainerEntityManagerFactoryBean em
    = new LocalContainerEntityManagerFactoryBean();
    em.setDataSource(productDataSourceSecond());
    em.setPackagesToScan("com.example.demo.secondentity");

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
  @ConfigurationProperties(prefix="spring.second.datasource")
  public DataSource productDataSourceSecond() {
    return DataSourceBuilder.create().build();
  }

  @Primary
  @Bean
  public PlatformTransactionManager userSecondTransactionManager() {

    JpaTransactionManager transactionManager
      = new JpaTransactionManager();
    transactionManager.setEntityManagerFactory(
      userSecondEntityManager().getObject());
    return transactionManager;
  }
}
