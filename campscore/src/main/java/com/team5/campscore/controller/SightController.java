package com.team5.campscore.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.team5.campscore.model.SightDTO;


@RestController
public class SightController {
	
	//@Autowired
	
	@GetMapping(value ="get/sightlist")
	ResponseEntity<Map<String, Map<String, Object>>> getCampingToViewByRegion(@RequestParam Map<String,String> params){
		int page; 
		String region= "";
		String sortType = "place_name"; 
		String order = "asc";
		if(params.get("page")==null) {
			page=1;
		}else{
			try {
				page=Integer.parseInt(params.get("page"));
				
			}
			catch (NumberFormatException e) {
				page=1;
			}
		}
		if(params.get("region")!=null) {
			region=params.get("region");
		}
		if(params.get("sort")!=null) {
			switch(params.get("sort")) {
				case "place_name": case "weather_score":
					sortType = params.get("sort_type");
			}
			
		}
		
		if(params.get("order")!=null) {
			switch(params.get("order")) {
				case "asc":	case "desc":
					order=params.get("order");
			}
			
		}
		
		
		
		
		int start = (page-1)*10 + 1;
		
		System.out.println("region="+region);
		
		Map<String, Map<String, Object>> campingMaps= new HashMap<String, Map<String, Object>>();
		List<SightDTO> SightList;
		//campingList=campingService.getCampingListByRegion(start,region,sortType,order);
		
		/*
		 * for(int i=0;i<campingList.size();i++) { Map<String, Object> campingMap = new
		 * HashMap<String, Object>();
		 * 
		 * try { BeanUtils.populate(campingMap, BeanUtils.describe(campingList.get(i)));
		 * } catch (IllegalAccessException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } catch (InvocationTargetException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); } catch
		 * (NoSuchMethodException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } System.out.println(campingMap.toString());
		 * 
		 * System.out.println(campingMap.get("placeID"));
		 * 
		 * campingMaps.put("item"+i,campingMap); }
		 */
		
		return new ResponseEntity<>(campingMaps, HttpStatus.OK);
	}
}
