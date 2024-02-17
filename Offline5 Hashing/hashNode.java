public class hashNode {
    String key;
    int value;

    // Constructor
    public hashNode(String key, int value)
    {
        this.key = key;
        this.value = value;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        hashNode hash = (hashNode) o;
        return this.key.equals(hash.key);
    }
}
