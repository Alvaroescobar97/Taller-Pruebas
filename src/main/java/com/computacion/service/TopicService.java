package com.computacion.service;

import java.util.Optional;

import com.computacion.model.TsscTopic;

public interface TopicService {

	public boolean save(TsscTopic topic);

	public boolean existById(long id);

	public Optional<TsscTopic> findById(long id);

}
