
package model.hero;

import java.io.IOException;

import javax.imageio.ImageIO;

import model.Element;
import model.Position;
import util.ListArray;

public class BomberTomato extends Hero {
private double selfdamage=0.10;
    public BomberTomato(int healthPoints, int damage, int resistence, int actionRank, int damageS, float damageC, String name, int actionRankS, int moveRank, Position position, String symbol, String description, boolean visibility, String id,String skillname,double selfdamage) {
        super(healthPoints, damage, resistence, actionRank, damageS, damageC, name, actionRankS, moveRank, position, symbol, description, visibility, id,skillname);
        this.healthPoints=50;
        this.actionRank=1;
        this.actionRankS=3;
        this.resistence=10;
        this.damage=25;
        this.damageS=40;
        this.description="Un tomate que daña a los enemigos explotando";
        this.moveRank= 2;
        this.name="Bomber Tomato";
        this.visibility=true;
        this.selfdamage=selfdamage;
        this.skillname="Acid Explotion";
    }

    public BomberTomato(String name, String description, String symbol, int healthPoints) {
        super(name, description,symbol,healthPoints);

    }

    @Override
    public void setParametersInitialDefault() {
        try {
			this.symbol=ImageIO.read(getClass().getResource("/resource/heros/BomberTomato.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			this.symbol = null;
		}
        this.description="Un tomate que daña a los enemigos explotando";
        this.healthPoints=50;
        this.actionRank=1;
        this.actionRankS=3;
        this.resistence=10;
        this.damage=25;
        this.damageS=40;
        this.moveRank= 2;
        this.name="Bomber Tomato";
        this.visibility=true;
        this.selfdamage=selfdamage;
        this.skillname="Acid Explotion";
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

    @Override
    public Position Attacking(ListArray<Position> posibleCellAction, ListArray<Element> enemys) {
        return null;
    }

    //@Override
    public Character Moving(ListArray<Position> movs, Position posPuzle) {
        return null;
    }

    //@Override
    public Character ActivatinSkill() {
        int tmp = this.damage;
        this.damage = this.damageS;
        this.damageS =tmp;
        this.healthPoints-=healthPoints*selfdamage;
        return null;
    }
}

