package gui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import javax.swing.SwingUtilities;

import model.CHaracter;
import controller.*;
import exception.EmptyListException;
import model.question.Question;
import model.question.QuestionTrueOrFalse;
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
                        ListArray<Question> questions = loadQuestionFromFile();
                        questions.forEach(c -> System.out.println("- " + c));
						Game.getInstance(Game.class).createNewGame(selected,questions);
						// Iniciar juego a pantalla completa
	                    GameScreen game = new GameScreen();
	                    game.startGame();
					} catch (IndexOutOfBoundsException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (EmptyListException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e){
                        e.printStackTrace();
                    }
                    
                });
            });
        });
    }

    public static ListArray<Question> loadQuestionFromFile() throws IOException{
        ListArray<Question> questions =new ListArray<>();
        loadQuestionTrueOrFalseFromFile("/resource/questions/true_false.txt",questions);
        return questions;
    }

    private static void loadQuestionTrueOrFalseFromFile(String filename, ListArray<Question> questions) throws IOException {
        InputStream inputStream = Main.class.getResourceAsStream(filename);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line ;
        while((line = bufferedReader.readLine())!=null){
            String [] args = line.split("@=@");
            String statement = args[0];
            boolean answer = ( args[1].compareToIgnoreCase("false")==0? false: true );
            QuestionTrueOrFalse qtf = new QuestionTrueOrFalse(statement,answer);
            questions.add(qtf);
        }
    }
}
