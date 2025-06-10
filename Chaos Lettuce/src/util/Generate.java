package util;

import java.util.Random;

import exception.EmptyListException;
import model.CHaracter;
import model.Element;
import model.Position;
import model.bonus.*;
import model.enemy.*;
import model.obstacule.Obstacule;
import model.puzzle.*;

public class Generate {

	public static ListArray<Element> generateElement(ListArray<CHaracter> players) throws IndexOutOfBoundsException, EmptyListException {
		Random rand = new Random();
        ListArray<Element> elements = new ListArray<>();
        int max_iterations;
        for(CHaracter ch : players) {
        	int row = rand.nextInt(12);
            int column =  rand.nextInt(14);
            Position pos = new Position(row,column);
            max_iterations = 25;
            while(busyCell(pos,elements) && max_iterations>0) {
            	row = rand.nextInt(12);
                column =  rand.nextInt(14);
                pos = new Position(row,column);
                max_iterations--;
            }
            if(max_iterations>0){
            	ch.setParametersInitialDefault();
                ch.setPosition(pos);
                elements.add(ch);
            }else {
            	boolean find_pos = false;
            	for(int r=0;r<11 && !find_pos;r++){
                    for(int c=0;c<14 && !find_pos;c++){
                        pos = new Position(r,c);
                        if(!busyCell(pos,elements)){
                            ch.setPosition(pos);
                            ch.setParametersInitialDefault();
                            elements.add(ch);
                            find_pos =true;
                        }
                    }
                }
            }

        }

        int enemys_create= 13;
        max_iterations = 50;
        while(enemys_create > 0 && max_iterations > 0){
            int type_enemy = rand.nextInt(5);
            Enemy enemy = factoryEnemy(type_enemy);

            if(enemy != null){
                int row = rand.nextInt(12);
                int column =  rand.nextInt(14);
                Position pos = new Position(row,column);
                while(busyCell(pos,elements) && max_iterations>0){
                    row = rand.nextInt(12);
                    column =  rand.nextInt(14);
                    pos = new Position(row,column);
                    max_iterations--;
                }
                if(max_iterations>0){
                    enemy.setPosition(pos);
                    elements.add(enemy);
                    enemys_create --;
                }
            }

        }
        for(int r=11;r>=0 && enemys_create>0 ;r--){
            for(int c=13;c>=0 && enemys_create>0 ;c--){
                Position pos = new Position(r,c);
                if(!busyCell(pos,elements)){
                    int type_enemy = rand.nextInt(5);
                    Enemy enemy = factoryEnemy(type_enemy);
                    enemy.setPosition(pos);
                    elements.add(enemy);
                    enemys_create --;
                }
            }
        }

        int puzzle_create = 1;
        max_iterations = 50;
        while( puzzle_create > 0 && max_iterations > 0){
            int type_puzzle = rand.nextInt(3);
            Puzzle puzzle = factoryPuzzle(type_puzzle);
            if(puzzle != null){
                int row = rand.nextInt(12);
                int column =  rand.nextInt(14);
                Position pos = new Position(row,column);
                while(busyCell(pos,elements) && max_iterations>0){
                    row =  rand.nextInt(12);
                    column =  rand.nextInt(14);
                    pos = new Position(row,column);
                    max_iterations--;
                }
                if(max_iterations>0){
                    puzzle.setPosition(pos);
                    elements.insert(0,puzzle);
                    puzzle_create --;
                }
            }
        }

        for(int r=11;r>=0 && puzzle_create>0 ;r--){
            for(int c=13;c>=0 && puzzle_create>0 ;c--){
                Position pos = new Position(r,c);
                if(!busyCell(pos,elements)){
                    int type_puzzle = rand.nextInt(3);
                    Puzzle puzzle = factoryPuzzle(type_puzzle);
                    puzzle.setPosition(pos);
                    elements.insert(0,puzzle);
                    puzzle_create --;
                }
            }
        }

        int bonus_create = 13;
        max_iterations = 50;
                while(bonus_create > 0 && max_iterations > 0){
            int type_bonus = rand.nextInt(4);
            Bonus bonus = factoryBonus(type_bonus);

            if(bonus != null){
                int row = rand.nextInt(12);
                int column =  rand.nextInt(14);
                Position pos = new Position(row,column);
                while(busyCell(pos,elements) && max_iterations>0){
                    row =  rand.nextInt(12);
                    column =  rand.nextInt(14);
                    pos = new Position(row,column);
                    max_iterations--;
                }
                if(max_iterations>0){
                    bonus.setPosition(pos);
                    elements.add(bonus);
                    bonus_create --;
                }
            }
        }
        for(int c=13;c>=0 && bonus_create>0 ;c--){
            for(int r=11;r>=0 && bonus_create>0 ;r--){
                Position pos = new Position(r,c);
                if(busyCell(pos,elements)==false){
                    int type_bonus = rand.nextInt(4);
                    Bonus bonus = factoryBonus(type_bonus);
                    bonus.setPosition(pos);
                    elements.add(bonus);
                    bonus_create --;
                }
            }
        }

        int obstacule_create = 13;
        max_iterations = 50;
        while(obstacule_create > 0 && max_iterations > 0){
            Obstacule obstacule = new Obstacule();

            if(obstacule != null){
                int row = rand.nextInt(12);
                int column =  rand.nextInt(14);
               Position pos = new Position(row,column);
                while(busyCell(pos,elements) && max_iterations>0){
                    row =  rand.nextInt(12);
                    column =  rand.nextInt(14);
                    pos = new Position(row,column);
                    max_iterations--;
                }

                if(max_iterations>0){
                    obstacule.setPosition(pos);
                    obstacule.setParametersInitialDefault();
                    elements.add(obstacule);
                    obstacule_create --;
                }
            }
        }

        for(int c=13;c>=0 && obstacule_create>0 ;c--){
            for(int r=11;r>=0 && obstacule_create>0 ;r--){
                Position pos = new Position(r,c);
                if(!busyCell(pos,elements)){
                    Obstacule obstacule = new Obstacule();
                    obstacule.setPosition(pos);
                    obstacule.setParametersInitialDefault();
                    elements.add(obstacule);
                    obstacule_create --;
                }
            }
        }

        return elements;

    }

    private static Bonus factoryBonus(int typeBonus) {
        Bonus bonus = null;
        switch (typeBonus){
            case 0:
                bonus = new Fertilizer();
                break;
            case 1:
                bonus = new InfestedSoil();
                break;
            case 2:
                bonus = new PurifiedWater();
                break;
            case 3:
                bonus = new DeadlyDrougth();
                break;

        }
        if(bonus != null)
            bonus.setParametersInitialDefault();

        return bonus;
    }

    private static Puzzle factoryPuzzle(int typePuzzle) {
        Puzzle puzle = new Puzzle();
        puzle.setParametersInitialDefault();

        return puzle;
    }

    private static Enemy factoryEnemy(int typeEnemy) {

        Enemy enemy = null ;
        switch (typeEnemy){
            case 0:
                enemy = new GiantSlug();
                break;
            case 1:
                enemy = new MoleDigger();
                break;
            case 2:
                enemy = new KillerBee();
                break;
            case 3:
                enemy = new ToxicMushroom();
                break;
            case 4:
                enemy = new MutantLettuce();
                break;
        }
        if(enemy != null)
            enemy.setParametersInitialDefault();
        return enemy;

    }

    private static boolean busyCell(Position pos, ListArray<Element> elements) {
        for(Element e : elements){
            if(pos.equals(e.getPosition()))
                return true;
        }
        return false;
    }

}
