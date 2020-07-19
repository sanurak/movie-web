package movie.app.web.model;

/**
 * 
 * @author Anurak Sirivoravit
 *
 */
public class MovieSearchRequest {
	private String searchBy;
	private String searchString;

	public String getSearchBy() {
		return searchBy;
	}

	public void setSearchBy(String searchBy) {
		this.searchBy = searchBy;
	}

	public String getSearchString() {
		return searchString;
	}

	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}

}
