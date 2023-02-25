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

    private boolean connect() {
        try {
            Class.forName(DRIVER);
            con = DriverManager.getConnection(DBURL, USER, PASSWORD);

            if (con != null) {
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

    public List<ContactPerson> getContacts() {
        String sqlStatement = "select * from contact";
        List<ContactPerson> contactsList = new ArrayList<>();

        try {
            if (connect()) {
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(sqlStatement);
                //System.out.println(rs.getString("name"));
                while (rs.next()) {
                    ContactPerson p = createContactPerson(rs);
                    contactsList.add(p);
                }
                rs.close();
                stmt.close();
                //con.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        //con.close();
        return contactsList;
    }

    private ContactPerson createContactPerson(ResultSet rs) {
        ContactPerson p = new ContactPerson();
        try {
            p.setId(rs.getInt("id"));
            p.setName(rs.getString("name"));
            p.setNickName(rs.getString("nick_name"));
            p.setAddress(rs.getString("address"));
            p.setHomePhone(rs.getString("home_phone"));
            p.setWorkPhone(rs.getString("work_phone"));
            p.setCellPhone(rs.getString("cell_phone"));
            p.setEmail(rs.getString("email"));
            p.setBirthDate(rs.getString("web_site"));
            p.setProfession(rs.getString("profession"));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return p;
    }

    public void insertData(ContactPerson p) {
        if (connect()) {
            try {
                String query = "INSERT INTO contact(id, name, nick_name, address, home_phone, work_phone, cell_phone, email, birthday, web_site, profession) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
                PreparedStatement pstmt = con.prepareStatement(query);
                pstmt.setInt(1, p.getId());
                pstmt.setString(2, p.getName());
                pstmt.setString(3, p.getNickName());
                pstmt.setString(4, p.getAddress());
                pstmt.setString(5, p.getHomePhone());
                pstmt.setString(6, p.getWorkPhone());
                pstmt.setString(7, p.getCellPhone());
                pstmt.setString(8, p.getEmail());
                pstmt.setString(9, p.getBirthDate());
                pstmt.setString(10, p.getWebSite());
                pstmt.setString(11, p.getProfession());
                
                pstmt.execute();
            } catch (SQLException ex) {
                ex.printStackTrace();
                //Logger.getLogger(ContactDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void updateData(String updatedVal, int id){
        if(connect()){
            try{
                String query = "UPDATE contact "
                             + "SET address=? "
                             + "WHERE id=?";
                
                PreparedStatement pstmt = con.prepareStatement(query);
                pstmt.setString(1, updatedVal);
                pstmt.setInt(2, id);
                pstmt.execute();
                
                //ResultSet rs = pstmt.executeQuery();
                
            }catch(SQLException ex){
                ex.printStackTrace();
            }
        }
    }
    
    public void deleteData(int id){
        if(connect()){
            try{
                String query = "DELETE FROM contact "
                             + "WHERE id=?";
                
                PreparedStatement pstmt = con.prepareStatement(query);
                pstmt.setInt(1, id);
                pstmt.execute();
                
                //ResultSet rs = pstmt.executeQuery();
                
            }catch(SQLException ex){
                ex.printStackTrace();
            }
        }
    }
    
    public void closeConn() {
        try {
            con.close();
            isConnected = false;
        } catch (SQLException ex) {
            Logger.getLogger(ContactDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
