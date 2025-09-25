package labirinto;

public class Inventory {
    private char[] pilha;
    private int topo = -1;
    private int capacity;

    public Inventory(int capacity) {
        this.capacity = capacity;
        pilha = new char[capacity];
    }

    public boolean push(char chave) {
        if (topo + 1 < capacity) {
            topo++;
            pilha[topo] = chave;
            return true;
        }
        return false;
    }

    public Character pop() {
        if (topo >= 0) {
            char c = pilha[topo];
            topo--;
            return c;
        }
        return null;
    }

    public Character peek() {
        if (topo >= 0) return pilha[topo];
        return null;
    }

    public int size() {
        return topo + 1;
    }

    public String toString() {
        if (topo < 0) return "[]";
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i <= topo; i++) {
            sb.append(pilha[i]);
            if (i < topo) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }

    public boolean isEmpty() {
        return topo < 0;
    }

    public boolean contains(char chave) {
        for (int i = 0; i <= topo; i++) {
            if (pilha[i] == chave) return true;
        }
        return false;
    }
}