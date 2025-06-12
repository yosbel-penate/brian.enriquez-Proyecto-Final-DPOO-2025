package model.bonus;

import java.io.IOException;

import javax.imageio.ImageIO;


import model.CHaracter;
import model.Position;

public class Fertilizer extends Bonus {
private int inA;
    public Fertilizer(String BonusDescription, String name, Position position, String symbol, String description, boolean visibility, String id) {
        super("Fertilizante que fortalece a los héroes", "Fertilizante", position, symbol, "Aumenta el ataque del personaje en 10", true, "F001");
inA=10;
    }


    public Fertilizer() {
        super();
        inA=10;
    }

    @Override
    public void setParametersInitialDefault() {
    	try {
			this.symbol=ImageIO.read(getClass().getResource("/resource/bonus/Fertilizer.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			this.symbol = null;
		}
    }

    @Override
    public void applyBonus(CHaracter X) {
        System.out.println(X.getHealthPoints()+ X.getName());
        this.IncreasAtt(X);
        System.out.println(X.getHealthPoints()+ X.getName());
    }

    public int getInA() {
        return inA;
    }

    public void setInA(int inA) {
        this.inA = inA;
    }
    public void IncreasAtt(CHaracter x){
        int nwDa=x.getDamage();
        x.setDamage(nwDa+inA);
        x.setDamageS(x.getDamageS()+inA);
        //Añadir despues una duración de 3 turnos
        this.visibility=false;
    }
}
