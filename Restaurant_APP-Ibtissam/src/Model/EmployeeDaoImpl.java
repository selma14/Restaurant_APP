package Model;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class EmployeeDaoImpl implements EmployeeDaoIntrf {
    Connection con;
    @Override
    public void createEmployee(Employee emp) {
        con =DBConnection.createDBConnection();
        String query="insert into employee values(?,?,?,?,?,?,?,?,?,?)";
        try{
            PreparedStatement pstm=con.prepareStatement(query);
            pstm.setInt(1,emp.getId());
            pstm.setString(2,emp.getName());
            pstm.setString(3,emp.getRole());
            pstm.setInt(4,emp.getAge());
            pstm.setInt(5,emp.getPhone());
            pstm.setString(6,emp.getAddress());
            pstm.setString(7,emp.getFamilySituation());
            pstm.setString(8,emp.getShift());
            pstm.setString(9,emp.getEmail());
            pstm.setDouble(10,emp.getSalary());
            int cnt= pstm.executeUpdate();
            if(cnt!=0)
                System.out.println("Employee Inserted Successfully !!!");


        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    @Override
    public void showAllEmployee() {

    }

    @Override
    public void showEmployeeBasedOnID(int id) {

    }

    @Override
    public void updateEmployee(int id, String name) {

    }

    @Override
    public void deleteEmployee(int id) {

    }
}
