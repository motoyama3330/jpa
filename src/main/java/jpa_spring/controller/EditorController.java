package jpa_spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import jpa_spring.form.PersonForm;
import jpa_spring.service.PersonService;

@Controller
public class EditorController {

	@Autowired
	private PersonService personService;

	@RequestMapping(value = "/edit/id/{id}/", method = RequestMethod.GET)
	public ModelAndView editPersonInfo(@PathVariable int id) {
		ModelAndView mav = new ModelAndView("edit");
		mav.addObject("person", personService.getPerson(id));
		mav.addObject("caption", "登録情報の編集");
		return mav;
	}

	@RequestMapping(value = "/create/", method = RequestMethod.GET)
	public ModelAndView createPersonInfo() {
		ModelAndView mav = new ModelAndView("create");
		mav.addObject("caption", "新規に追加する情報");
		mav.addObject("person", new PersonForm());
		return mav;
	}

	@RequestMapping(value = "/create/post/", method = RequestMethod.POST)
	public String entryPersonInfo(@ModelAttribute("person") PersonForm person) {
		if(person != null) {
			personService.createPerson(person);
		}
		return "redirect:/top/";
	}

	@RequestMapping(value = "/delete/id/{id}", method = RequestMethod.GET)
	public String deletePerson(@PathVariable int id) {
		personService.deletePerson(id);
		return "redirect:/top/";
	}

	@RequestMapping(value = "/edit/post/", method = RequestMethod.POST)
	public String postEditInfo(@ModelAttribute("person") PersonForm person) {
		if(person != null) {
			personService.updatePersonInfo(person);
		}
		return "redirect:/top/";
	}


}
