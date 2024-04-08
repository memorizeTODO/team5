package com.team5.campscore.schedules;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.team5.campscore.service.WeatherDAOImpl;
import com.team5.campscore.tools.WeatherTools;

@Component
public class WeatherSchedule {
	@Autowired
	WeatherDAOImpl weatherService;

	@Scheduled(cron = "0 1/10 6 * * *")
	public void updateWeatherScheduleAt6() {
		WeatherTools wTools = new WeatherTools();
		//wTools
		//updateWeatherAPItoDB()
	}
	
}
