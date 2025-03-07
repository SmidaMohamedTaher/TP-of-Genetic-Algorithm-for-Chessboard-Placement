import java.util.Vector;

public class Cell {

    char cell ;
    Vector<Cell> attacors ;

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

    public int getNumAttacor() {
        return attacors.size();
    }

    public boolean inAtacor(Cell attacor) {
        return attacors.contains(attacor);
    }
    
    public int number(){
        int x = 0 ;

        for (int i = 0; i < this.attacors.size(); i++) {
            if(this.attacors.get(i).inAtacor(this)){
                x++;
            }
        }

        return x ;
    }

}
