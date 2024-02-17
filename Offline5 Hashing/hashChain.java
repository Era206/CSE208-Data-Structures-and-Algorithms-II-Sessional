
import java.util.ArrayList;
import java.util.LinkedList;

import static java.lang.StrictMath.abs;

// Class to represent entire hash table
class separateChain<K, V, linkedList> {
    public ArrayList<LinkedList<hashNode>> arrayList;
    // Current capacity of array list
    private int capacity;
    private int tracker = 0;
    private int hashSerial;
    // Current size of array list
    private int size;
    int collision= 0;
    double probes=0;

    public separateChain(int flag)
    {
        arrayList = new ArrayList<>();
        capacity = Hashing.n;
        hashSerial=flag;
        size = 0;

        for (int i = 0; i < capacity; i++){
            LinkedList<hashNode> list= new LinkedList<>();
            arrayList.add(list);
        }

    }

    public int size() { return size; }
    public boolean isEmpty() { return size() == 0; }
    public int getCollisions() { return collision; }
    public double getProbes() { return probes; }

    // This implements hash function to find index for a key
    private int getArrayIndex(String key)
    {
        int hashCode;
        if(hashSerial==1) hashCode =Hashing.Hash1(key) % capacity;
        else hashCode =Hashing.Hash2(key)% capacity;
        int index = hashCode % capacity;
        index = index < 0 ? index * -1 : index;
        return index;
    }

    // Method to remove a given key
    public int delete(String key)
    {
        int arrayIndex = getArrayIndex(key);
        int hashCode;
        if(hashSerial==1) hashCode =Hashing.Hash1(key)%capacity;
        else hashCode =Hashing.Hash2(key)% capacity;

        LinkedList<hashNode> head = arrayList.get(arrayIndex);

        hashNode h = new hashNode(key, -2);
            if (head.contains(h)) {
               int index = head.indexOf(h);
                hashNode val =  head.get(index);
                head.remove(index);
                size--;
                return val.value;
            }

        // If key was not there
            return 0;

    }

    // Returns value for a key
    public int search(String key)
    {
        // Find head of chain for given key
        int bucketIndex = getArrayIndex(key);

        int hashCode;
        if(hashSerial==1) hashCode =Hashing.Hash1(key)%capacity;
        else hashCode =Hashing.Hash2(key)%capacity;
        LinkedList<hashNode> head = arrayList.get(bucketIndex);
        hashNode h = new hashNode(key, -2);

        // Search key in chain
            if (head.contains(h)){
                int index = head.indexOf(h);
                probes+=index;
                hashNode val =  head.get(index);
                return val.value;
            }

        // If key not found 0 returning
        return 0;
    }

    public void insert(String key){

        int arrayIndex = getArrayIndex(key);

        int hashCode;
        if(hashSerial==1) hashCode =Hashing.Hash1(key)%capacity;
        else hashCode =Hashing.Hash2(key)%capacity;

        LinkedList<hashNode> list = arrayList.get(arrayIndex);
        hashNode h = new hashNode(key, -2);
        // Checking if key is already present
            if (!list.isEmpty() && list.contains(h))
                return;
            if(!list.isEmpty()) collision++;

        tracker++;
        h= new hashNode(key, tracker);
        list.add(h);
        size++;

    }

}


