package com.reeinvent.backend.grpc;

import io.grpc.ClientInterceptor;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class ChannelProvider {
    public ChannelProvider() {
    }

    public static ManagedChannel get(String host, int port) {
        return ManagedChannelBuilder.forAddress(host, port).usePlaintext().defaultLoadBalancingPolicy("round_robin").maxInboundMessageSize(20971520).maxInboundMetadataSize(20971520).intercept(new ClientInterceptor[]{new GrpcClientInterceptor()}).build();
    }
}
