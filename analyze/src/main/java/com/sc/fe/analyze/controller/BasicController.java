package com.sc.fe.analyze.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sc.fe.analyze.data.entity.Services;
import com.sc.fe.analyze.service.BaseService;

@RestController
@CrossOrigin(origins = "*")
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
	public Services getServices(@PathVariable("id") int id) {
		//service for given ID
		return baseService.getServiceRepo().findById(id).get();
	}
	
	
	@PostMapping(path="/services/{id}/{name}")
	public void getServices(@PathVariable("id") int id, @PathVariable("name") String name) {
		//Update service name in DB for give id
		
	}
	
	
}
