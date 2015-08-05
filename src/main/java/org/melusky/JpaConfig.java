package org.melusky;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Properties;

/**
 * Created by mmelusky on 8/4/2015.
 */

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "org.melusky.model.obj.centralPaJug08042015.repository")
public class JpaConfig {

    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager tm = new JpaTransactionManager();
        tm.setEntityManagerFactory(entityManagerFactory().getObject());
        return tm;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor persistenceManager() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    @Bean
    public HikariDataSource mainDataSource() {

        HikariDataSource hikariDataSource = new HikariDataSource();
        hikariDataSource.setUsername("central_pa_jug");
        hikariDataSource.setPassword("password");
        hikariDataSource.setJdbcUrl("jdbc:postgresql://localhost/central_pa_jug_08042015?searchpath=application");
        hikariDataSource.setDriverClassName("org.postgresql.Driver");
        hikariDataSource.setMaximumPoolSize(20);
        hikariDataSource.setAutoCommit(true);
        return hikariDataSource;

    }

    @Bean
    public LazyConnectionDataSourceProxy dataSource() {
        LazyConnectionDataSourceProxy ds = new LazyConnectionDataSourceProxy();
        ds.setTargetDataSource(mainDataSource());
        return ds;
    }


    @Bean
    public HibernateJpaVendorAdapter vendorAdapter()
    {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setDatabasePlatform("");
        return vendorAdapter;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setPersistenceUnitName("caliphr-persistence");
        em.setDataSource(dataSource());
        em.setPackagesToScan(new String[]{
                "org.melusky.model.obj.centralPaJug08042015.**",
                "org.melusky.model.obj.centralPaJug08042015.iface.**",
                "org.melusky.model.obj.centralPaJug08042015.repository.**",
                "org.melusky.model.obj.centralPaJug08042015.repository.factory.**",
                "org.melusky.util.**"});
        em.setJpaVendorAdapter(vendorAdapter());
        em.setJpaProperties(additionalProperties());

        return em;
    }

    private Properties additionalProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.dialect","org.hibernate.dialect.PostgreSQL9Dialect");
        properties.setProperty("hibernate.flush_before_completion","true");
        properties.setProperty("hibernate.generate_statistics", "false");
        properties.setProperty("hibernate.show_sql", "false");
        properties.setProperty("hibernate.current_session_context_class","org.springframework.orm.hibernate4.SpringSessionContext");
        properties.setProperty("hibernate.id.new_generator_mappings", "true");
        properties.setProperty("hibernate.id.optimizer.pooled.prefer_lo","true");
        properties.setProperty("hibernate.cache.use_second_level_cache", "true");
        properties.setProperty("hibernate.cache.use_query_cache", "true");
        properties.setProperty("hibernate.cache.region.factory_class", "org.hibernate.cache.ehcache.EhCacheRegionFactory");
        return properties;
    }


}
