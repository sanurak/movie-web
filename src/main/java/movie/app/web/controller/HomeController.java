package movie.app.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import movie.app.web.Response;
import movie.app.web.Status;
import movie.app.web.model.MovieResponse;
import movie.app.web.model.MovieSearchRequest;
import movie.app.web.rest.MovieRestClient;

/**
 * 
 * @author Anurak Sirivoravit
 *
 */
@Controller
public class HomeController {
	@Autowired
	private MovieRestClient movieRestClient;

	/**
	 * Mapping for home path
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping("/")
	public String home(Model model) {
		MovieSearchRequest movieSearchRequest = new MovieSearchRequest();

		model.addAttribute("movieSearchRequest", movieSearchRequest);
		return "home";
	}

	/**
	 * mapping when click on buy button
	 * 
	 * @param buyForm
	 * @param sessionMap
	 * @return
	 */
	@PostMapping("/")
	public ModelAndView searh(MovieSearchRequest movieSearchRequest) {
		ModelAndView modelAndView = new ModelAndView("home");

		Response<List<MovieResponse>> moveieResponse = movieRestClient.searchMovie(movieSearchRequest);

		modelAndView.addObject("movieSearchRequest", movieSearchRequest);

		if (Status.SUCCESS.equalsIgnoreCase(moveieResponse.getStatus())) {
			// for show detail on page
			modelAndView.addObject("movieList", moveieResponse.getResult());
			modelAndView.addObject("recordFound", "Found " + moveieResponse.getResult().size() + " Record(s)");
		} else {
			// if book not found show error
			modelAndView.addObject("error", moveieResponse.getMessage());
		}

		return modelAndView;

	}
}
