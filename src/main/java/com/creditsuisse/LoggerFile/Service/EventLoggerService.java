package com.creditsuisse.LoggerFile.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.creditsuisse.LoggerFile.DAO.EventsRepository;
import com.creditsuisse.LoggerFile.Models.Event;
import com.creditsuisse.LoggerFile.Models.LogServerModel;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;



@Service
public class EventLoggerService {

    @Autowired
    EventsRepository eventsRepository;
    private  static  final Logger log = LoggerFactory.getLogger(EventLoggerService.class);

    /**
     * @param args - takes command line argument as file path
     */
    public void parseAndStoreEvents(String args) {

        Gson gson = new Gson();
        try {

            // Creating Parallel stream of data from file
            log.info("Reading file from the path to map into Java Object");

        	Path path = Paths.get(args);
			Stream<String> parallelStream = Files.lines(path).parallel();
			Stream<String> filteredStream = parallelStream.filter(x -> StringUtils.hasText(x));
			Stream<LogServerModel> stream = filteredStream.map(x -> gson.fromJson(x, LogServerModel.class));
			
            // Creating unique events map to store uniquely identified events from the
            // stream
            Map<String, LogServerModel> uniqueEvents = new HashMap<>();
            // Looping on every object from the stream
            log.info("Streaming over each event");
            stream.forEach(streamObj -> {
                boolean flag = false;
                long timeDifference = 0L;
                //Below condition is to check if there is invalid event based on event id
                if (streamObj.getId() != null) {
                    /*
                     * Checking if there is already an event in unique events map based on id. If
                     * not adding it to map
                     */
                    if (!uniqueEvents.containsKey(streamObj.getId())) {
                        uniqueEvents.put(streamObj.getId(), streamObj);
                    }
                    /*
                     * If key is not unique, calculate time difference and set boolean value for
                     * event alert
                     */
                    else {
                        LogServerModel singleLog = uniqueEvents.remove(streamObj.getId());
                        timeDifference = Math.abs(streamObj.getTimestamp() - singleLog.getTimestamp());

                        if (timeDifference > 4)
                            flag = true;

                    }

                    // Mapping a new event entity to store the details in events table
                    Event eventEntity = new Event();
                    eventEntity.setEventId(streamObj.getId());
                    eventEntity.setHost(streamObj.getHost());
                    eventEntity.setType(streamObj.getType());
                    eventEntity.setLongEventFlag(flag);
                    eventEntity.setTimestampDifference(timeDifference);
                    log.info("Saving the record into database");
                    // Saving the record in database
                    if(eventsRepository.save(eventEntity)!=null) {
                        log.info("Event saved into the database - " + eventEntity.getEventId());
                    }

                }else {
                    log.warn("Invalid event as Id is not available for the event - " +streamObj+" Ignoring the event and moving to next");
                }

            });

        } catch (IOException | IllegalArgumentException | JsonSyntaxException e) {
            log.error("Issue with reading the file", e);
        }

    }
    public void printAllDatabaseEvents() {
        //Getting all the attributes and printing them
        log.info("Printing the values in database");
        Iterable<Event> allEvents=eventsRepository.findAll();
        System.out.println("\n\n______________________________Result______________________________\n\n");
        allEvents.forEach(System.out::println);
        System.out.println("\n\n___________________________________________________________________\n\n");
    }
	
}
