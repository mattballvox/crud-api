package com.crud.api.validator;

import com.crud.api.repository.model.User;
import com.crud.api.validator.exception.UserValidationException;

/**
 * A functional interface that can be implemented if more rigorous validation needs
 * to be applied.
 * 
 * @author mball
 */
@FunctionalInterface
public interface UserValidator {
    
    /**
     * Implement rules that the data within the request has to pass before it can be persisted.
     * 
     * @param user
     * @return 
     * @throws com.crud.api.validator.exception.UserValidationException 
     */
    boolean validate(User user) throws UserValidationException;
    
}
