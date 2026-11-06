package org.aptech.sis;

import java.sql.Connection;
import java.sql.DriverManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SisApplication {

    private static final Logger log = LoggerFactory.getLogger(SisApplication.class);

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(SisApplication.class);
        if (!isMySqlAvailable()) {
            log.warn("MySQL (localhost:3306/sis) chua san sang -> Tu dong kich hoat profile 'h2' (MySQL compatibility mode) de chay thu nghiem tren cong 8080.");
            app.setAdditionalProfiles("h2");
        }
        app.run(args);
    }

    private static boolean isMySqlAvailable() {
        String activeProfiles = System.getProperty("spring.profiles.active", System.getenv().getOrDefault("SPRING_PROFILES_ACTIVE", ""));
        if (activeProfiles.contains("test") || activeProfiles.contains("h2")) {
            return false;
        }
        String url = System.getenv().getOrDefault(
                "DB_URL",
                "jdbc:mysql://localhost:3306/sis?createDatabaseIfNotExist=true&useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Ho_Chi_Minh&allowPublicKeyRetrieval=true&useSSL=false&connectTimeout=1500"
        );
        String username = System.getenv().getOrDefault("DB_USERNAME", "root");
        String password = System.getenv().getOrDefault("DB_PASSWORD", "");
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            return conn != null && !conn.isClosed();
        } catch (Exception ex) {
            return false;
        }
    }
}
