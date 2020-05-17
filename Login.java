import javax.swing.*;

public class Login extends JFrame {
    JLabel lusername = new JLabel("Username");
    JLabel lpass = new JLabel("Password");

    JTextField txtusername = new JTextField();
    JPasswordField txtpassword = new JPasswordField();

    JButton login = new JButton("Login");
    public Login() {
        setLayout(null);
        add(lusername);
        add(lpass);
        add(txtusername);
        add(txtpassword);
        add(login);

        lusername.setBounds(50, 25, 100, 20);
        lpass.setBounds(50, 60, 100, 20);
        txtusername.setBounds(130, 25, 140, 20);
        txtpassword.setBounds(130, 60, 140, 20);
        login.setBounds(130, 100, 80, 20);

        setSize(350,200);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    String getUsername() { return txtusername.getText(); }
}
