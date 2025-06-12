package gui;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import controller.Game;
import exception.EmptyListException;
import model.CHaracter;
import util.ListArray;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ConfigScreen extends JFrame {
    private ListArray<CHaracter> selectedCharacters = new ListArray<>();
    private JPanel charactersPanel;
    private JTextArea descriptionArea;
   // private JSpinner rowsSpinner;
   // private JSpinner colsSpinner;

    public ConfigScreen() {
        setTitle("Configuración del Juego");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true);
        GraphicsEnvironment.getLocalGraphicsEnvironment()
            .getDefaultScreenDevice()
            .setFullScreenWindow(this);
        
        // Botón de inicio
        JButton startButton = new JButton("Iniciar Juego");
        startButton.addActionListener(e -> validateAndStart());
        
        // Organizar componentes
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(createMapConfigPanel(), BorderLayout.CENTER);
        getContentPane().add(startButton, BorderLayout.SOUTH);
    }
    
    private JPanel createMapConfigPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Título
//        gbc.gridwidth = 2;
//        gbc.gridx = 0;
//        gbc.gridy = 0;
//        JLabel titleLabel = new JLabel("Configuración del Mapa");
//        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
//        panel.add(titleLabel, gbc);
        
        // Configuración de dimensiones del mapa
        //gbc.gridwidth = 1;
        //gbc.gridy++;
        //panel.add(new JLabel("Filas del mapa (9-15):"), gbc);
        
        //gbc.gridx = 1;
        //rowsSpinner = new JSpinner(new SpinnerNumberModel(9, 9, 15, 1));
        //panel.add(rowsSpinner, gbc);
        
        //gbc.gridx = 0;
        //gbc.gridy++;
        //panel.add(new JLabel("Columnas del mapa (9-15):"), gbc);
        
        //gbc.gridx = 1;
        //colsSpinner = new JSpinner(new SpinnerNumberModel(9, 9, 15, 1));
        //panel.add(colsSpinner, gbc);
        
        
        // Panel de vista previa del mapa
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        
       
        
        JPanel panelCharacters =  this.createCharactersSelectionPanel();
        
        panel.add(panelCharacters, gbc);

        return panel;
    }
    
    
    private JPanel createCharactersSelectionPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        
        
        // Lista de personajes disponibles
        ArrayList<CHaracter> characters = Game.getInstance(Game.class).getAlwayCharacter();
        
     // Panel con GridBagLayout para organización en columnas con tamaños fijos
        charactersPanel = new JPanel(new GridBagLayout());
        charactersPanel.setBorder(new TitledBorder("Selecciona 5 personajes (haz clic para seleccionar)"));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Espaciado
        gbc.fill = GridBagConstraints.HORIZONTAL;

        int numColumns = 5;
        int currentCol = 0;
        int currentRow = 0;

        for (CHaracter character : characters) {
            gbc.gridx = currentCol;
            gbc.gridy = currentRow;
            
            JButton charButton = createCharacterButton(character);
            charactersPanel.add(charButton, gbc);
            
            currentCol++;
            if (currentCol >= numColumns) {
                currentCol = 0;
                currentRow++;
            }
        }
        
        // Área de descripción
        descriptionArea = new JTextArea();
        descriptionArea.setEditable(false);
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);
        descriptionArea.setBorder(new TitledBorder("Detalles del Personaje"));
        descriptionArea.setFont(new Font("Arial", Font.PLAIN, 14));
        descriptionArea.setPreferredSize(new Dimension(300, 400));
        
        // Contador de selección
        JLabel counterLabel = new JLabel("Seleccionados: 0/5");
        counterLabel.setHorizontalAlignment(SwingConstants.CENTER);
        counterLabel.setFont(new Font("Arial", Font.BOLD, 16));
        
        // Organizar componentes
        JScrollPane scrollPane = new JScrollPane(charactersPanel);
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(new JScrollPane(descriptionArea), BorderLayout.EAST);
        panel.add(counterLabel, BorderLayout.SOUTH);
        
        JLabel titleLabel = new JLabel("Selección de Personajes");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(titleLabel, BorderLayout.NORTH);
        
        return panel;
    }
    
    private JButton createCharacterButton(CHaracter character){
        // Crear botón con imagen del kart
        JButton button = new JButton(character.getName());
        button.setVerticalTextPosition(SwingConstants.BOTTOM);
        button.setHorizontalTextPosition(SwingConstants.CENTER);
        button.setFont(new Font("Arial", Font.BOLD, 12));
        
        // Cargar imagen desde recursos
        ImageIcon icon = createResizedIcon(character.getSymbol(), 120, 120);
        if (icon != null) {
            button.setIcon(icon);
        } else {
            button.setText(character.getName());
        }
        
        // Establecer acción al hacer clic
        button.addActionListener(e -> {
            String desc = String.format("%s\n", 
                character.getName());
            
            descriptionArea.setText(desc);
            
            if (selectedCharacters.contains(character)) {
                try {
					selectedCharacters.remove(character);
				} catch (EmptyListException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                button.setBorder(null);
            } else {
                if (selectedCharacters.length() < 5) {
                    selectedCharacters.add(character);
                    button.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
                } else {
                    JOptionPane.showMessageDialog(this, 
                            "Ya has seleccionado 5 personajes",
                            "Límite alcanzado", 
                            JOptionPane.WARNING_MESSAGE);
                }
            }
            
            updateSelectionCounter();
        });
        
        return button;
    }
    
    private ImageIcon createResizedIcon(BufferedImage imagePath, int width, int height) {
        try {
            if (imagePath != null) {
                ImageIcon originalIcon = new ImageIcon(imagePath);
                Image resizedImage = originalIcon.getImage().getScaledInstance(
                    width, height, Image.SCALE_SMOOTH);
                return new ImageIcon(resizedImage);
            } else {
                System.err.println("No se encontró la imagen: " + imagePath);
            }
        } catch (Exception e) {
            System.err.println("Error cargando imagen: " + imagePath);
            e.printStackTrace();
        }
        return null;
    }
    
    private void updateSelectionCounter() {
        Component[] components = ((JPanel)getContentPane().getComponent(0)).getComponents();
        for (Component comp : components) {
            if (comp instanceof JPanel) {
                Component[] subComps = ((JPanel)comp).getComponents();
                for (Component subComp : subComps) {
                    if (subComp instanceof JLabel && ((JLabel)subComp).getText().startsWith("Seleccionados:")) {
                        JLabel label = (JLabel)subComp;
                        label.setText("Seleccionados: " + selectedCharacters.length() + "/5");
                        label.setForeground(selectedCharacters.length() == 5 ? 
                            new Color(0, 150, 0) : Color.RED);
                    }
                }
            }
        }
    }
    
    
    
    private void centerWindow() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - getWidth()) / 2;
        int y = (screenSize.height - getHeight()) / 2;
        setLocation(x, y);
    }
    
    public void showConfig(Runnable callback) {
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                if (selectedCharacters.length() == 5) {
                    callback.run();
                }
            }
        });
        setVisible(true);
    }
    
    public ListArray<CHaracter> getSelectedCharacters() {
        return selectedCharacters;
    }
    
 // Métodos para obtener la configuración del mapa
//    public int getMapRows() {
//        return (int) rowsSpinner.getValue();
//    }
//    
//    public int getMapColumns() {
//        return (int) colsSpinner.getValue();
//    }
    
    private void validateAndStart() {
        // Validar selección de karts
        if (selectedCharacters.length() != 5) {
            JOptionPane.showMessageDialog(this, 
                "Debes seleccionar exactamente 5 personajes",
                "Selección incompleta", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        // Validar dimensiones del mapa
//        int rows = (int) rowsSpinner.getValue();
//        int cols = (int) colsSpinner.getValue();
//        
//        if (rows < 9 || rows > 15 || cols < 9 || cols > 15) {
//            JOptionPane.showMessageDialog(this, 
//                "Las dimensiones del mapa deben estar entre 9 y 15", 
//                "Dimensiones inválidas", 
//                JOptionPane.ERROR_MESSAGE);
//            return;
//        }
        
        dispose();
    }
    
    // Clase para representar los personajes de SuperTuxKart
    public static class STKCharacter {
        private String name;
        private String imagePath;
        private String description;
        private String speed;
        private String acceleration;
        private String handling;
        
        public STKCharacter(String name, String imagePath, String description, 
                          String speed, String acceleration, String handling) {
            this.name = name;
            this.imagePath = imagePath;
            this.description = description;
            this.speed = speed;
            this.acceleration = acceleration;
            this.handling = handling;
        }
        
        // Getters
        public String getName() { return name; }
        public String getImagePath() { return imagePath; }
        public String getDescription() { return description; }
        public String getSpeed() { return speed; }
        public String getAcceleration() { return acceleration; }
        public String getHandling() { return handling; }
        
        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            STKCharacter that = (STKCharacter) obj;
            return name.equals(that.name);
        }
        
        @Override
        public int hashCode() {
            return name.hashCode();
        }
    }
    
    // Clase para diseño de wrap mejorado
    private static class WrapLayout extends FlowLayout {
        public WrapLayout(int align, int hgap, int vgap) {
            super(align, hgap, vgap);
        }
        
        @Override
        public Dimension preferredLayoutSize(Container target) {
            return layoutSize(target, true);
        }
        
        @Override
        public Dimension minimumLayoutSize(Container target) {
            return layoutSize(target, false);
        }
        
        private Dimension layoutSize(Container target, boolean preferred) {
            synchronized (target.getTreeLock()) {
                int targetWidth = target.getSize().width;
                if (targetWidth == 0) targetWidth = Integer.MAX_VALUE;
                
                int hgap = getHgap();
                int vgap = getVgap();
                Insets insets = target.getInsets();
                int maxWidth = targetWidth - (insets.left + insets.right + hgap * 2);
                
                Dimension dim = new Dimension(0, 0);
                int rowWidth = 0;
                int rowHeight = 0;
                
                for (Component comp : target.getComponents()) {
                    if (comp.isVisible()) {
                        Dimension d = preferred ? comp.getPreferredSize() : comp.getMinimumSize();
                        
                        if (rowWidth + d.width > maxWidth) {
                            dim.width = Math.max(dim.width, rowWidth);
                            dim.height += rowHeight + vgap;
                            rowWidth = 0;
                            rowHeight = 0;
                        }
                        
                        rowWidth += d.width + hgap;
                        rowHeight = Math.max(rowHeight, d.height);
                    }
                }
                
                dim.width = Math.max(dim.width, rowWidth);
                dim.height += rowHeight + vgap;
                
                dim.width += insets.left + insets.right + hgap * 2;
                dim.height += insets.top + insets.bottom + vgap * 2;
                
                return dim;
            }
        }
    }
}
