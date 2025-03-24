package br.com.fiap.tds2ps.spring_mvc.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * DTO for {@link br.com.fiap.tds2ps.spring_mvc.model.Prontuario}
 */
public class ProntuarioDto implements Serializable {
    private final Long id;
    private final String anamnese;
    private final String prescricao;
    private final Date data;
    private final PacienteDto PacienteDTO;

    public ProntuarioDto(Long id, String anamnese, String prescricao, Date data, PacienteDto pacienteDTO) {
        this.id = id;
        this.anamnese = anamnese;
        this.prescricao = prescricao;
        this.data = data;
        PacienteDTO = pacienteDTO;
    }

    public Long getId() {
        return id;
    }

    public String getAnamnese() {
        return anamnese;
    }

    public String getPrescricao() {
        return prescricao;
    }

    public Date getData() {
        return data;
    }

    public PacienteDto getPacienteDTO() {
        return PacienteDTO;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProntuarioDto entity = (ProntuarioDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.anamnese, entity.anamnese) &&
                Objects.equals(this.prescricao, entity.prescricao) &&
                Objects.equals(this.data, entity.data) &&
                Objects.equals(this.PacienteDTO, entity.PacienteDTO);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, anamnese, prescricao, data, PacienteDTO);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "anamnese = " + anamnese + ", " +
                "prescricao = " + prescricao + ", " +
                "data = " + data + ", " +
                "PacienteDTO = " + PacienteDTO + ")";
    }
}