package model.enemy;

import exception.EmptyListException;
import model.Position;
import util.ListArray;

import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public class GiantSlug extends Enemy {

    public GiantSlug(int healthPoints, int damage, int resistence, int actionRank, int damageS, float damageC, String name, int actionRankS, int moveRank, Position position, String symbol, String description, boolean visibility, String id, String skillname) {
        super(healthPoints, damage, resistence, actionRank, damageS, damageC, name, actionRankS, moveRank, position, symbol, description, visibility, id, skillname);
        try {
			this.symbol=ImageIO.read(getClass().getResource("/resource/enemys/01.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			this.symbol = null;
		}
        
        this.healthPoints = 50;
        this.actionRank = 2;
        this.actionRankS = 2;
        this.resistence = 10;
        this.damage = 10;
        this.damageS = 15;
        this.description = "Una babosa que corroe a sus enemigos";
        this.moveRank = 2;
        this.name = "Gigant Slug";
        this.visibility = true;
    }

    public GiantSlug() {
        super();
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
        
        this.healthPoints = 50;
        this.actionRank = 2;
        this.actionRankS = 2;
        this.resistence = 10;
        this.damage = 10;
        this.damageS = 15;
        this.description = "Una babosa que corroe a sus enemigos";
        this.moveRank = 2;
        this.name = "Gigant Slug";
        this.visibility = true;

        this.skillname = "Corrosive Slobber";
    }

    public void attacking() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    public ListArray<Position> posibleRangeAttacking() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    public void moving() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    public ListArray<Position> posibleRangeMoving() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    public boolean isEnableSkill() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

 //   @Override
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
        for(Position p : movs){
            if(p.equals(posPuzle)){
                newPos = p;
            }
        }
        if(newPos == null && movs.length()>0){
            Random rand = new Random();
            int pos = rand.nextInt(movs.length());
            try {
                newPos = movs.get(pos);
                this.setPosition(newPos);
            } catch (EmptyListException e) {
                throw new RuntimeException(e);
            }
        }else{
            this.setPosition(newPos);
        }
        return null;
    }

    @Override
    public Character ActivatinSkill() {
        return null;
    }
}
