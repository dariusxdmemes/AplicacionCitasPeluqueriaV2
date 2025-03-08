import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UI extends JFrame {
    // Ancho y alto del frame
    private  final int WIDTH = 600, HEIGHT = 450;

    // Ancho y alto de los botones
    private final int BUTTON_WIDTH = 100, BUTTON_HEIGHT = 30, LRG_BUTTON_WIDTH = 150;

    // Ancho y alto de los textos (JLabels)
    private final int LABEL_TEXT_WIDTH = 100, LABEL_TEXT_HEIGHT = 100;

    // Componentes para crear las diferentes ventanas
    private JPanel panelContenedor;
    private CardLayout cardLayout;

    // Diferentes paneles de navegacion
    private JPanel panelInicio;
    private JPanel panelLogin;
    private JPanel panelRegistro;
    private JPanel panelListarServicios;
    private JPanel panelMenuPrincipal;

    UI() {
        // Configuracion de la ventana
        ventanaCfg("Mimahair", WIDTH, HEIGHT, false, 200, 200);

        cardLayout = new CardLayout();
        panelContenedor = new JPanel(cardLayout);

        // Creacion de los diferentes paneles
        crearPanelInicio();
        crearPanelLogin();
        crearPanelRegistro();
        //crearPanelListarServ();
        //crearPanelMenuOpciones();

        panelContenedor.add(panelInicio, "inicio");
        panelContenedor.add(panelLogin, "login");
        panelContenedor.add(panelRegistro, "registro");

        this.getContentPane().add(panelContenedor);

        cardLayout.show(panelContenedor, "inicio");

        // Validar y actualizar los componentes
        this.validate();
        this.repaint();

    }

    // Metodos de configuracion de componentes
    private void ventanaCfg(String tituloVentana,  int ancho, int alto, boolean redimensionable, int posX, int posY) {
        setTitle(tituloVentana);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(ancho, alto);
        setResizable(redimensionable);
        setLocation(posX, posY);
    }

    private JPanel panelCfg(LayoutManager layout, Color color) {
        JPanel panel = new JPanel();
        panel.setBackground(color);

        return panel;
    }

    private JButton crearBoton(String textoBoton, int posX, int posY, int ancho, int alto) {
        JButton boton = new JButton();
        boton.setText(textoBoton);
        boton.setBounds(posX, posY, ancho, alto);

        return boton;
    }

    private JLabel crearLabelCustom(String textoEtiqueta, int posX, int posY, int ancho, int alto) {
        JLabel labelCustom = new JLabel();
        labelCustom.setText(textoEtiqueta);
        labelCustom.setBounds(posX, posY, ancho, alto);
        labelCustom.setFont(new Font("Montserrat", Font.BOLD, 15));

        return labelCustom;
    }

    // Botones para volver atras
    private void botonVolverAtrasLogin() {
        // Crear boton para volver atras
        JButton botonAtras = crearBoton("Atras", WIDTH/2 + 40, 220, BUTTON_WIDTH, BUTTON_HEIGHT);
        botonAtras.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panelContenedor, "inicio");
            }
        });
        panelLogin.add(botonAtras);
    }

    private void botonVolverAtrasRegistro() {
        // Crear boton para volver atras
        JButton botonAtras = crearBoton("Atras", 300, 350, BUTTON_WIDTH, BUTTON_HEIGHT);
        botonAtras.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panelContenedor, "inicio");
            }
        });
        panelRegistro.add(botonAtras);
    }

    private void crearPanelInicio() {
        panelInicio = panelCfg(null, Color.PINK);
        panelInicio.setLayout(null);

        // Configuracion de esta ventana
        JLabel textoBienvenida = new JLabel("Bienvenido a Mimahair!");
        textoBienvenida.setBounds(WIDTH/2 - 100, 20, 200, 30);
        textoBienvenida.setHorizontalAlignment(SwingConstants.CENTER);
        panelInicio.add(textoBienvenida);

        // Creacion de boton login
        JButton botonLogin = crearBoton("Identificate", WIDTH/2 - BUTTON_WIDTH/2, 80, BUTTON_WIDTH, BUTTON_HEIGHT);
                botonLogin.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        cardLayout.show(panelContenedor, "login");
                    }
                });
        panelInicio.add(botonLogin);

        // Creacion de boton Registro
        JButton botonRegistro = crearBoton("Registrate", WIDTH/2 - BUTTON_WIDTH/2, 130, BUTTON_WIDTH, BUTTON_HEIGHT);
                botonRegistro.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        cardLayout.show(panelContenedor, "registro");
                    }
                });
                panelInicio.add(botonRegistro);

        // Creacion de boton para listar servicios
        JButton botonServicios = crearBoton("Servicios", WIDTH/2 - BUTTON_WIDTH/2, 180, BUTTON_WIDTH, BUTTON_HEIGHT);
                botonServicios.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        cardLayout.show(panelContenedor, "listarServicios");
                    }
                });
        panelInicio.add(botonServicios);
    }

    private void crearPanelLogin() {
        panelLogin = panelCfg(null, new Color(230, 230, 250));
        panelLogin.setLayout(null);

        JLabel textoLogin = new JLabel("Identificacion de usuario");
        textoLogin.setBounds(WIDTH/2 - 100, 20, 200, 30);
        textoLogin.setHorizontalAlignment(SwingConstants.CENTER);
        panelLogin.add(textoLogin);

        // Campos de email y contrasena
        JLabel textoEmail = new JLabel("Email: ");
        textoEmail.setBounds(150, 80, 80, 25);
        panelLogin.add(textoEmail);

        JTextField campoEmail = new JTextField();
        campoEmail.setBounds(240, 80, 200, 25);
        panelLogin.add(campoEmail);

        JLabel textoContrasena = new JLabel("Contrasena: ");
        textoContrasena.setBounds(150, 120, 80, 25);
        panelLogin.add(textoContrasena);

        JPasswordField campoContrasena = new JPasswordField();
        campoContrasena.setBounds(240, 120, 200, 25);
        panelLogin.add(campoContrasena);

        // Boton enter-contiunar
        JButton botonIniciarSesion = crearBoton("Iniciar sesion", WIDTH/2 + 20, 180, LRG_BUTTON_WIDTH, BUTTON_HEIGHT);
        botonIniciarSesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = campoEmail.getText();
                String password = new String(campoContrasena.getPassword());

                if (GestionBaseDatos.identificacionUsuario(email, password)) {
                    JOptionPane.showMessageDialog(null,
                            "Bienvenido "+campoEmail.getText(),
                                    "Identificacion correcta",
                                    JOptionPane.INFORMATION_MESSAGE);

                    campoEmail.setText("");
                    campoContrasena.setText("");

                    // Implementar la llamada al siguiente panel (menu principal).
                } else {
                    JOptionPane.showMessageDialog(null,
                            "Error de identificacion",
                                "Error",
                                     JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        panelLogin.add(botonIniciarSesion);

        botonVolverAtrasLogin();
    }

    private void crearPanelRegistro() {
        final int WIDTH_TEXT = 80;
        final int HEIGHT_TEXT = 25;
        final int WIDTH_TEXTFIELD = 200;
        final int HEIGHT_TEXTFIELD = 25;

        panelRegistro = panelCfg(null, new Color(230, 230, 250));
        panelRegistro.setLayout(null);

        JLabel textoRegistro = new JLabel("Registro de usuario");
        textoRegistro.setBounds(WIDTH/2 - 100, 20, 200, 30);
        textoRegistro.setHorizontalAlignment(SwingConstants.CENTER);
        panelRegistro.add(textoRegistro);

        // Campos de email y contrasena
        JLabel textoEmail = new JLabel("Email: ");
        textoEmail.setBounds(150, 80, WIDTH_TEXT, HEIGHT_TEXT);
        panelRegistro.add(textoEmail);

        JTextField campoEmail = new JTextField();
        campoEmail.setBounds(240, 80, WIDTH_TEXTFIELD, HEIGHT_TEXTFIELD);
        panelRegistro.add(campoEmail);

        JLabel textoContrasena = new JLabel("Contrasena: ");
        textoContrasena.setBounds(150, 120, WIDTH_TEXT, HEIGHT_TEXT);
        panelRegistro.add(textoContrasena);

        JPasswordField campoContrasena = new JPasswordField();
        campoContrasena.setBounds(240, 120, WIDTH_TEXTFIELD, HEIGHT_TEXTFIELD);
        panelRegistro.add(campoContrasena);

        JLabel textoNombre = new JLabel("Nombre: ");
        textoNombre.setBounds(150, 160, WIDTH_TEXT, HEIGHT_TEXT);
        panelRegistro.add(textoNombre);

        JTextField campoNombre = new JTextField();
        campoNombre.setBounds(240, 160, WIDTH_TEXTFIELD, HEIGHT_TEXTFIELD);
        panelRegistro.add(campoNombre);

        JLabel textoApellidos = new JLabel("Apellidos: ");
        textoApellidos.setBounds(150, 200, WIDTH_TEXT, HEIGHT_TEXT);
        panelRegistro.add(textoApellidos);

        JTextField campoApellidos = new JTextField();
        campoApellidos.setBounds(240, 200, WIDTH_TEXTFIELD, HEIGHT_TEXTFIELD);
        panelRegistro.add(campoApellidos);

        JLabel textoTlf = new JLabel("Telefono: ");
        textoTlf.setBounds(150, 240, WIDTH_TEXT, HEIGHT_TEXT);
        panelRegistro.add(textoTlf);

        JTextField campoTlf = new JTextField();
        campoTlf.setBounds(240, 240, WIDTH_TEXTFIELD, HEIGHT_TEXTFIELD);
        panelRegistro.add(campoTlf);

        // Boton para registrarse
        JButton botonRegistrarse = crearBoton("Registrarse", 300, 300, LRG_BUTTON_WIDTH, BUTTON_HEIGHT);
        botonRegistrarse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = campoEmail.getText();
                String contrasena = new String(campoContrasena.getPassword());
                String nombre = campoNombre.getText();
                String apellidos = campoApellidos.getText();
                String tlf = campoTlf.getText();

                try {
                    GestionBaseDatos.registrarUsuario(email, contrasena, nombre, apellidos, tlf);

                    JOptionPane.showMessageDialog(null,
                            "Registro completado!",
                            "Usuario registrado",
                            JOptionPane.INFORMATION_MESSAGE);

                    // Esto reinicia los datos de los campos
                    campoEmail.setText("");
                    campoContrasena.setText("");
                    campoNombre.setText("");
                    campoApellidos.setText("");
                    campoTlf.setText("");

                    cardLayout.show(panelContenedor, "inicio");
                } catch (Exception exc) {
                    JOptionPane.showMessageDialog(null,
                            "Error al registrar usuario, intentalo mas tarde",
                            "Error de registro",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        panelRegistro.add(botonRegistrarse);
        botonVolverAtrasRegistro();
    }

}
