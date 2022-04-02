package com.cms.megaprint.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Description {

    private String shortDescription;
    private String fullDescription;

    public static Description of(String s, String f) {
        return new Description(s, f);
    }

}
