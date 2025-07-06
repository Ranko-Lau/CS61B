import edu.princeton.cs.algs4.WeightedQuickUnionUF;


public class Percolation {
    // TODO: Add any necessary instance variables.
    private boolean grid[][];
    private WeightedQuickUnionUF uni;
    private WeightedQuickUnionUF uniback;
    private int H;
    private int opennum;

    public Percolation(int N) {
        // TODO: Fill in this constructor.
        H = N;
        if (N <= 0){
            throw new java.lang.IllegalArgumentException();
        }
        grid = new boolean[N][N];
        for (int i = 0; i < N; i++){
            for (int j = 0; j < N; j++){
                grid[i][j] = false;
            }
        }
        uni = new WeightedQuickUnionUF(N * N);
        uniback = new WeightedQuickUnionUF(N * N + 2);
        for (int i = 0; i < H; i++){
            uniback.union(i, N * N);
            uniback.union(H * (H - 1) + i, N * N + 1);
        }
        opennum = 0;
    }

    public void open(int row, int col) {
        // TODO: Fill in this method.
        if (row < 0 || row > H || col < 0 || col > H){
            throw new java.lang.IndexOutOfBoundsException();
        }
        if (isOpen(row, col)){
            return;
        }
        grid[row][col] = true;
        if (H == 1){
            return;
        }
        if (row == 0) {
            if (isOpen(row + 1, col)){
                uni.union(col, col + H);
                uniback.union(col, col + H);
            }
        }
        if (row == H - 1) {
            if (isOpen(row - 1, col)){
                uni.union(col + H * (H - 2), col + H * (H - 1));
                uniback.union(col + H * (H - 2), col + H * (H - 1));
            }
        }
        if (col == 0) {
            if (isOpen(row, col + 1)){
                uni.union(row * H, 1 + row * H);
                uniback.union(row * H, 1 + row * H);
            }
        }
        if (col == H - 1) {
            if (isOpen(row, col - 1)){
                uni.union(row * H + col - 1, row * H + col);
                uniback.union(row * H + col - 1, row * H + col);
            }
        }
        if (row > 0 && row < H - 1){
            if (isOpen(row + 1, col)){
                uni.union(row * H + col, col + (row + 1) * H);
                uniback.union(row * H + col, col + (row + 1) * H);
            }
            if (isOpen(row - 1, col)){
                uni.union(col + H * row, col + H * (row - 1));
                uniback.union(col + H * row, col + H * (row - 1));
            }
        }
        if (col > 0 && col < H - 1){
            if (isOpen(row, col + 1)){
                uni.union(col + 1 + row * H, col + row * H);
                uniback.union(col + 1 + row * H, col + row * H);
            }
            if (isOpen(row, col - 1)){
                uni.union(row * H + col - 1, row * H + col);
                uniback.union(row * H + col - 1, row * H + col);
            }
        }
        opennum += 1;
    }

    public boolean isOpen(int row, int col) {
        // TODO: Fill in this method.
        if (row < 0 || row > H || col < 0 || col > H){
            throw new java.lang.IndexOutOfBoundsException();
        }
        return grid[row][col];
    }

    public boolean isFull(int row, int col) {
        // TODO: Fill in this method.
        if (row < 0 || row > H || col < 0 || col > H){
            throw new java.lang.IndexOutOfBoundsException();
        }
        return (isOpen(row, col) && uniback.connected(row * H + col, H * H));
    }

    public int numberOfOpenSites() {
        // TODO: Fill in this method.
        return opennum;
    }

    public boolean percolates() {
        // TODO: Fill in this method.
//        for (int i = 0; i < H; i++){
//            for (int j = 0; j < H; j++){
//                if (uni.connected(i, H * (H - 1) + j)){
//                    return true;
//                }
//            }
//        }
//        for (int i = 0; i < H; i++){
//            if (uni.find(H * (H - 1) + i) < H){
//                return true;
//            }
//        }
        return uniback.connected(H * H + 1, H * H);
    }

    // TODO: Add any useful helper methods (we highly recommend this!).
    // TODO: Remove all TODO comments before submitting.

}
