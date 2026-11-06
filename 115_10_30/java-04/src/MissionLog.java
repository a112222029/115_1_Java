import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

// 同一任務規則，只新增逐筆保存；目前狀態維持具名變數，方便銜接第三週。
public class MissionLog {
    public static void main(String[] args) {
        int[] script = {3, 9, 0};// 探索 → 探索 → 掃描。
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
            commandLog.add(command);
            energyLog.add(energy);
            resultLog.add(result);
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
        for (int command : commandLog) {
        counts[command]++;
        }

        return counts;
    }

    static void printReplay(ArrayList<Integer> commandLog, ArrayList<Integer> energyLog,
            ArrayList<String> resultLog) {
        System.out.println("=== replay ===");
        // TODO 3：用相同索引讀三條清單，格式見講義 E。
        for (int i = 0; i < commandLog.size(); i++) {
        System.out.println("step " + (i + 1)
            + ": command=" + commandLog.get(i)
            + ", result=" + resultLog.get(i)
            + ", energy=" + energyLog.get(i));
}
    }

    static void printSummary(int scriptLength, ArrayList<Integer> energyLog,
            ArrayList<String> resultLog) {
        int steps = resultLog.size();
        int ok = Collections.frequency(resultLog, "OK");
        int returned = Collections.frequency(resultLog, "RETURN");
        int rejected = steps - ok - returned;
        int ignored = scriptLength - steps;

System.out.println("steps=" + steps + ", ok=" + ok
        + ", rejected=" + rejected + ", ignored=" + ignored);

        if (energyLog.isEmpty()) {
    System.out.println("lowest energy=N/A");
    } else {
    System.out.println("lowest energy=" + Collections.min(energyLog));
    }
    }

    static double calculateBatteryPercent(int energy) {
        return energy / 20.0 * 100;
    }
}
