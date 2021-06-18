/**
 * 
 */
package com.self.crud.service;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.self.crud.dto.EmployeeDto;
import com.self.crud.dto.EmployeeListResponseDto;
import com.self.crud.entity.Employee;
import com.self.crud.exception.BadRequestException;
import com.self.crud.repo.EmployeeRepository;

/**
 * @author Arjun
 *
 */

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeRepository employeeRepo;

	@Override
	public EmployeeListResponseDto getEmployeeList(String searchKey, int pageNumber, int count) {
		Pageable pageable = PageRequest.of(pageNumber, count);
		Page<Employee> page;

		EmployeeListResponseDto response = new EmployeeListResponseDto();
		if (StringUtils.isEmpty(searchKey)) {
			page = employeeRepo.findAll(pageable);
		} else {
			page = employeeRepo.findByFirstNameContainingIgnoreCaseOrMobileNumberContainingOrIdNumberContaining(
					searchKey, searchKey, searchKey, pageable);
		}

		if (!page.isEmpty()) {
			response.setCountPerPage(count);
			response.setTotalElements(page.getTotalElements());
			response.setTotalPages(page.getTotalPages());
			response.setEmployeeList(
					page.stream().map(this::transformEmplyoeeToEmployeeDto).collect(Collectors.toList()));
		}
		return response;
	}

	private EmployeeDto transformEmplyoeeToEmployeeDto(Employee employee) {
		EmployeeDto dto = new EmployeeDto();
		BeanUtils.copyProperties(employee, dto);
		return dto;
	}
	
	@Override
	public EmployeeDto createEmployee(EmployeeDto dto) {
		dto.setId(null);
		long idcount = employeeRepo.countByIdNumber(dto.getIdNumber());
		if (idcount > 0) {
			throw new BadRequestException("Id number is already present in the system");
		}

		if (!StringUtils.isEmpty(dto.getMobileNumber())) {
			long mobileNumberCount = employeeRepo.countByMobileNumber(dto.getMobileNumber());
			if (mobileNumberCount > 0) {
				throw new BadRequestException("Mobile number is already present in the system");
			}
		}

		Employee employee = transformEmployeeDtoIntoEmployee(dto);
		employeeRepo.save(employee);

		return transformEmplyoeeToEmployeeDto(employee);
	}
	
	
	@Override
	public EmployeeDto updateEmployee(EmployeeDto dto) {
		Long id = dto.getId();
		
		if(Objects.isNull(id)) {
			throw new BadRequestException("Employee id is missing in the request");
		}
		
		
		Optional<Employee> empOpt  = employeeRepo.findById(id);
		
		if(empOpt.isEmpty()) {
			throw new BadRequestException("Employee is not found in the database");
		}
		
		long idcount = employeeRepo.countByIdNumberAndIdNot(dto.getIdNumber(),id);
		if (idcount > 0) {
			throw new BadRequestException("Id number is already present in the system");
		}

		if (!StringUtils.isEmpty(dto.getMobileNumber())) {
			long mobileNumberCount = employeeRepo.countByMobileNumberAndIdNot(dto.getMobileNumber(), id);
			if (mobileNumberCount > 0) {
				throw new BadRequestException("Mobile number is already present in the system");
			}
		}

		Employee employee = transformEmployeeDtoIntoEmployee(dto);
		employeeRepo.save(employee);

		return transformEmplyoeeToEmployeeDto(employee);
	}

	public Employee transformEmployeeDtoIntoEmployee(EmployeeDto dto) {
		Employee employee = new Employee();
		BeanUtils.copyProperties(dto, employee);
		return employee;
	}

}
