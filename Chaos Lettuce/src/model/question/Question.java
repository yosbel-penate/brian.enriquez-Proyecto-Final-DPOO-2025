package model.question;

public class Question {
    protected String statement;

    public Question(String statement) {
        this.statement = statement;
    }

    public Question() {
    }

    public String getStatement() {
        return statement;
    }

    public void setStatement(String statement) {
        this.statement = statement;
    }
}
