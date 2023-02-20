package com.reeinvent.backend.grpc;

import io.grpc.ManagedChannel;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import java.util.concurrent.TimeUnit;

public abstract class AbstractChannel implements DisposableBean, InitializingBean {
    private ManagedChannel channel;

    public AbstractChannel() {
    }

    protected abstract ChannelConfig configure();

    public void destroy() throws Exception {
        this.channel.shutdown();
        this.channel.awaitTermination(1L, TimeUnit.SECONDS);
    }

    public void afterPropertiesSet() throws Exception {
        ChannelConfig channelConfig = this.configure();
        this.channel = ChannelProvider.get(channelConfig.getHost(), channelConfig.getPort());
        this.addShutdownHook();
    }

    public ManagedChannel getChannel() {
        return this.channel;
    }

    private void addShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                try {
                    AbstractChannel.this.destroy();
                } catch (Exception var2) {
                }

            }
        });
    }
}

