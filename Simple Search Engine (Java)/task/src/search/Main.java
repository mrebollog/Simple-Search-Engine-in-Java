package search;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        String fileName = buscarNombreArchivo(args);
        if (fileName.isEmpty()){
            System.out.println("No se recibió nombre de archivo");
            return;
        }
        try {
            DatosIndexados datos = cargarDatos(fileName);
            Scanner scanner = new Scanner(System.in);
            mostrarMenu(scanner, datos.getLineasOriginales(), datos.getIndiceInvertido());
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error al abrir el archivo");

        }
    }

    static String buscarNombreArchivo(String[] args){
        for (int i = 0; i < args.length - 1; i++){
            if ("--data".equals(args[i])){
                return args[i + 1];
            }
        }
        return "";
    }

    static DatosIndexados cargarDatos(String fileName) throws FileNotFoundException {
        List<String> lineasOriginales = new ArrayList<>();
        Map<String, Set<Integer>> indiceInvertido = new HashMap<>();
        File file = new File(fileName);
        Scanner scanner = new Scanner(file);
        int numeroLinea = 0;

        while (scanner.hasNextLine()) {
            String linea = scanner.nextLine();
            lineasOriginales.add(linea);
            String[] palabras = linea.toLowerCase().trim().split(" ");

            for (String palabra : palabras) {
                indiceInvertido.putIfAbsent(palabra, new LinkedHashSet<>());
                indiceInvertido.get(palabra).add(numeroLinea);

            }
            numeroLinea++;
        }

        scanner.close();
        return new DatosIndexados(indiceInvertido, lineasOriginales);
    }

    static void mostrarMenu(Scanner scanner, List<String> lineas, Map<String, Set<Integer>> indiceInvertido) {
        boolean finish = false;
        while (!finish) {
            System.out.println("\n=== Menu ===");
            System.out.println("1. Search information.");
            System.out.println("2. Print all data.");
            System.out.println("0. Exit.");

            int opcion;
            try {
                opcion = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a number.");
                scanner.nextLine();
                continue;
            }


            switch (opcion) {
                case 1:
                    buscarInformacion(scanner, lineas, indiceInvertido);
                    break;
                case 2: imprimirDatos(lineas); break;
                case 0:
                    finish = true;
                    System.out.println("Bye!");
                    break;
                default: System.out.println("Incorrect option! Try again."); break;
            }
        }
        scanner.close();
    }

    static void buscarInformacion(Scanner scanner, List<String> lineas, Map<String, Set<Integer>> indiceInvertido) {
        System.out.println("Select a matching strategy: ALL, ANY, NONE");
        String searchingStrategy = scanner.nextLine().trim().toUpperCase();

        SearchStrategy strategy;
        try {
            strategy = StrategyFactory.getStrategy(searchingStrategy);
        } catch (IllegalArgumentException e) {
            System.out.println("Please enter a valid strategy");
            return;
        }

        System.out.println("Enter information to search:");
        String[] consulta = scanner.nextLine().toLowerCase().trim().split(" ");
        Set<Integer> resultados = strategy.search(consulta, indiceInvertido, lineas);

        if (resultados.isEmpty()) {
            System.out.println("No matching information found.");
        } else {
            for (Integer linea : resultados) {
                System.out.println(lineas.get(linea));
            }
        }
    }

    static void imprimirDatos(List<String> lineas) {
        System.out.println("All data:");
        for (String linea : lineas) {
            System.out.println(linea);
        }
    }

}
