import javax.swing.*;
import java.io.*;
import java.sql.*;
import java.util.TimeZone;

public class Model {
        static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
        static final String DB_URL = "jdbc:mysql://localhost/apotek?serverTimezone=" + TimeZone.getDefault().getID();
        static final String USER = "root";
        static final String PASS = "";
        String username;
        Connection koneksi;
        Statement statement;
         //+-------------------------------------------+ connect ke database
        public Model()
        {
            try
            {
                Class.forName(JDBC_DRIVER);
                koneksi = (Connection) DriverManager.getConnection(DB_URL, USER, PASS);
                System.out.println("Koneksi Berhasil");
            }
            catch(Exception ex)
            {
                JOptionPane.showMessageDialog(null, ex.getMessage());
                System.out.println("Koneksi Gagal");
            }
        }
        public int login(String a , String b)
        {
            try
            {
                int jmlData = 0;
                username = a;
                statement = koneksi.createStatement();
                String query = "SELECT * FROM admin where username='"+a+"' and password='"+b+"'"; //pengambilan dara dalam java dari database
                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next())
                {
                    jmlData++;
                }
                if(jmlData > 0)
                {
                    JOptionPane.showMessageDialog(null, "Selamat datang, " + a + "!");
                    return 1;
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Username/Password salah!");
                    return 0;
                }
            }
            catch(SQLException e)
            {
                System.out.println(e.getMessage());
                System.out.println("SQL Error");
                return 0;
            }
        }

    public String getUsername() {
        return username;
    }

    //+-------------------------------------------+ read database
        public String[][] readData(){
            try{
                int jmlData = 0; // utk nampung jumlah data
                String data[][] = new String[getBanyakData()][4]; // baris blm diketahui, kolom 4
                String query = "SELECT * FROM obat";
                ResultSet res = statement.executeQuery(query); // menampilkan hasil execute query dengan ResultSet (karena >1)
                while(res.next()){
                    data[jmlData][0] = res.getString("id_Ob");
                    data[jmlData][1] = res.getString("namaOb");
                    data[jmlData][2] = res.getString("stock");
                    data[jmlData][3] = res.getString("harga");
                    jmlData++;
                }
                return data;
            }catch (Exception e){
                System.out.println(e.getMessage());
                System.out.println("SQL Error!");
                return null;
            }
        }
        //+-------------------------------------------+ read database transaksi
        public String[][] readTrans(){
            try{
                int jmlData = 0; // utk nampung jumlah data
                String data2[][] = new String[getBanyakData2()][5]; // baris blm diketahui, kolom 4
                String query = "SELECT * FROM transaksi where username = '"+getUsername()+"';";
                ResultSet res = statement.executeQuery(query); // menampilkan hasil execute query dengan ResultSet (karena >1)
                while(res.next()){
                    data2[jmlData][0] = res.getString("idTrans");
                    data2[jmlData][1] = res.getString("username");
                    data2[jmlData][2] = res.getString("id_Ob");
                    data2[jmlData][3] = res.getString("jumlah");
                    data2[jmlData][4] = res.getString("totalHarga");
                    jmlData++;
                }
                return data2;
            }catch (Exception e){
                System.out.println(e.getMessage());
                System.out.println("SQL Error!");
                return null;
            }
        }

        public int getBanyakData2() {
            int jmlData = 0;
            try{
                statement = koneksi.createStatement();
                String query = "SELECT * FROM transaksi WHERE username = '"+getUsername()+"';";
                ResultSet res = statement.executeQuery(query);
                while(res.next()){
                    jmlData++;
                }
                return jmlData;
            }catch (Exception e){
                System.out.println(e.getMessage());
                System.out.println("SQL Error!");
                return 0;
            }
        }
        //+-------------------------------------------+ mengambil jumlah data yang ada di tabel
        public int getBanyakData() {
            int jmlData = 0;
            try{
                statement = koneksi.createStatement();
                String query = "SELECT * FROM obat";
                ResultSet res = statement.executeQuery(query);
                while(res.next()){
                    jmlData++;
                }
                return jmlData;
            }catch (Exception e){
                System.out.println(e.getMessage());
                System.out.println("SQL Error!");
                return 0;
            }
        }
        //+-------------------------------------------+ create data in database
        public void insertData(String username, String id_Ob, int jumlah, int totalHarga){
            try{
                String query = "INSERT INTO transaksi(username, id_Ob, jumlah, totalHarga) VALUES ('"
                        +username+"', '"+id_Ob+"', "+jumlah+", "+totalHarga+");";
                //String '"+String+"' kalau Int "+int+"
                statement = (Statement)koneksi.createStatement(); // prepare statementnya
                statement.executeUpdate(query); // execute querynya
                System.out.println("Berhasil ditambahkan ke database!");
                JOptionPane.showMessageDialog(null,"Pembelian Anda telah tercatat!");
            }catch (Exception sql){
                System.out.println(sql.getMessage());
                JOptionPane.showMessageDialog(null, sql.getMessage());
            }
        }
        //+-------------------------------------------+ update data di database (jika user beli, stock akan berkurang)
        public void updateData(String id_Ob, int stock, int jml){
            try{
                if(stock!=0) {
                    String query = "UPDATE obat SET stock = " + (stock - jml) + " WHERE id_Ob = '" + id_Ob + "';";
                    statement = koneksi.createStatement();
                    statement.executeUpdate(query);

                    JOptionPane.showMessageDialog(null, "Pembelian berhasil!");
                }else{
                    JOptionPane.showMessageDialog(null, "Stock habis!");
                }
            }catch (Exception sql){
                System.out.println(sql.getMessage());
                JOptionPane.showMessageDialog(null,sql.getMessage());
            }
        }
}