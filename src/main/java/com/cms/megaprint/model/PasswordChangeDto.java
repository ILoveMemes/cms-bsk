package com.cms.megaprint.model;

import lombok.Data;

@Data
public class PasswordChangeDto {

    private String oldPass;
    private String newUname;
    private String newPass;
    private String newPassConf;

}
