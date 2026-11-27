// 類別：CompareMissions（第 8 週主入口）
// 意義：比較一般探測車與重型探測車執行同一份任務的程式入口。
// 作用：兩台車都用 Rover 型態的變數操作、各自配一個新任務，執行後用 show 印出報表；
//       同樣的 Mission 程式會因實際車種得到不同產量。
// 執行：在本人的 java-08 編譯 src/*.java，再執行 CompareMissions。
// 固定腳本，不讀命令列參數。每個新任務配新車，只run一次。
public class CompareMissions {
    // main：程式進入點。準備固定腳本 {1, 1, 2}，讓兩台新車各執行一次任務並印出報表。
    public static void main(String[] args) {
        int[] script = {1, 1, 2};
        // TODO 3：兩次分別建立Rover型態參考、新車與新Mission。
        // 一般車與重型車各run同一份script一次，再呼叫已提供的show，
        // 標籤分別用"一般探測車"與"重型探測車"。
        Rover normal = new Rover(20, 0);
        Mission normalMission = new Mission(normal);
        normalMission.run(script);
        show("一般探測車", normal, normalMission, script.length);

        Rover heavy = new HeavyRover(20, 0);
        Mission heavyMission = new Mission(heavy);
        heavyMission.run(script);
        show("重型探測車", heavy, heavyMission, script.length);
    }

    // show：印出一台車的完整報表：標題、任務回放、指令次數、摘要、最終狀態與電量百分比。
    // 已提供；這裡不判斷車種，也不執行行動，只讀取資料。
    static void show(String label, Rover rover, Mission mission, int scriptLength) {
        System.out.println("=== " + label + " ===");
        MissionLog.printReplay(mission);
        System.out.println("指令次數[0..3]="
                + java.util.Arrays.toString(mission.countCommands()));
        MissionLog.printSummary(scriptLength, mission);
        System.out.println("最終能源=" + rover.getEnergy()
                + ", 樣本數=" + rover.getSamples()
                + ", 行動次數=" + rover.getActionCount()
                + " -> " + mission.getOutcome());
        System.out.println("電量百分比=" + rover.getEnergy() / 20.0 * 100);
    }
}
