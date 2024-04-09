package com.team5.campscore.schedules;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


import com.team5.campscore.tools.WeatherTools;

@Component
public class WeatherSchedule {
	@Autowired
	private WeatherTools wTools;

	@Scheduled(cron = "0 28 20 * * *")
	public void autoUpdateScheduleAt6() {
		wTools.updateWeather("202404060600");
		wTools.updateWeatherMoveUpDate();
		wTools.updateWeather("202404070600");
		wTools.updateWeatherMoveUpDate();
		wTools.updateWeather("202404080600");
		wTools.updateWeatherMoveUpDate();
		wTools.updateWeather("202404090600");
	}
	
	@Scheduled(cron = "0 0 0 * * *")
	public void autoUpdateScheduleAt0() {	
		wTools.updateWeatherMoveUpDate();
			
	}
	
}
