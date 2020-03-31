package com.computacion.service;

import java.util.Optional;

import com.computacion.model.TsscStory;

public interface StoryService {

	public boolean save(TsscStory story,long id);

	public boolean existById(long id);

	public Optional<TsscStory> findById(long id);
}
