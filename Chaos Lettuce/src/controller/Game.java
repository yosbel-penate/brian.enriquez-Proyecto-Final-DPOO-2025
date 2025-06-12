package controller;

import java.util.ArrayList;
import java.util.Random;

import exception.EmptyListException;
import model.CHaracter;
import model.Element;
import model.Map;
import model.Position;
import model.hero.*;
import model.question.Question;
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

	public controller.GameStatus getStatus() {
		return status;
	}

	public void setStatus(controller.GameStatus status) {
		this.status = status;
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
		characters.add(new RadishSprinter("RadishSprinter", "", "/resource/heros/radish_sprint.png", 40));
		characters.add(new SpinachRage("SpinachRage", "", "/resource/heros/SpinachRage.png", 40));

		return characters;
	}

	public void createNewGame(ListArray<CHaracter> seleccionados, ListArray<Question> questions) throws IndexOutOfBoundsException, EmptyListException {

		ListArray<Element> listElement = new ListArray<>();
		listElement = Generate.generateElement(seleccionados,questions);
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
				//System.out.println("comenzo el ataque"+ actionPlayer);
				actionPlayer = attackPlayer(position);
				//System.out.println("Termino el ataque"+ actionPlayer);
			} else if (this.currentAction == ActionPlayer.SKILL) {
				actionPlayer = skillPlayer(position);
			}

			if (actionPlayer) {
				this.currentPlayer = null;
				this.currentAction = ActionPlayer.UNKWON;
				this.map.nextHero();
				players++;
				if(players%map.getHeros().length()==0) playEnemys();
				turn = (players / map.getHeros().length()) + 1;
				

			}
		}
	}

	private void playEnemys() throws IndexOutOfBoundsException, EmptyListException {

		Random rand = new Random();
		for(Element e : map.getEnemys()) {
			int action = rand.nextInt(2);
			if(action == 0) {
				if(e instanceof CHaracter) {
					CHaracter ch = (CHaracter) e;
					ListArray<Position> positions = map.getPosibleCellMov(ch);
					ch.Moving(positions, map.getPuzzles().get(0).getPosition());
					System.out.println(ch.getHealthPoints() + ch.getName());
					this.map.applyBonus(ch);
					System.out.println(ch.getHealthPoints() + ch.getName());
				}

			} else if (action == 1) {
				if(e instanceof CHaracter) {
					CHaracter ch = (CHaracter) e;
					ListArray<Position> positionsatck = map.getPosibleCellAction(ch);
					Position pos=ch.Attacking(positionsatck,map.getHeros());
					if(pos!=null){
						ListArray<Position> positions = map.getPosibleCellMov(ch);
						ch.Moving(positions, map.getPuzzles().get(0).getPosition());
						this.map.applyBonus(ch);
					}

				}
			}
		}
		this.map.checkStatusHero();
		this.map.checkStatusEnemy();
	}

	private boolean skillPlayer(Position position) throws EmptyListException {
		boolean skill =false;
		if(this.currentPlayer instanceof  CHaracter){
			CHaracter hero = (CHaracter) this.currentPlayer;
            hero.ActivatinSkill();

			boolean is_position_correct = false;
			ListArray<Position> posiblesPositions = this.map.getPosibleCellSkill(hero);

			for(Position p : posiblesPositions) {
				if (p.equals(position)) is_position_correct = true;
			}
			if(!is_position_correct) return false;

			for(Element e : this.map.getEnemys()){
				if (e.getPosition().equals(position)){
					if( e instanceof CHaracter){
						//System.out.println("atacando a " + e.getName() );
						CHaracter enenmy = (CHaracter) e;
						//System.out.println("Vida pre" + enenmy.getHealthPoints() );
						hero.attacking(enenmy);
						if(enenmy.getHealthPoints()<=0){
							this.map.getEnemys().remove(e);
						}
						//	System.out.println("Vida pos" + enenmy.getHealthPoints() );
						skill = true;
					}
				}
			}

			for(Element e : this.map.getHeros()){
				if (e.getPosition().equals(position)){
					if( e instanceof CHaracter){
						//	System.out.println("atacando a " + e.getName() );
						CHaracter	enenmy = (CHaracter) e;
						//System.out.println("Vida pre" + enenmy.getHealthPoints() );
						hero.attacking(enenmy);
						if(enenmy.getHealthPoints()<=0){
							this.map.getHeros().remove(e);
						}
						skill = true;
						//	System.out.println("Vida pos" + enenmy.getHealthPoints() );
					}
				}
			}

			hero.DeactivateSkill();
		}





		return skill;
	}

	private boolean attackPlayer(Position position) throws EmptyListException {
		boolean attack = false;

		if(this.currentPlayer instanceof  CHaracter){
			CHaracter ch = (CHaracter) this.currentPlayer;
			boolean is_position_correct = false;
			ListArray<Position> posiblesPositions = this.map.getPosibleCellAction(ch);

			for(Position p : posiblesPositions) {
				if (p.equals(position)) is_position_correct = true;
			}
			if(!is_position_correct) return false;
		}

		for(Element e : this.map.getEnemys()){
			if (e.getPosition().equals(position)){
				if(this.currentPlayer instanceof  CHaracter && e instanceof CHaracter){
					//System.out.println("atacando a " + e.getName() );
				  	CHaracter	enenmy = (CHaracter) e;
				  	CHaracter hero = (CHaracter) this.currentPlayer;
					//System.out.println("Vida pre" + enenmy.getHealthPoints() );
				  	hero.attacking(enenmy);
				  	if(enenmy.getHealthPoints()<=0){
					  	this.map.getEnemys().remove(e);
				    }
				//	System.out.println("Vida pos" + enenmy.getHealthPoints() );
					attack = true;
				}
			}
		}

		for(Element e : this.map.getHeros()){
			if (e.getPosition().equals(position)){
				if(this.currentPlayer instanceof  CHaracter && e instanceof CHaracter){
				//	System.out.println("atacando a " + e.getName() );
					CHaracter	enenmy = (CHaracter) e;
					CHaracter hero = (CHaracter) this.currentPlayer;
					//System.out.println("Vida pre" + enenmy.getHealthPoints() );
					hero.attacking(enenmy);
					if(enenmy.getHealthPoints()<=0){
						this.map.getHeros().remove(e);
					}
					attack = true;
				//	System.out.println("Vida pos" + enenmy.getHealthPoints() );
				}
			}
		}

		return attack;
	}

	private boolean movePlayer(Position position) throws IndexOutOfBoundsException, EmptyListException {
		// TODO Auto-generated method stub
		ListArray<Position> positions = this.map.getPosibleCellMov((CHaracter) this.currentPlayer);

		boolean isCorrect = false;

		for (Position p : positions) {
			if (p.equals(position)) {
				isCorrect = true;
				break;
			}
		}
		if (isCorrect){
			this.map.getHeros().get(0).setPosition(position);
			this.map.applyBonus(this.map.getHeros().get(0));
		}
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

	public void checkStatusGame() {
		status = GameStatus.EJECT;
		if (!map.existHero()) {
			status = GameStatus.WIN_ENEMY;
		} else if (!map.existEnemy() && map.puzzlesResolved()){
			status=GameStatus.WIN_HERO;
		}

	}

	public boolean isActivatePuzzle() throws EmptyListException{
		return this.map.isActivatePuzzle();
	}
}
