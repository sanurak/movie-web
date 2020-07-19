package movie.app.web;

import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import movie.app.web.rest.MovieRestClient;

@SpringBootApplication
public class MovieWebApplication {
	public static void main(String[] args) {
		SpringApplication.run(MovieWebApplication.class, args);
	}

	@Bean
	public MovieRestClient movieRestClient(@Value("${movie.rest.url}") String movieRestUrl)
			throws IllegalArgumentException, URISyntaxException {
		MovieRestClient bookStoreService = new MovieRestClient(movieRestUrl);

		return bookStoreService;
	}
}
