import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {

    public MainFrame(){
        main(null);
    }
    public static void main(String[] args) {

        Icon loginIcon = new ImageIcon("images/newLoginButton.png");
        Icon loginIconHover = new ImageIcon("images/loginHover.png");
        Icon signUpIcon = new ImageIcon("images/newSignUp.png");
        Icon signUpHover = new ImageIcon("images/signUpHover.png");
        Icon exitIcon = new ImageIcon("images/newExit.png");
        Icon exitIconHover = new ImageIcon("images/exitHover.png");

        // FRAME
        JFrame frame = new JFrame();
        frame.setTitle("Welcome");
        frame.setLayout(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setUndecorated(true);
        frame.setShape(new RoundRectangle2D.Double(0, 0, 990, 590, 70, 70));
        frame.setSize(990, 590);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        // frame.getRootPane().setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
        // Color.BLACK));
        frame.setBackground(Color.WHITE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // BUTTONS

        // LOGIN BUTTON
        JButton login = new JButton();
        login.setBounds(425, 310, 150, 40);
        login.setOpaque(true);
        login.setFocusPainted(false);
        login.setBorderPainted(false);
        login.setContentAreaFilled(false);
        login.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        login.setIcon(loginIcon);
        login.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                login.setIcon(loginIconHover);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                login.setIcon(loginIcon);
            }
        });
        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Login Login = new Login();
                Login.pack();
                frame.dispose();
            }
        });

        // SIGNUP BUTTON
        JButton signup = new JButton();
        signup.setBounds(425, 360, 150, 40);
        signup.setOpaque(false);
        signup.setFocusPainted(false);
        signup.setBorderPainted(false);
        signup.setContentAreaFilled(false);
        signup.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        signup.setIcon(signUpIcon);
        signup.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                signup.setIcon(signUpHover);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                signup.setIcon(signUpIcon);
            }
        });
        signup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Register Register = new Register();
                Register.pack();
                frame.dispose();
            }
        });

        // EXIT BUTTON
        JButton exit = new JButton();
        exit.setBounds(425, 410, 150, 40);
        exit.setOpaque(false);
        exit.setFocusPainted(false);
        exit.setBorderPainted(false);
        exit.setContentAreaFilled(false);
        exit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        exit.setIcon(exitIcon);
        exit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                exit.setIcon(exitIconHover);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                exit.setIcon(exitIcon);
            }
        });
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // CONTAINERS FOR SEPARATE ELEMENTS

        // BACKGROUND IMG
        Container a = frame.getContentPane();

        JLabel bg = new JLabel();
        bg.setIcon(new ImageIcon("images/finalbg1.png"));
        Dimension sizeA = bg.getPreferredSize();
        bg.setBounds(0, -25, sizeA.width, sizeA.height);

        // ICON IMAGES AND TEXTS (ICONS MAY BE CLICKED)
        Container b = frame.getContentPane();

        // ICONS

        JLabel aboutUs = new JLabel();
        aboutUs.setIcon(new ImageIcon("images/aboutus.png"));
        Dimension sizeAboutUs = aboutUs.getPreferredSize();
        aboutUs.setBounds(10, 520, sizeAboutUs.width, sizeAboutUs.height);

        JLabel twitter = new JLabel();
        twitter.setIcon(new ImageIcon("images/twitter.png"));
        Dimension sizeTwitter = twitter.getPreferredSize();
        twitter.setBounds(790, 520, sizeTwitter.width, sizeTwitter.height);

        JLabel facebook = new JLabel();
        facebook.setIcon(new ImageIcon("images/facebook2.png"));
        Dimension sizeFacebook = facebook.getPreferredSize();
        facebook.setBounds(570, 510, sizeFacebook.width, sizeFacebook.height);

        JLabel line = new JLabel();
        line.setIcon(new ImageIcon("images/line2.png"));
        Dimension sizeLine = line.getPreferredSize();
        line.setBounds(0, 559, sizeLine.width, 1);

        // TEXTS
        JLabel textAboutUs = new JLabel();
        textAboutUs.setText("About Us");
        textAboutUs.setBounds(50, 488, 100, 100);

        JLabel twitterLink = new JLabel();
        twitterLink.setText("https://twitter.com/e-dible");
        twitterLink.setBounds(830, 488, 200, 100);

        JLabel facebookLink = new JLabel();
        facebookLink.setText("https://facebook.com/e-dible");
        facebookLink.setBounds(610, 488, 200, 100);

        JLabel copyrightLink = new JLabel();
        copyrightLink.setText("Copyright © 2023 All Rights Reserved. CC BY-NC-ND License.");
        copyrightLink.setBounds(325, 523, 500, 100);

        // ADDING ELEMENTS IN FRAME AND SETTING VISIBILITY
        frame.add(login);
        frame.add(signup);
        frame.add(exit);

        a.add(bg);
        b.add(aboutUs);
        b.add(textAboutUs);
        b.add(twitter);
        b.add(twitterLink);
        b.add(facebook);
        b.add(facebookLink);
        b.add(line);
        b.add(copyrightLink);

        frame.setVisible(true);
    }

}
