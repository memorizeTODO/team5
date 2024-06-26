<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title></title>
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="">
    </head>
    <body>
        <link
            rel="stylesheet"
            href="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.css"
        />
        <script src="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.js"></script>
        <script src="https://cdn.tailwindcss.com"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/flowbite/2.3.0/datepicker.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/swiper@11/swiper-element-bundle.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.js"></script>


     
        <header 
            class="fixed flex justify-between p-5 w-full bg-white z-50 border-2 border-black-100"
        >   
            <button type="submit" onclick=location.href="mainpage.jsp" class="ml-5">
                <span class="text-4xl text-[#74cfca] font-bold">CAMP</span> <span class="text-2xl">*</span> <span class="text-4xl text-[#74cfca] font-bold">SCORE</span>
            </button>
        <div class="flex justify-start">   
            <div date-rangepicker date="sysdate" class="flex items-center relative">
                <div class="relative">
                  <div class="absolute inset-y-0 start-0 flex items-center ps-3 pointer-events-none">

                  </div>
                  <div>
                  <input name="start" inline-datepicker datepicker-format="mm/dd/yyyy" type="text" class="bg-gray-50 border rounded-l-lg border-gray-300 text-gray-900 text-sm
                                                          focus:ring-blue-500 focus:border-blue-500 
                                                            block w-full ps-10 p-2.5  
                                                        " 
                            placeholder="">
                        </input>
                </div>         
                </div>
                <div class="relative">
                  <div class="absolute inset-y-0 start-0 flex items-center ps-3 pointer-events-none">
                  </div>
                  <input name="end" type="text"  class="bg-gray-50 border border-gray-300 text-gray-900 text-sm focus:ring-blue-500 
                                                        focus:border-blue-500 block w-full ps-10 p-2.5" 
                            placeholder="">
              </div>
              <div>
                <input name="지역" type="text" class="bg-gray-50 border rounded-r-lg border-gray-300 text-gray-900 text-sm  focus:ring-blue-500 
                focus:border-blue-500 block w-full ps-10 p-2.5">
            </div> 
            
              </div>
              <div class= "flex relative items-center">   
                <button id="headerSearchButton" type="submit" onclick="search()" class="bg-[#eeeeee] hover:bg-[#dddddd] 
                                                                w-10 h-10    rounded-full
                                                                ">
                    <svg viewBox="-5 -5  36 36" fill="none" xmlns="http://www.w3.org/2000/svg" class="">
                        <path d="M15.7955 15.8111L21 21M18 10.5C18 14.6421 14.6421 18 10.5 18C6.35786 18 3 14.6421 3 10.5C3 6.35786 6.35786 3 10.5 3C14.6421 3 18 6.35786 18 10.5Z"
                        stroke="#000000" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"/>
                                                                        </svg> 
                </button>
            </div>
        </div>                                                                 
            <div>
                <button class="bg-[#eeeeee] hover:bg-[#dddddd] w-full h-full px-10 rounded-lg">로그인</button>
            </div>
        </header>
        <div class="pt-20 z-10">
            <div>
                <div id="camp-list">                                               
                    </div>
                    
                </div>
                <div class="h-auto px-5">
                    <div class="flex flex-col 2xl:flex-row w-auto relative rounded-lg relative mx-5  px-10 border-b-2 border-black-100">
                        <div class="basis-3/5 p-5 content-center">
                            <img class="flex h-4/5 w-full relative rounded-lg mr-20 " src="images/gapyung67.webp" >
                        </div>
                
                        <div class="grid grid-cols-2 gap-5 relative basis-2/5 content-center">
                            <div>
                                <img class="rounded-lg" src="images/gapyung67-5.jpeg" alt="">
                            </div>
                            <div>
                                <img class="rounded-lg" src="images/gapyung67-2.jpeg" alt="">
                            </div>
                            <div>
                                <img class="rounded-lg" src="images/gapyung67-3.jpeg" alt="">
                            </div>
                            <div>
                                <img class="rounded-lg" src="images/gapyung67-4.jpeg" alt="">
                            </div>
                        </div>
                     </div>
                </div>
            
            <div class="px-10">
                <div class="py-10 font-bold text-4xl px-20 border-b-2 border-black-100 ">
                    캠핑장 소개
                    <div class="font-medium text-sm py-5">
                        프라이빗 계곡과 독채 럭셔리 글램핑을 즐길 수 있는 캠핑장입니다
                        히노끼 월풀 스파, 영화관 객실, 안마의자 등 즐길 거리가 갖춰져 있습니다
                    </div>
                </div>
            <div class="relative w-full px-10 border-b-2 border-black-100 py-5">
                <div class="flex  h-80 w-10/12  bg-[#ffffff]  z-30 flex py-5 rounded-lg">
                            <div id="weather-list" class="flex flex-row">
                            </div>
                            
                </div>
            </div>   
            </div>
            <div class="py-10 font-bold text-md px-20  ">
                    추천 관광지
                </div>
                <div>
                    <div class="grid md:grid-cols-4 gap-4 px-20">
                        <div>
                            <img class="h-auto max-w-full rounded-lg" src="images/tour1.jpg" alt="">
                            <label for="campExp1" class="text-center block mb-2 text-md  font-bold text-gray-900 dark:text-white px-10 py-5">가평67캠핑장</label>
                        </div>
                        <div>
                            <img class="h-auto max-w-full rounded-lg" src="images/tour2.bmp" alt="">
                            <label for="campExp1" class="text-center block mb-2 text-md  font-bold text-gray-900 dark:text-white px-10 py-5">가평67캠핑장</label>
                        </div>
                        <div>
                            <img class="h-auto max-w-full rounded-lg" src="images/tour3.jpg" alt="">
                            <label for="campExp1" class="text-center block mb-2 text-md  font-bold text-gray-900 dark:text-white px-10 py-5">가평67캠핑장</label>
                        </div>
                        <div>
                            <img class="h-auto max-w-full rounded-lg" src="images/tour4.jpg" alt="">
                            <label for="campExp1" class="text-center block mb-2 text-md  font-bold text-gray-900 dark:text-white px-10 py-5">가평67캠핑장</label>
                        </div>
                    </div>
                </div>
			</div>
                                                              
        <script>
		let weatherScoreArray = new Array();
		let sum = 0;
    	let weatherScoreArray_avg =0;
			
      	async function getWeatherByRegion(){
        	const res = await fetch(`http://localhost:80/get/weather?region=충남`);
        	const resJson = await res.json();
        	const weathers = Object.values(resJson);
        	
        	 const data = [
                 {
                	 weather: resJson.wc0,
                     temp:resJson.tp0,
                     prec:resJson.rp0,
					 rain:resJson.wcd0,
                 },
                 {
                	 weather: resJson.wc1,
                     temp: resJson.tp1,
                     prec:resJson.rp1,
                     rain:resJson.wcd1,
                 },
                 {
                	 weather: resJson.wc2,
                     temp:resJson.tp2,
                     prec:resJson.rp2,
                     rain:resJson.wcd2,
                 },
                 {
                	 weather: resJson.wc3,
                     temp: resJson.tp3,
                     prec:resJson.rp3,
                     rain:resJson.wcd3,
                 },
                 {
                	 weather: resJson.wc4,
                     temp: resJson.tp4,
                     prec:resJson.rp4,
                     rain:resJson.wcd4,
                 },
                 {
                	 weather: resJson.wc5,
                     temp: resJson.tp5,
                     prec:resJson.rp5,
                     rain:resJson.wcd5,
                 },
                 {
                     weather: resJson.wc6,
                     temp: resJson.tp6,
                     rain:resJson.wcd6,
                     prec:resJson.rp6,
 
                 },
                 {
                     weather: resJson.wc7,
                     temp:resJson.tp7,
                     prec:resJson.rp7,
                     rain:resJson.wcd7,
                 }
             ];
        	 const weatherListTag = document.getElementById("weather-list");
             let innerHTML = '';
             var datetoday = new Date();
             var startdate = datetoday;
             var month = ('0' + (datetoday.getMonth() + 1)).slice(-2);
             var date = ('0' + datetoday.getDate()).slice(-2);
             
             var arrDayStr = ['일','월','화','수','목','금','토']; 
             var today = datetoday.getDay()
             let zerodate = 0;
             
        	 for(let i = 0; i<8;i++){
        		const weatherData = data[i];
        		const precarray = (weatherData.prec).split('|',2);
        		const temparray = (weatherData.temp).split('|',2);
        		
        		const temp = temparray[0];
        		const temp2 = temparray[1];
            	const prec = temparray[0];
            	const prec2 = temparray[1];
            	;
            	const rainarray = (weatherData.rain).split('|',2);
            	const rain = rainarray[0];
            	const rain2 = rainarray[1];
            	const weatherarray = (weatherData.weather).split('|',2);
            	const weather1 = (rain == "WB09") ? rain :weatherarray[0];
            	const weather2 = (rain2 == "WB09") ? rain2 :weatherarray[1]
            	const imgSrc = getImgSrc(weather1);
        		const imgSrc2 = getImgSrc(weather2);
        		
        		
        		
        		
            	var dt = ("0" + (datetoday.getDate()+i)).slice(-2);
            	var arraydate = today+i;
      			
            	var td = (arraydate > 6) ? zerodate++ : arraydate;
            	
            	var weatherscore = (weather1 == "WB01") ? 9 :
            					   (weather1 == "WB02") ? 8 : 
            				       (weather1 == "WB03") ? 7.5 : 
            				       (weather1 == "WB04") ? 7 : 
            				       (weather1 == "WB09") ? 5 : 
            				        "error" ;
            	weatherscore =(weatherscore == "error") ? "error" : 
            			      (weather2 == "WB01") ? weatherscore+1 :
            			      (weather2 == "WB02") ? weatherscore : 
            			      (weather2 == "WB03") ? weatherscore -0.5 : 
            			      (weather2 == "WB04") ? weatherscore -1 : 
            			      (weather2 == "WB09") ? weatherscore -3: 
            			       error ;
        
	       		console.log(i+"일차"+weatherscore);
				weatherScoreArray[i] = weatherscore;
	      		
	      		
          	 	innerHTML += ` 
               		
          	 		<div class="flex flex-row  h-full w-32 mr-10 pt-10 items-center justify-center ">
            		<div class="flex flex-col w-32">
		               <label for="" class="block text-lg font-bold text-gray-900 mx-auto"><span class="mx-auto text-xl"><span class="flex justify-center">${"${arrDayStr[td]}"}</span><br>${"${month}.${dt}"}</label>
		                <div class="h-auto w-auto flex flex-row items-center mt-5 justify-center ">
		                    <img src=${"${imgSrc}"} class="w-16 h-16">
		                    <img src=${"${imgSrc2}"} class="w-16 h-16">
		                </div>
		                <div class="flex flex-row mx-auto items-center mt-3 justify-center">
		                    <span class="text-[#45A6FF] font-md text-2xl">${"${temp}"}°</span>
		                    <span class="text-[#959FA9] text-2xl">/</span>
		                    <span class="text-[#F42E2E] font-bold text-2xl">${"${temp2}"}°</span>
		                </div>
		                <div class="flex flex-row mx-auto items-center mt-5 font-bold justify-center">
		                    <span class="text-[#3B9DE3] mr-2">${"${prec}%"}</span>
		              	      <span class="text-[#3B9DE3]">${"${prec2}%"}</span>
		                </div>
	                </div>
	             </div>
            `;
              
        	 }
            	
	      		
	      		for(let i = 0;i < weatherScoreArray.length; i++){
	      			sum += weatherScoreArray[i];
	      		}
	      		weatherScoreArray_avg = Math.floor(sum/weatherScoreArray.length);   
             	
                weatherListTag.innerHTML = innerHTML;

                function getImgSrc(weather){	
                    switch(weather){
                    	case 'WB01':
                    		return 'images/sunny.png';
                    		break;
                    	case 'WB02':
                    		return 'images/partly-cloudy.png';
                    		break;
                    	case 'WB03':
                    		return 'images/cloudy(2).png';
                    		break;
                    	case 'WB04':
                    		return 'images/cloudy.png';
                    		break;
                    	case 'WB09':
                    		return 'images/rain.png';
                    		break;
                        default :
                            return 'images/raindrop.png';
                 	}
                    
                }
                
                function getImgSrc2(weather2){	
                    switch(weather2){
                    	case 'WB01':
                    		return 'images/sunny.png';
                    		break;
                    	case 'WB02':
                    		return 'images/partly-cloudy.png';
                    		break;
                    	case 'WB03':
                    		return 'images/cloudy(2).png';
                    		break;
                    	case 'WB04':
                    		return 'images/cloudy.png';
                    		break;
                        default :
                            return 'images/raindrop.png';
                 	}
                    
                }
                
            }
      		
      	const weatherListTag = document.getElementById("weather-list");
			
			
  		
  		
		async function campdata() {
				 
        	
        const cres = await fetch('http://localhost:80/get/campinglist?place_name=');
        const campJson = await cres.json();
        
        
    	const campdata = [
        	{
        		placeaddress:campJson.item0.addressName,
              	placeid:campJson.item0.placeID,
              	placename:campJson.item0.placeName,
		        placeurl:campJson.item0.placeUrl,
       			placecategory: campJson.item0.placeCategoryDetail,
                placeregion:campJson.item0.region,
      
        	}
    	];
        const campListTag = document.getElementById("camp-list");
		
	            const innerHTML = ` 
			            	<div class="flex flex-col w-auto h-auto px-5 py-5 border-b-2 border-black-100 ">
			                <div class="flex flex-row place-content-between">
			                    <div class="flex flex-col">  
			                        <div class="text-sm">
			                            경기도/${"${campJson.item1.placeCategoryDetail}"}
			                        </div>
			                        <div class="mt-1  font-bold text-4xl">
			                            ${"${campJson.item1.placeName}"}
			                        </div>
			                        <div class="text-sm font-sm mb-10">
			                        	${"${campJson.item1.addressName}"}
			                        </div>
			                         <div>
			                        <button type="button" onclick ="location.href='${campJson.item1.placeUrl}'"
				                    	class= "flex text-white end-2.5 bottom-2.5 bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 font-sm rounded-lg text-sm px-4 py-2" >url</button>
			                    	</div>
			                    </div> 
			                   
			                    <div>
			                         <div class="text-4xl font-bold">
			                            총날씨점수
			                        </div>
			                        <div class="text-8xl items-center flex justify-center">
			                        	${"${weatherScoreArray_avg}"}
				                    </div>                                                
			                </div>
			                
			            </div>
        `;
        	
             
	            campListTag.innerHTML = innerHTML;
 
    }
      	getWeatherByRegion();
      	campdata();
        </script>
    </body>
</html>