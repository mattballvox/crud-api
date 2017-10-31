package com.crud.api.validator;

import com.crud.api.repository.model.User;
import com.crud.api.validator.exception.UserValidationException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * 
 * @author mball
 */
public class SimpleUserValidatorTest {
    
    private static final String EMAIL = "some@mail.com";
    private static final String FORENAME = "someForename";
    private static final String SURNAME = "someSurname";    
    
    public SimpleUserValidatorTest() {
    }
    
    /**
     * Test of validate method, of class SimpleUserValidator.
     * @throws com.crud.api.validator.exception.UserValidationException
     */
    @Test
    public void testValidateWithValidUser() throws UserValidationException {
        System.out.println("testValidateWithValidUser");
        User user = new User();
        user.setCreated(LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME));
        user.setEmail(EMAIL);
        user.setForename(FORENAME);
        user.setSurname(SURNAME);        
        SimpleUserValidator instance = new SimpleUserValidator();
        boolean expResult = true;
        boolean result = instance.validate(user);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of validate method, of class SimpleUserValidator.
     * @throws com.crud.api.validator.exception.UserValidationException
     */
    @Test(expected = UserValidationException.class)
    public void testValidateWithEmptyForename() throws UserValidationException {
        System.out.println("testValidateWithEmptyForename");
        User user = new User();
        user.setCreated(LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME));
        user.setEmail(EMAIL);
        user.setForename(StringUtils.EMPTY);
        user.setSurname(SURNAME);        
        SimpleUserValidator instance = new SimpleUserValidator();
        instance.validate(user);
    }
    
    /**
     * Test of validate method, of class SimpleUserValidator.
     * @throws com.crud.api.validator.exception.UserValidationException
     */
    @Test(expected = UserValidationException.class)
    public void testValidateWithEmptySurname() throws UserValidationException {
        System.out.println("testValidateWithEmptySurname");
        User user = new User();
        user.setCreated(LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME));
        user.setEmail(EMAIL);
        user.setForename(FORENAME);
        user.setSurname(StringUtils.EMPTY);        
        SimpleUserValidator instance = new SimpleUserValidator();
        instance.validate(user);
    }
    
    /**
     * Test of validate method, of class SimpleUserValidator.
     * @throws com.crud.api.validator.exception.UserValidationException
     */
    @Test(expected = UserValidationException.class)
    public void testValidateWithEmptyEmail() throws UserValidationException {
        System.out.println("testValidateWithEmptyEmail");
        User user = new User();
        user.setCreated(LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME));
        user.setEmail(StringUtils.EMPTY);
        user.setForename(FORENAME);
        user.setSurname(SURNAME);        
        SimpleUserValidator instance = new SimpleUserValidator();
        instance.validate(user);
    }   
    
}
