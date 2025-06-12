
package model;

import exception.EmptyListException;
import model.bonus.Bonus;
import model.enemy.Enemy;
import model.hero.Hero;
import model.obstacule.Obstacule;
import model.puzzle.Puzzle;
import util.ListArray;

import java.util.Arrays;

public class Map {

	public int rows;
	public int columns;
	public ListArray<Element> elements;
	public ListArray<Element> heros;
	public ListArray<Element> enemys;
	public ListArray<Element> puzzles;
	public ListArray<Element> obstacules;
	public ListArray<Element> bonus;

	public Map(int rows, int columns) {
		this.rows = rows;
		this.columns = columns;
		this.elements = new ListArray<>();
		this.heros = new ListArray<>();
		this.enemys = new ListArray<>();
		this.puzzles = new ListArray<>();
		this.obstacules = new ListArray<>();
		this.bonus = new ListArray<>();
	}

	public Map() {
		this.elements = new ListArray<>();
		this.elements = new ListArray<>();
		this.heros = new ListArray<>();
		this.enemys = new ListArray<>();
		this.puzzles = new ListArray<>();
		this.obstacules = new ListArray<>();
		this.bonus = new ListArray<>();
		this.rows = 12;
		this.columns = 14;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getColumns() {
		return columns;
	}

	public void setColumns(int columns) {
		this.columns = columns;
	}

	public boolean addElement(Element x) throws UnsupportedOperationException {
		this.elements.add(x);
		if(x instanceof Hero) heros.add(x);
		if(x instanceof Enemy) enemys.add(x);
		if(x instanceof Obstacule) obstacules.add(x);
		if(x instanceof Puzzle) puzzles.add(x);
		if(x instanceof Bonus) bonus.add(x);
		return true;
	}

	public ListArray<Element> getEnemys() {
		return enemys;
	}

	public ListArray<Element> getPuzzles() {
		return puzzles;
	}

	public ListArray<Element> getObstacules() {
		return obstacules;
	}

	public ListArray<Element> getBonus() {
		return bonus;
	}

	public boolean isPositionFree(Position position) throws UnsupportedOperationException {
//		for (Element e : elements) {
//			if (e instanceof Bonus)
//				continue;
//			if (e instanceof Puzzle)
//				continue;
//			if (e.getPosition().equals(position))
//				return false;
//		}
		for(Element h : this.heros){
			if(h.getPosition().equals(position)) return false;
		}
		for(Element ene : this.enemys){
			if(ene.getPosition().equals(position)) return false;
		}
		for(Element obs : this.obstacules){
			if(obs.getPosition().equals(position)) return false;
		}
		return true;
	}

	public Element toPlay() throws EmptyListException {
		return this.heros.get(0);
	}

	public void nextPlayer() throws EmptyListException {
		Element element = this.heros.get(0);
		this.heros.remove(0);
		this.heros.add(element);
	}

	public ListArray<Position> getPosibleCellMov(CHaracter character) {
		int row_min = character.getPosition().getRow() - character.getMoveRank();
		int column_min = character.getPosition().getColumn() - character.getMoveRank();
		int row_max = character.getPosition().getRow() + character.getMoveRank();
		int column_max = character.getPosition().getColumn() + character.getMoveRank();
//		System.out.println("Character :"+character.getPosition());
//		System.out.println("RANK MOVE :"+character.getMoveRank());
		ListArray<Position> positions = new ListArray<>();

		for (int i = row_min; i <= row_max; i++) {
			for (int j = column_min; j <= column_max; j++) {
				Position p = new Position(i, j);
			//	System.out.println("POSSIBLE POSICION:"+p);
				if (validPosition(p) && !character.getPosition().equals(p)
						&& distanceMathanan(p, character.getPosition()) <= character.getMoveRank()
						&& isPositionFree(p)) {
					positions.add(p);
					//System.out.println("SIRVE");
				}
			}
		}
		return positions;
	}

	public ListArray<Position> getPosibleCellAction(CHaracter character) {
		int row_min = character.getPosition().getRow() - character.getActionRank();
		int column_min = character.getPosition().getColumn() - character.getActionRank();
		int row_max = character.getPosition().getRow() + character.getActionRank();
		int column_max = character.getPosition().getColumn() + character.getActionRank();

		ListArray<Position> positions = new ListArray<>();

		for (int i = row_min; i <= row_max; i++) {
			for (int j = column_min; j <= column_max; j++) {
				Position p = new Position(i, j);
				if (validPosition(p) && !character.getPosition().equals(p)
						&& distanceMathanan(p, character.getPosition()) <= character.getActionRank()) {
					positions.add(p);
				}
			}
		}
		return positions;
	}

	public ListArray<Position> getPosibleCellSkill(CHaracter character) {
		int row_min = character.getPosition().getRow() - character.getActionRankS();
		int column_min = character.getPosition().getColumn() - character.getActionRankS();
		int row_max = character.getPosition().getRow() + character.getActionRankS();
		int column_max = character.getPosition().getColumn() + character.getActionRankS();

		ListArray<Position> positions = new ListArray<>();

		for (int i = row_min; i <= row_max; i++) {
			for (int j = column_min; j <= column_max; j++) {
				Position p = new Position(i, j);
				if (validPosition(p) && character.getPosition().equals(p) == false
						&& distanceMathanan(p, character.getPosition()) <= character.getActionRankS()) {
					positions.add(p);
				}
			}
		}
		return positions;
	}

	private int distanceMathanan(Position a, Position b) {
		return Math.abs(a.getColumn() - b.getColumn()) + Math.abs(a.getRow() - b.getRow());
	}

	private boolean validPosition(Position p) {
		return (0 <= p.getRow() && p.getRow() < 12 && 0 <= p.getColumn() && p.getColumn() < 14);
	}

	public Position getPositionPuzzle() {
		Position pos = null;
		for (Element e : elements) {
			if (e instanceof Puzzle) {
				return e.getPosition();
			}
		}
		return pos;
	}

	public void clear() {
		this.elements = new ListArray<>();
        this.heros = new ListArray<>();
        this.enemys = new ListArray<>();
        this.puzzles = new ListArray<>();
        this.obstacules = new ListArray<>();
        this.bonus = new ListArray<>();
	}

	public ListArray<Element> getHeros() {
		return this.heros;
	}

	public void nextHero() throws IndexOutOfBoundsException, EmptyListException {
		// TODO Auto-generated method stub
		Element hero = this.heros.get(0);
		this.heros.remove(0);
		if(hero instanceof CHaracter){
			CHaracter h = (CHaracter) hero;
			if(h.getHealthPoints()>0){
				this.heros.add(hero);
			}
		}
	}


	public void checkStatusHero() throws EmptyListException {
		for (int i =this.heros.length()-1;i>=0;i--){
			Element e=this.heros.get(i);
			if(e instanceof CHaracter){
				CHaracter ch=(CHaracter) e;
				if(ch.getHealthPoints()<=0){
					this.heros.remove(i);
				}
			}

		}
	}


	public void checkStatusEnemy() throws EmptyListException {
		for (int i =this.enemys.length()-1;i>=0;i--){
			Element e=this.enemys.get(i);
			if(e instanceof CHaracter){
				CHaracter ch=(CHaracter) e;
				if(ch.getHealthPoints()<=0){
					this.enemys.remove(i);
				}
			}

		}
	}

	public boolean existHero(){

		return this.heros.length()>0;
	}

	public boolean existEnemy(){

		return this.enemys.length()>0;
	}

	public boolean puzzlesResolved() {
		boolean resolve=false;
		for (Element e: this.puzzles){
			if(e instanceof Puzzle){
				Puzzle p = (Puzzle) e;
				resolve=resolve|p.isResolved();
			}
		}
		return resolve;
	}

	public void applyBonus(Element element) throws EmptyListException {
		if(element instanceof CHaracter){
			CHaracter cHaracter = (CHaracter) element;
            Element remove = null;
			for(Element e: this.bonus){
				if(e.getPosition().equals(cHaracter.getPosition())){
					if(e instanceof Bonus){
						Bonus b = (Bonus) e;
						b.applyBonus(cHaracter);
						remove=e;
					}
				}
			}
             if (remove!=null){
				 this.bonus.remove(remove);
			 }
		}
	}

	public boolean isActivatePuzzle() throws EmptyListException {
		boolean active = true;
		boolean pos= false;
		active = (active & !(this.enemys.length()>0));
		for(int i=0;i<this.puzzles.length() && !pos;i++){
			Element puzzle = puzzles.get(i);
			for(int j=0;j<this.heros.length() && !pos;j++){
				Element hero = heros.get(j);
				if(puzzle.getPosition().equals(hero.getPosition())){
					pos=true;
				}
			}
		}
	    return (active & pos);


	}

}
