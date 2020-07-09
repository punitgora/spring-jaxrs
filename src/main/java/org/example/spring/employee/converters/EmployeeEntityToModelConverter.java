package org.example.spring.employee.converters;

import org.example.spring.employee.entity.EmployeeEntity;
import org.example.spring.employee.model.EmployeeModel;
import org.springframework.core.convert.converter.Converter;

import javax.inject.Named;
import java.util.Date;
import java.util.Optional;

@Named
public class EmployeeEntityToModelConverter implements Converter<EmployeeEntity, EmployeeModel> {

    @Override
    public EmployeeModel convert(EmployeeEntity employeeEntity) {
        return EmployeeModel.builder()
                .id(employeeEntity.getId())
                .dateOfBirth(Optional.ofNullable(employeeEntity.getDateOfBirth()).map(Date::new).orElse(null))
                .dateOfJoining(Optional.ofNullable(employeeEntity.getDateOfJoining()).map(Date::new).orElse(null))
                .phone(employeeEntity.getPhone())
                .email(employeeEntity.getEmail())
                .name(employeeEntity.getName())
                .city(employeeEntity.getCity())
                .address1(employeeEntity.getAddress1())
                .address2(employeeEntity.getAddress2())
                .state(employeeEntity.getState())
                .employeeType(employeeEntity.getEmployeeType())
                .build();
    }
}
