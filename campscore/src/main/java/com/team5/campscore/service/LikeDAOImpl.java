package com.team5.campscore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team5.campscore.dao.LikeDAO;
import com.team5.campscore.model.LikeDTO;

//@Service
public class LikeDAOImpl implements LikeDAO {
	//@Autowired
	LikeDAO dao;
	
	public int insertLike(LikeDTO likeDTO){
		try {
			dao.insertLike(likeDTO);
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
		return 1;
	}
	
	public int deleteLike(LikeDTO likeDTO){
		
		return 1;
	}
}
