/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crc.commonservice.notification.serviceimpl;

import com.crc.commonservice.notification.dto.AllLogEvent;
import com.crc.commonservice.notification.model.EventDetails;
import com.crc.commonservice.notification.service.LogCheckServices;
import com.google.gson.Gson;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 *
 * @author lenovo
 */
public class LogCheckServicesImpl implements LogCheckServices{
    	private static final SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        public static final Logger logger = LoggerFactory.getLogger(LogCheckServicesImpl.class);

    //TODO:the map will contain n/2 element in the worse case, it's better to find 
    //another solution for big files when we need a lot of memory
        @Override
        public void readEvents(String path) {
	    Map<String,AllLogEvent> map = new HashMap<>();
	    Session session = sessionFactory.openSession();
	    session.beginTransaction();
	    
	    try (Stream<String> lines = Files.lines(Paths.get(path))) {
	    	  lines.forEach(line -> process(map,line,session));
	    }catch (IOException e) {
			logger.error("File not found exception: {}",e.getMessage());
		}
	    
	    session.getTransaction().commit();
	    session.close();
	    sessionFactory.close();
	}

        @Override
	public void process(Map<String,AllLogEvent >map, String line, Session session) {
            AllLogEvent logEvent = new Gson().fromJson(line, AllLogEvent.class);
            AllLogEvent previus = map.putIfAbsent(logEvent.getId(),logEvent);
            if(previus!= null) {
    		EventDetails event = getEventFromLogs(previus,logEvent);
    		logger.debug(event.toString());
    		//TODO: manage database exceptions
	    	session.persist(event);
	    	map.remove(previus.getId());
    	}	    				    	
    }
	@Override
	public EventDetails getEventFromLogs(AllLogEvent event_1, AllLogEvent event_2) {
		EventDetails event = new EventDetails();
		event.setId(event_1.getId());
		event.setDuration(calculateTime(event_1.getTimestamp(),event_2.getTimestamp()));
		event.setHost(event_1.getHost());
		event.setType(event_1.getType());
		event.setAlert(event.getDuration()>4);
		return event;
	}
	
	private long calculateTime(long l1, long l2) {
		return l1 > l2 ?  l1-l2 : l2-l1;
	}
}
