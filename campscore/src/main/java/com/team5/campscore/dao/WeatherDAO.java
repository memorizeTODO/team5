package com.team5.campscore.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.team5.campscore.model.Weather;

@Mapper
public interface WeatherDAO {
	public int insertWeather(Weather w); 
	public int updateWeather(Weather w);
	public List<Weather> getWeather();
}
