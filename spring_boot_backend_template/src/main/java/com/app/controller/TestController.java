package com.app.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //@Controller + @ ResponseBody
//implicitely added 
@RequestMapping("/test")  //optional but recommended
public class TestController {
	public TestController()
	{
		System.out.println("in ctor of "+getClass());
	}
//	http://host:port/, method = GET
//	resp:<list<Integer>
	
	@GetMapping
	public List<Integer> testMe()
	{
		System.out.println("in test me");
		return List.of(1,2,3,4,5);
	}
	/*
	 * TestController --> @ResponseBody List<Integer> --> D.S
	 * SC uses Jackson to martial java -->JSON --> sent to REST clnt
	 */
	 
}
