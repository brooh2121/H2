import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectH2 {
    public static void main (String[] args){
        try{
            Class.forName("org.h2.Driver").newInstance();
            Connection con = DriverManager.getConnection("jdbc:h2:./db/SomeBase","test","123qweasd");
            if (!con.isClosed()){
                System.out.println("Соединение с БД установлено");

                Statement CreateTable = con.createStatement();
                CreateTable.execute("CREATE table if NOT EXISTS Test(ID INT, NAME VARCHAR(20))");
                System.out.println("Table created successfully...");

                Statement InsertValue = con.createStatement();
                InsertValue.execute("INSERT into Test values (1,'GayHunter')");

                String str = "Jack";
                String querySel = "insert into Test(name) values(?)";
                PreparedStatement st1 = con.prepareStatement(querySel);
                st1.setString(1,str);
                st1.execute();

                ResultSet result;
                result = InsertValue.executeQuery("Select * from Test");
                while (result.next()){
                    String name = result.getString("NAME");
                    System.out.println(result.getString("ID")+" "+name);
                }
            }else{
                System.out.println("Соединение с БД не установлено");
            }
           /*Statement DropTable =con.createStatement();
            DropTable.execute("DROP table Test");
            System.out.println("Table Droped!");*/
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
