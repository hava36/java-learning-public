package main.config;

import main.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
public class TransactionManagersConfig {

    @Autowired
    EntityManagerFactory emf;

    @Autowired
    private DataSource dataSource;

    @Bean(name = "transactionManager")
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager tm =
                new JpaTransactionManager();
        tm.setEntityManagerFactory(emf);
        tm.setDataSource(dataSource);
        return tm;
    }

}
