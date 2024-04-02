package com.team5.campscore.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;

public class URLlib {
	private HttpURLConnection urlConnection;
	private StringBuilder urlBuilder;
	private URL url;
	private InputStream resInputStream;
	private String result;
	
	URLlib(){}//인스턴스 참조멤버들의 초기화 없이 URLlib클래스의 객체만 생성
	
	
	URLlib(String url){//url 값대로 내부의 URL클래스의 인스턴스를 생성
		setURLbuilder(url);
		setURL();
		setURLConnection();
	}
	URLlib(String url,HashMap<String,String> properties){//url 값과 get방식 호출을 위한 인자인 properties를 토대로 URL클래스의 인스턴스를 생성
		setURLbuilder(url,properties);
		setURL();
		System.out.println(urlBuilder.toString());
		setURLConnection();
	}
	
	URLlib(String url,HashMap<String,String> properties,HashMap<String,String> headers){//url 값과 get방식 호출을 위한 인자인 properties를 토대로 URL클래스의 인스턴스를 생성
		setURLbuilder(url,properties);
		setURL();
		System.out.println(urlBuilder.toString());
		setURLConnection();
		setRequestHeaders(headers);
	}
	
	void setURLbuilder(String url) { //인자로 가져온 스트링 값을 토대로 urlBuilder 객체 생성
		urlBuilder = new StringBuilder(url); 
	}
	
	void setURLbuilder(String url,HashMap<String,String>properties) { //문자열 형식의 url과 get방식으로 넘겨줄 인자들(key,value)을 urlBuilder의 요소로 추가
		urlBuilder = new StringBuilder(url);
		
		setURLBuilderProperties(properties);
		
	}
	
	void setURLBuilderProperties(HashMap<String,String>properties) { //urlBuilder에 get방식으로 넘겨줄 인자들(key,value)을 요소로 추가
		if(urlBuilder==null|| urlBuilder.length()==0|| properties==null||properties.isEmpty()==true) { //요청을 보낼 url값이 먼저 저장되있지 않거나 하면 값 저장 안함
			return;
		}
		String value="";
		String separator = "?";
				
		for( String key : properties.keySet() ){
			value = properties.get(key);
			
			try {
				if ((urlBuilder.toString()).indexOf("?")!=-1) {
					separator="&";
				}
				urlBuilder.append(separator+URLEncoder.encode(key, "UTF-8") + "=" + URLEncoder.encode(value, "UTF-8"));
			}
			catch(UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	
	
	
	void setURL() { //먼저 urlBuilder를 생성, 세팅후 호출해야함 URL클래스 객체 생성
		try {
			this.url = new URL(urlBuilder.toString());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	void setURL(String url) { //먼저 urlBuilder를 생성, 세팅후 호출해야함 URL클래스 객체 생성
		try {
			this.url = new URL(url);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	void setURL(String url,HashMap<String,String>properties) { //파라미터 값을 이용해 urlBuilder를 생성,설정 후 URL클래스 객체 생성
		if(properties.isEmpty()==false|| properties!=null) {
			setURLbuilder(url,properties);
		}
		try {
			this.url = new URL(urlBuilder.toString());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	void setURLConnection() { //urlConnection 세팅 
		try {
			urlConnection =(HttpURLConnection) this.url.openConnection();
		}catch(IOException e) {
            e.printStackTrace();
        }	
	}
	
	
	void setURLConnection(URL url){ //인자로 가져온 파라미터를 토대로 urlConnection 세팅
		try {
			urlConnection =(HttpURLConnection) url.openConnection();
		}catch(IOException e) {
            e.printStackTrace();
        }	
	}
	
	void setRequestMethod(String reqMethod) {//urlConnection의 요청방식 세팅
		try {
			urlConnection.setRequestMethod(reqMethod);
		} catch (ProtocolException e) {
			e.printStackTrace();
		}
	}
	void setRequestContentType(String contentType) {//urlConnection의 요청에 대한 응답의 콘텐츠 타입 지정 
		urlConnection.setRequestProperty("Content-type", "application/"+contentType);
	}
	void setRequestHeaders(HashMap<String,String> headers) {
		if (headers==null||headers.isEmpty()==true) { //헤더가 비어있으면 수행 안함
			return;
		}
		String value="";

		for( String key : headers.keySet() ){
			value = headers.get(key);
			urlConnection.setRequestProperty(key,value);
		}
		
		
	}
	
	boolean isNull() {
		return urlConnection == null;
	}
	void disconnect() {
		if(isNull()==false) {
			urlConnection.disconnect();
		}
	}
	
	
	void getNetworkConnection() throws IOException { // 세팅완료된 urlConnection객체의 메소드를 통해 요청을 하여 서버로부터 응답을 받아와 urlConnection 객체 안의 inputstream 객체에 저장 
        urlConnection.setConnectTimeout(3000);
        urlConnection.setReadTimeout(3000);
        urlConnection.setDoInput(true);//서버의 응답을 inputStream으로 받겠다는 뜻

        if(urlConnection.getResponseCode() != HttpURLConnection.HTTP_OK) {
            throw new IOException("HTTP error code : " + urlConnection.getResponseCode());
        }

        resInputStream = urlConnection.getInputStream();
    }
	
	void readStreamToString() throws IOException{
        StringBuilder result = new StringBuilder();

        BufferedReader br = new BufferedReader(new InputStreamReader(this.resInputStream, "UTF-8"));

        String readLine;
        while((readLine = br.readLine()) != null) {
            result.append(readLine + "\n\r");
        }

        br.close();

        this.result = result.toString();
    }
	
	String getResult() { //최종 결과물 반환
		return this.result;
	}
	
}
