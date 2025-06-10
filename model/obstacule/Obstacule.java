
package model.obstacule;

import java.io.IOException;

import javax.imageio.ImageIO;

import model.Element;
import model.Position;

public class Obstacule extends Element{

    public Obstacule(String name, Position position, String symbol, String description, boolean visibility, String id) {
        super(name, position, symbol, description, visibility, id);
    }

    public Obstacule() {
        super();
    }

    @Override
    public void setParametersInitialDefault() {
    	try {
 			this.symbol=ImageIO.read(getClass().getResource("/resource/obstacules/rock.png"));
 		} catch (IOException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 			this.symbol = null;
 		}
    }
}
