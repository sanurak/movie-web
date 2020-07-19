package movie.app.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpUriRequest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import movie.app.web.Response;
import movie.app.web.model.MovieResponse;
import movie.app.web.model.MovieSearchRequest;

public class MovieRestClient {
	private static ParameterizedTypeReference<Response<List<MovieResponse>>> typeRefList = new ParameterizedTypeReference<Response<List<MovieResponse>>>() {
	};

	private RestTemplate restTemplate = new RestTemplate();

	private URI movieSearchUri = null;

	public MovieRestClient(String restUrl) throws URISyntaxException {
		movieSearchUri = new URI(restUrl);

		this.restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestWithBodyFactory());
	}

	public Response<List<MovieResponse>> searchMovie(MovieSearchRequest movieSearchRequest) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

		HttpEntity<MovieSearchRequest> request = new HttpEntity<MovieSearchRequest>(movieSearchRequest, headers);
		ResponseEntity<Response<List<MovieResponse>>> resultResponse = this.restTemplate.exchange(movieSearchUri,
				HttpMethod.GET, request, typeRefList);

		if (resultResponse.getStatusCode() == HttpStatus.OK) {
			Response<List<MovieResponse>> movieResponse = (Response<List<MovieResponse>>) resultResponse.getBody();

			return movieResponse;

		} else {
			return Response.createError(resultResponse.getStatusCode().toString());
		}
	}

	private static final class HttpComponentsClientHttpRequestWithBodyFactory
			extends HttpComponentsClientHttpRequestFactory {
		@Override
		protected HttpUriRequest createHttpUriRequest(HttpMethod httpMethod, URI uri) {
			if (httpMethod == HttpMethod.GET) {
				return new HttpGetRequestWithEntity(uri);
			}
			return super.createHttpUriRequest(httpMethod, uri);
		}
	}

	private static final class HttpGetRequestWithEntity extends HttpEntityEnclosingRequestBase {
		public HttpGetRequestWithEntity(final URI uri) {
			super.setURI(uri);
		}

		@Override
		public String getMethod() {
			return HttpMethod.GET.name();
		}
	}
}
