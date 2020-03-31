package com.computacion.service;

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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;


import lombok.extern.java.Log;

@Log
@RunWith(MockitoJUnitRunner.class)
class TopicServiceImpTest {

	@Mock
	private TopicRepo topicRepository;

	@InjectMocks
	private TopicServiceImp topicService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.initMocks(this);
		
	}

	@Nested
	@DisplayName("Test TopicServiceImp Save()")
	class GuardadoTest{
		
		@BeforeClass
		public void setUp(){
			log.info("Test save()");
			topicRepository.deleteAll();
		}
		
		@DisplayName("Topic = null")
		@Test
		public void testTopicNull() {
			TsscTopic aux = null;
			assertFalse(topicService.save(aux));
			Mockito.verifyZeroInteractions(topicRepository);
		}
		
		
		@DisplayName("Sprints = 0")
		@Test
		public void testTopicSprintsEqualZero() {
			TsscTopic aux = new TsscTopic();
			aux.setDefaultGroups(1);
			aux.setDefaultSprints(0);
			assertFalse(topicService.save(aux));
			Mockito.verifyZeroInteractions(topicRepository);
		}
		
		@DisplayName("Sprints are negative")
		@Test
		public void testTopicSprintsBelowZero() {
			TsscTopic aux = new TsscTopic();
			aux.setDefaultGroups(1);
			aux.setDefaultSprints(-1);
			assertFalse(topicService.save(aux));
			Mockito.verifyZeroInteractions(topicRepository);
		}
		
		@DisplayName("Groups = 0")
		@Test
		public void testTopicGroupsEqualZero() {
			TsscTopic aux = new TsscTopic();
			aux.setDefaultGroups(0);
			aux.setDefaultSprints(1);
			assertFalse(topicService.save(aux));
			Mockito.verifyZeroInteractions(topicRepository);
		}
		
		@DisplayName("Groups are negative")
		@Test
		public void testTopicGroupsBelowZero() {
			TsscTopic aux = new TsscTopic();
			aux.setDefaultGroups(-1);
			aux.setDefaultSprints(0);
			assertFalse(topicService.save(aux));
			Mockito.verifyZeroInteractions(topicRepository);
		}
		
		@DisplayName("Groups & Srpints = 0")
		@Test
		public void testTopicBothEqualZero() {
			TsscTopic aux = new TsscTopic();
			aux.setDefaultGroups(0);
			aux.setDefaultSprints(0);
			assertFalse(topicService.save(aux));
			Mockito.verifyZeroInteractions(topicRepository);
		}
		
		@DisplayName("Groups & Srpints are negative")
		@Test
		public void testTopicBothBelowZero() {
			TsscTopic aux = new TsscTopic();
			aux.setDefaultGroups(-1);
			aux.setDefaultSprints(-1);
			assertFalse(topicService.save(aux));
			Mockito.verifyZeroInteractions(topicRepository);
		}
		
		@DisplayName("Groups & Srpints are positive")
		@Test
		public void testTopicBothAboveZero() {
			TsscTopic aux = new TsscTopic();
			aux.setDefaultGroups(1);
			aux.setDefaultSprints(1);
			assertTrue(topicService.save(aux));
		}
	}
	
	@Nested
	@DisplayName("Test TopicServiceImp Save() for Update")
	class ActualizarTest{
		
		TsscTopic topic;

		@BeforeEach
		public void setUp(){
			topic = new TsscTopic();
			topic.setDefaultGroups(1);
			topic.setDefaultSprints(1);

		}
		
		@DisplayName("Change Sprint = 0")
		@Test
		public void testTopicSprintsChangeZero() {
			assertTrue(topicService.save(topic));
			TsscTopic aux = topic;
			aux.setDefaultSprints(0);
			assertFalse(topicService.save(aux));
		}
		
		@DisplayName("Change Sprints to negative")
		@Test
		public void testTopicSprintsChangeNegative() {
			assertTrue(topicService.save(topic));
			TsscTopic aux = topic;
			aux.setDefaultSprints(-1);
			assertFalse(topicService.save(aux));
		}
		
		@DisplayName("Change Groups = 0")
		@Test
		public void testTopicGroupsChangeZero() {
			assertTrue(topicService.save(topic));
			TsscTopic aux = topic;
			aux.setDefaultGroups(0);
			assertFalse(topicService.save(aux));
		}
		
		@DisplayName("Change Group to negative")
		@Test
		public void testTopicGroupsChangeNegative() {
			assertTrue(topicService.save(topic));
			TsscTopic aux = topic;
			aux.setDefaultGroups(-1);
			assertFalse(topicService.save(aux));
		}
		
		@DisplayName("Valid Change Sprint")
		@Test
		public void testTopicSprintsChangeValid() {
			assertTrue(topicService.save(topic));
			TsscTopic aux = topic;
			aux.setDefaultSprints(2);
			assertTrue(topicService.save(aux));
		}
		
		@DisplayName("Valid Change Groups")
		@Test
		public void testTopicBothChangeValid() {
			assertTrue(topicService.save(topic));
			TsscTopic aux = topic;
			aux.setDefaultGroups(2);
			aux.setDefaultSprints(2);
			assertTrue(topicService.save(aux));
		}
	}

}
