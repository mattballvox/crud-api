package com.crud.api.repository.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import springfox.documentation.annotations.ApiIgnore;

/**
 * Our User Object that will be persisted into our embedded database.
 *
 * @author mball
 */
@Entity
@ApiModel
public class User implements Serializable {

    @Id
    @ApiModelProperty(dataType = "String", hidden = true)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;    
    @ApiModelProperty(dataType = "String", required = true)
    private String forename;
    @ApiModelProperty(dataType = "String", required = true)
    private String surname;
    @ApiModelProperty(dataType = "String", required = true)
    private String email;
    @ApiModelProperty(dataType = "String", hidden = true)    
    private String created;

    
    public Long getId() {
        return id;
    }
    
    public void setId(@ApiIgnore Long id) {
        this.id = id;
    }

    @ApiModelProperty(dataType = "String", required = true)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @ApiModelProperty(dataType = "String", required = true)
    public String getForename() {
        return forename;
    }

    public void setForename(String forename) {
        this.forename = forename;
    }

    
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
    
    public String getCreated() {
        return created;
    }
    
    public void setCreated(@ApiIgnore String created) {
        this.created = created;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(forename)
                .append(surname)
                .append(email)
                .append(created)
                .hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof User) {
            final User user = (User) obj;
            return new EqualsBuilder()
                    .append(forename, user.forename)
                    .append(surname, user.surname)
                    .append(email, user.email)
                    .append(created, user.created)
                    .isEquals();
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("id", id)
                .append("forename", forename)
                .append("surname", surname)
                .append("email", email)
                .append("created", created)
                .toString();
    }
}
