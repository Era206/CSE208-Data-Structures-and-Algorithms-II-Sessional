import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


class Node {

    int key, degree;
    Node parent;
    Node sibling;
    Node child;

    public Node(int k) {

        key = k;
        degree = 0;
        parent = null;
        sibling = null;
        child = null;
    }

   public void print(int order){
       Node temp = this;
       if (order == 0){
           int counter = 1;
           while (temp != null){
               System.out.println("Binomial tree, B" + temp.degree);
               System.out.println("Level " + order + ": " + temp.key);
               if (temp.child != null){
                   temp.child.print(order+1);
               }
               temp = temp.sibling;
               counter++;
           }
           return;
       }

       System.out.print("Level " + order + ": ");

       while (temp !=null){
           System.out.print(temp.key + " ");
           temp = temp.sibling;
       }
       System.out.println();
       temp = this;
       while (temp != null){
           if (temp.child != null){
               temp.child.print(order+1);
           }
           temp = temp.sibling;
       }
   }
}


    class BinomialHeap{

        private static final int INF = -6000000;
        public Node head;

    public BinomialHeap() {
        head = null;
    }

    public BinomialHeap(Node head) {
        this.head = head;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void clear() {
        head = null;
    }


    public int findMax() {
        if (head == null) {
            return INF;
        } else {
            Node min = head;
            Node next = min.sibling;

            while (next != null) {
                if (min.key < next.key) min = next;
                next = next.sibling;
            }

            return min.key;
        }
    }

    // Implemented to test decrease key, runs in O(n) time
    public Node search(int key) {
        List<Node> nodes = new ArrayList<Node>();
        nodes.add(head);
        while (!nodes.isEmpty()) {
            Node curr = nodes.get(0);
            nodes.remove(0);
            if (curr.key == key) {
                return curr;
            }
            if (curr.sibling != null) {
                nodes.add(curr.sibling);
            }
            if (curr.child != null) {
                nodes.add(curr.child);
            }
        }
        return null;
    }

    public void increaseKey(Node node, int newKey) {
        System.out.println("Increased " + node.key + ". The updated value is " + newKey + ".");
        node.key = newKey;
        bubbleUp(node, false);
    }


    private Node bubbleUp(Node node, boolean toRoot) {
        Node parent = node.parent;
        while (parent != null && (parent.key<node.key)) {
            int temp = node.key;
            node.key = parent.key;
            parent.key = temp;
            node = parent;
            parent = parent.parent;
        }
        return node;
    }

    public int extractMax() {
        if (head == null) {
            return INF;
        }

        Node max = head;
        Node maxPrev = null;
        Node next = max.sibling;
        Node nextPrev = max;

        while (next != null) {
            if (next.key > max.key) {
                max = next;
                maxPrev = nextPrev;
            }
            nextPrev = next;
            next = next.sibling;
        }

        removeTreeRoot(max, maxPrev);
        return max.key;
    }

    private void removeTreeRoot(Node root, Node prev) {
        // Remove root from the heap
        if (root == head) {
            head = root.sibling;
        } else {
            prev.sibling = root.sibling;
        }

        // Reverse the order of root's children and make a new heap
        Node newHead = null;
        Node child = root.child;
        while (child != null) {
            Node next = child.sibling;
            child.sibling = newHead;
            child.parent = null;
            newHead = child;
            child = next;
        }
        BinomialHeap newHeap = new BinomialHeap(newHead);

        head = union(newHeap);
    }

        public void insert(int key) {
            System.out.println("Inserted  " + key);
            Node node = new Node(key);
            BinomialHeap tempHeap = new BinomialHeap(node);
            head = union(tempHeap);
        }

    public Node union(BinomialHeap heap) {
        Node newHead = merge(this, heap);

        return newHead;
    }

    private static Node merge(BinomialHeap heap1, BinomialHeap heap2) {
        if (heap1.head == null) {
            return heap2.head;
        }
        else if (heap2.head == null) {
            return heap1.head;
        }
        else {
            Node head;
            Node heap1Next = heap1.head;
            Node heap2Next = heap2.head;

            if (heap1.head.degree < heap2.head.degree) {
                head = heap1.head;
                heap1Next = heap1Next.sibling;
            }
            else if(heap2.head.degree < heap1.head.degree){
                head = heap2.head;
                heap2Next = heap2Next.sibling;
            }
            else{
                Node temp1 = heap1Next,temp2 = heap2Next;
                heap1Next = heap1Next.sibling;
                heap2Next = heap2Next.sibling;
                head = makeNextNode(temp1, temp2);
                if((heap1Next!=null) && (heap1Next.degree==head.degree )&&(heap1Next.key>head.key)){
                    Node temp = head;
                    temp.sibling= heap1Next.sibling;
                    head = heap1Next;
                    heap1Next=temp;
                }
                if((heap2Next!=null) && (heap2Next.degree==head.degree) &&(heap2Next.key>head.key)){
                    Node temp = head;
                    temp.sibling= heap2Next.sibling;
                    head = heap2Next;
                    heap2Next=temp;
                }
            }

            if(head.sibling!=null) head.sibling=null;
            Node tail = head;
           head = tail;

            while (heap1Next != null && heap2Next != null) {
                if ((heap1Next.degree < heap2Next.degree)) {
                    if(tail.degree < heap1Next.degree){
                        tail.sibling = heap1Next;
                        heap1Next = heap1Next.sibling;
                        tail = tail.sibling;
                    }
                    else{
                        Node temp = heap1Next;
                        heap1Next = heap1Next.sibling;
                        tail = makeNextNode(tail, temp);
                    }
                }
                else if(heap2Next.degree < heap1Next.degree) {
                    if(tail.degree < heap2Next.degree){
                        tail.sibling = heap2Next;
                        heap2Next = heap2Next.sibling;
                        tail = tail.sibling;
                    }
                    else{
                        Node temp = heap2Next;
                        heap2Next = heap2Next.sibling;
                        tail = makeNextNode(tail, temp);

                    }
                }
                else{
                        Node temp1 = heap1Next, temp2 = heap2Next;
                        heap1Next = heap1Next.sibling;
                        heap2Next = heap2Next.sibling;
                        tail.sibling = makeNextNode(temp1, temp2);
                        tail = tail.sibling;

                }
            }
            while (heap1Next != null) {
                if(tail.degree==heap1Next.degree){
                    Node temp = heap1Next;
                    heap1Next = heap1Next.sibling;
                    tail=makeNextNode( temp,tail);
                }
                else{
                    tail.sibling=heap1Next;
                    heap1Next = heap1Next.sibling;
                    tail= tail.sibling;

                }
            }

            while(heap2Next != null) {

                if(tail.degree==heap2Next.degree){
                    Node temp= heap2Next;
                    heap2Next = heap2Next.sibling;
                    tail=makeNextNode(temp, tail);
                }
                else{
                    tail.sibling=heap2Next;
                    heap2Next = heap2Next.sibling;
                    tail= tail.sibling;

                }
            }
            return head;
        }
    }

    private static Node makeNextNode(Node node1, Node node2){
        Node node;
        if(node1.key>node2.key){
            node = node1;
            node2.parent = node;
            node2.sibling = node.child;
            node.child= node2;
            node.degree++;
            return node;
        }
        else{
            node = node2;
            node1.parent = node;
            node1.sibling = node.child;
            node.child= node1;
            node.degree++;
            return node;
        }
    }


    public void print() {
        if (head != null) {
            head.print(0);
        }
    }


}
class Main {

    public static void main(String[] args) {

        String IN = "E:\\2-2\\offline6\\offline6_demo.txt";
        try {
            BufferedReader br = new BufferedReader(new FileReader(IN));
            BinomialHeap b = new BinomialHeap();
            while (true) {
                String[] input = br.readLine().split(" ");
                if (input[0].equalsIgnoreCase("INS")) {
                    b.insert(Integer.parseInt(input[1]));
                } else if (input[0].equalsIgnoreCase("INC")) {
                    b.increaseKey(b.search(Integer.parseInt(input[1])), Integer.parseInt(input[2]));
                } else if (input[0].equalsIgnoreCase("EXT")) {
                    System.out.println("ExtractMax returned " + b.extractMax());
                } else if (input[0].equalsIgnoreCase("FIN")) {
                    System.out.println("FindMax returned " + b.findMax());
                } else if (input[0].equalsIgnoreCase("PRI")) {
                    System.out.println("Printing Binomial Heap....");
                    System.out.println("-------------------------------------------");
                    b.print();
                }
                if (input[0].equalsIgnoreCase("end"))
                    break;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
