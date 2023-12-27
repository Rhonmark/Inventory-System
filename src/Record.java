import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;

public class Record extends JPanel {

	Record(Main parent) {
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

		setRecords(parent, panel);

		add(scrollPane);
	}

	private void setRecords(Main parent, JPanel panel) {
		ArrayList<String> records = parent.account.getRecords();

		int y = 25;

		for (int i = records.size() - 1; i >= 0; i--) {
			JPanel panel_1 = new JPanel();
			panel_1.setBackground(new Color(41, 181, 167));
			panel_1.setBounds(26, y, 1174, 133);
			panel_1.setLayout(null);

			JLabel notif = new JLabel(records.get(i));
			notif.setBounds(180, 0, 1174, 133);
			notif.setFont(new Font("SansSerif", Font.ITALIC, 36));
			panel_1.add(notif);
			panel.add(panel_1);

			y = y + 150;
		}
	}

}
