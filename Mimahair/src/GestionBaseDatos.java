import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

/* Esta clase maneja la logica de la gestion de la base de datos
* es decir, maneja todas las sentencias que registro, identification,
* updates, deletes... */

public class GestionBaseDatos {

    static Scanner inval = new Scanner(System.in);
    protected static final int ANYO = LocalDate.now().getYear();
    static int idClienteLogged = -1;

    public static boolean identificacionUsuario(String email, String contrasena) {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection conn = null;
        boolean loginCorrecto = false;

        try {
            conn = ConexionDesconexionBD.conexionBaseDatos();

            String sql = "SELECT id_cliente FROM clientes WHERE email = ? AND contrasena = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, email);
            pstmt.setString(2, contrasena);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                idClienteLogged = rs.getInt("id_cliente");
                loginCorrecto = true;
            }
            return loginCorrecto;

        } catch (Exception e) {
            System.out.println("Error al consultar datos: " + e.getMessage());
            return false;
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (rs != null) rs.close();
                ConexionDesconexionBD.desconexionBaseDatos(conn);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public static void registrarUsuario(String email, String contrasena, String nombre, String apellidos, String tlf) {
        PreparedStatement pstmt = null;
        Connection conn = null;

        try {
            conn = ConexionDesconexionBD.conexionBaseDatos();

            String sql = "INSERT INTO clientes (email, contrasena, nombre, apellidos, tlf) VALUES (?, ?, ?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, email);
            pstmt.setString(2, contrasena);
            pstmt.setString(3, nombre);
            pstmt.setString(4, apellidos);
            pstmt.setString(5, tlf);

            int columnasInsertadas = pstmt.executeUpdate();
            if (columnasInsertadas > 0) {
                System.out.println("Registro completado!");
            }

        } catch (Exception e) {
            System.out.println("Error en el registro "+e.getMessage());
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                ConexionDesconexionBD.desconexionBaseDatos(conn);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public static ArrayList<String[]> listarServicios() {
        Statement stmt = null;
        ResultSet rs = null;
        Connection conn = null;
        ArrayList<String[]> servicios = new ArrayList<>();

        try {
            conn = ConexionDesconexionBD.conexionBaseDatos();

            String sql = "SELECT nombre_servicio, descrip, precio_servicio FROM servicios";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                String nombreServicio = rs.getString("nombre_servicio");
                String descripServicio = rs.getString("descrip");
                double precioServicio = rs.getDouble("precio_servicio");

                String[] servicio = {
                        nombreServicio,
                        descripServicio,
                        String.format("%.2f €", precioServicio)
                };

                servicios.add(servicio);
            }
        } catch (Exception e) {
            System.out.println("Error al consultar los datos "+e.getMessage());
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (rs != null) rs.close();
                ConexionDesconexionBD.desconexionBaseDatos(conn);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
        return servicios;
    }

    public static void modificarEmail(String emailActual, String emailNuevo) {
        int idCliente = IdsUsuarioUpdates.getIdUsuarioEmail(emailActual);

        if (idCliente == -1) {
            System.out.println("No existe ningun usuario con ese email!");
        }


        boolean actualizado = IdsUsuarioUpdates.sentenciaUpdateEmail(idCliente, emailNuevo);

        if (actualizado) {
            System.out.println("Email modificado correctamente!");
        } else {
            System.out.println("No se pudo actualizar el email");
        }
    }

    public static void modificarContrasena(String contrasenaActual, String contrasenaNueva) {
        int idCliente = IdsUsuarioUpdates.getIdUsuarioContrasena(contrasenaActual);

        if (idCliente == -1) {
            System.out.println("Contrasena incorrecta!");
        }

        boolean actualizado = IdsUsuarioUpdates.sentenciaUpdateContrasena(idCliente, contrasenaNueva);

        if (actualizado) {
            System.out.println("Contrasena modificada correctamente!");
        } else {
            System.out.println("No se pudo actualizar la contrasena");
        }
    }

    public static void modificarTlf() {
        System.out.print("Introduce tu telefono actual: ");
        String tlfActual = inval.nextLine();

        int idCliente = IdsUsuarioUpdates.getIdUsuarioTlf(tlfActual);

        if (idCliente == -1) {
            System.out.println("Numero de telefono incorrecto!");
        }

        System.out.print("Introduce el nuevo numero de telefono: ");
        String nuevoTlf = inval.nextLine();

        boolean actualizado = IdsUsuarioUpdates.sentenciaUpdateTlf(idCliente, nuevoTlf);

        if (actualizado) {
            System.out.println("Numero de telefono modificado correctamente!");
        } else {
            System.out.println("No se pudo actualizar el telefono");
        }
    }

    public static void solicitarCita(int YEAR) {

        if (idClienteLogged == -1) {
            System.out.println("Error no hay usuario identificado, porfavor inicie sesion.");
            return;
        }

        System.out.print("Introduce el mes de la cita: ");
        int mes = inval.nextInt();
        inval.nextLine();
        System.out.print("Introduce el dia de la cita: ");
        int dia = inval.nextInt();
        inval.nextLine();
        System.out.print("Selecciona el servicio > [1. corte] [2. tinte] [3. cejas]: ");
        int servicio = inval.nextInt();
        inval.nextLine();

        LocalDate fechaCita = LocalDate.of(YEAR, mes, dia);
        PreparedStatement pstmt = null;
        Connection conn = ConexionDesconexionBD.conexionBaseDatos();
        try {
            String sql = "INSERT INTO citas (fecha, id_cli_cita, id_serv_cita) VALUES (?, ?, ?)";
            pstmt = conn.prepareStatement(sql);

            java.sql.Date fechaSqlite = java.sql.Date.valueOf(fechaCita);
            pstmt.setDate(1, fechaSqlite);
            pstmt.setInt(2, idClienteLogged);
            pstmt.setInt(3, servicio);

            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Cita registrada correctamente!");
            }
        } catch (Exception e) {
            System.out.println("Error en el registro de la cita"+e.getMessage());
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                ConexionDesconexionBD.desconexionBaseDatos(conn);
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public static void consultarCita() {
        Statement stmt = null;
        ResultSet rs = null;
        Connection conn = null;
        try {
            conn = ConexionDesconexionBD.conexionBaseDatos();

            String sql = "SELECT c.nombre, c.apellidos, c.email, c.tlf, ct.fecha, s.nombre_servicio, s.precio_servicio FROM clientes c JOIN citas ct ON c.id_cliente=ct.id_cli_cita JOIN servicios s ON ct.id_serv_cita=s.id";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                System.out.println("==============================================");
                System.out.println("Nombre: "+rs.getString("nombre")+"\n"+
                                    "Apellidos: "+rs.getString("apellidos")+"\n"+
                                    "Correo: "+rs.getString("email")+"\n"+
                                    "Telefono: "+rs.getString("tlf")+"\n"+
                                    "Fecha de la cita: "+rs.getDate("fecha")+"\n"+
                                    "Servicio: "+rs.getString("nombre_servicio")+"\n"+
                                    "Precio: "+rs.getDouble("precio_servicio"));
                System.out.println("==============================================");
            }
        } catch (SQLException e) {
            System.out.println("Error al generar el ticket de la cita "+e.getMessage());
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (rs != null) rs.close();
                ConexionDesconexionBD.desconexionBaseDatos(conn);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
    public static void eliminarCita() {
        if (idClienteLogged == -1) {
            System.out.println("Error no hay usuario identificado, porfavor inicie sesion.");
            return;
        }
        PreparedStatement pstmt = null;
        Connection conn = null;

        try {
            conn = ConexionDesconexionBD.conexionBaseDatos();
            String sql = "DELETE from citas WHERE id_cli_cita = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, idClienteLogged);

            int rowsDeleted = pstmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Cita eliminada con exito!");
            } else {
                System.out.println("Este usuario no tiene citas agendadas!");
            }
        } catch (SQLException e) {
            System.out.println("Error al eliminar la cita "+e.getMessage());
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                ConexionDesconexionBD.desconexionBaseDatos(conn);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
    public static String seleccionarNombreUsuario() {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection conn = null;
        String nombreCliente="";

        try {
            conn = ConexionDesconexionBD.conexionBaseDatos();

            String sql = "SELECT nombre FROM clientes WHERE id_cliente = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, idClienteLogged);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                nombreCliente = rs.getString("nombre");
            }

        } catch (Exception e) {
            System.out.println("Error al consultar datos: " + e.getMessage());
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (rs != null) rs.close();
                ConexionDesconexionBD.desconexionBaseDatos(conn);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
        return nombreCliente;
    }
}