package com.library.library_management.memberservice.model;

import com.library.library_management.authservice.model.Account;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@DiscriminatorValue("MEMBER")
@Data // Generates getters, setters, toString, equals, and hashCode
public class Member extends Account {

}
