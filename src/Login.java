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

public class Login extends JFrame {

	public Login() {
		main(null);
	}

	final private static Font mainFont = new Font("Inria Sans", Font.PLAIN, 40);
	final private static Font linksFont = new Font("Inria Sans", Font.PLAIN, 20);
	final private static Font hoverFont = new Font("Inria Sans", Font.BOLD, 20);

	public static void main(String[] args) {

		Icon createIcon = new ImageIcon("images/newLoginButton.png");
		Icon createIconHover = new ImageIcon("images/loginHover.png");
		/*
		 * Icon createIcon = new ImageIcon("createIcon2.png"); Icon createIconHover =
		 * new ImageIcon("createHover.png");
		 */
		// FRAME
		JFrame frame = new JFrame();
		frame.setTitle("Welcome");
		frame.getContentPane().setLayout(null);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setUndecorated(true);
		// frame.setShape(new RoundRectangle2D.Double(0, 0, 580, 800, 70, 70));
		frame.setSize(580, 800);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		// rame.getRootPane().setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
		// Color.BLACK));
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
		JLabel username = new JLabel();
		username.setText("Username");
		username.setBounds(35, 180, 1000, 100);
		username.setFont(mainFont);

		JLabel password = new JLabel();
		password.setText("Password");
		password.setBounds(35, 300, 1000, 100);
		password.setFont(mainFont);

		JLabel createAccount = new JLabel();
		createAccount.setText("Create Account");
		createAccount.setBounds(350, 450, 170, 23);
		createAccount.setFont(linksFont);
		createAccount.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		createAccount.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				createAccount.setFont(hoverFont);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				createAccount.setFont(linksFont);
			}

			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				Register reg = new Register();
				reg.pack();
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
		usernameInput.setBounds(35, 260, 500, 50);
		usernameInput.setFont(mainFont);

		JPasswordField passwordInput = new JPasswordField();
		passwordInput.setBounds(35, 380, 500, 50);
		passwordInput.setFont(mainFont);

		Container d = frame.getContentPane();

		// BUTTON
		JButton loginButton = new JButton("LOGIN");
		loginButton.setBounds(25, 650, 170, 40);
		loginButton.setHorizontalAlignment(SwingConstants.LEFT);
		loginButton.setOpaque(false);
		loginButton.setFocusPainted(false);
		loginButton.setBorderPainted(false);
		loginButton.setContentAreaFilled(false);
		// loginButton.setHorizontalAlignment(SwingConstants.CENTER);
		// loginButton.setFont(new Font("Inria Sans", Font.BOLD, 35));
		// loginButton.setBackground(teal);
		loginButton.setIcon(createIcon);
		loginButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		loginButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				loginButton.setIcon(createIconHover);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				loginButton.setIcon(createIcon);
			}
		});
		loginButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (usernameInput.getText().trim().isEmpty() ||
						new String(passwordInput.getPassword()).trim().isEmpty()) {
					new Error("ERROR:  Please fill in all required fields.");
					return;
				}

				try {
					ArrayList<Account> db = Database.loadDB();
					boolean found = false;

					for (Account acc : db) {
						if (acc.getUsername().equals(usernameInput.getText()) &&
								acc.getpassword().equals(Arrays.toString(passwordInput.getPassword()))) {
							new Main(acc);
							frame.dispose();
							found = true;
							break;
						}
					}

					if (!found) {
						new Error("ERROR:  Invalid username or password.");
					}
				} catch (ClassNotFoundException | IOException e1) {
					e1.printStackTrace();
				}
			}
		});

		a.add(logo);
		b.add(username);
		b.add(password);
		b.add(createAccount);
		b.add(line);
		b.add(secondLine);
		b.add(copyrightLink);
		c.add(usernameInput);
		c.add(passwordInput);
		d.add(loginButton);

		frame.setVisible(true);

	}
}