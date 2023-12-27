import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.JTextField;

public class Inventory extends JPanel {
	private Main parent;
	private JTable productTable;
	private JPanel contentPanel;
	private String cellselected;
	private int[] rowselected;
	private int[] columnselected;
	private String itemselected;
	private JButton deleteButton;
	private JButton editButton;
	private JButton finishEditingButton;
	private SearchPanel searchPanel;
	private String category;
	private boolean isEditing = false;
	JFrame f;

	Inventory(Main parent) {
		Icon editIcon = new ImageIcon("images/editbutton.png");
		Icon editIconHover = new ImageIcon("images/edithover.png");
		Icon saveIcon = new ImageIcon("images/saveicon.png");
		Icon saveIconHover = new ImageIcon("images/savehover.png");
		Icon applyIcon = new ImageIcon("images/applyicon.png");
		Icon applyIconHover = new ImageIcon("images/applyhover.png");
		Icon finishEditingIcon = new ImageIcon("images/finisheditingicon.png");
		Icon finishEditingHover = new ImageIcon("images/finisheditinghover.png");
		Icon deleteIcon = new ImageIcon("images/newdeleteicon.png");
		Icon deleteIconHover = new ImageIcon("images/newdeletehover.png");
		Icon exportIcon = new ImageIcon("images/newexporticon.png");
		Icon exportHover = new ImageIcon("images/exporthover.png");
		this.parent = parent;

		setBounds(0, 0, 1366, 642);
		setLayout(null);

		contentPanel = new JPanel();
		contentPanel.setBounds(48, 85, 1240, 388);
		contentPanel.setLayout(null);

		JButton exportToExcelButton = new JButton("Export to Excel");
		exportToExcelButton.setIcon(exportIcon);
		exportToExcelButton.setHorizontalAlignment(SwingConstants.LEFT);
		exportToExcelButton.setBounds(510, 34, 240, 35);
		exportToExcelButton.setFocusPainted(false);
		exportToExcelButton.setOpaque(false);
		exportToExcelButton.setBorderPainted(false);
		exportToExcelButton.setContentAreaFilled(false);
		add(exportToExcelButton);
		exportToExcelButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				exportToExcelButton.setIcon(exportHover);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				exportToExcelButton.setIcon(exportIcon);
			}
		});
		exportToExcelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exportToCSV();
			}
		});

		productTable = new JTable();
		setModel(parent.account.getInventory());
		productTable.setBorder(new LineBorder(new Color(0, 0, 0)));
		productTable.setFont(new Font("Tahoma", Font.PLAIN, 14));
		productTable.setRowHeight(39);
		productTable.setEnabled(false);
		productTable.getTableHeader().setReorderingAllowed(false);
		centerTable();

		adjustColumnWidth(0, 110);
		adjustColumnWidth(1, 160);
		adjustColumnWidth(2, 90);
		adjustColumnWidth(3, 110);
		adjustColumnWidth(4, 160);
		adjustColumnWidth(5, 110);

		ListSelectionModel select = productTable.getSelectionModel();
		select.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		select.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				cellselected = null;
				rowselected = productTable.getSelectedRows();
				columnselected = productTable.getSelectedColumns();

				if (rowselected.length > 0 && columnselected.length > 0) {
					for (int i = 0; i < rowselected.length; i++) {
						for (int j = 0; j < columnselected.length; j++) {
							cellselected = (String) productTable.getValueAt(rowselected[i], columnselected[j]);
							System.out.println(cellselected);
						}
					}
					itemselected = (String) productTable.getValueAt(rowselected[0], 0);
					System.out.println(itemselected);
					deleteButton.setVisible(true);
				} else {
					deleteButton.setVisible(false);
				}
			}
		});

		JTableHeader header = productTable.getTableHeader();
		header.setFont(new Font("Tahoma", Font.PLAIN, 20));
		header.setBackground(Color.decode("#29B5A7"));
		header.setForeground(Color.BLACK);

		JScrollPane scrollPane = new JScrollPane(productTable);
		scrollPane.setBounds(40, 11, 1178, 338);
		contentPanel.add(scrollPane);

		searchPanel = new SearchPanel();
		add(searchPanel);

		JTextField searchField = searchPanel.getSearchField();
		searchField.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				performSearch();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				performSearch();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				performSearch();
			}
		});

		JLabel lineImage = new JLabel("");
		lineImage.setIcon(new ImageIcon("images/LINE.png"));
		lineImage.setBounds(47, 550, 1254, 14);
		add(lineImage);

		JButton savebutton = new JButton("SAVE");
		savebutton.setIcon(saveIcon);
		savebutton.setBounds(70, 480, 177, 35);
		savebutton.setHorizontalAlignment(SwingConstants.LEFT);
		savebutton.setOpaque(false);
		savebutton.setFocusPainted(false);
		savebutton.setBorderPainted(false);
		savebutton.setContentAreaFilled(false);
		add(savebutton);
		savebutton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				savebutton.setIcon(saveIconHover);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				savebutton.setIcon(saveIcon);
			}
		});

		savebutton.setVisible(true);
		savebutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				for (int i = 0; i < productTable.getRowCount(); i++) {
					SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
					Date date = new Date();
					String datetime = formatter.format(date);

					String itemname = (String) productTable.getValueAt(i, 0);
					String itemquantity = (String) productTable.getValueAt(i, 1);
					String itemminval = (String) productTable.getValueAt(i, 2);
					String itemrestock = (String) productTable.getValueAt(i, 3);
					String itemprice = (String) productTable.getValueAt(i, 4);

					int iquantity = Integer.parseInt(itemquantity);
					int iminval = Integer.parseInt(itemminval);
					int irestock = Integer.parseInt(itemrestock);
					double iprice = Double.parseDouble(itemprice);

					Item item = parent.account.getItem(itemname);

					if (iquantity < 0 || iminval < 0 || irestock < 0 || iprice < 0) {
						new Error("Cannot save negtive values.");
					} else {
						if (!Integer.toString(item.getonStock()).equals(itemquantity)) {
							parent.account.addRecord(
									itemname + " changed quantity from " + item.getonStock() + " to " + itemquantity
											+ " " + datetime);
							item.setQuantity(Integer.parseInt(itemquantity));
							System.out.println("quantt changed");
						}
						if (!Integer.toString(item.getminValue()).equals(itemminval)) {
							parent.account.addRecord(
									itemname + " changed minimum quantity from " + item.getminValue() + " to "
											+ itemminval + " " + datetime);
							item.setminValue(Integer.parseInt(itemminval));
						}
						if (!Integer.toString(item.getrsAmount()).equals(itemrestock)) {
							parent.account.addRecord(
									itemname + " changed restock amount from " + item.getrsAmount() + " to "
											+ itemrestock + " " + datetime);
							item.setrsAmount(Integer.parseInt(itemrestock));
						}
						if (item.getunitPrice() != Double.parseDouble(itemprice)) {
							parent.account.addRecord(
									itemname + " changed unit price from " + item.getunitPrice() + " to " + itemprice
											+ " " + datetime);
							item.setunitPrice(Double.parseDouble(itemprice));
						}
					}

				}
				try {
					Database.updateAccount(parent.account);
				} catch (ClassNotFoundException | IOException e1) {
					e1.printStackTrace();
				}

				parent.updateNotification();
				parent.updateRecord();

			}
		});
		editButton = new JButton("EDIT");
		editButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				editButton.setIcon(editIconHover);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				editButton.setIcon(editIcon);
			}
		});
		editButton.setIcon(editIcon);
		editButton.setBounds(1090, 480, 177, 35);
		editButton.setHorizontalAlignment(SwingConstants.LEFT);
		editButton.setOpaque(false);
		editButton.setFocusPainted(false);
		editButton.setBorderPainted(false);
		editButton.setContentAreaFilled(false);
		editButton.setVisible(true);
		add(editButton);
		editButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				isEditing = !isEditing;
				if (isEditing) {
					finishEditingButton.setVisible(true);
					savebutton.setVisible(false);
					deleteButton.setVisible(true);
					productTable.setEnabled(true);
					editButton.setVisible(false);
				}
			}
		});
		finishEditingButton = new JButton("FINISH EDITING");
		// finishEditingButton.setBackground(new Color(41, 181, 167));
		finishEditingButton.setIcon(finishEditingIcon);
		finishEditingButton.setBounds(1090, 480, 177, 35);
		finishEditingButton.setHorizontalAlignment(SwingConstants.LEFT);
		finishEditingButton.setOpaque(false);
		finishEditingButton.setFocusPainted(false);
		finishEditingButton.setBorderPainted(false);
		finishEditingButton.setContentAreaFilled(false);
		add(finishEditingButton);
		finishEditingButton.setVisible(false);
		finishEditingButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				finishEditingButton.setIcon(finishEditingHover);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				finishEditingButton.setIcon(finishEditingIcon);
			}
		});
		finishEditingButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				isEditing = !isEditing;

				if (!isEditing) {
					productTable.setEnabled(false);
					finishEditingButton.setVisible(false);
					savebutton.setVisible(true);
					deleteButton.setVisible(false);
					editButton.setVisible(true);
				}

				productTable.clearSelection();
			}
		});

		deleteButton = new JButton("DELETE ROW");
		deleteButton.setVisible(false);
		deleteButton.setIcon(deleteIcon);
		deleteButton.setBounds(860, 480, 177, 35);
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
					if (rowselected != null && rowselected.length > 0) {

						SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
						Date date = new Date();
						String datetime = formatter.format(date);
						Item i = parent.account.getItem(itemselected);
						parent.account.addRecord(i.getproductName() + " was deleted " + " " + datetime);
						parent.account.removeItem(itemselected);

						try {
							Database.updateAccount(parent.account);
						} catch (ClassNotFoundException | IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

						if (category == null || category.equals("Default")) {
							setModel(parent.account.getInventory());
						} else {
							setModel(setFilter(category, parent));
						}

						productTable.clearSelection();
						deleteButton.setVisible(false);

						centerTable();
					}
				} else if (selection == 1) {
					System.out.println("poginichan");
				}
			}
		});

		JButton applyButton = new JButton("Apply");
		applyButton.setBackground(new Color(41, 181, 167));
		applyButton.setVisible(true);
		applyButton.setIcon(applyIcon);
		applyButton.setBounds(365, 38, 120, 28);
		applyButton.setHorizontalAlignment(SwingConstants.LEFT);
		applyButton.setOpaque(false);
		applyButton.setFocusPainted(false);
		applyButton.setBorderPainted(false);
		applyButton.setContentAreaFilled(false);
		searchPanel.add(applyButton);
		applyButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				applyButton.setIcon(applyIconHover);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				applyButton.setIcon(applyIcon);
			}
		});
		applyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				category = searchPanel.getCategory();

				searchPanel.getSearchField().setText("");

				if (category == "Default") {
					setModel(parent.account.getInventory());
				} else {
					setModel(setFilter(category, parent));
				}
				centerTable();
			}
		});

		JLabel searchIcon = new JLabel("");
		searchIcon.setBounds(1204, 36, 30, 28);
		searchPanel.add(searchIcon);
		searchIcon.setIcon(new ImageIcon("images/SEARCH.png"));

		add(contentPanel);
	}

	private void adjustColumnWidth(int columnIndex, int width) {
		TableColumn column = productTable.getColumnModel().getColumn(columnIndex);
		column.setPreferredWidth(width);
	}

	private void centerTable() {
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

		for (int columnIndex = 0; columnIndex < productTable.getColumnCount(); columnIndex++) {
			productTable.getColumnModel().getColumn(columnIndex).setCellRenderer(centerRenderer);
		}
	}

	public void setModel(String[][] dataarray) {
		String data[][] = dataarray;
		String column[] = { "Product Name", "Quantity", "Min Value", "Re-Stock Amount", "Unit Price",
				"Total Unit Value" };

		productTable.setModel(new DefaultTableModel(data, column));
	}

	private String[][] setFilter(String category, Main parent) {
		ArrayList<Item> items = parent.account.getItems();
		ArrayList<Item> filteredItems = new ArrayList<>();

		items.forEach(i -> {
			if (i.getCategory().equals(category)) {
				filteredItems.add(i);
			}
		});

		return filterArrayList(filteredItems);
	}

	private String[][] filterArrayList(ArrayList<Item> filteredItems) {
		String[][] array = { null, null, null, null, null, null, null, null };
		if (!filteredItems.isEmpty()) {
			array = new String[filteredItems.size()][6];
			for (int i = 0; i < filteredItems.size(); i++) {
				array[i][0] = filteredItems.get(i).getproductName();
				array[i][1] = Integer.toString(filteredItems.get(i).getonStock());
				array[i][2] = Integer.toString(filteredItems.get(i).getminValue());
				array[i][3] = Integer.toString(filteredItems.get(i).getrsAmount());
				array[i][4] = Double.toString(filteredItems.get(i).getunitPrice());
				array[i][5] = Double
						.toString(filteredItems.get(i).getunitPrice() * filteredItems.get(i).getonStock());
			}
		}

		return array;
	}

	private void performSearch() {
		String searchText = searchPanel.getSearchText().toLowerCase();

		if (!searchText.isEmpty()) {
			String[][] filteredData = filterData(searchText, parent, category);
			setModel(filteredData);
		} else {
			if (category == null || category.equals("Default")) {
				setModel(parent.account.getInventory());
			} else {
				setModel(setFilter(category, parent));
			}
		}

		centerTable();
	}

	private String[][] filterData(String searchText, Main parent, String category) {
		ArrayList<Item> items = parent.account.getItems();
		ArrayList<Item> filteredItems = new ArrayList<>();

		items.forEach(i -> {
			if (i.getproductName().toLowerCase().startsWith(searchText) &&
					(category == null || category.equals("Default") || i.getCategory().equals(category))) {
				filteredItems.add(i);
			}
		});

		return filterArrayList(filteredItems);
	}

	private void exportToCSV() {
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd_HHmmss");
			Date date = new Date();
			String fileName = "Inventory_" + formatter.format(date) + ".csv";

			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setSelectedFile(new File(fileName));
			int userSelection = fileChooser.showSaveDialog(this);

			if (userSelection == JFileChooser.APPROVE_OPTION) {
				File fileToSave = fileChooser.getSelectedFile();
				FileWriter csvWriter = new FileWriter(fileToSave);

				for (int col = 0; col < productTable.getColumnCount(); col++) {
					csvWriter.append(productTable.getColumnName(col));
					if (col < productTable.getColumnCount() - 1) {
						csvWriter.append(",");
					}
				}
				csvWriter.append("\n");

				for (int row = 0; row < productTable.getRowCount(); row++) {
					for (int col = 0; col < productTable.getColumnCount(); col++) {
						csvWriter.append(String.valueOf(productTable.getValueAt(row, col)));
						if (col < productTable.getColumnCount() - 1) {
							csvWriter.append(",");
						}
					}
					csvWriter.append("\n");
				}

				csvWriter.flush();
				csvWriter.close();

				JOptionPane.showMessageDialog(this, "Data exported to " + fileToSave.getAbsolutePath(),
						"Export Successful", JOptionPane.INFORMATION_MESSAGE);
			}

		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Error exporting data", "Export Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}