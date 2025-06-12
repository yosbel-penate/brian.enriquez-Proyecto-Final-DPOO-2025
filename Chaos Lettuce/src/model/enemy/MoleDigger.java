package model.enemy;

import exception.EmptyListException;
import model.CHaracter;
import model.Element;
import model.Position;
import util.ListArray;

import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public class MoleDigger extends Enemy {

    public MoleDigger(int healthPoints, int damage, int resistence, int actionRank, int damageS, float damageC, String name, int actionRankS, int moveRank, Position position, String symbol, String description, boolean visibility, String id, String skillname) {
        super(healthPoints, damage, resistence, actionRank, damageS, damageC, name, actionRankS, moveRank, position, symbol, description, visibility, id, skillname);
        this.healthPoints = 35;
        this.actionRank = 3;
        this.actionRankS = 3;
        this.resistence = 10;
        this.damage = 12;
        this.damageS = 18;
        this.description = "Un topo que daña a sus rivales atacando desde el suelo";
        this.moveRank = 2;
        this.name = "Mole Digger";
        this.visibility = true;
        this.skillname = "Dig";

    }

    public MoleDigger() {
        super();
    }

    //@Override
    public void attacking() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    //@Override
    public ListArray<Position> posibleRangeAttacking() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    //@Override
    public void moving() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    //@Override
    public ListArray<Position> posibleRangeMoving() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    //@Override
    public boolean isEnableSkill() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    //@Override
    public ListArray<Position> posibleRangeSkill() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    //@Override
    public void skill() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    //@Override
    public Position Attacking(ListArray<Position> posibleCellAction, ListArray<Element> heros) {
        Position pos = null;
        boolean attack=false;
        for(int i=0;i<posibleCellAction.length() && !attack;i++){
            try {
                Position pos1=posibleCellAction.get(i);
                for(int j=0;j<heros.length() && !attack;j++){
                    Element e = heros.get(j);
                    if(e.getPosition().equals(pos1)){
                        if (e instanceof CHaracter) {
                            CHaracter ch=(CHaracter) e;
                            this.attacking(ch);
                            attack=true;
                        }

                    }
                }
            } catch (EmptyListException e) {
                throw new RuntimeException(e);
            }

        }
        if(!attack){
            pos=new Position();

        }
        return pos;

    }

    @Override
    public Character Moving(ListArray<Position> movs, Position posPuzle) {
        Position newPos = null ;
        if(movs.length()>0){
            Random rand = new Random();
            int pos = rand.nextInt(movs.length());
            try {
                newPos = movs.get(pos);
                this.setPosition(newPos);
            } catch (EmptyListException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }

    @Override
    public Character ActivatinSkill() {
        return null;
    }

    @Override
    public void setParametersInitialDefault() {

    	try {
			this.symbol=ImageIO.read(getClass().getResource("/resource/enemys/topo_excavador.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			this.symbol = null;
		}
        
        this.healthPoints = 35;
        this.actionRank = 3;
        this.actionRankS = 3;
        this.resistence = 10;
        this.damage = 12;
        this.damageS = 18;
        this.description = "Un topo que daña a sus rivales atacando desde el suelo";
        this.moveRank = 2;
        this.name = "Mole Digger";
        this.visibility = true;
        this.skillname = "Dig";
    }
}
