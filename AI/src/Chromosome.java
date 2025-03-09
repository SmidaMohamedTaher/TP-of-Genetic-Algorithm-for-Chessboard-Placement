import java.util.concurrent.ThreadLocalRandom;

public class Chromosome {

    Cell []table = new Cell[64];
    int numAttacks ;

    public Chromosome(int numQueens,int numRooks,int numBishops,int numKings) {
        for(int i=0; i<64; i++){
            table[i] = new Cell();
        }
        //ThreadLocalRandom.current().nextInt(1, 101);

        position(numQueens,'Q');
        position(numRooks,'R');
        position(numBishops,'B');
        position(numKings,'K');

        calculNumAttacks();

    }

    public Chromosome (){
        for(int i=0; i<64; i++){
            table[i] = new Cell();
        }
    }

    public Chromosome(Cell []t){
        this.table = t ;
    }

    public void position(int num,char car){

        int n = num/2;
        int x ;
        for(int i=0; i<n; i++){
            x = ThreadLocalRandom.current().nextInt(0, 32);
            if (table[x].getCell() == 'E' ) {
                table[x].setCell(car);
                attack(x,table[x]);
            }
            else{
                i-- ;
            }
        }

        for (int i=0; i<num - n; i++){
            x = ThreadLocalRandom.current().nextInt(32, 64);
            if (table[x].getCell() == 'E' ) {
                table[x].setCell(car);
                attack(x,table[x]);
            }
            else{
                i-- ;
            }
        }
    }

    /**
     *
     * @param indexOfAttacore
     * @param attacker
     * find cell who the piece can attack its
     */
    public void attack(int indexOfAttacore , Cell attacker){
        if (attacker.getCell() == 'Q'){
            attackUpDown(attacker,indexOfAttacore);
            attackDiagonally(attacker,indexOfAttacore);
            attackInLine(attacker,indexOfAttacore);
        }else{
            if (attacker.getCell() == 'R'){
                attackUpDown(attacker,indexOfAttacore);
                attackInLine(attacker,indexOfAttacore);
            }
            else if (attacker.getCell() == 'B'){
                attackDiagonally(attacker,indexOfAttacore);
            }
            else if (attacker.getCell() == 'K'){
                lAttack(attacker,indexOfAttacore);
            }
        }
    }

    /**
     *
     * @param attacore
     * @param x
     * this function to find the cells that this piece (attacore) can attack it (up and down)
     */
    public void attackUpDown(Cell attacore,int x){
        for (int i = x-8; i >= 0; i-=8) {
            this.table[i].setAttacor(attacore);
        }
        for (int i = x+8; i < 64; i+=8) {
            this.table[i].setAttacor(attacore);
        }
    }

    /**
     *
     * @param attacore
     * @param x
     * this function to find the cells that this piece (attacore) can attack it (diagonally)
     */
    public void attackDiagonally(Cell attacore,int x){
        for (int i = x-7; i >= 0; i-=7) {
            this.table[i].setAttacor(attacore);
        }

        for (int i = x+7; i < 64; i+=7) {
            this.table[i].setAttacor(attacore);
        }

        for (int i = x-9; i > 0; i-=9) {
            this.table[i].setAttacor(attacore);
        }

        for (int i = x+9; i < 64; i+=9) {
            this.table[i].setAttacor(attacore);
        }

    }

    public void attackInLine(Cell attacore , int x){
        if (x%8 == 0){
            while((x+1)%8 != 0 && (x+1) <64){
                table[x+1].setAttacor(attacore);
                x++;
            }

        } else if ((x+1)%8 == 0) {
            while((x-1)%8 != 0 && (x-1)>=0 ){
                table[x-1].setAttacor(attacore);
                x-- ;
            }
        }
        else{
            int i = x ;
            while((x+1)%8 != 0){
                table[x+1].setAttacor(attacore);
                x++ ;
            }
            while((i-1)%8 != 0){
                table[i-1].setAttacor(attacore);
                i-- ;
            }
        }
    }

    public void lAttack(Cell attacker,int x){
        int []t = {17,15,10,6} ;
        int []indexs = new int[8];

        for (int i = 0; i < 8; i++) {
            if (i<4){
                indexs[i] = x-t[i];
            }else{
                indexs[i] = x+t[i-4];
            }
        }

        for (int i = 0; i < 8; i++) {
            if (indexs[i]>0 && indexs[i]<64 ){
                table[indexs[i]].setAttacor(attacker);
            }
        }
    }

    public void display(){
        String color;
        for (int i = 0; i < 64; i++) {
            if (i%8 == 0){
                System.out.print("\n");
            }

            switch (table[i].getCell()) {
                case 'Q': color = "\u001B[35m"; break;
                case 'R': color = "\u001B[34m"; break;
                case 'B': color = "\u001B[32m"; break;
                case 'K': color = "\u001B[31m"; break;
                case 'E': color = "\u001B[39m"; break;
                default: color = "\u001B[37m"; break;
            }
            System.out.print( color+table[i].getCell() + " ");
        }
        System.out.println("\n");
        double fitnes =  (1.0/(1+this.numAttacks)) ;
        System.out.println("Number of the attacks : " + fitnes );
    }

    public Cell[] getTable(){
        return table;
    }

    public void setTable(Cell []table){
        this.table = table;
    }

    public void calculNumAttacks(){
        int num = 0 ;

        for (int i = 0; i < 64; i++) {
            if (table[i].getCell() != 'E'){
                num += this.table[i].getNumAttacor();
            }
        }

        int x = 0 ;

        for (int i = 0; i < 64; i++) {
            if (table[i].getCell() != 'E'){
                x += this.table[i].number();
            }
        }

        this.numAttacks = num - x/2 ;
    }

    public Double getNumAttacks(){
        Double i = (double) this.numAttacks ;
        return i;
    }

}
