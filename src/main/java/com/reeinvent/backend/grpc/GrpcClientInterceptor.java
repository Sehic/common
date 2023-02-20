package com.reeinvent.backend.grpc;

import io.grpc.*;
import org.springframework.stereotype.Component;

@Component
public class GrpcClientInterceptor implements ClientInterceptor {
    public GrpcClientInterceptor() {
    }

    public <ReqT, RespT> ClientCall<ReqT, RespT> interceptCall(MethodDescriptor<ReqT, RespT> method, CallOptions callOptions, Channel next) {
        return new ForwardingClientCall.SimpleForwardingClientCall<ReqT, RespT>(next.newCall(method, callOptions)) {
            public void start(Listener<RespT> responseListener, Metadata headers) {
                super.start(responseListener, headers);
            }
        };
    }
}

