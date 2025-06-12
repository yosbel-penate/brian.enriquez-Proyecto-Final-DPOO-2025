package model.hero;

import java.io.IOException;

import javax.imageio.ImageIO;


import model.Element;
import model.Position;
import util.ListArray;

public class CucumberShadow extends Hero {

    public CucumberShadow(int healthPoints, int damage, int resistence, int actionRank, int damageS, float damageC, String name, int actionRankS, int moveRank, Position position, String symbol, String description, boolean visibility, String id, String skillname) {
        super(healthPoints, damage, resistence, actionRank, damageS, damageC, name, actionRankS, moveRank, position, symbol, description, visibility, id, skillname);
        this.healthPoints = 45;
        this.actionRank = 2;
        this.actionRankS = 2;
        this.resistence = 10;
        this.damage = 20;
        this.damageS = 30;
        this.description = "Un ninja que ha perfeccionado el arte del sigilo";
        this.moveRank = 4;
        this.name = "Cucumber Shadow";
        this.visibility = true;
        this.skillname = "Silent Cut";

    }

    public CucumberShadow(String name, String description, String symbol, int healthPoints) {
        super(name, description, symbol,healthPoints);

    }


    @Override
    public void setParametersInitialDefault() {
    	try {
 			this.symbol=ImageIO.read(getClass().getResource("/resource/heros/CucumberShadow.png"));
 		} catch (IOException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 			this.symbol = null;
 		}
        
    //    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        this.healthPoints = 45;
        this.actionRank = 2;
        this.actionRankS = 2;
        this.resistence = 10;
        this.damage = 20;
        this.damageS = 30;
        this.description = "Un ninja que ha perfeccionado el arte del sigilo";
        this.moveRank = 4;
        this.name = "Cucumber Shadow";
        this.visibility = true;
        this.skillname = "Silent Cut";
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

    @Override
    public Character ActivatinSkill() {
        int tmp = this.damage;
        this.damage = this.damageS;
        this.damageS =tmp;
        return null;
    }
}

