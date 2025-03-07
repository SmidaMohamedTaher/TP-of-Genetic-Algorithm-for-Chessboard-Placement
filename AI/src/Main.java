import java.util.concurrent.ThreadLocalRandom;

public class Main {
    public static void main(String[] args) {

        Chromosome chromosome = new Chromosome(4,2,2,2);
        chromosome.display();

    }

    public static int rend(){
        return ThreadLocalRandom.current().nextInt(1, 101);
    }

}