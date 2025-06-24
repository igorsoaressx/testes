package view;

import java.awt.Frame;
import javax.swing.JOptionPane;
import model.Carta;
import model.Categoria;

public class CadastroCartaJD extends javax.swing.JDialog {
    private Carta carta;

    public Carta getCarta() {
        return carta;
    }

    public void setCarta(Carta carta) {
        this.carta = carta;
    }

    public void loadCategorias(){
        cmbCategoria.removeAllItems();
        for(Categoria cat: Categoria.values()){
            cmbCategoria.addItem(cat);
        }
    }

    public CadastroCartaJD(Frame parent, boolean modal, Carta cartaSel) {
        super(parent, modal);
        initComponents();
        loadCategorias();
        this.carta = cartaSel;
        if (carta != null) {
            txtNome.setText(carta.getNome());
            cmbCategoria.setSelectedItem(carta.getCategoria());
            txtAtaque.setText(String.valueOf(carta.getAtaque()));
            txtDefesa.setText(String.valueOf(carta.getDefesa()));
        }
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        lblTitulo = new javax.swing.JLabel();
        lblNome = new javax.swing.JLabel();
        lblCategoria = new javax.swing.JLabel();
        lblAtaque = new javax.swing.JLabel();
        lblDefesa = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        cmbCategoria = new javax.swing.JComboBox<>();
        txtAtaque = new javax.swing.JTextField();
        txtDefesa = new javax.swing.JTextField();
        btnSalvar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        lblTitulo.setText("Cadastro de Carta");

        lblNome.setText("Nome:");

        lblCategoria.setText("Categoria:");

        lblAtaque.setText("Ataque:");

        lblDefesa.setText("Defesa:");

        btnSalvar.setText("Salvar");
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblNome)
                        .addGap(18, 18, 18)
                        .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblCategoria)
                            .addComponent(lblAtaque)
                            .addComponent(lblDefesa))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmbCategoria, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtAtaque)
                            .addComponent(txtDefesa)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(17, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(lblTitulo)
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNome)
                    .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCategoria)
                    .addComponent(cmbCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblAtaque)
                    .addComponent(txtAtaque, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDefesa)
                    .addComponent(txtDefesa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(65, 65, 65)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSalvar)
                    .addComponent(btnCancelar))
                .addContainerGap(41, Short.MAX_VALUE))
        );

        pack();
    }

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {
        dispose();
    }

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {
        if (txtNome.getText().trim().isEmpty()) {
        JOptionPane.showMessageDialog(this, "Nome não pode estar vazio");
        return;
    }
    try {
        int ataque = Integer.parseInt(txtAtaque.getText());
        int defesa = Integer.parseInt(txtDefesa.getText());

        if (carta == null) {
            carta = new Carta();
        }

        carta.setNome(txtNome.getText().trim());
        carta.setCategoria((Categoria)cmbCategoria.getSelectedItem());
        carta.setAtaque(ataque);
        carta.setDefesa(defesa);

        dispose();
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Ataque e Defesa devem ser números inteiros.");
    }
    }

    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JComboBox<Categoria> cmbCategoria;
    private javax.swing.JLabel lblAtaque;
    private javax.swing.JLabel lblCategoria;
    private javax.swing.JLabel lblDefesa;
    private javax.swing.JLabel lblNome;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JTextField txtAtaque;
    private javax.swing.JTextField txtDefesa;
    private javax.swing.JTextField txtNome;
}
