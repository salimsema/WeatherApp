package au.com.weather.controller;

import java.util.ArrayList;
import java.util.Properties;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
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
	private final String DEFAULTACTION = "/*";
	private final String PARAMVALUECITYDROPDOWN = "cityDropdown";
	private final String PROPAPI = "/apidetails.properties";
	private final String DEFAULTCITYCODE = "defaultcitycode";
	
	HelperClass helperClass = new HelperClass();
	
	/**
	 * Method for landing page
	 * @param model {@link Model}
	 * @return view
	 */
	@RequestMapping(value=DEFAULTACTION, method = RequestMethod.GET)
	public String landingPage(ModelMap model) {
		OpenWeatherMap owm;
		ArrayList<City> cityList = helperClass.populateCityDropDown();
		model.addAttribute(CITYLIST, cityList); 
		
		Properties apiDetails = helperClass.loadPropFile(PROPAPI);
		String defaultCityCode = apiDetails.getProperty(DEFAULTCITYCODE);
		owm=helperClass.getWeatherData(defaultCityCode);
		model.addAttribute(OWM, owm);
		return WEATHERAPPVIEW;
	}
	
	/**
	 * Post method to facilitate requests
	 * @param model {@link Model}
	 * @param cityCode
	 * @return view
	 */
	@PostMapping(value=ACTIONHOME)
	public String postMethod(ModelMap model,@RequestParam(value=PARAMVALUECITYDROPDOWN, required=false) Integer cityCode) {
		ArrayList<City> cityList = helperClass.populateCityDropDown();
	    model.addAttribute(CITYLIST, cityList);
		OpenWeatherMap owm;
		owm = helperClass.getWeatherData(cityCode.toString());
		model.addAttribute(OWM, owm);
		return WEATHERAPPVIEW;
	}
	 
	

	

	

}
