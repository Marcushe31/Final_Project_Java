    import javax.swing.*;
    import java.awt.*;

    // ==================== Mood Message App ====================
    public class Main {
        public static void main(String[] args) {
    // ============ Part A: Create the Window =============
            JFrame frame = new JFrame("Boba n Chill");
            frame.setSize(500, 450);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



    // ============ Center Menu Ordering ================
           JLabel label = new JLabel(" Welcome to Boba n Chill");
            JButton button1 = new JButton("Taro Tea");
            JButton button2 = new JButton("Thai Tea");
            JButton button3 = new JButton ("Matcha Tea");
            JButton button4 = new JButton("Vietnamese Coffee");
            JButton button5 = new JButton("Vietnamese Coffee");
            JButton button6 = new JButton("Vietnamese Coffee");

            JPanel Menupanel = new JPanel(new GridLayout(3,2,10,10));

            Menupanel.add(button1);
            Menupanel.add(button2);
            Menupanel.add(button3);
            Menupanel.add(button4);
            Menupanel.add(button5);
            Menupanel.add(button6);
            frame.add(Menupanel);

            frame.setLayout(new BorderLayout());
            frame.add(Menupanel, BorderLayout.CENTER);





    // ============ Part C: Add Button Actions =============
            button1.addActionListener(e->
                    label.setText("That's awesome! Kep smiling!")
            );
            button2.addActionListener(e->
                    label.setText("Take a short break and relax.")
            );
            button3.addActionListener(e->
                    label.setText("Go conquer your goals!")
            );
    // ============ Part D: Display the Window =============
            frame.setVisible(true);
        }
    }
