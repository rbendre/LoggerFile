package com.creditsuisse.LoggerFile.service;

import com.creditsuisse.LoggerFile.DAO.EventsRepository;
import com.creditsuisse.LoggerFile.Models.Event;
import com.creditsuisse.LoggerFile.Service.EventLoggerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest(classes = EventLoggerService.class)
@RunWith(MockitoJUnitRunner.class)
public class LogServiceTestEvent {

	@Mock
	EventsRepository eventsRepositoryMock;

	@InjectMocks
	EventLoggerService eventLoggerService;

	@Test
	public void testParseAndStoreEvents() {
		String absolutePathFile = new File("src/test/resources/test-input.txt").getAbsolutePath();
		Event eventEntity = new Event();
		eventEntity.setEventId("scsmbstgra");
		eventEntity.setHost("12345");
		eventEntity.setType("APPLICATION_LOG");
		eventEntity.setLongEventFlag(true);
		eventEntity.setTimestampDifference(5);
		Mockito.when(eventsRepositoryMock.save(eventEntity)).thenReturn(eventEntity);
		eventLoggerService.parseAndStoreEvents(absolutePathFile);
	}

	@Test
	public void testPrintAllDatabaseEvents() {
		List<Event> eventList = new ArrayList<>();
		Event e1 = new Event(1, "scsmbstgrz", 2, "APPLICATION_LOG", "12345", false);

		eventList.add(e1);
		Mockito.when(eventsRepositoryMock.findAll()).thenReturn(eventList);
		eventLoggerService.printAllDatabaseEvents();
	}

}