package com.bdd.mer;

import com.bdd.mer.interfaz.LoadingScreen;
import com.bdd.mer.interfaz.MarcoPrincipal;
import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import java.awt.*;

public class DiagramaERApp {
    public static void main(String[] args) {
        // Mostrar la ventana de carga
        LoadingScreen loadingScreen = new LoadingScreen();

        // Crear un SwingWorker para realizar la inicialización en segundo plano
        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                // Simular la inicialización de la aplicación (puedes realizar la carga real aquí)
                Thread.sleep(3000); // Simula la carga con un retardo
                return null;
            }

            @Override
            protected void done() {
                // Cerrar la ventana de carga
                loadingScreen.dispose();

                // Mostrar el frame principal después de que la carga esté completa
                SwingUtilities.invokeLater(() -> {
                    try {
                        UIManager.setLookAndFeel(new FlatLightLaf());
                    } catch (Exception e) {
                        // Si FlatLightLaf no está disponible, puedes configurar otro look and feel.
                    }
                    MarcoPrincipal frame = new MarcoPrincipal();
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.setVisible(true);
                });
            }
        };

        // Ejecutar el SwingWorker
        worker.execute();
    }
}