
import java.util.Scanner;

public class avl {
    class Node{
        int key;
        Node left;
        Node right;

        public Node(int key) {
            this.key = key;
            left=right=null;
        }
    }

    Node node;

    avl()
    {
        this.node = null;
    }

    int max(int x, int y) {
        if(x>y) return x;
        else return y;
    }

    boolean isEmpty(){
        return node ==null;
    }

    //returns the height difference between left and right tree
    int getHeightDifference(Node node) {
        if (node == null)
            return 0;
        int balance = getHeight(node.left) - getHeight(node.right);
        return balance;
    }

    Node rightRotation(Node node){
        Node leftNode = node.left;
        Node leftRightNode = leftNode.right;

        leftNode.right = node;
        node.left = leftRightNode;

        return leftNode;
    }

    Node leftRotation(Node node){
        Node rightNode = node.right;
        Node rightLeftNode = rightNode.left;

        rightNode.left = node;
        node.right = rightLeftNode;

        return rightNode;
    }

    Node insertNew(int value, Node node){
        if(node==null){
            node= new Node(value);
            return node;
        }
        else if(value>node.key){
            node.right=insertNew(value, node.right);
        }
        else if(value<node.key){
            node.left=insertNew(value, node.left);
        }
        else return node;

        int heightDifference = getHeightDifference(node);


        //if left subtree's height is more than 1 time greater than right subtree's height
        if(heightDifference > 1){
            System.out.println("Height invariant violated.");
            System.out.print("After rebalancing:");

            //left left case : key is the left child of node's left child
            if(value < node.left.key){
                return rightRotation(node);
            }

            //left right case : key is right child of node's left child
            if(value > node.left.key){
                node.left= leftRotation(node.left);// now the tree is left left form
                return rightRotation(node);
            }
        }

        //if right subtree's height is more than 1 time greater than left subtree's height
        if(heightDifference < -1){
            System.out.println("Height invariant violated.");
            System.out.print("After rebalancing:");

            //right right case : key is right child of node's right child
            if(value > node.right.key){
                return leftRotation(node);
            }

            //right left case : key is left child of node's right child
            if(value < node.right.key){
                node.right = rightRotation(node.right);// now tree is right right form
                return leftRotation(node);
            }

        }

        return node;
    }


    public boolean findValue(int value, Node node){
        if(node==null) {
            return false;
        }
        if(node.key==value)
            return true;
        else if(value>node.key)
            return findValue(value, node.right);
        else if(value<node.key)
            return findValue(value, node.left);
        else return false;
    }

    Node deleteNode( int value, Node node)
    {

        if (node == null)
            return node;

       if (value < node.key)
            node.left = deleteNode( value, node.left);
        else if (value > node.key)
            node.right = deleteNode(value, node.right);

        else
        {

            // node with only one child or no child
            if ((node.left == null) || (node.right == null))
            {
                Node temp = null;
                if (temp == node.left)
                    temp = node.right;
                else
                    temp = node.left;

                // No child case
                if (temp == null)
                {
                    temp = node;
                    node = null;
                }
                else
                    node = temp;

            }
            else
            {
                Node temp = getMin(node.right);

                node.key = temp.key;

                node.right = deleteNode( temp.key, node.right);
            }
        }

        // If the tree had only one node then return
        if (node == null)
            return node;


        int heightDifference = getHeightDifference(node);

        if(heightDifference > 1){
            System.out.println("Height invariant violated.");
            System.out.print("After rebalancing:");

        //left left case : key is the left child of node's left child
        if(getHeightDifference(node.left) >= 0){
            return rightRotation(node);
        }

        //left right case : key is right child of node's left child
        if(getHeightDifference(node.left) < 0){
            node.left= leftRotation(node.left);// now the tree is left left form
            return rightRotation(node);
        }
    }

    //if right subtree's height is more than 1 time greater than left subtree's height
        if(heightDifference < -1){
            System.out.println("Height invariant violated.");
            System.out.print("After rebalancing:");

        //right right case : key is right child of node's right child
        if(getHeightDifference(node.right) <= 0){
            return leftRotation(node);
        }

        //right left case : key is left child of node's right child
        if(getHeightDifference(node.right) > 0){
            node.right = rightRotation(node.right);// now tree is right right form
            return leftRotation(node);
        }

    }


        return node;
}


    public int getMax(Node root){
        while(root.right!=null){
            root=root.right;
        }
        return root.key;
    }

    Node getMin(Node node){
        Node current = node;

        /* loop down to find the leftmost leaf */
        while (current.left != null)
            current = current.left;

        return current;
    }

    public int getHeight(Node root){
        if(root==null) return 0;
        if(root.left==null&&root.right==null) return 1;
        else{
            int leftHeight = getHeight(root.left);
            int rightHeight = getHeight(root.right);
            if(leftHeight>rightHeight)
                return (leftHeight+1);
            else return (rightHeight+1);
        }
    }



    public void getPreOrder(Node root){

        if(root==null) return;
        System.out.print(root.key);

        if(root.left != null || root.right!=null){
            System.out.print("(");
            getPreOrder(root.left);
            System.out.print(")");
            System.out.print("(");
            getPreOrder(root.right);
            System.out.print(")");
        }

    }



}
