package au.com.weather;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;
import java.util.Set;

import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import au.com.weather.data.model.City;
import au.com.weather.data.model.OpenWeatherMap;

@Controller
@RequestMapping("/")
public class WeatherController {
	@RequestMapping(method = RequestMethod.GET)
	   public String printHello(ModelMap model) {
	      Properties properties = loadPropFile();
	      Set<String> citiesSet = properties.stringPropertyNames();
	      ArrayList<City> cityList = new ArrayList<City>();
	      
	      for (String cityFromProp : citiesSet) {
	    	  City city = new City();
	    	  city.setCityName(cityFromProp);
	    	  city.setCityCode(properties.getProperty(cityFromProp));
	    	  cityList.add(city);
		}
	      model.addAttribute("cityList", cityList);
	      
	      getRespViaSpring();
	      return "hello";
	}
	
	private Properties loadPropFile() {
		Properties prop = new Properties();
		try {
			prop.load(WeatherController.class.getClassLoader().getResourceAsStream("/cities.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}	
	
	 public void getRespViaSpring() {
		 RestTemplate restTemplate = new RestTemplate();
		  
		 String url = "http://api.openweathermap.org/data/2.5/weather?id=2158177&units=metric&APPID=141653b2b41049eeb0a1bda35eae3762";
		 
		MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
		mappingJackson2HttpMessageConverter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON, MediaType.APPLICATION_OCTET_STREAM));
		restTemplate.getMessageConverters().add(mappingJackson2HttpMessageConverter);
		 
		 OpenWeatherMap owm = restTemplate.getForObject(url, OpenWeatherMap.class);
		 if(null != owm)
			 owm.toPrint();
     }
}
