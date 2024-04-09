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
	
	public void insertSight(SightDTO s){
		dao.insertSight(s);
	};
}
