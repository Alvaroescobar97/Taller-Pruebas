package com.computacion.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.computacion.model.TsscSprint;
@Repository

public interface SprintRepo extends CrudRepository<TsscSprint, Long>{

}
