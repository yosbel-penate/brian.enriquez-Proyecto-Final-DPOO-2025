
package model.puzzle;

import java.io.IOException;

import javax.imageio.ImageIO;

import model.Element;
import model.Position;

public class Puzzle extends Element{
    protected String question;
    protected String questionDescription;
    public Puzzle(String name, Position position, String symbol, String description, boolean visibility, String id,String question, String questionDescription) {
        super(name, position, symbol, description, visibility, id);
        this.question=question;
        this.questionDescription=questionDescription;
    }

    public Puzzle() {
        super();
        question=null;
    }

    public void setParametersInitialDefault() {
    	try {
 			this.symbol=ImageIO.read(getClass().getResource("/resource/puzzles/Block.png"));
 		} catch (IOException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 			this.symbol = null;
 		}
    }

    public String getQuestion() {
            return question;
        }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getQuestionDescription() {
        return questionDescription;
    }

    public void setQuestionDescription(String questionDescription) {
        this.questionDescription = questionDescription;
    }
   
    
    
    
    
}
