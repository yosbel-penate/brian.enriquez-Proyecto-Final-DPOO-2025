
package model.puzzle;

import model.Position;

import javax.imageio.ImageIO;
import java.io.IOException;

public class PuzzleTrueFalse extends Puzzle{
    private boolean answer;

    public PuzzleTrueFalse(boolean answer, String name, Position position, String symbol, String description, boolean visibility, String id,String question,String questionDescription){
        super(name, position, symbol, description, visibility, id,question,questionDescription);
        this.answer = answer;
    }

    public PuzzleTrueFalse() {
        super();
        this.answer = false;
        
    }


    public boolean isAnswer() {
        return answer;
    }

    public void setAnswer(boolean answer) {
        this.answer = answer;
    }
}
