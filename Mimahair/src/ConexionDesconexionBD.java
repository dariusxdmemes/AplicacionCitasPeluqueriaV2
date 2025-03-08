import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/* Para SQLite solamente se necesita la url para la connexion a la base
de datos, ya que es local, en el caso de migrarla a una externa como la
de clase (DBeaver) se tendrían que crear otras constantes las cuales serían:
USER y PASSWORD.
 */
public class ConexionDesconexionBD {
    private static final String url = "jdbc:sqlite:mimahair.db";

    public static Connection conexionBaseDatos() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
            System.out.println("Conexion establecida");
        } catch (SQLException e) {
            System.out.println("Error al conectar con la base de datos! "+e.getMessage());
        }
        return conn;
    }

    public static void desconexionBaseDatos(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
                System.out.println("Conexion cerrada!");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}