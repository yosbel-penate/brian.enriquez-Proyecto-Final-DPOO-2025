
package model.bonus;

import model.CHaracter;
import model.Element;
import model.Position;

public abstract class Bonus extends Element{
    protected String BonusDescription;

    public Bonus() {
    }

    public Bonus(String BonusDescription, String name, Position position, String symbol, String description, boolean visibility, String id) {
        super(name, position, symbol, description, visibility, id);
        this.BonusDescription = BonusDescription;
    }

    public Bonus(String BonusDescription) {
        this.BonusDescription = BonusDescription;
    }

    public String getBonusDescription() {
        return BonusDescription;
    }

    public void setBonusDescription(String BonusDescription) {
        this.BonusDescription = BonusDescription;
    }

    public abstract void setParametersInitialDefault();

   public abstract void applyBonus (CHaracter X);
}

