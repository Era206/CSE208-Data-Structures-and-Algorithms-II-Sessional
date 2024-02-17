import static java.lang.Math.abs;

 class Hashing {
    static int n=10000;

    static int auxHash(String k){
        int hash=0;
        for (int i = 0; i < k.length(); i++) {
            hash+=k.charAt(i);
        }
        return hash ;
    }


    static int Hash1(String k){
        int hash=5381;
        for (int i = 0; i < k.length(); i++) {
            hash= abs(((hash<<5)+hash))+k.charAt(i);
        }

        return hash ;
    }


    static int Hash2(String k){
        int hash=0;
        for (int i = 0; i < k.length(); i++) {
            hash= k.charAt(i)+abs((hash<<6)+(hash<<16)-hash);
        }
        return hash ;
    }

}
