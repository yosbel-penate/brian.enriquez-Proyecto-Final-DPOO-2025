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
import model.question.Question;
import model.question.QuestionTrueOrFalse;

public class Generate {

	public static ListArray<Element> generateElement(ListArray<CHaracter> players, ListArray<Question> questions) throws IndexOutOfBoundsException, EmptyListException {
		Random rand = new Random();
        ListArray<Element> elements = new ListArray<>();
        createHeros(players, rand, elements);
        createEnemys(rand, elements);
        createPuzzles(rand, elements,questions);
        createBonus(rand, elements);
        createObstacule(rand, elements);

        return elements;

    }

    private static void createObstacule(Random rand, ListArray<Element> elements) {
        int max_iterations;

        int obstacule_create = 13;
        max_iterations = 50;
        while(obstacule_create > 0 && max_iterations > 0){
            Obstacule obstacule = new Obstacule();

            if(obstacule != null){
                int row = rand.nextInt(12);
                int column =  rand.nextInt(14);
               Position pos = new Position(row,column);
                while(busyCell(pos, elements) && max_iterations>0){
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
                if(!busyCell(pos, elements)){
                    Obstacule obstacule = new Obstacule();
                    obstacule.setPosition(pos);
                    obstacule.setParametersInitialDefault();
                    elements.add(obstacule);
                    obstacule_create --;
                }
            }
        }
    }

    private static void createBonus(Random rand, ListArray<Element> elements) {
        int max_iterations;

        int bonus_create = 13;
        max_iterations = 50;
        while(bonus_create > 0 && max_iterations > 0){
    int type_bonus = rand.nextInt(4);
    Bonus bonus = factoryBonus(type_bonus);

    if(bonus != null){
        int row = rand.nextInt(12);
        int column =  rand.nextInt(14);
        Position pos = new Position(row,column);
        while(busyCell(pos, elements) && max_iterations>0){
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
                if(busyCell(pos, elements)==false){
                    int type_bonus = rand.nextInt(4);
                    Bonus bonus = factoryBonus(type_bonus);
                    bonus.setPosition(pos);
                    elements.add(bonus);
                    bonus_create --;
                }
            }
        }
    }

    private static void createPuzzles(Random rand, ListArray<Element> elements, ListArray<Question> questions) throws EmptyListException {
        int max_iterations;

        int puzzle_create = 1;
        max_iterations = 50;
        while( puzzle_create > 0 && max_iterations > 0){
            int type_puzzle = rand.nextInt(3);
            Puzzle puzzle = factoryPuzzle(type_puzzle,questions);
            if(puzzle != null){
                int row = rand.nextInt(12);
                int column =  rand.nextInt(14);
                Position pos = new Position(row,column);
                while(busyCell(pos, elements) && max_iterations>0){
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
                if(!busyCell(pos, elements)){
                    int type_puzzle = rand.nextInt(3);
                    Puzzle puzzle = factoryPuzzle(type_puzzle, questions);
                    puzzle.setPosition(pos);
                    elements.insert(0,puzzle);
                    puzzle_create --;
                }
            }
        }
    }

    private static void createEnemys(Random rand, ListArray<Element> elements) {
        int max_iterations;

        int enemys_create= 13;
        max_iterations = 50;
        while(enemys_create > 0 && max_iterations > 0){
            int type_enemy = rand.nextInt(5);
            Enemy enemy = factoryEnemy(type_enemy);

            if(enemy != null){
                int row = rand.nextInt(12);
                int column =  rand.nextInt(14);
                Position pos = new Position(row,column);
                while(busyCell(pos, elements) && max_iterations>0){
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
                if(!busyCell(pos, elements)){
                    int type_enemy = rand.nextInt(5);
                    Enemy enemy = factoryEnemy(type_enemy);
                    enemy.setPosition(pos);
                    elements.add(enemy);
                    enemys_create --;
                }
            }
        }
    }

    private static void createHeros(ListArray<CHaracter> players, Random rand, ListArray<Element> elements) {
        int max_iterations;
        for(CHaracter ch : players) {
        	int row = rand.nextInt(12);
            int column =  rand.nextInt(14);
            Position pos = new Position(row,column);
            max_iterations = 25;
            while(busyCell(pos, elements) && max_iterations>0) {
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
                        if(!busyCell(pos, elements)){
                            ch.setPosition(pos);
                            ch.setParametersInitialDefault();
                            elements.add(ch);
                            find_pos =true;
                        }
                    }
                }
            }

        }
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

    private static Puzzle factoryPuzzle(int typePuzzle, ListArray<Question> questions) throws EmptyListException {
        Puzzle puzle = new Puzzle();
        Random rand = new Random();
        int pos = rand.nextInt(questions.length());

        Question q = questions.get(pos);
        if(q instanceof QuestionTrueOrFalse){
            QuestionTrueOrFalse qtf = (QuestionTrueOrFalse) q;
            PuzzleTrueFalse ptf = new PuzzleTrueFalse();
            ptf.setQuestion(qtf.getStatement());
            ptf.setAnswer(qtf.isAnwser());
            ptf.setParametersInitialDefault();
            return ptf;
        }

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
