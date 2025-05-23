// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.baeldung.web;

import com.baeldung.web.MainController;
import io.springlets.web.NotFoundException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

privileged aspect MainController_Roo_Thymeleaf_MainController {
    
    declare @type: MainController: @Controller;
    
    /**
     * TODO Auto-generated method documentation
     * 
     * @param model
     * @return String
     */
    @GetMapping("/")
    public String MainController.index(Model model) {
        model.addAttribute("application_locale", LocaleContextHolder.getLocale().getLanguage());
        return "index";
    }
    
    /**
     * TODO Auto-generated method documentation
     * 
     * @param model
     * @return String
     */
    @GetMapping("/accessibility")
    public String MainController.accessibility(Model model) {
        model.addAttribute("application_locale", LocaleContextHolder.getLocale().getLanguage());
        return "accessibility";
    }
    
    /**
     * TODO Auto-generated method documentation
     * 
     * @param template
     * @return String
     */
    @RequestMapping(value = "/js/{template}.js", method = RequestMethod.GET)
    public String MainController.javascriptTemplates(@PathVariable("template") String template) {
        if (StringUtils.hasLength(template)) {
            return template.concat(".js");
        }
        throw new NotFoundException("File not found");
    }
    
}
