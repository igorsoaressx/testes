package model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "barbeiros")
public class Barbeiro extends Usuario implements Serializable {

    private String especialidades;

  
    public String getEspecialidades() {
        return especialidades;
    }

    public void setEspecialidades(String especialidades) {
        this.especialidades = especialidades;
    }
    
    @Override
public String toString() {
    // mostrar o nome
    return this.getNome(); 
}
}