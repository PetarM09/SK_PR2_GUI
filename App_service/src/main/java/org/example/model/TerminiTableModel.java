package org.example.model;

import org.example.restClient.dto.TerminTreningaListDto;

import javax.swing.table.DefaultTableModel;

public class TerminiTableModel extends DefaultTableModel {

    private TerminTreningaListDto terminTreningaListDto = new TerminTreningaListDto();

    public TerminiTableModel() throws IllegalAccessException, NoSuchMethodException{
            super(new String[]{"Fiskulturna sala", "Tip treninga", "Datum", "Vreme pocetka", "Maksimalni broj ucesnika"},0);
    }



    @Override
    public void addRow(Object[] row) {
        super.addRow(row);
//        TerminTreningaDto dto = new TerminTreningaDto();
//        dto.setIdSale(Long.parseLong(String.valueOf(row[0])));
//        dto.set(String.valueOf(row[0]));
//        dto.setIdSale(Long.parseLong(String.valueOf(row[0])));
//        dto.setIdSale(Long.parseLong(String.valueOf(row[0])));
//        treningListDto.getContent().add(dto);
    }
}
