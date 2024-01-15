package org.example.view;

import org.example.MyApp;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AdminToolPanel extends JPanel {

    private JPanel toolPanel;
    private JPanel actionPanel;
    private String podaci = "";
    private JButton zabraniKorisnikuPristup;
    private JButton odobriKorisnikuPristup;
    private JButton izlistajKorisnike;
    private JButton izlistajSlobodneTermine;
    private JButton izlistajZakazaneTermine;


    public AdminToolPanel(String podaci){
        setBoxLayout();
        this.podaci = podaci;
        this.actionPanel = new JPanel();
        this.add(actionPanel(podaci));
    }

    private void setBoxLayout(){
        BoxLayout boxLayout = new BoxLayout(this,BoxLayout.Y_AXIS);
        this.setLayout(boxLayout);
    }

    private JPanel actionPanel(String podaci){
        BoxLayout layout = new BoxLayout(actionPanel,BoxLayout.Y_AXIS);
        actionPanel.setLayout(layout);
        JLabel label = new JLabel(podaci);

        zabraniKorisnikuPristup = new JButton("Zabrani korisniku pristup");

        odobriKorisnikuPristup = new JButton("Odobri korisniku pristup");
        izlistajKorisnike = new JButton("Izlistaj korisnike");
        izlistajSlobodneTermine = new JButton("Izlistaj slobodne termine");
        izlistajZakazaneTermine = new JButton("Izlistaj zakazane termine");

        actionPanel.add(label);
        actionPanel.add(zabraniKorisnikuPristup);
        actionPanel.add(odobriKorisnikuPristup);
        actionPanel.add(izlistajKorisnike);
        actionPanel.add(izlistajSlobodneTermine);
        actionPanel.add(izlistajZakazaneTermine);
        actions();
        return actionPanel;
    }

    public void actions(){
        this.izlistajKorisnike.addActionListener(e -> {
            MyApp.getInstance().getAdminView().KorisniciListTable();
        });

        this.izlistajZakazaneTermine.addActionListener(e -> {
            try {
                MyApp.getInstance().getAdminView().initTerminListTable();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        this.zabraniKorisnikuPristup.addActionListener(e -> {
            MyApp.getInstance().getAdminView().zabraniPristup();
        });
        this.odobriKorisnikuPristup.addActionListener(e -> {
            MyApp.getInstance().getAdminView().odobriPristup();
        });
    }



    public void setPodaci(List<String> podaci){

    }
}
