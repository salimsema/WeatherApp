package au.com.weather.controller;

import java.util.ArrayList;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import au.com.weather.data.model.City;
import au.com.weather.data.model.OpenWeatherMap;
import au.com.weather.helper.HelperClass;

@Controller
public class WeatherController {
	
	private final String CITYLIST = "cityList";
	private final String WEATHERAPPVIEW = "weatherAppView";
	private final String OWM = "owm";
	private final String ACTIONHOME = "/home";
	private final String DEFAULTACTION = "/";
	private final String PARAMVALUECITYDROPDOWN = "cityDropdown";
	
	HelperClass HelperClass = new HelperClass();
	
	@RequestMapping(value=DEFAULTACTION, method = RequestMethod.GET)
	public String landingPage(ModelMap model) {
		OpenWeatherMap owm;
		ArrayList<City> cityList = HelperClass.populateCityDropDown();
		model.addAttribute(CITYLIST, cityList); 
		owm=HelperClass.getRespViaSpring("2147714");
		model.addAttribute(OWM, owm);
		return WEATHERAPPVIEW;
	}
	
	@RequestMapping(value=ACTIONHOME, method = RequestMethod.POST)
	public String postMethod(ModelMap model,@RequestParam(value=PARAMVALUECITYDROPDOWN, required=false) Integer cityCode) {
		ArrayList<City> cityList = HelperClass.populateCityDropDown();
	    model.addAttribute(CITYLIST, cityList);
		OpenWeatherMap owm;
		owm = HelperClass.getRespViaSpring(cityCode.toString());
		model.addAttribute(OWM, owm);
		return WEATHERAPPVIEW;
	}
	 
	

	

	

}
