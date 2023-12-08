package com.example.controlador;

import com.example.modelo.ConexionBD;
import com.example.modelo.Informacion;
import com.example.modelo.Referencia;
import com.example.servicio.InformacionService;
import com.example.servicio.PlantillasService;
import com.example.servicio.ReferenciaService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import static com.example.servicio.PlantillasService.generarDocumentoReferencia;
import static com.example.servicio.Warnings.mostrarAlertWarning;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HelloController {

    @FXML
    private ComboBox<String> menuPlantillas;

    @FXML
    private TableColumn<Informacion, String> columAlcance;

    @FXML
    private TableColumn<Informacion, Double> columHR;

    @FXML
    private TableColumn<Informacion, String> columNInforme;

    @FXML
    private TableColumn<Informacion, Double> columRMuestra;

    @FXML
    private TableColumn<Informacion, Double> columResiduoBlanco;

    @FXML
    private TableColumn<Informacion, Double> columResultadoBlanco;

    @FXML
    private TableColumn<Informacion, String> columStatusBlanco;

    @FXML
    private TableColumn<Informacion, String> columStatusMuestra;

    @FXML
    private TableColumn<Informacion, Double> columTemp;

    @FXML
    private TableColumn<Informacion,String> columFecha;

    @FXML
    private TableColumn<Informacion, Double> columPesoFin;

    @FXML
    private TableColumn<Informacion, Double> columPesoIni;

    @FXML
    private TableColumn<Informacion,String> columRef;

    @FXML
    private TableColumn<Informacion,String> columStatus;

    @FXML
    private TableColumn<Informacion, Double> columDif;

    @FXML
    private TableColumn<Informacion,String> columObs;


    @FXML
    private Button generarDocButton;

    @FXML
    private GridPane gpIngresoRef;

    @FXML
    private GridPane gpIngresoRef2;

    @FXML
    private Button ingresarDatos;

    @FXML
    private Button ingresarRef;

    @FXML
    private Label labelObs;

    @FXML
    private MenuButton menuRef;

    @FXML
    private ComboBox<String> menuRefToDocument;

    @FXML
    private Pane panelGenerarDoc;

    @FXML
    private Pane panelIngresarRef;

    @FXML
    private Pane panelVisualizarRef;

    @FXML
    private MenuItem ref3;

    @FXML
    private TableView<Informacion> tableRefs;

    @FXML
    private TextField txtAlcance;

    @FXML
    private TextArea txtComents;

    @FXML
    private TextField txtEstadoBlanco;

    @FXML
    private TextField txtEstadoMuestra;

    @FXML
    private TextField txtHumedad;

    @FXML
    private TextField txtNuevoInf;

    @FXML
    private TextField txtPesoFi;

    @FXML
    private TextField txtPesoIni;

    @FXML
    private TextField txtRef;

    @FXML
    private TextField txtResiduoBlanco;

    @FXML
    private TextField txtResultBlanco;

    @FXML
    private TextField txtResultMuestra;

    @FXML
    private TextField txtTemp;

    @FXML
    private Button visualizarRef;

    @FXML
    private Font x1;

    @FXML
    private Color x2;

    @FXML
    private Font x3;

    @FXML
    private Color x4;

    @FXML
    void vistaGenerarDoc(ActionEvent event) {
        System.out.println("A generar Doc");
        panelIngresarRef.setVisible(false);
        panelGenerarDoc.setVisible(true);
        panelVisualizarRef.setVisible(false);

        menuPlantillas.getItems().clear();

        PlantillasService plantillasService = new PlantillasService();
        List<String> plantillas = plantillasService.getPlantillas();

        for (String plantilla : plantillas) {
            MenuItem menuItem = new MenuItem(plantilla);

            menuPlantillas.getItems().addAll(plantillas);


        }
        menuRefToDocument.getItems().clear();

        InformacionService informacionService = new InformacionService();
        List<Object[]> referenciasYFechas = informacionService.getReferenciaYFechaInformacion();

        for (Object[] result : referenciasYFechas) {


            System.out.println();;
            String referencia = (String) result[1];
            LocalDate fechaCreacion = (LocalDate) result[2];
            String referenciaYFecha= result[0] +" " +referencia +" "+ fechaCreacion;
            // Crea un MenuItem con el texto de referencia.getReferencia()
            // Agrega el código necesario para manejar el evento de clic en el MenuItem, si es necesario
            menuRefToDocument.getItems().addAll(referenciaYFecha);

        }

    }

    @FXML
    void generarDoc(ActionEvent event) throws FileNotFoundException {

        String[] valorComboBoxMenuRef = menuRefToDocument.getValue().split(" ");
        int idReferencia = Integer.parseInt(valorComboBoxMenuRef[0]);
        System.out.println(idReferencia);

        InformacionService informacionService = new InformacionService();
        List<Informacion> results = informacionService.getInformacion(idReferencia);
        Informacion informacion = results.get(0);
        System.out.println("Informacion de combo box");

        for (Informacion result : results
        ) {
            System.out.println(result.getAlcanceExtraccion());
        }


        String valorComboBoxMenuPlantillas = menuPlantillas.getValue();
        System.out.println(valorComboBoxMenuPlantillas);

        FileInputStream fis = new FileInputStream("src/main/resources/com.example.documents/"+valorComboBoxMenuPlantillas);
        try {
            XWPFDocument document = new XWPFDocument(fis);

            List<XWPFTable> tables = document.getTables();
            for (XWPFTable table : tables) {
                List<XWPFTableRow> rows = table.getRows();
                for (XWPFTableRow row : rows) {
                    List<XWPFTableCell> cells = row.getTableCells();
                    for (XWPFTableCell cell : cells) {
                        System.out.println(cell.getText());
                    }
                }
            }
            //referencia
            agregarDatoATabla(tables,0,1,1,informacion.getReferencia() );
            //punto muestreo
            agregarDatoATabla(tables,0,2,1,informacion.getEstadoMuestra() );
            //temperatura
            agregarDatoATabla(tables,1,0,1,String.valueOf(informacion.getTemperatura()) );
            //humedad
            agregarDatoATabla(tables,1,0,2,String.valueOf(informacion.getHumedad()+"%"));
            //fecha analisis
            agregarDatoATabla(tables,2,0,1, String.valueOf(informacion.getFecha()));
            //alcance extraccion
            agregarDatoATabla(tables,2,1,1,informacion.getAlcanceExtraccion());
            //informacion adicional
            agregarDatoATabla(tables,2,2,1,informacion.getEstado());
            //resultado muestra
            agregarDatoATabla(tables,3,4,1, String.valueOf(informacion.getResultadoMuestra()));
            //residuo blanco
            agregarDatoATabla(tables,3,4,2, String.valueOf(informacion.getResiduoBlanco()));
            //resultado blanco
            agregarDatoATabla(tables,3,4,3, String.valueOf(informacion.getResultadoBlanco()));
            //estado muestra
            agregarDatoATabla(tables,3,4,1, informacion.getEstadoMuestra());
            //
            agregarDatoATabla(tables,3,4,1, informacion.getEstadoBlanco());





            FileOutputStream fos = new FileOutputStream("src/main/resources/Word_Modificado2.docx");
            document.write(fos);
            fos.close();


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        /*InformacionService informacionService = new InformacionService();
        List<Informacion> informacion = informacionService.getInformacion(menuRefToDocument.getValue());

        System.out.println(informacion);
        try {

            generarDocumentoReferencia(, menuPlantillas.getValue());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }*/

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



    @FXML
    void ingresarRef(ActionEvent event) {
        System.out.println("click ingresar Referencia");

        panelIngresarRef.setVisible(true);
        panelGenerarDoc.setVisible(false);
        panelVisualizarRef.setVisible(false);


    }

    @FXML
    void visualizarRef(ActionEvent event) {
        System.out.println("click visualizar referencia");
        panelIngresarRef.setVisible(false);
        panelGenerarDoc.setVisible(false);
        panelVisualizarRef.setVisible(true);

        menuRef.getItems().clear();

        ReferenciaService referenciaService = new ReferenciaService();
        List<Referencia> referenciasBD = referenciaService.getRerefencias();

        for (Referencia referencia : referenciasBD) {
            MenuItem menuItem = new MenuItem(referencia.getReferencia());
            // Crea un MenuItem con el texto de referencia.getReferencia()
            // Agrega el código necesario para manejar el evento de clic en el MenuItem, si es necesario
            menuRef.getItems().add(menuItem);
            menuItem.setOnAction(e -> visualizarListaReferencias(referencia.getReferencia()));
        }


    }


    @FXML
    void ingresarDatos(ActionEvent event) {
        String referencia = txtRef.getText();
        String observacion = txtComents.getText();
        String pesoInicialText = txtPesoIni.getText();
        String pesoFinalText = txtPesoFi.getText();
        String numeroInforme = txtNuevoInf.getText();
        String temperaturaText = txtTemp.getText();
        String humedadText = txtHumedad.getText();
        String alcanceExtraccion =  txtAlcance.getText();
        String resutadoMuestraText = txtResultMuestra.getText();
        String residuoBlancoText = txtResiduoBlanco.getText();
        String resultadoBlancoText = txtResultBlanco.getText();
        String estadoMuestra = txtEstadoMuestra.getText();
        String estadoBlanco = txtEstadoBlanco.getText();


        Session mySesion = ConexionBD.getConexion();

        if (referencia.isEmpty()) {
            mostrarAlertWarning("Ingrese referencia");
            return;

        }

        try {
            double pesoInicial = Double.parseDouble(pesoInicialText);
            double pesoFinal = Double.parseDouble(pesoFinalText);
            double temperatura = Double.parseDouble(temperaturaText);
            double humedad = Double.parseDouble(humedadText);
            double resultadoMuestra = Double.parseDouble(resutadoMuestraText);
            double residuoBlanco = Double.parseDouble(residuoBlancoText);
            double resultadoBlanco = Double.parseDouble(resultadoBlancoText);

            // Realizar validaciones adicionales si es necesario,
            // por ejemplo, asegurarse de que los valores de peso sean válidos

            Informacion info = new Informacion(pesoInicial, pesoFinal,
                                                referencia, "bien", observacion,
                                                numeroInforme, temperatura, humedad,
                                                alcanceExtraccion, resultadoMuestra,
                                                residuoBlanco, resultadoBlanco,
                                                estadoMuestra, estadoBlanco);

            mySesion.beginTransaction();
            mySesion.save(info);
            mySesion.getTransaction().commit();
            System.out.println("Registro insertado correctamente");
        } catch (NumberFormatException e) {
            mostrarAlertWarning("Ingrese valores numéricos válidos en los campos de peso");
        } finally {
            mySesion.close();
            System.out.println("Fin del insert");
        }
    }




    void visualizarListaReferencias(String referencia) {

        tableRefs.getItems().clear();

        Session mySesion = ConexionBD.getConexion();

        List<Informacion> results;

        try {
            Informacion info = new Informacion();

            mySesion.beginTransaction();

            String hql = "FROM Informacion WHERE referencia = :referencia";
            Query query = mySesion.createQuery(hql);
            query.setParameter("referencia", referencia);
            results = query.list();

            for (Object result : results
            ) {
                System.out.println(result);
            }


            mySesion.getTransaction().commit();

            System.out.println("visualizar lista referencias 2");

            mySesion.close();
        } finally {
            mySesion.close();
        }

        columFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        columPesoIni.setCellValueFactory(new PropertyValueFactory<>("pesoIni"));
        columPesoFin.setCellValueFactory(new PropertyValueFactory<>("pesoFin"));
        columDif.setCellValueFactory(new PropertyValueFactory<>("diferenciaPeso"));
        columRef.setCellValueFactory(new PropertyValueFactory<>("referencia"));
        columStatus.setCellValueFactory(new PropertyValueFactory<>("estado"));
        columObs.setCellValueFactory(new PropertyValueFactory<>("observacion"));
        columNInforme.setCellValueFactory(new PropertyValueFactory<>("numeroInforme"));
        columTemp.setCellValueFactory(new PropertyValueFactory<>("temperatura"));
        columHR.setCellValueFactory(new PropertyValueFactory<>("humedad"));
        columAlcance.setCellValueFactory(new PropertyValueFactory<>("alcanceExtraccion"));
        columRMuestra.setCellValueFactory(new PropertyValueFactory<>("resultadoMuestra"));
        columResiduoBlanco.setCellValueFactory(new PropertyValueFactory<>("residuoBlanco"));
        columResultadoBlanco.setCellValueFactory(new PropertyValueFactory<>("resultadoBlanco"));
        columStatusMuestra.setCellValueFactory(new PropertyValueFactory<>("estadoMuestra"));
        columStatusBlanco.setCellValueFactory(new PropertyValueFactory<>("estadoBlanco"));


        tableRefs.getItems().addAll(results);


    }



}