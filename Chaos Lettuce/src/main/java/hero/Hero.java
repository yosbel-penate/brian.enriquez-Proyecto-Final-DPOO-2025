
package model.hero;

import model.CHaracter;
import model.Position;

public abstract class Hero extends CHaracter {

    public Hero(int healthPoints, int damage, int resistence, int actionRank, int damageS, float damageC, String name, int actionRankS, int moveRank, Position position, String symbol, String description, boolean visibility, String id,String skillname) {
        super(healthPoints, damage, resistence, actionRank, damageS, damageC, name, actionRankS, moveRank, position, symbol, description, visibility, id,skillname);
    }

    public Hero(String name, String description, String symbol, int healthPoints) {
        super(name, description, symbol,healthPoints);

    }
    
}
