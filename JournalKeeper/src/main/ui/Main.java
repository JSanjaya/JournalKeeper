package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Main allows the user to run the application and initializes
// a password gui that the user must pass in order to access the main gui

public class Main extends JFrame implements ActionListener {
    PlaySound playSound;
    JLabel passwordLabel;
    JPasswordField passwordText;
    JButton loginButton;
    static JLabel success;
    JFrame frame;

    // EFFECTS: Runs a new main object
    public static void main(String[] args) {
        new Main();
    }

    // EFFECTS: creates Main constructor that sets up the login panel
    public Main() {
        JPanel panel = new JPanel();
        frame = new JFrame();
        frame.setSize(200,100);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.add(panel);

        panel.setLayout(null);

        passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(10,10,80,25);
        panel.add(passwordLabel);

        passwordText = new JPasswordField();
        passwordText.setBounds(75,10,80,25);
        panel.add(passwordText);

        loginButton = new JButton("     Login     ");
        loginButton.setBounds(0,50,200,25);
        loginButton.addActionListener(this);
        panel.add(loginButton);

        success = new JLabel("");
        success.setBounds(10,70,300,50);
        panel.add(success);

        frame.setVisible(true);
    }

    // EFFECTS: Verifies that the password is correct when the login
    // button is pressed. Also plays a sound when the password is incorrect.
    @Override
    public void actionPerformed(ActionEvent e) {
        playSound = new PlaySound();
        String password = passwordText.getText();
        if (password.equals("password")) {
            success.setText("Login Successful");
            frame.setVisible(false);
            new JournalKeeper().createAndShowGUI();

        } else {
            success.setText("Incorrect Password");
            playSound.playSound();
        }
    }
}



