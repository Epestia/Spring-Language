package be.ipam.language;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@EnableAsync
@SpringBootApplication
public class LanguageApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(LanguageApplication.class);

        app.addListeners((ApplicationListener<WebServerInitializedEvent>) event -> {
            Environment env = event.getApplicationContext().getEnvironment();
            int port = event.getWebServer().getPort();
            String contextPath = env.getProperty("server.servlet.context-path", "");
            if (contextPath.equals("/")) {
                contextPath = "";
            }

            String baseUrl = "http://localhost:" + port + contextPath;

            System.out.println("✅ API started at: " + baseUrl);
            System.out.println("📘 Swagger UI: " + baseUrl + "/swagger-ui.html");
            System.out.println("📘 API doc: " + baseUrl + "/apidoc");
            System.out.println("Hello, Language Service!!!");
        });

        app.run(args);
    }

    @Bean(name = "taskExecutor")
    public Executor taskExecutor() {
        return Executors.newVirtualThreadPerTaskExecutor();
    }
}
