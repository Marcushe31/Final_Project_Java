import javax.swing.*;
import java.awt.*;
import java.io.*;

public class Main {
    public static void main(String[] args) {

        // HERE AND BELOW, main setup for GUI components (buttons, panels, frames, etc).
        JFrame frame = new JFrame("Boba n Chill");
        frame.setSize(700, 645);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Card Layout Setup that switches screens)
        CardLayout cardLayout = new CardLayout();
        JPanel mainPanel = new JPanel(cardLayout);

        // HOME SCREEN
        JPanel homePanel = new JPanel(new BorderLayout());
        JLabel title = new JLabel("Welcome to Boba n Chill", SwingConstants.CENTER); //alignment to CENTER
        title.setFont(new Font("Arial", Font.BOLD, 26));
        homePanel.add(title,BorderLayout.NORTH); // adding title to NORTH.

        //Start Buttons
        JButton startButton = new JButton("Start your order!");
        startButton.setFont(new Font("Arial", Font.BOLD, 20));
        homePanel.add(startButton, BorderLayout.SOUTH); //adding start button to SOUTH

        // adding homescreen boba image (boba.png)
        ImageIcon boba = new ImageIcon("boba.png");
        JLabel imageLabel = new JLabel(boba);
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        homePanel.add(imageLabel, BorderLayout.CENTER); // adding the homepanel to CENTER alignment,

        // ORDER SCREEN setup
        JPanel orderPanel = new JPanel();
        orderPanel.setLayout(new GridLayout(3,2,8,8)); // (3 x 2), so there are 6 TOTAL drinks
        Font buttonFont = new Font("Arial" , Font.BOLD, 14); //setsfont

        // ORDER list display (on the right sidebar)
        JTextArea orderList = new JTextArea();
        orderList.setEditable(false);
        orderList.setFont(new Font("Arial", Font.BOLD, 14));
        final double[] totalPrice = {0}; // creates array to store total price
        // @Jeremy, make sure to remember to SUBTRACT the total price when the removeitem button is clicked. Otherwise the total will remain unchanged.

        // new file that stores the order history
        String historyFile = "order_history.txt";

        //DRINK BUTTONS (creating)
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

        //adding buttons to order panel
        orderPanel.add(taro);
        orderPanel.add(thai);
        orderPanel.add(matcha);
        orderPanel.add(sugar);
        orderPanel.add(viet);
        orderPanel.add(oolong);

        // adding panels to cardlayout (also wraps the 6 buttons)
        mainPanel.add(homePanel, "HOME");
        JPanel orderWrapper = new JPanel(new BorderLayout());
        orderWrapper.add(orderPanel, BorderLayout.CENTER);
        JPanel orderScreen = new JPanel(new BorderLayout());
        mainPanel.add(orderScreen, "ORDER");
        orderScreen.add(orderWrapper, BorderLayout.CENTER);
        orderScreen.add(new JScrollPane(orderList), BorderLayout.EAST);
        JPanel bottomBar = new JPanel();

        // place order button
        JButton placeOrder = new JButton("PLACE ORDER");
        placeOrder.setFont(buttonFont);

        // order history button
        JButton history = new JButton("ORDER HISTORY");
        history.setFont(buttonFont);

        // remove last drink button
        JButton undo = new JButton("REMOVE LAST ITEM");
        undo.setFont(buttonFont);

        // total LABEL (on the bottom right)
        JLabel total = new JLabel("Total: $0.00");
        total.setFont(buttonFont);

        // adding components (above) to bottom bar
        bottomBar.add(undo);
        bottomBar.add(placeOrder);
        bottomBar.add(history);
        bottomBar.add(total);
        orderScreen.add(bottomBar, BorderLayout.SOUTH); //allignment

        // add mainPanel to frame
        frame.add(mainPanel, BorderLayout.CENTER);

        // switch screens when "ORDER" button is pressed (from home to order screen)
        startButton.addActionListener(e -> {
            cardLayout.show(mainPanel, "ORDER");
        });
        taro.addActionListener(e-> {
            orderList.append("Taro Milk Tea - $5.50 \n");
            totalPrice[0] += 5.50;
            total.setText("Total: $" + String.format("%.2f", totalPrice[0]));
        });
        thai.addActionListener(e -> {
            orderList.append("Thai Tea - $5.00\n");
            totalPrice[0] += 5.00;
            total.setText("Total: $" + String.format("%.2f", totalPrice[0]));
        });
        matcha.addActionListener(e -> {
            orderList.append("Matcha Latte - $6.00\n");
            totalPrice[0] += 6.00;
            total.setText("Total: $" + String.format("%.2f", totalPrice[0]));
        });
        sugar.addActionListener(e -> {
            orderList.append("Brown Sugar Milk Tea - $6.50\n");
            totalPrice[0] += 6.50;
            total.setText("Total: $" + String.format("%.2f", totalPrice[0]));
        });
        viet.addActionListener(e -> {
            orderList.append("Vietnamese Coffee - $5.75\n");
            totalPrice[0] += 5.75;
            total.setText("Total: $" + String.format("%.2f", totalPrice[0]));
        });
        oolong.addActionListener(e -> {
            orderList.append("Oolong Milk Tea - $5.25\n");
            totalPrice[0] += 5.25;
            total.setText("Total: $" + String.format("%.2f", totalPrice[0]));
        });

        // PLACE ORDER BUTTON ACTION LISTENER
        placeOrder.addActionListener(e -> {
            int orderNumber = (int)(Math.random() * 100 ) + 1;
            String currentOrderText = orderList.getText();
            JOptionPane.showMessageDialog(frame,
                    "----- Your Order ----- \n\n"
                            + "Order Number: #" + orderNumber
                            + "\n\n"
                            + orderList.getText()
                            + "\nTOTAL: $"
                            + String.format("%.2f", totalPrice[0])
                    + "\n\n Thank you for \n choosing Boba N Chill! \n"
                    + "----------------------------",
                    "Order Receipt", JOptionPane.INFORMATION_MESSAGE); //thisis the receipt popup window

            // adds order to the history file
           try (FileWriter fw = new FileWriter(historyFile, true);
                BufferedWriter bw = new BufferedWriter(fw)) {
               bw.write("\n Order Number: #" + orderNumber + "\n");
               bw.write(currentOrderText);
               bw.write("TOTAL: $" + String.format("%.2f", totalPrice[0]) + "\n");
               bw.write("---------------------------- \n \n");
           } catch (IOException ex) {
               JOptionPane.showMessageDialog(frame,
                       "Error Saving order history.", "Error", JOptionPane.ERROR_MESSAGE); //error fail
           }

            // RESETS ALL OF THE TEXT and TOTAL $
            orderList.setText("");
            totalPrice[0] = 0;
            total.setText("Total: $0.00");
        });

        // REMOVE BUTTON ACTION LISTENER
        undo.addActionListener(e-> {
            String text = orderList.getText().trim(); //gets all the text displayed in ORDER LIST. ADDITIONALLY, trim removes any spaces at top/bottom. stores in string var text.
            if(text.isEmpty()) return; // if the order list is empty, it will stop the method.
            String[] lines = text.split("\n"); //this will SPLIT THE TEXT BLOCK INTO INDIVIDUAL LINES
            String last = lines [lines.length - 1]; //gets the lsat item in the list, which will be REMOVED..
            // remember to subtract from total when remove button is clicked, keep tally for this later !!
            orderList.setText(text.substring(0, text.lastIndexOf("\n")));
            // ^ this is what ACTUALLY REMOVES THE LAST LINE from the text.

            // updates total price depending on price of last
            if (last.contains("5.50")) totalPrice[0] -= 5.50; // try to update... must be a more efficient way
            else if (last.contains("5.00")) totalPrice[0] -= 5.00;
            else if (last.contains("6.00")) totalPrice[0] -= 6.00;
            else if (last.contains("6.50")) totalPrice[0] -= 6.50;
            else if (last.contains("5.75")) totalPrice[0] -= 5.75;
            else if (last.contains("5.25")) totalPrice[0] -= 5.25;
            total.setText("Total: $" + String.format("%.2f", totalPrice[0]));
        });

        history.addActionListener(e -> {
            File file = new File(historyFile);

            // checking to see if file doesn't exist OR if the file is empty (==0)
            if (!file.exists() || file.length() == 0) {
                JOptionPane.showMessageDialog(frame,
                        "No previous orders yet.","Order History", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            StringBuilder historyText = new StringBuilder();
            // br reads file line by line UNTIL THERE ARE NO MORE LINES, then it will add each line to stringBuilder**
            try (BufferedReader br = new BufferedReader(new FileReader(file))) { // try catch incase no history
                String line;
                while ((line = br.readLine()) != null) {  //br.readLine reads a line, when the file is empty/has no more lines, it will return null
                    historyText.append(line).append("\n"); // also, while loop runs until there are NO lines left.
                }
            } catch (IOException ex) { //fail catch
                JOptionPane.showMessageDialog(frame,
                        "Error reading order history.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            JTextArea historyArea = new JTextArea(historyText.toString());
            historyArea.setEditable(false); // a USER CANNOT type inside of it, they can only read it
            historyArea.setFont(new Font("Arial", Font.BOLD, 12)); //setsfont

            // scrolling pane in case the history extends (long history)
            JScrollPane historyScroll = new JScrollPane(historyArea);
            historyScroll.setPreferredSize(new Dimension(400,300)); //sizeof popup window

            // this is the popup window that contains the order history
            JOptionPane.showMessageDialog(frame,
                    historyScroll,"Order History", JOptionPane.INFORMATION_MESSAGE);
        });
        frame.setVisible(true); //visibility = true
    }
}
