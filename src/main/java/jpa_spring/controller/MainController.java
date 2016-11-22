package jpa_spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import jpa_spring.service.PersonService;

@Controller
public class MainController {

	@Autowired
	private PersonService personService;

	@RequestMapping(value = "/top/, method = RequestMethod.GET")
	public ModelAndView showMessage() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("message", "登録者一覧");
		mav.addObject("personList", personService.getPersonList());
		mav.setViewName("top");
		return mav;
	}
}
