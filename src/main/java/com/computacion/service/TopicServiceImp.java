package com.computacion.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.computacion.model.TsscTopic;
import com.computacion.repository.TopicRepo;

@Service
public class TopicServiceImp implements TopicService {

	private TopicRepo repo;
	
	@Autowired
	public TopicServiceImp(TopicRepo repo) {
		this.repo=repo;
	}
	
	@Override
	public boolean save(TsscTopic topic) {
		if(topic == null)
			return false;
		if(topic.getDefaultGroups() > 0 && topic.getDefaultSprints() > 0) {
			repo.save(topic);
			return true;
		}
		else {
			return false;
		}
	}
	
	@Override
	public boolean existById(long id) {
		return repo.existsById(id);
	}
	
	@Override
	public Optional<TsscTopic> findById(long id) {
		return repo.findById(id);
	}
	
}
