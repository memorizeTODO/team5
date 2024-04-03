package com.team5.campscore.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
interface WeatherDAO {
	public int insert(); 
}
