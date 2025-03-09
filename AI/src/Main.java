import java.util.Collections;
import java.util.Scanner;
import java.util.Vector;
import java.util.concurrent.ThreadLocalRandom;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int numQ ;
        int numR ;
        int numB ;
        int numK ;

        int numOFChromosomes ;
        int numOfGenerations ;

        System.out.println("\u001B[94m" + "Enter the number of the Queens");
        numQ = scanner.nextInt();
        System.out.println("\u001B[94m" + "Enter the number of the Rooks");
        numR = scanner.nextInt();
        System.out.println("\u001B[94m" + "Enter the number of the Bishops");
        numB = scanner.nextInt();
        System.out.println("\u001B[94m" + "Enter the number of the Knights");
        numK = scanner.nextInt();

        System.out.println("\u001B[35m" + "Enter the number of the Generations");
        numOfGenerations = scanner.nextInt();

        System.out.println("\u001B[35m" + "Enter the number of the Chromosomes");
        numOFChromosomes = scanner.nextInt();

        Vector<Generation> all = new Vector<Generation>();

        all.add(new Generation(numQ,numR,numB,numK,numOFChromosomes));
       // all.get(0).display();

        for (int i = 0; i < numOfGenerations - 1 ; i++) {
            all.add(all.get(i).newGeneration()) ;
        }

        int indexOfMin = 0 ;

        for (int i = 1; i < all.size() ; i++) {
            if (all.get(i).minimal()<all.get(indexOfMin).minimal()) {
                indexOfMin = i;
            }
        }



        System.out.println("\u001B[94m" + "##########################################################");
        System.out.println("\u001B[94m" + "##########################################################");

        for (int i = 0; i < all.size(); i++) {
            System.out.println("generation number "+(i+1));
            all.get(i).display();
        }


        System.out.println("\n\u001B[92m" + "the best solve is :");
        all.get(indexOfMin).displayTheMin();


        scanner.close();
    }

    public static int rend(){
        return ThreadLocalRandom.current().nextInt(1, 101);
    }

}