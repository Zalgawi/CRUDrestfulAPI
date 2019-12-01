package com.ts.helpadmin.view_model;

import javax.validation.constraints.NotNull;
import lombok.Data;


@Data
public class HelpCardVM {


    private String id;
    @NotNull(message = "The \"Url\" field can not be left empty. ")
    private String url;
    @NotNull(message = "The \"Title\" field can not be left empty.")
    private String title;    
    private String thumbnail;
    @NotNull(message = "The \"Type\" field can not be left empty.")
    private String type;


}
