package tech.talci.notificationservice;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import tech.talci.notificationservice.service.EmailSenderService;

import java.util.function.Consumer;

@SpringBootApplication
@RequiredArgsConstructor
public class NotificationServiceApplication {

    private final EmailSenderService emailSenderService;

    public static void main(String[] args) {
        SpringApplication.run(NotificationServiceApplication.class, args);
    }

    @Bean
    public Consumer<String> notificationEventSupplier() {
        return this.emailSenderService::sendEmail;
    }
}
