package au.com.weather.data.model;

import java.util.List;

public class OpenWeatherMap {
    private Coord coord;
    private List<Weather> weather;	
    private String base;
    private  Main main;
    private int visibility;
    private Wind wind;
    private Clouds clouds;
    private String dt;
    private Sys sys;
    private int timezone;
    private int id;
    private String name;
    private int cod;
    

    public void toPrint() {
    	System.out.println("Coord=lat:"+coord.getLat() +":lon:"+coord.getLon());
    	for (Weather weather2 : weather) {
			System.out.println("Weather=desc:"+weather2.getDescription()+":icon:"+weather2.getIcon()+":id:"
					+weather2.getId()+":main:"+weather2.getMain());
		}
    	System.out.println("base="+base);
    	System.out.println("main=temp:"+main.getTemp()+":max_t:"+main.getTemp_max()+":min_t:"+main.getTemp_min()
    		+":humidity:"+main.getHumidity()+":pressure:"+main.getPressure());
    	System.out.println("wind=deg:"+wind.getDeg()+":speed:"+wind.getSpeed());
    	System.out.println("clouds="+clouds.getAll());
    	System.out.println("dt="+dt);
    	System.out.println("sys=country:"+sys.getCountry()+":id:"+sys.getId()+":message:"+sys.getMessage()
    			+":sunrise:"+sys.getSunrise()+":sunshine:"+sys.getSunset()+":type:"+sys.getType());
    	System.out.println("id="+id+":timezone="+timezone+":name="+name+":cod="+cod);
    	
    }

    public  Coord getCoord() {
        return coord;
    }

    public  void setCoord(Coord coord) {
        this.coord = coord;
    }


    public  List<Weather> getWeather() {
        return weather;
    }

    public  void setWeather(List<Weather> weather) {
        this.weather = weather;
    }

    public  String getBase() {
        return base;
    }

    public  void setBase(String base) {
        this.base = base;
    }

    public  Main getMain() {
        return main;
    }

    public  void setMain(Main main) {
        this.main = main;
    }

    public int getVisibility() {
		return visibility;
	}

	public void setVisibility(int visibility) {
		this.visibility = visibility;
	}

	public  Wind getWind() {
        return wind;
    }

    public  void setWind(Wind wind) {
        this.wind = wind;
    }

    public  Clouds getClouds() {
        return clouds;
    }

    public  void setClouds(Clouds clouds) {
        this.clouds = clouds;
    }

    public  String getDt() {
        return dt;
    }

    public  void setDt(String dt) {
        this.dt = dt;
    }

    public  Sys getSys() {
        return sys;
    }

    public  void setSys(Sys sys) {
        this.sys = sys;
    }

    public  int getId() {
        return id;
    }

    public  void setId(int id) {
        this.id = id;
    }

    public int getTimezone() {
		return timezone;
	}

	public void setTimezone(int timezone) {
		this.timezone = timezone;
	}

	public  String getName() {
        return name;
    }

    public  void setName(String name) {
        this.name = name;
    }

    public  int getCod() {
        return cod;
    }

    public  void setCod(int cod) {
        this.cod = cod;
    }
    
}
