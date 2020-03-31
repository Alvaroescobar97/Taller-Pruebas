package com.computacion.service;

import java.util.Optional;

import com.computacion.model.TsscGame;

public interface GameService {

	public boolean save(TsscGame game);

	public boolean existById(long id);

	public Optional<TsscGame> findById(long id);

	public boolean save2(TsscGame game, long id);

	public boolean save(TsscGame game, long id);
}
