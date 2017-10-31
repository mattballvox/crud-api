package com.crud.api.validator;

import com.crud.api.repository.model.User;
import com.crud.api.validator.exception.UserValidationException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * A simple implementation of our UserValidator interface. This will do some
 * basic checking that the request had the bare minimum of data.
 * 
 * @author mball
 */
@Component
public class SimpleUserValidator implements UserValidator {
    
    private static final String FORENAME_ERROR = "Your Forename cannot be empty";
    private static final String SURNAME_ERROR = "Your Surname cannot be empty";
    private static final String EMAIL_EMPTY_ERROR = "Your email cannot be empty";
    private static final String EMAIL_INVALID_ERROR = "Your email seems to be missing an @";

    @Override
    public boolean validate(User user) throws UserValidationException {
        try {            
            Assert.hasText(user.getForename(), FORENAME_ERROR);
            Assert.hasText(user.getSurname(), SURNAME_ERROR);
            Assert.hasText(user.getEmail(), EMAIL_EMPTY_ERROR);
        } catch (IllegalArgumentException iae) {
            throw new UserValidationException(iae.getMessage());
        }        
        Pattern pattern = Pattern.compile("^(.+)@(.+)$");        
        Matcher matcher = pattern.matcher(user.getEmail());
        
        if(!matcher.matches()) {
            throw new UserValidationException(EMAIL_INVALID_ERROR);
        }
        
        return true;
    }

}
