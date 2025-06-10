package model.hero;

import java.io.IOException;

import javax.imageio.ImageIO;


import model.Position;
import util.ListArray;

public class CornSniper extends Hero {

    public CornSniper(int healthPoints, int damage, int resistence, int actionRank, int damageS, float damageC, String name, int actionRankS, int moveRank, Position position, String symbol, String description, boolean visibility, String id,String skillname) {
        super(healthPoints, damage, resistence, actionRank, damageS, damageC, name, actionRankS, moveRank, position, symbol, description, visibility, id,skillname);
        this.healthPoints = 40;
        this.actionRank = 4;
        this.actionRankS = 6;
        this.resistence = 10;
        this.damage = 20;
        this.damageS = 40;
        this.description = "Una mazorca de maís cuya precisión es legendaria";
        this.moveRank = 2;
        this.name = "Corn Sniper";
        this.visibility = true;
        this.skillname="Corn Shoot";
    }

    public CornSniper(String name, String description, String symbol, int healthPoints) {
        super(name, description, symbol,healthPoints);

    }


    @Override
    public void setParametersInitialDefault() {
    	try {
 			this.symbol=ImageIO.read(getClass().getResource("/resource/heros/CornSniper.png"));
 		} catch (IOException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 			this.symbol = null;
 		}
        
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        this.healthPoints = 40;
        this.actionRank = 4;
        this.actionRankS = 6;
        this.resistence = 10;
        this.damage = 20;
        this.damageS = 40;
        this.description = "Una mazorca de maís cuya precisión es legendaria";
        this.moveRank = 2;
        this.name = "Corn Sniper";
        this.visibility = true;
        this.skillname="Corn Shoot";
    }


   // @Override
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

   // @Override
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
    public Character Attacking() {
        return null;
    }

    //@Override
    public Character Moving(ListArray<Position> movs, Position posPuzle) {
        return null;
    }

    //@Override
    public Character ActivatinSkill() {
        return null;
    }
}
