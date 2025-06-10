package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import exception.EmptyListException;
import model.CHaracter;
import model.Element;
import model.Map;
import model.Position;
import model.hero.*;
import pattern.ParentSingleton;
import util.Generate;
import util.ListArray;

public class Game extends ParentSingleton {

	private GameStatus status;
	private ActionPlayer currentAction;
	private Map map;
	private Element currentPlayer;
	private int turn;
	private int players;

	public Game() {
		this.status = GameStatus.UNKWON;
		this.map = new Map();
		this.currentPlayer = null;
		this.currentAction = ActionPlayer.UNKWON;
		this.turn = 0;
		this.players = 0;
	}

	public ArrayList<CHaracter> getAlwayCharacter() {
		ArrayList<CHaracter> characters = new ArrayList<>();

		characters.add(new BomberTomato("BomberTomato", "", "/resource/heros/BomberTomato.png", 40));
		characters.add(new BroccoliSage("BroccoliSage", "", "/resource/heros/BroccoliSage.png", 40));
		characters.add(new CarrotShooter("CarrotShooter", "", "/resource/heros/CarrotShooter.png", 40));
		characters.add(new CornSniper("CornSniper", "", "/resource/heros/CornSniper.png", 40));
		characters.add(new CucumberShadow("CucumberShadow", "", "/resource/heros/CucumberShadow.png", 40));
		characters.add(new GarlicWarlock("GarlicWarlock", "", "/resource/heros/GarlicWarlock.png", 40));
		characters.add(new LanceLettuce("LanceLettuce", "", "/resource/heros/LanceLettuce.png", 40));
		characters.add(new PumpkinShield("PumpkinShield", "", "/resource/heros/PumpkinShield.png", 40));
		characters.add(new RadishSprinter("RadishSprinter", "", "/resource/heros/loca.png", 40));
		characters.add(new SpinachRage("SpinachRage", "", "/resource/heros/SpinachRage.png", 40));

		return characters;
	}

	public void createNewGame(ListArray<CHaracter> seleccionados) throws IndexOutOfBoundsException, EmptyListException {

		ListArray<Element> listElement = new ListArray<>();
		listElement = Generate.generateElement(seleccionados);
		status = GameStatus.EJECT;
		this.map.clear();
		this.currentPlayer = null;
		this.turn = 1;
		this.players = 0;
		this.currentAction = ActionPlayer.UNKWON;
		for (Element e : listElement) {
			this.map.addElement(e);
		}
	}

	public ListArray<Element> getEnemys() {
		return this.map.getEnemys();
	}

	public ListArray<Element> getPuzzles() {
		return this.map.getPuzzles();
	}

	public ListArray<Element> getObstacules() {
		return this.map.getObstacules();
	}

	public ListArray<Element> getBonus() {
		return this.map.getBonus();
	}

	public ListArray<Element> getHeros() {
		return this.map.getHeros();
	}

	public boolean isCurrentPlayer() {
		return (this.currentPlayer != null);
	}

	public ActionPlayer getCurrentAction() {
		return currentAction;
	}

	public void setCurrentAction(ActionPlayer currentAction) {
		this.currentAction = currentAction;
	}

	public int getTurn() {
		return turn;
	}

	public void selectPos(Position position) throws IndexOutOfBoundsException, EmptyListException {
		// TODO Auto-generated method stub
		if (this.currentPlayer == null && map.getHeros().get(0).getPosition().equals(position)) {
			this.currentPlayer = map.getHeros().get(0);
			this.currentAction = ActionPlayer.UNKWON;
		} else if (isCurrentPlayer() && this.currentAction != ActionPlayer.UNKWON) {
			boolean actionPlayer = false;
			if (this.currentAction == ActionPlayer.MOVE) {
				actionPlayer = movePlayer(position);
			} else if (this.currentAction == ActionPlayer.ATTACK) {
				actionPlayer = attackPlayer(position);
			} else if (this.currentAction == ActionPlayer.SKILL) {
				actionPlayer = skillPlayer(position);
			}

			if (actionPlayer) {
				this.currentPlayer = null;
				this.currentAction = ActionPlayer.UNKWON;
				this.map.nextHero();
				players++;
				if(players%5==0) playEnemys();
				turn = (players / 5) + 1;
				

			}
		}
	}

	private void playEnemys() throws IndexOutOfBoundsException, EmptyListException {
		Random rand = new Random();
		for(Element e : map.getEnemys()) {
			int action = rand.nextInt(1);
			if(action == 0) {
				if(e instanceof CHaracter) {
					CHaracter ch = (CHaracter) e;
					ListArray<Position> positions = map.getPosibleCellMov(ch);
					ch.Moving(positions, map.getPuzzles().get(0).getPosition());
				}
			}
		}
	}

	private boolean skillPlayer(Position position) {
		// TODO Auto-generated method stub
		return false;
	}

	private boolean attackPlayer(Position position) {
		// TODO Auto-generated method stub
		return false;
	}

	private boolean movePlayer(Position position) throws IndexOutOfBoundsException, EmptyListException {
		// TODO Auto-generated method stub
		ListArray<Position> positions = this.map.getPosibleCellAction((CHaracter) this.currentPlayer);

		boolean isCorrect = false;

		for (Position p : positions) {
			if (p.equals(position)) {
				isCorrect = true;
				break;
			}
		}
		if (isCorrect)
			this.map.getHeros().get(0).setPosition(position);
		// TODO Falta saber si el usuario esta sobre un bonus o sobre el la enigma
		return isCorrect;
	}

	public ListArray<Position> getPosiblesPosicionAction() {
		ListArray<Position> positions = new ListArray<Position>();
		if (isCurrentPlayer() && this.currentPlayer instanceof CHaracter) {
			switch (this.currentAction) {
			case ATTACK:
				positions = this.map.getPosibleCellAction((CHaracter) this.currentPlayer);
				break;
			case MOVE:
				positions = this.map.getPosibleCellMov((CHaracter) this.currentPlayer);
				break;
			case SKILL:
				positions = this.map.getPosibleCellSkill((CHaracter) this.currentPlayer);
				break;
			}
		}
		return positions;
	}
}
