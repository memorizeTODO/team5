package com.team5.campscore.service;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team5.campscore.dao.WeatherDAO;
import com.team5.campscore.model.Weather;

import lombok.RequiredArgsConstructor;

@Service
public class WeatherDAOImpl implements WeatherDAO {
	@Autowired
	WeatherDAO dao;
	
	public int insertWeather(Weather w) {
		
		dao.insertWeather(w);
		
		return 1;
	}
	
	public List<Weather> getWeather() {
		
		 
		return dao.getWeather();
		
	}
	public int updateWeather(Weather w) {
		dao.updateWeather(w);
	
		return 1;
	}
}
