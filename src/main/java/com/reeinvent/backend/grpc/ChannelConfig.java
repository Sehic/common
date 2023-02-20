package com.reeinvent.backend.grpc;

public class ChannelConfig {
    private String host;
    private int port;
    private boolean secured;
    private boolean production;

    public ChannelConfig() {
    }

    public boolean isSecured() {
        return this.secured;
    }

    public void setSecured(boolean secured) {
        this.secured = secured;
    }

    public String getHost() {
        return this.host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return this.port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public boolean isProduction() {
        return this.production;
    }

    public void setProduction(boolean production) {
        this.production = production;
    }
}
