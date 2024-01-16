package org.example.view;

import org.example.MyApp;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class KlijentToolPanel extends JPanel {

    private JPanel toolPanel;
    private JPanel actionPanel;
    private String podaci = "";
    private JButton izlistajSlobodneTermine;
    private JButton izlistajZakazaneTermine;
    private JButton izmeniPodatke;
    private JButton logOut;
    private JButton promeniLozinku;
    private JLabel label;


    public KlijentToolPanel(String podaci){
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
        label = new JLabel(podaci);

        izlistajSlobodneTermine = new JButton("Izlistaj slobodne termine");
        izlistajZakazaneTermine = new JButton("Izlistaj zakazane termine");
        promeniLozinku = new JButton("Promeni lozinku");
        izmeniPodatke = new JButton("Izmeni podatke");
        logOut = new JButton("Log out");


        actionPanel.add(label);

        actionPanel.add(izlistajSlobodneTermine);
        actionPanel.add(izlistajZakazaneTermine);
        actionPanel.add(izmeniPodatke);
        actionPanel.add(logOut);
        actions();
        return actionPanel;
    }

    public void actions(){
        this.izlistajSlobodneTermine.addActionListener(e -> {
            MyApp.getInstance().getAdminView().initSlobodniTerminiListTable();
        });
        this.logOut.addActionListener(e -> {
            MyApp.getInstance().intView("LOGOUT");
        });
        this.izmeniPodatke.addActionListener(e -> {
            MyApp.getInstance().getAdminView().izmenaPodataka();
        });
        this.promeniLozinku.addActionListener(e -> {
            MyApp.getInstance().getAdminView().promeniSifru();
        });
    }

    public JLabel getLabel() {
        return label;
    }

    public void setLabel(JLabel label) {
        this.label = label;
    }

    public void setPodaci(List<String> podaci){

    }
}
