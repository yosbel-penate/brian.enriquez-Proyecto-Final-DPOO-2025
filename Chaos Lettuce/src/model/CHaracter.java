package model;


import java.util.Random;

import contract.IAttack;
import contract.IMove;
import contract.ISkill;

public abstract class CHaracter extends Element implements IAttack, IMove, ISkill {

    protected int healthPoints;
    protected int damage;
    protected int resistence;
    protected int actionRank;
    protected int damageS;
    protected float damageC;
    protected int actionRankS;
    protected int moveRank;
    protected String skillname;
    private Random rand = new Random(System.nanoTime());
   
    public CHaracter(int healthPoints, int damage, int resistence, int actionRank, int damageS, float damageC, String name, int actionRankS, int moveRank, Position position, String symbol, String description, boolean visibility, String id,String skillname) {
        super(name, position, symbol, description, visibility, id);
        this.healthPoints = healthPoints;
        this.damage = damage;
        this.resistence = resistence;
        this.actionRank = actionRank;
        this.damageS = damageS;
        this.damageC = damageC;
        this.name = name;
        this.actionRankS = actionRankS;
        this.moveRank = moveRank;
        this.skillname=skillname;
    }


    public CHaracter(String name, String description, String symbol, int healthPoints) {
        super(name, description, symbol);
        this.healthPoints = healthPoints;
    }

    public CHaracter() {

    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public void setHealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getResistence() {
        return resistence;
    }

    public void setResistence(int resistence) {
        this.resistence = resistence;
    }

    public int getActionRank() {
        return actionRank;
    }

    public void setActionRank(int actionRank) {
        this.actionRank = actionRank;
    }

    public int getDamageS() {
        return damageS;
    }

    public void setDamageS(int damageS) {
        this.damageS = damageS;
    }

    public float getDamageC() {
        return damageC;
    }

    public void setDamageC(float damageC) {
        this.damageC = damageC;
    }

    public int getActionRankS() {
        return actionRankS;
    }

    public void setActionRankS(int actionRankS) {
        this.actionRankS = actionRankS;
    }

    public int getMoveRank() {
        return moveRank;
    }

    public void setMoveRank(int moveRank) {
        this.moveRank = moveRank;
    }



    public void attacking(CHaracter target){

        int random=rand.nextInt(10);
int realDamage=this.damage-target.getResistence();
if(realDamage<0){
    realDamage=0;
}
else if(random==0){
    realDamage=0;
    }
else if(random==2||random==3){
            realDamage=realDamage+10;
            
            }
else{
    realDamage=realDamage;
}
int newHealth = target.getHealthPoints() - realDamage;
        if (newHealth < 0) {
            newHealth = 0;
        }
        target.setHealthPoints(newHealth);
}


    public abstract void setParametersInitialDefault();
}

    




