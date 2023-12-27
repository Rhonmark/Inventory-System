import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class StockEntry extends JPanel {
	private JTextField productNameField;
	private JTextField quantityField;
	private JTextField minValueField;
	private JTextField reStockAmountField;
	private JTextField unitPriceField;
	private JComboBox<String> categoryComboBox;
	private JTable productTable;
	private ArrayList<Item> addedItems;
	private int[] rowselected;
	private JPanel productTablePanel;
	private JTableHeader header;
	private JScrollPane scrollPane;
	private JButton deleteButton;
	private DefaultTableModel model;
	private JButton clear;

	StockEntry(Main parent) {
		Icon addIcon = new ImageIcon("images/addicon.png");
		Icon addIconHover = new ImageIcon("images/addhover.png");
		Icon saveIcon = new ImageIcon("images/saveicon.png");
		Icon saveIconHover = new ImageIcon("images/savehover.png");
		Icon deleteIcon = new ImageIcon("images/newdeleteicon.png");
		Icon deleteIconHover = new ImageIcon("images/newdeletehover.png");
		Icon clearIcon = new ImageIcon("images/clearicon.png");
		Icon clearHover = new ImageIcon("images/clearhover.png");

		addedItems = new ArrayList<Item>();
		setBounds(0, 0, 1366, 642);
		setLayout(null);

		JPanel productInfoPanel = new JPanel();
		productInfoPanel.setBackground(new Color(41, 181, 167));
		productInfoPanel.setBounds(60, 80, 294, 359);
		add(productInfoPanel);
		productInfoPanel.setLayout(null);

		JLabel lineImage = new JLabel("");
		lineImage.setIcon(new ImageIcon("images/LINE.png"));
		lineImage.setBounds(47, 550, 1254, 14);
		add(lineImage);

		JLabel reStockAmountLabel = new JLabel("ReStock Amount");
		reStockAmountLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		reStockAmountLabel.setBounds(30, 192, 143, 14);
		productInfoPanel.add(reStockAmountLabel);

		JLabel minValueLabel = new JLabel("Min Value");
		minValueLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		minValueLabel.setBounds(30, 149, 94, 14);
		productInfoPanel.add(minValueLabel);

		JLabel quantityLabel = new JLabel("Quantity");
		quantityLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		quantityLabel.setBounds(30, 111, 83, 20);
		productInfoPanel.add(quantityLabel);

		JLabel productNameLabel = new JLabel("Product Name");
		productNameLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		productNameLabel.setBounds(30, 71, 119, 14);
		productInfoPanel.add(productNameLabel);

		JLabel unitPriceLabel = new JLabel("Unit Price");
		unitPriceLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		unitPriceLabel.setBounds(30, 231, 104, 14);
		productInfoPanel.add(unitPriceLabel);

		JLabel categoryLabel = new JLabel("Category");
		categoryLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		categoryLabel.setBounds(30, 271, 104, 20);
		productInfoPanel.add(categoryLabel);

		productNameField = new JTextField();
		productNameField.setBounds(168, 70, 102, 20);
		productInfoPanel.add(productNameField);
		productNameField.setColumns(10);

		quantityField = new JTextField();
		quantityField.setBounds(168, 110, 102, 20);
		productInfoPanel.add(quantityField);
		quantityField.setColumns(10);
		quantityField.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();

				if (!Character.isDigit(c) && c != KeyEvent.VK_BACK_SPACE) {
					e.consume();
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}
		});

		minValueField = new JTextField();
		minValueField.setBounds(168, 148, 102, 20);
		productInfoPanel.add(minValueField);
		minValueField.setColumns(10);
		minValueField.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();

				if (!Character.isDigit(c) && c != KeyEvent.VK_BACK_SPACE) {
					e.consume();
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}
		});

		reStockAmountField = new JTextField();
		reStockAmountField.setBounds(168, 191, 102, 20);
		productInfoPanel.add(reStockAmountField);
		reStockAmountField.setColumns(10);
		reStockAmountField.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();

				if (!Character.isDigit(c) && c != KeyEvent.VK_BACK_SPACE) {
					e.consume();
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}
		});

		unitPriceField = new JTextField();
		unitPriceField.setBounds(168, 230, 102, 20);
		productInfoPanel.add(unitPriceField);
		unitPriceField.setColumns(10);
		unitPriceField.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!Character.isDigit(c) && c != '.' && c != KeyEvent.VK_BACK_SPACE) {
					e.consume();
				}
				if (c == '.' && unitPriceField.getText().contains(".")) {
					e.consume();
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}
		});

		String[] categoryOptions = { "None", "Meat", "Condiment", "Drinks", "Container" };
		categoryComboBox = new JComboBox<String>(categoryOptions);
		categoryComboBox.setBounds(168, 271, 102, 20);
		productInfoPanel.add(categoryComboBox);

		setTable();

		JButton addButton = new JButton("ADD");
		addButton.setIcon(addIcon);
		addButton.setBounds(45, 450, 177, 35);
		addButton.setHorizontalAlignment(SwingConstants.LEFT);
		addButton.setOpaque(false);
		addButton.setFocusPainted(false);
		addButton.setBorderPainted(false);
		addButton.setContentAreaFilled(false);
		add(addButton);
		addButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				addButton.setIcon(addIconHover);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				addButton.setIcon(addIcon);
			}
		});
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String pName = productNameField.getText().toLowerCase();
				int pQuantity = Integer.parseInt(quantityField.getText());
				int pMinValue = Integer.parseInt(minValueField.getText());
				int pRSAmount = Integer.parseInt(reStockAmountField.getText());
				double pPrice = Double.parseDouble(unitPriceField.getText());
				String category = (String) categoryComboBox.getSelectedItem();

				Item item = new Item(pName, pQuantity, pMinValue, pRSAmount, pPrice);
				item.setCategory(category);

				if (!parent.account.isItemPresent(item)) {
					addedItems.add(item);

					setModel();
					productTable.setModel(model);
					centerTable();

					deleteButton.setVisible(true);
				}

			}
		});

		JButton saveButton = new JButton("SAVE");
		saveButton.setIcon(saveIcon);
		saveButton.setBounds(1110, 450, 177, 35);
		saveButton.setHorizontalAlignment(SwingConstants.LEFT);
		saveButton.setOpaque(false);
		saveButton.setFocusPainted(false);
		saveButton.setBorderPainted(false);
		saveButton.setContentAreaFilled(false);
		add(saveButton);
		saveButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				saveButton.setIcon(saveIconHover);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				saveButton.setIcon(saveIcon);
			}
		});
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
				Date date = new Date();
				String datetime = formatter.format(date);

				addedItems.forEach(item -> {
					parent.account.addItem(item);
					parent.account.addRecord(item.getproductName() + " created at " + datetime);
				});

				try {
					Database.updateAccount(parent.account);
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				parent.updateInventory();

				addedItems.clear();
				String[] column = { "Product Name", "Quantity", "Min Value", "Re-Stock Amount",
						"Unit Price" };
				productTable.setModel(new DefaultTableModel(addItemtoTable(addedItems), column));

				parent.updateRecord();
				parent.updateNotification();
			}
		});

		deleteButton = new JButton("DELETE ROW");
		deleteButton.setVisible(false);
		deleteButton.setIcon(deleteIcon);
		deleteButton.setBounds(940, 450, 177, 35);
		deleteButton.setHorizontalAlignment(SwingConstants.LEFT);
		deleteButton.setOpaque(false);
		deleteButton.setFocusPainted(false);
		deleteButton.setBorderPainted(false);
		deleteButton.setContentAreaFilled(false);
		add(deleteButton);
		deleteButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				deleteButton.setIcon(deleteIconHover);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				deleteButton.setIcon(deleteIcon);
			}
		});
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String[] options = { "Yes", "No" };
				int selection = JOptionPane.showOptionDialog(null, "Are you sure you want to delete this row?", ".",
						0, 3, null, options, options[0]);

				if (selection == 0) {
					addedItems.remove(rowselected[0]);
					setModel();
					productTable.setModel(model);
					centerTable();
				}else if (selection == 1) {
					System.out.println("draepogi");
				}
			}
		});

		clear = new JButton("CLEAR");
		// clear.setBackground(new Color(41, 181, 167));
		clear.setIcon(clearIcon);
		clear.setBounds(225, 453, 120, 35);
		clear.setHorizontalAlignment(SwingConstants.LEFT);
		clear.setOpaque(false);
		clear.setFocusPainted(false);
		clear.setBorderPainted(false);
		clear.setContentAreaFilled(false);
		clear.setVisible(true);
		add(clear);
		clear.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				clear.setIcon(clearHover);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				clear.setIcon(clearIcon);
			}
		});
		clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				productNameField.setText("");
				quantityField.setText("");
				minValueField.setText("");
				reStockAmountField.setText("");
				unitPriceField.setText("");
				categoryComboBox.getModel().setSelectedItem("None");
			}
		});

		ListSelectionModel select = productTable.getSelectionModel();
		select.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		select.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				rowselected = productTable.getSelectedRows();
			}
		});
	}

	private void adjustColumnWidth(int columnIndex, int width) {
		TableColumn column = productTable.getColumnModel().getColumn(columnIndex);
		column.setPreferredWidth(width);
	}

	private void setTable() {
		productTablePanel = new JPanel();
		productTablePanel.setBounds(378, 80, 943, 359);
		add(productTablePanel);
		productTablePanel.setLayout(null);

		productTable = makeTable();

		header = productTable.getTableHeader();
		header.setFont(new Font("Tahoma", Font.PLAIN, 20));
		header.setBackground(Color.decode("#29B5A7"));
		header.setForeground(Color.BLACK);

		adjustColumnWidth(0, 110);
		adjustColumnWidth(1, 160);
		adjustColumnWidth(2, 90);
		adjustColumnWidth(3, 110);
		adjustColumnWidth(4, 160);

		productTable.setRowHeight(39);
		productTable.getTableHeader().setReorderingAllowed(false);

		scrollPane = new JScrollPane(productTable);
		scrollPane.setBounds(40, 11, 873, 338);
		productTablePanel.add(scrollPane);
	}

	private String[][] addItemtoTable(ArrayList<Item> addedItems) {
		String[][] array = {};
		if (!addedItems.isEmpty()) {
			array = new String[addedItems.size()][5];
			for (int i = 0; i < addedItems.size(); i++) {
				array[i][0] = addedItems.get(i).getproductName();
				array[i][1] = Integer.toString(addedItems.get(i).getonStock());
				array[i][2] = Integer.toString(addedItems.get(i).getminValue());
				array[i][3] = Integer.toString(addedItems.get(i).getrsAmount());
				array[i][4] = Double.toString(addedItems.get(i).getunitPrice());
			}
		}
		return array;
	}

	private JTable makeTable() {

		JTable table = new JTable();
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		setModel();
		table.setModel(model);

		return table;
	}

	private void setModel() {
		String[] column = { "Product Name", "Quantity", "Min Value", "Re-Stock Amount",
				"Unit Price" };
		model = new DefaultTableModel(addItemtoTable(addedItems), column) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
	}

	private void centerTable() {
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

		for (int columnIndex = 0; columnIndex < productTable.getColumnCount(); columnIndex++) {
			productTable.getColumnModel().getColumn(columnIndex).setCellRenderer(centerRenderer);
		}
	}
}