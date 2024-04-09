package com.team5.campscore.service;

import java.util.List;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import org.apache.ibatis.exceptions.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.team5.campscore.dao.WeatherDAO;
import com.team5.campscore.model.WeatherDTO;

import lombok.RequiredArgsConstructor;

@Service
public class WeatherDAOImpl implements WeatherDAO {
	@Autowired
	private WeatherDAO dao;
	
	public int insertWeather(WeatherDTO w) {
		int result=1;
	    try {
	        // insert 쿼리 실행
	        result = dao.insertWeather(w);
	        
	    } catch (PersistenceException e) {
	        // MyBatis의 PersistenceException 처리
	    	
	        return 0;
	    }
	    return result;
	}
	
	public List<WeatherDTO> getWeatherList() {
		
		 
		return dao.getWeatherList();
		
	}
	public WeatherDTO getWeather(String region) {

		return dao.getWeather(region);
		
	}
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public int updateWeather(WeatherDTO w) {
    	int result=1;
    	try {
	        // insert 쿼리 실행
    		result= dao.updateWeather(w);
	        
	    } catch (PersistenceException e) {
	        // MyBatis의 PersistenceException 처리
	        return result=0;
	    }
    	
    	return result;
	}
}
