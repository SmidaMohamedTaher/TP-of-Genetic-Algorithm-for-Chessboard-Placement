import java.util.Vector;

public class Cell {

    char cell ;
    Vector attacors ;

    public Cell(char cell) {
        this.cell = cell;
        attacors = new Vector<Cell>() ;
    }

    public Cell(){
        this.cell = 'E';
        attacors = new Vector<Cell>() ;
    }

    public char getCell() {
        return cell;
    }

    public void setCell(char cell) {
        this.cell = cell;
    }

    public void setAttacor(Cell attacor) {
        this.attacors.add(attacor);
    }

}
