import java.util.Collections;
import java.util.Vector;

public class Generation {

    private Vector<Chromosome> gen ;
    int allAttacks ;
    int num ;
    int indexOfMin ;

    public Generation() {
        this.gen = new Vector<Chromosome>();
    }

    public void setChromosome(Chromosome gen) {
        this.gen.add(gen);
    }

    public void setNum(int num) {
        this.num = num;
    }

    public Generation(int numQueens,int numRooks,int numBishops,int numKings,int num) {
        this.gen = new Vector<Chromosome>();
        this.num = num;

        for (int i = 0; i < num; i++) {
            this.gen.add(new Chromosome(numQueens,numRooks,numBishops,numKings));
        }

        calculNumberOfAttacks();
        theMin();

    }

    private void calculNumberOfAttacks() {
        int x = 0 ;
        for (int i = 0; i < this.gen.size(); i++) {
            x+= this.gen.get(i).getNumAttacks() ;
        }
        this.allAttacks = x ;
    }

    public Generation newGeneration() {

        Generation newGen = new Generation();
        Vector<Double> fitness = new Vector<Double>(this.num) ;


        for (int i = 0; i < this.gen.size(); i++) {
            if (i == 0)
            fitness.add( (this.gen.get(i).getNumAttacks()/this.allAttacks));
            else{
                fitness.add( (this.gen.get(i).getNumAttacks()/this.allAttacks)+fitness.get(i-1));
            }
        }

        Collections.sort(fitness);

        //################################################/////////////
        for (int k = 0; k < this.num/2; k++)
        {
            double r;

            Chromosome[] c = new Chromosome[2];

            for (int i = 0; i < 2; i++) {
                r = Main.rend();
                for (int j = 0; j < fitness.size(); j++) {
                    if (r/100 <= fitness.get(j)) {
                        c[i] = this.gen.get(j);
                        break;
                    }
                }
                if (c[i] == null){
                    c[i] = this.gen.get(this.gen.size()-1);
                }
            }
            Pair neww = new Pair(c[0], c[1]);
            newGen.setChromosome(neww.cromosone1);
            newGen.setChromosome(neww.cromosone2);
        }

        //###########################################################

        newGen.setNum(this.num);
        newGen.calculNumberOfAttacks();
        newGen.theMin();
        return newGen ;
    }

    public void theMin(){
        int index = 0 ;
        for (int i = 1; i < this.gen.size(); i++) {
            if (this.gen.get(i).getNumAttacks() < this.gen.get(index).getNumAttacks()) {
                index = i;
            }
        }
        this.indexOfMin = index;
    }

    public void display(){

        for (int i = 0; i < this.gen.size(); i++) {
            if (i == this.indexOfMin){
                System.out.println("\u001B[94m" + "\nthe min of this generation\n" + "#########################################################");
                this.gen.get(i).display();
                System.out.println("\u001B[94m" +"#########################################################");
            }
            else {
                this.gen.get(i).display();
            }
        }
    }

    public Double minimal(){
        return  this.gen.get(this.indexOfMin).getNumAttacks() ;
    }

    public void displayTheMin(){
        this.gen.get(this.indexOfMin).display();
    }

}
