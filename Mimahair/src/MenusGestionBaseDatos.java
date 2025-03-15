import java.util.Scanner;

public class MenusGestionBaseDatos {

    /* Esta clase gestiona/maneja todos los menus para navegar
    * por el programa de manera aseada */

   static Scanner inval = new Scanner(System.in);
//    public static void menuPrincipal() throws Exception {
//        boolean isRunning = true;
//
//        while (isRunning) {
//            System.out.println("********MIMAHAIR********");
//            System.out.println("1. Identificate");
//            System.out.println("2. Registrate");
//            System.out.println("3. Consultar servicios");
//            System.out.println("4. Salir");
//            System.out.println("************************");
//
//            System.out.print("\nSelecciona una opcion: ");
//            int seleccion = inval.nextInt();
//            inval.nextLine();
//
//            switch (seleccion) {
//                case 1 -> {
//                    if (GestionBaseDatos.identificacionUsuario()) {
//                        menuOpciones();
//                    } else {
//                        System.out.println("Identificacion incorrecta");
//                        return;
//                    }
//                }
//                case 2 -> GestionBaseDatos.registrarUsuario();
//                case 3 -> GestionBaseDatos.listarServicios();
//                case 4 -> {
//                    System.out.println("Hasta pronto!");
//                    isRunning = false;
//                }
//                default -> System.out.println("Introduce una opcion valida!");
//            }
//        }
//    }

//    public static void menuOpciones() {
//        boolean isRunning = true;
//
//        while (isRunning) {
//            System.out.println("[- - - MIMAHAIR - - -]");
//            System.out.println("*** MENU PRINCIPAL ***");
//            System.out.println("1. Gestion de la cuenta");
//            System.out.println("2. Solicitar nueva cita");
//            System.out.println("3. Servicios disponibles");
//            System.out.println("4. Volver atras");
//            System.out.println("***********************");
//
//            System.out.print("\nSelecciona una opcion: ");
//            int seleccion = inval.nextInt();
//            inval.nextLine();
//
//            switch (seleccion) {
//                case 1 -> menuGestionCuenta();
//                case 2 -> GestionBaseDatos.solicitarCita(GestionBaseDatos.ANYO);
//                case 3 -> GestionBaseDatos.listarServicios();
//                case 4 -> {
//                    System.out.println("Gracias por usar Mimahair!");
//                    isRunning = false;
//                }
//            }
//        }
//    }

//    public static void menuGestionCuenta() {
//        boolean isRunning = true;
//
//        while (isRunning) {
//            System.out.println("[- - - MIMAHAIR - - -]");
//            System.out.println("*** GESTION CUENTA ***");
//            System.out.println("1. Editar email"); // String
//            System.out.println("2. Cambiar contrasena"); // String
//            System.out.println("3. Modificar telefono"); // String
//            System.out.println("4. Gestoinar citas"); // Menu
//            System.out.println("5. Volver");
//            System.out.println("**********************");
//
//            System.out.print("\nSelecciona una opcion: ");
//            int opcion = inval.nextInt();
//            inval.nextLine();
//
//            switch (opcion) {
//                case 1 -> GestionBaseDatos.modificarEmail();
//                case 2 -> GestionBaseDatos.modificarContrasena();
//                case 3 -> GestionBaseDatos.modificarTlf();
//                case 4 -> menuGestionCita();
//                case 5 -> isRunning = false;
//            }
//        }
//    }

    public static void menuGestionCita() {
        boolean isRunning = true;

        while (isRunning) {
            System.out.println("[- - - MIMAHAIR - - -]");
            System.out.println("******* CITAS ********");
            System.out.println("1. Consultar cita");
            System.out.println("2. Eliminar cita");
            System.out.println("3. Volver");
            System.out.println("********************\n");
            System.out.print("Que quieres hacer?: ");
            int opcion = inval.nextInt();
            inval.nextLine();

            switch (opcion) {
                case 1 -> GestionBaseDatos.consultarCita();
                case 2 -> GestionBaseDatos.eliminarCita();
                case 3 -> isRunning = false;
            }
        }
    }
}