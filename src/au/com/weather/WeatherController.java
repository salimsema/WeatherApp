package au.com.weather;

import java.util.ArrayList;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import au.com.weather.data.model.City;
import au.com.weather.data.model.OpenWeatherMap;

@Controller
@RequestMapping(value="/", method = RequestMethod.GET)
public class WeatherController {
	
	HelperClass HelperClass = new HelperClass();
	
	@RequestMapping(method = RequestMethod.GET)
	   public String landingPage(ModelMap model) {
		OpenWeatherMap owm;
	    ArrayList<City> cityList = HelperClass.populateCityDropDown();
	    model.addAttribute("cityList", cityList);   
	      owm=HelperClass.getRespViaSpring("2147714");
	      model.addAttribute("owm", owm);
	      return "weatherAppView";
	}
	
	@RequestMapping(value="/home", method = RequestMethod.POST)
	public String postMethod(ModelMap model,@RequestParam(value="cityDropdown", required=false) Integer cityCode) {
		ArrayList<City> cityList = HelperClass.populateCityDropDown();
	    model.addAttribute("cityList", cityList);
		OpenWeatherMap owm;
		owm = HelperClass.getRespViaSpring(cityCode.toString());
		model.addAttribute("owm", owm);
		return "weatherAppView";
	}
	 
	

	

	

}
