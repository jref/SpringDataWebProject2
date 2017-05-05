package com.ua.codespace.config;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import com.ua.codespace.service.PhotoService;
import com.ua.codespace.service.impl.AmazonPhotoServiceImpl;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories("com.ua.codespace.repository")
@Import(TransactionConfig.class)
public class JpaConfig {

    //todo: don't forget to set your database properties in the application.properties file
    @Value("${spring.data.db.url}")
    private String url;
    @Value("${spring.data.db.user}")
    private String user;
    @Value("${spring.data.db.password}")
    private String password;
    @Value("${spring.data.db.driver}")
    private String driverClassName;
    @Value("${spring.data.db.cp.maxSize}")
    private Integer maxPoolSize;
    @Value("${hibernate.hbm2ddl.auto}")
    private String hbm2dll;


    @Bean
    @Profile("test")
    DataSource testDataSource() {
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .setName("bambooTestDB")
                .build();
    }

    @Bean
    @Profile("dev")
    DataSource dataSource() {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setURL(url);
        dataSource.setUser(user);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean
    @Profile({"stage", "prod"})
    DataSource pooledDataSource(HikariConfig config) {
        return new HikariDataSource(config);
    }

    @Bean
    @Profile({"stage", "prod"})
    HikariConfig hikariConfig() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(url);
        hikariConfig.setUsername(user);
        hikariConfig.setPassword(password);
        hikariConfig.setDriverClassName(driverClassName);
        hikariConfig.setMaximumPoolSize(maxPoolSize);
        hikariConfig.setPoolName("springHikariCP");
        hikariConfig.setConnectionTestQuery("SELECT 1");

        return hikariConfig;
    }

    @Bean
    @Profile("test")
    JpaVendorAdapter testJpaVendorAdapter() {
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setDatabase(Database.H2);
        adapter.setShowSql(true);

        adapter.setGenerateDdl(true);

        adapter.setDatabasePlatform("org.hibernate.dialect.H2Dialect");
        return adapter;
    }

    @Bean
    @Profile({"dev", "stage", "prod"})
    JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setDatabase(Database.MYSQL);
        adapter.setShowSql(true);

        adapter.setGenerateDdl(false);
        adapter.setDatabasePlatform("org.hibernate.dialect.MySQLDialect");

        return adapter;
    }

    @Bean("entityManagerFactory")
    LocalContainerEntityManagerFactoryBean localContainerEMF(DataSource dataSource, JpaVendorAdapter jpaVendorAdapter) {
        LocalContainerEntityManagerFactoryBean lcmfb = new LocalContainerEntityManagerFactoryBean();
        lcmfb.setDataSource(dataSource);
        lcmfb.setJpaVendorAdapter(jpaVendorAdapter);
        lcmfb.setPersistenceUnitName("bamboo");//todo: you can rename persistent unit
        lcmfb.setPackagesToScan("com.ua.codespace.model");

//        lcmfb.getJpaPropertyMap().put(org.hibernate.cfg.Environment.HBM2DDL_AUTO, hbm2dll);

        return lcmfb;
    }

    // todo: use this EMF if you want to configure it with /META-INF/persistence.xml
    /*@Bean("entityManagerFactory")
    public LocalEntityManagerFactoryBean entityManagerFactoryBean() {
        LocalEntityManagerFactoryBean emfb = new LocalEntityManagerFactoryBean();
        emfb.setPersistenceUnitName("BambooPersistenceUnit");
        return emfb;
    }*/

}
