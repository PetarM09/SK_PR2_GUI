package org.example.view;

import org.example.MyApp;

import javax.swing.*;

public class ToolPanel extends JPanel {

    private JPanel toolPanel;
    private JPanel actionPanel;

    public ToolPanel(){
        setBoxLayout();
        this.actionPanel = new JPanel();
        this.add(actionPanel());
    }

    private void setBoxLayout(){
        BoxLayout boxLayout = new BoxLayout(this,BoxLayout.Y_AXIS);
        this.setLayout(boxLayout);
    }

    private JPanel actionPanel(){
        BoxLayout layout = new BoxLayout(actionPanel,BoxLayout.Y_AXIS);
        actionPanel.setLayout(layout);
        JLabel label = new JLabel("Akcije");

        JButton create = new JButton("1");

        JButton delete = new JButton("2");

        JButton read = new JButton("3");

        JButton update = new JButton("4");

        actionPanel.add(label);
        actionPanel.add(create);
        actionPanel.add(delete);
        actionPanel.add(read);
        actionPanel.add(update);

        return actionPanel;
    }



}
