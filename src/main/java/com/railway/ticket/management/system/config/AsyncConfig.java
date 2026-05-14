package com.railway.ticket.management.system.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;


@Configuration // This is a settings file
@EnableAsync    // Allow background tasks
public class AsyncConfig {

    @Bean(name = "railwayExecutor") // Creates a Worker Machine
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();

        executor.setCorePoolSize(2);   // 2 workers are always ready.
        executor.setMaxPoolSize(10);  // If busy, hire up to 10 workers.
        executor.setQueueCapacity(1000); // 100 tasks can wait in line.
        executor.setThreadNamePrefix("Railway Counter - "); // Name of the workers.

        executor.initialize(); // Start the machine.
        return executor;
    }
}

