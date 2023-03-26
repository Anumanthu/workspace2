package src.dbconnect;

import org.openqa.selenium.WebDriver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

//import com.mysql.jdbc.Connection;
//import com.mysql.jdbc.Statement;

public class JDBCConnector {

    // 1)Install MYSQL server (It's sql editor as same as MS SQL SERVER)
    // 2)Created DB,Tables and inserted values
    // 3)Downloaded and configured Mysql Connector Java jar files to this project
    // Written code to connect DB and retrieve data
    // DriverManager.getConnection

    public static WebDriver driver;

    public static String host = "localhost";
    public static String port = "3306";

    public static void main(String[] args) throws Exception {

        Connection con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/QADB", "root",
                "Divya@123");

        Statement s = con.createStatement();

        ResultSet rs = s.executeQuery("select * from EmployeeInfo where id=2");

        // rs.next();

        while (rs.next()) {
            System.out.println(rs.getString("empname"));

            // we can use these values
            // driver.findElement(By.xpath("xpath").sendKeys(rs.getString("empname"))

            System.out.println(rs.getString("id"));
        }
    }

}
