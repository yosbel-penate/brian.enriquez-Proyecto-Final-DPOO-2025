
package model.bonus;

import java.io.IOException;

import javax.imageio.ImageIO;


import model.CHaracter;
import model.Position;

public class DeadlyDrougth extends Bonus {
private int reHP;
  public DeadlyDrougth(String BonusDescription, String name, Position position, String symbol, String description, boolean visibility, String id) {
    super("Una sequía causada por los mutantes", "Sequía Mortal", position, symbol, "Disminuye la vida en 50", true, "BDD01");
      reHP=50;
  }

  public DeadlyDrougth() {
    super();
      reHP=50;
  }

  @Override
  public void setParametersInitialDefault() {
	  try {
			this.symbol=ImageIO.read(getClass().getResource("/resource/bonus/DeadlyDrougth.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			this.symbol = null;
		}


  }

    @Override
    public void applyBonus(CHaracter X) {
        System.out.println(X.getHealthPoints()+ X.getName());
        System.out.println("Este es tuneo q le voy a meter" + reHP);
        this.DecreaseHp(X);
        System.out.println(X.getHealthPoints()+ X.getName());
    }

    public int getReHP() {
        return reHP;
    }
    public void setInHP(int inHP) {
        this.reHP = reHP;
    }
    public void DecreaseHp(CHaracter x){
        int nwHP=x.getHealthPoints();
        x.setHealthPoints(nwHP-reHP);

        if (nwHP==0||nwHP<0){
            nwHP=0;
            x.setHealthPoints(nwHP);
        }

        this.visibility=false;
    }
}
