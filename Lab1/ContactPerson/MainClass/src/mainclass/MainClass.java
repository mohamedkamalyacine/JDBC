package mainclass;

public class MainClass {

    public static void main(String[] args) {
        ContactDAO testDB = new ContactDAO();
        
        testDB.getContacts().forEach((s)->System.out.println(s.getName()));
        testDB.closeConn();
    }
    
}
