import java.util.concurrent.ThreadLocalRandom;

public class Pair {
    public Chromosome cromosone1 ;
    public Chromosome cromosone2 ;

    public Pair(Chromosome chromosome1, Chromosome chromosome2) {
        this.cromosone1 = chromosome1;
        this.cromosone2 = chromosome2;

        int n = Main.rend() ;
        Chromosome c1 ;
        Chromosome c2 ;
        if (n <= 80){
            Cell []t1 = cromosone1.getTable() ;
            Cell []t2 = cromosone2.getTable() ;

            Cell []t3 = new Cell[64] ;
            Cell []t4 = new Cell[64] ;

            for (int i = 0; i < 32; i++) {
                t3[i] = t1[i] ;
                t4[i] = t2[i] ;
            }

            for (int i = 32; i < 64; i++) {
                t3[i] = t2[i] ;
                t4[i] = t1[i] ;
            }

            c1 = new Chromosome(t3) ;
            c2 = new Chromosome(t4) ;

        }else{
            c2 = cromosone1;
            c1 = cromosone2;
        }

        if (Main.rend() >=91){
            int n1 = ThreadLocalRandom.current().nextInt(0, 64) ;
            int n2 = ThreadLocalRandom.current().nextInt(0, 64) ;
            Cell t = c1.getTable()[n1] ;
            c1.getTable()[n1] = c1.getTable()[n2] ;
            c1.getTable()[n2] = t ;
        }

        if (Main.rend() >=91){
            int n1 = ThreadLocalRandom.current().nextInt(0, 64) ;
            int n2 = ThreadLocalRandom.current().nextInt(0, 64) ;
            Cell t = c1.getTable()[n1] ;
            c2.getTable()[n1] = c2.getTable()[n2] ;
            c2.getTable()[n2] = t ;
        }

        c1.calculNumAttacks();
        c2.calculNumAttacks();
        this.cromosone1 = c1 ;
        this.cromosone2 = c2 ;

    }

//    public void Crossover(){
//
//    }


}
