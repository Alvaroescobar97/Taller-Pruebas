package com.computacion.integracion;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.computacion.model.TsscTopic;
import com.computacion.service.TopicServiceImp;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class TopicServiceImpTestInt {

	@Autowired
	TopicServiceImp topicService;
	TsscTopic tsscTopic_1,tsscTopic_2,tsscTopic_3;
	

	@BeforeEach
	void setUp(){
		tsscTopic_1 = new TsscTopic();
		tsscTopic_1.setDefaultGroups(10);
		tsscTopic_1.setDefaultSprints(12);
		
		tsscTopic_2 = new TsscTopic();
		tsscTopic_2.setDefaultGroups(15);
		tsscTopic_2.setDefaultSprints(8);
		
		tsscTopic_3 = new TsscTopic();
		tsscTopic_3.setDefaultGroups(20);
		tsscTopic_3.setDefaultSprints(35);
	}
	
}
