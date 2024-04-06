package com.team5.campscore.dao;

import org.apache.ibatis.annotations.Mapper;

import com.team5.campscore.model.Camping;

@Mapper

public interface CampingDAO {
	public int insertCamping(Camping c); 
}
