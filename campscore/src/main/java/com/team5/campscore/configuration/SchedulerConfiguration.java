package com.team5.campscore.configuration;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class SchedulerConfiguration {
	 //private final DeviceService deviceService;

	    @Scheduled(fixedDelay = 60000)
	    public void run() {
	        //deviceService.synchronize();
	    }
}
