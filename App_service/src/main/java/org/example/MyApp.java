package org.example;

import org.example.view.LoginView;
import org.example.view.TerminiView;

import javax.swing.*;
import java.awt.*;

public class MyApp extends JFrame {
    private String token;
    private LoginView loginView;
    private TerminiView terminiView;

    private MyApp() throws IllegalAccessException, NoSuchMethodException{
        this.setTitle("App");
        this.setSize(1200, 1200);
        this.setLayout(new BorderLayout());

        loginView = new LoginView();
        this.add(loginView, BorderLayout.NORTH);

        terminiView = new TerminiView();
        terminiView.setVisible(false);
        this.add(terminiView, BorderLayout.CENTER);

        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private static class InstanceHolder {
        private static MyApp instance;

        static {
            try {
                instance = new MyApp();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LoginView getLoginView() {
        return loginView;
    }

    public void setLoginView(LoginView loginView) {
        this.loginView = loginView;
    }

    public TerminiView getTerminiView() {
        return terminiView;
    }

    public void setTerminiView(TerminiView terminiView) {
        this.terminiView = terminiView;
    }

    public static MyApp getInstance(){return InstanceHolder.instance;}
}
