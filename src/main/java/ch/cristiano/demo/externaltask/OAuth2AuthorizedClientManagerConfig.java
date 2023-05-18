package ch.cristiano.demo.externaltask;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.AuthorizedClientServiceOAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientProvider;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientProviderBuilder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;

@Configuration
public class OAuth2AuthorizedClientManagerConfig {

        @Bean
        public OAuth2AuthorizedClientManager oAuthAuthorizedClientManager(
                        ClientRegistrationRepository clientRegistrationRepository,
                        OAuth2AuthorizedClientService oAuth2AuthorizedClientService) {

                OAuth2AuthorizedClientProvider oAuth2AuthorizedClientProvider = OAuth2AuthorizedClientProviderBuilder
                                .builder()
                                .clientCredentials()
                                .build();

                AuthorizedClientServiceOAuth2AuthorizedClientManager oAuth2AuthorizedClientManager = new AuthorizedClientServiceOAuth2AuthorizedClientManager(
                                clientRegistrationRepository, oAuth2AuthorizedClientService);
                oAuth2AuthorizedClientManager.setAuthorizedClientProvider(oAuth2AuthorizedClientProvider);

                return oAuth2AuthorizedClientManager;
        }
}
