package movie.app.web.model;

/**
 * 
 * @author Anurak Sirivoravit
 *
 */
public class MovieResponse {
	private String title;
	private String genres;

	public MovieResponse() {

	}

	public MovieResponse(String title, String genres) {
		this.title = title;
		this.genres = genres;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getGenres() {
		return genres;
	}

	public void setGenres(String genres) {
		this.genres = genres;
	}

}
