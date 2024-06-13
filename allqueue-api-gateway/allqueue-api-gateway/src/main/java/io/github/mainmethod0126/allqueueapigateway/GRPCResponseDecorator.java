package io.github.mainmethod0126.allqueueapigateway;

import java.net.URI;

import org.reactivestreams.Publisher;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.http.server.reactive.ServerHttpResponseDecorator;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

public class GRPCResponseDecorator extends ServerHttpResponseDecorator {

    private final ServerWebExchange exchange;

    public GRPCResponseDecorator(ServerWebExchange exchange) {
        super(exchange.getResponse());
        this.exchange = exchange;
    }

    @Override
    public Mono<Void> writeWith(Publisher<? extends DataBuffer> body) {

        exchange.getResponse().getHeaders().set("Content-Type", "application/json");

        URI requestURI = exchange.getRequest().getURI();
        ManagedChannel channel = createSecuredChannel(requestURI.getHost(), 6565);

        return getDelegate().writeWith(deserializeJSONRequest()
                .map(jsonRequest -> {
                    String firstName = jsonRequest.getFirstName();
                    String lastName = jsonRequest.getLastName();
                    return HelloServiceGrpc.newBlockingStub(channel)
                            .hello(HelloRequest.newBuilder()
                                    .setFirstName(firstName)
                                    .setLastName(lastName)
                                    .build());
                })
                .map(this::serialiseJSONResponse)
                .map(wrapGRPCResponse())
                .cast(DataBuffer.class)
                .last());
    }

}
