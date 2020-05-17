import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Transaksi extends JFrame {
    JTable tabel2;
    DefaultTableModel tableModel2; // untuk tabel
    JScrollPane scrollPane2;  // untuk scroll
    Object namaKolom2[] = {"ID Trans", "Username", "ID Obat", "Jumlah", "Total Harga"};

    JButton back = new JButton("Kembali");

    public Transaksi() {
        setLayout(null);
        //+------------------------------------------+ displaying things
            tableModel2 = new DefaultTableModel(namaKolom2, 0); //0 baris utk awalan
            tabel2 = new JTable(tableModel2);
            scrollPane2 = new JScrollPane(tabel2);
            tabel2.setBounds(10, 30, 700, 300);
            add(scrollPane2);
            scrollPane2.setBounds(10, 10, 700, 300);

            add(back);
            back.setBounds(600,330,80,20);
            // jframe stuff
            setTitle("Apotek Online Hijau Farma");
            setSize(750, 700);
            setLocationRelativeTo(null);
            setVisible(true);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
        }
    }
