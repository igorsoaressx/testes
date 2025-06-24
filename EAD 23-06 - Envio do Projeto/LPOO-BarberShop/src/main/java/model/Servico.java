package model; 

import javax.persistence.*;

@Entity
@Table(name = "servicos")
public class Servico {
   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private Double preco;
    private Integer duracaoMinutos;
    // Getters e Setters
}