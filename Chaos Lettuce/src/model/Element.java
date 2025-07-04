
package model;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public abstract class Element {
    protected String name;
    protected Position position;
    protected BufferedImage symbol;
    protected String description;
    protected boolean visibility;
    protected String id;

    public Element(String name, Position position, String symbol, String description, boolean visibility, String id) {
        this.name = name;
        this.position = position;
        try {
			this.symbol = ImageIO.read(getClass().getResource(symbol));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			this.symbol = null;
		}
        this.description = description;
        this.visibility = visibility;
        this.id = id;
    }

    public Element() {
         this.name = null;
        this.position = null;
        this.symbol = null;
        this.description = null;
        this.visibility = false ;
        this.id = null;
    }

    public Element(String name, String description,String symbol) {
        this.name = name;
        this.description = description;
        try {
			this.symbol = ImageIO.read(getClass().getResource(symbol));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			this.symbol = null;
		}
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public BufferedImage getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
    	try {
			this.symbol = ImageIO.read(getClass().getResource(symbol));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			this.symbol = null;
		}
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isVisibility() {
        return visibility;
    }

    public void setVisibility(boolean visibility) {
        this.visibility = visibility;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public abstract void setParametersInitialDefault();
}

