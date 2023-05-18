package ch.cristiano.demo.externaltask;

import org.camunda.bpm.client.interceptor.ClientRequestInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;

@Configuration
public class AadOAuth2RequestInterceptorConfiguration {

    @Autowired
    OAuth2AuthorizedClientManager oAuth2AuthorizedClientManager;

    private static final Authentication ANONYMOUS_AUTHENTICATION = new AnonymousAuthenticationToken("anonymous",
            "anonymousUser", AuthorityUtils.createAuthorityList("ROLE_ANONYMOUS"));

    @Bean
    public ClientRequestInterceptor interceptor() {
        return context -> {

            Authentication principal = SecurityContextHolder.getContext().getAuthentication();

            if (principal == null) {
                principal = ANONYMOUS_AUTHENTICATION;
            }

            OAuth2AuthorizeRequest oAuth2AuthorizeRequest = OAuth2AuthorizeRequest
                    .withClientRegistrationId("camundaLocalEngine").principal(principal).build();
            OAuth2AuthorizedClient camundaLocalEngineClient = oAuth2AuthorizedClientManager
                    .authorize(oAuth2AuthorizeRequest);

            context.addHeader("Authorization", "Bearer " + camundaLocalEngineClient.getAccessToken().getTokenValue());
        };
    }
}
