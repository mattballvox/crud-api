package com.crud.api.repository;

import com.crud.api.repository.model.User;
import java.util.Date;
import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Custom Repository interface for Spring Data. We have added a few custom methods to help us
 * find more specific data from our database.
 * 
 * @author mball
 */
@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long> {
    
    List<User> findByForename(String forename);
    
    List<User> findBySurname(String surname);
    
    List<User> findByEmail(String email);
    
    List<User> findByCreatedGreaterThanEqual(Date created);
    
    List<User> findByCreatedLessThanEqual(Date created);
}
