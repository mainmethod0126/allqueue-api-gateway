package io.github.mainmethod0126.allqueueapigateway.user;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import reactor.core.publisher.Mono;

@RequestMapping
public class UserController {

    @GetMapping("/user/info")
    public Mono<String> getCurrentUserInfo(
            @AuthenticationPrincipal OAuth2AuthenticationToken authenticationToken) {

        return Mono.just("Hello, " + authenticationToken.getPrincipal().getName());
    }

}
