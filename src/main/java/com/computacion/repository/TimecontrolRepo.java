package com.computacion.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.computacion.model.TsscTimecontrol;
@Repository

public interface TimecontrolRepo extends CrudRepository<TsscTimecontrol, Long> {

}
