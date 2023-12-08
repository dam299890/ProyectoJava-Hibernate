package com.example.servicio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.example.modelo.Informacion;
import org.apache.poi.xwpf.usermodel.*;

public class PlantillasService {

    public List<String> getPlantillas(){

        List<String> plantillas = new ArrayList<>();

        ClassLoader classLoader = getClass().getClassLoader();

        try {
            // Especifica la ruta de la carpeta de recursos que contiene las plantillas
            String carpetaRecursos = "com.example.documents"; // Por ejemplo, "recursos/plantillas"

            // Utiliza el ClassLoader para cargar recursos desde el classpath
            Path carpetaRecursosPath = Paths.get(classLoader.getResource(carpetaRecursos).toURI());
            System.out.println(carpetaRecursosPath);
            // Utiliza Files.walk para recorrer todos los archivos en la carpeta de recursos
            try (Stream<Path> paths = Files.walk(carpetaRecursosPath)) {
                plantillas = paths
                        .filter(Files::isRegularFile)
                        .map(Path::getFileName)
                        .map(Path::toString)
                        .filter(nombreArchivo -> nombreArchivo.endsWith(".docx")) // Filtra por archivos .docx
                        .collect(Collectors.toList());
            }

        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }

        return plantillas;




    }


    public static void generarDocumentoReferencia(Informacion informacion, String plantilla) throws IOException {

        String pathPlantilla = "src/main/resources/"+ plantilla;

        FileInputStream fis = new FileInputStream(pathPlantilla);
        XWPFDocument document = new XWPFDocument(fis);

        List<XWPFTable> tables = document.getTables();


    }

    public static void agregarDatoATabla( List<XWPFTable> tables, int numTabla, int row, int cell,String contenido) throws FileNotFoundException {

        if (!tables.isEmpty()) {
            XWPFTable table = tables.get(numTabla); // Obtén la primera tabla (puedes ajustar el índice según tus necesidades)

            int rowIndex = row; // Índice de la fila a modificar
            int cellIndex = cell; // Índice de la celda a modificar

            XWPFTableRow fila = table.getRow(rowIndex);
            XWPFTableCell celda = fila.getCell(cellIndex);

            // Establece el nuevo contenido de la celda
            celda.setText(contenido);

        }

    }
}
