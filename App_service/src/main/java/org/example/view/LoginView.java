package org.example.view;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.MyApp;
import org.example.restClient.UserServiceClient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class LoginView extends JPanel {
    private JPanel inputPanel;

    private JLabel emailLabel;
    private JLabel passwordLabel;
    private JTextField emailField;
    private JPasswordField passwordField;

    private JButton loginButton;

    private UserServiceClient userServiceRestClient = new UserServiceClient();

    private ObjectMapper objectMapper = new ObjectMapper();
    private final HttpClient httpClient;


    public LoginView() {
        httpClient = HttpClient.newHttpClient();
        this.setSize(400, 400);

        this.setLayout(new BorderLayout());

        initInputPanel();

        loginButton = new JButton("Login");
        this.add(loginButton, BorderLayout.SOUTH);

        loginButton.addActionListener(e -> {
            String email = emailField.getText();
            String password = new String(passwordField.getPassword());

           // String apiUrl = "http://localhost:8084/users/api/korisnici/login";
//            String apiUrl = "http://localhost:8080/api/korisnici/login";
            String requestBody = String.format("{\"email\":\"%s\",\"password\":\"%s\"}", email, password);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(MyApp.apiUrl+ "api/korisnici/login"))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();

            try {
                HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
                if (response.statusCode() == 200) {
                    this.setVisible(false);
                    MyApp.getInstance().getTerminiView().init();
                    String[] strings = response.body().split("\":\"");
                    MyApp.getInstance().setToken(strings[1].replaceAll("[\"}]",""));
                } else if (response.statusCode() == 403) {
                    JOptionPane.showMessageDialog(this, "Zabranjen pristup", "Greška", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Pogrešni kredencijali", "Greška", JOptionPane.ERROR_MESSAGE);
                }
            } catch (InterruptedException | IOException ex) {
                ex.printStackTrace();
            }
        });
    }

    private void initInputPanel() {

        inputPanel = new JPanel();

        emailLabel = new JLabel("Username: ");
        passwordLabel = new JLabel("Password: ");

        emailField = new JTextField(20);
        passwordField = new JPasswordField(20);

        inputPanel.add(emailLabel);
        inputPanel.add(emailField);

        inputPanel.add(passwordLabel);
        inputPanel.add(passwordField);

        this.add(inputPanel, BorderLayout.CENTER);
    }
}
