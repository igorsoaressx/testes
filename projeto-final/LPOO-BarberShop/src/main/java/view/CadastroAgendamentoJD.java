package view;

import control.PersistenciaJPA;
import model.*;
import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

public class CadastroAgendamentoJD extends javax.swing.JDialog {

    private PersistenciaJPA jpa;
    private Agendamento agendamento;


    public CadastroAgendamentoJD(Frame parent, boolean modal, PersistenciaJPA jpa) {
        super(parent, modal);
        this.jpa = jpa;
        initComponents(); 
        this.setLocationRelativeTo(null);
        carregarCombosEListas();
    }

    private void initComponents() {
        setTitle("Novo Agendamento");
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

      
        cmbCliente = new JComboBox<>();
        cmbBarbeiro = new JComboBox<>();
        lstServicos = new JList<>();
        txtDataHora = new JTextField(15);
        btnSalvar = new JButton("Salvar Agendamento");
        btnCancelar = new JButton("Cancelar");

        JLabel lblCliente = new JLabel("Cliente:");
        JLabel lblBarbeiro = new JLabel("Barbeiro:");
        JLabel lblServicos = new JLabel("Serviços (segure Ctrl para selecionar vários):");
        JLabel lblDataHora = new JLabel("Data e Hora (dd/MM/yyyy HH:mm):");

        JScrollPane scrollPaneServicos = new JScrollPane(lstServicos);
        
        Container contentPane = getContentPane();
        contentPane.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); 
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.LINE_END;
        contentPane.add(lblCliente, gbc);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        contentPane.add(cmbCliente, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        contentPane.add(lblBarbeiro, gbc);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        contentPane.add(cmbBarbeiro, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.FIRST_LINE_END;
        contentPane.add(lblServicos, gbc);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.weighty = 1.0; 
        gbc.fill = GridBagConstraints.BOTH;
        contentPane.add(scrollPaneServicos, gbc);
        gbc.weighty = 0.0; 
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.LINE_END;
        contentPane.add(lblDataHora, gbc);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        contentPane.add(txtDataHora, gbc);

        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        painelBotoes.add(btnSalvar);
        painelBotoes.add(btnCancelar);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2; 
        gbc.anchor = GridBagConstraints.CENTER;
        contentPane.add(painelBotoes, gbc);
        
        btnSalvar.addActionListener(this::btnSalvarActionPerformed);
        btnCancelar.addActionListener(e -> dispose());
        
        pack(); 
    }

    private void carregarCombosEListas() {
        try {
            List<Cliente> clientes = jpa.getEntityManager().createQuery("SELECT c FROM Cliente c ORDER BY c.nome", Cliente.class).getResultList();
            clientes.forEach(cmbCliente::addItem);

            List<Barbeiro> barbeiros = jpa.getEntityManager().createQuery("SELECT b FROM Barbeiro b ORDER BY b.nome", Barbeiro.class).getResultList();
            barbeiros.forEach(cmbBarbeiro::addItem);

            DefaultListModel<Servico> listModel = new DefaultListModel<>();
            List<Servico> servicos = jpa.getServicos();
            servicos.forEach(listModel::addElement);
            lstServicos.setModel(listModel);
            lstServicos.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar dados iniciais: " + e.getMessage());
        }
    }
    
    public void setAgendamento(Agendamento agendamento) {
       this.agendamento = agendamento; // Guarda o agendamento que será editado

   
    cmbCliente.setSelectedItem(agendamento.getCliente());
    cmbBarbeiro.setSelectedItem(agendamento.getBarbeiro());
    txtDataHora.setText(agendamento.getDataHora().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
   
    }

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {
       try {
      
        if (this.agendamento == null) {
            this.agendamento = new Agendamento();
        }
        
        Cliente clienteSelecionado = (Cliente) cmbCliente.getSelectedItem();
        Barbeiro barbeiroSelecionado = (Barbeiro) cmbBarbeiro.getSelectedItem();
        List<Servico> servicosSelecionados = lstServicos.getSelectedValuesList();

        if (clienteSelecionado == null || barbeiroSelecionado == null || servicosSelecionados.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Cliente, Barbeiro e ao menos um Serviço devem ser selecionados.", "Erro de Validação", JOptionPane.ERROR_MESSAGE);
            return; 
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime dataHora = LocalDateTime.parse(txtDataHora.getText(), formatter);

        agendamento.setCliente(clienteSelecionado);
        agendamento.setBarbeiro(barbeiroSelecionado);
        agendamento.setServicos(servicosSelecionados);
        agendamento.setDataHora(dataHora);
        agendamento.setStatus(StatusAgendamento.AGENDADO);
        
        double valorTotal = servicosSelecionados.stream().mapToDouble(Servico::getPreco).sum();
        agendamento.setValorTotal(valorTotal);

        jpa.persist(agendamento);
        
        JOptionPane.showMessageDialog(this, "Agendamento salvo com sucesso!");
        
        dispose(); 

    } catch (DateTimeParseException dtpe) {
         JOptionPane.showMessageDialog(this, "Formato de Data e Hora inválido. Use dd/MM/yyyy HH:mm.", "Erro de Formato", JOptionPane.ERROR_MESSAGE);
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Ocorreu um erro ao salvar o agendamento: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
    }
    }
    
    private JComboBox<Cliente> cmbCliente;
    private JComboBox<Barbeiro> cmbBarbeiro;
    private JList<Servico> lstServicos;
    private JTextField txtDataHora;
    private JButton btnSalvar;
    private JButton btnCancelar;
}