

class hashCustomTable {


    private int HASH_TABLE_SIZE;
    private int size;
    private hashNode[] table;
    int hashSerial, tracker=0, collision, probes;


    public hashCustomTable(int flag)
    {

        size = 0;
        HASH_TABLE_SIZE = 10007;
        hashSerial = flag;
        table = new hashNode[HASH_TABLE_SIZE];
        collision =0;
        probes = 0;

        for (int i = 0; i < HASH_TABLE_SIZE; i++)
            table[i] = null;

    }


    // To get number of key-value pairs
    public int size() { return size; }
    public boolean isEmpty() { return size == 0; }
    public int getCollisions() { return collision; }
    public double getProbes() { return probes; }


    /* Function to clear hash table */
    public void makeEmpty()
    {
        size = 0;
        for (int i = 0; i < HASH_TABLE_SIZE; i++)
            table[i] = null;
    }


    // To insert a key value pair
    public int insert(String key)
    {

        if (size == HASH_TABLE_SIZE) {
            System.out.println("Table is full");
            return -1;
        }
        int i=0, doublehash;
        int hash1, hash2;
        int C1 =9,  C2=11;
        if(hashSerial==1){
            hash1 = Hashing.Hash1(key);
            hash2 = Hashing.auxHash(key);
        }
        else{
            hash1 = Hashing.Hash2(key);
            hash2 = Hashing.auxHash(key);
        }

        while(i<HASH_TABLE_SIZE){
            doublehash = (int)(((long)hash1 +C1 * i * (long) hash2 + C2 *i*i)%HASH_TABLE_SIZE);
            if(table[doublehash]==null){
                tracker++;
                table[doublehash]=new hashNode(key, tracker);
                size++;
                return table[doublehash].value;
            }
            else{
                i++;
                collision++;
            }

        }
        return 0;
    }

    public int search(String key)
    {
        int i=0, doublehash;
        int hash1, hash2;
        int C1 =9,  C2=11;
        hashNode h = new hashNode(key, -1);
        if(hashSerial==1){
            hash1 = Hashing.Hash1(key);
            hash2 = Hashing.auxHash(key);
        }
        else{
            hash1 = Hashing.Hash2(key);
            hash2 = Hashing.auxHash(key);
        }

        while(i<HASH_TABLE_SIZE){
            doublehash = (int)(((long)hash1 +C1 * i * (long) hash2 + C2 *i*i)%HASH_TABLE_SIZE);
            if(table[doublehash].equals(h)){
                return doublehash;
            }
            else{
                i++;
                probes++;
            }

        }
        return 0;
    }

    // To remove a key
    void delete(String key){
        int i=0, doublehash;
        int hash1, hash2;
        int C1 =9,  C2=11;
        if(hashSerial==1){
            hash1 = Hashing.Hash1(key);
            hash2 = Hashing.auxHash(key);
        }
        else{
            hash1 = Hashing.Hash2(key);
            hash2 = Hashing.auxHash(key);
        }
        hashNode h=new hashNode(key,-1);
        while(i<HASH_TABLE_SIZE){
            doublehash = (int)(((long)hash1 +C1 * i * (long) hash2 + C2 *i*i)%HASH_TABLE_SIZE);
            if(table[doublehash].equals(h)){
                table[doublehash].key=null;
                return;
            }
            else {
                i++;
            }
        }
    }


    // To print the hash table
    public void printHashTable()
    {
        // Display message
        System.out.println("\nHash Table");

        for (int i = 0; i < HASH_TABLE_SIZE; i++)
            if (table[i] != null)
                System.out.println(table[i].key + " "
                        + table[i].value);
    }
}



