import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class Main extends JFrame {
    Account account;
    private JPanel navBarPanel;
    private JLayeredPane layerpane;

    private JPanel inventorypanel;
    private JPanel stockentrypanel;
    private JPanel notificationpanel;
    private JPanel recordpanel;

    Main(Account account) {
        this.account = account;

        setBackground(new Color(255, 255, 255));
        setTitle("E-Dible");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1366, 768);
        setLocationRelativeTo(null);
        getContentPane().setLayout(null);
        ImageIcon img = new ImageIcon("images/LOGO.png");
        setIconImage(img.getImage());

        navBarPanel = new NavBar(this);
        add(navBarPanel);
        setupLayer();

        setVisible(true);
    }

    private void setupLayer() {
        layerpane = new JLayeredPane();
        layerpane.setBounds(0, 126, 1366, 642);
        inventorypanel = new Inventory(this);
        stockentrypanel = new StockEntry(this);
        notificationpanel = new Notification(this);
        recordpanel = new Record(this);

        layerpane.add(recordpanel, 4);
        layerpane.add(notificationpanel, 3);
        layerpane.add(stockentrypanel, 1);
        layerpane.add(inventorypanel, 0);

        setVisible(inventorypanel);

        add(layerpane);
    }

    public void changePanel(JPanel panel) {
        layerpane.moveToFront(panel);
    }

    public JPanel getInvPanel() {
        return this.inventorypanel;
    }

    public JPanel getStockEntryPanel() {
        return this.stockentrypanel;
    }

    public JPanel getNotificationPanel() {
        return this.notificationpanel;
    }

    public JPanel getRecordPanel() {
        return this.recordpanel;
    }

    public void updateInventory() {
        this.inventorypanel = new Inventory(this);
        layerpane.add(inventorypanel, 1);
    }

    public void updateRecord(){
        this.recordpanel = new Record(this);
        layerpane.add(recordpanel, 4);
    }

    public void updateNotification() {
        this.notificationpanel = new Notification(this);
        layerpane.add(notificationpanel, 1);
    }

    public void setVisible(JPanel panel) {
        JPanel[] components = { this.inventorypanel, this.stockentrypanel, this.notificationpanel, this.recordpanel };
        for (int i = 0; i < components.length; i++) {
            components[i].setVisible(false);
        }
        panel.setVisible(true);
    }

    public static void main(String[] args) throws ClassNotFoundException, IOException {
        ArrayList<Account> accounts = Database.loadDB();
        new Main(accounts.get(1));
        // Account acc = accounts.get(1);

        // ArrayList<String> records = acc.getRecords();
        // records.forEach(i -> System.out.println(i));
    }
}