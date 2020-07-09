package org.example.spring.employee.entity;

import lombok.*;
import org.example.spring.employee.entity.enums.EmployeeTypeEnum;
import org.example.spring.employee.entity.enums.StatusEnum;

import javax.persistence.*;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "employee")
public class EmployeeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "dob")
    private Long dateOfBirth;

    @Column(name = "doj")
    private Long dateOfJoining;

    @Column(name = "employee_type")
    private EmployeeTypeEnum employeeType;

    @Column(name = "city")
    private String city;

    @Column(name = "address_line_1")
    private String address1;

    @Column(name = "address_line_2")
    private String address2;

    @Column(name = "state")
    private String state;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private Long phone;

    @Column(name = "status")
    private StatusEnum status;

    @Column(name = "time_created")
    private Long timeCreated;

    @Column(name = "time_updated")
    private Long timeUpdated;
}
