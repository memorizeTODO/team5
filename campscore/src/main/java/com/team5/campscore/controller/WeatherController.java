package com.team5.campscore.controller;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.team5.campscore.model.Weather;
import com.team5.campscore.service.WeatherDAOImpl;
import com.team5.campscore.utilities.WeatherDataExtractor;

@RestController
@RequestMapping("/")
public class WeatherController {
	@Autowired
	WeatherDAOImpl weatherService;
	
	private Map<String,String>  getWeatherWC(String rcode) {
    	
    	System.out.println(rcode);
        // 변수 설정
    	//Map<String,Map<String,Object>> formattedDataMaps = new HashMap<String,Map<String,Object>>(); 
    	Map<String,String> formattedDataMap = new HashMap<String,String>(); // db로 보낼 데이터맵
    	formattedDataMap.put("rcode",rcode);
    	
    	//***날짜가져오는 알고리즘 -> 함수화 필요
    	Date date =new Date();
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
    	SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMddHH");
    	String tmfc="";
    	Calendar c = Calendar.getInstance();
    	// -4
    	if (Integer.parseInt(sdf2.format(date)) >= Integer.parseInt(sdf.format(date)+"06")) {
    		Calendar.getInstance();
    		tmfc = sdf.format(date)+"06";
    	}else {
        	Calendar.getInstance();
        	c.add(c.DATE, -1);
        	tmfc = sdf.format(c.getTime())+"06";
    	}

    	
    	//오늘 새벽 6시 자료를
    	
    	
    	Map<String,String> params=new HashMap<String,String>();
    	Map<String,String> headers=new HashMap<String,String>();// get방식으로 요청 보낼 파라미터 모음
    	//
    	
    	String apiurl = "https://apihub.kma.go.kr/api/typ01/url/fct_afs_wl.php";
        params.put("authKey","zZ1CFjy9RvadQhY8vTb2fw");// 본인 서비스 키 입력
        
        params.put("reg", rcode );
        params.put("mode","0" );
        params.put("disp", "0" );
        params.put("tmfc",tmfc );
        headers.put("Connection", "keep-alive");
        
       
        URLlib urlCon = null;
        String result = null;
        boolean isSuccess=false;
	
        try {        
        	urlCon=new URLlib(apiurl,params); // api 주소, 파라미터(get), 헤더 값을 넣어 httpURLConnection 객체 할당
      
        	//urlCon.setRequestContentType("json");// 응답받고자하는 콘텐츠 타입 지정
        	urlCon.setRequestMethod("GET");// get방식으로 요청하도록 세팅
        	
        	
        	isSuccess=urlCon.getNetworkConnection();// 요청 실행
        	
        	if(isSuccess==false) {
        		return null;
        	}
       
            urlCon.readStreamToString("EUC-KR"); // 받아온 응답을 문자열로 저장
            result = urlCon.getResult(); // 응답 문자열을 가져옴
           
        } catch(IOException e) {
            e.printStackTrace();
        } finally {
            urlCon.disconnect();
        }

          
        String filePath = "c:\\Temp\\weatherWC.json";
        
        
		
		  try { FileWriter fileWriter = new FileWriter(filePath);
		  		fileWriter.write(result);
		  
		  		fileWriter.close(); 
		  		} catch (IOException e) { 
		  			e.printStackTrace(); 
		  		}

        try {
        	WeatherDataExtractor w= new WeatherDataExtractor();
        	Map<String,String>dataMapAM= new HashMap<String,String>();
        	Map<String,String>dataMapPM= new HashMap<String,String>();
        	String am;
        	String pm;
        	
        	String[] lines = w.findWCData(result);
        	if (lines.length==0) {
        		return null;
        	}
        	
        	int cntAM = 3;
        	int cntPM = 3;
        	for(int i = 0; i<=10;i++) {
        		if(i%2==0) {
        			dataMapAM.put("wcAM"+cntAM, w.splitLineWC(lines[cntAM]).get("wc"));
        			dataMapAM.put("wcdAM"+cntAM, w.splitLineWC(lines[cntAM]).get("wcd"));
        			dataMapAM.put("rpAM"+cntAM, w.splitLineWC(lines[cntAM]).get("rp"));
        			cntAM++;
        		}else {
        			dataMapPM.put("wcPM"+cntPM, w.splitLineWC(lines[cntPM]).get("wc"));
        			dataMapPM.put("wcdPM"+cntPM, w.splitLineWC(lines[cntPM]).get("wcd"));
        			dataMapPM.put("rpPM"+cntPM, w.splitLineWC(lines[cntPM]).get("rp"));
        			cntPM++;
        		}
        	}
        	
        	for(int i=3; i<=7; i++) {
        		
        		formattedDataMap.put("wc"+i,dataMapAM.get("wcAM"+i)+"|"+dataMapPM.get("wcPM"+i));
        		formattedDataMap.put("wcd"+i,dataMapAM.get("wcdAM"+i)+"|"+dataMapPM.get("wcdPM"+i));
        		formattedDataMap.put("rp"+i,dataMapAM.get("rpAM"+i)+"|"+dataMapPM.get("rpPM"+i));
        		
        	}
        		
        		//formattedDataMap.put("wc"+i ,dataMap.get("wcd"));
        		//formattedDataMap.put("wc"+i ,dataMap.get("rp"));
        	
        	
        
        System.out.println(formattedDataMap.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    	return formattedDataMap;
    	
    	
    	//
    }
    
    public Map<String,String> getWeatherTP(String rcode){

    	// 변수 설정

    	Map<String,String> formattedDataMap = new HashMap<String,String> (); // db로 보낼 데이터맵
    	
    	//***날짜가져오는 알고리즘 -> 함수화 필요
    	Date date =new Date();
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
    	SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMddHH");
    	String tmfc="";
    	Calendar c = Calendar.getInstance();
    	// -4
    	if (Integer.parseInt(sdf2.format(date)) >= Integer.parseInt(sdf.format(date)+"06")) {
    		Calendar.getInstance();
        	tmfc = sdf.format(date)+"06";
    	}else {
        	Calendar.getInstance();
        	c.add(c.DATE, -1);
        	tmfc = sdf.format(c.getTime())+"06";
    	}
    	//
    	
    	//오늘 새벽 6시 자료를
    	
    	
    	Map<String,String> params=new HashMap<String,String>();// get방식으로 요청 보낼 파라미터 모음
    	Map<String,String> headers=new HashMap<String,String>();
    	//
    	
    	String apiurl = "https://apihub.kma.go.kr/api/typ01/url/fct_afs_wc.php";
        params.put("authKey","zZ1CFjy9RvadQhY8vTb2fw");// 본인 서비스 키 입력
       
        params.put("reg", rcode);
        params.put("mode","0" );
        params.put("disp", "0" );
        params.put("tmfc", tmfc);
        headers.put("Connection", "keep-alive");
        
       
        URLlib urlCon = null;
        String result = null;
        boolean isSuccess=false;
	
        try {        
        	urlCon=new URLlib(apiurl,params); // api 주소, 파라미터(get), 헤더 값을 넣어 httpURLConnection 객체 할당
      
        	//urlCon.setRequestContentType("json");;// 응답받고자하는 콘텐츠 타입 지정
        	urlCon.setRequestMethod("GET");// get방식으로 요청하도록 세팅
        	
        	isSuccess=urlCon.getNetworkConnection();// 요청 실행
        	
        	if(isSuccess==false) {
        		return null;
        	}
            urlCon.readStreamToString("EUC-KR"); // 받아온 응답을 문자열로 저장
            result = urlCon.getResult(); // 응답 문자열을 가져옴
           
        }  catch(IOException e) {
            e.printStackTrace();
        } finally {
            urlCon.disconnect();
        }

          
        String filePath = "c:\\Temp\\weatherTP.json";
        
        
		
		 try { FileWriter fileWriter = new FileWriter(filePath);
		 		fileWriter.write(result);
		  
		 		fileWriter.close(); 
		  }catch (IOException e) { 
		  		e.printStackTrace(); 
		  }
		 
			WeatherDataExtractor w= new WeatherDataExtractor();
        	Map<String,String>dataMap= new HashMap<String,String>();
        	
        	
        	
        	String[] lines = w.findTPData(result);
        	if (lines.length==0) {
        		return null;
        	}
        	
        	
        	for(int i = 0; i<5;i++) {
        		
        		dataMap.put("tpMin"+(i+3), w.splitLineTP(lines[i]).get("tpMin"));
        		dataMap.put("tpMax"+(i+3), w.splitLineTP(lines[i]).get("tpMax"));
        		
        	}
        	
        	for(int i=3; i<=7; i++) {
        		
        		formattedDataMap.put("tp"+i,dataMap.get("tpMin"+i)+"|"+dataMap.get("tpMax"+i));
        		
        	}

        
        System.out.println(formattedDataMap.toString());

    	return formattedDataMap;
    }

    // 샘플용 코드, tmFc값 문서보고 잘 세팅할 것  
    private Map<String, Map<String, String>> getWeather(){
    	
    	Map<String,Map<String,String>> weatherMaps=new HashMap<String,Map<String,String>>();
    	Map<String,String>weatherMap= new HashMap<String,String>();
    	
    	Map<String,String> weatherWCMap;
    	Map<String,String> weatherTPMap;
    	
    	Map<String,String> rcodeMap = new HashMap<String,String>();
    	int i = 0;
    	rcodeMap.put("item"+(i++),"11B20503");//경기11B
		rcodeMap.put("item"+(i++),"11C10303");//충북11C1
		rcodeMap.put("item"+(i++),"11C20303");//충남11C2
		rcodeMap.put("item"+(i++),"11D10302");//강원11D
		rcodeMap.put("item"+(i++),"11H10502");//경북
		rcodeMap.put("item"+(i++),"11H20603");//경남
		rcodeMap.put("item"+(i++),"11F10201");//전북
		rcodeMap.put("item"+i,"11F20503");//전남
		 
		
    	
    	
    	for ( String key : rcodeMap.keySet() ) {
    		weatherMap = new HashMap<String,String>();
    		if(key.equals("item0")!=true) {
    			weatherWCMap=getWeatherWC(rcodeMap.get(key).substring(0,4)+"0000");
    			
    		}else {
    			weatherWCMap=getWeatherWC(rcodeMap.get(key).substring(0,3)+"00000");
    		}
    		weatherWCMap.put("addr","");
        	weatherTPMap=getWeatherTP(rcodeMap.get(key));
        	weatherMap.putAll(weatherTPMap);
        	weatherMap.putAll(weatherWCMap);
        	System.out.println(key);
        	weatherMaps.put(key,weatherMap);
    	}
    	
    	return weatherMaps;
    }
    
//    @RequestMapping("search/siteList/{page}")
//    ResponseEntity<Map<String, Object>> searchlist(@PathVariable("page") int page){
//    	
//    	return new ResponseEntity<>(map, HttpStatus.OK);
//    }
    
    
    @RequestMapping("insert/weather")
    private int insertWeather() {
    	int returnVal=1;
    	Map<String,Map<String,String>> weatherMaps;
    	try {
    	
    	weatherMaps=getWeather();
    	for(String key : weatherMaps.keySet()) {
    		Weather weatherDTO = new Weather(); 
    		System.out.println(weatherMaps.get(key).toString());
    		Map<String, String> weatherMap = weatherMaps.get(key);
    		BeanUtils.populate(weatherDTO, weatherMap);
    		System.out.println( weatherDTO.getRcode());
    	
    	weatherService.insertWeather(weatherDTO);
    	}
    	}catch(Exception e) {
    		e.printStackTrace();
    		returnVal=0;
    	}
    	
    	return returnVal;
    }
    
    
    @RequestMapping("get/weather")
    public Map<String,Map<String,Object>> getWeatherToView(){
    	Map<String,Map<String,Object>> returnVal= new HashMap<String,Map<String,Object>>();
    	
    	Map<String,Object> weatherMap= new HashMap<String,Object>(); 
    	List<Weather> wList = weatherService.getWeather();
    	
    	for(int i=0;i<wList.size();i++ ) {
    		try {
				BeanUtils.populate(weatherMap, BeanUtils.describe(wList.get(i)));
				System.out.println(weatherMap.toString());
				
				returnVal.put("item"+i, weatherMap);
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				continue;
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				continue;
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				continue;
			}
    		
    	}
    	
    	
    	return returnVal;
    }
    
    public void updateWeather() {
    	Map<String,Object> weatherMap= new HashMap<String,Object>();
    	Map<String,Map<String,String>> weatherMaps;
    	weatherMaps=getWeather();
    	List<Weather> wList = weatherService.getWeather();
    	
    	
    }
    
}
