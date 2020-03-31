package com.computacion.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.junit.jupiter.api.*;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;

import com.computacion.model.TsscGame;
import com.computacion.model.TsscStory;
import com.computacion.model.TsscTimecontrol;
import com.computacion.model.TsscTopic;
import com.computacion.repository.GameRepo;
import com.computacion.repository.StoryRepo;
import com.computacion.repository.TopicRepo;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;


import lombok.extern.java.Log;

@Log
@RunWith(MockitoJUnitRunner.class)
class StoryServiceImpTest {

	@Mock
	private GameRepo gameRepository;
	
	@Mock
	private StoryRepo storyRepository;
	
	@InjectMocks
	private StoryServiceImp storyService;
	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Nested
	@DisplayName("Test StoryServiceImp Save()")
	class GuardadoTest{
		long id;
		@BeforeEach
		public void setUp(){
			log.info("Test Save()");
			id=0;
		}
		
		@DisplayName("Story = null")
		@Test
		public void testStoryNull() {
			TsscStory aux = null;
			TsscGame game = new TsscGame();
			assertFalse(storyService.save(aux, id));
			Mockito.verifyZeroInteractions(gameRepository);
		}
		
		@DisplayName("Sprints = 0, game doesnt exists")
		@Test
		public void testStorySprintsEqualZero() {
			TsscStory aux = new TsscStory();
			TsscGame game = new TsscGame();
			aux.setBusinessValue(BigDecimal.valueOf(1));
			aux.setPriority(BigDecimal.valueOf(1));
			aux.setInitialSprint(BigDecimal.ZERO);
			game.setId(id);
			when(gameRepository.existsById(id)).thenReturn(false);
			when(gameRepository.findById(id)).thenReturn(null);
			aux.setTsscGame(game);
			assertFalse(storyService.save(aux, id));
			Mockito.verifyZeroInteractions(gameRepository);
		}
		
		@DisplayName("Sprints are negative, game doesnt exists")
		@Test
		public void testStorySprintsNegative() {
			TsscStory aux = new TsscStory();
			TsscGame game = new TsscGame();
			aux.setBusinessValue(BigDecimal.valueOf(1));
			aux.setPriority(BigDecimal.valueOf(1));
			aux.setInitialSprint(BigDecimal.valueOf(-1));
			game.setId(id);
			when(gameRepository.existsById(id)).thenReturn(false);
			when(gameRepository.findById(id)).thenReturn(null);
			aux.setTsscGame(game);
			assertFalse(storyService.save(aux, id));
			Mockito.verifyZeroInteractions(gameRepository);
		}
		
		@DisplayName("bussinesValue= 0, game doesn't exists")
		@Test
		public void testStoryBussinesEqualZero() {
			TsscStory aux = new TsscStory();
			TsscGame game = new TsscGame();
			aux.setBusinessValue(BigDecimal.ZERO);
			aux.setPriority(BigDecimal.valueOf(1));
			aux.setInitialSprint(BigDecimal.valueOf(1));
			game.setId(id);
			when(gameRepository.existsById(id)).thenReturn(false);
			when(gameRepository.findById(id)).thenReturn(null);
			aux.setTsscGame(game);
			assertFalse(storyService.save(aux, id));
			Mockito.verifyZeroInteractions(gameRepository);
		}
		
		@DisplayName("bussinesValue is negative, game doesn't exists")
		@Test
		public void testStoryBussinesNegative() {
			TsscStory aux = new TsscStory();
			TsscGame game = new TsscGame();
			aux.setBusinessValue(BigDecimal.valueOf(-1));
			aux.setPriority(BigDecimal.valueOf(1));
			aux.setInitialSprint(BigDecimal.valueOf(1));
			game.setId(id);
			when(gameRepository.existsById(id)).thenReturn(false);
			when(gameRepository.findById(id)).thenReturn(null);
			aux.setTsscGame(game);
			assertFalse(storyService.save(aux, id));
			Mockito.verifyZeroInteractions(gameRepository);
		}
		
		@DisplayName("priority = 0, game doesnt exists")
		@Test
		public void testStoryPriorityEqualZero() {
			TsscStory aux = new TsscStory();
			TsscGame game = new TsscGame();
			aux.setBusinessValue(BigDecimal.valueOf(1));
			aux.setPriority(BigDecimal.ZERO);
			aux.setInitialSprint(BigDecimal.valueOf(1));
			game.setId(id);
			when(gameRepository.existsById(id)).thenReturn(false);
			when(gameRepository.findById(id)).thenReturn(null);
			aux.setTsscGame(game);
			assertFalse(storyService.save(aux, id));
			Mockito.verifyZeroInteractions(gameRepository);
		}
		
		@DisplayName("priority is negative, game doesnt exists")
		@Test
		public void testStoryPriorityNegative() {
			TsscStory aux = new TsscStory();
			TsscGame game = new TsscGame();
			aux.setBusinessValue(BigDecimal.valueOf(1));
			aux.setPriority(BigDecimal.valueOf(-1));
			aux.setInitialSprint(BigDecimal.valueOf(1));
			game.setId(id);
			when(gameRepository.existsById(id)).thenReturn(false);
			when(gameRepository.findById(id)).thenReturn(null);
			aux.setTsscGame(game);
			assertFalse(storyService.save(aux, id));
			Mockito.verifyZeroInteractions(gameRepository);
		}
		
		@DisplayName("all attribute are valid, game doesnt exists")
		@Test
		public void testStoryAllValidWithoutGame() {
			TsscStory aux = new TsscStory();
			TsscGame game = new TsscGame();
			aux.setBusinessValue(BigDecimal.valueOf(1));
			aux.setPriority(BigDecimal.valueOf(1));
			aux.setInitialSprint(BigDecimal.valueOf(1));
			game.setId(id);
			when(gameRepository.existsById(id)).thenReturn(false);
			when(gameRepository.findById(id)).thenReturn(null);
			aux.setTsscGame(game);
			assertFalse(storyService.save(aux, id));
		}
		
		@DisplayName("Sprints = 0, game exists")
		@Test
		public void testStorySprintsEqualZeroWithGame() {
			TsscStory aux = new TsscStory();
			TsscGame game = new TsscGame();
			aux.setBusinessValue(BigDecimal.valueOf(1));
			aux.setPriority(BigDecimal.valueOf(1));
			aux.setInitialSprint(BigDecimal.ZERO);
			game.setId(id);
			when(gameRepository.existsById(id)).thenReturn(true);
			when(gameRepository.findById(id)).thenReturn(Optional.of(game));
			aux.setTsscGame(game);
			assertFalse(storyService.save(aux, id));
			Mockito.verifyZeroInteractions(gameRepository);
		}
		
		@DisplayName("Sprints are negative, game exists")
		@Test
		public void testStorySprintsNegativeWithGame() {
			TsscStory aux = new TsscStory();
			TsscGame game = new TsscGame();
			aux.setBusinessValue(BigDecimal.valueOf(1));
			aux.setPriority(BigDecimal.valueOf(1));
			aux.setInitialSprint(BigDecimal.valueOf(-1));
			game.setId(id);
			when(gameRepository.existsById(id)).thenReturn(true);
			when(gameRepository.findById(id)).thenReturn(Optional.of(game));
			aux.setTsscGame(game);
			assertFalse(storyService.save(aux, id));
			Mockito.verifyZeroInteractions(gameRepository);
		}
		
		@DisplayName("bussinesValue = 0, game exists")
		@Test
		public void testStoryBussinesEqualZeroWithGame() {
			TsscStory aux = new TsscStory();
			TsscGame game = new TsscGame();
			aux.setBusinessValue(BigDecimal.ZERO);
			aux.setPriority(BigDecimal.valueOf(1));
			aux.setInitialSprint(BigDecimal.valueOf(1));
			game.setId(id);
			when(gameRepository.existsById(id)).thenReturn(true);
			when(gameRepository.findById(id)).thenReturn(Optional.of(game));
			aux.setTsscGame(game);
			assertFalse(storyService.save(aux, id));
			Mockito.verifyZeroInteractions(gameRepository);
		}
		
		@DisplayName("bussinesValue is negative, game exists")
		@Test
		public void testStoryBussinesNegativeWithGame() {
			TsscStory aux = new TsscStory();
			TsscGame game = new TsscGame();
			aux.setBusinessValue(BigDecimal.valueOf(-1));
			aux.setPriority(BigDecimal.valueOf(1));
			aux.setInitialSprint(BigDecimal.valueOf(1));
			game.setId(id);
			when(gameRepository.existsById(id)).thenReturn(true);
			when(gameRepository.findById(id)).thenReturn(Optional.of(game));
			aux.setTsscGame(game);
			assertFalse(storyService.save(aux, id));
			Mockito.verifyZeroInteractions(gameRepository);
		}
		
		@DisplayName("priority = 0, game exists")
		@Test
		public void testStoryPriorityEqualZeroWithGame() {
			TsscStory aux = new TsscStory();
			TsscGame game = new TsscGame();
			aux.setBusinessValue(BigDecimal.valueOf(1));
			aux.setPriority(BigDecimal.ZERO);
			aux.setInitialSprint(BigDecimal.valueOf(1));
			game.setId(id);
			when(gameRepository.existsById(id)).thenReturn(true);
			when(gameRepository.findById(id)).thenReturn(Optional.of(game));
			aux.setTsscGame(game);
			assertFalse(storyService.save(aux, id));
			Mockito.verifyZeroInteractions(gameRepository);
		}
		
		@DisplayName("priority is negative, game exists")
		@Test
		public void testStoryPriorityNegativeWithGame() {
			TsscStory aux = new TsscStory();
			TsscGame game = new TsscGame();
			aux.setBusinessValue(BigDecimal.valueOf(1));
			aux.setPriority(BigDecimal.valueOf(-1));
			aux.setInitialSprint(BigDecimal.valueOf(1));
			game.setId(id);
			when(gameRepository.existsById(id)).thenReturn(true);
			when(gameRepository.findById(id)).thenReturn(Optional.of(game));
			aux.setTsscGame(game);
			assertFalse(storyService.save(aux, id));
			Mockito.verifyZeroInteractions(gameRepository);
		}
		
		@DisplayName("all attribute are valid, game exists")
		@Test
		public void testStoryAllValidWithGame() {
			TsscStory aux = new TsscStory();
			TsscGame game = new TsscGame();
			aux.setBusinessValue(BigDecimal.valueOf(1));
			aux.setPriority(BigDecimal.valueOf(1));
			aux.setInitialSprint(BigDecimal.valueOf(1));
			game.setId(id);
			when(gameRepository.existsById(id)).thenReturn(true);
			when(gameRepository.findById(id)).thenReturn(Optional.of(game));
			aux.setTsscGame(game);
			assertTrue(storyService.save(aux, id));
		}
	}
	
	
	@Nested
	@DisplayName("Test StoryServiceImp Save() for Update")
	class ActualizarTest{
		
		long id;
		@BeforeEach
		public void setUp(){
			log.info("Teste Save() for update");
			id=0;
		}
		
		@DisplayName("Invalid change of initialSprints to 0")
		@Test
		public void testStoryUpdateInitiaSprintstoZero() {
			TsscStory aux = new TsscStory();
			TsscGame game = new TsscGame();
			aux.setBusinessValue(BigDecimal.valueOf(1));
			aux.setPriority(BigDecimal.valueOf(1));
			aux.setInitialSprint(BigDecimal.valueOf(1));
			game.setId(id);
			when(gameRepository.existsById(id)).thenReturn(true);
			when(gameRepository.findById(id)).thenReturn(Optional.of(game));
			aux.setTsscGame(game);
			assertTrue(storyService.save(aux, id));
			
			aux.setBusinessValue(BigDecimal.valueOf(1));
			aux.setPriority(BigDecimal.valueOf(1));
			aux.setInitialSprint(BigDecimal.valueOf(0));
			
			assertFalse(storyService.save(aux, id));
		}
		
		@DisplayName("Invalid change of initialSprints to -1")
		@Test
		public void testStoryUpdateInitiaSprintstoNegative() {
			TsscStory aux = new TsscStory();
			TsscGame game = new TsscGame();
			aux.setBusinessValue(BigDecimal.valueOf(1));
			aux.setPriority(BigDecimal.valueOf(1));
			aux.setInitialSprint(BigDecimal.valueOf(1));
			game.setId(id);
			when(gameRepository.existsById(id)).thenReturn(true);
			when(gameRepository.findById(id)).thenReturn(Optional.of(game));
			aux.setTsscGame(game);
			assertTrue(storyService.save(aux, id));
			
			aux.setBusinessValue(BigDecimal.valueOf(1));
			aux.setPriority(BigDecimal.valueOf(1));
			aux.setInitialSprint(BigDecimal.valueOf(-1));
			
			assertFalse(storyService.save(aux, id));
		}
		
		@DisplayName("Invalid change of bussinesValue to 0")
		@Test
		public void testStoryUpdateBussinesValuetoZero() {
			TsscStory aux = new TsscStory();
			TsscGame game = new TsscGame();
			aux.setBusinessValue(BigDecimal.valueOf(1));
			aux.setPriority(BigDecimal.valueOf(1));
			aux.setInitialSprint(BigDecimal.valueOf(1));
			game.setId(id);
			when(gameRepository.existsById(id)).thenReturn(true);
			when(gameRepository.findById(id)).thenReturn(Optional.of(game));
			aux.setTsscGame(game);
			assertTrue(storyService.save(aux, id));
			
			aux.setBusinessValue(BigDecimal.valueOf(0));
			aux.setPriority(BigDecimal.valueOf(1));
			aux.setInitialSprint(BigDecimal.valueOf(1));
			
			assertFalse(storyService.save(aux, id));
		}
		
		@DisplayName("Invalid change of bussinesValue to -1")
		@Test
		public void testStoryUpdateBussinesValuetoNegative() {
			TsscStory aux = new TsscStory();
			TsscGame game = new TsscGame();
			aux.setBusinessValue(BigDecimal.valueOf(1));
			aux.setPriority(BigDecimal.valueOf(1));
			aux.setInitialSprint(BigDecimal.valueOf(1));
			game.setId(id);
			when(gameRepository.existsById(id)).thenReturn(true);
			when(gameRepository.findById(id)).thenReturn(Optional.of(game));
			aux.setTsscGame(game);
			assertTrue(storyService.save(aux, id));
			
			aux.setBusinessValue(BigDecimal.valueOf(-1));
			aux.setPriority(BigDecimal.valueOf(1));
			aux.setInitialSprint(BigDecimal.valueOf(1));
			
			assertFalse(storyService.save(aux, id));
		}
		
		@DisplayName("Invalid change of priority to 0")
		@Test
		public void testStoryUpdatePrioritytoZero() {
			TsscStory aux = new TsscStory();
			TsscGame game = new TsscGame();
			aux.setBusinessValue(BigDecimal.valueOf(1));
			aux.setPriority(BigDecimal.valueOf(1));
			aux.setInitialSprint(BigDecimal.valueOf(1));
			game.setId(id);
			when(gameRepository.existsById(id)).thenReturn(true);
			when(gameRepository.findById(id)).thenReturn(Optional.of(game));
			aux.setTsscGame(game);
			assertTrue(storyService.save(aux, id));
			
			aux.setBusinessValue(BigDecimal.valueOf(1));
			aux.setPriority(BigDecimal.valueOf(0));
			aux.setInitialSprint(BigDecimal.valueOf(1));
			
			assertFalse(storyService.save(aux, id));
		}
		
		@DisplayName("Invalid change of priority to -1")
		@Test
		public void testStoryUpdatePrioritytoNegative() {
			TsscStory aux = new TsscStory();
			TsscGame game = new TsscGame();
			aux.setBusinessValue(BigDecimal.valueOf(1));
			aux.setPriority(BigDecimal.valueOf(1));
			aux.setInitialSprint(BigDecimal.valueOf(1));
			game.setId(id);
			when(gameRepository.existsById(id)).thenReturn(true);
			when(gameRepository.findById(id)).thenReturn(Optional.of(game));
			aux.setTsscGame(game);
			assertTrue(storyService.save(aux, id));
			
			aux.setBusinessValue(BigDecimal.valueOf(1));
			aux.setPriority(BigDecimal.valueOf(-1));
			aux.setInitialSprint(BigDecimal.valueOf(1));
			
			assertFalse(storyService.save(aux, id));
		}
		
		@DisplayName("Invalid change delete game")
		@Test
		public void testStoryUpdateDeleteGame() {
			TsscStory aux = new TsscStory();
			TsscGame game = new TsscGame();
			aux.setBusinessValue(BigDecimal.valueOf(1));
			aux.setPriority(BigDecimal.valueOf(1));
			aux.setInitialSprint(BigDecimal.valueOf(1));
			game.setId(id);
			when(gameRepository.existsById(id)).thenReturn(true);
			when(gameRepository.findById(id)).thenReturn(Optional.of(game));
			aux.setTsscGame(game);
			assertTrue(storyService.save(aux, id));
			
			aux.setTsscGame(null);
			assertFalse(storyService.save(aux, id));
		}
		
		@DisplayName("Valid change")
		@Test
		public void testStoryUpdateValidChange() {
			TsscStory aux = new TsscStory();
			TsscGame game = new TsscGame();
			aux.setBusinessValue(BigDecimal.valueOf(1));
			aux.setPriority(BigDecimal.valueOf(1));
			aux.setInitialSprint(BigDecimal.valueOf(1));
			game.setId(id);
			when(gameRepository.existsById(id)).thenReturn(true);
			when(gameRepository.findById(id)).thenReturn(Optional.of(game));
			aux.setTsscGame(game);
			assertTrue(storyService.save(aux, id));
			
			aux.setBusinessValue(BigDecimal.valueOf(2));
			aux.setPriority(BigDecimal.valueOf(2));
			aux.setInitialSprint(BigDecimal.valueOf(2));
			
			assertTrue(storyService.save(aux, id));
		}
	}

}
