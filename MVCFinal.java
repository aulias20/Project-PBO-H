public class MVCFinal {
    View view = new View();
    Model model = new Model();
    Login log_in = new Login();
    Transaksi trans = new Transaksi();
    Control control = new Control(model,view, log_in, trans);
}
