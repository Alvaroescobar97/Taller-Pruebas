package com.computacion.repository;

import java.util.function.BooleanSupplier;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.computacion.model.TsscStory;
import com.computacion.model.TsscTopic;

@Repository
public interface TopicRepo extends CrudRepository<TsscTopic, Long>{

}
