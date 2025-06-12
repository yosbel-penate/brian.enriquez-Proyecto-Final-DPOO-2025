package model.bonus;
import java.io.IOException;

import javax.imageio.ImageIO;


import model.CHaracter;
import model.Position;

public class InfestedSoil extends Bonus {
private int dcA;
    public InfestedSoil(String BonusDescription, String name, Position position, String symbol, String description, boolean visibility, String id) {
        super("Tierras infestadas que merman las fuerzas", "Suelo infestado", position, symbol, "Disminuye el ataque en 10", true, "IS001");
        dcA=10;
    }

    public InfestedSoil(String BonusDescription) {
        super(BonusDescription);
        dcA=10;
    }

    public InfestedSoil() {
        super();
        dcA=10;
    }

    @Override
    public void setParametersInitialDefault() {
    	try {
			this.symbol=ImageIO.read(getClass().getResource("/resource/bonus/InfestedSoil.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			this.symbol = null;
		}

    }

    @Override
    public void applyBonus(CHaracter X) {
        System.out.println(X.getHealthPoints()+ X.getName());
        this.DecreaseAtt(X);
        System.out.println(X.getHealthPoints()+ X.getName());
    }

    public int getDcA() {
        return dcA;
    }

    public void setDcA(int dcA) {
        this.dcA = dcA;
    }
    public void DecreaseAtt(CHaracter x){
        int nwDa=x.getDamage();
        x.setDamage(nwDa-dcA);
        x.setDamageS(x.getDamageS()-dcA);
        //Añadir despues una duración de 3 turnos
        this.visibility=false;
    }
}
