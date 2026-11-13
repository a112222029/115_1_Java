import java.util.ArrayList;
import java.util.Collections;

public class CollectionsDemo {
    public static void main(String[] args) {
        ArrayList<Integer> energyLog = new ArrayList<>();
        energyLog.add(14);
        energyLog.add(8);
        energyLog.add(20);
        energyLog.add(6);
        System.out.println("min=" + Collections.min(energyLog));
        System.out.println("max=" + Collections.max(energyLog));
        System.out.println("frequency(8)=" + Collections.frequency(energyLog, 8));
        // 把值加到另一條清單，避免排序破壞原始任務的時間順序。
        ArrayList<Integer> sorted = new ArrayList<>();
        for (int energy : energyLog) {
            sorted.add(energy);
        }
        Collections.sort(sorted);
        System.out.println("original=" + energyLog);
        System.out.println("sorted=" + sorted);
        ArrayList<String> results = new ArrayList<>();
        results.add("OK");
        results.add("ALREADY_FULL");
        results.add("OK");
        System.out.println("ok=" + Collections.frequency(results, "OK"));
    }
}
