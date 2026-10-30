import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

// 同一任務規則，只新增逐筆保存；目前狀態維持具名變數，方便銜接第三週。
public class MissionLog {
    public static void main(String[] args) {
        int[] script = {1, 1, 2}; // 探索 → 探索 → 掃描。
        runMission(script, 20, 0);
    }

    // 教師測試入口；script 非 null，初始 energy 為 0..20、samples 非負。
    static void runMission(int[] script, int energy, int samples) {
        int actionCount = 0;
        boolean running = true;
        ArrayList<Integer> commandLog = new ArrayList<>();
        ArrayList<Integer> energyLog = new ArrayList<>();
        ArrayList<String> resultLog = new ArrayList<>();

        // 先判斷狀態，能源已耗盡或樣本已達標時，不再執行第一筆指令。
        for (int i = 0; i < script.length && running && energy > 0 && samples < 5; i++) {
            int command = script[i];
            String result;
            switch (command) {
                case 1: // 指令 1：探索，消耗 6 格能源，取得 2 份樣本。
                    if (energy >= 6) {
                        energy -= 6;
                        samples += 2;
                        actionCount++;
                        result = "OK";
                    } else {
                        result = "NOT_ENOUGH_ENERGY";
                    }
                    break;
                case 2: // 指令 2：掃描，消耗 2 格能源，取得 1 份樣本。
                    if (energy >= 2) {
                        energy -= 2;
                        samples++;
                        actionCount++;
                        result = "OK";
                    } else {
                        result = "NOT_ENOUGH_ENERGY";
                    }
                    break;
                case 3: // 指令 3：充電，增加 5 格能源，最多 20 格；已滿時拒絕充電。
                    if (energy < 20) {
                        energy += 5;
                        if (energy > 20) {
                            energy = 20;
                        }
                        actionCount++;
                        result = "OK";
                    } else {
                        result = "ALREADY_FULL";
                    }
                    break;
                case 0: // 指令 0：返回，停止讀取後續指令，不增加成功行動次數。
                    running = false;
                    result = "RETURN";
                    break;
                default: // 其他整數：無效指令，不改變能源、樣本或成功行動次數。
                    result = "UNKNOWN_COMMAND";
                    break;
            }
            // TODO 2：把 command、energy、result 加到對應清單，拒絕與返回也要記錄。
        }

        String outcome;
        if (samples >= 5) {
            outcome = "SUCCESS";
        } else if (energy == 0) {
            outcome = "EXHAUSTED";
        } else if (!running) {
            outcome = "RETURNED";
        } else {
            // 固定腳本用完不等於返回；保留尚未完成這個新結果。
            outcome = "UNFINISHED";
        }
        printReplay(commandLog, energyLog, resultLog);
        System.out.println("counts[0..3]=" + Arrays.toString(countCommands(commandLog)));
        printSummary(script.length, energyLog, resultLog);
        System.out.println("final energy=" + energy + ", samples=" + samples
                + ", actionCount=" + actionCount + " -> " + outcome);
        System.out.println("battery=" + calculateBatteryPercent(energy));
    }

    static int[] countCommands(ArrayList<Integer> commandLog) {
        int[] counts = new int[4];
        // TODO 1：走訪 commandLog；指令在 0..3 才增加 counts[command]。
        return counts;
    }

    static void printReplay(ArrayList<Integer> commandLog, ArrayList<Integer> energyLog,
            ArrayList<String> resultLog) {
        System.out.println("=== replay ===");
        // TODO 3：用相同索引讀三條清單，格式見講義 E。
    }

    static void printSummary(int scriptLength, ArrayList<Integer> energyLog,
            ArrayList<String> resultLog) {
        // TODO 4：用 frequency 計算 OK、RETURN，印出 steps、ok、rejected、ignored。
        System.out.println("TODO 4: summary");
        // TODO 5：先用 isEmpty 判斷；空清單印 N/A，否則用 min 印最小能源。
        System.out.println("TODO 5: lowest energy");
    }

    static double calculateBatteryPercent(int energy) {
        return energy / 20.0 * 100;
    }
}
