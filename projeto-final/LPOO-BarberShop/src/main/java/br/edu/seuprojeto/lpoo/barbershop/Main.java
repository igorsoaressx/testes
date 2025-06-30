package br.edu.seuprojeto.lpoo.barbershop;

import control.PersistenciaJPA;
import view.PrincipalJF;
import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {
        
        PersistenciaJPA jpa = new PersistenciaJPA();
        jpa.inicializarDadosPadrao(); // Garante que os serviços existem no banco
        
        SwingUtilities.invokeLater(() -> {
            PrincipalJF telaPrincipal = new PrincipalJF();
            telaPrincipal.setVisible(true);
        });
        
        // Hook para fechar a conexão ao sair
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            if (jpa != null && jpa.conexaoAberta()) {
                jpa.fecharConexao();
            }
        }));
    }
}