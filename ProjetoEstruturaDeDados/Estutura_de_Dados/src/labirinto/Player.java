package labirinto;

public class Player {
    int r, c;
    int score = 0;
    Inventory inventario;

    public Player(int startRow, int startCol, int capacity) {
        this.r = startRow;
        this.c = startCol;
        this.inventario = new Inventory(capacity);
    }

    public void moverCima() {
        this.r--;
    }

    public void moverBaixo() {
        this.r++;
    }

    public void moverEsquerda() {
        this.c--;
    }

    public void moverDireita() {
        this.c++;
    }

    // Getters e Setters
    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
    }

    public int getC() {
        return c;
    }

    public void setC(int c) {
        this.c = c;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void addScore(int pontos) {
        this.score += pontos;
    }

    public Inventory getInventario() {
        return inventario;
    }

    public boolean temChaves() {
        return inventario.size() > 0;
    }

    public char getChaveTopo() {
        Character chave = inventario.peek();
        return chave != null ? chave : ' ';
    }

    public boolean usarChave() {
        return inventario.pop() != null;
    }

    public boolean coletarChave(char chave) {
        return inventario.push(chave);
    }

    public void mostrarStatus() {
        System.out.println("Posição: (" + r + ", " + c + ")");
        System.out.println("Pontuação: " + score);
        System.out.println("Chaves no inventário: " + inventario.size());
        System.out.println("Inventário: " + inventario.toString());
    }

    public void resetar(int novaRow, int novaCol) {
        this.r = novaRow;
        this.c = novaCol;
        this.score = 0;
    }
}