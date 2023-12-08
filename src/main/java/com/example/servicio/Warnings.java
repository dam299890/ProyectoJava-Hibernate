package com.example.servicio;

import javafx.scene.control.Alert;

public class Warnings {

    public static void mostrarAlertWarning(String msg) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText("Alerta");
        alert.setTitle("Warning");
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
