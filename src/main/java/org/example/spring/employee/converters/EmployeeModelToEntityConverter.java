package org.example.spring.employee.converters;

import org.example.spring.employee.entity.EmployeeEntity;
import org.example.spring.employee.model.EmployeeModel;
import org.springframework.core.convert.converter.Converter;

import javax.inject.Named;
import java.util.Date;
import java.util.Optional;

@Named
public class EmployeeModelToEntityConverter implements Converter<EmployeeModel, EmployeeEntity> {

    @Override
    public EmployeeEntity convert(EmployeeModel employeeModel) {
        return EmployeeEntity.builder()
                .id(employeeModel.getId())
                .dateOfBirth(Optional.ofNullable(employeeModel.getDateOfBirth())
                        .map(Date::getTime)
                        .orElse(null))
                .dateOfJoining(Optional.ofNullable(employeeModel.getDateOfJoining())
                        .map(Date::getTime).orElse(null))
                .phone(employeeModel.getPhone())
                .email(employeeModel.getEmail())
                .name(employeeModel.getName())
                .city(employeeModel.getCity())
                .address1(employeeModel.getAddress1())
                .address2(employeeModel.getAddress2())
                .state(employeeModel.getState())
                .employeeType(employeeModel.getEmployeeType())
                .build();
    }
}
