package com.adnanarch.loginapp.enumeration;

import lombok.Getter;

/**
 * Author: Adnan Rafique
 * Date: 4/9/2024
 * Time: 11:47 PM
 * Version: 1.0
 */

@Getter
public enum EmailTemplateName {
    ACTIVATE_ACCOUNT("activate_account");

    private final String templateName;

    EmailTemplateName(String templateName) {
        this.templateName = templateName;
    }
}
