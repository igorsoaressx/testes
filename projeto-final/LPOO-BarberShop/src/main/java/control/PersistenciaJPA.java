package control; 

import model.Servico; 
import java.util.List; 
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PersistenciaJPA implements InterfaceBD {

    private EntityManager entityManager;
    private EntityManagerFactory factory;

    public PersistenciaJPA() {
        factory = Persistence.createEntityManagerFactory("LPOO-BarberShop-JPA");
        entityManager = factory.createEntityManager();
    }

    @Override
    public Boolean conexaoAberta() {
        return entityManager != null && entityManager.isOpen();
    }

    @Override
    public void fecharConexao() {
        if (entityManager != null && entityManager.isOpen()) {
            entityManager.close();
            if (factory != null && factory.isOpen()) {
                factory.close();
            }
        }
    }

    @Override
    public Object find(Class c, Object id) throws Exception {
        return entityManager.find(c, id);
    }

    @Override
    public void persist(Object o) throws Exception {
        try {
        entityManager.getTransaction().begin();
        entityManager.merge(o);
        entityManager.getTransaction().commit();
    } catch (Exception e) {
        if (entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().rollback();
        }
        Logger.getLogger(PersistenciaJPA.class.getName()).log(Level.SEVERE, "Erro ao persistir objeto", e);
        throw new Exception("Erro ao salvar no banco de dados.");
    }
    }

    @Override
    public void remover(Object o) throws Exception {
        try {
            entityManager.getTransaction().begin();
            if (!entityManager.contains(o)) {
                o = entityManager.merge(o);
            }
            entityManager.remove(o);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            Logger.getLogger(PersistenciaJPA.class.getName()).log(Level.SEVERE, "Erro ao remover objeto", e);
            throw new Exception("Erro ao remover do banco de dados.");
        }
    }

    public EntityManager getEntityManager() {
        if (entityManager == null || !entityManager.isOpen()) {
            entityManager = factory.createEntityManager();
        }
        return this.entityManager;
    }

  

    public List<Servico> getServicos() {
        return getEntityManager().createQuery("SELECT s FROM Servico s ORDER BY s.nome ASC", Servico.class).getResultList();
    }

    public void inicializarDadosPadrao() {
        try {
            if (getServicos().isEmpty()) {
                System.out.println("Nenhum serviço encontrado. Criando dados padrão...");
                criarServicoSeNaoExistir("Corte de Cabelo", 50.0, 30);
                criarServicoSeNaoExistir("Barba", 40.0, 25);
                criarServicoSeNaoExistir("Sobrancelha", 20.0, 15);
                criarServicoSeNaoExistir("Pintura de Cabelo", 80.0, 60);
                System.out.println("Dados padrão de serviços criados com sucesso.");
            }
        } catch (Exception e) {
            System.err.println("Erro ao inicializar dados padrão de serviços: " + e.getMessage());
        }
    }
    
    private void criarServicoSeNaoExistir(String nome, Double preco, Integer duracao) throws Exception {
        Servico s = new Servico();
        s.setNome(nome);
        s.setPreco(preco);
        s.setDuracaoMinutos(duracao);
        persist(s);
    }
}