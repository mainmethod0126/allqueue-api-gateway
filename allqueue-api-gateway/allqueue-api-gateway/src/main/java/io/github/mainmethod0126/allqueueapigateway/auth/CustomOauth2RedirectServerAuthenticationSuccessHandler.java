package io.github.mainmethod0126.allqueueapigateway.auth;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.token.TokenService;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.security.web.server.authentication.RedirectServerAuthenticationSuccessHandler;

import reactor.core.publisher.Mono;

public class CustomOauth2RedirectServerAuthenticationSuccessHandler extends RedirectServerAuthenticationSuccessHandler {

    private final TokenService tokenService; // 토큰 생성을 위한 서비스

    public CustomRedirectAuthenticationSuccessHandler(String redirectUrl, TokenService tokenService) {
        super(redirectUrl); // 부모 클래스의 생성자 호출로 리다이렉트 URL 설정
        this.tokenService = tokenService;
    }

    @Override
    public Mono<Void> onAuthenticationSuccess(WebFilterExchange webFilterExchange, Authentication authentication) {
        // TODO Auto-generated method stub
        return super.onAuthenticationSuccess(webFilterExchange, authentication);
    }
}
