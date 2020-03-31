package com.computacion.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.computacion.model.TsscGame;
import com.computacion.model.TsscStory;
import com.computacion.repository.GameRepo;
import com.computacion.repository.TopicRepo;
import com.computacion.model.TsscTimecontrol;

@Service
public class GameServiceImp implements GameService {

	private GameRepo gameRepo;
	private TopicRepo topicRepo;

	
	@Autowired
	public GameServiceImp(GameRepo gameRepo,TopicRepo topicRepo) {
		this.gameRepo=gameRepo;
		this.topicRepo=topicRepo;
	}
	
	@Override
	public boolean save(TsscGame game) {
		boolean check= false;
		if(game == null) {
			check= false;
		}
		else {
			if(game.getNGroups() > 0 && game.getNSprints() > 0) {
				if(game.getTsscTopic() == null) {
					gameRepo.save(game);
					check= true;
				}
			}
		}
		return check;
	}
	
	@Override
	public boolean save(TsscGame game, long id) {
		if(game == null) {
					
		}
		else {
			if(game.getNGroups() > 0 && game.getNSprints() > 0) {
				if(game.getTsscTopic() == null) {
					gameRepo.save(game);
					return true;
				}
				else {
					if(topicRepo.existsById(id)) {
						gameRepo.save(game);
						return true;
					}
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
	public Optional<TsscGame> findById(long id) {
		return gameRepo.findById(id);
	}

	@Override
	public boolean save2(TsscGame game, long id) {
		boolean check=false;
			if(game == null) {
				
			}
			else {
				if(game.getNGroups() > 0 && game.getNSprints() > 0) {
					if(game.getTsscTopic() == null) {
						gameRepo.save(game);
						check=true;
					}
					else {
						if(topicRepo.existsById(id)) {
							List<TsscTimecontrol> listTimecontrol = game.getTsscTimecontrols();
							game.getTsscTimecontrols().addAll(listTimecontrol);
							List<TsscStory> lisStories = game.getTsscTopic().getTsscStories();
							game.getTsscStories().addAll(lisStories);
							gameRepo.save(game);
							check=true;
						}
					}
				}
			}
			return check;
	}
}
