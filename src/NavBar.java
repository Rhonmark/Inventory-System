import java.awt.Color;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class NavBar extends JPanel {
    private JLabel inventorylabel;
    private JLabel stockentrylabel;
    private JLabel notificationlabel;
    private JLabel recordlabel;

    NavBar(Main parent) {
        setBackground(new Color(0, 0, 0));
        setBounds(0, 0, 1366, 126);

        JLabel logo = new JLabel(new ImageIcon("images/LOGO.png"));
        logo.setBounds(10, 0, 186, 118);

        inventorylabel = new JLabel("INVENTORY");
        inventorylabel.setBounds(322, 48, 102, 23);
        inventorylabel.setFont(new Font("Tahoma", Font.PLAIN, 19));
        inventorylabel.setForeground(new Color(41, 181, 167));

        inventorylabel.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                changeColor(inventorylabel);
                parent.setVisible(parent.getInvPanel());
                parent.changePanel(parent.getInvPanel());
            }
        });

        stockentrylabel = new JLabel("STOCK ENTRY");
        stockentrylabel.setBounds(524, 48, 121, 23);
        stockentrylabel.setFont(new Font("Tahoma", Font.PLAIN, 19));
        stockentrylabel.setForeground(new Color(255, 255, 255));
        stockentrylabel.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                changeColor(stockentrylabel);
                parent.setVisible(parent.getStockEntryPanel());
                parent.changePanel(parent.getStockEntryPanel());

            }
        });

        notificationlabel = new JLabel("NOTIFICATION");
        notificationlabel.setBounds(759, 48, 127, 23);
        notificationlabel.setFont(new Font("Tahoma", Font.PLAIN, 19));
        notificationlabel.setForeground(new Color(255, 255, 255));
        notificationlabel.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                changeColor(notificationlabel);
                parent.setVisible(parent.getNotificationPanel());
                parent.changePanel(parent.getNotificationPanel());
            }
        });

        recordlabel = new JLabel("RECORD");
        recordlabel.setBounds(983, 48, 72, 23);
        recordlabel.setFont(new Font("Tahoma", Font.PLAIN, 19));
        recordlabel.setForeground(new Color(255, 255, 255));
        recordlabel.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                changeColor(recordlabel);
                parent.setVisible(parent.getRecordPanel());
                parent.changePanel(parent.getRecordPanel());
            }
        });

        JLabel signoutlabel = new JLabel("SIGN OUT");
        signoutlabel.setBounds(1152, 48, 86, 23);
        signoutlabel.setFont(new Font("Tahoma", Font.PLAIN, 19));
        signoutlabel.setForeground(new Color(255, 255, 255));
        signoutlabel.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                parent.dispose();
                new MainFrame();
            }
        });

        add(logo);
        add(inventorylabel);
        add(stockentrylabel);
        add(notificationlabel);
        add(recordlabel);
        add(signoutlabel);

        setLayout(null);
    }

    private void changeColor(JLabel label) {
        inventorylabel.setForeground(new Color(255, 255, 255));
        stockentrylabel.setForeground(new Color(255, 255, 255));
        notificationlabel.setForeground(new Color(255, 255, 255));
        recordlabel.setForeground(new Color(255, 255, 255));
        
        label.setForeground(new Color(41, 181, 167));
    }
}
