import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //Inicializamos variables
        Scanner s = new Scanner(System.in);
        int opcionMenu, opcionMenuHabitaciones = 0, opcionMenuAdministrador = 0;
        int habitacionesIndividualesLibres = 2, habitacionesDoblesLibres = 8, habitacionAsignada = 0;
        float ingresosTotales = 0;
        int reservasFinalizadas = 0, reservasRealizadas = 0;
        boolean habitacionCompleta = false;
        String nombreCliente = "", apellidoCliente = "", dniCliente = "";
        int intentosMenuAdmin = 3;
        boolean logueado = false, apagarMaquina = false;
        boolean vuelveMenuPrincipal;

        //Variables Habitaciones Individuales
        int habitacionIndi1 = 1, habitacionIndi2 = 2;
        String nombreHabitIndi1 = "", nombreHabitIndi2 = "";
        String apellidoHabitIndi1 = "", apellidoHabitIndi2 = "";
        String dniHabitIndi1 = "", dniHabitIndi2 = "";
        String confirHabitIndividual1 = "", confirHabitIndividual2 = "";

        //Variables Habitaciones Dobles
        int habitacionDoble1 = 3, habitacionDoble2 = 4, habitacionDoble3 = 5, habitacionDoble4 = 6,
                habitacionDoble5 = 7, habitacionDoble6 = 8, habitacionDoble7 = 9, habitacionDoble8 = 10;
        String nombreHabitDoble1 = "", nombreHabitDoble2 = "", nombreHabitDoble3 = "", nombreHabitDoble4 = "",
                nombreHabitDoble5 = "", nombreHabitDoble6 = "", nombreHabitDoble7 = "", nombreHabitDoble8 = "";
        String apellidoHabitDoble1 = "", apellidoHabitDoble2 = "", apellidoHabitDoble3 = "", apellidoHabitDoble4 = "",
                apellidoHabitDoble5 = "", apellidoHabitDoble6 = "", apellidoHabitDoble7 = "", apellidoHabitDoble8 = "";
        String dniHabitDoble1 = "", dniHabitDoble2 = "", dniHabitDoble3 = "", dniHabitDoble4 = "",
                dniHabitDoble5 = "", dniHabitDoble6 = "", dniHabitDoble7 = "", dniHabitDoble8 = "";
        String confirHabitDoble1 = "", confirHabitDoble2 = "", confirHabitDoble3 = "", confirHabitDoble4 = "",
                confirHabitDoble5 = "", confirHabitDoble6 = "", confirHabitDoble7 = "", confirHabitDoble8 = "";

        //Variables DNI
        int numDNI, moduloDNI;
        final String LISTA_LETRAS = "TRWAGMYFPDXBNJZSQVHLCKE";
        char letraDNI;
        boolean dniValido = false;

        // Variables fechas
        LocalDate fechaHoy = LocalDate.now();
        int diaActual = fechaHoy.getDayOfMonth();
        LocalDate fechaSalida;
        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Period periodoFecha;

        // Variables checkout
        int habitacionTeclado, intentosConfir, diaSalida, mesSalida, precioPorNoche, pax, numNoches;
        boolean habitacionSeleccionada, confirCorrecto, checkoutRealizado;
        String confirCliente = "", confirTeclado = "";
        String fechaEntrada;
        String fechaSalidaCheckout;
        float totalSinIVA, iva, totalConIVA;

        //Variables Monedas y Precios
        final int PRECIO_HABITACION_INDIVIDUAL = 45, PRECIO_HABITACION_DOBLE = 60;
        float dineroIntroducido, cambio = 0;
        int billetes200 = 20, billetes100 = 20, billetes50 = 20, billetes20 = 20, billetes10 = 20, billetes5 = 20;
        int billetes200Cambio = 0, billetes100Cambio = 0, billetes50Cambio = 0, billetes20Cambio = 0, billetes10Cambio = 0, billetes5Cambio = 0;
        int monedas2 = 50, monedas1 = 50, monedas050 = 50, monedas020 = 50, monedas010 = 50,
                monedas005 = 50, monedas002 = 50, monedas001 = 50;
        int monedas2Cambio = 0, monedas1Cambio = 0, monedas050Cambio = 0, monedas020Cambio = 0, monedas010Cambio = 0,
                monedas005Cambio = 0, monedas002Cambio = 0, monedas001Cambio = 0;

        //Variables Menú Administrador
        String adminUser = "admin", adminPassword = "admin", adminTeclado = "", claveTeclado = "";
        String rellenaDinero = "";

        //Inicio del programa. Mostramos el menú
        do {
            intentosMenuAdmin = 3;
            System.out.print("""          
                    *====================================================================================================================*
                    |                                      _    _  ____ _______ ______ _     \s                                           |
                    |                                     | |  | |/ __ \\__   __|  ____| |    \s                                           |
                    |                                     | |__| | |  | | | |  | |__  | |    \s                                           |
                    |                                     |  __  | |  | | | |  |  __| | |    \s                                           |
                    |                                     | |  | | |__| | | |  | |____| |____\s                                           |
                    |                                     |_|  |_|\\____/  |_|  |______|______|                                           |
                    |--------------------------------------------------------------------------------------------------------------------|
                    |                                                                       \s                                            |
                    |--------------------------------------------------------------------------------------------------------------------|                                                                        
                    |      _____ _____ _    _ _____          _____    _____  ______   __  __          _____ _______ ____   _____\s        |
                    |     / ____|_   _| |  | |  __ \\   /\\   |  __ \\  |  __ \\|  ____| |  \\/  |   /\\   |  __ \\__   __/ __ \\ / ____|        |
                    |    | |      | | | |  | | |  | | /  \\  | |  | | | |  | | |__    | \\  / |  /  \\  | |__) | | | | |  | | (___ \s        |
                    |    | |      | | | |  | | |  | |/ /\\ \\ | |  | | | |  | |  __|   | |\\/| | / /\\ \\ |  _  /  | | | |  | |\\___ \\\s        |
                    |    | |____ _| |_| |__| | |__| / ____ \\| |__| | | |__| | |____  | |  | |/ ____ \\| | \\ \\  | | | |__| |____) |        |
                    |     \\_____|_____|\\____/|_____/_/    \\_\\_____/  |_____/|______| |_|  |_/_/    \\_\\_|  \\_\\ |_|  \\____/|_____/\s        |
                    |                                                                                                         \s          |
                    *======================================================*=============================================================*  
                    |                                                      |          
                    |  1.- Ver el estado de ocupación de las habitaciones  |
                    |  2.- Reservar una habitación                         |
                    |  3.- Realizar un checkout de una habitación          |
                    |  4.- Menú de Administrador                           |
                    |                                                      |
                    *======================================================*
                    Selecciona una opción:\s""");
            opcionMenu = Integer.parseInt(s.nextLine());

            switch (opcionMenu) {
                case 1: //Mostramos por pantalla la cantidad de habitaciones libres que hay.
                    for (int i = 0; i < 40; i++) { //Limpieza
                        System.out.println();
                    }

                    if (habitacionesIndividualesLibres == 1)
                        System.out.printf("Actualmente hay disponibles %d habitación individual y %d habitaciones dobles.\n", habitacionesIndividualesLibres, habitacionesDoblesLibres);
                    else {
                        if (habitacionesDoblesLibres == 1)
                            System.out.printf("Actualmente hay disponibles %d habitaciones individuales y %d habitación doble.\n", habitacionesIndividualesLibres, habitacionesDoblesLibres);
                        else
                            System.out.printf("Actualmente hay disponibles %d habitaciones individuales y %d habitaciones dobles.\n", habitacionesIndividualesLibres, habitacionesDoblesLibres);
                    }

                    System.out.print("Pulse una tecla para continuar: ");
                    s.nextLine();
                    for (int i = 0; i < 40; i++) { //Limpieza
                        System.out.println();
                    }
                    break;

                case 2: //Hacemos la reserva de las habitaciones, tomando en cuenta si hay libres o no y la verificación de DNI.
                    vuelveMenuPrincipal = false;

                    for (int i = 0; i < 40; i++) { //Limpieza
                        System.out.println();
                    }

                    if (habitacionesIndividualesLibres == 0 && habitacionesDoblesLibres == 0) {
                        System.out.println("No hay habitaciones disponibles en este momento.");

                        System.out.print("Pulse una tecla para continuar: ");
                        s.nextLine();
                        for (int i = 0; i < 40; i++) { //Limpieza
                            System.out.println();
                        }

                    }else {
                        do {
                            habitacionCompleta = false;
                            habitacionAsignada = 0;
                            System.out.print("""
                                    *====================================*
                                    |  Tipo de habitación  |    Precio   |
                                    *------------------------------------*
                                    | 1.- Individual       |     45€     |
                                    | 2.- Doble            |     60€     |
                                    *------------------------------------*
                                    | 3.- Volver al Menú Principal       |
                                    *====================================*
                                    Seleccione una opción:\s""");
                            opcionMenuHabitaciones = Integer.parseInt(s.nextLine());

                            if (opcionMenuHabitaciones == 1 && habitacionesIndividualesLibres == 0) {
                                habitacionCompleta = true;
                                System.out.println("No hay habitaciones individuales disponibles, pruebe con otro tipo de habitación.");

                                System.out.print("Pulse una tecla para continuar: ");
                                s.nextLine();
                                for (int i = 0; i < 40; i++) { //Limpieza
                                    System.out.println();
                                }
                            }

                            if (opcionMenuHabitaciones == 2 && habitacionesDoblesLibres == 0) {
                                habitacionCompleta = true;
                                System.out.println("No hay habitaciones dobles disponibles, pruebe con otro tipo de habitación.");

                                System.out.print("Pulse una tecla para continuar: ");
                                s.nextLine();
                                for (int i = 0; i < 40; i++) { //Limpieza
                                    System.out.println();
                                }
                            }

                            switch (opcionMenuHabitaciones) { //Con este menú, se elige el tipo de habitación y se piden datos.
                                case 1:
                                    System.out.println("Ha seleccionado una habitación individual");
                                    break;

                                case 2:
                                    System.out.println("Ha seleccionado una habitación doble");
                                    break;

                                case 3:
                                    vuelveMenuPrincipal = true;
                                    System.out.print("Volviendo al menú principal");
                                    for (int i = 0; i < 4; i++) { //Animación
                                        System.out.print(".");
                                        try {
                                            Thread.sleep(500);
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }

                                    }
                                    for (int i = 0; i < 40; i++) { //Limpieza
                                        System.out.println();
                                    }
                                    break;

                                default:
                                    System.out.println("Opción no válida");
                                    System.out.print("Pulse una tecla para continuar: ");
                                    s.nextLine();
                                    for (int i = 0; i < 40; i++) { //Limpieza
                                        System.out.println();
                                    }
                            }
                        } while ((opcionMenuHabitaciones != 1) && (opcionMenuHabitaciones != 2) && (opcionMenuHabitaciones != 3) || habitacionCompleta);

                        if (!vuelveMenuPrincipal) {
                            System.out.println("Introduzca los datos que le pedimos, por favor");

                            // Con este bucle hacemos que no haya errores después en el checkout
                            do {
                                System.out.print("Nombre: ");
                                nombreCliente = s.nextLine();
                                if (nombreCliente.isEmpty()) System.out.println("Este campo es obligatorio");
                            } while (nombreCliente.isEmpty());

                            System.out.print("Primer apellido: ");
                            apellidoCliente = s.nextLine();
                            do {
                                System.out.print("DNI: ");
                                dniCliente = s.nextLine().toUpperCase();
                                if (dniCliente.length() != 9) System.out.println("Número de caracteres no válido");
                                else {
                                    numDNI = Integer.parseInt(dniCliente.substring(0, 8));
                                    letraDNI = dniCliente.charAt(8);
                                    moduloDNI = numDNI % 23;
                                    if (letraDNI != LISTA_LETRAS.charAt(moduloDNI))
                                        System.out.println("Hubo un error, compruebe su DNI nuevamente");
                                    else dniValido = true;
                                }
                            } while (!dniValido);

                            //Generamos el número de confirmación de reserva
                            reservasRealizadas++;
                            confirCliente = "";
                            confirCliente = confirCliente.concat(dniCliente.substring(5)).concat(String.valueOf(reservasRealizadas));

                            //Escribimos los datos de los clientes en su respectiva variable. Individuales.
                            if (opcionMenuHabitaciones == 1 && nombreHabitIndi1.isEmpty()) {
                                habitacionAsignada = habitacionIndi1;
                                nombreHabitIndi1 = nombreCliente;
                                apellidoHabitIndi1 = apellidoCliente;
                                dniHabitIndi1 = dniCliente;
                                confirHabitIndividual1 = confirCliente.concat(String.valueOf(habitacionAsignada * 100));
                            } else {
                                if (opcionMenuHabitaciones == 1 && nombreHabitIndi2.isEmpty()) {
                                    habitacionAsignada = habitacionIndi2;
                                    nombreHabitIndi2 = nombreCliente;
                                    apellidoHabitIndi2 = apellidoCliente;
                                    dniHabitIndi2 = dniCliente;
                                    confirHabitIndividual2 = confirCliente.concat(String.valueOf(habitacionAsignada * 100));
                                }
                            }

                            //Escribimos los datos de los clientes en su respectiva variable. Dobles.
                            if (opcionMenuHabitaciones == 2 && nombreHabitDoble1.isEmpty()) {
                                habitacionAsignada = habitacionDoble1;
                                nombreHabitDoble1 = nombreCliente;
                                apellidoHabitDoble1 = apellidoCliente;
                                dniHabitDoble1 = dniCliente;
                                confirHabitDoble1 = confirCliente.concat(String.valueOf(habitacionAsignada * 100));
                            } else {
                                if (opcionMenuHabitaciones == 2 && nombreHabitDoble2.isEmpty()) {
                                    habitacionAsignada = habitacionDoble2;
                                    nombreHabitDoble2 = nombreCliente;
                                    apellidoHabitDoble2 = apellidoCliente;
                                    dniHabitDoble2 = dniCliente;
                                    confirHabitDoble2 = confirCliente.concat(String.valueOf(habitacionAsignada * 100));
                                } else {
                                    if (opcionMenuHabitaciones == 2 && nombreHabitDoble3.isEmpty()) {
                                        habitacionAsignada = habitacionDoble3;
                                        nombreHabitDoble3 = nombreCliente;
                                        apellidoHabitDoble3 = apellidoCliente;
                                        dniHabitDoble3 = dniCliente;
                                        confirHabitDoble3 = confirCliente.concat(String.valueOf(habitacionAsignada * 100));
                                    } else {
                                        if (opcionMenuHabitaciones == 2 && nombreHabitDoble4.isEmpty()) {
                                            habitacionAsignada = habitacionDoble4;
                                            nombreHabitDoble4 = nombreCliente;
                                            apellidoHabitDoble4 = apellidoCliente;
                                            dniHabitDoble4 = dniCliente;
                                            confirHabitDoble4 = confirCliente.concat(String.valueOf(habitacionAsignada * 100));
                                        } else {
                                            if (opcionMenuHabitaciones == 2 && nombreHabitDoble5.isEmpty()) {
                                                habitacionAsignada = habitacionDoble5;
                                                nombreHabitDoble5 = nombreCliente;
                                                apellidoHabitDoble5 = apellidoCliente;
                                                dniHabitDoble5 = dniCliente;
                                                confirHabitDoble5 = confirCliente.concat(String.valueOf(habitacionAsignada * 100));
                                            } else {
                                                if (opcionMenuHabitaciones == 2 && nombreHabitDoble6.isEmpty()) {
                                                    habitacionAsignada = habitacionDoble6;
                                                    nombreHabitDoble6 = nombreCliente;
                                                    apellidoHabitDoble6 = apellidoCliente;
                                                    dniHabitDoble6 = dniCliente;
                                                    confirHabitDoble6 = confirCliente.concat(String.valueOf(habitacionAsignada * 100));
                                                } else {
                                                    if (opcionMenuHabitaciones == 2 && nombreHabitDoble7.isEmpty()) {
                                                        habitacionAsignada = habitacionDoble7;
                                                        nombreHabitDoble7 = nombreCliente;
                                                        apellidoHabitDoble7 = apellidoCliente;
                                                        dniHabitDoble7 = dniCliente;
                                                        confirHabitDoble7 = confirCliente.concat(String.valueOf(habitacionAsignada * 100));
                                                    } else {
                                                        if (opcionMenuHabitaciones == 2 && nombreHabitDoble8.isEmpty()) {
                                                            habitacionAsignada = habitacionDoble8;
                                                            nombreHabitDoble8 = nombreCliente;
                                                            apellidoHabitDoble8 = apellidoCliente;
                                                            dniHabitDoble8 = dniCliente;
                                                            confirHabitDoble8 = confirCliente.concat(String.valueOf(habitacionAsignada * 100));
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }

                            //Al tener la reserva realizada, se resta a habitaciones individuales o dobles, según la que hemos seleccionado.
                            if (habitacionAsignada <= 2) habitacionesIndividualesLibres--;
                            else habitacionesDoblesLibres--;

                            //Se "reinicia" y modifica el valor de la variable confirCliente para que concuerde con el que ha sido guardado.
                            confirCliente = "";
                            confirCliente = confirCliente.concat(dniCliente.substring(5)).concat(String.valueOf(reservasRealizadas));
                            confirCliente = confirCliente.concat(String.valueOf(habitacionAsignada * 100));

                            for (int i = 0; i < 40; i++) { //Limpieza
                                System.out.println();
                            }
                            System.out.print("Realizando reserva");
                            for (int i = 0; i < 4; i++) { //Animación
                                System.out.print(".");
                                try {
                                    Thread.sleep(500);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }

                            }
                            for (int i = 0; i < 40; i++) { //Limpieza
                                System.out.println();
                            }

                            //Mostramos los datos que se han introducido junto al numero de habitación
                            System.out.printf("""
                                *==============================================*
                                |               DATOS DE TU RESERVA            |
                                *----------------------------------------------*
                                - Número de habitación:                %d               
                                - Número de confirmación de reserva:   #%s  
                                - Nombre:                              %s                              
                                - Apellido:                            %s
                                - DNI:                                 %s
                                """, habitacionAsignada, confirCliente, nombreCliente, apellidoCliente, dniCliente);
                            System.out.print("Pulse una tecla para continuar: ");
                            s.nextLine();
                            for (int i = 0; i < 40; i++) { //Limpieza
                                System.out.println();
                            }
                        }
                    }
                    break;

                case 3: //Realizar checkout
                    for (int i = 0; i < 40; i++) { //Limpieza
                        System.out.println();
                    }

                    if (habitacionesIndividualesLibres == 2 && habitacionesDoblesLibres == 8) {
                        System.out.println("Aún no se han realizado reservas");

                        System.out.print("Pulse una tecla para continuar: ");
                        s.nextLine();

                    }else {
                        do {
                            //Reasiganmos valores iniciales a varias variables
                            habitacionSeleccionada = false;
                            confirCorrecto = false;
                            intentosConfir = 3;
                            checkoutRealizado = false;
                            billetes200Cambio = 0;
                            billetes100Cambio = 0;
                            billetes50Cambio = 0;
                            billetes20Cambio = 0;
                            billetes10Cambio = 0;
                            billetes5Cambio = 0;
                            monedas2Cambio = 0;
                            monedas1Cambio = 0;
                            monedas050Cambio = 0;
                            monedas020Cambio = 0;
                            monedas010Cambio = 0;
                            monedas005Cambio = 0;
                            monedas002Cambio = 0;
                            monedas001Cambio = 0;

                            System.out.print("Introduzca el número de su habitación: ");
                            habitacionTeclado = Integer.parseInt(s.nextLine());

                            if ((habitacionTeclado == habitacionIndi1 && nombreHabitIndi1.isEmpty()) ||
                                    (habitacionTeclado == habitacionIndi2 && nombreHabitIndi2.isEmpty()) ||
                                    (habitacionTeclado == habitacionDoble1 && nombreHabitDoble1.isEmpty()) ||
                                    (habitacionTeclado == habitacionDoble2 && nombreHabitDoble2.isEmpty()) ||
                                    (habitacionTeclado == habitacionDoble3 && nombreHabitDoble3.isEmpty()) ||
                                    (habitacionTeclado == habitacionDoble4 && nombreHabitDoble4.isEmpty()) ||
                                    (habitacionTeclado == habitacionDoble5 && nombreHabitDoble5.isEmpty()) ||
                                    (habitacionTeclado == habitacionDoble6 && nombreHabitDoble6.isEmpty()) ||
                                    (habitacionTeclado == habitacionDoble7 && nombreHabitDoble7.isEmpty()) ||
                                    (habitacionTeclado == habitacionDoble8 && nombreHabitDoble8.isEmpty()))
                                System.out.println("Esa habitación está vacía actualmente");
                            else {
                                 /*Con este switch asignamos los valores de la habitacion a las variables que usaremos para
                                 sacar el checkout por pantalla
                                  */
                                switch (habitacionTeclado) {
                                    case 1 -> {
                                        habitacionAsignada = habitacionIndi1;
                                        nombreCliente = nombreHabitIndi1;
                                        apellidoCliente = apellidoHabitIndi1;
                                        dniCliente = dniHabitIndi1;
                                        confirCliente = confirHabitIndividual1;
                                        habitacionSeleccionada = true;
                                    }
                                    case 2 -> {
                                        habitacionAsignada = habitacionIndi2;
                                        nombreCliente = nombreHabitIndi2;
                                        apellidoCliente = apellidoHabitIndi2;
                                        dniCliente = dniHabitIndi2;
                                        confirCliente = confirHabitIndividual2;
                                        habitacionSeleccionada = true;
                                    }
                                    case 3 -> {
                                        habitacionAsignada = habitacionDoble1;
                                        nombreCliente = nombreHabitDoble1;
                                        apellidoCliente = apellidoHabitDoble1;
                                        dniCliente = dniHabitDoble1;
                                        confirCliente = confirHabitDoble1;
                                        habitacionSeleccionada = true;
                                    }
                                    case 4 -> {
                                        habitacionAsignada = habitacionDoble2;
                                        nombreCliente = nombreHabitDoble2;
                                        apellidoCliente = apellidoHabitDoble2;
                                        dniCliente = dniHabitDoble2;
                                        confirCliente = confirHabitDoble2;
                                        habitacionSeleccionada = true;
                                    }
                                    case 5 -> {
                                        habitacionAsignada = habitacionDoble3;
                                        nombreCliente = nombreHabitDoble3;
                                        apellidoCliente = apellidoHabitDoble3;
                                        dniCliente = dniHabitDoble3;
                                        confirCliente = confirHabitDoble3;
                                        habitacionSeleccionada = true;
                                    }
                                    case 6 -> {
                                        habitacionAsignada = habitacionDoble4;
                                        nombreCliente = nombreHabitDoble4;
                                        apellidoCliente = apellidoHabitDoble4;
                                        dniCliente = dniHabitDoble4;
                                        confirCliente = confirHabitDoble4;
                                        habitacionSeleccionada = true;
                                    }
                                    case 7 -> {
                                        habitacionAsignada = habitacionDoble5;
                                        nombreCliente = nombreHabitDoble5;
                                        apellidoCliente = apellidoHabitDoble5;
                                        dniCliente = dniHabitDoble5;
                                        confirCliente = confirHabitDoble5;
                                        habitacionSeleccionada = true;
                                    }
                                    case 8 -> {
                                        habitacionAsignada = habitacionDoble6;
                                        nombreCliente = nombreHabitDoble6;
                                        apellidoCliente = apellidoHabitDoble6;
                                        dniCliente = dniHabitDoble6;
                                        confirCliente = confirHabitDoble6;
                                        habitacionSeleccionada = true;
                                    }
                                    case 9 -> {
                                        habitacionAsignada = habitacionDoble7;
                                        nombreCliente = nombreHabitDoble7;
                                        apellidoCliente = apellidoHabitDoble7;
                                        dniCliente = dniHabitDoble7;
                                        confirCliente = confirHabitDoble7;
                                        habitacionSeleccionada = true;
                                    }
                                    case 10 -> {
                                        habitacionAsignada = habitacionDoble8;
                                        nombreCliente = nombreHabitDoble8;
                                        apellidoCliente = apellidoHabitDoble8;
                                        dniCliente = dniHabitDoble8;
                                        confirCliente = confirHabitDoble8;
                                        habitacionSeleccionada = true;
                                    }
                                    default -> System.out.println("El número de habitación no es correcto");
                                }

                                if (habitacionSeleccionada){
                                    System.out.print("Buscando en el sistema");
                                    for (int i = 0; i < 4; i++) { //Animación
                                        System.out.print(".");
                                        try {
                                            Thread.sleep(500);
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }

                                    }
                                    for (int i = 0; i < 40; i++) { //Limpieza
                                        System.out.println();
                                    }

                                    System.out.printf("Hola %s, procederemos con el checkout de su habitación.\n", nombreCliente);
                                    do {
                                        System.out.println("Introduzca el número de confirmación que se le proporcionó.");
                                        System.out.printf("Tiene %d intentos (no añada el #, solo los números y  letras): ", intentosConfir);

                                        confirTeclado = s.nextLine();

                                        if (!confirTeclado.equals(confirCliente)) {
                                            System.out.println("Código incorrecto");
                                            intentosConfir--;
                                        }
                                        else{
                                            System.out.println("El código es correcto");
                                            confirCorrecto = true;
                                        }
                                    }while(!confirCorrecto && intentosConfir != 0);

                                    //Si se acaban los intentos
                                    if (!confirCorrecto){
                                        System.out.print("Se volverá al menú principal");

                                        for (int i = 0; i < 4; i++) { //Animación
                                            System.out.print(".");
                                            try {
                                                Thread.sleep(500);
                                            } catch (InterruptedException e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    }
                                }

                                //Si ha introducido bien el codigo de confirmación de la reserva
                                if (confirCorrecto){
                                    do {
                                        System.out.print("Introduzca el día de salida: ");
                                        diaSalida = Integer.parseInt(s.nextLine());
                                        System.out.print("Introduzca el mes de salida: ");
                                        mesSalida = Integer.parseInt(s.nextLine());

                                        if ( (mesSalida == 1 && (diaSalida <= diaActual || diaSalida > 31)) ||
                                                (mesSalida == 2 && (diaSalida <= diaActual || diaSalida > 28)) ||
                                                (mesSalida == 3 && (diaSalida <= diaActual || diaSalida > 31)) ||
                                                (mesSalida == 4 && (diaSalida <= diaActual || diaSalida > 30)) ||
                                                (mesSalida == 5 && (diaSalida <= diaActual || diaSalida > 31)) ||
                                                (mesSalida == 6 && (diaSalida <= diaActual || diaSalida > 30)) ||
                                                (mesSalida == 7 && (diaSalida <= diaActual || diaSalida > 31)) ||
                                                (mesSalida == 8 && (diaSalida <= diaActual || diaSalida > 31)) ||
                                                (mesSalida == 9 && (diaSalida <= diaActual || diaSalida > 30)) ||
                                                (mesSalida == 10 && (diaSalida <= diaActual || diaSalida > 31)) ||
                                                (mesSalida == 11 && (diaSalida <= diaActual || diaSalida > 30)) ||
                                                (mesSalida == 12 && (diaSalida <= diaActual || diaSalida > 31)) )
                                            System.out.println("Hubo un error en los datos");
                                        else{
                                            //Asignamos los valores antes de pintar el checkout
                                            fechaEntrada = fechaHoy.format(formatoFecha);
                                            fechaSalida = LocalDate.of(2023, mesSalida, diaSalida);
                                            fechaSalidaCheckout = fechaSalida.format(formatoFecha);
                                            pax = ( (habitacionAsignada <= 2) ? 1 : 2 );
                                            periodoFecha = fechaHoy.until(fechaSalida);
                                            numNoches = periodoFecha.getDays();
                                            precioPorNoche = ( (habitacionAsignada <= 2) ? PRECIO_HABITACION_INDIVIDUAL : PRECIO_HABITACION_DOBLE );
                                            totalSinIVA = numNoches * precioPorNoche;
                                            iva = totalSinIVA * 0.21f;
                                            totalConIVA = totalSinIVA + iva;

                                            System.out.print("Generando factura");
                                            for (int i = 0; i < 4; i++) { //Animación
                                                System.out.print(".");
                                                try {
                                                    Thread.sleep(500);
                                                } catch (InterruptedException e) {
                                                    e.printStackTrace();
                                                }

                                            }
                                            for (int i = 0; i < 40; i++) { //Limpieza
                                                System.out.println();
                                            }

                                            //Pintamos el checkout
                                            System.out.printf("""
                                                    *=================================*
                                                    |             FACTURA             |
                                                    *---------------------------------*
                                                      - Descripción:     %s %s
                                                      - DNI:             %s
                                                      - Confir:          %s    
                                                      - Entrada:         %s         
                                                      - Salida:          %s         
                                                      - Pax:             %d         
                                                      - Noches:          %d         
                                                      - Tarifa:          %d €   
                                                      - Monto (sin IVA): %.2f € 
                                                      - IVA:             %.2f € 
                                                    *---------------------------------*
                                                      - TOTAL:           %.2f €  
                                                    *=================================*
                                                    """, nombreCliente, apellidoCliente, dniCliente, confirCliente, fechaEntrada,
                                                    fechaSalidaCheckout, pax, numNoches, precioPorNoche, totalSinIVA,
                                                    iva, totalConIVA);

                                            System.out.print("Pulse una tecla para continuar: ");
                                            s.nextLine();
                                            for (int i = 0; i < 40; i++) { //Limpieza
                                                System.out.println();
                                            }

                                            //Redondeamos para evitar fallos después en el cambio
                                            totalConIVA = (float) Math.round(totalConIVA * 100) / 100;
                                            System.out.printf("El total a pagar es %.2f euros\n", totalConIVA);

                                            do {
                                                System.out.printf("Introduzca importe necesario (%.2f euros): ", totalConIVA);
                                                dineroIntroducido = Float.parseFloat(s.nextLine());

                                                if (dineroIntroducido < totalConIVA) System.out.println("Importe insuficiente");
                                            }while (dineroIntroducido < totalConIVA);

                                            cambio = dineroIntroducido - totalConIVA;

                                            //Controlamos el caso de que se introduzca el importe exacto
                                            System.out.printf( (cambio != 0) ? "Su cambio es de %.2f euros.\n Usted recibirá:\n" : "Introdujo el importe exacto", cambio);

                                            //Calculamos el cambio. Billetes
                                            while (cambio >= 200 && billetes200 != 0) {
                                                billetes200Cambio++;
                                                billetes200--;
                                                cambio -= 200;
                                            }
                                            while (cambio >= 100 && billetes100 != 0) {
                                                billetes100Cambio++;
                                                billetes100--;
                                                cambio -= 100;
                                            }
                                            while (cambio >= 50 && billetes50 != 0) {
                                                billetes50Cambio++;
                                                billetes50--;
                                                cambio -= 50;
                                            }
                                            while (cambio >= 20 && billetes20 != 0) {
                                                billetes20Cambio++;
                                                billetes20--;
                                                cambio -= 20;
                                            }
                                            while (cambio >= 10 && billetes10 != 0) {
                                                billetes10Cambio++;
                                                billetes10--;
                                                cambio -= 10;
                                            }
                                            while (cambio >= 5 && billetes5 != 0) {
                                                billetes5Cambio++;
                                                billetes5--;
                                                cambio -= 5;
                                            }

                                            //Calculamos el cambio. Monedas
                                            while (cambio >= 2 && monedas2 != 0) {
                                                monedas2Cambio++;
                                                monedas2--;
                                                cambio -= 2;
                                            }
                                            while (cambio >= 1 && monedas1 != 0) {
                                                monedas1Cambio++;
                                                monedas1--;
                                                cambio -= 1;
                                            }
                                            while (cambio >= 0.50 && monedas050 != 0) {
                                                monedas050Cambio++;
                                                monedas050--;
                                                cambio -= 0.50f;
                                                cambio = (float) Math.round(cambio*100)/100;
                                            }
                                            while (cambio >= 0.20 && monedas020 != 0) {
                                                monedas020Cambio++;
                                                monedas020--;
                                                cambio -= 0.20f;
                                                cambio = (float) Math.round(cambio*100)/100;
                                            }
                                            while (cambio >= 0.10 && monedas010 != 0) {
                                                monedas010Cambio++;
                                                monedas010--;
                                                cambio -= 0.10f;
                                                cambio = (float) Math.round(cambio*100)/100;
                                            }
                                            while (cambio >= 0.05 && monedas005 != 0) {
                                                monedas005Cambio++;
                                                monedas005--;
                                                cambio -= 0.05f;
                                                cambio = (float) Math.round(cambio*100)/100;
                                            }
                                            while (cambio >= 0.0147 && monedas002 != 0) {
                                                monedas002Cambio++;
                                                monedas002--;
                                                cambio -= 0.02f;
                                                cambio = (float) Math.round(cambio*100)/100;
                                            }
                                            while (cambio > 0 && monedas001 != 0) {
                                                monedas001Cambio++;
                                                monedas001--;
                                                cambio -= 0.01f;
                                                cambio = (float) Math.round(cambio*100)/100;
                                            }

                                            //Mostramos los billetes y monedas que recibirá el cliente
                                            if (billetes200Cambio != 0) System.out.printf(" %d billetes de 200 euros\n", billetes200Cambio);
                                            if (billetes100Cambio != 0) System.out.printf(" %d billetes de 100 euros\n", billetes100Cambio);
                                            if (billetes50Cambio != 0) System.out.printf(" %d billetes de 50 euros\n", billetes50Cambio);
                                            if (billetes20Cambio != 0) System.out.printf(" %d billetes de 20 euros\n", billetes20Cambio);
                                            if (billetes10Cambio != 0) System.out.printf(" %d billetes de 10 euros\n", billetes10Cambio);
                                            if (billetes5Cambio != 0) System.out.printf(" %d billetes de 5 euros\n", billetes5Cambio);

                                            if (monedas2Cambio != 0) System.out.printf(" %d monedas de 2 euros\n", monedas2Cambio);
                                            if (monedas1Cambio != 0) System.out.printf(" %d monedas de 1 euro\n", monedas1Cambio);
                                            if (monedas050Cambio != 0) System.out.printf(" %d monedas de 50 céntimos\n", monedas050Cambio);
                                            if (monedas020Cambio != 0) System.out.printf(" %d monedas de 20 céntimos\n", monedas020Cambio);
                                            if (monedas010Cambio != 0) System.out.printf(" %d monedas de 10 céntimos\n", monedas010Cambio);
                                            if (monedas005Cambio != 0) System.out.printf(" %d monedas de 5 céntimos\n", monedas005Cambio);
                                            if (monedas002Cambio != 0) System.out.printf(" %d monedas de 2 céntimos\n", monedas002Cambio);
                                            if (monedas001Cambio != 0) System.out.printf(" %d monedas de 1 céntimo\n", monedas001Cambio);

                                            System.out.print("Pulse una tecla para continuar: ");
                                            s.nextLine();


                                            ingresosTotales += totalConIVA;
                                            reservasFinalizadas++;

                                            //Este switch sirve para reiniciar las variables de las habitaciones que ya están libres.
                                            switch (habitacionAsignada) {
                                                case 1 -> {
                                                    nombreHabitIndi1 = "";
                                                    apellidoHabitIndi1 = "";
                                                    dniHabitIndi1 = "";
                                                    confirHabitIndividual1 = "";
                                                }
                                                case 2 -> {
                                                    nombreHabitIndi2 = "";
                                                    apellidoHabitIndi2 = "";
                                                    dniHabitIndi2 = "";
                                                    confirHabitIndividual2 = "";
                                                }
                                                case 3 -> {
                                                    nombreHabitDoble1 = "";
                                                    apellidoHabitDoble1 = "";
                                                    dniHabitDoble1 = "";
                                                    confirHabitDoble1 = "";
                                                }
                                                case 4 -> {
                                                    nombreHabitDoble2 = "";
                                                    apellidoHabitDoble2 = "";
                                                    dniHabitDoble2 = "";
                                                    confirHabitDoble2 = "";
                                                }
                                                case 5 -> {
                                                    nombreHabitDoble3 = "";
                                                    apellidoHabitDoble3 = "";
                                                    dniHabitDoble3 = "";
                                                    confirHabitDoble3 = "";
                                                }
                                                case 6 -> {
                                                    nombreHabitDoble4 = "";
                                                    apellidoHabitDoble4 = "";
                                                    dniHabitDoble4 = "";
                                                    confirHabitDoble4 = "";;
                                                }
                                                case 7 -> {
                                                    nombreHabitDoble5 = "";
                                                    apellidoHabitDoble5 = "";
                                                    dniHabitDoble5 = "";
                                                    confirHabitDoble5 = "";
                                                }
                                                case 8 -> {
                                                    nombreHabitDoble6 = "";
                                                    apellidoHabitDoble6 = "";
                                                    dniHabitDoble6 = "";
                                                    confirHabitDoble6 = "";
                                                }
                                                case 9 -> {
                                                    nombreHabitDoble7 = "";
                                                    apellidoHabitDoble7 = "";
                                                    dniHabitDoble7 = "";
                                                    confirHabitDoble7 = "";
                                                }
                                                case 10 -> {
                                                    nombreHabitDoble8 = "";
                                                    apellidoHabitDoble8 = "";
                                                    dniHabitDoble8 = "";
                                                    confirHabitDoble8 = "";
                                                }
                                            }

                                            //Se suma el contador de habitaciones libres dependiendo el número de habitación
                                            if ((habitacionAsignada <= 2)) habitacionesIndividualesLibres++;
                                            else habitacionesDoblesLibres++;

                                            //Asignamos el valor a la variable que srive como bandera para salir del bucle
                                            checkoutRealizado = true;
                                        }
                                    }while(!checkoutRealizado);
                                }
                            }
                        } while (!habitacionSeleccionada);
                    }

                    for (int i = 0; i < 40; i++) { //Limpieza
                        System.out.println();
                    }
                    break;

                case 4:
                    intentosMenuAdmin = 3;
                    logueado = false;
                    apagarMaquina = false;

                    for (int i = 0; i < 40; i++) { //Limpieza
                        System.out.println();
                    }

                    do { //Menú de Administrador que solo se accede desde su usuario y contraseña correspondiente.
                        System.out.print("Introduce Usuario Administrador: ");
                        adminTeclado = s.nextLine();
                        System.out.print("Introduce Contraseña Administrador: ");
                        claveTeclado = s.nextLine();

                        if (!adminTeclado.equals(adminUser) || !claveTeclado.equals(adminPassword)) {
                            System.out.println("Hubo un error al introducir los datos, introducelos nuevamente.");
                            intentosMenuAdmin--;
                            System.out.println("Tienes " + intentosMenuAdmin + " intentos");
                            if (intentosMenuAdmin == 0){
                                System.out.println("Número de intentos agotado.");
                                System.out.print("Se volverá al menú principal");

                                for (int i = 0; i < 4; i++) { //Animación
                                    System.out.print(".");
                                    try {
                                        Thread.sleep(500);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                }

                                for (int i = 0; i < 40; i++) { //Limpieza
                                    System.out.println();
                                }
                            }
                        } else {
                            logueado = true;
                            System.out.print("Accediendo al menú de administrador");

                            for (int i = 0; i < 4; i++) { //Animación
                                System.out.print(".");
                                try {
                                    Thread.sleep(500);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }

                            for (int i = 0; i < 40; i++) { //Limpieza
                                System.out.println();
                            }
                            do {
                                System.out.print("""
                                        *======================================================================*
                                        |                         MENÚ DE ADMINISTRADOR                        |
                                        *----------------------------------------------------------------------*
                                        |                                                                      |
                                        |  1.- Consultar ingresos totales y el número de reservas finalizadas  |
                                        |  2.- Consultar las monedas restantes para el cambio                  |
                                        |  3.- Volver al menú principal                                         |
                                        |  4.- Apagar el software                                              |
                                        |                                                                      |
                                        *======================================================================*
                                        Selecciona una opción:\s""");
                                opcionMenuAdministrador = Integer.parseInt(s.nextLine());

                                switch (opcionMenuAdministrador) {
                                    case 1: //Mostramos el total de ingresos y reservas echas
                                        for (int i = 0; i < 40; i++) { //Limpieza
                                            System.out.println();
                                        }

                                        System.out.println("Ingresos Totales: " + ingresosTotales + "€");
                                        System.out.println("Número de reservas finalizadas: " + reservasFinalizadas);

                                        System.out.print("Pulse una tecla para continuar: ");
                                        s.nextLine();
                                        for (int i = 0; i < 40; i++) { //Limpieza
                                            System.out.println();
                                        }
                                        break;

                                    case 2: //Mostramos la cantidad de monedas y billetes que queda y damos la opción de rellenarlos.
                                        for (int i = 0; i < 40; i++) { //Limpieza
                                            System.out.println();
                                        }

                                        System.out.println("*=======================*");
                                        System.out.println("|  Recuento de billetes |");
                                        System.out.println("*-----------------------*");
                                        System.out.println("|  Billetes 200€ = " + billetes200 + "   |");
                                        System.out.println("|  Billetes 100€ = " + billetes100 + "   |");
                                        System.out.println("|  Billetes 50€ = " + billetes50 + "    |");
                                        System.out.println("|  Billetes 20€ = " + billetes20 + "    |");
                                        System.out.println("|  Billetes 10€ = " + billetes10 + "    |");
                                        System.out.println("|  Billetes 5€ = " + billetes5 + "     |");
                                        System.out.println("*=======================*");
                                        System.out.println("|  Recuento de monedas  |");
                                        System.out.println("*-----------------------*");
                                        System.out.println("|  Monedas 2€ = " + monedas2 + "      |");
                                        System.out.println("|  Monedas 1€ = " + monedas1 + "      |");
                                        System.out.println("|  Monedas 0.50€ = " + monedas050 + "   |");
                                        System.out.println("|  Monedas 0.20€ = " + monedas020 + "   |");
                                        System.out.println("|  Monedas 0.10€ = " + monedas010 + "   |");
                                        System.out.println("|  Monedas 0.05€ = " + monedas005 + "   |");
                                        System.out.println("|  Monedas 0.02€ = " + monedas002 + "   |");
                                        System.out.println("|  Monedas 0.01€ = " + monedas001 + "   |");
                                        System.out.println("*=======================*");

                                        System.out.print("Esta es la cantidad de dinero actualmente, " +
                                                "¿desea rellenarlo?(y/n): ");
                                        do {
                                            rellenaDinero = s.nextLine();

                                            switch (rellenaDinero) {
                                                case "y" -> {
                                                    System.out.println("Perfecto, procedo a rellenar las cantidades necesarias.");
                                                    System.out.print("Rellenando");

                                                    for (int i = 0; i < 4; i++) { //Animación
                                                        System.out.print(".");
                                                        try {
                                                            Thread.sleep(500);
                                                        } catch (InterruptedException e) {
                                                            e.printStackTrace();
                                                        }
                                                    }

                                                    for (int i = 0; i < 40; i++) { //Limpieza
                                                        System.out.println();
                                                    }

                                                    billetes200 = 20;
                                                    billetes100 = 20;
                                                    billetes50 = 20;
                                                    billetes20 = 20;
                                                    billetes10 = 20;
                                                    billetes5 = 20;
                                                    monedas2 = 50;
                                                    monedas1 = 50;
                                                    monedas050 = 50;
                                                    monedas020 = 50;
                                                    monedas010 = 50;
                                                    monedas005 = 50;
                                                    monedas002 = 50;
                                                    monedas001 = 50;
                                                }

                                                case "n" -> {
                                                    System.out.println("No se realizará ningún cambio en el recuento.");

                                                    System.out.print("Pulse una tecla para continuar: ");
                                                    s.nextLine();
                                                    for (int i = 0; i < 40; i++) { //Limpieza
                                                        System.out.println();
                                                    }
                                                }

                                                default -> System.out.print("Opción no válida, vuelva a intentarlo (y/n): ");
                                            }
                                        }while(!rellenaDinero.equals("y") && !rellenaDinero.equals("n"));
                                        break;

                                    case 3: //Volver al menú principal
                                        System.out.print("Volviendo al menú principal");
                                        for (int i = 0; i < 4; i++) { //Animación
                                            System.out.print(".");
                                            try {
                                                Thread.sleep(500);
                                            } catch (InterruptedException e) {
                                                e.printStackTrace();
                                            }

                                        }
                                        for (int i = 0; i < 40; i++) { //Limpieza
                                            System.out.println();
                                        }
                                        break;

                                    case 4: //Apagar programa
                                        apagarMaquina = true;
                                        System.out.println("Gracias por usar nuestro programa. Hasta la próxima.");
                                        System.out.println();
                                        System.out.print("Apagando sistema");
                                        for (int i = 0; i < 4; i++) { //Animación
                                            System.out.print(".");
                                            try {
                                                Thread.sleep(500);
                                            } catch (InterruptedException e) {
                                                e.printStackTrace();
                                            }

                                        }
                                        for (int i = 0; i < 40; i++) { //Limpieza
                                            System.out.println();
                                        }
                                        break;

                                    default:
                                        System.out.println("No es una opción válida.");
                                        System.out.print("Pulse una tecla para continuar: ");
                                        s.nextLine();
                                        for (int i = 0; i < 40; i++) { //Limpieza
                                            System.out.println();
                                        }
                                }
                            } while (opcionMenuAdministrador != 3 && opcionMenuAdministrador != 4);
                        }

                    } while (intentosMenuAdmin != 0 && !logueado);
                    break;

                default:
                    System.out.println("Opción no válida");
                    System.out.print("Pulse una tecla para continuar: ");
                    s.nextLine();
                    for (int i = 0; i < 40; i++) { //Limpieza
                        System.out.println();
                    }
            }
        } while (!apagarMaquina);
    }
}
