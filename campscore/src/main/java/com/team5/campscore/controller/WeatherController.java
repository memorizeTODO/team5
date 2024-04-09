package com.team5.campscore.controller;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.team5.campscore.model.WeatherDTO;
import com.team5.campscore.service.WeatherDAOImpl;
import com.team5.campscore.tools.WeatherTools;
import com.team5.campscore.utilities.WeatherDataExtractor;

@RestController
@RequestMapping("/")
public class WeatherController {
	@Autowired
	WeatherDAOImpl weatherService;
    
    @RequestMapping("get/weather")
    public Map<String,Map<String,Object>> getWeatherToView(){
    	Map<String,Map<String,Object>> weatherMaps = new HashMap<String,Map<String,Object>>();

    	Map<String,Object> weatherMap= new HashMap<String,Object>(); 
    	List<WeatherDTO> wList = weatherService.getWeather();

    	for(int i=0;i<wList.size();i++ ) {
    		try {
				BeanUtils.populate(weatherMap, BeanUtils.describe(wList.get(i)));
				System.out.println(weatherMap.toString());

				weatherMaps.put("item"+i, weatherMap);
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				continue;
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				continue;
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				continue;
			}

    	}
    	return weatherMaps;
    }
}
