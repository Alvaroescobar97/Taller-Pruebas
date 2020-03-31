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
import com.computacion.repository.TopicRepo;

import java.util.List;
import java.util.Optional;


import lombok.extern.java.Log;

@Log
@RunWith(MockitoJUnitRunner.class)
class GameServiceImpTest {

	@Mock
	private GameRepo gameRepository;
	
	@Mock
	private TopicRepo topicRepository;
	
	@InjectMocks
	private GameServiceImp gameService;
	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.initMocks(this);	
	}
	
	@Nested
	@DisplayName("Test GameServiceImp Save()")
	class GuardadoTest{
		
		@BeforeClass
		public void setUp(){
			log.info("Test Save()");
			gameRepository.deleteAll();
		}
		
		@DisplayName("Game = null")
		@Test
		public void testGameNull() {
			TsscGame aux = null;
			assertFalse(gameService.save(aux));
			Mockito.verifyZeroInteractions(gameRepository);
		}
		
		
		@DisplayName("Sprints = 0")
		@Test
		public void testGameSprintsEqualZero() {
			TsscGame aux = new TsscGame();
			aux.setNGroups(1);
			aux.setNSprints(0);
			assertFalse(gameService.save(aux));
			Mockito.verifyZeroInteractions(gameRepository);
		}
		
		@DisplayName("Sprints are negative")
		@Test
		public void testGameSprintsBelowZero() {
			TsscGame aux = new TsscGame();
			aux.setNGroups(1);
			aux.setNSprints(-1);
			assertFalse(gameService.save(aux));
			Mockito.verifyZeroInteractions(gameRepository);
		}
		
		@DisplayName("Groups = 0")
		@Test
		public void testGameGroupsEqualZero() {
			TsscGame aux = new TsscGame();
			aux.setNGroups(0);
			aux.setNSprints(1);
			assertFalse(gameService.save(aux));
			Mockito.verifyZeroInteractions(gameRepository);
		}
		
		@DisplayName("Groups are negative")
		@Test
		public void testGameGroupsBelowZero() {
			TsscGame aux = new TsscGame();
			aux.setNGroups(-1);
			aux.setNSprints(0);
			assertFalse(gameService.save(aux));
			Mockito.verifyZeroInteractions(gameRepository);
		}
		
		@DisplayName("Groups & Sprints = 0")
		@Test
		public void testGameBothEqualZero() {
			TsscGame aux = new TsscGame();
			aux.setNGroups(0);
			aux.setNSprints(0);
			assertFalse(gameService.save(aux));
			Mockito.verifyZeroInteractions(gameRepository);
		}
		
		@DisplayName("Groups & Sprints are negative")
		@Test
		public void testGameBothBelowZero() {
			TsscGame aux = new TsscGame();
			aux.setNGroups(-1);
			aux.setNSprints(-1);
			assertFalse(gameService.save(aux));
			Mockito.verifyZeroInteractions(gameRepository);
		}
		
		@DisplayName("Groups & Sprints are positive")
		@Test
		public void testGameBothAboveZero() {
			TsscGame aux = new TsscGame();
			aux.setNGroups(1);
			aux.setNSprints(1);
			assertTrue(gameService.save(aux));
		}
		
		@DisplayName("Topic is valid but doesn't exists")
		@Test
		public void testGameWithNonExistsTopic() {
			TsscTopic topic = new TsscTopic();
			topic.setDefaultGroups(1);
			topic.setDefaultSprints(1);
			long id = 1;
			topic.setId(id);
			when(topicRepository.existsById(id)).thenReturn(false);
			when(topicRepository.findById(id)).thenReturn(null);
			TsscGame aux = new TsscGame();
			aux.setNGroups(1);
			aux.setNSprints(1);
			aux.setTsscTopic(topic);
			assertFalse(gameService.save(aux));
		}
		
		@DisplayName("Topis is valid and exists")
		@Test
		public void testGameWithExistsTopic() {
			TsscTopic topic = new TsscTopic();
			topic.setDefaultGroups(1);
			topic.setDefaultSprints(1);
			long id = 1;
			topic.setId(id);
			topicRepository.save(topic);
			topic.setId(id);
			when(topicRepository.existsById(id)).thenReturn(true);
			when(topicRepository.findById(id)).thenReturn(Optional.of(topic));
			TsscGame aux = new TsscGame();
			aux.setNGroups(1);
			aux.setNSprints(1);
			aux.setTsscTopic(topic);
			assertTrue(gameService.save(aux, id));
		}
		
	}
	
	/////////////////////////////////////////////////////////
	
	@Nested
	@DisplayName("Test GameServiceImp Save() for Update")
	class ActualizarTest{
		
		TsscGame game;

		@BeforeEach
		public void setUp(){
			game = new TsscGame();
			game.setNGroups(1);
			game.setNSprints(1);

		}
		
		@DisplayName("Modify Sprint = 0")
		@Test
		public void testGameSprintsChangeZero() {
			assertTrue(gameService.save(game));
			TsscGame aux = game;
			aux.setNSprints(0);
			assertFalse(gameService.save(aux));
		}
		
		@DisplayName("Modify Sprints to negative")
		@Test
		public void testGameSprintsChangeNegative() {
			assertTrue(gameService.save(game));
			TsscGame aux = game;
			aux.setNSprints(-1);
			assertFalse(gameService.save(aux));
		}
		
		@DisplayName("Modify Groups = 0")
		@Test
		public void testGameGroupsChangeZero() {
			assertTrue(gameService.save(game));
			TsscGame aux = game;
			aux.setNGroups(0);
			assertFalse(gameService.save(aux));
		}
		
		@DisplayName("Modify Group to negative")
		@Test
		public void testGameGroupsChangeNegative() {
			assertTrue(gameService.save(game));
			TsscGame aux = game;
			aux.setNGroups(-1);
			assertFalse(gameService.save(aux));
		}
		
		@DisplayName("Valid Change Sprint")
		@Test
		public void testGameSprintsChangeValid() {
			assertTrue(gameService.save(game));
			TsscGame aux = game;
			aux.setNSprints(2);
			assertTrue(gameService.save(aux));
		}
		
		@DisplayName("Valid Change Groups")
		@Test
		public void testGameBothChangeValid() {
			assertTrue(gameService.save(game));
			TsscGame aux = game;
			aux.setNGroups(2);
			aux.setNSprints(2);
			assertTrue(gameService.save(aux));
		}
		
		@DisplayName("Topic is change for invalid value")
		@Test
		public void testGameWithNonExistsTopic() {
			TsscTopic topic = new TsscTopic();
			topic.setDefaultGroups(1);
			topic.setDefaultSprints(1);
			long id = 1;
			topic.setId(id);
			topicRepository.save(topic);
			topic.setId(id);
			when(topicRepository.existsById(id)).thenReturn(true);
			when(topicRepository.findById(id)).thenReturn(Optional.of(topic));
			TsscGame aux = new TsscGame();
			aux.setNGroups(1);
			aux.setNSprints(1);
			aux.setTsscTopic(topic);
			assertTrue(gameService.save(aux, id));
			topic.setDefaultGroups(0);
			topic.setDefaultSprints(0);
			id = 5;
			topic.setId(id);
			topicRepository.save(topic);
			when(topicRepository.existsById(id)).thenReturn(false);
			when(topicRepository.findById(id)).thenReturn(null);
			aux.setTsscTopic(topic);
			assertFalse(gameService.save(aux, id));
			
		}
		
		@DisplayName("Topis is change for valid value")
		@Test
		public void testGameWithExistsTopic() {
			TsscTopic topic = new TsscTopic();
			topic.setDefaultGroups(1);
			topic.setDefaultSprints(1);
			long id = 1;
			topic.setId(id);
			topicRepository.save(topic);
			topic.setId(id);
			when(topicRepository.existsById(id)).thenReturn(true);
			when(topicRepository.findById(id)).thenReturn(Optional.of(topic));
			TsscGame aux = new TsscGame();
			aux.setNGroups(1);
			aux.setNSprints(1);
			aux.setTsscTopic(topic);
			assertTrue(gameService.save(aux, id));
			topic.setDefaultGroups(2);
			topic.setDefaultSprints(2);
			id = 5;
			topic.setId(id);
			topicRepository.save(topic);
			when(topicRepository.existsById(id)).thenReturn(true);
			when(topicRepository.findById(id)).thenReturn(Optional.of(topic));
			aux.setTsscTopic(topic);
			assertTrue(gameService.save(aux, id));
		}
		
	}
	
	@Nested
	@DisplayName("Test GameServiceImp Save2()")
	class GuardadoTest2{
		long id;
		@BeforeClass
		public void setUp(){
			log.info("Test save2");
			gameRepository.deleteAll();
			id = 0;
		}
		
		@DisplayName("Game = null")
		@Test
		public void testGameNull() {
			TsscGame aux = null;
			assertFalse(gameService.save(aux));
			Mockito.verifyZeroInteractions(gameRepository);
		}
		
		
		@DisplayName("Sprints = 0")
		@Test
		public void testGameSprintsEqualZero() {
			TsscGame aux = new TsscGame();
			aux.setNGroups(1);
			aux.setNSprints(0);
			assertFalse(gameService.save2(aux, id));
			Mockito.verifyZeroInteractions(gameRepository);
		}
		
		@DisplayName("Sprints are negative")
		@Test
		public void testGameSprintsBelowZero() {
			TsscGame aux = new TsscGame();
			aux.setNGroups(1);
			aux.setNSprints(-1);
			assertFalse(gameService.save2(aux, id));
			Mockito.verifyZeroInteractions(gameRepository);
		}
		
		@DisplayName("Groups = 0")
		@Test
		public void testGameGroupsEqualZero() {
			TsscGame aux = new TsscGame();
			aux.setNGroups(0);
			aux.setNSprints(1);
			assertFalse(gameService.save2(aux, id));
			Mockito.verifyZeroInteractions(gameRepository);
		}
		
		@DisplayName("Groups is negative")
		@Test
		public void testGameGroupsBelowZero() {
			TsscGame aux = new TsscGame();
			aux.setNGroups(-1);
			aux.setNSprints(0);
			assertFalse(gameService.save2(aux, id));
			Mockito.verifyZeroInteractions(gameRepository);
		}
		
		@DisplayName("Groups & Sprints = 0")
		@Test
		public void testGameBothEqualZero() {
			TsscGame aux = new TsscGame();
			aux.setNGroups(0);
			aux.setNSprints(0);
			assertFalse(gameService.save2(aux, id));
			Mockito.verifyZeroInteractions(gameRepository);
		}
		
		@DisplayName("Groups & Sprints are negative")
		@Test
		public void testGameBothBelowZero() {
			TsscGame aux = new TsscGame();
			aux.setNGroups(-1);
			aux.setNSprints(-1);
			assertFalse(gameService.save2(aux, id));
			Mockito.verifyZeroInteractions(gameRepository);
		}
		
		@DisplayName("Groups & Sprints are positive")
		@Test
		public void testGameBothAboveZero() {
			TsscGame aux = new TsscGame();
			aux.setNGroups(1);
			aux.setNSprints(1);
			assertTrue(gameService.save2(aux, id));
		}
		
		@DisplayName("Topic is valid but doesent exists")
		@Test
		public void testGameWithNonExistsTopic() {
			TsscTopic topic = new TsscTopic();
			topic.setDefaultGroups(1);
			topic.setDefaultSprints(1);
			topic.setId(id);
			when(topicRepository.existsById(id)).thenReturn(false);
			when(topicRepository.findById(id)).thenReturn(null);
			TsscGame aux = new TsscGame();
			aux.setNGroups(1);
			aux.setNSprints(1);
			aux.setTsscTopic(topic);
			assertFalse(gameService.save(aux, id));
		}
		
		@DisplayName("Topis is valid and exists")
		@Test
		public void testGameWithExistsTopic() {
			TsscTopic topic = new TsscTopic();
			topic.setDefaultGroups(1);
			topic.setDefaultSprints(1);
			topic.setId(id);
			topicRepository.save(topic);
			topic.setId(id);
			when(topicRepository.existsById(id)).thenReturn(true);
			when(topicRepository.findById(id)).thenReturn(Optional.of(topic));
			TsscGame aux = new TsscGame();
			aux.setNGroups(1);
			aux.setNSprints(1);
			aux.setTsscTopic(topic);
			assertTrue(gameService.save(aux, id));
		}
		
	}
}
