package pillihuaman.com.pe.basebd.employee.dao;

import pillihuaman.com.pe.basebd.common.MyJsonWebToken;
import pillihuaman.com.pe.basebd.config.BaseMongoRepository;
import pillihuaman.com.pe.basebd.employee.Employee;
import pillihuaman.com.pe.lib.request.ReqEmployee;

import java.util.List;

public interface EmployeeDAO extends BaseMongoRepository<Employee> {

    /**
     * Lists employees based on specific request criteria.
     *
     * @param reqEmployee The request criteria for filtering employees.
     * @return A list of employees matching the criteria.
     */
    List<Employee> listEmployees(ReqEmployee reqEmployee);

    /**
     * Saves or updates an employee entity.
     *
     * @param employee The employee entity to save or update.
     * @param token    The JWT token containing user context.
     * @return The saved or updated employee entity.
     */
    Employee saveEmployee(Employee employee, MyJsonWebToken token);

    /**
     * Finds employees associated with a specific user.
     *
     * @param userId The ID of the user.
     * @return A list of employees linked to the specified user.
     */
    List<Employee> findByUserId(String userId);

}
