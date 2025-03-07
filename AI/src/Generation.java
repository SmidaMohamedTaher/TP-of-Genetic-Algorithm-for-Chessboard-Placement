import java.util.Vector;

public class Generation {

    private Vector<Chromosome> gen ;
    int allAttacks ;
    int num ;

    public Generation() {
        this.gen = new Vector<Chromosome>();
    }

    public void setChromosome(Chromosome gen) {
        this.gen.add(gen);
    }

    public Generation(int numQueens,int numRooks,int numBishops,int numKings,int num) {
        this.gen = new Vector<Chromosome>();
        this.num = num;

        for (int i = 0; i < num; i++) {
            this.gen.add(new Chromosome(numQueens,numRooks,numBishops,numKings));
        }
        int x = 0 ;
        for (int i = 0; i < this.gen.size(); i++) {
            x+= this.gen.get(i).getNumAttacks() ;
        }

        this.allAttacks = x ;
    }

    public Generation newGeneration() {
        Vector<Integer> v = new Vector<Integer>(this.num) ;



        return null ;
    }

}
