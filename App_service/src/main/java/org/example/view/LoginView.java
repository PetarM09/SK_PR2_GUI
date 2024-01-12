package org.example.view;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.MyApp;
import org.example.restClient.UserServiceClient;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class LoginView extends JPanel {
    private JPanel inputPanel;

    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JTextField usernameInput;
    private JPasswordField passwordInput;

    private JButton loginButton;

    private UserServiceClient userServiceRestClient = new UserServiceClient();

    private ObjectMapper objectMapper = new ObjectMapper();

    public LoginView() {

        super();
        this.setSize(400, 400);

        this.setLayout(new BorderLayout());

        initInputPanel();

        loginButton = new JButton("Login");
        this.add(loginButton, BorderLayout.SOUTH);
        loginButton.addActionListener((event) -> {

            try {
                String token = userServiceRestClient
                        .login(usernameInput.getText(), String.valueOf(passwordInput.getPassword()));
                this.setVisible(false);
                MyApp.getInstance().setToken(token);
                System.out.println(token);
                MyApp.getInstance().getTerminiView().init();

            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void initInputPanel() {

        inputPanel = new JPanel();

        usernameLabel = new JLabel("Username: ");
        passwordLabel = new JLabel("Password: ");

        usernameInput = new JTextField(20);
        passwordInput = new JPasswordField(20);

        inputPanel.add(usernameLabel);
        inputPanel.add(usernameInput);

        inputPanel.add(passwordLabel);
        inputPanel.add(passwordInput);

        this.add(inputPanel, BorderLayout.CENTER);
    }
}
