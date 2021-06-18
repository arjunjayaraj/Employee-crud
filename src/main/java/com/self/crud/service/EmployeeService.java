/**
 * 
 */
package com.self.crud.service;

import com.self.crud.dto.EmployeeDto;
import com.self.crud.dto.EmployeeListResponseDto;

/**
 * @author Arjun
 *
 */


public interface EmployeeService {

	public EmployeeListResponseDto getEmployeeList(String searchKey, int pageNumber, int count);

	public EmployeeDto updateEmployee(EmployeeDto dto);

	public EmployeeDto createEmployee(EmployeeDto dto);

}
