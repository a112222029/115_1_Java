// Week 08 - D5: 兩組邊界案例與一組自訂反例
// 每組案例使用新的 Rover 和 Mission，不修改既有主程式。
public class D5BoundaryTry {
    public static void main(String[] args) {
        // 案例 1：能源剛好可探索一次，下一個充電指令應被略過。
        check("案例1－一般車", new Rover(6, 2), new int[]{1, 3},
                "0/4/1/能源耗盡/1/0");
        check("案例1－重型車", new HeavyRover(6, 2), new int[]{1, 3},
                "0/5/1/任務成功/1/0");

        // 案例 2：能源不足 6，兩種車都不能探索。
        check("案例2－一般車", new Rover(5, 0), new int[]{1},
                "5/0/0/任務未完成/1/1");
        check("案例2－重型車", new HeavyRover(5, 0), new int[]{1},
                "5/0/0/任務未完成/1/1");

        // 自訂反例：重型車第一次探索即達 5 份，不能再讀第二次探索和充電。
        // 若忘記覆寫探索產量，重型車會錯誤地讀取第二筆指令。
        check("反例－一般車", new Rover(12, 2), new int[]{1, 1, 3},
                "0/6/2/任務成功/2/0");
        check("反例－重型車", new HeavyRover(12, 2), new int[]{1, 1, 3},
                "6/5/1/任務成功/1/0");
    }

    private static void check(String label, Rover rover, int[] script, String expected) {
        Mission mission = new Mission(rover);
        mission.run(script);
        String actual = rover.getEnergy() + "/" + rover.getSamples() + "/"
                + rover.getActionCount() + "/" + mission.getOutcome() + "/"
                + mission.getStepCount() + "/" + mission.countRejected();
        System.out.println(label + "：" + actual + " （預期 " + expected + "） "
                + (actual.equals(expected) ? "相符" : "不符"));
        for (int i = 0; i < mission.getStepCount(); i++) {
            MissionStep step = mission.getStep(i);
            System.out.println("  步驟" + (i + 1) + "：指令=" + step.getCommand()
                    + "，結果=" + step.getResult());
        }
    }
}
