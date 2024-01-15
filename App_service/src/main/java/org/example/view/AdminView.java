package org.example.view;

import org.example.MyApp;
import org.example.model.KorisniciModel;
import org.example.model.TerminiTableModel;
import org.example.restClient.UserServiceClient;
import org.example.restClient.dto.KorisniciListaDto;
import org.example.restClient.dto.KorisnikKlijentDTO;
import org.example.restClient.dto.TerminTreningaListDto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.IOException;
import java.util.List;

public class AdminView extends JPanel {
    private JToolBar toolBar;
    private JPanel desktop;
    private JTable jTable;
    private TerminiTableModel terminiTableModel;
    private KorisniciModel korisniciTableModel;

    private JSplitPane leftSplit;

    private AdminToolPanel adminToolPanel;

    private UserServiceClient userServiceClient;
    public AdminView(){
        this.terminiTableModel = new TerminiTableModel();
        this.korisniciTableModel = new KorisniciModel();
        userServiceClient = new UserServiceClient();

        this.toolBar = new Toolbar();
        add(toolBar,BorderLayout.NORTH);

        this.desktop = new JPanel();
        this.desktop.setLayout(new BorderLayout());

        KorisnikKlijentDTO k = userServiceClient.getPodaci();
        this.adminToolPanel = new AdminToolPanel("Korisnik : " + k.getIme() + " " + k.getPrezime());

        jTable = new JTable();
        jTable.setFillsViewportHeight(true);
        jTable.setRowSelectionAllowed(true);
        jTable.setColumnSelectionAllowed(false);


        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, adminToolPanel, new JScrollPane(jTable));

        this.add(splitPane,BorderLayout.CENTER);
    }

    public void initZauzetiTerminListTable() throws IOException {
        TerminTreningaListDto terminTreningaListDto = userServiceClient.getSviZakazaniTreninzi();
        terminiTableModel.removeRows();
        terminiTableModel.getTerminTreningaListDto().getContent().clear();
        terminTreningaListDto.getContent().forEach(terminTreningaDto -> {
            terminiTableModel.addRow(new Object[]{
                    terminTreningaDto.getNazivSale(),
                    terminTreningaDto.getNazivTreninga(),
                    terminTreningaDto.getDatum(),
                    terminTreningaDto.getVremePocetka(),
                    terminTreningaDto.getMaksimalanBrojUcesnika()});
        });
        jTable.setModel(terminiTableModel);
        MyApp.getInstance().refreshPanel();
    }
    public void initSlobodniTerminiListTable() {
        TerminTreningaListDto terminTreningaListDto = userServiceClient.getSviSlobodniTreninzi();
        terminiTableModel.removeRows();
        terminiTableModel.getTerminTreningaListDto().getContent().clear();
        terminTreningaListDto.getContent().forEach(terminTreningaDto -> {
            terminiTableModel.addRow(new Object[]{
                    terminTreningaDto.getNazivSale(),
                    terminTreningaDto.getNazivTreninga(),
                    terminTreningaDto.getDatum(),
                    terminTreningaDto.getVremePocetka(),
                    terminTreningaDto.getMaksimalanBrojUcesnika()});
        });
        jTable.setModel(terminiTableModel);
        MyApp.getInstance().refreshPanel();
    }

    public void KorisniciListTable(){
        KorisniciListaDto korisniciListaDto = userServiceClient.getKorisnici();
        korisniciTableModel.removeRows();
        korisniciTableModel.getKorisniciListaDto().getContent().clear();
        korisniciListaDto.getContent().forEach(korisniciDto -> {
            korisniciTableModel.addRow(new Object[]{
                    korisniciDto.getId(),
                    korisniciDto.getIme(),
                    korisniciDto.getPrezime(),
                    korisniciDto.getEmail(),
                    korisniciDto.getUsername(),
                    korisniciDto.getDatumRodjenja(),
                    korisniciDto.isZabranjenPristup()
                    });
            });
        jTable.setModel(korisniciTableModel);
        MyApp.getInstance().refreshPanel();
    }
    public void zabraniPristup() {
        korisniciTableModel.getKorisniciListaDto().getContent().forEach(korisniciDto -> {
            if (korisniciDto.getId().equals(jTable.getValueAt(jTable.getSelectedRow(),0))){
                korisniciDto.setZabranjenPristup(true);
                userServiceClient.zabraniPistup(korisniciDto);
            }
        });
        KorisniciListTable();
    }

    public void odobriPristup() {
        korisniciTableModel.getKorisniciListaDto().getContent().forEach(korisniciDto -> {
            if (korisniciDto.getId().equals(jTable.getValueAt(jTable.getSelectedRow(),0))){
                korisniciDto.setZabranjenPristup(false);
                userServiceClient.odobriPristup(korisniciDto);
            }
        });
        KorisniciListTable();
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

    public AdminToolPanel getToolPanel() {
        return adminToolPanel;
    }

    public void setToolPanel(AdminToolPanel adminToolPanel) {
        this.adminToolPanel = adminToolPanel;
    }

    public UserServiceClient getUserServiceClient() {
        return userServiceClient;
    }

    public void setUserServiceClient(UserServiceClient userServiceClient) {
        this.userServiceClient = userServiceClient;
    }



}
