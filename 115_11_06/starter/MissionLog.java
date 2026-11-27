import java.util.ArrayList;
import java.util.Arrays;

// 類別：MissionLog（第 5 週探測車任務日誌）
// 意義：一次火星探測車任務的完整程式；行動規則與第 4 週相同，只改變「日誌的存法」。
// 作用：把第 4 週的三條平行清單，換成一條 ArrayList<MissionStep>：每一步只建立一個 MissionStep 物件，
//       同一步的指令、能源、結果一起保存；最後印出與第 4 週數值相同的報表（文字改成中文）。
public class MissionLog {
    // main：程式進入點。準備固定腳本 {1, 1, 2}（探索、探索、掃描），
    // 以能源 20、樣本 0 開始執行一次任務。
    public static void main(String[] args) {
        int[] script = {1, 1, 2};
        runMission(script, 20, 0);
    }

    // runMission：執行一次完整任務並印出全部報表。
    // 參數 script 是指令腳本（不可為 null），energy 是初始能源（0～20），samples 是初始樣本數（不可為負）。
    // 教師工具 FixedScriptMission 與 MissionLogCheck 也從這個方法進入，所以方法名稱和參數要保持不變。
    static void runMission(int[] script, int energy, int samples) {
        // actionCount：成功行動的次數；running：還沒收到返回指令時為 true。
        // history：任務日誌，每一筆 MissionStep 代表已讀取的一步。
        int actionCount = 0;
        boolean running = true;
        ArrayList<MissionStep> history = new ArrayList<>();

        // 先判斷狀態，能源已耗盡或樣本已達標時，不再執行第一筆指令。
        for (int i = 0; i < script.length && running && energy > 0 && samples < 5; i++) {
            int command = script[i];
            String result;
            switch (command) {
                case 1:
                    if (energy >= 6) {
                        energy -= 6;
                        samples += 2;
                        actionCount++;
                        result = "成功";
                    } else {
                        result = "能源不足";
                    }
                    break;
                case 2:
                    if (energy >= 2) {
                        energy -= 2;
                        samples++;
                        actionCount++;
                        result = "成功";
                    } else {
                        result = "能源不足";
                    }
                    break;
                case 3:
                    if (energy < 20) {
                        energy += 5;
                        if (energy > 20) {
                            energy = 20;
                        }
                        actionCount++;
                        result = "成功";
                    } else {
                        result = "能源已滿";
                    }
                    break;
                case 0:
                    running = false;
                    result = "返回";
                    break;
                default:
                    result = "無效指令";
                    break;
            }
            // 每讀一筆就建立一個新物件，記錄行動執行後的快照。
            // TODO 4：在這裡一次新增本步的 MissionStep，包含拒絕、無效與返回。
        }

        String outcome;
        if (samples >= 5) {
            outcome = "任務成功";
        } else if (energy == 0) {
            outcome = "能源耗盡";
        } else if (!running) {
            outcome = "已返回";
        } else {
            // 固定腳本用完不等於返回；保留尚未完成這個新結果。
            outcome = "任務未完成";
        }
        printReplay(history);
        System.out.println("指令次數[0..3]=" + Arrays.toString(countCommands(history)));
        printSummary(script.length, history);
        System.out.println("最終能源=" + energy + ", 樣本數=" + samples
                + ", 行動次數=" + actionCount + " -> " + outcome);
        System.out.println("電量百分比=" + calculateBatteryPercent(energy));
    }

    // countCommands：統計日誌中指令 0～3 各出現幾次，回傳長度 4 的陣列。
    // counts[0] 是返回次數、counts[1] 是探索次數，依此類推；無效指令不計入。
    static int[] countCommands(ArrayList<MissionStep> history) {
        int[] counts = new int[4];
        for (MissionStep step : history) {
            int command = step.getCommand();
            if (command >= 0 && command < counts.length) {
                counts[command]++;
            }
        }
        return counts;
    }

    // printReplay：依序印出每一步的指令、結果與執行後能源（任務回放）。
    // 每一步的三個值都從同一個 MissionStep 物件用 getter 取出。
    static void printReplay(ArrayList<MissionStep> history) {
        System.out.println("=== 任務回放 ===");
        // TODO 5：依索引取出 MissionStep，以 getter 印出與第四週相同的逐步回放。
    }

    // printSummary：印出摘要（步數、成功、拒絕、略過）與最低能源。
    // scriptLength 是腳本長度，用來算出「沒讀到」而略過的指令數。
    static void printSummary(int scriptLength, ArrayList<MissionStep> history) {
        int ok = 0;
        int returned = 0;
        // TODO 6：走訪 history，計算 成功 與 返回 筆數；後面的摘要與最低值已提供。
        int steps = history.size();
        int rejected = steps - ok - returned;
        int ignored = scriptLength - steps;
        System.out.println("步數=" + steps + ", 成功=" + ok
                + ", 拒絕=" + rejected + ", 略過=" + ignored);
        if (history.isEmpty()) {
            System.out.println("最低能源=無紀錄");
        } else {
            // MissionStep 不是 Integer；讀能源欄位，以已教迴圈求最小值。
            int lowest = history.get(0).getEnergy();
            for (MissionStep step : history) {
                if (step.getEnergy() < lowest) {
                    lowest = step.getEnergy();
                }
            }
            System.out.println("最低能源=" + lowest);
        }
    }

    // calculateBatteryPercent：把能源格數換算成百分比（20 格 = 100%）。
    // 除以 20.0 而不是 20，才不會變成整數除法。
    static double calculateBatteryPercent(int energy) {
        return energy / 20.0 * 100;
    }
}
