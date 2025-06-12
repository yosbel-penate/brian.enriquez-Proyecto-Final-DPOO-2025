
package model.bonus;

import java.io.IOException;

import javax.imageio.ImageIO;


import model.CHaracter;
import model.Position;

public class PurifiedWater extends Bonus{
private int inHP;
    public PurifiedWater(String BonusDescription, String name, Position position, String symbol, String description, boolean visibility, String id) {
        super("Agua Sagrada que elimina las impurezas y restaura la vitalidad", "Agua Purufucada", position, symbol, "Aumenta la vitalidad en 50", true, "PW001");
inHP=50;
    }

    public PurifiedWater() {
        super();
        inHP=50;
    }

    @Override
    public void setParametersInitialDefault() {
        
        try {
			this.symbol=ImageIO.read(getClass().getResource("/resource/bonus/PurifiedWater.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			this.symbol = null;
		}
    }

    @Override
    public void applyBonus(CHaracter X) {
        System.out.println(X.getHealthPoints()+ X.getName());
        this.increaseHP(X);
        System.out.println(X.getHealthPoints()+ X.getName());
    }


    public int getInHP() {
        return inHP;
    }

    public void setInHP(int inHP) {
        this.inHP = inHP;
    }

    public void increaseHP(CHaracter bt){
        int nwHp=bt.getHealthPoints();
        bt.setHealthPoints(nwHp+inHP);
        this.visibility=false;
    }
}
