package model.hero;

import java.io.IOException;

import javax.imageio.ImageIO;


import model.Element;
import model.Position;
import util.ListArray;

public class GarlicWarlock extends Hero {

    public GarlicWarlock(int healthPoints, int damage, int resistence, int actionRank, int damageS, float damageC, String name, int actionRankS, int moveRank, Position position, String symbol, String description, boolean visibility, String id, String skillname) {
        super(healthPoints, damage, resistence, actionRank, damageS, damageC, name, actionRankS, moveRank, position, symbol, description, visibility, id, skillname);
        this.healthPoints = 40;
        this.actionRank = 3;
        this.actionRankS = 3;
        this.resistence = 10;
        this.damage = 10;
        this.damageS = 15;
        this.description = "Un maestro en el envenenamiento";
        this.moveRank = 2;
        this.name = "Garlic Warlock";
        this.visibility = true;
        this.skillname = "Poison Gas";
    }

    public GarlicWarlock(String name, String description, String symbol, int healthPoints) {
        super(name, description, symbol,healthPoints);

    }

    @Override
    public void setParametersInitialDefault() {
    	try {
 			this.symbol=ImageIO.read(getClass().getResource("/resource/heros/GarlicWarlock.png"));
 		} catch (IOException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 			this.symbol = null;
 		}
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        this.healthPoints = 40;
        this.actionRank = 3;
        this.actionRankS = 3;
        this.resistence = 10;
        this.damage = 10;
        this.damageS = 15;
        this.description = "Un maestro en el envenenamiento";
        this.moveRank = 2;
        this.name = "Garlic Warlock";
        this.visibility = true;
        this.skillname = "Poison Gas";
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

    @Override
    public Position Attacking(ListArray<Position> posibleCellAction, ListArray<Element> enemys) {
        return null;
    }
    @Override
    public Character Moving(ListArray<Position> movs, Position posPuzle) {
        return null;
    }
    public Character ActivatinSkill() {
        int tmp = this.damage;
        this.damage = this.damageS;
        this.damageS =tmp;
        return null;
    }
}
