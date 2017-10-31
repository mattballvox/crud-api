package com.crud.api.controllers;

import com.crud.api.repository.model.User;
import com.crud.api.repository.UserRepository;
import com.crud.api.validator.UserValidator;
import com.crud.api.validator.exception.UserValidationException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Our RestController that will expose a number of endpoints to allow third parties to integrate with our API.
 * 
 * @author mball
 */
@RestController
public class CRUDController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CRUDController.class);
    private static final String CREATED_USER = "Created User";
    private static final String UPDATED_USER = "Updated User";
    private static final String DELETED_USER = "Deleted User";
    private static final String USER_DOES_NOT_EXIST = "A User with that ID doesn't exist";

    private UserRepository userRepository;

    private UserValidator userValidator;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setUserValidator(UserValidator userValidator) {
        this.userValidator = userValidator;
    }

    /**
     * Handle requests to create a new User.
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST, 
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> create(@RequestBody User user) {

        LOGGER.debug("Received request to create " + user);
        try {
            userValidator.validate(user);
            //Set created time. This is cannot be changed.
            user.setCreated(LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME));
            
            userRepository.save(user);
            LOGGER.debug("User " + user + " added successfully");
            return new ResponseEntity<>(CREATED_USER, HttpStatus.OK);
        } catch (UserValidationException | DataAccessException dae) {
            return new ResponseEntity<>(dae.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Handle requests to fetch all users.
     *
     * @return
     */
    @RequestMapping(value = "/findAll", method = RequestMethod.GET)    
    public List<User> readAll() {
        LOGGER.debug("Received request to find all users");
        try {
            List<User> users = new ArrayList<>();            
            userRepository.findAll().forEach(user -> {
               users.add(user);
            });
            LOGGER.debug("Found " + users + ".");
            return users;
        } catch (DataAccessException dae) {
            LOGGER.error(dae.getMessage());
            return null;
        }
    }

    /**
     * Handle requests to fetch a users by forename.
     *
     * @param forename
     * @return
     */
    @RequestMapping(value = "/findByForename", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<User>> readByForename(@RequestParam String forename) {

        LOGGER.debug("Received request to find users by forename " + forename);
        try {
            List<User> users = userRepository.findByForename(forename);
            LOGGER.debug("Found " + users + ".");
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (DataAccessException dae) {
            LOGGER.error(dae.getMessage());
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Handle requests to fetch a users by surname.
     *
     * @param surname
     * @return
     */
    @RequestMapping(value = "/findBySurname", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<User>> readBySurname(@RequestParam String surname) {

        LOGGER.debug("Received request to find users by surname " + surname);
        try {
            List<User> users = userRepository.findBySurname(surname);
            LOGGER.debug("Found " + users + ".");
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (DataAccessException dae) {
            LOGGER.error(dae.getMessage());
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Handle requests to fetch users by email.
     *
     * @param email
     * @return
     */
    @RequestMapping(value = "/findByEmail", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<User>> readByEmail(@RequestParam String email) {

        LOGGER.debug("Received request to find users by email " + email);
        try {
            List<User> users = userRepository.findByEmail(email);
            LOGGER.debug("Found " + users + ".");
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (DataAccessException dae) {
            LOGGER.error(dae.getMessage());
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Handle requests to delete user.
     *
     * @param id
     * @param user     
     * @return
     */
    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT, 
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> update(@PathVariable("id") Long id, @RequestBody User user) {

        LOGGER.debug("Received request to update user with id" + id);
        try {            
            User userToUpdate = userRepository.findOne(id);
            
            userToUpdate.setForename(user.getForename());
            userToUpdate.setSurname(user.getSurname());
            userToUpdate.setEmail(user.getEmail());
            
            userValidator.validate(userToUpdate);
            userRepository.save(userToUpdate);
            LOGGER.debug("Updated " + user + ".");
            return new ResponseEntity<>(UPDATED_USER, HttpStatus.OK);
        } catch (NullPointerException | UserValidationException | DataAccessException dae) {
            LOGGER.error(dae.getMessage());
            if(dae instanceof NullPointerException) {
                return new ResponseEntity<>(USER_DOES_NOT_EXIST, HttpStatus.BAD_REQUEST);
            } else {
                return new ResponseEntity<>(dae.getMessage(), HttpStatus.BAD_REQUEST);
            }            
        }
    }

    /**
     * Handle requests to delete user.
     *
     * @param id     
     * @return
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)    
    public ResponseEntity<String> delete(@PathVariable("id") long id) {

        LOGGER.debug("Received request to delete user with id:" + id);
        try {
            userRepository.delete(id);
            LOGGER.debug("Deleted user with id: " + id);
            return new ResponseEntity<>(DELETED_USER, HttpStatus.OK);
        } catch (DataAccessException dae) {
            LOGGER.error(dae.getMessage());
            return new ResponseEntity<>(dae.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
