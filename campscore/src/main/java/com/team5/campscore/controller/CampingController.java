package com.team5.campscore.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.ibatis.annotations.Mapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.team5.campscore.model.Camping;
import com.team5.campscore.service.CampingDAOImpl;
import com.team5.campscore.utilities.CampingCategoryExtrator;
import com.team5.campscore.utilities.PlaceIdMapBuilder;

@RestController
@RequestMapping("/")
public class CampingController {
	@Autowired
	CampingDAOImpl campingService;
	
	@Mapper
	@GetMapping(value ="getListByRegion")
	ResponseEntity<Map<String, Map<String, Object>>> getCampingToViewByRegion(@RequestParam Map<String,String> params){
		int page; 
		String region=null;
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
			
		}else {
			//rcode가 없을때 넘겨줄 함수 호출해야함
		}
		int start = (page-1)*10 + 1;
		
		System.out.println("region="+region);
		
		Map<String, Map<String, Object>> campingMaps= new HashMap<String, Map<String, Object>>();
		List<Camping> campingList;
		campingList=campingService.getCampingListByRegion(start,region);
		
	
		for(int i=0;i<campingList.size();i++) {
			Map<String, Object> campingMap = new HashMap<String, Object>();
			
			try {
				BeanUtils.populate(campingMap, BeanUtils.describe(campingList.get(i)));
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(campingMap.toString());
			
			System.out.println(campingMap.get("placeId"));
			
			campingMaps.put("item"+i,campingMap);
		}
		
		return new ResponseEntity<>(campingMaps, HttpStatus.OK);
	}
	
	@RequestMapping("insert/camping")
	public void insertCamping() {
		
		String apiurl = "https://dapi.kakao.com/v2/local/search/keyword.json";

        URLlib urlCon = null;
        String result = null;
       
        PlaceIdMapBuilder pidMapBuilder= new PlaceIdMapBuilder();
        Map<String,String> pidMap = pidMapBuilder.getPlaceIdMap();
        Map<String,String> checkDuplicateId=new HashMap<String,String>();
        
        for(String key:pidMap.keySet()) {
        	boolean isEnd=false;
        	int cnt=1;
	        while(isEnd!=true) {
	        	
	        	
		        try { 
		           Map<String,String> params=new HashMap<String,String>();
		    	   Map<String,String> headers=new HashMap<String,String>();
		    	   params.put("query", pidMap.get(key)+" 캠핑장" );
		    	   params.put("category_group_code", "AD5");
		           headers.put("Authorization", "KakaoAK adacc2024f0537f8eb428ee10db1dc20");
		        	
		           params.put("page", Integer.toString(cnt) );
		           urlCon=new URLlib(apiurl,params,headers); // api 주소, 파라미터(get), 헤더 값을 넣어 httpURLConnection 객체 할당
		      
		           //urlCon.setRequestContentType("json");// 응답받고자하는 콘텐츠 타입 지정
		           urlCon.setRequestMethod("GET");// get방식으로 요청하도록 세팅
		       
		            urlCon.getNetworkConnection();// 요청 실행
		            urlCon.readStreamToString("UTF8"); // 받아온 응답을 문자열로 저장
		            result = urlCon.getResult(); // 응답 문자열을 가져옴
		            
		            
		            
		           
		        } catch(IOException e) {
		            e.printStackTrace();
		        } finally {
		            urlCon.disconnect();
		        }
		        
		        try {
	                JSONObject jsonObject = new JSONObject(result);
	
	                // Example: Accessing specific values
	                
	                JSONArray documents = jsonObject.getJSONArray("documents");
	                JSONObject metaData =  jsonObject.getJSONObject("meta");
	                
	                isEnd=metaData.getBoolean("is_end");
	                System.out.println(isEnd);
	                	
	                CampingCategoryExtrator cce= new CampingCategoryExtrator();
	                Map<String,String> categoryMap;
	                
	                if(documents==null) {
	                	System.out.println("errorType="+jsonObject.getString("errorType"));
	                	continue;
	                }
	                
	                for(int i=0;i<documents.length();i++) {
	                	
	                	
	                	JSONObject item = documents.getJSONObject(i);
	                	
	                	categoryMap=cce.findCampingCategoryData(item.getString("category_name"));
	                	if(categoryMap.get("category2")==null || !categoryMap.get("category2").equals("야영,캠핑장")) {
	                		continue; 
	                	}
	                	
	                			
	                	Camping camping= new Camping();
	                	
	                	camping.setPlaceId(item.getString("id"));
	                	if(checkDuplicateId.get(camping.getPlaceId())!=null) {
	                		continue;
	                	}
	                	camping.setPlaceName(item.getString("place_name"));
	                	camping.setAddressName(item.getString("address_name"));
	                	camping.setRoadAddressName(item.getString("road_address_name"));
	                	camping.setPlaceUrl(item.getString("place_url"));
	                	camping.setPlaceImg("");
	                	System.out.println(item.getDouble("x"));
	                	camping.setPlaceLong(item.getDouble("x"));
	                	System.out.println(item.getDouble("y"));
	                	camping.setPlaceLat(item.getDouble("y"));
	                	camping.setPlaceCategoryDetail(categoryMap.get("category3"));
	                	
	                	
	                	campingService.insertCamping(camping);
	                	
	                	/*System.out.println("camp_id: " + camp_id);
	    				System.out.println("camp_name: " +camp_name); 
	    				System.out.println("camp_address name: " +camp_address name); 
	    				System.out.println("camp_ road address name: " +camp_ road address name); 
	    				System.out.println("camp_url: " +camp_url); 
	    				System.out.println("camp_imgl: " +camp_img); 
	    				System.out.println("camp_lat: " +camp_lat); 
	    				System.out.println("camp_long: " +camp_long); */
	                	checkDuplicateId.put(item.getString("id"), item.getString("id"));
	                }
	                // You can similarly access other values as needed
	                Thread.sleep(1000);//1초간 휴식
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
		        cnt++;
		        
		        if (isEnd==true||cnt==46) {
		            break;
		        }
	        }
        }
	}
}
