package org.example.restClient.dto;

import java.util.ArrayList;
import java.util.List;

public class TreningListDto {
    private List<TerminTreningaDto> content = new ArrayList<>();

    public TreningListDto() {
    }

    public TreningListDto(List<TerminTreningaDto> content) {
        this.content = content;
    }

    public List<TerminTreningaDto> getContent() {
        return content;
    }

    public void setContent(List<TerminTreningaDto> content) {
        this.content = content;
    }
}
