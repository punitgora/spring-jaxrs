package org.example.spring.employee.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.example.spring.employee.entity.enums.EmployeeTypeEnum;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeModel {

    @JsonProperty(value = "employee_id")
    private Long id;

    @JsonProperty(value = "name")
    private String name;

    @JsonProperty(value = "date_of_birth")
    private Date dateOfBirth;

    @JsonProperty(value = "date_of_joining")
    private Date dateOfJoining;

    @JsonProperty(value = "employee_type")
    private EmployeeTypeEnum employeeType;

    @JsonProperty(value = "city")
    private String city;

    @JsonProperty(value = "address_line_1")
    private String address1;

    @JsonProperty(value = "address_line_2")
    private String address2;

    @JsonProperty(value = "state")
    private String state;

    @JsonProperty(value = "email")
    private String email;

    @JsonProperty(value = "phone")
    private Long phone;
}
