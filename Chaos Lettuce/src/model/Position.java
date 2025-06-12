
package model;


public class Position {
    private int row;
    private int column;

    public Position(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public Position() {
        this.row=-1;
        this.column=-1;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public boolean equals(Object obj) {
        if(obj instanceof Position)
        {
            Position x = (Position) obj;
            if(this.row == x.getRow() && this.column == x.getColumn())
                return true;
            return false;
        }
        else
            return false;

    }

    @Override
    public String toString() {
        return "Row: "+this.row+" Column:"+this.column;
    }
}
