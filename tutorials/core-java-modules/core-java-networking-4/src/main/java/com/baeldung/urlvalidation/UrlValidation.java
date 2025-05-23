package com.baeldung.urlvalidation;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import org.apache.commons.validator.routines.UrlValidator;

public class UrlValidation {
    public boolean isValidURLJavaNet(String url) throws MalformedURLException, URISyntaxException {
        try {
            new URI(url);
            return true;
        } catch (URISyntaxException e) {
            return false;
            
        }
    }
    
    public boolean invalidUrlAsValidJavaNet(String url) throws MalformedURLException {
        try {
            new URI(url).toURL();
            return true;
        } catch (URISyntaxException e) {
            return false;
        }
    }
    
    public boolean isValidURLApache(String url) throws MalformedURLException {
        UrlValidator validator = new UrlValidator();
        return validator.isValid(url);
    }
    
}