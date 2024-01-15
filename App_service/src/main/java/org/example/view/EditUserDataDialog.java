package org.example.view;

import org.example.MyApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EditUserDataDialog extends JDialog {
    private JTextField usernameField;
    private JTextField birthdateField;
    private JTextField emailField;
    private JTextField nameField;
    private JTextField passwordField;
    private JTextField surnameField;

    private static final Map<String, String> podaci = new HashMap<>();

    public EditUserDataDialog(JPanel owner) {
        super();
        setLayout(new GridLayout(7, 2));

        usernameField = new JTextField();
        birthdateField = new JTextField();
        emailField = new JTextField();
        nameField = new JTextField();
        passwordField = new JTextField();
        surnameField = new JTextField();

        add(new JLabel("Username:"));
        add(usernameField);
        add(new JLabel("Datum rođenja:"));
        add(birthdateField);
        add(new JLabel("Email:"));
        add(emailField);
        add(new JLabel("Ime:"));
        add(nameField);
        add(new JLabel("Password:"));
        add(passwordField);
        add(new JLabel("Prezime:"));
        add(surnameField);

        JButton confirmButton = new JButton("Potvrdi");
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String datumRodjenja = birthdateField.getText();
                String email = emailField.getText();
                String ime = nameField.getText();
                String password = passwordField.getText();
                String prezime = surnameField.getText();
                podaci.clear();

                podaci.put("username", username);
                podaci.put("datumRodjenja", datumRodjenja);
                podaci.put("email", email);
                podaci.put("ime", ime);
                podaci.put("password", password);
                podaci.put("prezime", prezime);
                dispose();
                if (MyApp.getInstance().getAdminView() != null) {
                    try {
                        MyApp.getInstance().getAdminView().izmeniPodatke();
                    } catch (ParseException ex) {
                        throw new RuntimeException(ex);
                    }
                } else if (MyApp.getInstance().getjPanel() instanceof KlijentToolPanel) {
                    //MyApp.getInstance().getKlijentView().izmeniPodatke();
                    System.out.println("BRAVO");
                } else {
                    System.out.println("Nije ni admin ni klijent");
                }
            }
        });
        add(confirmButton);

        setSize(300, 200);
        setLocationRelativeTo(owner);
        setVisible(true);
    }

    public static Map<String,String> getPodaci() {
        return podaci;
    }
}
