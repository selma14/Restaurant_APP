package Model;
import java.sql.*;

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
    public ResultSet showAllEmployee() throws SQLException {
        con=DBConnection.createDBConnection();
        String query="select * from employee";

        Statement stmt=con.createStatement();
        ResultSet result= stmt.executeQuery(query);

        return result;
    }

    @Override
    public void showEmployeeBasedOnID(int id) {

    }

    @Override
    public void updateEmployee(int id, int age, int phone, float salary, String name, String role, String address, String shift, String familySituation, String email) {
        con=DBConnection.createDBConnection();
        String query="update employee set name=?,role=?,age=?,phone=?,address=?,familySituation=?,shift=?,email=?,salary=? where id=?";
        try{
            PreparedStatement pstm=con.prepareStatement(query);
            pstm.setString(1,name);
            pstm.setString(2,role);
            pstm.setInt(3,age);
            pstm.setInt(4,phone);
            pstm.setString(5,address);
            pstm.setString(6,familySituation);
            pstm.setString(7,shift);
            pstm.setString(8,email);
            pstm.setFloat(9,salary);
            pstm.setInt(10,id);
            int cnt=pstm.executeUpdate();
            if(cnt!=0)
                System.out.println("Menu Item Details updated successfully !!");

        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    @Override
    public void deleteEmployee(int id) {
        con=DBConnection.createDBConnection();
        String query="delete from employee where id=?";
        try{
            PreparedStatement pstm=con.prepareStatement(query);
            pstm.setInt(1,id);
            int cnt= pstm.executeUpdate();
            if(cnt!=0)
                System.out.println("Menu Item Deleted Successfully!!! "+id);

        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
