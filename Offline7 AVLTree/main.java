import java.io.*;
import java.util.Objects;

class Main {

    public static void main(String[] args) {

        avl tree = new avl();

        try {
            BufferedReader br = new BufferedReader(new FileReader("input.txt"));

            String str;
            while ((str = br.readLine()) != null) {

                String[] currentInput = str.split(" ");

                if (Objects.equals(currentInput[0], "I")) {
                    tree.node = tree.insertNew(Integer.parseInt(currentInput[1]), tree.node);
                    tree.getPreOrder(tree.node);
                    System.out.println(" ");
                } else if (Objects.equals(currentInput[0], "F")) {
                    if(tree.findValue(Integer.parseInt(currentInput[1]), tree.node)) System.out.println("True");
                    else System.out.println("False");
                } else if (Objects.equals(currentInput[0], "D")) {
                    tree.node=tree.deleteNode( Integer.parseInt(currentInput[1]), tree.node);
                    tree.getPreOrder(tree.node);
                }
                System.out.println();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
