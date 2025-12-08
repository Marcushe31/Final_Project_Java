import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {

        // ============ Part A: Create the Window =============
        JFrame frame = new JFrame("Boba n Chill");
        frame.setSize(700, 645);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Card Layout Setup (to switch screens)
        CardLayout cardLayout = new CardLayout();
        JPanel mainPanel = new JPanel(cardLayout);

        // HOME SCREEN
        JPanel homePanel = new JPanel(new BorderLayout());

        JLabel title = new JLabel("Welcome to Boba n Chill", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 26));
        homePanel.add(title,BorderLayout.NORTH);

        JButton startButton = new JButton("Start your order!");
        startButton.setFont(new Font("Arial", Font.BOLD, 20));
        homePanel.add(startButton, BorderLayout.SOUTH);

        // adding image
        ImageIcon boba = new ImageIcon("boba.png");
        JLabel imageLabel = new JLabel(boba);
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        homePanel.add(imageLabel, BorderLayout.CENTER);

        // ORDER SCREEN
        JPanel orderPanel = new JPanel();
        orderPanel.setLayout(new GridLayout(3,2,8,8));

        Font buttonFont = new Font("Arial" , Font.BOLD, 14);

        // New order list display
        JTextArea orderList = new JTextArea();
        orderList.setEditable(false);
        orderList.setFont(new Font("Arial", Font.BOLD, 14));

        final double[] totalPrice = {0};

        JScrollPane scroll = new JScrollPane(orderList);

        //buttons
        JButton taro = new JButton("Taro Milk Tea - $5.50");
        taro.setFont(buttonFont);

        JButton thai = new JButton("Thai Tea - $5.00");
        thai.setFont(buttonFont);

        JButton matcha = new JButton("Matcha Latte - $6.00");
        matcha.setFont(buttonFont);

        JButton sugar = new JButton("Brown Sugar Milk Tea - $6.50");
        sugar.setFont(buttonFont);

        JButton viet = new JButton("Vietnamese Coffee - $5.75");
        viet.setFont(buttonFont);

        JButton oolong = new JButton("Oolong Milk Tea - $5.25");
        oolong.setFont(buttonFont);

        orderPanel.add(taro);
        orderPanel.add(thai);
        orderPanel.add(matcha);
        orderPanel.add(sugar);
        orderPanel.add(viet);
        orderPanel.add(oolong);

        // adding panels to cardlayout
        mainPanel.add(homePanel, "HOME");


        JPanel orderWrapper = new JPanel(new BorderLayout());
        orderWrapper.add(orderPanel, BorderLayout.CENTER);

        JPanel orderScreen = new JPanel(new BorderLayout());
        mainPanel.add(orderScreen, "ORDER");
        orderScreen.add(orderWrapper, BorderLayout.CENTER);
        orderScreen.add(new JScrollPane(orderList), BorderLayout.EAST);
        orderScreen.revalidate();

        JPanel bottomBar = new JPanel();

        // place order button
        JButton placeOrder = new JButton("PLACE ORDER");
        placeOrder.setFont(buttonFont);

        // order history button
        JButton history = new JButton("ORDER HISTORY");
        history.setFont(buttonFont);

        // remove last BUTTON
        JButton undo = new JButton("REMOVE LAST ITEM");
        undo.setFont(buttonFont);

        // total LABEL
        JLabel total = new JLabel("Total: $0.00");
        total.setFont(buttonFont);

        // adding components to bottom bar
        bottomBar.add(undo);
        bottomBar.add(placeOrder);
        bottomBar.add(history);
        bottomBar.add(total);

        orderScreen.add(bottomBar, BorderLayout.SOUTH);

        // add mainPanel to frame
        frame.add(mainPanel, BorderLayout.CENTER);

        // switch screens when "ORDER" button is pressed
        startButton.addActionListener(e -> {
            cardLayout.show(mainPanel, "ORDER");
        });

        taro.addActionListener(e-> {
            orderList.append("Taro Milk Tea - $5.50 \n");
            totalPrice[0] += 5.50;
            total.setText("Total: $" + String.format("%.2f", totalPrice[0]));
            frame.revalidate();
            frame.repaint();

        });

        thai.addActionListener(e -> {
            orderList.append("Thai Tea - $5.00\n");
            totalPrice[0] += 5.00;
            total.setText("Total: $" + String.format("%.2f", totalPrice[0]));
            frame.revalidate();
            frame.repaint();
        });

        matcha.addActionListener(e -> {
            orderList.append("Matcha Latte - $6.00\n");
            totalPrice[0] += 6.00;
            total.setText("Total: $" + String.format("%.2f", totalPrice[0]));
            frame.revalidate();
            frame.repaint();
        });

        sugar.addActionListener(e -> {
            orderList.append("Brown Sugar Milk Tea - $6.50\n");
            totalPrice[0] += 6.50;
            total.setText("Total: $" + String.format("%.2f", totalPrice[0]));
            frame.revalidate();
            frame.repaint();
        });

        viet.addActionListener(e -> {
            orderList.append("Vietnamese Coffee - $5.75\n");
            totalPrice[0] += 5.75;
            total.setText("Total: $" + String.format("%.2f", totalPrice[0]));
            frame.revalidate();
            frame.repaint();
        });

        oolong.addActionListener(e -> {
            orderList.append("Oolong Milk Tea - $5.25\n");
            totalPrice[0] += 5.25;
            total.setText("Total: $" + String.format("%.2f", totalPrice[0]));
            frame.revalidate();
            frame.repaint();
        });

        // PLACE ORDER BUTTON ACTION LISTENER

        int orderNumber = (int)(Math.random() * 100 ) + 1;
        placeOrder.addActionListener(e -> {
            JOptionPane.showMessageDialog(frame,
                    "----- Your Order ----- \n\n"
                            + "Order Number: #" + orderNumber
                            + "\n\n"
                            + orderList.getText()
                            + "\nTOTAL: $"
                            + String.format("%.2f", totalPrice[0])
                    + "\n\n Thank you for \n choosing Boba N Chill! \n"
                    + "----------------------------",
                    "Order Receipt", JOptionPane.INFORMATION_MESSAGE);

            // RESTS ALL OF THE TEXT and TOTAL
            orderList.setText("");
            totalPrice[0] = 0;
            total.setText("Total: $0.00");
        });

        // REMOVE BUTTON ACTION LISTENER
        undo.addActionListener(e-> {
            String text = orderList.getText().trim();

            if(text.isEmpty()) return;

            String[] lines = text.split("\n");
            String last = lines [lines.length - 1];

            orderList.setText(text.substring(0, text.lastIndexOf("\n")));

            // updates total price depending on price of last
            if (last.contains("5.50")) totalPrice[0] -= 5.50;
            else if (last.contains("5.00")) totalPrice[0] -= 5.00;
            else if (last.contains("6.00")) totalPrice[0] -= 6.00;
            else if (last.contains("6.50")) totalPrice[0] -= 6.50;
            else if (last.contains("5.75")) totalPrice[0] -= 5.75;
            else if (last.contains("5.25")) totalPrice[0] -= 5.25;

            total.setText("Total: $" + String.format("%.2f", totalPrice[0]));

        });

        frame.setVisible(true);
    }
}
