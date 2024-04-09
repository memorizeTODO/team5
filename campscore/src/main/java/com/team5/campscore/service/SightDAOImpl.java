package com.team5.campscore.service;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team5.campscore.dao.SightDAO;
import com.team5.campscore.model.SightDTO;

@Service
public class SightDAOImpl {
	@Autowired
	private SightDAO dao;
	
	public int insertSight(SightDTO s){
		int returnVal =1;
		returnVal=dao.insertSight(s);
		System.out.println(returnVal);
		return returnVal;
	};
}
