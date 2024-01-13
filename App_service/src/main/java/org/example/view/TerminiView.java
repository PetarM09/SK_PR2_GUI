package org.example.view;

import org.example.model.TerminiTableModel;
import org.example.restClient.UserServiceClient;
import org.example.restClient.dto.TerminTreningaListDto;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class TerminiView extends JPanel {
    private JToolBar toolBar;
    private JPanel desktop;
    private JTable jTable;
    private TerminiTableModel terminiTableModel;

    private JSplitPane leftSplit;

    private ToolPanel toolPanel;

    private UserServiceClient userServiceClient;
    public TerminiView() {
        userServiceClient = new UserServiceClient();

        this.toolBar = new Toolbar();
        add(toolBar,BorderLayout.NORTH);

        this.desktop = new JPanel();
        this.desktop.setLayout(new BorderLayout());

        //Tools panel
        this.toolPanel = new ToolPanel();

        JLabel jLabel = new JLabel();

        //Left split
        this.leftSplit = new JSplitPane(JSplitPane.VERTICAL_SPLIT, jLabel, toolPanel);

        //JTable
        jTable = new JTable();
        jTable.setFillsViewportHeight(true);
        jTable.setRowSelectionAllowed(true);
        jTable.setColumnSelectionAllowed(false);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftSplit, new JScrollPane(jTable));

        this.add(splitPane,BorderLayout.CENTER);
        setVisible(true);
    }

    public void init() throws IOException {
        this.setVisible(true);

        TerminTreningaListDto terminTreningaListDto = userServiceClient.getTreninzi();
//        terminTreningaListDto.getContent().forEach(terminTreningaDto -> {
//            System.out.println(terminTreningaDto);
//            terminiTableModel.addRow(new Object[]{"OVDE IDU PODACI REDOM"});
//        });

    }

    public JToolBar getToolBar() {
        return toolBar;
    }

    public void setToolBar(JToolBar toolBar) {
        this.toolBar = toolBar;
    }

    public JPanel getDesktop() {
        return desktop;
    }

    public void setDesktop(JPanel desktop) {
        this.desktop = desktop;
    }

    public JTable getjTable() {
        return jTable;
    }

    public void setjTable(JTable jTable) {
        this.jTable = jTable;
    }

    public TerminiTableModel getTerminiTableModel() {
        return terminiTableModel;
    }

    public void setTerminiTableModel(TerminiTableModel terminiTableModel) {
        this.terminiTableModel = terminiTableModel;
    }

    public JSplitPane getLeftSplit() {
        return leftSplit;
    }

    public void setLeftSplit(JSplitPane leftSplit) {
        this.leftSplit = leftSplit;
    }

    public ToolPanel getToolPanel() {
        return toolPanel;
    }

    public void setToolPanel(ToolPanel toolPanel) {
        this.toolPanel = toolPanel;
    }

    public UserServiceClient getUserServiceClient() {
        return userServiceClient;
    }

    public void setUserServiceClient(UserServiceClient userServiceClient) {
        this.userServiceClient = userServiceClient;
    }
}
