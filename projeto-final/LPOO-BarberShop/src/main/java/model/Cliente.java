package model; 

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "clientes")
public class Cliente extends Usuario implements Serializable {
    
    @Override
public String toString() {
    //  mostra o nome.
    return this.getNome(); 
}
   
    
}