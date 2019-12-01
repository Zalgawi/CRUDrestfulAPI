package com.ts.helpadmin.service;

import com.ts.helpadmin.exception.DataNotFoundException;
import com.ts.helpadmin.model.HelpCard;
import com.ts.helpadmin.view_model.HelpCardVM;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.context.annotation.Configuration;


@Configuration
@Slf4j
public class HelpAdminMockServiceImpl implements HelpAdminService {
   
    // created list to populate and update help card details
    private List<HelpCard> helpCardList = new ArrayList();

    public HelpCardVM validator(HelpCardVM helpCardVM) throws Exception {
        
        // Set the URL validator method and acceptable parameters
        String[] schemes = {"http","https"}; // DEFAULT schemes = "http", "https", "ftp"
        UrlValidator urlValidator = new UrlValidator(schemes);

        //Manual validators for empty / null fields and appropriate URL formats.

        if (helpCardVM.getTitle() == null || helpCardVM.getTitle().trim().isEmpty()) {
            
            throw new DataNotFoundException("The Title field can not be left null or empty. ");
        }
        else if (helpCardVM.getUrl() == null || helpCardVM.getUrl().trim().isEmpty()){
            
            throw new Exception("The Url field can not be left null or empty. ");
        }
        else if (!urlValidator.isValid(helpCardVM.getUrl()) ){
            
            throw new Exception("The Url field is not valid. ");
        }
        else if (helpCardVM.getThumbnail() != null && !helpCardVM.getThumbnail().trim().isEmpty() && !urlValidator.isValid(helpCardVM.getThumbnail()) ){
            
            throw new Exception("The Thumbnail field is not valid. ");
        }
        else if (helpCardVM.getType() == null || helpCardVM.getType().trim().isEmpty()) {
            throw new Exception("The Type field can not be left null or empty. ");
            
        } else if (!(helpCardVM.getType().equalsIgnoreCase(HelpCard.VIDEOS_TYPE) || helpCardVM.getType().equalsIgnoreCase(HelpCard.MANUALS_TYPE))) {
            throw new Exception("The Type field must only be videos or manuals. ");
        }
 
            return helpCardVM;
    }
    
    @Override
    public List<HelpCardVM> getAllCards(String type){
         
        return helpCardList.stream()
                .filter(x -> x.getType().equals(type))
                .map(helpCard -> convertModelToViewModel(helpCard))
                .collect(Collectors.toList());
    }
        
    @Override
    public HelpCardVM getCard(String id) throws DataNotFoundException {
        HelpCard dbHelpCard = null;
         // Validate ID not Null | Empty          
        if (id == null || id.trim().isEmpty()){
            throw new DataNotFoundException("The card with that ID does not exist");
        }
        else
        {
            boolean helpCardFound = false;
            
            for(HelpCard helpCard : helpCardList) { 
                
            //Check if ID is not existing (If not, return exception)
             
            if(helpCard.getId().equals(id)) { 
                
                helpCardFound = true;
                dbHelpCard = helpCard;
                break;
                }
            }
            
            if(!helpCardFound)
        {
            throw new DataNotFoundException("The card with that ID does not exist");
        }
        
        }
         
        return this.convertModelToViewModel(dbHelpCard);
    }
          
    @Override
    public HelpCardVM deleteCard(String id) throws DataNotFoundException{
         
        HelpCardVM helpCardVm = getCard(id);
        helpCardList.removeIf(x -> x.getId().equals(id));

        return helpCardVm;
     
     }
         
    @Override
    public HelpCardVM addCard(HelpCardVM helpCardVM) throws Exception {

        this.validator(helpCardVM);

        HelpCard helpCard = convertViewModelToModel(helpCardVM);
        
        // generate a unique ID manually through the back-end and set for card.
        helpCard.setId(UUID.randomUUID().toString());
        helpCardVM.setId(helpCard.getId());
        
        
        helpCardList.add(helpCard);

        return helpCardVM;
    }
    
    @Override
    public HelpCardVM editCard (HelpCardVM helpCardVM) throws DataNotFoundException, Exception{
        
        if (helpCardVM.getId() == null || helpCardVM.getId().trim().isEmpty()){
            throw new DataNotFoundException("The card with that ID cannot be empty or null");
        }
        
        //Validate remaining fields      
        this.validator(helpCardVM);

        Optional<HelpCard> optionalValue = helpCardList.stream()
                .filter(x -> x.getId().equals(helpCardVM.getId()))
                .findAny();
        
        optionalValue.orElseThrow(() -> new DataNotFoundException("The card with that ID does not exist"));
        
        optionalValue.ifPresent(helpCard -> {
            helpCard.setTitle(helpCardVM.getTitle());
            helpCard.setUrl(helpCardVM.getUrl());
            helpCard.setThumbnail(helpCardVM.getThumbnail());
            helpCard.setType(helpCardVM.getType());
        });


        return helpCardVM;
    }
    
    private HelpCardVM convertModelToViewModel(HelpCard helpCard) {
        HelpCardVM viewModel = new HelpCardVM();
        viewModel.setId(helpCard.getId());
        viewModel.setUrl(helpCard.getUrl());
        viewModel.setTitle(helpCard.getTitle());
        viewModel.setThumbnail(helpCard.getThumbnail());
        viewModel.setType(helpCard.getType());
        return viewModel;
    }

    private HelpCard convertViewModelToModel(HelpCardVM helpCardVM) {
        HelpCard model = new HelpCard();
        model.setId(helpCardVM.getId());
        model.setUrl(helpCardVM.getUrl());
        model.setTitle(helpCardVM.getTitle());
        model.setThumbnail(helpCardVM.getThumbnail());
        model.setType(helpCardVM.getType());

      
        return model;
    }
}
