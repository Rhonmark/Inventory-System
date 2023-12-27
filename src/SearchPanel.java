import java.awt.Color;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SearchPanel extends JPanel {
	private JComboBox<String> categoryComboBox;
	private JTextField searchTextField;

	SearchPanel() {
		setBounds(0, 0, 1342, 94);
		setLayout(null);

		JLabel categoryLabel = new JLabel("Category");
		categoryLabel.setBounds(68, 38, 88, 28);
		add(categoryLabel);
		categoryLabel.setFont(new Font("Tahoma", Font.BOLD, 15));

		String[] categoryOptions = { "Default", "Meat", "Condiment", "Drinks", "Container" };
		categoryComboBox = new JComboBox<String>(categoryOptions);
		categoryComboBox.setBounds(154, 38, 202, 28);
		add(categoryComboBox);
		categoryComboBox.setBackground(Color.decode("#29B5A7"));

		searchTextField = new JTextField();
		searchTextField.setBounds(780, 38, 414, 28);
		add(searchTextField);
		searchTextField.setBackground(new Color(0, 0, 0));
		searchTextField.setForeground(Color.white);
		searchTextField.setColumns(10);
		searchTextField.setCaretColor(Color.WHITE);
	}

	public String getCategory() {
		return (String) this.categoryComboBox.getSelectedItem();
	}

	public String getSearchText() {
		return searchTextField.getText();
	}

	public JTextField getSearchField() {
		return searchTextField;
	}
}
