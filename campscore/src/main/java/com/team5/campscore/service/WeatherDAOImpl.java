package com.team5.campscore.service;

import java.util.List;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import org.apache.ibatis.exceptions.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team5.campscore.dao.WeatherDAO;
import com.team5.campscore.model.WeatherDTO;

import lombok.RequiredArgsConstructor;

@Service
public class WeatherDAOImpl implements WeatherDAO {
	@Autowired
	WeatherDAO dao;
	
	public int insertWeather(WeatherDTO w) {
	    try {
	        // insert 쿼리 실행
	        int result = dao.insertWeather(w);
	        return result;
	    } catch (PersistenceException e) {
	        // MyBatis의 PersistenceException 처리
	        return updateWeather(w);
	    }
	}
	
	public List<WeatherDTO> getWeather() {
		
		 
		return dao.getWeather();
		
	}
	public int updateWeather(WeatherDTO w) {
	
		return dao.updateWeather(w);
	}
}
