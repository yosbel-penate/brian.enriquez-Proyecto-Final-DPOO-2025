package model.enemy;

import exception.EmptyListException;
import model.Position;
import util.ListArray;

import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public class ToxicMushroom extends Enemy {

    public ToxicMushroom(int healthPoints, int damage, int resistence, int actionRank, int damageS, float damageC, String name, int actionRankS, int moveRank, Position position, String symbol, String description, boolean visibility, String id, String skillname) {
        super(healthPoints, damage, resistence, actionRank, damageS, damageC, name, actionRankS, moveRank, position, symbol, description, visibility, id, skillname);
        this.healthPoints = 40;
        this.actionRank = 3;
        this.actionRankS = 3;
        this.resistence = 10;
        this.damage = 15;
        this.damageS = 20;
        this.description = "Hongo tóxico que solo vive para hacer sufrir a sus víctimas";
        this.moveRank = 2;
        this.name = "Toxic Mushroom";
        this.visibility = true;
        this.skillname = "Poison Spores";

    }

    public ToxicMushroom() {
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

   // @Override
    public ListArray<Position> posibleRangeSkill() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   // @Override
    public void skill() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Character Attacking() {
        return null;
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
			this.symbol=ImageIO.read(getClass().getResource("/resource/enemys/01.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			this.symbol = null;
		}
        
        this.healthPoints = 40;
        this.actionRank = 3;
        this.actionRankS = 3;
        this.resistence = 10;
        this.damage = 15;
        this.damageS = 20;
        this.description = "Hongo tóxico que solo vive para hacer sufrir a sus víctimas";
        this.moveRank = 2;
        this.name = "Toxic Mushroom";
        this.visibility = true;
        this.skillname = "Poison Spores";
    }
}
