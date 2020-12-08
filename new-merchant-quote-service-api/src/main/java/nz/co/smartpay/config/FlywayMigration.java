package nz.co.smartpay.config;

import org.flywaydb.core.Flyway;

public class FlywayMigration {

    private static boolean migrationHasBeenExecuted;

    public static void migrate(String jdbcUrl, String jdbcUsername, String jdbcPassword) {
        if (migrationHasBeenExecuted) {
            return;
        }
        Flyway flyway = Flyway
                .configure()
                .dataSource(jdbcUrl, jdbcUsername, jdbcPassword)
                .table("schema_version")
                .schemas("quote")
                .load();
        flyway.repair();
        flyway.migrate();
        migrationHasBeenExecuted = true;
    }
}
