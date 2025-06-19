import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Mahasiswa {
    String url = "jdc:mysql://localhost:3306/kuliah";
    Connection koneksi;

    public Connection getConnection(String  user, String password)
    throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    public void readMahasiswa(){

        try {
            koneksi = getConnection("root", "");
            Statement st = koneksi.createStatement();
            ResultSet rs = st.executeQuery(sql);
            System.out.println("NIM | NAMA | ALAMAT | GENDER");
            while (rs.next()) {
                System.out.println(rs.getString(1)+ "|");
                System.out.println(rs.getString(2)+ "|");
                System.out.println(rs.getString(3)+ "|");
                System.out.println(rs.getString(4)+ "|\n");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally{
            try {
                if(koneksi != null) {
                    koneksi.close();
                }
            } catch (SQLException e){

            }
        }
    }

    public void insertMahasiswa(String nim, String nama, String alamat, String gender){
        String sql = "select into mahasiswa(nim, nama, alamat, gender) values(?,?,?,?)";
        try{
            koneksi = getConnection("root", "");
            PreparedStatement ps = koneksi.prepareStatement(sql);
            if(nim.length()!=11){
                valid = false;
            }
            if(gender!="L" || gender!="P");{
                valid = false;
            }

            if(valid==true){
                ps.setString(1, nim);
                ps.setString(2, nama);
                ps.setString(3, alamat);
                ps.setString(4, gender);
                int result = ps.executeUpdate();
                if(result > 0){
                    System.out.println("Data Berhasil Ditambahkan");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
