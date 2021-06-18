/**
 * 
 */
package com.self.crud.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.self.crud.entity.Employee;

/**
 * @author Arjun
 *
 */

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	public long countByIdNumberAndIdNot(String idNumber, Long id);

	public long countByIdNumber(String idNumber);

	public long countByMobileNumberAndIdNot(String mobileNumber, Long id);

	public long countByMobileNumber(String mobileNumber);

	public Page<Employee> findByFirstNameContainingIgnoreCaseOrMobileNumberContainingOrIdNumberContaining(
			String firstName, String mobileNumber, String idNumber, Pageable pageable);

}
