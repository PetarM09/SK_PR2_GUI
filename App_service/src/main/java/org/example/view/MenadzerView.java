package org.example.view;

import org.example.model.TerminiTableModel;
import org.example.restClient.UserServiceClient;
import org.example.restClient.dto.KorisnikKlijentDTO;
import org.example.restClient.dto.TerminTreningaListDto;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.List;

public class MenadzerView extends JPanel {
    private JToolBar toolBar;
    private JPanel desktop;
    private JTable jTable;
    private TerminiTableModel terminiTableModel;

    private JSplitPane leftSplit;

    private KlijentToolPanel klijentToolPanel;

    private UserServiceClient userServiceClient;
    public MenadzerView(){
        userServiceClient = new UserServiceClient();

        this.toolBar = new Toolbar();
        add(toolBar,BorderLayout.NORTH);

        this.desktop = new JPanel();
        this.desktop.setLayout(new BorderLayout());

        KorisnikKlijentDTO k = userServiceClient.getPodaci();
        this.klijentToolPanel = new KlijentToolPanel("Korisnik : " + k.getIme() + " " + k.getPrezime());

        terminiTableModel = new TerminiTableModel();
        jTable = new JTable(terminiTableModel);
        jTable.setFillsViewportHeight(true);
        jTable.setRowSelectionAllowed(true);
        jTable.setColumnSelectionAllowed(false);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, klijentToolPanel, new JScrollPane(jTable));

        this.add(splitPane,BorderLayout.CENTER);
        setVisible(true);
    }

    public void init() throws IOException {

        this.setVisible(true);

        TerminTreningaListDto terminTreningaListDto = userServiceClient.getTreninziZaKorisnika();
        terminTreningaListDto.getContent().forEach(terminTreningaDto -> {
            System.out.println(terminTreningaDto.toString());

            terminiTableModel.addRow(new Object[]{
                    terminTreningaDto.getNazivSale(),
                    terminTreningaDto.getNazivTreninga(),
                    terminTreningaDto.getDatum(),
                    terminTreningaDto.getVremePocetka(),
                    terminTreningaDto.getMaksimalanBrojUcesnika()});
        });
        KorisnikKlijentDTO k = userServiceClient.getPodaci();

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

    public KlijentToolPanel getToolPanel() {
        return klijentToolPanel;
    }

    public void setToolPanel(KlijentToolPanel klijentToolPanel) {
        this.klijentToolPanel = klijentToolPanel;
    }

    public UserServiceClient getUserServiceClient() {
        return userServiceClient;
    }

    public void setUserServiceClient(UserServiceClient userServiceClient) {
        this.userServiceClient = userServiceClient;
    }
}
