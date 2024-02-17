import java.util.Random;
import java.util.Scanner;

public class mainForAll {
    static String randomWordGenerator(int n) {
        StringBuffer sb= new StringBuffer(n);
        Random random= new Random();

        for (int i = 0; i < n; i++) {
            sb.append((char)(97+random.nextInt(26)));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n= sc.nextInt();
        String[] input= new String[n];
        Random random= new Random();


        for (int i = 0; i < n; i++) {
            input[i]= randomWordGenerator(7);
        }

        separateChain  sct= new separateChain (1);
        hashDouble hdt = new hashDouble(1);
        hashCustomTable hct = new hashCustomTable(1);

        System.out.println("for 1st hash function: ");
        for (int i = 0; i < n; i++) {
            sct.insert(input[i]);
        }
        for (int i = 0; i < n/10; i++) {
            int s= sct.search(input[random.nextInt(n)]);
        }
        System.out.println("separate chain: ");
        System.out.println(sct.getCollisions());
        System.out.println(sct.getProbes()/(n/10));
        System.out.println(sct.size());


        for (int i = 0; i < n; i++) {
            hdt.insert(input[i]);
        }
        for (int i = 0; i < n/10; i++) {
            int s= hdt.search(input[random.nextInt(n)]);
        }
        System.out.println("double hashing: ");
        System.out.println(hdt.getCollisions());
        System.out.println(hdt.getProbes()/(n/10));
        System.out.println(hdt.size());

        for (int i = 0; i < n; i++) {
            hct.insert(input[i]);
        }
        for (int i = 0; i < n/10; i++) {
            int s= hct.search(input[random.nextInt(n)]);
        }
        System.out.println("custom hashing: ");
        System.out.println(hct.getCollisions());
        System.out.println(hct.getProbes()/(n/10));
        System.out.println(hct.size());

        sct= new separateChain (2);
        hdt = new hashDouble(2);
        hct = new hashCustomTable(2);
        System.out.println("for 2nd hash function: ");

        for (int i = 0; i < n; i++) {
            sct.insert(input[i]);
        }
        for (int i = 0; i < n/10; i++) {
            int s= sct.search(input[random.nextInt(n)]);
        }
        System.out.println("separate chain: ");
        System.out.println(sct.getCollisions());
        System.out.println(sct.getProbes()/(n/10));
        System.out.println(sct.size());


        for (int i = 0; i < n; i++) {
            hdt.insert(input[i]);
        }
        for (int i = 0; i < n/10; i++) {
            int s= hdt.search(input[random.nextInt(n)]);
        }
        System.out.println("double hashing: ");
        System.out.println(hdt.getCollisions());
        System.out.println(hdt.getProbes()/(n/10));
        System.out.println(hdt.size());


        for (int i = 0; i < n; i++) {
            hct.insert(input[i]);
        }
        for (int i = 0; i < n/10; i++) {
            int s= hct.search(input[random.nextInt(n)]);
        }
        System.out.println("custom hashing: ");
        System.out.println(hct.getCollisions());
        System.out.println(hct.getProbes()/(n/10));
        System.out.println(hct.size());




    }
}
