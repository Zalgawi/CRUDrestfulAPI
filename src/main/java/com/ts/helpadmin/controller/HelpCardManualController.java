package com.ts.helpadmin.controller;

import com.ts.helpadmin.service.HelpAdminMockServiceImpl;
import com.ts.helpadmin.service.HelpAdminService;
import com.ts.helpadmin.view_model.HelpCardVM;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//@RestController indicates that this class will have Rest End points.
@RestController
@RequestMapping("/help-card/cards")
@Slf4j
public class HelpCardManualController {
    // get, post, put and delete
    HelpAdminService helpAdminMockService = new HelpAdminMockServiceImpl();

    //This is the GET Method that retrieves all the cards from source
    @RequestMapping(method = RequestMethod.GET)
    public List<HelpCardVM> getAllCard(@RequestParam String type) throws Exception  {
        return helpAdminMockService.getAllCards(type);
    } 
    
    //This is the GET Method that retrieves card from source
    //@RequestMapping maps the /helpcard endpoint to helpcard Function
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public HelpCardVM getCard(@PathVariable String id) throws Exception  {
        
        return helpAdminMockService.getCard(id);
    } 
    
    //This is the POST method that creates a new Help Card
    @RequestMapping(method = RequestMethod.POST)
    public HelpCardVM postCard(@RequestBody HelpCardVM newHelpCardVM ) throws Exception {
        return helpAdminMockService.addCard(newHelpCardVM);
    }

    //This is the PUT method that allows us to edit a current Help Card
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public HelpCardVM editCard(@PathVariable String id, @RequestBody HelpCardVM newHelpCardVM) throws Exception {
        return helpAdminMockService.editCard(newHelpCardVM);
    }
   
    //This is the DELETE method, that allows us to Remove this Help Card.
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public HelpCardVM deleteCard(@PathVariable String id) throws Exception {
        return helpAdminMockService.deleteCard(id);
    }
       
    }

    



