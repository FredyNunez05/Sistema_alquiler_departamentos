/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Vista;

import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

public final class FormPrincipal extends javax.swing.JFrame {
    
    
private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(FormPrincipal.class.getName());

private static final java.util.ArrayList<String[]> listaEmpleadosCompleta =
        new java.util.ArrayList<>();

private static final java.util.ArrayList<String[]> listaRecepcionistas =
        FormLogin.listaRecepcionistas;

private static final java.util.ArrayList<String[]> listaInquilinos =
        new java.util.ArrayList<>();
// Lista para guardar los departamentos registrados
private static final java.util.ArrayList<String[]> listaDepartamentos =
        new java.util.ArrayList<>();





private boolean seleccionandoPorBusqueda = false;
private boolean modoEdicionEmpleado = false;
private boolean buscandoInquilino = false;

private boolean modoEdicionInquilino = false;

// ===============================
// VALIDAR DATOS DEL EMPLEADO
// ===============================

private boolean validarEmpleado() {

    String nombres = txtNombres.getText().trim();
    String apellidos = txtApellidos.getText().trim();
    String nroDoc = txtNroDocEmpleado.getText().trim();
    String telefono = txtTelefono.getText().trim();
    String direccion = txtDireccion.getText().trim();
    String sueldo = txtSueldo.getText().trim();
    String fechaInicio = txtFechaInicio.getText().trim();
    String fechaTermino = txtFechaTermino.getText().trim();
    String usuario = txtUsuarioEmpleado.getText().trim();
    String contrasena =
            new String(pswContrasenaEmpleado.getPassword()).trim();

    // Verificar campos obligatorios
    if (nombres.isEmpty() || apellidos.isEmpty()
        || nroDoc.isEmpty() || telefono.isEmpty()
        || direccion.isEmpty() || sueldo.isEmpty()
        || fechaInicio.isEmpty()) {

        javax.swing.JOptionPane.showMessageDialog(
                this,
                "LLENA TODOS LOS CAMPOS OBLIGATORIOS."
        );
        return false;
    }

    // Validar nombres
    if (!nombres.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")
            || nombres.length() < 3) {

        javax.swing.JOptionPane.showMessageDialog(
                this,
                "LOS NOMBRES SOLO DEBEN CONTENER LETRAS."
        );
        return false;
    }

    // Validar apellidos
    if (!apellidos.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")
            || apellidos.length() < 3) {

        javax.swing.JOptionPane.showMessageDialog(
                this,
                "LOS APELLIDOS SOLO DEBEN CONTENER LETRAS."
        );
        return false;
    }

    // Validar tipo de documento
    String tipoDoc =
            cboTipoDocEmpleado.getSelectedItem().toString();

    if (tipoDoc.equalsIgnoreCase("DNI")) {

        if (!nroDoc.matches("\\d{8}")) {

            javax.swing.JOptionPane.showMessageDialog(
                    this,
                    "EL DNI DEBE TENER 8 NÚMEROS."
            );
            return false;
        }

    } else {

        if (!nroDoc.matches("[A-Za-z0-9]{9,12}")) {

            javax.swing.JOptionPane.showMessageDialog(
                    this,
                    "EL CARNET DE EXTRANJERÍA DEBE TENER ENTRE 9 Y 12 CARACTERES."
            );
            return false;
        }
    }

    // Validar teléfono
    if (!telefono.matches("\\d{9}")) {

        javax.swing.JOptionPane.showMessageDialog(
                this,
                "EL TELÉFONO DEBE TENER 9 NÚMEROS."
        );
        return false;
    }

    // Validar dirección
    if (direccion.length() < 3) {

        javax.swing.JOptionPane.showMessageDialog(
                this,
                "INGRESA UNA DIRECCIÓN VÁLIDA."
        );
        return false;
    }

    // Validar sueldo
    try {
    double sueldoValor = Double.parseDouble(sueldo);

    if (sueldoValor <= 0) {
        JOptionPane.showMessageDialog(
                this,
                "EL SUELDO DEBE SER MAYOR QUE 0."
        );
        return false;
    }

    if (sueldoValor >= 4000) {
        JOptionPane.showMessageDialog(
                this,
                "EL SUELDO DEBE SER MENOR A S/ 4,000."
        );
        return false;
    }

    } catch (NumberFormatException e) {
    JOptionPane.showMessageDialog(
            this,
            "EL SUELDO DEBE SER UN NÚMERO VÁLIDO."
    );
    return false;
}
    

    // Validar fecha de inicio
    java.time.format.DateTimeFormatter formato =
            java.time.format.DateTimeFormatter.ofPattern("dd/MM/uuuu")
                    .withResolverStyle(
                            java.time.format.ResolverStyle.STRICT
                    );

    java.time.LocalDate inicio;

    try {

        inicio = java.time.LocalDate.parse(
                fechaInicio,
                formato
        );

    } catch (java.time.format.DateTimeParseException e) {

        javax.swing.JOptionPane.showMessageDialog(
                this,
                "LA FECHA DE INICIO DEBE TENER EL FORMATO dd/MM/aaaa."
        );
        return false;
    }
    

   // Validar fecha de término si fue ingresada
    if (!fechaTermino.isEmpty()) {

    try {

        java.time.LocalDate termino =
                java.time.LocalDate.parse(
                        fechaTermino,
                        formato
                );

        java.time.LocalDate hoy =
                java.time.LocalDate.now();

        if (inicio.isBefore(hoy)) {

            javax.swing.JOptionPane.showMessageDialog(
                    this,
                    "LA FECHA DE INICIO NO PUEDE SER ANTERIOR A LA FECHA ACTUAL."
            );
            return false;
        }

        if (termino.isBefore(hoy)) {

            javax.swing.JOptionPane.showMessageDialog(
                    this,
                    "LA FECHA DE TÉRMINO NO PUEDE SER ANTERIOR A LA FECHA ACTUAL."
            );
            return false;
        }

        if (termino.isBefore(inicio)) {

            javax.swing.JOptionPane.showMessageDialog(
                    this,
                    "LA FECHA DE TÉRMINO NO PUEDE SER ANTERIOR A LA FECHA DE INICIO."
            );
            return false;
        }

    } catch (java.time.format.DateTimeParseException e) {

        javax.swing.JOptionPane.showMessageDialog(
                this,
                "LA FECHA DE TÉRMINO DEBE TENER EL FORMATO dd/MM/aaaa."
        );
        return false;
     }
   }

    // Validar usuario
    if (!usuario.isEmpty()
        && (usuario.length() < 3 || usuario.contains(" "))) {

        javax.swing.JOptionPane.showMessageDialog(
                this,
                "EL USUARIO DEBE TENER AL MENOS 3 CARACTERES Y NO TENER ESPACIOS."
        );
        return false;
    }

    // Validar contraseña
    if (!contrasena.isEmpty() && contrasena.length() < 4) {

        javax.swing.JOptionPane.showMessageDialog(
                this,
                "LA CONTRASEÑA DEBE TENER AL MENOS 4 CARACTERES."
        );
        return false;
    }

    return true;
}


// =====================================================
// CARGAR EMPLEADOS EN LA TABLA
// =====================================================

private void cargarEmpleados() {

    javax.swing.table.DefaultTableModel modelo =
            (javax.swing.table.DefaultTableModel)
                    tablaEmpleados.getModel();

    modelo.setRowCount(0);

    for (String[] empleado : listaEmpleadosCompleta) {

        modelo.addRow(new Object[]{
            empleado[0],
            empleado[1],
            empleado[2],
            empleado[3],
            empleado[4],
            empleado[5],
            empleado[6],
            empleado[7],
            empleado[8],
            empleado[9],
        });
    }
}


// =====================================================
// LIMPIAR CAMPOS DE EMPLEADO
// =====================================================

private void limpiarEmpleado() {

    txtNombres.setText("");
    txtApellidos.setText("");
    txtNroDocEmpleado.setText("");
    txtTelefono.setText("");
    txtDireccion.setText("");
    txtSueldo.setText("");
    txtFechaInicio.setText("");
    txtFechaTermino.setText("");
    txtUsuarioEmpleado.setText("");
    pswContrasenaEmpleado.setText("");

    if (cboTipoDocEmpleado.getItemCount() > 0) {
        cboTipoDocEmpleado.setSelectedIndex(0);
    }

    if (cboCargoEmpleado.getItemCount() > 0) {
        cboCargoEmpleado.setSelectedIndex(0);
    }

    tablaEmpleados.clearSelection();
    txtUsuarioEmpleado.setEnabled(true);
    pswContrasenaEmpleado.setEnabled(true);
}


// =====================================================
// BUSCAR EMPLEADO POR DOCUMENTO
// =====================================================

private int buscarIndiceEmpleado(String nroDoc) {

    for (int i = 0; i < listaEmpleadosCompleta.size(); i++) {

        if (listaEmpleadosCompleta.get(i)[3]
                .equalsIgnoreCase(nroDoc)) {

            return i;
        }
    }

    return -1;
}


// =====================================================
// CARGAR LOS DATOS DE LA FILA SELECCIONADA
// =====================================================

private void cargarEmpleadoSeleccionado() {

    int fila = tablaEmpleados.getSelectedRow();

    if (fila == -1) {
        return;
    }

    txtNombres.setText(
            tablaEmpleados.getValueAt(fila, 0).toString()
    );

    txtApellidos.setText(
            tablaEmpleados.getValueAt(fila, 1).toString()
    );

    cboTipoDocEmpleado.setSelectedItem(
            tablaEmpleados.getValueAt(fila, 2).toString()
    );

    txtNroDocEmpleado.setText(
            tablaEmpleados.getValueAt(fila, 3).toString()
    );

    txtTelefono.setText(
            tablaEmpleados.getValueAt(fila, 4).toString()
    );

    txtDireccion.setText(
            tablaEmpleados.getValueAt(fila, 5).toString()
    );

    cboCargoEmpleado.setSelectedItem(
            tablaEmpleados.getValueAt(fila, 6).toString()
    );

    txtSueldo.setText(
            tablaEmpleados.getValueAt(fila, 7).toString()
    );

    txtFechaInicio.setText(
            tablaEmpleados.getValueAt(fila, 8).toString()
    );

    txtFechaTermino.setText(
            tablaEmpleados.getValueAt(fila, 9).toString()
    );

    String[] empleado = listaEmpleadosCompleta.get(fila);

    txtUsuarioEmpleado.setText(empleado[10]);
    pswContrasenaEmpleado.setText("");

    txtUsuarioEmpleado.setEnabled(false);
    pswContrasenaEmpleado.setEnabled(false);
}

  
    public FormPrincipal() {
        initComponents();
        
         cargarDatosIniciales();
         cargarDepartamentos();
        
        
        // CENRAR ALQUILER
        javax.swing.table.DefaultTableCellRenderer centradoAlquiler =
        new javax.swing.table.DefaultTableCellRenderer();

centradoAlquiler.setHorizontalAlignment(
        javax.swing.SwingConstants.CENTER
);

for (int i = 0; i < tablaAlquileres.getColumnCount(); i++) {

    tablaAlquileres.getColumnModel()
            .getColumn(i)
            .setCellRenderer(centradoAlquiler);
}
        //CENTRAR DATOS DE INQUILINOS
        
    javax.swing.table.DefaultTableCellRenderer centrado =
        new javax.swing.table.DefaultTableCellRenderer();

    centrado.setHorizontalAlignment(
        javax.swing.SwingConstants.CENTER
    );

    for (int i = 0; i < tblInquilinos.getColumnCount(); i++) {
    tblInquilinos.getColumnModel()
            .getColumn(i)
            .setCellRenderer(centrado);
    }
        
    // PONER TODAS LAS TABLAS EN NEGRITA

    // Tabla de alquileres
    tablaAlquileres.setFont(
        new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 12)
    );
    tablaAlquileres.getTableHeader().setFont(
        new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 12)
    );

    // Tabla de inquilinos
    tblInquilinos.setFont(
        new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 12)
    );
    tblInquilinos.getTableHeader().setFont(
        new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 12)
    );

    // Tabla de empleados
    tablaEmpleados.setFont(
        new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 12)
    );
    tablaEmpleados.getTableHeader().setFont(
        new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 12)
    );

    // Tabla de departamentos
    tblDepartamentos.setFont(
        new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 12)
    );
    tblDepartamentos.getTableHeader().setFont(
        new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 12)
    );
      centrado.setHorizontalAlignment(
    javax.swing.SwingConstants.CENTER
);
tblDepartamentos.setDefaultRenderer(Object.class, centrado);

centradoAlquiler.setHorizontalAlignment(
        javax.swing.SwingConstants.CENTER
);

for (int i = 0; i < tablaAlquileres.getColumnCount(); i++) {

    tablaAlquileres.getColumnModel()
            .getColumn(i)
            .setCellRenderer(centradoAlquiler);
}
    
        // Al seleccionar una fila, cargar sus datos en los campos
       tablaEmpleados.getSelectionModel().addListSelectionListener(e -> {
    if (!e.getValueIsAdjusting() && !seleccionandoPorBusqueda) {
        cargarEmpleadoSeleccionado();
      }
    });
         // Cargar empleados guardados
        cargarEmpleados();
        
        cargarInquilinos();
      tblInquilinos.getSelectionModel().addListSelectionListener(e -> {

    if (!e.getValueIsAdjusting() && !buscandoInquilino) {
        cargarInquilinoSeleccionado();
    }

       });
    }
    

// Cargar datos de ejemplo al iniciar el sistema
private void cargarDatosIniciales() {

    // Registrar inquilinos de ejemplo
    if (listaInquilinos.isEmpty()) {
        listaInquilinos.add(new String[]{
            "Ana", "Torres", "DNI", "12345678", "987654321"
        });

        listaInquilinos.add(new String[]{
            "Luis", "Ramírez", "DNI", "87654321", "912345678"
        });

        cargarInquilinos();
    }

    // Actualizar la lista de DNI para alquileres
    cargarDniInquilinos();
    
    
    // Registrar departamentos de ejemplo
if (listaDepartamentos.isEmpty()) {

    listaDepartamentos.add(new String[]{
        "101", "1", "2", "1", "800", "Disponible",
        "Departamento de dos habitaciones"
    });

    listaDepartamentos.add(new String[]{
        "202", "2", "3", "2", "1200", "Disponible",
        "Departamento amplio"
    });

    cargarDepartamentos();
    cargarNumerosDepartamentos();
}

}
    
    
  

       
    @SuppressWarnings("unchecked")
    private void cargarInquilinoSeleccionado() {

    int fila = tblInquilinos.getSelectedRow();

    if (fila == -1) {
        return;
    }

    txtNombresInquilino.setText(
            tblInquilinos.getValueAt(fila, 0).toString()
    );

    txtApellidosInquilino.setText(
            tblInquilinos.getValueAt(fila, 1).toString()
    );

    cboTipoDocInquilino.setSelectedItem(
            tblInquilinos.getValueAt(fila, 2).toString()
    );

    txtNroDocInquilino.setText(
            tblInquilinos.getValueAt(fila, 3).toString()
    );

    txtTelefonoInquilino.setText(
            tblInquilinos.getValueAt(fila, 4).toString()
    );
}
    
    private boolean validarInquilino() {
    
    String nombres = txtNombresInquilino.getText().trim();
    String apellidos = txtApellidosInquilino.getText().trim();
    String nroDoc = txtNroDocInquilino.getText().trim();
    String telefono = txtTelefonoInquilino.getText().trim();
    String tipoDoc = cboTipoDocInquilino.getSelectedItem().toString();

    // Campos obligatorios
    if (nombres.isEmpty() || apellidos.isEmpty()
            || nroDoc.isEmpty() || telefono.isEmpty()) {

        javax.swing.JOptionPane.showMessageDialog(
                this,
                "LLENA TODOS LOS CAMPOS."
        );
        return false;
    }

    // Validar nombres
   if (!nombres.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]{3,}")) {

    JOptionPane.showMessageDialog(
            this,
            "NO SE PUEDEN COLOCAR NÚMEROS NI CARACTERES ESPECIALES EN LOS NOMBRES. MÍNIMO 3 LETRAS."
    );
    return false;
    }

    // Validar apellidos
    if (!apellidos.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]{3,}")) {

    JOptionPane.showMessageDialog(
            this,
            "NO SE PUEDEN COLOCAR NÚMEROS NI CARACTERES ESPECIALES EN LOS APELLIDOS. MÍNIMO 3 LETRAS."
    );
    return false;
   }

    // Validar documento
    if (tipoDoc.equals("DNI")) {

        if (!nroDoc.matches("\\d{8}")) {

            javax.swing.JOptionPane.showMessageDialog(
                    this,
                    "EL DNI DEBE TENER 8 NÚMEROS."
            );
            return false;
        }

    } else {

        if (!nroDoc.matches("[A-Za-z0-9]{9,12}")) {

            javax.swing.JOptionPane.showMessageDialog(
                    this,
                    "EL CARNET DE EXTRANJERÍA DEBE TENER ENTRE 9 Y 12 CARACTERES."
            );
            return false;
        }
    }

    // Validar teléfono
    if (!telefono.matches("\\d{9}")) {

        javax.swing.JOptionPane.showMessageDialog(
                this,
                "EL TELÉFONO DEBE TENER 9 NÚMEROS."
        );
        return false;
    }

    return true;
}


private void cargarInquilinos() {

    javax.swing.table.DefaultTableModel modelo =
            (javax.swing.table.DefaultTableModel) tblInquilinos.getModel();

    modelo.setRowCount(0);

    for (String[] inquilino : listaInquilinos) {

        modelo.addRow(new Object[]{
            inquilino[0],
            inquilino[1],
            inquilino[2],
            inquilino[3],
            inquilino[4]
        });
    }
    
}
// Cargar los DNI de los inquilinos en alquileres
private void cargarDniInquilinos() {
    cboInquilinoAlquiler.removeAllItems();

    for (String[] inquilino : listaInquilinos) {
        cboInquilinoAlquiler.addItem(inquilino[3]);
    }
}


private void limpiarInquilino() {

    txtNombresInquilino.setText("");
    txtApellidosInquilino.setText("");
    txtNroDocInquilino.setText("");
    txtTelefonoInquilino.setText("");

    cboTipoDocInquilino.setSelectedIndex(0);

    tblInquilinos.clearSelection();
}

// Mostrar los departamentos registrados en la tabla
private void cargarDepartamentos() {

    javax.swing.table.DefaultTableModel modelo =
            (javax.swing.table.DefaultTableModel)
                    tblDepartamentos.getModel();

    modelo.setRowCount(0);

    for (String[] departamento : listaDepartamentos) {
        modelo.addRow(new Object[]{
            departamento[0],
            departamento[1],
            departamento[2],
            departamento[3],
            departamento[4],
            departamento[5],
            departamento[6]
        });
    }
}
// Cargar los números de departamento en alquileres
private void cargarNumerosDepartamentos() {

    cboDeptoAlquiler.removeAllItems();

    for (String[] departamento : listaDepartamentos) {
        cboDeptoAlquiler.addItem(departamento[0]);
    }
}


private int buscarIndiceInquilino(String nroDoc) {

    for (int i = 0; i < listaInquilinos.size(); i++) {

        if (listaInquilinos.get(i)[3]
                .equalsIgnoreCase(nroDoc)) {

            return i;
        }
    }

    return -1;
}
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        btnGenerarAlquiler = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablaAlquileres = new javax.swing.JTable();
        cboInquilinoAlquiler = new javax.swing.JComboBox<>();
        cboDeptoAlquiler = new javax.swing.JComboBox<>();
        txtFechaInicioAlquiler = new javax.swing.JTextField();
        txtMontoAlquiler = new javax.swing.JTextField();
        btnEditarAlquiler = new javax.swing.JButton();
        btnEliminarAlquiler = new javax.swing.JButton();
        txtFechaFinAlquiler = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtMesesAlquiler = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        txtFechaPago = new javax.swing.JTextField();
        cboMetodoPago = new javax.swing.JComboBox<>();
        jLabel32 = new javax.swing.JLabel();
        btnSalirAlquiler = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        cboTipoDocInquilino = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblInquilinos = new javax.swing.JTable();
        btnGuardarInquilino = new javax.swing.JButton();
        btnEditarInquilino = new javax.swing.JButton();
        btnEliminarInquilino = new javax.swing.JButton();
        btnBuscarInquilino = new javax.swing.JButton();
        jLabel30 = new javax.swing.JLabel();
        txtNombresInquilino = new javax.swing.JTextField();
        txtApellidosInquilino = new javax.swing.JTextField();
        txtNroDocInquilino = new javax.swing.JTextField();
        txtTelefonoInquilino = new javax.swing.JTextField();
        btnSalirInquilinos = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        txtSueldo = new javax.swing.JTextField();
        jScrollPane5 = new javax.swing.JScrollPane();
        tablaEmpleados = new javax.swing.JTable();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        btnGuardar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        txtFechaInicio = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        btnBuscar = new javax.swing.JButton();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        txtUsuarioEmpleado = new javax.swing.JTextField();
        pswContrasenaEmpleado = new javax.swing.JPasswordField();
        txtDireccion = new javax.swing.JTextField();
        txtTelefono = new javax.swing.JTextField();
        txtNroDocEmpleado = new javax.swing.JTextField();
        cboTipoDocEmpleado = new javax.swing.JComboBox<>();
        txtApellidos = new javax.swing.JTextField();
        txtNombres = new javax.swing.JTextField();
        cboCargoEmpleado = new javax.swing.JComboBox<>();
        txtFechaTermino = new javax.swing.JTextField();
        btnSalirEmpleados = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        btnReporteAlquileres = new javax.swing.JButton();
        btnReportePagos = new javax.swing.JButton();
        btnReporteInquilinos = new javax.swing.JButton();
        btnReporteEmpleados = new javax.swing.JButton();
        btnReporteDepartamentos = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblReportes = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        btnModificarDpto = new javax.swing.JButton();
        btnEliminarDpto = new javax.swing.JButton();
        cboEstado = new javax.swing.JComboBox<>();
        btnRegistrarDpto = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblDepartamentos = new javax.swing.JTable();
        jLabel29 = new javax.swing.JLabel();
        btnBuscarDpto = new javax.swing.JButton();
        txtNroDpto = new javax.swing.JTextField();
        txtPiso = new javax.swing.JTextField();
        txtNroHabitaciones = new javax.swing.JTextField();
        txtNroBanos = new javax.swing.JTextField();
        txtPrecioMensual = new javax.swing.JTextField();
        txtDescripcion = new javax.swing.JTextField();
        btnSalirDepartamentos = new javax.swing.JButton();
        jTabbedPane2 = new javax.swing.JTabbedPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTabbedPane1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel16.setText("SELECCIONE NRO DOC INQUILINO :");

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel17.setText("SELECCIONE N° DEPTO :");

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel18.setText("FECHA DE INICIO :");

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel19.setText("FECHA FIN :");

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel20.setText("MONTO S/.");

        btnGenerarAlquiler.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnGenerarAlquiler.setText("GENERAR ALQUILER");
        btnGenerarAlquiler.addActionListener(this::btnGenerarAlquilerActionPerformed);

        tablaAlquileres.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        tablaAlquileres.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        tablaAlquileres.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "NOMBRES INQUILINO", "NRO DE DEPTO", "FECHA DE INICIO", "FECHA FIN", "MONTO POR MES"
            }
        ));
        jScrollPane3.setViewportView(tablaAlquileres);

        cboInquilinoAlquiler.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        cboInquilinoAlquiler.addActionListener(this::cboInquilinoAlquilerActionPerformed);

        cboDeptoAlquiler.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        btnEditarAlquiler.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnEditarAlquiler.setText("EDITAR");

        btnEliminarAlquiler.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnEliminarAlquiler.setText("ELIMINAR ALQUILER");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setText("MESES DE ALQUILER :");

        jLabel31.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel31.setText("DATOS DEL PAGO");

        txtFechaPago.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        cboMetodoPago.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        cboMetodoPago.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Efectivo", "Yape", "Plin", "Transferencia" }));
        cboMetodoPago.addActionListener(this::cboMetodoPagoActionPerformed);

        jLabel32.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel32.setText("FECHA DE PAGO");

        btnSalirAlquiler.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnSalirAlquiler.setText("SALIR");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(145, 145, 145)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtFechaPago, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboMetodoPago, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(66, 66, 66)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnGenerarAlquiler)
                    .addComponent(btnEliminarAlquiler))
                .addGap(28, 28, 28)
                .addComponent(btnEditarAlquiler)
                .addGap(76, 76, 76)
                .addComponent(btnSalirAlquiler)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel16)
                                    .addComponent(jLabel17))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cboDeptoAlquiler, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cboInquilinoAlquiler, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel20)
                                        .addComponent(jLabel8))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addGap(0, 0, Short.MAX_VALUE)
                                            .addComponent(txtMontoAlquiler, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(txtMesesAlquiler, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(0, 0, Short.MAX_VALUE))))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtFechaFinAlquiler, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel18)
                                    .addGap(18, 18, 18)
                                    .addComponent(txtFechaInicioAlquiler, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 706, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(73, 73, 73))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 381, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16)
                            .addComponent(cboInquilinoAlquiler, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cboDeptoAlquiler, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtFechaInicioAlquiler, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtFechaFinAlquiler, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(5, 5, 5)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txtMesesAlquiler, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel20)
                            .addComponent(txtMontoAlquiler, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel31)
                        .addGap(34, 34, 34)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(btnGenerarAlquiler)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(txtFechaPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cboMetodoPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnEliminarAlquiler)
                                .addComponent(btnSalirAlquiler)))
                        .addGap(22, 22, 22))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(btnEditarAlquiler)
                        .addGap(34, 34, 34))))
        );

        jTabbedPane1.addTab("ALQUILER", jPanel1);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("NOMBRES :");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("APELLIDOS :");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("TIPO DOCUMENTO :");

        cboTipoDocInquilino.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        cboTipoDocInquilino.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "DNI", "CARNET EXTRANJERIA" }));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setText("TELEFONO :");

        tblInquilinos.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        tblInquilinos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "NOMBRES ", "APELLIDOS", "TIPO DOC", "NRO DOCUMENTO", "TELEFONO"
            }
        ));
        jScrollPane1.setViewportView(tblInquilinos);

        btnGuardarInquilino.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnGuardarInquilino.setText("GUARDAR INQUILINO");
        btnGuardarInquilino.addActionListener(this::btnGuardarInquilinoActionPerformed);

        btnEditarInquilino.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnEditarInquilino.setText("EDITAR");
        btnEditarInquilino.addActionListener(this::btnEditarInquilinoActionPerformed);

        btnEliminarInquilino.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnEliminarInquilino.setText("ELIMINAR");
        btnEliminarInquilino.addActionListener(this::btnEliminarInquilinoActionPerformed);

        btnBuscarInquilino.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnBuscarInquilino.setText("BUSCAR");
        btnBuscarInquilino.addActionListener(this::btnBuscarInquilinoActionPerformed);

        jLabel30.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel30.setText("NRO DOCUMENTO :");

        btnSalirInquilinos.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnSalirInquilinos.setText("SALIR");
        btnSalirInquilinos.addActionListener(this::btnSalirInquilinosActionPerformed);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtApellidosInquilino, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtNombresInquilino, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(jLabel30, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGap(32, 32, 32)
                                                .addComponent(cboTipoDocInquilino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGap(18, 18, 18)
                                                .addComponent(txtNroDocInquilino, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(btnGuardarInquilino, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(txtTelefonoInquilino, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(125, 125, 125)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(btnEliminarInquilino)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(btnEditarInquilino, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(btnBuscarInquilino, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(4, 4, 4)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnSalirInquilinos)
                        .addGap(70, 70, 70)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 566, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(184, 184, 184))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNombresInquilino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtApellidosInquilino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cboTipoDocInquilino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel30)
                            .addComponent(txtNroDocInquilino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(txtTelefonoInquilino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31)
                        .addComponent(btnGuardarInquilino)
                        .addGap(30, 30, 30)
                        .addComponent(btnEditarInquilino)
                        .addGap(18, 18, 18)
                        .addComponent(btnBuscarInquilino)
                        .addGap(18, 18, 18)
                        .addComponent(btnEliminarInquilino)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSalirInquilinos))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(60, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("INQUILINOS", jPanel2);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("NOMBRES :");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setText("APELLIDOS :");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setText("TIPO DOC :");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setText("CARGO :");

        jLabel21.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel21.setText("SUELDO :");

        txtSueldo.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        tablaEmpleados.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        tablaEmpleados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "NOMBRES ", "APELLIDOS", "TIPO DOC", "N°  DOC", "TELEFONO", "DIRECCION", "CARGO", "SUELDO", "F. INICIO", "F. TERMINO"
            }
        ));
        jScrollPane5.setViewportView(tablaEmpleados);

        jLabel22.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel22.setText("TELEFONO :");

        jLabel23.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel23.setText("DIRECCION :");

        jLabel24.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel24.setText("FECHA DE INICIO :");

        jLabel25.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel25.setText("FECHA DE TERMINO :");

        btnGuardar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnGuardar.setText("GUARDAR");
        btnGuardar.addActionListener(this::btnGuardarActionPerformed);

        btnEditar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnEditar.setText("EDITAR");
        btnEditar.addActionListener(this::btnEditarActionPerformed);

        btnEliminar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnEliminar.setText("ELIMINAR");
        btnEliminar.addActionListener(this::btnEliminarActionPerformed);

        jLabel26.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel26.setText("NRO DOCUMENTO :");

        btnBuscar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnBuscar.setText("BUSCAR POR NRO DOC");
        btnBuscar.setActionCommand("");
        btnBuscar.addActionListener(this::btnBuscarActionPerformed);

        jLabel27.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel27.setText("USUARIO :");

        jLabel28.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel28.setText("CONTRASEÑA :");

        cboTipoDocEmpleado.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        cboTipoDocEmpleado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "DNI", "CARNET DE EXTRANJERIA" }));

        cboCargoEmpleado.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        cboCargoEmpleado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Recepcionista", "Limpieza" }));

        btnSalirEmpleados.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnSalirEmpleados.setText("Salir");
        btnSalirEmpleados.addActionListener(this::btnSalirEmpleadosActionPerformed);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addComponent(btnEliminar)
                        .addGap(18, 18, 18)
                        .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnGuardar)
                        .addGap(31, 31, 31)
                        .addComponent(btnEditar)
                        .addGap(37, 37, 37)
                        .addComponent(btnSalirEmpleados)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtNombres, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtSueldo, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cboCargoEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel22)
                                .addGap(18, 18, 18)
                                .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel26)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNroDocEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel24)
                                .addGap(18, 18, 18)
                                .addComponent(txtFechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(cboTipoDocEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel27)
                                    .addComponent(jLabel28))
                                .addGap(19, 19, 19)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(pswContrasenaEmpleado)
                                    .addComponent(txtUsuarioEmpleado)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel25)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtFechaTermino, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 785, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNombres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(9, 9, 9)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(cboTipoDocEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNroDocEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel22)
                            .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel23)
                            .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(cboCargoEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel21)
                            .addComponent(txtSueldo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel24)
                            .addComponent(txtFechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(24, 24, 24)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel25)
                            .addComponent(txtFechaTermino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel27)
                            .addComponent(txtUsuarioEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel28)
                            .addComponent(pswContrasenaEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 398, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 65, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEliminar)
                    .addComponent(btnBuscar)
                    .addComponent(btnGuardar)
                    .addComponent(btnEditar)
                    .addComponent(btnSalirEmpleados))
                .addGap(15, 15, 15))
        );

        jLabel24.getAccessibleContext().setAccessibleName("");
        jLabel25.getAccessibleContext().setAccessibleName("Fe:");

        jTabbedPane1.addTab("EMPLEADOS", jPanel3);

        btnReporteAlquileres.setText("REPORTE DE ALQUILERES");

        btnReportePagos.setText("REPORTE DE PAGOS");

        btnReporteInquilinos.setText("REPORTE DE INQUILINOS");

        btnReporteEmpleados.setText("REPORTE DE EMPLEADOS");

        btnReporteDepartamentos.setText("REPORTE DE DEPARTAMENTOS");

        tblReportes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(tblReportes);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(79, 79, 79)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnReporteAlquileres)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(btnReporteDepartamentos)
                                .addComponent(btnReporteInquilinos)))
                        .addGap(259, 259, 259)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnReportePagos)
                            .addComponent(btnReporteEmpleados)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(184, 184, 184)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(414, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(87, 87, 87)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnReporteAlquileres)
                    .addComponent(btnReportePagos))
                .addGap(61, 61, 61)
                .addComponent(btnReporteDepartamentos)
                .addGap(41, 41, 41)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnReporteInquilinos)
                    .addComponent(btnReporteEmpleados))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );

        jTabbedPane1.addTab("REPORTES", jPanel5);

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel10.setText("N° DEPTO :");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel11.setText("PISO :");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel12.setText("N° HABITACIONES :");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel13.setText("N° BAÑOS :");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel14.setText("PRECIO MENSUAL  : S/");

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel15.setText("ESTADO DEL DPTO :");

        btnModificarDpto.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnModificarDpto.setText("MODIFICAR DPTO");

        btnEliminarDpto.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnEliminarDpto.setText("ELIMINAR DPTO");

        cboEstado.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        cboEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Diponible", "Alquilado" }));

        btnRegistrarDpto.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnRegistrarDpto.setText("REGISTRAR DPTO");
        btnRegistrarDpto.addActionListener(this::btnRegistrarDptoActionPerformed);

        tblDepartamentos.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        tblDepartamentos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "N° DEPTO", "PISO", "N° HABITACIONES", "N° DE BAÑOS", "PRECIO POR MES", "ESTADO", "DESCRIPCION "
            }
        ));
        jScrollPane4.setViewportView(tblDepartamentos);

        jLabel29.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel29.setText("DESCRIPCION :");

        btnBuscarDpto.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnBuscarDpto.setText("BUSCAR DPTO");

        btnSalirDepartamentos.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnSalirDepartamentos.setText("SALIR");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addGap(18, 18, 18)
                        .addComponent(txtNroHabitaciones, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addGap(27, 27, 27)
                        .addComponent(txtNroBanos, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(txtPiso, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(1, 1, 1)
                            .addComponent(txtNroDpto, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPrecioMensual, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15)
                            .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cboEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnModificarDpto)
                            .addComponent(btnRegistrarDpto))
                        .addGap(29, 29, 29)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnEliminarDpto)
                            .addComponent(btnBuscarDpto))))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 738, Short.MAX_VALUE)
                .addGap(45, 45, 45))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(148, 148, 148)
                .addComponent(btnSalirDepartamentos)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNroDpto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(txtPiso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNroHabitaciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNroBanos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPrecioMensual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cboEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel29)
                            .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnRegistrarDpto)
                            .addComponent(btnEliminarDpto))
                        .addGap(17, 17, 17)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnModificarDpto)
                            .addComponent(btnBuscarDpto)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 412, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(13, 13, 13)
                .addComponent(btnSalirDepartamentos)
                .addContainerGap(72, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("DEPARTAMENTOS", jPanel4);
        jTabbedPane1.addTab("tab6", jTabbedPane2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 556, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
    String usuario = txtUsuarioEmpleado.getText().trim();
    String contrasena =
        new String(pswContrasenaEmpleado.getPassword()).trim();

    if (usuario.isEmpty() || contrasena.isEmpty()) {
    javax.swing.JOptionPane.showMessageDialog(
            this,
            "INGRESA EL USUARIO Y LA CONTRASEÑA."
    );
    return;
    }

    if (!validarEmpleado()) {
    return;
    }
    

    String nroDoc = txtNroDocEmpleado.getText().trim();

    // Verificar que no exista otro empleado con el mismo documento
    if (buscarIndiceEmpleado(nroDoc) != -1) {

        javax.swing.JOptionPane.showMessageDialog(
                this,
                "YA EXISTE UN EMPLEADO CON ESE DOCUMENTO."
        );
        return;
    }

    String nombres = txtNombres.getText().trim();
    String apellidos = txtApellidos.getText().trim();
    String tipoDoc = cboTipoDocEmpleado.getSelectedItem().toString();
    String telefono = txtTelefono.getText().trim();
    String direccion = txtDireccion.getText().trim();
    String cargo = cboCargoEmpleado.getSelectedItem().toString();
    String sueldo = txtSueldo.getText().trim();
    String fechaInicio = txtFechaInicio.getText().trim();
    String fechaTermino = txtFechaTermino.getText().trim();
    

    // Guardar todos los datos del empleado
    String[] empleado = {
        nombres,
        apellidos,
        tipoDoc,
        nroDoc,
        telefono,
        direccion,
        cargo,
        sueldo,
        fechaInicio,
        fechaTermino,
        usuario,
        contrasena
    };

    listaEmpleadosCompleta.add(empleado);

    // Guardar los datos necesarios para el Login
    listaRecepcionistas.add(new String[]{
        nroDoc,
        usuario,
        contrasena
    });

    cargarEmpleados();
    limpiarEmpleado();

    javax.swing.JOptionPane.showMessageDialog(
            this,
            "EMPLEADO REGISTRADO CORRECTAMENTE."
    );   
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
      String nroDoc = txtNroDocEmpleado.getText().trim();

    if (nroDoc.isEmpty()) {

        javax.swing.JOptionPane.showMessageDialog(
                this,
                "INGRESA EL NÚMERO DE DOCUMENTO PARA BUSCAR."
        );
        return;
    }

    int indice = buscarIndiceEmpleado(nroDoc);

    if (indice == -1) {

        javax.swing.JOptionPane.showMessageDialog(
                this,
                "NO SE ENCONTRÓ EL EMPLEADO."
        );
        return;
    }

    seleccionandoPorBusqueda = true;

    tablaEmpleados.setRowSelectionInterval(indice, indice);

    seleccionandoPorBusqueda = false;

    javax.swing.JOptionPane.showMessageDialog(
            this,
            "EMPLEADO ENCONTRADO."
    );
    
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
       int fila = tablaEmpleados.getSelectedRow();

    if (fila == -1) {

        JOptionPane.showMessageDialog(
                this,
                "SELECCIONA UN EMPLEADO DE LA TABLA PARA EDITAR."
        );
        return;
    }

    // PRIMER CLIC EN EDITAR:
    // Solo carga los datos en los campos
    if (!modoEdicionEmpleado) {

        cargarEmpleadoSeleccionado();

        modoEdicionEmpleado = true;

        return;
    }

    // SEGUNDO CLIC EN EDITAR:
    // Ahora sí valida y guarda los cambios

    if (!validarEmpleado()) {
        return;
    }

    String nuevoNroDoc = txtNroDocEmpleado.getText().trim();

    // Buscar si el documento pertenece a otro empleado
    for (int i = 0; i < listaEmpleadosCompleta.size(); i++) {

        if (i != fila
                && listaEmpleadosCompleta.get(i)[3]
                .equalsIgnoreCase(nuevoNroDoc)) {

            JOptionPane.showMessageDialog(
                    this,
                    "OTRO EMPLEADO YA TIENE ESE DOCUMENTO."
            );
            return;
        }
    }

    String[] empleado = listaEmpleadosCompleta.get(fila);

    String documentoAnterior = empleado[3];

    empleado[0] = txtNombres.getText().trim();
    empleado[1] = txtApellidos.getText().trim();
    empleado[2] = cboTipoDocEmpleado.getSelectedItem().toString();
    empleado[3] = nuevoNroDoc;
    empleado[4] = txtTelefono.getText().trim();
    empleado[5] = txtDireccion.getText().trim();
    empleado[6] = cboCargoEmpleado.getSelectedItem().toString();
    empleado[7] = txtSueldo.getText().trim();
    empleado[8] = txtFechaInicio.getText().trim();
    empleado[9] = txtFechaTermino.getText().trim();

    // Actualizar también los datos usados por el Login
    for (int i = 0; i < listaRecepcionistas.size(); i++) {

        if (listaRecepcionistas.get(i)[0]
                .equalsIgnoreCase(documentoAnterior)) {

            listaRecepcionistas.get(i)[0] = nuevoNroDoc;
            listaRecepcionistas.get(i)[1] = empleado[10];
            listaRecepcionistas.get(i)[2] = empleado[11];

            break;
        }
    }

    cargarEmpleados();
    limpiarEmpleado();

    modoEdicionEmpleado = false;

    JOptionPane.showMessageDialog(
            this,
            "EMPLEADO MODIFICADO CORRECTAMENTE."
    );
  
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
    int fila = tablaEmpleados.getSelectedRow();

    if (fila == -1) {

        javax.swing.JOptionPane.showMessageDialog(
                this,
                "SELECCIONA UN EMPLEADO DE LA TABLA PARA ELIMINAR."
        );
        return;
    }

    int confirmar = javax.swing.JOptionPane.showConfirmDialog(
            this,
            "¿ESTÁS SEGURO DE ELIMINAR ESTE EMPLEADO?",
            "CONFIRMAR ELIMINACIÓN",
            javax.swing.JOptionPane.YES_NO_OPTION
    );

    if (confirmar != javax.swing.JOptionPane.YES_OPTION) {
        return;
    }

    String documento = listaEmpleadosCompleta.get(fila)[3];

    // Eliminar de la lista de empleados
    listaEmpleadosCompleta.remove(fila);

    // Eliminar también sus credenciales del Login
    for (int i = listaRecepcionistas.size() - 1; i >= 0; i--) {

        if (listaRecepcionistas.get(i)[0]
                .equalsIgnoreCase(documento)) {

            listaRecepcionistas.remove(i);
        }
    }

    cargarEmpleados();
    limpiarEmpleado();

    javax.swing.JOptionPane.showMessageDialog(
            this,
            "EMPLEADO ELIMINADO CORRECTAMENTE."
    );   
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnSalirEmpleadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirEmpleadosActionPerformed
      int confirmar = javax.swing.JOptionPane.showConfirmDialog(
            this,
            "¿DESEAS CERRAR SESIÓN?",
            "CERRAR SESIÓN",
            javax.swing.JOptionPane.YES_NO_OPTION
    );

    if (confirmar == javax.swing.JOptionPane.YES_OPTION) {

        // Abrir nuevamente el Login
        new FormLogin().setVisible(true);
        this.dispose();
    }    
   
    }//GEN-LAST:event_btnSalirEmpleadosActionPerformed

    private void cboMetodoPagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboMetodoPagoActionPerformed
        
    }//GEN-LAST:event_cboMetodoPagoActionPerformed

    private void btnGuardarInquilinoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarInquilinoActionPerformed
        if (!validarInquilino()) {
        return;
        }

        String nroDoc = txtNroDocInquilino.getText().trim();

        if (buscarIndiceInquilino(nroDoc) != -1) {

        javax.swing.JOptionPane.showMessageDialog(
            this,
            "YA EXISTE UN INQUILINO CON ESE DOCUMENTO." );
            return;
        }

    String[] inquilino = {
    txtNombresInquilino.getText().trim(),
    txtApellidosInquilino.getText().trim(),
    cboTipoDocInquilino.getSelectedItem().toString(),
    nroDoc,
    txtTelefonoInquilino.getText().trim()
    };

      listaInquilinos.add(inquilino);

      cargarInquilinos();
      cargarDniInquilinos();
      limpiarInquilino();

      javax.swing.JOptionPane.showMessageDialog(
        this,
        "INQUILINO REGISTRADO CORRECTAMENTE." );
    }//GEN-LAST:event_btnGuardarInquilinoActionPerformed

    private void btnBuscarInquilinoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarInquilinoActionPerformed
    String nroDoc =
            txtNroDocInquilino.getText().trim();

    if (nroDoc.isEmpty()) {

        javax.swing.JOptionPane.showMessageDialog(
                this,
                "INGRESA EL NÚMERO DE DOCUMENTO PARA BUSCAR."
        );
        return;
    }

    int indice =
            buscarIndiceInquilino(nroDoc);

    if (indice == -1) {

        javax.swing.JOptionPane.showMessageDialog(
                this,
                "NO SE ENCONTRÓ EL INQUILINO."
        );
        return;
    }

    buscandoInquilino = true;

    tblInquilinos.setRowSelectionInterval(
            indice,
            indice
    );

    buscandoInquilino = false;

    javax.swing.JOptionPane.showMessageDialog(
            this,
            "INQUILINO ENCONTRADO."
    );
    }//GEN-LAST:event_btnBuscarInquilinoActionPerformed

    private void btnEditarInquilinoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarInquilinoActionPerformed
        int fila = tblInquilinos.getSelectedRow();

    if (fila == -1) {

        javax.swing.JOptionPane.showMessageDialog(
                this,
                "SELECCIONA UN INQUILINO DE LA TABLA PARA EDITAR."
        );
        return;
    }

    // PRIMER CLIC EN EDITAR:
    // Solo carga los datos de la fila en los campos
    if (!modoEdicionInquilino) {

        cargarInquilinoSeleccionado();

        modoEdicionInquilino = true;

        return;
    }

    // SEGUNDO CLIC EN EDITAR:
    // Ahora sí modifica los datos

    if (!validarInquilino()) {
        return;
    }

    String nuevoNroDoc =
            txtNroDocInquilino.getText().trim();

    // Verificar que el documento no pertenezca
    // a otro inquilino
    for (int i = 0; i < listaInquilinos.size(); i++) {

        if (i != fila
                && listaInquilinos.get(i)[3]
                .equalsIgnoreCase(nuevoNroDoc)) {

            javax.swing.JOptionPane.showMessageDialog(
                    this,
                    "OTRO INQUILINO YA TIENE ESE DOCUMENTO."
            );
            return;
        }
    }

    String[] inquilino =
            listaInquilinos.get(fila);

    inquilino[0] =
            txtNombresInquilino.getText().trim();

    inquilino[1] =
            txtApellidosInquilino.getText().trim();

    inquilino[2] =
            cboTipoDocInquilino.getSelectedItem().toString();

    inquilino[3] = nuevoNroDoc;

    inquilino[4] =
            txtTelefonoInquilino.getText().trim();

    cargarInquilinos();
    limpiarInquilino();

    modoEdicionInquilino = false;

    javax.swing.JOptionPane.showMessageDialog(
            this,
            "INQUILINO MODIFICADO CORRECTAMENTE."
    );
    }//GEN-LAST:event_btnEditarInquilinoActionPerformed

    private void btnEliminarInquilinoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarInquilinoActionPerformed
        int fila = tblInquilinos.getSelectedRow();

    if (fila == -1) {

    javax.swing.JOptionPane.showMessageDialog(
            this,
            "SELECCIONA UN INQUILINO DE LA TABLA PARA ELIMINAR."
    );
    return;
    }

    int confirmar = javax.swing.JOptionPane.showConfirmDialog(
        this,
        "¿ESTÁS SEGURO DE ELIMINAR ESTE INQUILINO?",
        "CONFIRMAR ELIMINACIÓN",
        javax.swing.JOptionPane.YES_NO_OPTION
    );

    if (confirmar != javax.swing.JOptionPane.YES_OPTION) {
    return;
    }

    listaInquilinos.remove(fila);

    cargarInquilinos();
    limpiarInquilino();

    javax.swing.JOptionPane.showMessageDialog(
        this,
        "INQUILINO ELIMINADO CORRECTAMENTE."
    );
    }//GEN-LAST:event_btnEliminarInquilinoActionPerformed

    private void btnSalirInquilinosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirInquilinosActionPerformed
       int confirmar = javax.swing.JOptionPane.showConfirmDialog(
        this,
        "¿DESEAS CERRAR SESIÓN?",
        "CERRAR SESIÓN",
        javax.swing.JOptionPane.YES_NO_OPTION
    );

    if (confirmar == javax.swing.JOptionPane.YES_OPTION) {

    new FormLogin().setVisible(true);
    this.dispose();
    }
    }//GEN-LAST:event_btnSalirInquilinosActionPerformed

    private void cboInquilinoAlquilerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboInquilinoAlquilerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboInquilinoAlquilerActionPerformed

    private void btnGenerarAlquilerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarAlquilerActionPerformed
        
    
try {
    // Obtenemos los datos ingresados
    String fechaInicio = txtFechaInicioAlquiler.getText().trim();
    String fechaFin = txtFechaFinAlquiler.getText().trim();
    String mesesTexto = txtMesesAlquiler.getText().trim();
    String montoTexto = txtMontoAlquiler.getText().trim();

    // Verificamos que los campos estén completos
    if (fechaInicio.isEmpty() || fechaFin.isEmpty()
            || mesesTexto.isEmpty() || montoTexto.isEmpty()) {
        throw new Exception("Completa todos los campos del alquiler.");
    }

    // Verificamos las selecciones
    if (cboInquilinoAlquiler.getSelectedItem() == null
            || cboDeptoAlquiler.getSelectedItem() == null) {
        throw new Exception("Selecciona un inquilino y un departamento.");
    }

    // Convertimos los datos numéricos
    int meses = Integer.parseInt(mesesTexto);
    double monto = Double.parseDouble(montoTexto);

    // Validamos los valores
    if (meses <= 0 || monto <= 0) {
        throw new Exception(
                "Los meses y el monto deben ser mayores que cero.");
    }

    // Obtenemos el inquilino y el departamento seleccionados
    String inquilino = cboInquilinoAlquiler
            .getSelectedItem().toString();

    String departamento = cboDeptoAlquiler
            .getSelectedItem().toString();

    // Obtenemos el modelo de la tabla
    javax.swing.table.DefaultTableModel modelo =
            (javax.swing.table.DefaultTableModel)
                    tablaAlquileres.getModel();

    // Registramos el alquiler en la tabla
  modelo.insertRow(0, new Object[]{
    inquilino,
    departamento,
    fechaInicio,
    fechaFin,
    monto
});

    // Limpiamos los campos del alquiler
    txtFechaInicioAlquiler.setText("");
    txtFechaFinAlquiler.setText("");
    txtMesesAlquiler.setText("");
    txtMontoAlquiler.setText("");

    JOptionPane.showMessageDialog(
            this,
            "Alquiler registrado correctamente.");

} catch (NumberFormatException e) {
    JOptionPane.showMessageDialog(
            this,
            "Ingresa números válidos en meses y monto.");
} catch (Exception e) {
    JOptionPane.showMessageDialog(this, e.getMessage());
}

    }//GEN-LAST:event_btnGenerarAlquilerActionPerformed

    private void btnRegistrarDptoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarDptoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnRegistrarDptoActionPerformed

    public static void main(String args[]) {
     
        java.awt.EventQueue.invokeLater(() -> new FormPrincipal().setVisible(true));
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnBuscarDpto;
    private javax.swing.JButton btnBuscarInquilino;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEditarAlquiler;
    private javax.swing.JButton btnEditarInquilino;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnEliminarAlquiler;
    private javax.swing.JButton btnEliminarDpto;
    private javax.swing.JButton btnEliminarInquilino;
    private javax.swing.JButton btnGenerarAlquiler;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnGuardarInquilino;
    private javax.swing.JButton btnModificarDpto;
    private javax.swing.JButton btnRegistrarDpto;
    private javax.swing.JButton btnReporteAlquileres;
    private javax.swing.JButton btnReporteDepartamentos;
    private javax.swing.JButton btnReporteEmpleados;
    private javax.swing.JButton btnReporteInquilinos;
    private javax.swing.JButton btnReportePagos;
    private javax.swing.JButton btnSalirAlquiler;
    private javax.swing.JButton btnSalirDepartamentos;
    private javax.swing.JButton btnSalirEmpleados;
    private javax.swing.JButton btnSalirInquilinos;
    private javax.swing.JComboBox<String> cboCargoEmpleado;
    private javax.swing.JComboBox<String> cboDeptoAlquiler;
    private javax.swing.JComboBox<String> cboEstado;
    private javax.swing.JComboBox<String> cboInquilinoAlquiler;
    private javax.swing.JComboBox<String> cboMetodoPago;
    private javax.swing.JComboBox<String> cboTipoDocEmpleado;
    private javax.swing.JComboBox<String> cboTipoDocInquilino;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JPasswordField pswContrasenaEmpleado;
    private javax.swing.JTable tablaAlquileres;
    private javax.swing.JTable tablaEmpleados;
    private javax.swing.JTable tblDepartamentos;
    private javax.swing.JTable tblInquilinos;
    private javax.swing.JTable tblReportes;
    private javax.swing.JTextField txtApellidos;
    private javax.swing.JTextField txtApellidosInquilino;
    private javax.swing.JTextField txtDescripcion;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtFechaFinAlquiler;
    private javax.swing.JTextField txtFechaInicio;
    private javax.swing.JTextField txtFechaInicioAlquiler;
    private javax.swing.JTextField txtFechaPago;
    private javax.swing.JTextField txtFechaTermino;
    private javax.swing.JTextField txtMesesAlquiler;
    private javax.swing.JTextField txtMontoAlquiler;
    private javax.swing.JTextField txtNombres;
    private javax.swing.JTextField txtNombresInquilino;
    private javax.swing.JTextField txtNroBanos;
    private javax.swing.JTextField txtNroDocEmpleado;
    private javax.swing.JTextField txtNroDocInquilino;
    private javax.swing.JTextField txtNroDpto;
    private javax.swing.JTextField txtNroHabitaciones;
    private javax.swing.JTextField txtPiso;
    private javax.swing.JTextField txtPrecioMensual;
    private javax.swing.JTextField txtSueldo;
    private javax.swing.JTextField txtTelefono;
    private javax.swing.JTextField txtTelefonoInquilino;
    private javax.swing.JTextField txtUsuarioEmpleado;
    // End of variables declaration//GEN-END:variables

    


}

