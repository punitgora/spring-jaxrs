package org.example.spring.employee.service;

import org.example.spring.employee.model.EmployeeModel;
import org.example.spring.employee.model.request.EmployeeCollectionQueryParams;

import java.util.List;

public interface EmployeeService {

    List<EmployeeModel> getEmployees(EmployeeCollectionQueryParams queryParams);

    EmployeeModel getEmployee(Long id);

    EmployeeModel createEmployee(EmployeeModel employee);

    void updateEmployee(Long id, EmployeeModel employee);

    void deleteEmployee(Long id);
}
