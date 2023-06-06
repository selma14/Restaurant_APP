package Model;

import java.sql.*;

public class MenuDAOImpl implements MenuDAOIntrf{
    Connection con;

    @Override
    public void createItem(MenuItem item) throws SQLException {
        con = DBConnection.createDBConnection();
        String query="insert into menutable values(?,?,?,?)";
        PreparedStatement pstm = con.prepareStatement(query);
        pstm.setInt(1,item.getItemId());
        pstm.setString(2, item.getItemName());
        pstm.setString(3,item.getItemCategory());
        pstm.setDouble(4,item.getItemPrice());
        int cnt= pstm.executeUpdate();
        if(cnt!=0) System.out.println("Menu Item Inserted Successfully !!!");
    }

    @Override
    public ResultSet showMenu() {
        con = DBConnection.createDBConnection();
        String query = "select * from menutable";
        ResultSet result;
        try {
            Statement stmt = con.createStatement();
            result = stmt.executeQuery(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public void updateMenuItem(int id, String name,String category,double price) {
        con=DBConnection.createDBConnection();
        String query="update menutable set ItemName=?,ItemCategory=?,ItemPrice=? where ItemId=?";
        try{
            PreparedStatement pstm=con.prepareStatement(query);
            pstm.setString(1,name);
            pstm.setString(2,category);
            pstm.setDouble(3,price);
            pstm.setInt(4,id);
            int cnt=pstm.executeUpdate();
            if(cnt!=0)
                System.out.println("Menu Item Details updated successfully !!");

        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    @Override
    public void deleteMenuItem(int id) {
        con=DBConnection.createDBConnection();
        String query="delete from menutable where ItemId=?";
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
