package com.computacion.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.computacion.model.TsscStory;

@Repository

public interface StoryRepo extends CrudRepository<TsscStory, Long>{

}
