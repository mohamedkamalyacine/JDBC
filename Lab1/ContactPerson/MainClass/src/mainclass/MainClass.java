package mainclass;

import java.sql.*;

public class MainClass {

    public static void main(String[] args) {
        ContactPerson p = new ContactPerson();
        p.setId(2);
        p.setName("Omar Hammad");
        p.setNickName("Miro");
        p.setAddress("KSA");
        p.setHomePhone("987654");
        p.setCellPhone("456789");
        p.setWorkPhone("123321");
        p.setEmail("Omar@gmail.com");
        p.setBirthDate("1997-12-10");
        p.setWebSite("www.hammad.com");
        p.setProfession("SW Eng");
        
        ContactDAO testDB = new ContactDAO();
        
        testDB.insertData(p);
        testDB.getContacts().forEach((s)->{
            System.out.println(s.getId() + " " + s.getName());
        });
        
        testDB.closeConn();
        
    }
    
}
