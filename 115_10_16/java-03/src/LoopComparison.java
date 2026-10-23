public class LoopComparison {
    public static void main(String[] args) {
        int whileCount = 0;
        while (whileCount < 0) {
            whileCount++;
        }
        int doCount = 0;
        do {
            doCount++;
        } while (doCount < 0);
        System.out.println("while=" + whileCount + ", do-while=" + doCount);
    }
}
