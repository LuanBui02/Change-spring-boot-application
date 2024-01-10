package com.managebankaccount.managebankaccount.details.beans;


import lombok.*;

import javax.accessibility.AccessibleContext;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.*;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Users {
    @Id
    private Long id;

    private String name;

    private String birthday;

    private String address;

    private Long idNumber;

}
