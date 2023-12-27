import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.geom.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.*;

import java.awt.event.MouseEvent;

public class Register extends JFrame {
	final private static Font mainFont = new Font("Inria Sans", Font.PLAIN, 40); // FONT
	final private static Font linksFont = new Font("Inria Sans", Font.PLAIN, 20);
	final private static Font hoverFont = new Font("Inria Sans", Font.BOLD, 20);

	public Register() {
		main(null);
	}

	public static void main(String[] args) {

		Icon createIcon = new ImageIcon("images/createIcon2.png");
		Icon createIconHover = new ImageIcon("images/createHover.png");
		// FRAME
		JFrame frame = new JFrame();
		frame.setTitle("Welcome");
		frame.setLayout(null);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setUndecorated(true);
		frame.setSize(580, 800);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setShape(new RoundRectangle2D.Double(0, 0, 580, 800, 70, 70));
		frame.setBackground(Color.BLACK);

		// CONTAINERS FOR SEPARATE ELEMENTS

		// BACKGROUND IMG
		Container a = frame.getContentPane();

		JLabel logo = new JLabel();
		logo.setIcon(new ImageIcon("images/logoLogin.png"));
		Dimension sizeA = logo.getPreferredSize();
		logo.setBounds(0, 30, sizeA.width, sizeA.height);

		Container b = frame.getContentPane();

		// TEXTS
		JLabel createAccount = new JLabel();
		createAccount.setText("create account");
		createAccount.setBounds(275, 160, 1000, 100);
		createAccount.setFont(mainFont);

		JLabel redirect = new JLabel();
		redirect.setText("login to existing account");
		redirect.setBounds(325, 600, 230, 23);
		redirect.setFont(linksFont);
		redirect.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		redirect.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				redirect.setFont(hoverFont);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				redirect.setFont(linksFont);
			}

			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				Login Login = new Login();
				Login.pack();
				frame.dispose();
			}
		});

		JLabel copyrightLink = new JLabel();
		copyrightLink.setText("Copyright © 2023 All Rights Reserved. CC BY-NC-ND License.");
		copyrightLink.setBounds(115, 735, 500, 100);

		// LINES
		JLabel line = new JLabel();
		line.setIcon(new ImageIcon("images/line2.png"));
		Dimension sizeLine = line.getPreferredSize();
		line.setBounds(0, 25, sizeLine.width, 1);

		JLabel secondLine = new JLabel();
		secondLine.setIcon(new ImageIcon("images/line2.png"));
		Dimension sizeSecondLine = secondLine.getPreferredSize();
		secondLine.setBounds(0, 768, sizeSecondLine.width, 1);

		Container c = frame.getContentPane();

		JTextField usernameInput = new JTextField();
		usernameInput.setBounds(35, 270, 500, 50);
		usernameInput.setFont(mainFont);

		JLabel usernameLabel = new JLabel("Name");
		usernameLabel.setBounds(35, 230, 500, 50);
		usernameLabel.setFont(new Font("Inria Sans", Font.PLAIN, 20));
		c.add(usernameLabel);

		JTextField businessNameInput = new JTextField();
		businessNameInput.setBounds(35, 360, 500, 50);
		businessNameInput.setFont(mainFont);

		JLabel businessNameLabel = new JLabel("Business Name");
		businessNameLabel.setBounds(35, 320, 500, 50);
		businessNameLabel.setFont(new Font("Inria Sans", Font.PLAIN, 20));
		c.add(businessNameLabel);

		JPasswordField passwordInput = new JPasswordField();
		passwordInput.setBounds(35, 450, 500, 50);
		passwordInput.setFont(mainFont);

		JLabel passwordLabel = new JLabel("Password");
		passwordLabel.setBounds(35, 410, 500, 50);
		passwordLabel.setFont(new Font("Inria Sans", Font.PLAIN, 20));
		c.add(passwordLabel);

		JPasswordField confirmPasswordInput = new JPasswordField();
		confirmPasswordInput.setBounds(35, 540, 500, 50);
		confirmPasswordInput.setFont(mainFont);

		JLabel confirmPasswordLabel = new JLabel("Confirm Password");
		confirmPasswordLabel.setBounds(35, 500, 500, 50);
		confirmPasswordLabel.setFont(new Font("Inria Sans", Font.PLAIN, 20));
		c.add(confirmPasswordLabel);

		Container d = frame.getContentPane();

		// BUTTON
		JButton createButton = new JButton();
		createButton.setBounds(35, 650, 150, 40);
		createButton.setOpaque(false);
		createButton.setFocusPainted(false);
		createButton.setBorderPainted(false);
		createButton.setContentAreaFilled(false);
		createButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		createButton.setIcon(createIcon);
		createButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				createButton.setIcon(createIconHover);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				createButton.setIcon(createIcon);
			}
		});

		createButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (usernameInput.getText().trim().isEmpty() || businessNameInput.getText().trim().isEmpty() ||
						new String(passwordInput.getPassword()).trim().isEmpty()) {
					new Error("ERROR:  Please fill in all required fields.");
					return;
				}

				try {
					ArrayList<Account> db = Database.loadDB();
					for (Account existingAccount : db) {
						if (existingAccount.getUsername().equals(usernameInput.getText())) {
							new Error("ERROR:  An account with this username already exists.");
							return;
						}
					}
				} catch (IOException | ClassNotFoundException ex) {
					ex.printStackTrace();
					return;
				}

				if (!Arrays.toString(passwordInput.getPassword())
						.equals(Arrays.toString(confirmPasswordInput.getPassword()))) {
					new Error("ERROR:  Password mismatch.");
					return;
				}

				Account acc = new Account(usernameInput.getText(), businessNameInput.getText(),
						Arrays.toString(passwordInput.getPassword()));
				try {
					Database.saveData(acc);
					MainFrame mainFrame = new MainFrame();
					mainFrame.pack();
					frame.dispose();
				} catch (IOException | ClassNotFoundException ex) {
					ex.printStackTrace();
				}
			}
		});

		a.add(logo);
		b.add(createAccount);
		b.add(redirect);
		b.add(line);
		b.add(secondLine);
		b.add(copyrightLink);
		c.add(usernameInput);
		c.add(businessNameInput);
		c.add(passwordInput);
		c.add(confirmPasswordInput);
		d.add(createButton);

		frame.setVisible(true);
	}
}