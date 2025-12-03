import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {

        JFrame F1 = new JFrame("Mood Message App");
        F1.setSize(400, 200);
        F1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel L1 = new JLabel("How are you feeling today?");
        JButton b1 = new JButton("Happy");
        JButton b2 = new JButton("Tired");
        JButton b3 = new JButton("Motivated");
        JButton bR = new JButton("Reset");

        JPanel p1 = new JPanel();
        p1.add(b1);
        p1.add(b2);
        p1.add(b3);
        p1.add(bR);
        p1.add(L1);

        // Panel Background Color Change
        b1.setBackground(Color.yellow);
        b2.setBackground(Color.GRAY);
        b3.setBackground(Color.red);

        // Text Font Change
        L1.setFont(new Font("Times New Roman", Font.BOLD, 16));

        F1.add(p1);

        b1.addActionListener(e -> L1.setText("Thats awesome! Keep Smiling!"));
        b2.addActionListener(e -> L1.setText("Take a short break and relax."));
        b3.addActionListener(e -> L1.setText("Go and conquer your goals!"));
        bR.addActionListener(e -> L1.setText("How are you feeling today?"));

        F1.setVisible(true);
    }
}