package com.sc.fe.analyze.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.sc.fe.analyze.data.entity.Services;
import com.sc.fe.analyze.service.BaseService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path="/api")
public class BasicController {

	@Autowired
	BaseService baseService;
	
	@GetMapping(path="/services")
	public List<Services> getServices() {
		//List all services
		List<Services> services = null;
		
		return services;
	}
	
	@GetMapping(path="/services/{id}")
	public Services getServices(@PathParam("id") int id) {
		//service for given ID
		return null;
	}
	
	
	@PostMapping(path="/services/{id}/{name}")
	public void getServices(@PathParam("id") int id, @PathParam("name") String name) {
		//Update service name in DB for give id
		
	}
	
	
}
