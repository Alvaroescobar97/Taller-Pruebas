package com.computacion.service;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.computacion.model.TsscStory;
import com.computacion.repository.GameRepo;
import com.computacion.repository.StoryRepo;

@Service
public class StoryServiceImp implements StoryService{

	private GameRepo gameRepo;
	private StoryRepo stroyRepo;
	
	@Override
	public boolean save(TsscStory story, long id) {
		if(story == null) {
			
		}
		else {
			if(story.getBusinessValue().compareTo(BigDecimal.ZERO)==1 
					&& story.getInitialSprint().compareTo(BigDecimal.ZERO)==1 
					&& story.getPriority().compareTo(BigDecimal.ZERO)==1
					&& story.getTsscGame()!= null) {
				if(gameRepo.existsById(id)) {
					stroyRepo.save(story);
					return true;
				}
			}
		}
		return false;
		
	}

	@Override
	public boolean existById(long id) {
		return gameRepo.existsById(id);
	}
	
	@Override
	public Optional<TsscStory> findById(long id) {
		return stroyRepo.findById(id);
	}

}
