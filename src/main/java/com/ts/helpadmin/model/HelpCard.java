package com.ts.helpadmin.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

// TODO uncomment out @Id when using mongodb 
//import javax.persistence.Id;

//Document is equivalent to Entity (mongo => sql)
@Document
@Data
public class HelpCard {
    
    public static final String VIDEOS_TYPE = "videos";
    public static final String MANUALS_TYPE = "manuals";

//  @Id
    private String id;
    private String url;
    private String title;
    private String thumbnail;
    private String type;

    public HelpCard(String id, String url, String title, String thumbnail, String type)
    {
        this.id = id;
        this.url = url;
        this.title = title;
        this.thumbnail = thumbnail;
        this.type = type;
        
    }
    
    public HelpCard() {
    }

    public HelpCard(long incrementAndGet, String format) {
    }

}
