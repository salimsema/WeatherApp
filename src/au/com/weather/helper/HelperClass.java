package au.com.weather.helper;

import java.io.IOException;
import java.text.MessageFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;
import java.util.Set;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import au.com.weather.controller.WeatherController;
import au.com.weather.data.model.City;
import au.com.weather.data.model.Main;
import au.com.weather.data.model.OpenWeatherMap;
import au.com.weather.data.model.Wind;

public class HelperClass {
	
	private final String PROPFILE = "/cities.properties";
	private final String PROPAPI = "/apidetails.properties";
	private final String APIURL = "apiurl";
	private final String UNITS = "units";
	private final String APPID = "appid";
	private final String METRIC = "metric";
	private final String DATETIMEPATTERN = "EEEE h:mm a";
	private final String TIMEZONE = "Australia/Sydney";
	private final String SPEEDCONSTANT =  " km/h";
	private final String TEMPCONSTANT = " \u00B0C";
			
	
	public Properties loadPropFile(String propFile) {
		Properties prop = new Properties();
		try {
			prop.load(WeatherController.class.getClassLoader().getResourceAsStream(propFile));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}
	
	public ArrayList<City> populateCityDropDown() {
		Properties properties = loadPropFile(PROPFILE);
	    Set<String> citiesSet = properties.stringPropertyNames();
		ArrayList<City> cityList = new ArrayList<City>();
		for (String cityFromProp : citiesSet) {
	    	  City city = new City();
	    	  city.setCityName(cityFromProp);
	    	  city.setCityCode(properties.getProperty(cityFromProp));
	    	  cityList.add(city);
		}
		return cityList;
	}
	
	public OpenWeatherMap getRespViaSpring(String cityCode) {
		RestTemplate restTemplate = new RestTemplate();
		 
		Properties apiDetails = loadPropFile(PROPAPI);
		String apiURL = apiDetails.getProperty(APIURL);
		String units = apiDetails.getProperty(UNITS);
		String appID = apiDetails.getProperty(APPID);
		String url = MessageFormat.format(apiURL, cityCode, units, appID);
		 
		 
		MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
		mappingJackson2HttpMessageConverter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON, MediaType.APPLICATION_OCTET_STREAM));
		restTemplate.getMessageConverters().add(mappingJackson2HttpMessageConverter);
		 
		OpenWeatherMap owm = restTemplate.getForObject(url, OpenWeatherMap.class);
		 
		owm = transformData(owm,units);
		 
		if(null != owm)
			owm.toPrint();
		 
		return owm;
	}
	 
	 public String getAESTDateAndTime(long unixTime) {
		 final DateTimeFormatter formatter = 
		    	    DateTimeFormatter.ofPattern(DATETIMEPATTERN);

		    	final String formattedDt = Instant.ofEpochSecond(unixTime)
		    	        .atZone(ZoneId.of(TIMEZONE))
		    	        .format(formatter);
		    	return formattedDt;
	 }
	 
	 public OpenWeatherMap transformData(OpenWeatherMap owm, String unit) {
		 
		 String formattedDt = getAESTDateAndTime(Long.parseLong(owm.getDt()));
		 owm.setDt(formattedDt);
		 
		 if(METRIC.equalsIgnoreCase(unit)) {
			 Wind wind = owm.getWind();
			 String windSpeed = owm.getWind().getSpeed() + SPEEDCONSTANT;
			 wind.setSpeed(windSpeed);
			 owm.setWind(wind); 
			 
			 Main main = owm.getMain();
			 String temp = main.getTemp()+TEMPCONSTANT;
			 main.setTemp(temp);
			 owm.setMain(main);
			 
		 }else {
			 //TODO Future scope
		 }
		 return owm;
	 }

}
