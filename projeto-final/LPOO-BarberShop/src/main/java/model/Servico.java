package model; 

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "servicos")
public class Servico implements Serializable { 

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE) 
    private Long id;

    private String nome;
    private Double preco;
    private Integer duracaoMinutos;

   

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Integer getDuracaoMinutos() {
        return duracaoMinutos;
    }

    public void setDuracaoMinutos(Integer duracaoMinutos) {
        this.duracaoMinutos = duracaoMinutos;
    }
    
    @Override
public String toString() {
    // Retorna o nome e o preço formatado
    return String.format("%s - R$ %.2f", this.getNome(), this.getPreco());
}
}