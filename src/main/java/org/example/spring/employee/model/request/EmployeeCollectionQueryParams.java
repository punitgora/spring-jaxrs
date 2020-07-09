package org.example.spring.employee.model.request;

import lombok.Getter;
import lombok.Setter;
import org.example.spring.employee.entity.enums.EmployeeTypeEnum;
import org.example.spring.employee.entity.enums.SortingOrderEnum;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.QueryParam;


@Getter
@Setter
public class EmployeeCollectionQueryParams {

    @QueryParam("name")
    private String name;

    @QueryParam("city")
    private String city;

    @QueryParam("type")
    private EmployeeTypeEnum employeeType;

    @QueryParam("order")
    @DefaultValue("ASCENDING")
    private SortingOrderEnum sortingOrder;

    @QueryParam("column")
    private String column;

    @QueryParam("limit")
    @DefaultValue("20")
    private Integer limit;

    @QueryParam("offset")
    @DefaultValue("0")
    private Integer offset;
}
