import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Error extends JFrame {

    public Error(String errorMessage) {
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        setSize(500, 300);
        setResizable(false);
        setLocationRelativeTo(null);
        setBackground(Color.WHITE);
        setUndecorated(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ImageIcon imageIcon = new ImageIcon("images/finalbg1.png");
        Image image = imageIcon.getImage();
        Image newimg = image.getScaledInstance(500, 200, java.awt.Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newimg);

        JLabel bg = new JLabel(imageIcon);
        bg.setAlignmentX(CENTER_ALIGNMENT);

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(null);
        contentPanel.setPreferredSize(new Dimension(500, 100));
        contentPanel.setBackground(new Color(255, 255, 255, 0));

        JLabel errorLabel = new JLabel(errorMessage);
        errorLabel.setForeground(Color.BLACK);
        errorLabel.setFont(new Font("Inria Sans", Font.BOLD, 15));
        errorLabel.setBounds(60, 1, 400, 30);

        

        JButton button = new JButton("OK");
        button.setBounds(200, 40, 100, 40);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setFont(new Font("Inria Sans", Font.BOLD, 14));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(0, 0, 0));
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        contentPanel.add(errorLabel);
        contentPanel.add(button);

        add(bg);
        add(contentPanel);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Error("Custom error message.");
    }
}