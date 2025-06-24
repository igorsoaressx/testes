package model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "barbeiros")
public class Barbeiro extends Usuario {

    private String especialidades;

    // Getters e Setters 
    public String getEspecialidades() {
        return especialidades;
    }

    public void setEspecialidades(String especialidades) {
        this.especialidades = especialidades;
    }
}