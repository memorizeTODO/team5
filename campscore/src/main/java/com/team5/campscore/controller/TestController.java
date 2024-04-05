package com.team5.campscore.controller;

import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.team5.campscore.utilities.WeatherDataExtractor;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.team5.campscore.model.Weather;
import com.team5.campscore.service.*;

@RestController
@RequestMapping("/")
public class TestController {
	@Autowired
	WeatherDAOImpl weatherService;
	@Autowired
	
	
	
    @RequestMapping("test1")
	public void testMethod() {
    	
        
        // 변수 설정
        String apiURL = "http://apis.data.go.kr/1360000/MidFcstInfoService/getMidLandFcst";
        String serviceKey = "qB0RB3NhMOOhDD8j/0UaO514AWZMty+bIyJTvHYiWvIGRa0+W0MH0tZ/9QlcJw/BG1Sdu4J98qBpn7PucDdSUg=="; // 본인 서비스 키 입력
        String numOfRows = "10";
        String regId = "11B00000";
        String tmFc = "202403191800";
        String pageNo = "1";
        
        String dataType = "JSON";
		
		
		HttpURLConnection urlConnection = null;
        InputStream stream = null;
        String result = null;

		/*
		 * String urlStr = callBackUrl + "serviceKey=" + serviceKey + "&dataType=" +
		 * dataType + "&base_date=" + baseDate + "&base_time=" + baseTime +
		 * "&beach_num=" + beachNum;
		 */

        try {
        	StringBuilder urlBuilder = new StringBuilder(apiURL);
        	urlBuilder.append("?" + URLEncoder.encode("tmFc", "UTF-8") + "=" + URLEncoder.encode(tmFc, "UTF-8"));
        	urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode(numOfRows, "UTF-8"));
        	urlBuilder.append("&" + URLEncoder.encode("regId", "UTF-8") + "=" + URLEncoder.encode(regId, "UTF-8"));
        	urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode(pageNo, "UTF-8"));
        	urlBuilder.append("&" + URLEncoder.encode("dataType", "UTF-8") + "=" + URLEncoder.encode(dataType, "UTF-8"));
        	urlBuilder.append("&" + URLEncoder.encode("ServiceKey", "UTF-8") + "=" + URLEncoder.encode(serviceKey, "UTF-8"));
            // 받으려는 타입
            
            URL url = new URL(urlBuilder.toString() );
            

            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setRequestProperty("Content-type", "application/json");
            
            stream = getNetworkConnection(urlConnection);
            result = readStreamToString(stream);
            
            String filePath = "c:\\Temp\\test.json";
            
            try {
             FileWriter fileWriter = new FileWriter(filePath);
             fileWriter.write(result);
             
             fileWriter.close();
            } catch (IOException e) {
             // TODO Auto-generated catch block
             e.printStackTrace();
            }
    
            
           
            // 잘 출력되는지 확인하고 싶으면 아래 주석 해제
//            System.out.println(weather);


            
        } catch(IOException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
        
        
		
	
	}
    @RequestMapping("test2")// 샘플용 코드, tmFc값 문서보고 잘 세팅할 것    
    private void test2Method() {
    
        // 변수 설정
        String apiURL = "http://apis.data.go.kr/1360000/MidFcstInfoService/getMidLandFcst";
        String serviceKey = "qB0RB3NhMOOhDD8j/0UaO514AWZMty+bIyJTvHYiWvIGRa0+W0MH0tZ/9QlcJw/BG1Sdu4J98qBpn7PucDdSUg=="; // 본인 서비스 키 입력
        String numOfRows = "10";
        String regId = "11B00000";
        String tmFc = "202403250600";
        String pageNo = "1";
        
        String dataType = "JSON";
		
		
		HttpURLConnection urlConnection = null;
        InputStream stream = null;
        String result = null;

		/*
		 * String urlStr = callBackUrl + "serviceKey=" + serviceKey + "&dataType=" +
		 * dataType + "&base_date=" + baseDate + "&base_time=" + baseTime +
		 * "&beach_num=" + beachNum;
		 */

        try {
        	StringBuilder urlBuilder = new StringBuilder(apiURL);
        	urlBuilder.append("?" + URLEncoder.encode("tmFc", "UTF-8") + "=" + URLEncoder.encode(tmFc, "UTF-8"));
        	urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode(numOfRows, "UTF-8"));
        	urlBuilder.append("&" + URLEncoder.encode("regId", "UTF-8") + "=" + URLEncoder.encode(regId, "UTF-8"));
        	urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode(pageNo, "UTF-8"));
        	urlBuilder.append("&" + URLEncoder.encode("dataType", "UTF-8") + "=" + URLEncoder.encode(dataType, "UTF-8"));
        	urlBuilder.append("&" + URLEncoder.encode("ServiceKey", "UTF-8") + "=" + URLEncoder.encode(serviceKey, "UTF-8"));
            // 받으려는 타입
            
            URL url = new URL(urlBuilder.toString() );
            

            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setRequestProperty("Content-type", "application/json");
            
            stream = getNetworkConnection(urlConnection);
            result = readStreamToString(stream);
            
            String filePath = "c:\\Temp\\test.json";
            
            
            try {
             FileWriter fileWriter = new FileWriter(filePath);
             fileWriter.write(result);
             
             fileWriter.close();
            } catch (IOException e) {
             // TODO Auto-generated catch block
             e.printStackTrace();
            }
            
            try {
                JSONObject jsonObject = new JSONObject(result);

                // Example: Accessing specific values
                
                JSONObject response = jsonObject.getJSONObject("response");
                
                JSONObject body = response.getJSONObject("body");
                JSONObject items = body.getJSONObject("items");
                JSONObject item = items.getJSONArray("item").getJSONObject(0);
                
                System.out.println(item.toString());
				
				 int rnSt3Am = item.getInt("rnSt3Am"); 
				 int rnSt3Pm =item.getInt("rnSt3Pm"); 
				 String wf3Am = item.getString("wf3Am"); 
				 String wf3Pm = item.getString("wf3Pm");
				 
                

				
				 System.out.println("rnSt3Am: " + rnSt3Am);
				 System.out.println("rnSt3Pm: " +rnSt3Pm); 
				 System.out.println("wf3Am: " + wf3Am); 
				 System.out.println("wf3Pm: "+ wf3Pm);
				 

                // You can similarly access other values as needed

            } catch (Exception e) {
                e.printStackTrace();
            }
    
            
           
            // 잘 출력되는지 확인하고 싶으면 아래 주석 해제
//            System.out.println(weather);


            
        } catch(IOException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
    	
    }
    
    private InputStream getNetworkConnection(HttpURLConnection urlConnection) throws IOException {
        urlConnection.setConnectTimeout(3000);
        urlConnection.setReadTimeout(3000);
        urlConnection.setDoInput(true);//서버의 응답을 inputStream으로 받겠다는 뜻

        if(urlConnection.getResponseCode() != HttpURLConnection.HTTP_OK) {
            throw new IOException("HTTP error code : " + urlConnection.getResponseCode());
        }

        return urlConnection.getInputStream();
    }

    /* InputStream을 전달받아 문자열로 변환 후 반환 */
    private String readStreamToString(InputStream stream) throws IOException{
        StringBuilder result = new StringBuilder();

        BufferedReader br = new BufferedReader(new InputStreamReader(stream, "UTF-8"));

        String readLine;
        while((readLine = br.readLine()) != null) {
            result.append(readLine + "\n\r");
        }

        br.close();

        return result.toString();
    }
    
   
    	
        @RequestMapping("test3")
    	public void testMethod3() {
        	
            
            // 변수 설정
            String apiURL = "http://apis.data.go.kr/1360000/WthrWrnInfoService/getWthrPwn";
            String serviceKey = "qB0RB3NhMOOhDD8j/0UaO514AWZMty+bIyJTvHYiWvIGRa0+W0MH0tZ/9QlcJw/BG1Sdu4J98qBpn7PucDdSUg=="; // 본인 서비스 키 입력
            String numOfRows = "10";           
            String fromTmFc ="20240316";
            String toTmFc ="20240321";
            String stnId ="108";
            String pageNo = "1";
            String dataType = "JSON";
    		
    		
    		HttpURLConnection urlConnection = null;
            InputStream stream = null;
            String result = null;

    		/*
    		 * String urlStr = callBackUrl + "serviceKey=" + serviceKey + "&dataType=" +
    		 * dataType + "&base_date=" + baseDate + "&base_time=" + baseTime +
    		 * "&beach_num=" + beachNum;
    		 */

            try {
            	StringBuilder urlBuilder = new StringBuilder(apiURL);
            	urlBuilder.append("?" + URLEncoder.encode("fromTmFc", "UTF-8") + "=" + URLEncoder.encode(fromTmFc, "UTF-8"));
            	urlBuilder.append("&" + URLEncoder.encode("toTmFc", "UTF-8") + "=" + URLEncoder.encode(toTmFc, "UTF-8"));
            	urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode(numOfRows, "UTF-8"));            	
            	urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode(pageNo, "UTF-8"));
            	urlBuilder.append("&" + URLEncoder.encode("stnId", "UTF-8") + "=" + URLEncoder.encode(stnId, "UTF-8"));
            	urlBuilder.append("&" + URLEncoder.encode("dataType", "UTF-8") + "=" + URLEncoder.encode(dataType, "UTF-8"));
            	urlBuilder.append("&" + URLEncoder.encode("ServiceKey", "UTF-8") + "=" + URLEncoder.encode(serviceKey, "UTF-8"));
                // 받으려는 타입
                
                URL url = new URL(urlBuilder.toString() );
                

                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.setRequestProperty("Content-type", "application/json");
                
                stream = getNetworkConnection(urlConnection);
                result = readStreamToString(stream);
                
                String filePath = "c:\\Temp\\test3.json";
                
                try {
                 FileWriter fileWriter = new FileWriter(filePath);
                 fileWriter.write(result);
                 
                 fileWriter.close();
                } catch (IOException e) {
                 // TODO Auto-generated catch block
                 e.printStackTrace();
                }
        
                
               
                // 잘 출력되는지 확인하고 싶으면 아래 주석 해제
//                System.out.println(weather);


                
            } catch(IOException e) {
                e.printStackTrace();
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
            }
        }
            
            
            @RequestMapping("rusService")
        	public void rusService() {
            	
                
            	
            	
                // 변수 설정
                String apiURL = "http://apis.data.go.kr/B551011/RusService1/areaBasedList1";
                String serviceKey = "qB0RB3NhMOOhDD8j/0UaO514AWZMty+bIyJTvHYiWvIGRa0+W0MH0tZ/9QlcJw/BG1Sdu4J98qBpn7PucDdSUg=="; // 본인 서비스 키 입력
                String numOfRows = "10";           
                String areaCode="1";
                String pageNo = "1";
                String _type = "JSON";
        		
        		
        		HttpURLConnection urlConnection = null;
                InputStream stream = null;
                String result = null;

        		/*
        		 * String urlStr = callBackUrl + "serviceKey=" + serviceKey + "&dataType=" +
        		 * dataType + "&base_date=" + baseDate + "&base_time=" + baseTime +
        		 * "&beach_num=" + beachNum;
        		 */

                try {
                	StringBuilder urlBuilder = new StringBuilder(apiURL);
                	urlBuilder.append("?" + URLEncoder.encode("areaCode", "UTF-8") + "=" + URLEncoder.encode(areaCode, "UTF-8"));
                	urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode(numOfRows, "UTF-8"));            	
                	urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode(pageNo, "UTF-8"));
                	urlBuilder.append("&" + URLEncoder.encode("MobileOS", "UTF-8") + "=" + URLEncoder.encode("ETC", "UTF-8"));
                	urlBuilder.append("&" + URLEncoder.encode("MobileApp", "UTF-8") + "=" + URLEncoder.encode("test", "UTF-8"));
                	urlBuilder.append("&" + URLEncoder.encode("_type", "UTF-8") + "=" + URLEncoder.encode(_type, "UTF-8"));
                	urlBuilder.append("&" + URLEncoder.encode("ServiceKey", "UTF-8") + "=" + URLEncoder.encode(serviceKey, "UTF-8"));
                    // 받으려는 타입
                    
                    URL url = new URL(urlBuilder.toString() );
                    

                    urlConnection = (HttpURLConnection) url.openConnection();
                    urlConnection.setRequestMethod("GET");
                    urlConnection.setRequestProperty("Content-type", "application/json");
                    
                    stream = getNetworkConnection(urlConnection);
                    result = readStreamToString(stream);
                    
                    String filePath = "c:\\Temp\\rusService.json";
                    
                    try {
                     FileWriter fileWriter = new FileWriter(filePath);
                     fileWriter.write(result);
                     
                     fileWriter.close();
                    } catch (IOException e) {
                     // TODO Auto-generated catch block
                     e.printStackTrace();
                    }
            
                    
                   
                    // 잘 출력되는지 확인하고 싶으면 아래 주석 해제
//                    System.out.println(weather);


                    
                } catch(IOException e) {
                    e.printStackTrace();
                } finally {
                    if (urlConnection != null) {
                        urlConnection.disconnect();
                    }
                }
    		
    	
    	}
            
        @RequestMapping("go/testview")
        private RedirectView test5() {
        	  RedirectView redirectView = new RedirectView();
              redirectView.setUrl("http://localhost:8080/views/test.html");
              return redirectView;
        }
        
        
        @RequestMapping("main/search/kakaomap/keyword")//  
        //@CrossOrigin(origins="*") 
        private Map<String,Map<String,Object>> test5Method() {
        	
        	
        	HashMap<String,String> params=new HashMap<String,String>();// get방식으로 요청 보낼 파라미터 모음
        	HashMap<String,String> headers=new HashMap<String,String>();// 요청을 보낼때 같이 보낼 헤더
            
            String apiurl="https://dapi.kakao.com/v2/local/search/keyword.json"; //api 주소
            params.put("query","경기도 시흥시 카라반");
            
            params.put("numOfRows","10");
            params.put("pageNo","1");
    		
            headers.put("Authorization","KakaoAK adacc2024f0537f8eb428ee10db1dc20");//api의key값(카카오는 headers에,공공데이터는 params에)
            
    		URLlib urlCon = null;
            String result = null;
            boolean isSuccess=false;
    	
            try {        
            	urlCon=new URLlib(apiurl,params,headers); // api 주소, 파라미터(get), 헤더 값을 넣어 httpURLConnection 객체 할당
          
            	urlCon.setRequestContentType("application/json");// 응답받고자하는 콘텐츠 타입 지정
            	urlCon.setRequestMethod("GET");// get방식으로 요청하도록 세팅
            	for(int i=0;i<3;i++) {
            		isSuccess=urlCon.getNetworkConnection();// 요청 실행
            		if (isSuccess==true) {
            			break;
            		}
            	}
                urlCon.readStreamToString("UTF-8"); // 받아온 응답을 문자열로 저장
                result = urlCon.getResult(); // 응답 문자열을 가져옴
               
            } catch(IOException e) {
                e.printStackTrace();
            } finally {
                urlCon.disconnect();
            }
            
            // 로컬디스크에 json 파일로 저장
            String filePath = "c:\\Temp\\kakaoMap_result.json";
            try {
             FileWriter fileWriter = new FileWriter(filePath);
             fileWriter.write(result);
             
             fileWriter.close();
            } catch (IOException e) {
             // TODO Auto-generated catch block
             e.printStackTrace();
            }
            
            //json 추출
            Map<String,Map<String,Object>> resItems = new HashMap<String,Map<String,Object>>();
            JSONObject returnJSONObject = new JSONObject(); //장소 데이터 묶음들이 저장된 JSONArray를 JSONObject에 저장;// 리턴할 JSONObject 
            try {
                // Example: Accessing specific values
            	
                JSONArray returnJSONArray = new JSONArray();  
                
                JSONObject res = new JSONObject(result);
                
                JSONArray documents = res.getJSONArray("documents");
                JSONObject meta = res.getJSONObject("meta");
                
                
                int totalCount= meta.getInt("total_count") ; // 반환된 장소데이터 묶음 개수
                JSONObject resItemJSONObj;
                
                for(int i = 0; i < totalCount;i++) {
                	if(documents.length()==0) {
                		break;
                	}
                	Map<String,Object> itemMap = new HashMap<String,Object>();
                	JSONObject item = documents.getJSONObject(i);// resItem = 가져온 장소 데이터 묶음(JSONObject) 중 하나
                	itemMap.put("address_name",item.getString("address_name"));
                	itemMap.put("id", item.getString("id"));
                	itemMap.put("x", item.getDouble("x"));
                	itemMap.put("y", item.getDouble("y"));
                	
                	// 경도 위도 변환
                	TransformCoordinates tsc = new TransformCoordinates();
                	tsc.setCRSFactory();
                	tsc.setCoordinateTransformFactory();
                	tsc.transformCoords((double)itemMap.get("x"),(double)itemMap.get("y") );
                	System.out.println("address_name:" + (String)itemMap.get("address_name"));
                	System.out.println(itemMap.get("x").toString() + "  " + (String)itemMap.get("y").toString());
                	tsc.getResult();
                	//
                	//resItems.put("item_"+i, itemMap);
                	resItemJSONObj = new JSONObject(itemMap);//사용할 정보만 모아서 JSONObject로 변환하여 장소 데이터 묶음으로 재저장
                	returnJSONObject.put("items_"+i,resItemJSONObj);
                	resItems.put("items_"+i ,itemMap);
                }
              
                
                
                //System.out.println(returnJSONObject.toString());
				
                


            } catch (Exception e) {
                e.printStackTrace();
            }
        	
            return resItems;
            
            
        }
        
       
        
        
       // @RequestMapping("insert/weather")// 샘플용 코드, tmFc값 문서보고 잘 세팅할 것  
       // private int getWeatherToDB(String rcode){
        	
        	
       // }
        private String findWeatherCondition(String wc) {
        	if (wc==null|| wc.equals("")) {
        		return "";
        	}
        	if(wc.indexOf("맑")!=-1) {
            	return "C1";
            }else if(wc.indexOf("흐림")!=-1 || wc.indexOf("흐리고")!=-1) {
            	return "C2";
            }else if(wc.indexOf("구름많")!=-1) {
            	return "C3";
            }
        	
        	return "";
        }
        
        private String findWeatherConditionDetail(String wcd) {
        	String returnVal="";
        	if (wcd==null || wcd.equals("")) {
        		return returnVal;
        	}
        	
        	if(wcd.indexOf("비")!=-1) { //'비' 라는 문자를 찾았을때
        		if (wcd.length()!=0 ) {
        			returnVal+="/";
        		}
            	returnVal+="D1";
            }
        	if(wcd.indexOf("눈")!=-1) { //'눈' 이라는 문자를 찾았을때
        		if (wcd.length()!=0 ) {
        			returnVal+="/";
        		}
            	returnVal+="D2";
            }
        	if(wcd.indexOf("소나기")!=-1) { //'소나기' 라는 문자를 찾았을때
        		if (wcd.length()!=0 ) {
        			returnVal+="/";
        		}
            	returnVal+="D3";
            }
        	return returnVal;
        }
        
        
        
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
            	
            	for(int i=0;i<3;i++) {
            		isSuccess=urlCon.getNetworkConnection();// 요청 실행
            		if (isSuccess==true) {
            			break;
            		}
            	}// 요청 실행
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
            	
            	for(int i=0;i<3;i++) {
            		isSuccess=urlCon.getNetworkConnection();// 요청 실행
            		if (isSuccess==true) {
            			break;
            		}
            	}// 요청 실행
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
            	
            	String am;
            	String pm;
            	
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
			
			/*
			 * rcodeMap.put("item"+(i++),"11C10303");//충북11C1
			 * rcodeMap.put("item"+(i++),"11C20303");//충남11C2
			 * rcodeMap.put("item"+(i++),"11D10302");//강원11D
			 * rcodeMap.put("item"+(i++),"11H10502");//경북
			 * rcodeMap.put("item"+(i++),"11H20603");//경남
			 * rcodeMap.put("item"+(i++),"11F10201");//전북
			 * rcodeMap.put("item"+i,"11F20503");//전남
			 */			         	
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
        		for(String innerKey:weatherMap.keySet()) {
        			switch(innerKey) {
        				case "rcode": 
        					weatherDTO.setRcode(weatherMap.get(innerKey)); break;
        				case "addr": 
        					weatherDTO.setAddr(weatherMap.get(innerKey)); break;
        				case "wc0": 
        					weatherDTO.setWc0(weatherMap.get(innerKey)); break;
        				case "wc1": 
        					weatherDTO.setWc1(weatherMap.get(innerKey)); break;
        				case "wc2": 
        					weatherDTO.setWc2(weatherMap.get(innerKey)); break;
        				case "wc3": 
        					weatherDTO.setWc3(weatherMap.get(innerKey)); break;
        				case "wc4": 
        					weatherDTO.setWc4(weatherMap.get(innerKey)); break;
        				case "wc5": 
        					weatherDTO.setWc5(weatherMap.get(innerKey)); break;
        				case "wc6": 
        					weatherDTO.setWc6(weatherMap.get(innerKey)); break;
        				case "wc7": 
        					weatherDTO.setWc7(weatherMap.get(innerKey)); break;
        				case "wcd0": 
        					weatherDTO.setWcd0(weatherMap.get(innerKey)); break;
        				case "wcd1": 
        					weatherDTO.setWcd1(weatherMap.get(innerKey)); break;
        				case "wcd2": 
        					weatherDTO.setWcd2(weatherMap.get(innerKey)); break;
        				case "wcd3": 
        					weatherDTO.setWcd3(weatherMap.get(innerKey)); break;
        				case "wcd4": 
        					weatherDTO.setWcd4(weatherMap.get(innerKey)); break;
        				case "wcd5": 
        					weatherDTO.setWcd5(weatherMap.get(innerKey)); break;
        				case "wcd6": 
        					weatherDTO.setWcd6(weatherMap.get(innerKey)); break;
        				case "wcd7": 
        					weatherDTO.setWcd7(weatherMap.get(innerKey)); break;
        				case "rp0": 
        					weatherDTO.setRp0(weatherMap.get(innerKey)); break;
        				case "rp1": 
        					weatherDTO.setRp1(weatherMap.get(innerKey)); break;
        				case "rp2": 
        					weatherDTO.setRp2(weatherMap.get(innerKey)); break;
        				case "rp3": 
        					weatherDTO.setRp3(weatherMap.get(innerKey)); break;
        				case "rp4": 
        					weatherDTO.setRp4(weatherMap.get(innerKey)); break;
        				case "rp5": 
        					weatherDTO.setRp5(weatherMap.get(innerKey)); break;
        				case "rp6": 
        					weatherDTO.setRp6(weatherMap.get(innerKey)); break;
        				case "rp7": 
        					weatherDTO.setRp7(weatherMap.get(innerKey)); break;
        				case "tp0": 
        					weatherDTO.setTp0(weatherMap.get(innerKey)); break;
        				case "tp1": 
        					weatherDTO.setTp1(weatherMap.get(innerKey)); break;
        				case "tp2": 
        					weatherDTO.setTp2(weatherMap.get(innerKey)); break;
        				case "tp3": 
        					weatherDTO.setTp3(weatherMap.get(innerKey)); break;
        				case "tp4": 
        					weatherDTO.setTp4(weatherMap.get(innerKey)); break;
        				case "tp5": 
        					weatherDTO.setTp5(weatherMap.get(innerKey)); break;
        				case "tp6": 
        					weatherDTO.setTp6(weatherMap.get(innerKey)); break;
        				case "tp7": 
        					weatherDTO.setTp7(weatherMap.get(innerKey)); break;
        			
        			
        			}
        			
        		}
        	
        	weatherService.insertWeather(weatherDTO);
        	}
        	}catch(Exception e) {
        		e.printStackTrace();
        		returnVal=0;
        	}
        	
        	return returnVal;
        }
        
        @RequestMapping("get/weather")
        public Map<String,Map<String,String>> getWeatherToView(){
        	Map<String,Map<String,String>> returnVal= new HashMap<String,Map<String,String>>();
        	Gson gson = new Gson();
        	List<Weather> wList= new ArrayList<Weather>();
        	wList = weatherService.getWeather();
        	
        	for(Weather w : wList) {
        		ObjectMapper objectMapper = new ObjectMapper();

        		// DTO 객체를 Map으로 변환
        		Map<String, String> item = objectMapper.convertValue(w, new TypeReference<Map<String, String>>() {});
        		if(w.getRcode().equals("11B00000")) {
        			returnVal.put("item0",item);
        		}else if(w.getRcode().equals("11C10000")) {
        			returnVal.put("item0",item);
        		}else if(w.getRcode().equals("11C20000")) {
        			returnVal.put("item0",item);
        		}else if(w.getRcode().equals("11D10000")) {
        			returnVal.put("item0",item);
        		}else if(w.getRcode().equals("11H10000")) {
        			returnVal.put("item0",item);
        		}else if(w.getRcode().equals("11H20000")) {
        			returnVal.put("item0",item);
        		}else if(w.getRcode().equals("11F10000")) {
        			returnVal.put("item0",item);
        		}else if(w.getRcode()=="11F20000") {
        			returnVal.put("item0",item);
        		}else {
        			return null;
        		}
        		
        	}
        	
        	
        	return returnVal;
        }
        
        
        
        
        
       
        	
        	
        

                   
}
