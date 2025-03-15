import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

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
    private JPanel panelMenuOpciones;
    private JPanel panelModificaciones;
    private JPanel panelModEmail;
    private JPanel panelModContrasena;
//    private JPanel panelModTlf;
//    private JPanel panelSolCita;
//    private JPanel panelConsulServ;

    private String nombreSaludo = null;
    private JLabel textoMenuPrincipal;

    UI() {
        // Configuracion de la ventana
        ventanaCfg("Mimahair", WIDTH, HEIGHT, false, 200, 200);

        cardLayout = new CardLayout();
        panelContenedor = new JPanel(cardLayout);

        // Creacion de los diferentes paneles
        crearPanelInicio();
        crearPanelLogin();
        crearPanelRegistro();
        crearPanelListarServ();
        crearPanelMenuOpciones();
        crearPanelModificaciones();
        crearPanelModEmail();
        crearPanelModContrasena();

        panelContenedor.add(panelInicio, "inicio");
        panelContenedor.add(panelLogin, "login");
        panelContenedor.add(panelRegistro, "registro");
        panelContenedor.add(panelListarServicios, "servicios");
        panelContenedor.add(panelMenuOpciones, "opciones");
        panelContenedor.add(panelModificaciones, "modificar");
        panelContenedor.add(panelModEmail, "email");
        panelContenedor.add(panelModContrasena, "contrasena");
//        panelContenedor.add(panelModTlf, "tlf");
//        panelContenedor.add(panelSolCita, "cita");
//        panelContenedor.add(panelConsulServ, "servicios");

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

    // Botones para volver atras
    private void botonVolverAtrasLogin() {
        // Crear boton para volver atras
        JButton botonAtras = crearBoton("Atrás", WIDTH/2 + 40, 220, BUTTON_WIDTH, BUTTON_HEIGHT);
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
        JButton botonAtras = crearBoton("Atrás", 300, 350, BUTTON_WIDTH, BUTTON_HEIGHT);
        botonAtras.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panelContenedor, "inicio");
            }
        });
        panelRegistro.add(botonAtras);
    }
    private void botonVolverAtrasServicios() {
        JButton botonAtras = crearBoton("Atrás", 300, 350, BUTTON_WIDTH, BUTTON_HEIGHT);
        botonAtras.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panelContenedor, "inicio");
            }
        });
        panelListarServicios.add(botonAtras);
    }

    private void botonVolverAtrasOpciones() {
        JButton botonAtras = crearBoton("Atrás", 300, 350, BUTTON_WIDTH, BUTTON_HEIGHT);
        botonAtras.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int opcion = JOptionPane.showConfirmDialog(
                        panelMenuOpciones,
                        "Vas a cerrar sesión, ¿estás seguro?",
                        "Confirmacion",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE
                );

                if (opcion == JOptionPane.YES_OPTION) {
                    cardLayout.show(panelContenedor, "inicio");
                }
            }
        });
        panelMenuOpciones.add(botonAtras);
    }

    private void botonVolverAtrasModificar() {
        JButton botonAtras = crearBoton("Atrás", 300, 350, BUTTON_WIDTH, BUTTON_HEIGHT);
        botonAtras.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panelContenedor, "opciones");
            }
        });
        panelModificaciones.add(botonAtras);
    }


    private JDialog crearDialogo(Component parent, String titulo, int width, int height) {
        JDialog dialogo = new JDialog();
        dialogo.setTitle(titulo);
        dialogo.setSize(width, height);
        dialogo.setModal(true);

        JPanel panelContenido = new JPanel();
        panelContenido.setLayout(new GridLayout(0, 2, 10, 10)); // Filas automáticas, 2 columnas
        dialogo.add(panelContenido);

        dialogo.setResizable(false);
        dialogo.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        return dialogo;
    }

    private void crearPanelInicio() {
        Font fuente = new Font("Montserrat", Font.BOLD, 15);
        panelInicio = panelCfg(null, Color.PINK);
        panelInicio.setLayout(null);

        // Configuracion de esta ventana
        JLabel textoBienvenida = new JLabel("Bienvenido a Mimahair!");
        textoBienvenida.setFont(fuente);
        textoBienvenida.setBounds(WIDTH/2 - 100, 20, 175, 30);
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
        JButton botonRegistro = crearBoton("Regístrate", WIDTH/2 - BUTTON_WIDTH/2, 130, BUTTON_WIDTH, BUTTON_HEIGHT);
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
                        cardLayout.show(panelContenedor, "servicios");
                    }
                });
        panelInicio.add(botonServicios);
    }

    private void crearPanelLogin() {
        panelLogin = panelCfg(null, new Color(150, 110, 233));
        panelLogin.setLayout(null);

        JLabel textoLogin = new JLabel("Identificacion de usuario");
        textoLogin.setBounds(WIDTH/2 - 100, 20, 200, 30);
        textoLogin.setHorizontalAlignment(SwingConstants.CENTER);
        panelLogin.add(textoLogin);

        // Campos de email y contraseña
        JLabel textoEmail = new JLabel("Email: ");
        textoEmail.setBounds(150, 80, 80, 25);
        panelLogin.add(textoEmail);

        JTextField campoEmail = new JTextField();
        campoEmail.setBounds(240, 80, 200, 25);
        panelLogin.add(campoEmail);

        JLabel textoContrasena = new JLabel("Contraseña: ");
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

                    nombreSaludo = GestionBaseDatos.seleccionarNombreUsuario();
                    actualizarSaludo();

                    campoEmail.setText("");
                    campoContrasena.setText("");

                    cardLayout.show(panelContenedor, "opciones");
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

    public void crearPanelListarServ() {
        final int WIDTH_TEXT = 300;
        final int HEIGHT_TEXT = 25;

        final int posX = 150;
        int posY = 80;

        panelListarServicios = panelCfg(null, new Color(150, 120, 250));
        panelListarServicios.setLayout(null);

        ArrayList<String[]> serviciosAlmacenados = new ArrayList<>();

        JLabel textoServicios = new JLabel("Listado de servicios");
        textoServicios.setBounds(WIDTH/2 - 50, 20, 200, 30);
        panelListarServicios.add(textoServicios);

        serviciosAlmacenados.addAll(GestionBaseDatos.listarServicios());

        JLabel servicio1 = new JLabel(Arrays.toString(serviciosAlmacenados.get(0)));
        servicio1.setBounds(posX, posY, WIDTH_TEXT, HEIGHT_TEXT);
        panelListarServicios.add(servicio1);

        JLabel servicio2 = new JLabel(Arrays.toString(serviciosAlmacenados.get(1)));
        servicio2.setBounds(posX, posY+40, WIDTH_TEXT, HEIGHT_TEXT);
        panelListarServicios.add(servicio2);

        JLabel servicio3 = new JLabel(Arrays.toString(serviciosAlmacenados.get(2)));
        servicio3.setBounds(posX, posY+80, WIDTH_TEXT, HEIGHT_TEXT);
        panelListarServicios.add(servicio3);

        JLabel servicio4 = new JLabel(Arrays.toString(serviciosAlmacenados.get(3)));
        servicio4.setBounds(posX, posY+120, WIDTH_TEXT, HEIGHT_TEXT);
        panelListarServicios.add(servicio4);


        botonVolverAtrasServicios();
    }

    private void actualizarSaludo() {
        if (textoMenuPrincipal != null && nombreSaludo != null) {
            Calendar horaActual = Calendar.getInstance();
            int hora = horaActual.get(Calendar.HOUR_OF_DAY);
            String saludo;

            if (hora >= 6 && hora <= 12) {
                saludo="Buenos dias ";
            } else if (hora >= 12 && hora <= 22) {
                saludo="Buenas tardes ";
            } else {
                saludo="¿Trasnochando/Madrugando? ";
            }

            textoMenuPrincipal.setText(saludo + nombreSaludo+"!");
        }
    }

    public void crearPanelMenuOpciones() {
        Icon iconPathCuenta = new ImageIcon("assets/icono_gestion_cuenta.png");
        Icon iconPathCita = new ImageIcon("assets/icono_sol_cita.png");
        Icon iconListServ = new ImageIcon("assets/icono_list_servicios.png");
        Font fuente = new Font("Montserrat", Font.BOLD, 20);

        panelMenuOpciones = panelCfg(null, new Color(181, 97, 223));
        panelMenuOpciones.setLayout(null);

        textoMenuPrincipal = new JLabel("Bienvenido");
        textoMenuPrincipal.setBounds(WIDTH/2 - 100, 20, 250, 30);
        textoMenuPrincipal.setHorizontalAlignment(SwingConstants.CENTER);
        textoMenuPrincipal.setFont(fuente);
        panelMenuOpciones.add(textoMenuPrincipal);

        JButton botonGestionCuenta = new JButton();
        botonGestionCuenta.setIcon(iconPathCuenta);
        botonGestionCuenta.setBounds(100, 100, 48, 48);
        botonGestionCuenta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panelContenedor, "modificar");
            }
        });
        panelMenuOpciones.add(botonGestionCuenta);

        JLabel textoGestionCuenta = new JLabel("Gestion cuenta");
        textoGestionCuenta.setBounds(170, 110, 100, 30);
        panelMenuOpciones.add(textoGestionCuenta);

        JButton botonSolicitudCita = new JButton();
        botonSolicitudCita.setIcon(iconPathCita);
        botonSolicitudCita.setBounds(100, 160, 48, 48 );
        botonSolicitudCita.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        panelMenuOpciones.add(botonSolicitudCita);

        JLabel textoSolicitudCita = new JLabel("Solicitar cita");
        textoSolicitudCita.setBounds(170, 170, 100, 30);
        panelMenuOpciones.add(textoSolicitudCita);

        JButton botonListaServicios = new JButton();
        botonListaServicios.setIcon(iconListServ);
        botonListaServicios.setBounds(100, 220, 48, 48);
        panelMenuOpciones.add(botonListaServicios);

        JLabel textoListaServicios = new JLabel("Servicios disponibles");
        textoListaServicios.setBounds(170, 230, 150, 30);
        panelMenuOpciones.add(textoListaServicios);

        botonVolverAtrasOpciones();
    }

    public void crearPanelModificaciones() {
        Icon iconPathModEmail = new ImageIcon("assets/icono_mod_email.png");
        Icon iconPathModPass = new ImageIcon("assets/icono_mod_pass.png");
        Icon iconPathModTlf = new ImageIcon("assets/icono_mod_tlf.png");
        Icon iconoPathGestCitas = new ImageIcon("assets/icono_gestion_citas.png");

        Font fuente = new Font("Montserrat", Font.BOLD, 20);
        panelModificaciones = panelCfg(null, new Color(120,220,170));
        panelModificaciones.setLayout(null);

        JLabel textoModificaciones = new JLabel("¿Que quieres modificar?");
        textoModificaciones.setBounds(WIDTH/2 - 120, 20, 250, 30);
        textoModificaciones.setHorizontalAlignment(SwingConstants.CENTER);
        textoModificaciones.setFont(fuente);
        panelModificaciones.add(textoModificaciones);

        // boton modificar email
        JButton botonModEmail = new JButton();
        botonModEmail.setIcon(iconPathModEmail);
        botonModEmail.setBounds(100, 100, 48, 48);
        panelModificaciones.add(botonModEmail);

        botonModEmail.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panelContenedor, "email");
            }
        });

        JLabel textoModEmail = new JLabel("Modificar email");
        textoModEmail.setBounds(170, 110, 100, 30);
        panelModificaciones.add(textoModEmail);

        // boton modificar contraseña
        JButton botonModPass = new JButton();
        botonModPass.setIcon(iconPathModPass);
        botonModPass.setBounds(100, 160, 48, 48);
        panelModificaciones.add(botonModPass);

        botonModPass.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog dialogo = crearDialogo(botonModPass, "Modificar contraseña", 200, 170);
                dialogo.setVisible(true);
                // TERMINAR DE IMPLEMENTAR
            }
        });

        JLabel textoModPass = new JLabel("Modificar contraseña");
        textoModPass.setBounds(170, 170, 120, 30);
        panelModificaciones.add(textoModPass);

        // boton modificar telefono
        JButton botonModTlf = new JButton();
        botonModTlf.setIcon(iconPathModTlf);
        botonModTlf.setBounds(100, 220, 48, 48);
        panelModificaciones.add(botonModTlf);

        botonModTlf.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog dialogo = crearDialogo(botonModTlf, "Modificar teléfono", 200, 170);
                dialogo.setVisible(true);
                // TERMINAR DE IMPLEMENTAR
            }
        });

        JLabel textoModTlf = new JLabel("Modificar teléfono");
        textoModTlf.setBounds(170, 230, 150, 30);
        panelModificaciones.add(textoModTlf);

        // boton gestion citas
        JButton botonGestionCitas = new JButton();
        botonGestionCitas.setIcon(iconoPathGestCitas);
        botonGestionCitas.setBounds(310, 160, 48, 48);
        panelModificaciones.add(botonGestionCitas);

        botonGestionCitas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Panel gestion citas
            }
        });

        JLabel textoGestionCitas = new JLabel("Gestion de citas");
        textoGestionCitas.setBounds(370, 170, 150, 30);
        panelModificaciones.add(textoGestionCitas);

        botonVolverAtrasModificar();
    }

    public void crearPanelModEmail() {
        final int WIDTH_TEXT = 150;
        final int HEIGHT_TEXT = 25;
        final int WIDTH_TEXTFIELD = 200;
        final int HEIGHT_TEXTFIELD = 25;

        panelModEmail = panelCfg(null, new Color(100, 200, 200));
        panelModEmail.setLayout(null);

        JLabel textoModEmail = new JLabel("Modifcacion de correo");
        textoModEmail.setBounds(WIDTH/2 - 120, 20, 250, 30);
        textoModEmail.setHorizontalAlignment(SwingConstants.CENTER);
        panelModEmail.add(textoModEmail);

        JLabel textoEmailActual = new JLabel("Introduce tu email actual:");
        textoEmailActual.setBounds(100, 80, WIDTH_TEXT, HEIGHT_TEXT);
        panelModEmail.add(textoEmailActual);

        JTextField campoEmailActual = new JTextField();
        campoEmailActual.setBounds(260, 80, WIDTH_TEXTFIELD, HEIGHT_TEXTFIELD);
        panelModEmail.add(campoEmailActual);

        JLabel textoEmailNuevo = new JLabel("Introduce el nuevo email:");
        textoEmailNuevo.setBounds(100, 120, WIDTH_TEXT, HEIGHT_TEXT);
        panelModEmail.add(textoEmailNuevo);

        JTextField campoEmailNuevo = new JTextField();
        campoEmailNuevo.setBounds(260, 120, WIDTH_TEXTFIELD, HEIGHT_TEXTFIELD);
        panelModEmail.add(campoEmailNuevo);

        JButton botonCambiarEmail = crearBoton("Aceptar", 300, 300, BUTTON_WIDTH, BUTTON_HEIGHT);
        botonCambiarEmail.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String emailActual = campoEmailActual.getText();
                String emailNuevo = campoEmailNuevo.getText();
                try {
                    GestionBaseDatos.modificarEmail(emailActual, emailNuevo);
                    JOptionPane.showMessageDialog(null,
                            "Email modificado satisfactoriamente, por favor, vuelve a identificarte",
                            "Modificacion realizada",
                            JOptionPane.INFORMATION_MESSAGE);

                    // Esto reinicia los datos de los campos
                    campoEmailActual.setText("");
                    campoEmailNuevo.setText("");

                    cardLayout.show(panelContenedor, "login");
                } catch (Exception exc) {
                    JOptionPane.showMessageDialog(null,
                            "Error al modificar email, intentalo mas tarde",
                            "Error de actualizacion de datos",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        panelModEmail.add(botonCambiarEmail);

    }

    public void crearPanelModContrasena() {
        final int WIDTH_TEXT = 150;
        final int HEIGHT_TEXT = 25;
        final int WIDTH_TEXTFIELD = 200;
        final int HEIGHT_TEXTFIELD = 25;

        panelModContrasena = panelCfg(null, new Color(100, 200, 200));
        panelModContrasena.setLayout(null);

        JLabel textoModContrasena = new JLabel("Modifcacion de contrasena");
        textoModContrasena.setBounds(WIDTH/2 - 120, 20, 250, 30);
        textoModContrasena.setHorizontalAlignment(SwingConstants.CENTER);
        panelModContrasena.add(textoModContrasena);

        JLabel textoContrasenaActual = new JLabel("Introduce tu contrasena actual:");
        textoContrasenaActual.setBounds(100, 80, WIDTH_TEXT, HEIGHT_TEXT);
        panelModContrasena.add(textoContrasenaActual);

        JTextField campoContrasenaActual = new JTextField();
        campoContrasenaActual.setBounds(260, 80, WIDTH_TEXTFIELD, HEIGHT_TEXTFIELD);
        panelModContrasena.add(campoContrasenaActual);

        JLabel textoConfirmContrasena = new JLabel("Confirma la contrasena:");
        textoConfirmContrasena.setBounds(100, 120, WIDTH_TEXT, HEIGHT_TEXT);
        panelModContrasena.add(textoConfirmContrasena);

        JTextField campoConfirmContrasena = new JTextField();
        campoConfirmContrasena.setBounds(260, 120, WIDTH_TEXTFIELD, HEIGHT_TEXTFIELD);
        panelModContrasena.add(campoConfirmContrasena);

        JLabel textoContrasenaNueva = new JLabel("Introduce la nueva contrasena:");
        textoContrasenaNueva.setBounds(100, 160, WIDTH_TEXT, HEIGHT_TEXT);
        panelModContrasena.add(textoContrasenaNueva);

        JTextField campoNuevaContrasena = new JTextField();
        campoNuevaContrasena.setBounds(260, 160, WIDTH_TEXTFIELD, HEIGHT_TEXTFIELD);
        panelModContrasena.add(campoNuevaContrasena);

        JButton botonCambiarContrasena = crearBoton("Aceptar", 300, 300, BUTTON_WIDTH, BUTTON_HEIGHT);
        botonCambiarContrasena.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String contrasenaActual = campoContrasenaActual.getText();
                String confirmContrasena = campoConfirmContrasena.getText();
                String contrasenaNueva = campoNuevaContrasena.getText();

                if (contrasenaActual.equals(confirmContrasena)) {
                    JOptionPane.showMessageDialog(null,
                            "Las contrasenas no coinciden!",
                            "Error",
                            JOptionPane.INFORMATION_MESSAGE);
                }

                try {
                    GestionBaseDatos.modificarContrasena(contrasenaActual, contrasenaNueva);
                    JOptionPane.showMessageDialog(null,
                            "Contrasena modificada satisfactoriamente, por favor vuelve a identificarte",
                            "Modificacion realizada",
                            JOptionPane.INFORMATION_MESSAGE);

                    // Esto reinicia los datos de los campos
                    campoContrasenaActual.setText("");
                    campoConfirmContrasena.setText("");

                    cardLayout.show(panelContenedor, "login");
                } catch (Exception exc) {
                    JOptionPane.showMessageDialog(null,
                            "Error al modificar email, intentalo mas tarde",
                            "Error de actualizacion de datos",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        panelModEmail.add(botonCambiarContrasena);

    }

}