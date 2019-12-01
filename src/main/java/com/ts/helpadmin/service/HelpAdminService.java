/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ts.helpadmin.service;

import com.ts.helpadmin.exception.DataNotFoundException;
import com.ts.helpadmin.view_model.HelpCardVM;
import java.util.List;


public  interface HelpAdminService {
   
    List<HelpCardVM> getAllCards(String type);
    HelpCardVM getCard(String id) throws DataNotFoundException;
    HelpCardVM deleteCard(String id) throws DataNotFoundException;
    HelpCardVM addCard(HelpCardVM helpCardVM) throws Exception;
    HelpCardVM editCard(HelpCardVM helpCardVM) throws DataNotFoundException, Exception;
    
}
