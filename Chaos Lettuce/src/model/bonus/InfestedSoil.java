package model.bonus;

import java.io.IOException;
import javax.imageio.ImageIO;
import model.Position;

public class InfestedSoil extends Bonus {
    public InfestedSoil(String BonusDescription, String name, Position position, String symbol, String description, boolean visibility, String id) {
        super(BonusDescription, name, position, symbol, description, visibility, id);
    }

    public InfestedSoil(String BonusDescription) {
        super(BonusDescription);
    }

    public InfestedSoil() {
    }

    public void setParametersInitialDefault() {
        try {
            this.symbol = ImageIO.read(this.getClass().getResource("/resource/bonus/InfestedSoil.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
            this.symbol = null;
        }

    }
}
