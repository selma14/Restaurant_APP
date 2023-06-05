package Model;

import java.sql.*;

public class MenuDAOImpl implements MenuDAOIntrf{
    Connection con;

    @Override
    public void createItem(MenuItem item) {
        con = DBConnection.createDBConnetion();
        String query="insert into menutable values(?,?,?,?)";
        try{
            PreparedStatement pstm = con.prepareStatement(query);
            pstm.setInt(1,item.getItemId());
            pstm.setString(2, item.getItemName());
            pstm.setString(3,item.getItemCategory());
            pstm.setDouble(4,item.getItemPrice());
            int cnt= pstm.executeUpdate();
            if(cnt!=0)
                System.out.println("Menu Item Inserted Successfully !!!");
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    @Override
    public ResultSet showMenu() throws SQLException {
        con=DBConnection.createDBConnetion();
        String query="select * from menutable";
        //System.out.println("Item Details :");
        //System.out.println("---------------------------------------------");

        //System.out.format("%s\t%s\t%s\t%s\n","ID","Name","Category","Price");
        //System.out.println("---------------------------------------------");
        //try{
            Statement stmt=con.createStatement();
            ResultSet result= stmt.executeQuery(query);
            //while (result.next()){
                //System.out.format("%d\t%s\t%s\t%f\n",
                  //      result.getInt(1),
                  //     result.getString(2),
                  //      result.getString(3),
                  //      result.getDouble(4));
                //System.out.println("---------------------------------------------");
           // }
       // }catch (Exception ex){
           // ex.printStackTrace();
       // }
        return result;
    }

    @Override
    public void showItemBasedOnID(int ItemId) {
        con=DBConnection.createDBConnetion();
        String query="select * from menutable where ItemId="+ItemId;
        try{
            Statement stmt=con.createStatement();
            ResultSet result= stmt.executeQuery(query);
            while (result.next()){
                System.out.format("%d\t%s\t%s\t%f\n",
                        result.getInt(1),
                        result.getString(2),
                        result.getString(3),
                        result.getDouble(4));
            }
        }
        catch (Exception ex){
            ex.printStackTrace();
        }

    }

    @Override
    public void updateMenuItem(int id, String name) {
        con=DBConnection.createDBConnetion();
        String query="update menutable set ItemName=? where ItemId=?";
        try{
            PreparedStatement pstm=con.prepareStatement(query);
            pstm.setString(1,name);
            pstm.setInt(2,id);
            int cnt=pstm.executeUpdate();
            if(cnt!=0)
                System.out.println("Menu Item Details updated successfully !!");

        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    @Override
    public void deleteMenuItem(int id) {
        con=DBConnection.createDBConnetion();
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
