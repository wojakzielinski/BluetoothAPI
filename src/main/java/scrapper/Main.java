package scrapper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Main {
    public static void main(String[] args) throws Exception {
        //String url = "jdbc:postgresql://77.55.192.99:5432/postgres";
        Class.forName("org.postgresql.Driver");
        String url = "jdbc:postgresql://77.55.192.99:5432/postgres?user=postgres&password=piwotomojepaliwo";
        String sql = "SELECT * FROM lekarze;";
        try(Connection conn = DriverManager.getConnection(url)){
            try(ResultSet rs = conn.prepareStatement(sql).executeQuery()){
                while(rs.next()){
                    System.out.println(rs.getString("nazwisko"));
                }
            }
        }
//        HtmlScrapper scrapper = new HtmlScrapper();
//        scrapper.getPersons(null,null,null,null);
    }
}
