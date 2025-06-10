package gui;

import java.util.List;

import javax.swing.SwingUtilities;

import model.CHaracter;
import controller.*;
import exception.EmptyListException;
import util.ListArray;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Mostrar splash screen primero
            SplashScreen splash = new SplashScreen();
            splash.showSplash(5000, () -> {
                splash.dispose();
                
                // Mostrar pantalla de configuración
                ConfigScreen config = new ConfigScreen();
                config.showConfig(() -> {
                    config.dispose();
                    
//                    int rows = config.getMapRows();
//                    int cols = config.getMapColumns();
                    ListArray<CHaracter> selected = config.getSelectedCharacters();
                    
//                    System.out.println("Configuración del mapa: " + rows + "x" + cols);
                    System.out.println("Personajes seleccionados:");
                    selected.forEach(c -> System.out.println("- " + c.getName()));
                    try {
						Game.getInstance(Game.class).createNewGame(selected);
						// Iniciar juego a pantalla completa
	                    GameScreen game = new GameScreen();
	                    game.startGame();
					} catch (IndexOutOfBoundsException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (EmptyListException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                    
                });
            });
        });
    }
}