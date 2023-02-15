package mainclass;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ContactDAO {
    private String DRIVER = "com.mysql.cj.jdbc.Driver";
    private String DBURL = "jdbc:mysql://localhost:3306/addressbook";
    private String USER = "root";
    private String PASSWORD = "12345";
    private boolean isConnected = false;
    private Connection con;
    
    private boolean connect()
    {
        try {
            Class.forName(DRIVER);
            con = DriverManager.getConnection(DBURL, USER, PASSWORD);
            
            if(con != null)
            {
                isConnected = true;
                return true;
            }
        } catch (SQLException ex) {
            System.out.println("mainclass.ContactDAO.connect()" + ex.getMessage());
            Logger.getLogger(ContactDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ContactDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    
    public List<ContactPerson> getContacts()
    {
        String sqlStatement = "select * from contact";
        List<ContactPerson> contactsList = new ArrayList<>();
        
        try {
            if(connect())
            {
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(sqlStatement);
                //System.out.println(rs.getString("name"));
                while (rs.next()){
                    ContactPerson p = createContactPerson(rs);
                    contactsList.add(p);
                }
                rs.close();
                stmt.close();
                //con.close();
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        //con.close();
        return contactsList;
    }
    
    private ContactPerson createContactPerson(ResultSet rs)
    {
        ContactPerson p = new ContactPerson();
        try {
            p.setId(rs.getInt("id"));
            p.setName(rs.getString("name"));
            p.setNickName("nick_name");
            p.setAddress("address");
            p.setHomePhone(rs.getString("home_phone"));
            p.setWorkPhone(rs.getString("work_phone"));
            p.setCellPhone(rs.getString("cell_phone"));
            p.setEmail(rs.getString("email"));
            p.setBirthDate(rs.getString("web_site"));
            p.setProfession(rs.getString("profession"));
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return p;
    }
    
    public void closeConn()
    {
        try{
            con.close();
            isConnected = false;
        }catch(SQLException ex){
            Logger.getLogger(ContactDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
