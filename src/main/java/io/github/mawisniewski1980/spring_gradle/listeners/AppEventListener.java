package io.github.mawisniewski1980.spring_gradle.listeners;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.time.LocalDateTime;

@Slf4j
@Component
public class AppEventListener {

    @EventListener
    void on(ApplicationReadyEvent event) {
        log.info("APP on {}", LocalDateTime.now());
    }

    @PreDestroy
    void off() {
        log.info("APP off {}", LocalDateTime.now());
    }
}
