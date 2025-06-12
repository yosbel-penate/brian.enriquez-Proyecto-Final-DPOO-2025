package model.puzzle;

import model.Position;

public class PuzzleTrueFalse extends Puzzle {
    private boolean answer;

    public PuzzleTrueFalse(boolean answer, String name, Position position, String symbol, String description, boolean visibility, String id, String question, String questionDescription) {
        super(name, position, symbol, description, visibility, id, question, questionDescription);
        this.answer = answer;
    }

    public PuzzleTrueFalse() {
        this.answer = false;
    }

    public void setParametersInitialDefault() {
    }
}
