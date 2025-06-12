package model.question;

public class QuestionTrueOrFalse extends Question{
    private boolean anwser;

    public QuestionTrueOrFalse(String statement, boolean anwser) {
        super(statement);
        this.anwser = anwser;
    }

    public QuestionTrueOrFalse(boolean anwser) {
        this.anwser = anwser;
    }

    public boolean isAnwser() {
        return anwser;
    }

    public void setAnwser(boolean anwser) {
        this.anwser = anwser;
    }

    @Override
    public String toString() {
        return this.statement+" ->"+this.anwser;
    }
}
