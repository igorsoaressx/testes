package br.edu.seuprojeto.lpoo.barbershop;

import model.Cliente;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        
        //CONFIGURAÇÃO E CONEXÃO
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("LPOO-BarberShop-JPA");
        EntityManager em = emf.createEntityManager();

        //CRIAÇÃO DO CLIENTE
        Cliente novoCliente = new Cliente();
        novoCliente.setNome("Igor Soares");
        novoCliente.setEmail("igor.soares@email.com");
        novoCliente.setSenha("senhaSegura456");
        novoCliente.setTelefone("55555555555");

        //PERSISTÊNCIA 
        try {
            // Inicia a transação
            em.getTransaction().begin();

            // Salva o objeto no banco
            em.persist(novoCliente);

            // Confirma a transação
            em.getTransaction().commit();

            System.out.println("SUCESSO: Cliente '" + novoCliente.getNome() + "' foi salvo com sucesso!");

        } catch (Exception e) {
            System.err.println("ERRO AO SALVAR O CLIENTE: " + e.getMessage());
            e.printStackTrace();
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        } finally {
            System.out.println("Finalizando a conexão.");
            em.close();
            emf.close();
        }
    }
}