import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
import java.util.ArrayList;

public class Notification extends JPanel {
    Notification(Main parent) {
        setBounds(0, 0, 1366, 642);
        setLayout(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setPreferredSize(new Dimension(1366, 1000));

        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setViewportBorder(new LineBorder(new Color(0, 0, 0)));
        scrollPane.setBounds(50, 50, 1266, 502);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        setNotifications(parent, panel);

        add(scrollPane);
    }

    private void setNotifications(Main parent, JPanel panel) {
        ArrayList<Item> items = parent.account.getItems();

        int y = 25;
        for (Item i: items){
            if (i.getonStock() == 0){
                JPanel panel_1 = new JPanel();
                panel_1.setBackground(new Color(41, 181, 167));
                panel_1.setBounds(26, y, 1174, 133);
                panel_1.setLayout(null);

                JLabel notif = new JLabel(i.getproductName() + " is out of Stock.");
                notif.setBounds(180, 0, 1174, 133);
                notif.setFont(new Font("sansSerif", Font.ITALIC, 36));

                panel_1.add(notif);
                panel.add(panel_1);

                y = y + 100;
            }else if (i.getonStock() < i.getminValue()) {
                JPanel panel_1 = new JPanel();
                panel_1.setBackground(new Color(41, 181, 167));
                panel_1.setBounds(26, y, 1174, 133);
                panel_1.setLayout(null);

                JLabel notif = new JLabel(i.getproductName() + " is low on Stock.");
                notif.setBounds(180, 0, 1174, 133);
                notif.setFont(new Font("sansSerif", Font.ITALIC, 36));

                panel_1.add(notif);
                panel.add(panel_1);

                y = y + 150;
            }
        }
    }

    public void updateContent() {
    }
}
