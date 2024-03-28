package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Rank;
import com.example.demo.repository.RankRepository;

@Service
public class RankServiceImpl implements RankService{
	
	@Autowired
	private RankRepository rankRepository;
	
	@Override
	public Rank getRankById(Long id) {
		return rankRepository.getById(id); 
	}
}
