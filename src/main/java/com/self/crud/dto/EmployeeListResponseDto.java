/**
 * 
 */
package com.self.crud.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Arjun
 *
 */
public class EmployeeListResponseDto implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6410233003853434768L;

	long totalPages;
	
	long totalElements;
	
	int countPerPage =10;
	
	List<EmployeeDto> employeeList = new ArrayList<>();

	/**
	 * @return the totalPages
	 */
	public long getTotalPages() {
		return totalPages;
	}

	/**
	 * @param totalPages the totalPages to set
	 */
	public void setTotalPages(long totalPages) {
		this.totalPages = totalPages;
	}

	/**
	 * @return the totalElements
	 */
	public long getTotalElements() {
		return totalElements;
	}

	/**
	 * @param totalElements the totalElements to set
	 */
	public void setTotalElements(long totalElements) {
		this.totalElements = totalElements;
	}

	/**
	 * @return the countPerPage
	 */
	public int getCountPerPage() {
		return countPerPage;
	}

	/**
	 * @param countPerPage the countPerPage to set
	 */
	public void setCountPerPage(int countPerPage) {
		this.countPerPage = countPerPage;
	}

	/**
	 * @return the employeeList
	 */
	public List<EmployeeDto> getEmployeeList() {
		return employeeList;
	}

	/**
	 * @param employeeList the employeeList to set
	 */
	public void setEmployeeList(List<EmployeeDto> employeeList) {
		this.employeeList = employeeList;
	}

}
