// 類別：HeavyRoverTry（學生用檢查程式，已提供）
// 意義：檢查 HeavyRover 是否符合規格的小程式。
// 作用：對幾台重型車執行鑽探與既有行動，把實際狀態和預期狀態並排印出；不同時會印「不符」。
// 先看講義規格自行推算，再執行。本檔不能代替你自己的反例。
public class HeavyRoverTry {
    // main：程式進入點。依序執行正常、能源不足、能源剛好、已耗盡、已達標等案例。
    public static void main(String[] args) {
        HeavyRover h = new HeavyRover(20, 0);
        check("鑽探", h.drill(), "成功");
        show("鑽探後", h, "12/3/1/1");
        check("探索", h.execute(1), "成功");
        show("探索後", h, "6/5/2/1");
        check("任務完成後鑽探", h.drill(), "任務已停止");
        show("完成後狀態維持", h, "6/5/2/1");

        HeavyRover low = new HeavyRover(7, 0);
        check("能源不足", low.drill(), "能源不足");
        show("能源不足時狀態維持", low, "7/0/0/0");
        HeavyRover exact = new HeavyRover(8, 0);
        check("能源剛好足夠", exact.drill(), "成功");
        show("能源剛好足夠時的狀態", exact, "0/3/1/1");
        check("能源耗盡後無法充電", exact.execute(3), "任務已停止");
        HeavyRover zero = new HeavyRover(0, 0);
        check("初始能源為零", zero.drill(), "任務已停止");
        show("耗盡後狀態維持", zero, "0/0/0/0");
        HeavyRover done = new HeavyRover(20, 5);
        check("初始已達標", done.drill(), "任務已停止");
        show("達標後狀態維持", done, "20/5/0/0");
    }

    // show：把車的狀態組成「能源/樣本/行動次數/鑽探次數」字串，再交給 check 比對。
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
