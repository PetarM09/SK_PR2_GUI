package org.example.view;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class MenadzerToolPanel extends JPanel {

    private JPanel toolPanel;
    private JPanel actionPanel;
    private String podaci = "";

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
        JLabel label = new JLabel(podaci);

        JButton create = new JButton("Uredi podatke o fiskulturnoj sali");

        JButton delete = new JButton("Definisi pogodnosti");

        JButton read = new JButton("3");

        JButton update = new JButton("4");

        actionPanel.add(label);
        actionPanel.add(create);
        actionPanel.add(delete);
        actionPanel.add(read);
        actionPanel.add(update);

        return actionPanel;
    }

    public void setPodaci(List<String> podaci){

    }
}
