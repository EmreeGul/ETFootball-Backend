package de.htw.ETFootball.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;


@Service
public class FootballDataService {

    private final WebClient webClient;

    public FootballDataService(@Value("${football.api.base-url}") String baseUrl,
                               @Value("${football.api.token}") String apiToken) {
        this.webClient = WebClient.builder()
                .baseUrl(baseUrl)
                .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + apiToken)
                .build();
    }

    public Mono<String> getTeamData(String teamId) {
        return webClient.get()
                .uri("/teams/{teamId}", teamId)
                .retrieve()
                .onStatus(httpStatus -> httpStatus.isError(), clientResponse ->
                        Mono.error(new WebClientResponseException(
                                clientResponse.statusCode().value(),
                                clientResponse.statusCode().toString(),
                                clientResponse.headers().asHttpHeaders(),
                                null,
                                null)))
                .bodyToMono(String.class);
    }
}
