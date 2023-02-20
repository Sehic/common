package com.reeinvent.backend;

import io.grpc.BindableService;
import io.grpc.Server;
import io.grpc.netty.NettyServerBuilder;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultServer {
    Logger LOGGER = LoggerFactory.getLogger(DefaultServer.class);
    private final List<BindableService> services;
    private final int port;
    private Server server;

    public DefaultServer(List<BindableService> services, int port) {
        this.services = services;
        this.port = port;
    }

    public void start() throws IOException {
        NettyServerBuilder serverBuilder = NettyServerBuilder.forPort(this.port);

        if (services != null && !services.isEmpty()) {
            services
                    .forEach(serverBuilder::addService);
        }

        serverBuilder.maxInboundMessageSize(20971520);
        serverBuilder.maxInboundMetadataSize(20971520);
        serverBuilder.permitKeepAliveWithoutCalls(true);
        serverBuilder.permitKeepAliveTime(30L, TimeUnit.SECONDS);
        serverBuilder.maxConnectionAgeGrace(Long.MAX_VALUE, TimeUnit.MILLISECONDS);
        serverBuilder.maxConnectionAge(Long.MAX_VALUE, TimeUnit.MILLISECONDS);
        serverBuilder.maxConnectionIdle(Long.MAX_VALUE, TimeUnit.MILLISECONDS);
        serverBuilder.keepAliveTime(3L, TimeUnit.HOURS);
        serverBuilder.keepAliveTimeout(10L, TimeUnit.SECONDS);
        this.server = serverBuilder.build().start();
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            DefaultServer.this.LOGGER.info("Shutting down server...");
            DefaultServer.this.stop();
            DefaultServer.this.LOGGER.info("Server shut down!");
        }));
    }

    public void stop() {
        if (this.server != null) {
            this.server.shutdown();
        }

    }

    public void blockUntilShutdown() throws InterruptedException {
        if (this.server != null) {
            this.server.awaitTermination();
        }

    }
}


