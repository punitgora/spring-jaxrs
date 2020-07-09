package org.example.spring.employee.dao;

import org.example.spring.employee.entity.EmployeeEntity;
import org.example.spring.employee.entity.enums.EmployeeTypeEnum;
import org.example.spring.employee.entity.enums.SortingOrderEnum;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface EmployeeDao {

    List<EmployeeEntity> getEmployees(String name, String city, EmployeeTypeEnum type, SortingOrderEnum order, String column, Integer limit, Integer offset);

    EmployeeEntity getEmployeeById(Long id);

    @Transactional
    EmployeeEntity saveOrUpdate(EmployeeEntity employeeEntity);
}
