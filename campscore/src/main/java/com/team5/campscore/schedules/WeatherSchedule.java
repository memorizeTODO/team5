package com.team5.campscore.schedules;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.team5.campscore.service.WeatherDAOImpl;
import com.team5.campscore.tools.WeatherTools;

@Component
public class WeatherSchedule {

	@Scheduled(cron = "0 1/10 6 * * *")
	public void autoUpdateScheduleAt6() {
		WeatherTools wTools = new WeatherTools();
		wTools.updateWeather();
	}
	
	@Scheduled(cron = "0 0 0 * * *")
	public void autoUpdateScheduleAt0() {
		int result=0;
		while(result==0) {
			WeatherTools wTools = new WeatherTools();
			result=wTools.updateWeatherMoveUpDate();
			if(result!=0) {
				break;
			}
		}
	}
	
}
