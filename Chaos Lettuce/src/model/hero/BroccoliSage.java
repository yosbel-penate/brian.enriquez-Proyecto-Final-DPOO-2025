package model.hero;


import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import model.CHaracter;
import model.Element;
import model.Position;
import util.ListArray;

public class BroccoliSage extends Hero {

    public BroccoliSage(int healthPoints, int damage, int resistence, int actionRank, int damageS, float damageC, String name, int actionRankS, int moveRank, Position position, String symbol, String description, boolean visibility, String id, String skillname) {
        super(healthPoints, damage, resistence, actionRank, damageS, damageC, name, actionRankS, moveRank, position, symbol, description, visibility, id, skillname);
        this.healthPoints = 45;
        this.actionRank = 4;
        this.actionRankS = 4;
        this.resistence = 10;
        this.damage = 20;
        this.damageS = 35;
        this.description = "Un sabio cuya presencia llena de vida a sus aliados";
        this.moveRank = 2;
        this.name = "Broccoli Sage";
        this.visibility = true;
        this.skillname = "Flower Storm";

    }

    public BroccoliSage(String name, String description, String symbol, int healthPoints) {
        super(name, description, symbol,healthPoints);

    }

    @Override
    public void setParametersInitialDefault() {
    	 try {
 			this.symbol=ImageIO.read(getClass().getResource("/resource/heros/BroccoliSage.png"));
 		} catch (IOException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 			this.symbol = null;
 		}
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        this.healthPoints = 45;
        this.actionRank = 4;
        this.actionRankS = 4;
        this.resistence = 10;
        this.damage = 20;
        this.damageS = 35;
        this.description = "Un sabio cuya presencia llena de vida a sus aliados";
        this.moveRank = 2;
        this.name = "Broccoli Sage";
        this.visibility = true;
        this.skillname = "Flower Storm";
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
//        Random rand=new Random();
//        int random=rand.nextInt(10);
//
//            int realDamage=this.damageS-target.getResistence();
//            if(realDamage<0){
//                realDamage=0;
//            }
//            else if(random==0){
//                realDamage=0;
//                System.out.println("¡Fallaste!");
//            }
//            else if(random==2||random==3){
//                realDamage=realDamage+10;
//                System.out.println("¡Golpe Crítico!");
//            }
//            else{
//                realDamage=realDamage;
//            }
//            int newHealth = target.getHealthPoints() - realDamage;
//            if (newHealth < 0) {
//                newHealth = 0;
//            }

        return null;
    }


}
