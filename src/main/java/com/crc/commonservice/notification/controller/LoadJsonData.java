package com.crc.commonservice.notification.controller;

import com.crc.commonlib.logging.configuration.RequestLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crc.commonservice.notification.service.LogCheckServices;
import com.opensymphony.xwork2.util.ClassLoaderUtil;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/")
public class LoadJsonData {
	@Autowired
	private LogCheckServices logcheckService;
        
        public static final Logger logger = LoggerFactory.getLogger(LoadJsonData.class);
	
        @CrossOrigin
	@RequestLogger
	@PostMapping(path = "/",consumes ="application/json",produces = "application/json")
	@ApiOperation(value = "Load Data")
	@ApiResponses(value = {@ApiResponse(code = 200,message = "Load Data")})
	public ResponseEntity sendSms() {
             URL url = ClassLoaderUtil.getResource("logfile.txt", LoadJsonData.class);
            try {
                Path path = Paths.get(url.toURI());
                System.out.println("path--"+path);
                logcheckService.readEvents(path.toString());

                logger.info("Execution completed");
                
            } catch (URISyntaxException ex) {
                java.util.logging.Logger.getLogger(LoadJsonData.class.getName()).log(Level.SEVERE, null, ex);
                return new ResponseEntity<>("Data File Not Found ",HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>("data Insertion completed successfully ",HttpStatus.OK);
	}
}
