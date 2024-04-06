package com.team5.campscore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team5.campscore.dao.CampingDAO;
import com.team5.campscore.model.Camping;

@Service
public class CampingDAOImpl  implements CampingDAO {
	@Autowired
	CampingDAO dao;
	
	public int insertCamping(Camping camping)  {
		int returnVal=0;
		
		returnVal=dao.insertCamping(camping);
		
		return returnVal;
	}
}
