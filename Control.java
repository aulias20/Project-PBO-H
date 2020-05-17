import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Control {
    Model model;
    View view;
    Login log_in;
    Transaksi trans;


    public Control(Model model, View view, Login log_in, Transaksi trans) {
        this.model = model;
        this.view = view;
        this.log_in = log_in;
        this.trans = trans;

        view.setVisible(false);
        log_in.setVisible(true);
        trans.setVisible(false);

        log_in.login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int hasil;
                String a = log_in.getUsername();
                String b = log_in.getPassword();
                hasil = model.login(a,b);
                if(hasil == 1)
                {
                    view.setVisible(true);
                    log_in.setVisible(false);
                }
            }
        });
        //+-------------------------------------------+ ngambil data dari database
        if (model.getBanyakData() != 0) {
            String dataObat[][] = model.readData();
            view.tabel.setModel((new JTable(dataObat, view.namaKolom)).getModel());
        } else {
            JOptionPane.showMessageDialog(null, "Data Tidak Ada");
        }
        //+-------------------------------------------+ jika tombol kembali diklik
        trans.back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                trans.setVisible(false);
                view.setVisible(true);
            }
        });
        //+-------------------------------------------+ jika tabel diklik
        view.tabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int baris = view.tabel.getSelectedRow();
                //+-------------------------------------------+ mengambil data dari tabel ke textfield/label
                String kolom1 = (String) view.tabel.getValueAt(baris,0);
                view.txtId.setText(kolom1);
                String kolom2 = (String) view.tabel.getValueAt(baris,1);
                view.txtNama.setText(kolom2);
                String kolom3 = (String) view.tabel.getValueAt(baris,2);
                view.txtStock.setText(kolom3);
                String kolom4 = (String) view.tabel.getValueAt(baris,3);
                view.txtHarga.setText(kolom4);
            }
        });
        //+-------------------------------------------+ jika tombol logout diklik
        view.logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                log_in.txtusername.setText("");
                log_in.txtpassword.setText("");
                log_in.setVisible(true);
                view.setVisible(false);
                JOptionPane.showMessageDialog(null,"Anda telah logout!");
            }
        });
        //+-------------------------------------------+ jika tombol riw.trans diklik
        view.riwayat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (model.getBanyakData2() != 0) {
                    view.setVisible(false);
                    trans.setVisible(true);
                    String dataTrans[][] = model.readTrans();
                    trans.tabel2.setModel((new JTable(dataTrans, trans.namaKolom2)).getModel());
                } else {
                    JOptionPane.showMessageDialog(null, "Data Tidak Ada");
                }
            }
        });
        //+-------------------------------------------+ jika tombol beli diklik
        view.beli.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //+-------------------------------------------+ variabel2 jframe yang mau ditambah
                JLabel jumlah = new JLabel("Jumlah");
                JTextField txtJumlah = new JTextField();
                JLabel totalHarga = new JLabel("Total Harga");
                JLabel nominal = new JLabel();
                JButton pay = new JButton("Bayar");
                //+-------------------------------------------+ ditambahkan ke view
                view.add(jumlah);
                jumlah.setBounds(10,130,100,20);
                view.add(txtJumlah);
                txtJumlah.setBounds(140,130,60,20);
                view.add(totalHarga);
                totalHarga.setBounds(10,160,100,20);
                view.add(nominal);
                nominal.setBounds(140,160,100,20);
                view.add(pay);
                pay.setBounds(120,210,80,20);
                //+-------------------------------------------+ jika tombol bayar diklik
                pay.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        //+-------------------------------------------+ variabel variabel utk dikirim dan didisplay
                        String id = view.txtId.getText();
                        int s = Integer.parseInt(view.txtStock.getText());
                        int h = Integer.parseInt(view.txtHarga.getText());
                        int j = Integer.parseInt(txtJumlah.getText());
                        int totalBayar = h*j;
                        nominal.setText(String.valueOf(totalBayar));
                        //+-------------------------------------------+ memastikan kembali user mau beli
                        int input = JOptionPane.showConfirmDialog(null,
                                "Anda yakin ingin membeli " + view.txtNama.getText() + " sejumlah "+
                                        j +" strip?", "Pilih Opsi...", JOptionPane.YES_NO_OPTION);
                        //+-------------------------------------------+ jika YES
                        if (input == 0) {
                            //+-------------------------------------------+ database akan diupdate & ditampilkan yang baru
                            model.updateData(id,s,j);
                            model.insertData(log_in.getUsername(),id,j,totalBayar);
                            String dataMahasiswa[][] = model.readData();
                            view.tabel.setModel(new JTable(dataMahasiswa, view.namaKolom).getModel());
                        } else { //+-------------------------------------------+ jika NO
                            //+-------------------------------------------+ hanya sebuah pesan
                            JOptionPane.showMessageDialog(null, "Pembelian dibatalkan.");
                        }
                    }
                }); //+-------------------------------------------+ penutup action tombol bayar
            }
        }); //+-------------------------------------------+ penutup action tombol beli
    }
}