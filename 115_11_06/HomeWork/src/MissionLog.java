import java.util.Arrays;

// 類別：MissionLog（任務入口與報表）
// 意義：探測車任務的程式入口，負責把任務結果印成報表。
// 作用：建立 Rover 與 Mission、執行固定腳本，再向它們查詢資料，印出與第 5 週相同格式的報表。
//       本檔已完成，學生不用修改。
public class MissionLog {
    // main：程式進入點。準備固定腳本 {1, 1, 2}（探索、探索、掃描），以能源 20、樣本 0 執行一次任務。
    public static void main(String[] args) {
        int[] script = {1, 1, 2};
        runMission(script, 20, 0);
    }

    // runMission：建立一台探測車與一個任務，執行腳本後印出全部報表。
    // 方法名稱與參數沿用第 5 週，所以教師工具 FixedScriptMission、MissionLogCheck 不必修改。
    static void runMission(int[] script, int energy, int samples) {
        Rover rover = new Rover(energy, samples);
        Mission mission = new Mission(rover);
        mission.run(script);
        printReplay(mission);
        System.out.println("指令次數[0..3]=" + Arrays.toString(mission.countCommands()));
        printSummary(script.length, mission);
        System.out.println("最終能源=" + rover.getEnergy() + ", 樣本數=" + rover.getSamples()
                + ", 行動次數=" + rover.getActionCount() + " -> " + mission.getOutcome());
        System.out.println("電量百分比=" + rover.getEnergy() / 20.0 * 100);
    }

    // printReplay：依序印出任務日誌的每一步（指令、結果、執行後能源）。
    static void printReplay(Mission mission) {
        System.out.println("=== 任務回放 ===");
        for (int i = 0; i < mission.getStepCount(); i++) {
            MissionStep step = mission.getStep(i);
            System.out.println("步驟 " + (i + 1) + ": 指令=" + step.getCommand()
                    + ", 結果=" + step.getResult() + ", 能源=" + step.getEnergy());
        }
    }

    // printSummary：印出步數、成功、拒絕、略過與最低能源。
    // scriptLength 是腳本長度；略過筆數 = 腳本長度 − 日誌筆數。
    static void printSummary(int scriptLength, Mission mission) {
        System.out.println("步數=" + mission.getStepCount() + ", 成功=" + mission.countOk()
                + ", 拒絕=" + mission.countRejected()
                + ", 略過=" + (scriptLength - mission.getStepCount()));
        if (mission.getStepCount() == 0) {
            System.out.println("最低能源=無紀錄");
        } else {
            System.out.println("最低能源=" + mission.lowestEnergy());
        }
    }
}
