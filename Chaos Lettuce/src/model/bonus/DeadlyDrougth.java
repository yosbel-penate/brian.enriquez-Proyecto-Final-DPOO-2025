package model.bonus;

import java.io.IOException;
import javax.imageio.ImageIO;
import model.Position;

public class DeadlyDrougth extends Bonus {
    public DeadlyDrougth(String BonusDescription, String name, Position position, String symbol, String description, boolean visibility, String id) {
        super(BonusDescription, name, position, symbol, description, visibility, id);
    }

    public DeadlyDrougth() {
    }

    public void setParametersInitialDefault() {
        try {
            this.symbol = ImageIO.read(this.getClass().getResource("/resource/bonus/DeadlyDrougth.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
            this.symbol = null;
        }

    }
}
