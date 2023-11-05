package com.example.secondservice;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@Slf4j
public class SecondServiceController {
	
	Environment env;

    @Autowired
    public SecondServiceController(Environment env) {
        this.env = env;
    }
	
    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome to the Second service.";
    }

    @GetMapping("/message")
    public String message(@RequestHeader("second-request") String header) {
    	System.out.println(header);
        return "Hello World in Second Service.";
    }

    
    @GetMapping("/check")
    public String check(HttpServletRequest request) {
        Enumeration<String> headers = request.getHeaderNames();
        Collections.list(headers).stream().forEach(name -> {
            Enumeration<String> values = request.getHeaders(name);
            Collections.list(values).stream().forEach(value -> System.out.println(name + "=" + value));
        });

       // log.info("Server port={}", request.getServerPort());

       // log.info("spring.cloud.client.hostname={}", env.getProperty("spring.cloud.client.hostname"));
       // log.info("spring.cloud.client.ip-address={}", env.getProperty("spring.cloud.client.ip-address"));

        return String.format("Hi, there. This is a message from Second Service on PORT %s"
                , env.getProperty("local.server.port"));
    }
    
}
