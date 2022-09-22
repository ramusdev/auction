package com.rb.auction.controller;

import com.rb.auction.model.Tag;
import com.rb.auction.service.InterfaceTagService;
import com.rb.auction.session.SessionObject;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Log4j2
@Controller
public class TagController {
    @Autowired
    SessionObject sessionObject;
    @Autowired
    InterfaceTagService tagService;

    @RequestMapping(value = "/tag/add", method = RequestMethod.GET)
    public String addTagShow(Model model) {
        SessionObject sessionObject = null;
        if (this.sessionObject.isLogged()) {
            sessionObject = this.sessionObject;
        }

        model.addAttribute("msession", sessionObject);
        model.addAttribute("mtag", new Tag());

        if (this.sessionObject.isLogged()) {
            return "tag";
        }

        return "no-login";
    }

    @RequestMapping(value = "/tag/add", method = RequestMethod.POST)
    public String addTag(@ModelAttribute Tag tag) {
        this.tagService.add(tag);

        return "redirect:/main";
    }
}
