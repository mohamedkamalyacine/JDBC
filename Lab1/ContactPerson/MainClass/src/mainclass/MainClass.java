package mainclass;

//import java.sql.*;

public class MainClass {

    public static void main(String[] args) {
        ContactPerson p = new ContactPerson();
        
        /*p.setId(3);
        p.setName("Mohsen Ali");
        p.setNickName("Moha");
        p.setAddress("London");
        p.setHomePhone("456321");
        p.setCellPhone("123654");
        p.setWorkPhone("741963");
        p.setEmail("Mohsen@gmail.com");
        p.setBirthDate("1997-02-10");
        p.setWebSite("www.Mohsen.com");
        p.setProfession("AV Eng");*/
        ContactDAO testDB = new ContactDAO();
        
        //testDB.insertData(p);
        
        testDB.getContacts().forEach((s)->{
            System.out.println(s.getId() + " " + s.getName() + " " + s.getNickName() + " " + s.getEmail());
        });
        
        testDB.updateData("Luxor", 1);
        testDB.getContacts().forEach((s)->{
            System.out.println(s.getId() + " " + s.getName() + " " + s.getAddress());
        });
        
        testDB.deleteData(3);
        
        testDB.getContacts().forEach((s)->{
            System.out.println(s.getId() + " " + s.getName() + " " + s.getAddress());
        });
        testDB.closeConn();
        
    }
    
}
