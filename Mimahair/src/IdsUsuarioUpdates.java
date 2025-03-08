import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/* Esta clase maneja de forma ordenada los "return" de las id de usuarios
* Al ser sentencias parecidas se almacenan de forma ordenada en una clase
* aparte para mejor organizacion y mantenimiento del codigo. */

public class IdsUsuarioUpdates {

    public static int getIdUsuarioEmail(String email) {
        String sql = "SELECT id_cliente FROM clientes WHERE email = ?";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int idCliente = -1;
       Connection conn = ConexionDesconexionBD.conexionBaseDatos();

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, email);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                idCliente = rs.getInt("id_cliente");
            }
        } catch (Exception e) {
            System.out.println("Error al consultar los datos "+e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                ConexionDesconexionBD.desconexionBaseDatos(conn);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
        return idCliente;
    }

    public static boolean sentenciaUpdateEmail(int idCliente, String nuevoEmail) {
        String sql = "UPDATE clientes SET email = ? WHERE id_cliente = ?";
        PreparedStatement pstmt = null;
        boolean estaActualizado = false;
        Connection conn = ConexionDesconexionBD.conexionBaseDatos();

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, nuevoEmail);
            pstmt.setInt(2, idCliente);

            int filasActualizadas = pstmt.executeUpdate();
            estaActualizado = (filasActualizadas > 0);
        } catch (Exception e) {
            System.out.println("Error al actualizar email: "+e.getMessage());
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                ConexionDesconexionBD.desconexionBaseDatos(conn);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
        return estaActualizado;
    }

    public static int getIdUsuarioContrasena(String contrasena) {
        String sql = "SELECT id_cliente FROM clientes WHERE contrasena = ?";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int idCliente = -1;
        Connection conn = ConexionDesconexionBD.conexionBaseDatos();

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, contrasena);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                idCliente = rs.getInt("id_cliente");
            }
        } catch (Exception e) {
            System.out.println("Error al consultar los datos "+e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                ConexionDesconexionBD.desconexionBaseDatos(conn);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
        return idCliente;
    }

    public static boolean sentenciaUpdateContrasena(int idCliente, String nuevaContrasena) {
        String sql = "UPDATE clientes SET contrasena = ? WHERE id_cliente = ?";
        PreparedStatement pstmt = null;
        boolean estaActualizado = false;
        Connection conn = ConexionDesconexionBD.conexionBaseDatos();

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, nuevaContrasena);
            pstmt.setInt(2, idCliente);

            int filasActualizadas = pstmt.executeUpdate();
            estaActualizado = (filasActualizadas > 0);
        } catch (Exception e) {
            System.out.println("Error al actualizar contrasena: "+e.getMessage());
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                ConexionDesconexionBD.desconexionBaseDatos(conn);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
        return estaActualizado;
    }

    public static int getIdUsuarioTlf(String tlf) {
        String sql = "SELECT id_cliente FROM clientes WHERE tlf = ?";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int idCliente = -1;
        Connection conn = ConexionDesconexionBD.conexionBaseDatos();

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, tlf);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                idCliente = rs.getInt("id_cliente");
            }
        } catch (Exception e) {
            System.out.println("Error al consultar los datos "+e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                ConexionDesconexionBD.desconexionBaseDatos(conn);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
        return idCliente;
    }

    public static boolean sentenciaUpdateTlf(int idCliente, String nuevoTlf) {
        String sql = "UPDATE clientes SET tlf = ? WHERE id_cliente = ?";
        PreparedStatement pstmt = null;
        boolean estaActualizado = false;
        Connection conn = ConexionDesconexionBD.conexionBaseDatos();

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, nuevoTlf);
            pstmt.setInt(2, idCliente);

            int filasActualizadas = pstmt.executeUpdate();
            estaActualizado = (filasActualizadas > 0);
        } catch (Exception e) {
            System.out.println("Error al actualizar el numero de telefono: "+e.getMessage());
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                ConexionDesconexionBD.desconexionBaseDatos(conn);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
        return estaActualizado;
    }
}