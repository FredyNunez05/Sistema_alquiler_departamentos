/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Vista;

/**
 *
 * @author USER
 */
public class FormLogin extends javax.swing.JFrame {
     public static java.util.ArrayList<String[]> listaRecepcionistas = new java.util.ArrayList<>();
    public static java.util.ArrayList<String[]> listaEmpleadosCompleta = new java.util.ArrayList<>();
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(FormLogin.class.getName());

   
 public FormLogin() {
    initComponents();

    // Configuración de la ventana
    setResizable(false);
    setLocationRelativeTo(null);

    // Hacer transparente el panel del formulario
    javax.swing.JPanel panel =
            (javax.swing.JPanel) getContentPane();
    panel.setOpaque(false);

    // Fondo con la imagen del edificio
    javax.swing.JLabel fondo = new javax.swing.JLabel() {
        @Override
        protected void paintComponent(java.awt.Graphics g) {
            super.paintComponent(g);

            java.awt.Graphics2D g2 =
                    (java.awt.Graphics2D) g.create();

            // Cargar la imagen
            java.net.URL ruta =
                    getClass().getResource(
                            "/Imagenes/DEPA JPEG.jpg");

            if (ruta != null) {
                java.awt.Image img =
                        new javax.swing.ImageIcon(ruta).getImage();

                g2.drawImage(
                        img, 0, 0,
                        getWidth(), getHeight(), this);
            }

            // Mejorar los bordes redondeados
            g2.setRenderingHint(
                    java.awt.RenderingHints.KEY_ANTIALIASING,
                    java.awt.RenderingHints.VALUE_ANTIALIAS_ON);

            // Sombra de la tarjeta
            g2.setColor(
                    new java.awt.Color(0, 0, 0, 35));

            g2.fillRoundRect(
                    40, 105, 350, 390, 25, 25);

            // Fondo blanco de la tarjeta
            g2.setColor(
                    new java.awt.Color(255, 255, 255, 235));

            g2.fillRoundRect(
                    35, 100, 350, 390, 25, 25);

            // Borde celeste
            g2.setColor(
                    new java.awt.Color(100, 190, 245));

            g2.setStroke(
                    new java.awt.BasicStroke(2.0f));

            g2.drawRoundRect(
                    35, 100, 350, 390, 25, 25);

            g2.dispose();
        }
    };

    // Ajustar el fondo al tamaño de la ventana
    fondo.setBounds(
            0, 0,
            getLayeredPane().getWidth(),
            getLayeredPane().getHeight());

    // Actualizar el fondo cuando cambie el tamaño
    getLayeredPane().addComponentListener(
            new java.awt.event.ComponentAdapter() {
        @Override
        public void componentResized(
                java.awt.event.ComponentEvent e) {

            fondo.setBounds(
                    0, 0,
                    getLayeredPane().getWidth(),
                    getLayeredPane().getHeight());

            fondo.repaint();
        }
    });

    // Colocar la imagen detrás de los controles
    getLayeredPane().add(
            fondo,
            javax.swing.JLayeredPane.FRAME_CONTENT_LAYER);

    getLayeredPane().setPosition(
            fondo,
            getLayeredPane().getComponentCount() - 1);

    // Cerrar las demás ventanas abiertas
    for (java.awt.Window w :
            java.awt.Window.getWindows()) {

        if (w != this) {
            w.dispose();
        }
    }
}
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTituloSistema = new javax.swing.JLabel();
        lblUsuario = new javax.swing.JLabel();
        lblContrasena = new javax.swing.JLabel();
        cboTipoIdentidad = new javax.swing.JComboBox<>();
        lblTipoIdentidad = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        pswContrasena = new javax.swing.JPasswordField();
        btnIngresar = new javax.swing.JButton();
        lblCargo = new javax.swing.JLabel();
        cboCargo = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        txtNroDocumento = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(900, 600));

        lblTituloSistema.setBackground(new java.awt.Color(255, 255, 255));
        lblTituloSistema.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblTituloSistema.setForeground(new java.awt.Color(25, 55, 110));
        lblTituloSistema.setText("  SISTEMA DE ALQUILER ");

        lblUsuario.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblUsuario.setText("USUARIO :");

        lblContrasena.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblContrasena.setText("CONTRASEÑA :");

        cboTipoIdentidad.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        cboTipoIdentidad.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "DNI", "CARNET DE EXTRANJERIA" }));
        cboTipoIdentidad.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(65, 145, 205), 2, true));

        lblTipoIdentidad.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblTipoIdentidad.setText("TIPO DE IDENTIDAD :");

        txtUsuario.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtUsuario.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(65, 145, 205), 2, true));

        pswContrasena.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(65, 145, 205), 2, true));

        btnIngresar.setBackground(new java.awt.Color(45, 130, 220));
        btnIngresar.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnIngresar.setForeground(new java.awt.Color(255, 255, 255));
        btnIngresar.setText("INGRESAR");
        btnIngresar.addActionListener(this::btnIngresarActionPerformed);

        lblCargo.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblCargo.setText("CARGO :");

        cboCargo.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        cboCargo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ADMINISTRADOR", "RECEPCIONISTA" }));
        cboCargo.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(65, 145, 205), 2, true));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("NRO DOCUMENTO :");

        txtNroDocumento.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtNroDocumento.setActionCommand("<Not Set>");
        txtNroDocumento.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(65, 145, 205), 2, true));

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(245, 115, 45));
        jLabel2.setText("DE DEPARTAMENTOS");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblCargo, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblUsuario)
                            .addComponent(lblTipoIdentidad)
                            .addComponent(lblContrasena))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cboTipoIdentidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cboCargo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNroDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pswContrasena, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(81, 81, 81)
                        .addComponent(lblTituloSistema, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(143, 143, 143)
                        .addComponent(jLabel2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(118, 118, 118)
                        .addComponent(btnIngresar)))
                .addContainerGap(109, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(73, Short.MAX_VALUE)
                .addComponent(lblTituloSistema, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTipoIdentidad, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboTipoIdentidad, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNroDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCargo, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboCargo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblUsuario)
                    .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblContrasena)
                    .addComponent(pswContrasena, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addComponent(btnIngresar)
                .addGap(115, 115, 115))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnIngresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIngresarActionPerformed
    
      try {
        // Guardar los datos que se ingresaron en el formulario
        String tipoDoc = cboTipoIdentidad.getSelectedItem().toString();
        String nroDoc = txtNroDocumento.getText().trim();
        String cargo = cboCargo.getSelectedItem().toString();
        String usuario = txtUsuario.getText().trim();
        String contra = new String(pswContrasena.getPassword());

        // Verificar que los campos obligatorios no esten vacios
        if (nroDoc.isEmpty() || usuario.isEmpty() || contra.isEmpty()) {
            throw new Exception("LLENA TODOS LOS CAMPOS");
        }

        // Verificar que el DNI tenga exactamente 8 numeros
        if (tipoDoc.equals("DNI") && !nroDoc.matches("\\d{8}")) {
            throw new Exception("DNI DEBE TENER 8 NUMEROS");
        }

        // Verificar que el carnet tenga entre 9 y 12 caracteres
        if (tipoDoc.equals("CARNET DE EXTRANJERIA")
                && !nroDoc.matches("[A-Za-z0-9]{9,12}")) {
            throw new Exception("CARNET DEBE TENER 9 A 12 CARACTERES");
        }

        // Comprobar si el cargo seleccionado es administrador
        if (cargo.equals("ADMINISTRADOR")) {

            // Comparar el documento, usuario y contraseña del administrador
            if (nroDoc.equals("60136617")
                    && usuario.equals("Grupo2")
                    && contra.equals("123456789")) {

                // Abrir el formulario principal y cerrar el login
                new FormPrincipal().setVisible(true);
                this.dispose();

            } else {
                // Si los datos no coinciden, lanzar un mensaje de error
                throw new Exception("DATOS DE ADMINISTRADOR INCORRECTOS");
            }

        } else {

            // Variable para saber si se encontro al recepcionista
            boolean encontrado = false;

            // Verificar que exista la lista de recepcionistas
            if (listaRecepcionistas == null) {
                throw new Exception("NO HAY RECEPCIONISTAS REGISTRADOS");
            }

            // Recorrer la lista para buscar al recepcionista
            for (String[] r : listaRecepcionistas) {

                // Comparar el documento, usuario y contraseña
                if (r != null && r.length >= 3
                        && r[0] != null && r[1] != null && r[2] != null
                        && r[0].equals(nroDoc)
                        && r[1].equals(usuario)
                        && r[2].equals(contra)) {

                    // Indicar que se encontro al recepcionista
                    encontrado = true;
                    break;
                }
            }

            // Si no se encontro, mostrar un mensaje de error
            if (!encontrado) {
                throw new Exception("RECEPCIONISTA NO REGISTRADO");
            }

            // Si los datos son correctos, abrir el formulario principal
            new FormPrincipal().setVisible(true);
            this.dispose();
        }

    } catch (Exception e) {

        // Capturar el error y mostrar el mensaje correspondiente
        javax.swing.JOptionPane.showMessageDialog(
                this,
                e.getMessage(),
                "VALIDACIÓN",
                javax.swing.JOptionPane.WARNING_MESSAGE
        );
    }



    }//GEN-LAST:event_btnIngresarActionPerformed

    
   public static void main(String args[]) {
        // Abrir el formulario de inicio de sesión
    java.awt.EventQueue.invokeLater(new Runnable() {
        @Override
        public void run() {
            new FormLogin().setVisible(true);
        }
    });
   }
     


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnIngresar;
    private javax.swing.JComboBox<String> cboCargo;
    private javax.swing.JComboBox<String> cboTipoIdentidad;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel lblCargo;
    private javax.swing.JLabel lblContrasena;
    private javax.swing.JLabel lblTipoIdentidad;
    private javax.swing.JLabel lblTituloSistema;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JPasswordField pswContrasena;
    private javax.swing.JTextField txtNroDocumento;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
