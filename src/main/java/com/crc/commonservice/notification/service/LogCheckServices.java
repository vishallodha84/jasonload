/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crc.commonservice.notification.service;

import com.crc.commonservice.notification.dto.AllLogEvent;
import java.util.Map;
import org.hibernate.Session;
import com.crc.commonservice.notification.model.EventDetails;
/**
 *
 * @author lenovo
 */
public interface LogCheckServices {
    	public void readEvents(String path);
        public void process(Map<String,AllLogEvent >map, String line, Session session);
        public EventDetails getEventFromLogs(AllLogEvent event_1, AllLogEvent event_2);
        


}
