package com.ia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ia.service.IAService;

@RestController
@RequestMapping(value = "/api/img")
public class IAController {

	@Autowired
	IAService iaService;
	
	
}
