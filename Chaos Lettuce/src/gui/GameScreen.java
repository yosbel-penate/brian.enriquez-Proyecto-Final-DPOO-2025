package gui;

import javax.swing.*;

import exception.EmptyListException;
import gui.components.GamePanel;
import gui.components.InfoPanel;
import model.puzzle.*;
import model.*;


import java.awt.*;
import java.awt.event.*;

import static javax.swing.SwingConstants.CENTER;

import  controller.*;

public class GameScreen extends JFrame {
    private boolean running = true;
    private GamePanel gamePanel;
    private InfoPanel infoPanel;
    private Game game;
    
    public GameScreen() {
        setTitle("Mi Juego");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Configurar pantalla completa
        setUndecorated(true);
        GraphicsEnvironment.getLocalGraphicsEnvironment()
            .getDefaultScreenDevice()
            .setFullScreenWindow(this);
        
        // Crear los paneles
        gamePanel = new GamePanel();
        infoPanel = new InfoPanel();
        
        // Configurar JSplitPane para dividir la pantalla
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, gamePanel, infoPanel);
        splitPane.setDividerSize(5); // Grosor del divisor
        splitPane.setResizeWeight(11.0/12.0); // Proporción 11/12 para el primer panel
        
        // Deshabilitar el redimensionamiento manual
        splitPane.setEnabled(false);
        
        add(splitPane);
        
        // Control del juego
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    running = false;
                    dispose();
                }
                // Aquí puedes añadir más controles
            }
            
            @Override
            public void keyReleased(KeyEvent e) {}
        });

        game = Game.getInstance(Game.class);
    }
    
    public void startGame() {
        setVisible(true);
        
        // Bucle principal del juego
        new Thread(() -> {
            while (running) {
                try {
                    Thread.sleep(16); // ~60 FPS
                    game.checkStatusGame();
                    if(game.getStatus()==GameStatus.WIN_ENEMY)
                    {
                        JOptionPane.showMessageDialog(gamePanel, "Lástima, perdiste");
                        this.running = false;
                    } else if (game.isActivatePuzzle()) {
                        Element e = game.getPuzzles().get(0);
                        if(e instanceof  Puzzle){
                            Puzzle p = (Puzzle)e;
                            showPuzzle(p);
                            this.running = false;
                        }
                    } else{
                        gamePanel.repaint();
                        infoPanel.updateInfo();
                        infoPanel.repaint();
                    }

				} catch (IndexOutOfBoundsException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (EmptyListException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InterruptedException e) {
                    e.printStackTrace();
                }
                
            }
        }).start();
    }

    private void showPuzzle(Puzzle p) {
        if(p instanceof  PuzzleTrueFalse){
            PuzzleTrueFalse ptf = (PuzzleTrueFalse) p;
            showPuzzleTrueFalse(ptf);
        }
    }

    private void showPuzzleTrueFalse(PuzzleTrueFalse ptf) {
        // Crear el diálogo modal (esto evita que la aplicación se minimice)
        JDialog dialog = new JDialog(this, "Seleccione una opción");
        dialog.setLayout(new GridLayout(3, 1));
        dialog.setSize(800, 200);
        dialog.setLocationRelativeTo(this);

        // Evitar que el diálogo pierda el foco
        dialog.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);

        // Añadir pregunta
        JLabel pregunta = new JLabel(ptf.getQuestion(), SwingConstants.CENTER);
        dialog.add(pregunta);

        // Añadir etiquetas para las opciones (que funcionarán como botones)
        JLabel lblVerdadero = new JLabel("VERDADERO", CENTER);
        JLabel lblFalso = new JLabel("FALSO", CENTER);


        // Estilo para que parezcan botones
        lblVerdadero.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        lblVerdadero.setOpaque(true);
        lblVerdadero.setBackground(Color.LIGHT_GRAY);
        lblVerdadero.setForeground(Color.BLUE);

        lblFalso.setBorder(BorderFactory.createLineBorder(Color.RED));
        lblFalso.setOpaque(true);
        lblFalso.setBackground(Color.LIGHT_GRAY);
        lblFalso.setForeground(Color.RED);

        // Añadir listeners para que funcionen como botones
        lblVerdadero.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dialog.dispose();
                if(ptf.isAnswer()){
                    JOptionPane.showMessageDialog(dialog, "Felicidades, ganaste");
                    game.setStatus(GameStatus.WIN_HERO);
                }else{
                    JOptionPane.showMessageDialog(dialog, "Lástima, perdiste");
                    game.setStatus(GameStatus.WIN_ENEMY);
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                lblVerdadero.setBackground(Color.GRAY);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                lblVerdadero.setBackground(Color.LIGHT_GRAY);
            }
        });

        lblFalso.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dialog.dispose();
                if(!ptf.isAnswer()){
                    JOptionPane.showMessageDialog(dialog, "Felicidades, ganaste");
                    game.setStatus(GameStatus.WIN_HERO);
                }else{
                    JOptionPane.showMessageDialog(dialog, "Lástima, perdiste");
                    game.setStatus(GameStatus.WIN_ENEMY);
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                lblFalso.setBackground(Color.GRAY);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                lblFalso.setBackground(Color.LIGHT_GRAY);
            }
        });

        // Panel para las opciones
        JPanel panelOpciones = new JPanel(new GridLayout(1, 2));
        panelOpciones.add(lblVerdadero);
        panelOpciones.add(lblFalso);

        dialog.add(panelOpciones);
        dialog.setVisible(true);

    }
}
