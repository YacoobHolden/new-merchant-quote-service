package nz.co.smartpay.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
public class DataSourceConfig {

    @Bean
    public DataSource dataSource() {
        System.setProperty("org.hibernate.envers.audit_table_suffix", "_history");

        String jdbcUrl = System.getenv("JDBCURL");
        String jdbcUsername = System.getenv("JDBCUSERNAME");
        String jdbcPassword = System.getenv("JDBCPASSWORD");

        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName("org.postgresql.Driver");
        hikariConfig.setJdbcUrl(jdbcUrl);
        hikariConfig.setUsername(jdbcUsername);
        hikariConfig.setPassword(jdbcPassword);
        hikariConfig.setMaximumPoolSize(5);
        hikariConfig.setConnectionTimeout(30000);
        FlywayMigration.migrate(jdbcUrl, jdbcUsername, jdbcPassword);
        return new HikariDataSource(hikariConfig);
    }
}
