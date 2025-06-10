package gui.components;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import controller.*;
import exception.EmptyListException;
import model.Element;
import model.Position;
import util.ListArray;

public class GamePanel extends JPanel {
	private int rows = 12; // Número de filas
	private int cols = 14; // Número de columnas
	private int cellSize; // Tamaño calculado de cada celda
	private int selectedRow = -1;
	private int selectedCol = -1;
	private int xOffset;
	private int yOffset;
	private BufferedImage backgroundImage;
	private BufferedImage moveImage;
	private BufferedImage attackImage;
	private BufferedImage skillImage;

	public GamePanel() {
		setOpaque(false); // Hacer el panel transparente

		// Cargar imagen de fondo desde recursos
		try {
			backgroundImage = ImageIO.read(getClass().getResource("/resource/background.png"));
		} catch (IOException e) {
			System.err.println("Error al cargar la imagen de fondo");
			backgroundImage = null;
		}
		try {
			moveImage = ImageIO.read(getClass().getResource("/resource/actions/action_move.png"));
		} catch (IOException e) {
			System.err.println("Error al cargar la imagen de fondo");
			moveImage = null;
		}
		try {
			attackImage = ImageIO.read(getClass().getResource("/resource/actions/action_attack.png"));
		} catch (IOException e) {
			System.err.println("Error al cargar la imagen de fondo");
			attackImage = null;
		}
		try {
			skillImage = ImageIO.read(getClass().getResource("/resource/actions/action_skill.png"));
		} catch (IOException e) {
			System.err.println("Error al cargar la imagen de fondo");
			skillImage = null;
		}
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					handleCellSelection(e.getX(), e.getY());
					repaint();
				} catch (IndexOutOfBoundsException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (EmptyListException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
	}

	private void handleCellSelection(int x, int y) throws IndexOutOfBoundsException, EmptyListException {
		// Calcular fila y columna basado en la posición del click
		x -= xOffset;
		y -= yOffset;
		System.out.println("Posicion:" + x + " " + y);
		if (x > 0 && y > 0) {
			if (cellSize > 0) {
				selectedCol = x / cellSize;
				selectedRow = y / cellSize;

				// Verificar que esté dentro de los límites
				if (selectedRow >= rows)
					selectedRow = -1;
				if (selectedCol >= cols)
					selectedCol = -1;

				// Mostrar en consola la selección
				if (selectedRow != -1 && selectedCol != -1) {
					Game.getInstance(Game.class).selectPos(new Position(selectedRow, selectedCol));
					System.out.println(
							"Celda seleccionada: Fila " + (selectedRow + 1) + ", Columna " + (selectedCol + 1));
				}
			}
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;

		// Dibujar imagen de fondo (si se cargó correctamente)
		if (backgroundImage != null) {
			g2d.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
		} else {
			// Fondo alternativo si no hay imagen
			g2d.setColor(Color.BLACK);
			g2d.fillRect(0, 0, getWidth(), getHeight());
		}

		// Calcular tamaño y posición del tablero
		cellSize = Math.min(getWidth() / cols, getHeight() / rows);
		int boardWidth = cellSize * cols;
		int boardHeight = cellSize * rows;
		xOffset = (getWidth() - boardWidth) / 2;
		yOffset = (getHeight() - boardHeight) / 2;

		// Dibujar solo los bordes de las celdas
//		g2d.setColor(Color.WHITE);
//		g2d.setStroke(new BasicStroke(2)); // Grosor del borde

//		for (int row = 0; row < rows; row++) {
//			for (int col = 0; col < cols; col++) {
//				int x = xOffset + col * cellSize;
//				int y = yOffset + row * cellSize;
//
//				// Solo dibujar el borde (sin relleno)
////				g2d.drawRect(x, y, cellSize, cellSize);
//
//				// Resaltar celda seleccionada (opcional)
//				if (row == selectedRow && col == selectedCol) {
//					g2d.setColor(new Color(255, 255, 0, 100)); // Amarillo semitransparente
//					g2d.fillRect(x, y, cellSize, cellSize);
//					g2d.setColor(Color.WHITE); // Restaurar color blanco
//				}
//			}
//		}

		// Resaltar celda seleccionada (opcional)

		int x = xOffset + selectedCol * cellSize;
		int y = yOffset + selectedRow * cellSize;
		g2d.setColor(new Color(255, 255, 0, 100)); // Amarillo semitransparente
		g2d.fillRect(x, y, cellSize, cellSize);
		g2d.setColor(Color.WHITE); // Restaurar color blanco

		paintBonus(g2d);
		paintObstacules(g2d);
		paintPuzzles(g2d);
		paintHeros(g2d);
		paintEnemys(g2d);
		paintPosiblesMovs(g2d);
	}

	private void paintPosiblesMovs(Graphics2D g2d) {
		// TODO Auto-generated method stub
		Game game = Game.getInstance(Game.class);
		ListArray<Position> positions = game.getPosiblesPosicionAction();
		ActionPlayer action = game.getCurrentAction();
		BufferedImage actionImage = null;
		if (action == ActionPlayer.ATTACK)
			actionImage = this.attackImage;
		else if (action == ActionPlayer.MOVE)
			actionImage = this.moveImage;
		else if (action == ActionPlayer.SKILL)
			actionImage = this.skillImage;
		System.out.println("Immage action:" + actionImage);
		System.out.println("Movimientos:" + positions.length());
		if (actionImage != null) {
			System.out.println("Immage action:" + actionImage);
			for (Position p : positions) {
				int x = xOffset + p.getColumn() * cellSize;
				int y = yOffset + p.getRow() * cellSize;
				g2d.drawImage(actionImage, x, y, cellSize, cellSize, this);
			}
		}
	}

	private void paintPuzzles(Graphics2D g2d) {
		// TODO Auto-generated method stub
		Game game = Game.getInstance(Game.class);
		ListArray<Element> puzzles = game.getPuzzles();
		for (Element puzzle : puzzles) {
			int x = xOffset + puzzle.getPosition().getColumn() * cellSize;
			int y = yOffset + puzzle.getPosition().getRow() * cellSize;
			g2d.drawImage(puzzle.getSymbol(), x, y, cellSize, cellSize, this);
		}
	}

	private void paintObstacules(Graphics2D g2d) {
		// TODO Auto-generated method stub
		Game game = Game.getInstance(Game.class);
		ListArray<Element> obstacules = game.getObstacules();
		for (Element obstacule : obstacules) {
			int x = xOffset + obstacule.getPosition().getColumn() * cellSize;
			int y = yOffset + obstacule.getPosition().getRow() * cellSize;
			g2d.drawImage(obstacule.getSymbol(), x, y, cellSize, cellSize, this);
		}
	}

	private void paintEnemys(Graphics2D g2d) {
		// TODO Auto-generated method stub
		Game game = Game.getInstance(Game.class);
		ListArray<Element> enemys = game.getEnemys();
		for (Element enemy : enemys) {
			int x = xOffset + enemy.getPosition().getColumn() * cellSize;
			int y = yOffset + enemy.getPosition().getRow() * cellSize;
			g2d.drawImage(enemy.getSymbol(), x, y, cellSize, cellSize, this);
		}
	}

	private void paintHeros(Graphics2D g2d) {
		// TODO Auto-generated method stub
		Game game = Game.getInstance(Game.class);
		ListArray<Element> heros = game.getHeros();
		for (Element hero : heros) {
			int x = xOffset + hero.getPosition().getColumn() * cellSize;
			int y = yOffset + hero.getPosition().getRow() * cellSize;
			g2d.drawImage(hero.getSymbol(), x, y, cellSize, cellSize, this);
		}
	}

	private void paintBonus(Graphics2D g2d) {
		Game game = Game.getInstance(Game.class);
		ListArray<Element> bonus = game.getBonus();
		for (Element bonu : bonus) {
			int x = xOffset + bonu.getPosition().getColumn() * cellSize;
			int y = yOffset + bonu.getPosition().getRow() * cellSize;
			g2d.drawImage(bonu.getSymbol(), x, y, cellSize, cellSize, this);
		}
	}
}