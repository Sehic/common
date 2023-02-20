package com.reeinvent.backend;

import io.grpc.BindableService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

public abstract class BaseApplication {
    @Value("${server.port}")
    private int port;
    @Autowired(
            required = false
    )
    private List<BindableService> services;

    public BaseApplication() {
    }

    @Bean
    public CommandLineRunner run() {
        return (args) -> {
            DefaultServer server = new DefaultServer(this.services, this.port);
            server.start();
            server.blockUntilShutdown();
        };
    }
}
