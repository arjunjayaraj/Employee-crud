/**
 * 
 */
package com.self.crud.controller;

import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.self.crud.dto.EmployeeDto;
import com.self.crud.dto.EmployeeListResponseDto;
import com.self.crud.dto.ErrorResponse;
import com.self.crud.exception.BadRequestException;
import com.self.crud.service.EmployeeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * @author Arjun
 *
 */

@RestController
@Tag(name = "Employee controller", description = "This controller contains all the endpoints related to Employee opertation")
@RequestMapping("emps/v1/employees")
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;

	@Operation(description = "This API will return the list of all employees")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successfull Response", content = @Content(mediaType = "application/json", schema = @Schema(implementation = EmployeeListResponseDto.class))),
			@ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
			@ApiResponse(responseCode = "200", description = "Successfull Response", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
			@ApiResponse(responseCode = "200", description = "Successfull Response", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))) })
	@GetMapping
	public ResponseEntity<EmployeeListResponseDto> getEmployeeList(
			@RequestParam(value = "pageNumber", required = false, defaultValue = "0") int pageNumber,
			@RequestParam(value = "count", required = false, defaultValue = "10") int count,
			@RequestParam(value = "searchKey", required = false, defaultValue = "") String searchKey) {

		EmployeeListResponseDto response = employeeService.getEmployeeList(searchKey, pageNumber, count);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Operation(description = "New employee with the data in the request will be created in the server")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successfull Response", content = @Content(mediaType = "application/json", schema = @Schema(implementation = List.class))),
			@ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
			@ApiResponse(responseCode = "200", description = "Successfull Response", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
			@ApiResponse(responseCode = "200", description = "Successfull Response", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))) })
	@PostMapping
	public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto) {
		validateRequest(employeeDto);
		return new ResponseEntity<>(employeeService.createEmployee(employeeDto), HttpStatus.CREATED);
	}

	@Operation(description = "Updates employee with the data in the request")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successfull Response", content = @Content(mediaType = "application/json", schema = @Schema(implementation = List.class))),
			@ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
			@ApiResponse(responseCode = "200", description = "Successfull Response", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
			@ApiResponse(responseCode = "200", description = "Successfull Response", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))) })
	@PutMapping
	public ResponseEntity<EmployeeDto> updateEmployee(@RequestBody EmployeeDto employeeDto) {
		validateRequest(employeeDto);
		return new ResponseEntity<>(employeeService.updateEmployee(employeeDto), HttpStatus.OK);
	}

	protected void validateRequest(EmployeeDto employee) {
		String idNumber = employee.getIdNumber();
		Pattern patern = Pattern.compile("^\\d{2}[0-1][0-9][0-3]\\d\\d{4}[0-1]\\d{2}$");
		if (StringUtils.isEmpty(employee.getFirstName())) {
			throw new BadRequestException("First Name is mandatory");
		}

		if (StringUtils.isEmpty(employee.getLastName())) {
			throw new BadRequestException("Last Name is mandatory");
		}

		if (StringUtils.isEmpty(idNumber)) {
			throw new BadRequestException("Id number is mandatory");
		}

		if (!patern.matcher(idNumber).matches()) {
			throw new BadRequestException("Id number is not in valid formate");
		}
	}

}
