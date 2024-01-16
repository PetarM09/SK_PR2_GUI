package org.example.view;

import org.example.MyApp;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class MenadzerToolPanel extends JPanel {

    private JPanel toolPanel;
    private JPanel actionPanel;
    private String podaci = "";
    private JButton izmeniPodatke;
    private JButton logOut;
    private JButton promeniLozinku;
    private JLabel label;
    private JButton prikaziNotifikacije;


    public MenadzerToolPanel(String podaci){
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

        promeniLozinku = new JButton("Promeni lozinku");
        izmeniPodatke = new JButton("Izmeni podatke");
        prikaziNotifikacije = new JButton("Prikazi notifikacije");
        logOut = new JButton("Log out");


        actionPanel.add(label);

        actionPanel.add(promeniLozinku);
        actionPanel.add(prikaziNotifikacije);
        actionPanel.add(izmeniPodatke);
        actionPanel.add(logOut);
        actions();
        return actionPanel;
    }

    public void actions(){
        this.logOut.addActionListener(e -> {
            MyApp.getInstance().intView("LOGOUT");
        });
        this.izmeniPodatke.addActionListener(e -> {
            MyApp.getInstance().getMenadzerView().izmenaPodataka();
        });
        this.promeniLozinku.addActionListener(e -> {
            MyApp.getInstance().getMenadzerView().promeniSifru();
        });
        this.prikaziNotifikacije.addActionListener(e -> {
            MyApp.getInstance().getMenadzerView().initNotifikacijeListTable();
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
