import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class View extends JFrame {
    JLabel idObat = new JLabel("ID Obat ");
    JLabel namaObat = new JLabel("Nama Obat ");
    JLabel stockObat = new JLabel("Stock");
    JLabel hargaObat = new JLabel("Harga (per strip)");
    JLabel ph = new JLabel("Placeholder Text");
    JLabel jumlah = new JLabel("Jumlah");
    JLabel totalHarga = new JLabel("Total Harga");
    JLabel nominal = new JLabel();

    JTextField txtId = new JTextField();
    JTextField txtNama = new JTextField();
    JTextField txtStock = new JLabel();
    JLabel txtHarga = new JLabel();
    JTextField txtJumlah = new JTextField();

    JPanel panGambar = new JPanel();

    JButton beli = new JButton("Beli");
    JButton pay = new JButton("Bayar");
    JButton riwayat = new JButton("Riwayat Transaksi");
    JButton logout = new JButton("Logout");

    JTable tabel;
        DefaultTableModel tableModel; // untuk tabel
        JScrollPane scrollPane;  // untuk scroll
        Object namaKolom[] = {"ID", "Nama Obat","Stock","Harga"};

    public View()
    {
        setLayout(null);
        //+------------------------------------------+ displaying things
        add(idObat);
        idObat.setBounds(10,10,80,20);
        add(txtId);
        txtId.setBounds(140, 10,80,20);
        add(namaObat);
        namaObat.setBounds(10,40,80,20);
        add(txtNama);
        txtNama.setBounds(140,40,390,20);
        add(stockObat);
        stockObat.setBounds(10,70,80,20);
        add(txtStock);
        txtStock.setBounds(140,70,60,20);
        add(hargaObat);
        hargaObat.setBounds(10, 100,100,20);
        add(txtHarga);
        txtHarga.setBounds(140,100,60,20);
        add(beli);
        beli.setBounds(10,210,80,20);
        //+------------------------------------------+ add jpanel untuk gambarnya
        add(panGambar);
        panGambar.setBounds(540,10,170,170);
        panGambar.add(ph);
        panGambar.setBackground(Color.white);
        ph.setBounds(540,10,20,20);
        //+-------------------------------------------+ jtable dari database
        tableModel = new DefaultTableModel(namaKolom, 0); //0 baris utk awalan
        tabel = new JTable(tableModel);
        scrollPane = new JScrollPane(tabel);
        tabel.setBounds(10,250,700,300);
        add(scrollPane);
        scrollPane.setBounds(10,250,700,300);

        add(riwayat);
        riwayat.setBounds(400, 600,170,20);
        //+-------------------------------------------+ tombol logout
        add(logout);
        logout.setBounds(600, 600, 80, 20);

        //+-------------------------------------------+ jframe
        setTitle("Apotek Online Hijau Farma");
        setSize(750,700);
        setLocationRelativeTo(null);;
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

}
