package model.bonus;

import java.io.IOException;
import javax.imageio.ImageIO;
import model.Position;

public class PurifiedWater extends Bonus {
    public PurifiedWater(String BonusDescription, String name, Position position, String symbol, String description, boolean visibility, String id) {
        super(BonusDescription, name, position, symbol, description, visibility, id);
    }

    public PurifiedWater() {
    }

    public void setParametersInitialDefault() {
        try {
            this.symbol = ImageIO.read(this.getClass().getResource("/resource/bonus/PurifiedWater.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
            this.symbol = null;
        }

    }
}
