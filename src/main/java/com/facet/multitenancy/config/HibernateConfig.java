package com.facet.multitenancy.config;

import com.facet.multitenancy.TenantConnectionProvider;
import org.hibernate.MultiTenancyStrategy;
import org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl;
import org.hibernate.cfg.Environment;
import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.hibernate.dialect.MySQL57Dialect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class HibernateConfig {
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(JpaVendorAdapter jpaVendorAdapter,
                                                                       DataSource dataSource,
                                                                       TenantConnectionProvider multiTenantConnectionProvider,
                                                                       CurrentTenantIdentifierResolver tenantIdentifierResolver) {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setPackagesToScan("com.facet.*");

        em.setJpaVendorAdapter(jpaVendorAdapter);

        Map<String, Object> jpaProperties = new HashMap<>();
        jpaProperties.put(Environment.MULTI_TENANT, MultiTenancyStrategy.DATABASE);
        jpaProperties.put(Environment.MULTI_TENANT_CONNECTION_PROVIDER, multiTenantConnectionProvider);
        jpaProperties.put(Environment.MULTI_TENANT_IDENTIFIER_RESOLVER, tenantIdentifierResolver);
        jpaProperties.put(Environment.FORMAT_SQL, true);

        jpaProperties.put(Environment.PHYSICAL_NAMING_STRATEGY, PhysicalNamingStrategyStandardImpl.class);
        jpaProperties.put(Environment.DIALECT, MySQL57Dialect.class);

        em.setJpaPropertyMap(jpaProperties);
        return em;
    }
}
