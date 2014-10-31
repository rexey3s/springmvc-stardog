package com.chuongdang.mvc.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.complexible.stardog.ext.spring.SnarlTemplate;
import com.complexible.stardog.ext.spring.mapper.SingleMapper;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	@Autowired
	private SnarlTemplate snarlTemplate;
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		String sparql = "SELECT ?a WHERE { ?a  <http://www.semanticweb.org/pseudo/ontologies/2014/7/transport.owl#canMoveOnOrIn> ?b} ";
		
		String result = snarlTemplate.queryForObject(sparql, new SingleMapper("a"));
		String formattedDate = dateFormat.format(date);
		System.out.println(result);
		model.addAttribute("serverTime", formattedDate );
		model.addAttribute("QueryResult", result);
		return "home";
	}
	
}
