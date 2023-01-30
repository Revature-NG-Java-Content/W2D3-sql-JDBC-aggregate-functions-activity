import java.sql.*;

public class ProduceRepo implements ProduceDAO{

    Connection conn ;

    public ProduceRepo() {

        String url = "jdbc:postgresql://localhost:5433/postgres?currentSchema=public";
        String username = "postgres";
        String password = "password";

        try {

            conn = DriverManager.getConnection(url,username,password);

        }catch(SQLException sqlException){

           System.out.println(sqlException);
        }

    }



    @Override
    public int getCount() {

        String sql = "SELECT COUNT(*) FROM produce";

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            return rs.getInt("count");


        } catch (SQLException sqlException) {
            System.out.println("Count function error: "+sqlException);
        }
        return 0;
    }



    @Override
    public int getSum() {

        // note you cannot add the (*) here because it will through an exception
        // you must include what column you want to "sum" up in your Query
        String sql = "SELECT SUM(quantity) FROM produce WHERE ptype = 'berry' ";

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            return rs.getInt("sum");


        } catch (SQLException sqlException) {
            System.out.println("Sum function error: "+sqlException);
        }
        return 0;
    }


    @Override
    public int getAverage() {
        return 0;
    }
}
