package org.example.model;

import org.example.restClient.dto.KorisniciDto;
import org.example.restClient.dto.KorisniciListaDto;
import org.example.restClient.dto.TerminTreningaDto;
import org.example.restClient.dto.TerminTreningaListDto;

import javax.swing.table.DefaultTableModel;
import java.sql.Time;
import java.time.LocalDate;
import java.util.Date;

public class KorisniciModel extends DefaultTableModel {

    private KorisniciListaDto korisniciListaDto = new KorisniciListaDto();

    public KorisniciModel(){
        super(new String[]{"Ime", "Prezime", "Email", "username", "datum_rodjenja"},0);
    }



    @Override
    public void addRow(Object[] row) {
        super.addRow(row);
        KorisniciDto dto = new KorisniciDto();
        dto.setIme(String.valueOf(row[0]));
        dto.setPrezime(String.valueOf(row[1]));
        dto.setEmail((String) row[2]);
        dto.setUsername((String) row[3]);
        dto.setDatumRodjenja((LocalDate) row[4]);

        korisniciListaDto.getContent().add(dto);
    }

    public KorisniciListaDto getTerminTreningaListDto() {
        return korisniciListaDto;
    }

    public void setTerminTreningaListDto(KorisniciListaDto korisniciListaDto) {
        this.korisniciListaDto = korisniciListaDto;
    }
}
