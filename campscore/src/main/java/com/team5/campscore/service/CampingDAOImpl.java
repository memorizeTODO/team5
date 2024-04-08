package com.team5.campscore.service;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team5.campscore.dao.CampingDAO;
import com.team5.campscore.model.Camping;

@Service
public class CampingDAOImpl  implements CampingDAO {
	@Autowired
	CampingDAO dao;
	
	public int insertCamping(Camping camping)  {
		int returnValue = 1;
		
		returnValue=dao.insertCamping(camping);
		
		return returnValue;
	}
	public List<Camping> getCampingListByRegion(int start, String region) {
		
		return dao.getCampingListByRegion(start,region);
	};
}
