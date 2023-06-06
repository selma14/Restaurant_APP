package Model;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface EmployeeDaoIntrf {
    //create employee
    public void createEmployee(Employee emp);
    //show all employee
    public ResultSet showAllEmployee() throws SQLException;


    //update employee
    public void updateEmployee(int id, int age, int phone, float salary, String name, String role, String address, String shift, String familySituation, String email);
    //delete employee
    public void deleteEmployee(int id);
}
