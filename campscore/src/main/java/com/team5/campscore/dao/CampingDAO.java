package com.team5.campscore.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.team5.campscore.model.Camping;

@Mapper
public interface CampingDAO {
	public int insertCamping(Camping c); 
	public List<Camping> getCampingListByRegion(@Param("start")int start, @Param("region")String region,);
}
