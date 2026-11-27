// 類別：DrillMissionTry（學生用檢查程式，已提供）
// 意義：檢查任務腳本下指令 4 時，重型車會鑽探、一般車會拒絕。
// 作用：用同一份 Mission 程式執行含指令 4 的腳本，再直接呼叫 execute 檢查幾個邊界案例，
//       把實際結果和預期並排印出；兩者不同時印「不符」。
// 先依講義 D6 自行推算，再執行。本檔不能代替你自己的反例。
public class DrillMissionTry {
    // main：程式進入點。先讓兩台車各跑一次腳本 {4, 1}，再檢查停止、能源不足與其他指令。
    public static void main(String[] args) {
        int[] script = {4, 1};

        // heavy 宣告為 HeavyRover，才能讀鑽探次數；Mission 的建構子收 Rover，
        // 所以 Mission 內部仍只把它當 Rover，靠 execute 的覆寫選到鑽探。
        HeavyRover heavy = new HeavyRover(20, 0);
        Mission heavyMission = new Mission(heavy);
        heavyMission.run(script);
        check("重型車：指令4的結果", heavyMission.getStep(0).getResult(), "成功");
        show("重型車：任務後狀態", heavy, "6/6/2/1");
        check("重型車：任務結果與已讀步數",
                heavyMission.getOutcome() + "/" + heavyMission.getStepCount(), "任務成功/2");
        check("重型車：指令次數[0..3]",
                java.util.Arrays.toString(heavyMission.countCommands()), "[0, 1, 0, 0]");

        // 一般車沒有覆寫 execute，指令 4 走父類別的 default 分支。
        Rover normal = new Rover(20, 0);
        Mission normalMission = new Mission(normal);
        normalMission.run(script);
        check("一般車：指令4的結果", normalMission.getStep(0).getResult(), "無效指令");
        check("一般車：任務後狀態",
                normal.getEnergy() + "/" + normal.getSamples() + "/" + normal.getActionCount(), "14/2/1");
        check("一般車：成功／拒絕筆數",
                normalMission.countOk() + "/" + normalMission.countRejected(), "1/1");

        HeavyRover low = new HeavyRover(7, 0);
        check("能源不足時下指令4", low.execute(4), "能源不足");
        show("能源不足時狀態維持", low, "7/0/0/0");
        HeavyRover done = new HeavyRover(20, 5);
        check("已達標後下指令4", done.execute(4), "任務已停止");
        show("已達標後狀態維持", done, "20/5/0/0");
        HeavyRover other = new HeavyRover(20, 0);
        check("重型車掃描仍用原規則", other.execute(2), "成功");
        show("掃描後狀態", other, "18/1/1/0");
        check("重型車的無效指令", other.execute(9), "無效指令");
    }

    // show：把重型車的狀態組成「能源/樣本/行動次數/鑽探次數」字串，再交給 check 比對。
    static void show(String label, HeavyRover h, String expected) {
        String actual = h.getEnergy() + "/" + h.getSamples() + "/"
                + h.getActionCount() + "/" + h.getDrillCount();
        check(label, actual, expected);
    }

    // check：印出實際值與預期值；兩者相同印「相符」，不同印「不符」。
    static void check(String label, String actual, String expected) {
        String mark = actual.equals(expected) ? "相符" : "不符";
        System.out.println(label + ": " + actual + " （預期 " + expected + "） " + mark);
    }
}
