package gui.components;

import javax.swing.*;

import controller.ActionPlayer;
import controller.Game;
import exception.EmptyListException;
import model.CHaracter;
import model.Element;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class InfoPanel extends JPanel {
    private int score = 0;
    private int level = 1;
	private JPanel panelBotones;
    private JPanel panelResetAction;
    private JButton btnResetAction;
    private JButton btnMove;
	private JButton btnSkill;
	private JButton btnAttack;
	private JPanel playerPanel;
	private JLabel lblImagenPlayer;
	private JLabel lblNamePlayer;
	private JLabel lblLifePlayer;
	private JLabel lblDescriptionPlayer;
	private JLabel lblTurnPlayer;
    private JLabel  lblAttackPlayer;
    private JLabel  lblSkillPlayer;
    public InfoPanel() {
        setBackground(new Color(240, 240, 240));
        setLayout(new BorderLayout());
        
        // Título del panel de información
        JLabel title = new JLabel("INFORMACIÓN DEL JUEGO", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 18));
        title.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        add(title, BorderLayout.NORTH);
        
        // Panel con la información
        JPanel infoContent = new JPanel();
        infoContent.setLayout(new BoxLayout(infoContent, BoxLayout.Y_AXIS));
        infoContent.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        
        // Componentes de información
       
        playerPanel = new JPanel();
        playerPanel.setLayout(new BoxLayout(playerPanel, BoxLayout.X_AXIS));
        playerPanel.setBorder(BorderFactory.createTitledBorder("Jugador en turno"));
        
        // Crear imagen de ejemplo (BufferedImage)
        BufferedImage imagen = createImageExample(80,80);
        
        // Panel para la imagen (izquierda)
        JPanel imagenPanel = new JPanel(new BorderLayout());
        lblImagenPlayer = new JLabel(new ImageIcon(imagen));
        imagenPanel.add(lblImagenPlayer, BorderLayout.CENTER);
        imagenPanel.add(Box.createHorizontalStrut(2), BorderLayout.EAST); // Espacio entre imagen y etiquetas
        
        // Panel para las etiquetas (derecha)
        JPanel etiquetasPanel = new JPanel();
        etiquetasPanel.setLayout(new BoxLayout(etiquetasPanel, BoxLayout.Y_AXIS));
        
        
        // Crear etiquetas
        lblNamePlayer = createLabel("Lechugas del Caos", Font.BOLD, 16, new Color(0, 100, 0));
        lblLifePlayer = createLabel("El Jardín de los Enigmas", Font.BOLD, 14, Color.BLUE);
        lblAttackPlayer = createLabel("El Jardín de los Enigmas", Font.BOLD, 14, Color.BLUE);
        lblTurnPlayer = createLabel("El Jardín de los Enigmas", Font.BOLD, 12, Color.BLACK);
        lblSkillPlayer = createLabel("El Jardín de los Enigmas", Font.BOLD, 14, Color.BLUE);


        // Añadir etiquetas con espacios
        etiquetasPanel.add(Box.createVerticalStrut(2));
        etiquetasPanel.add(lblNamePlayer);
        etiquetasPanel.add(Box.createVerticalStrut(2));
        etiquetasPanel.add(lblLifePlayer);
        etiquetasPanel.add(Box.createVerticalStrut(2));
        etiquetasPanel.add(lblAttackPlayer);
        etiquetasPanel.add(Box.createVerticalStrut(2));
        etiquetasPanel.add(lblSkillPlayer);
        etiquetasPanel.add(Box.createVerticalStrut(2));
        etiquetasPanel.add(lblTurnPlayer);
        // Para centrar verticalmente
        
        // Añadir componentes al panel de contenido
        playerPanel.add(imagenPanel);
        playerPanel.add(etiquetasPanel);
        Dimension dimension = playerPanel.getSize();
        dimension.setSize(380,100);
        playerPanel.setMinimumSize(dimension);
        playerPanel.setMaximumSize(dimension);
        
        // Crear el panel de botones
        panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(1, 3, 10, 0)); // 1 fila, 3 columnas, espacio horizontal 10px
        panelBotones.setMaximumSize(new Dimension(380, 24));
        panelBotones.setMinimumSize(new Dimension(380, 24));
        btnMove = createButton("Mover", new Color(0, 120, 215));
        btnSkill = createButton("Habilidad", new Color(255, 204, 0));
        btnAttack = createButton("Atacar", new Color(220, 60, 50));

        panelResetAction = new JPanel();
        panelResetAction.setLayout(new GridLayout(1, 3, 10, 0)); // 1 fila, 3 columnas, espacio horizontal 10px
        panelResetAction.setMaximumSize(new Dimension(380, 24));
        panelResetAction.setMinimumSize(new Dimension(380, 24));
        btnResetAction = createButton("Cambiar accion", new Color(127, 127, 127));

        Game game = Game.getInstance(Game.class);
        
        btnMove.addActionListener(e ->{
        	game.setCurrentAction(ActionPlayer.MOVE);
        });
        
        btnAttack.addActionListener(e ->{
        	game.setCurrentAction(ActionPlayer.ATTACK);
        });
        
        btnSkill.addActionListener(e ->{
        	game.setCurrentAction(ActionPlayer.SKILL);
        });

        btnResetAction.addActionListener(e ->{
            game.setCurrentAction(ActionPlayer.UNKWON);
            btnResetAction.setVisible(false);
            panelResetAction.setVisible(false);
        });
        
        // Añadir botones al panel
        panelBotones.add(btnMove);
        panelBotones.add(btnSkill);
        panelBotones.add(btnAttack);
        panelBotones.setVisible(false);
        panelResetAction.add(btnResetAction);
        panelResetAction.setVisible(false);
        btnAttack.setVisible(false);
        btnMove.setVisible(false);
        btnSkill.setVisible(false);
        btnResetAction.setVisible(false);
        
        // Añadir márgenes alrededor del panel
        panelBotones.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        infoContent.add(playerPanel);
        infoContent.add(panelBotones);
        infoContent.add(panelResetAction);
        infoContent.add(Box.createVerticalGlue());
        
        add(infoContent, BorderLayout.CENTER);
        
        // Panel de botones en la parte inferior
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 20, 10));
        buttonPanel.setBackground(new Color(240, 240, 240));
        
        // Botón de créditos (usar un icono adecuado)
        JButton creditsButton = createIconButton(UIManager.getIcon("OptionPane.informationIcon"));
        creditsButton.setToolTipText("Créditos");
        creditsButton.addActionListener(e -> showCredits());
        
        // Botón de ayuda (usar un icono adecuado)
        JButton helpButton = createIconButton(UIManager.getIcon("OptionPane.questionIcon"));
        helpButton.setToolTipText("Ayuda");
        helpButton.addActionListener(e -> showHelp());
        
        // Botón de salir (usar un icono adecuado)
        JButton exitButton = createIconButton(UIManager.getIcon("OptionPane.errorIcon"));
        exitButton.setToolTipText("Salir");
        exitButton.addActionListener(e -> exitApplication());
        
        buttonPanel.add(creditsButton);
        buttonPanel.add(helpButton);
        buttonPanel.add(exitButton);
        
        add(buttonPanel, BorderLayout.SOUTH);
    }
    
    private JLabel createLabel(String texto, int estilo, int tamaño, Color color) {
    	JLabel etiqueta = new JLabel(texto);
        etiqueta.setFont(new Font("Arial", estilo, tamaño));
        etiqueta.setForeground(color);
        etiqueta.setAlignmentX(Component.LEFT_ALIGNMENT);
        return etiqueta;
	}

	private BufferedImage createImageExample(int ancho, int alto) {
		// TODO Auto-generated method stub
		BufferedImage imagen = new BufferedImage(ancho, alto, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = imagen.createGraphics();
        
        // Fondo degradado
        GradientPaint gradiente = new GradientPaint(
            0, 0, new Color(100, 200, 100), 
            ancho, alto, new Color(50, 150, 50)
        );
        g2d.setPaint(gradiente);
        g2d.fillRect(0, 0, ancho, alto);
        
        // Dibujar elementos
        g2d.setColor(new Color(200, 50, 50));
        g2d.setStroke(new BasicStroke(4));
        g2d.drawOval(50, 50, ancho-100, alto-100);
        
        g2d.setColor(new Color(240, 200, 50));
        g2d.fillOval(ancho/2-30, alto/2-30, 60, 60);
        
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Arial", Font.BOLD, 24));
        g2d.drawString("Vegetales", ancho/2-60, alto/2+5);
        
        g2d.dispose();
        return imagen;
	}

	private JButton createButton(String texto, Color colorFondo) {
    	JButton boton = new JButton(texto);
        boton.setBackground(colorFondo);
        boton.setOpaque(true);
        boton.setBorderPainted(false);
        boton.setFocusPainted(false);
        boton.setFont(new Font("Arial", Font.BOLD, 12));
        
        // Calcular color de texto contrastante (blanco o negro)
        boton.setForeground(getContrastColor(colorFondo));
        
        // Hacer los botones más grandes
        boton.setPreferredSize(new Dimension(100, 60));
        
        return boton;
	}
    
    private Color getContrastColor(Color color) {
        double luminance = (0.299 * color.getRed() + 
                          0.587 * color.getGreen() + 
                          0.114 * color.getBlue()) / 255;
        return luminance > 0.5 ? Color.BLACK : Color.WHITE;
    }

	private JButton createIconButton(Icon icon) {
        JButton button = new JButton(icon);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        return button;
    }
    
    private void showCredits() {
        JOptionPane.showMessageDialog(this.getParent(),
            "Desarrollado por:\nBrian Ramirez,Dassiel Quintero,Guillermo León\nVersión 1.0",
            "Créditos", 
            JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void showHelp() {
        JOptionPane.showMessageDialog(this.getParent(), 
            "Ayuda del juego:\n\n1. Has click encima de el héroe en turno\n2 Selecciona la accion deseada en el panel lateral y ejecutala.",
            "Ayuda", 
            JOptionPane.QUESTION_MESSAGE);
    }
    
    private void exitApplication() {
        int confirm = JOptionPane.showConfirmDialog(this.getParent(), 
            "¿Estás seguro de que quieres salir?", 
            "Salir", 
            JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }
    
    public void updateInfo() throws IndexOutOfBoundsException, EmptyListException {
        
        Game game  = Game.getInstance(Game.class);
        if(game.getCurrentAction() == ActionPlayer.UNKWON && game.isCurrentPlayer()) {
        	panelBotones.setVisible(true);
            btnAttack.setVisible(true);
            btnMove.setVisible(true);
            btnSkill.setVisible(true);
            btnResetAction.setVisible(false);
            panelResetAction.setVisible(false);
        }else {
        	panelBotones.setVisible(false);
            btnAttack.setVisible(false);
            btnMove.setVisible(false);
            btnSkill.setVisible(false);
        }

        if(game.getCurrentAction() != ActionPlayer.UNKWON && game.isCurrentPlayer()){
            btnResetAction.setVisible(true);
            panelResetAction.setVisible(true);
        }

        if(game.getCurrentAction() == ActionPlayer.UNKWON || !game.isCurrentPlayer()){
            btnResetAction.setVisible(false);
            panelResetAction.setVisible(false);
        }

        if(game.getHeros().length()>0){
            Element player = game.getHeros().get(0);
            if(player != null) {
                CHaracter ch = (CHaracter) player;
                lblLifePlayer.setText("Vida: "+ch.getHealthPoints());
                lblNamePlayer.setText("Nombre: "+ch.getName());
                lblTurnPlayer.setText("Turno: "+game.getTurn());
                lblAttackPlayer.setText("Ataque: " + ch.getDamage());
                lblSkillPlayer.setText("Habilidad: " +ch.getSkillname() +" "+ ch.getDamageS());
                int anchoDeseado = ch.getSymbol().getWidth();
                int altoDeseado = ch.getSymbol().getHeight();

                Image imagenEscalada = ch.getSymbol().getScaledInstance(
                        80,
                        80,
                        Image.SCALE_SMOOTH
                );

                // Crear un nuevo ImageIcon con la imagen
                lblImagenPlayer.setIcon(new ImageIcon(imagenEscalada));
            }
        }
        else{
            playerPanel.setVisible(false);
        }

        
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.GRAY);
        g.drawLine(0, 0, 0, getHeight());
    }
}
