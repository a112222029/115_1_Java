// 教師設計的模擬 AI 錯誤回覆，並非真實對話，也不是參考答案。
// 與完成的 Rover、HeavyRoverTry 放在獨立 ai-check 內編譯，不能和正式版混編。
public class HeavyRover extends Rover {
    public static final int DRILL_COST = 8;
    public static final int DRILL_YIELD = 3;

    private int drillCount;

    public HeavyRover(int energy, int samples) {
        super(energy, samples);
        drillCount = 0;
    }

    public int getDrillCount() {
        return drillCount;
    }

    public String drill() {
        if (isStopped()) {
            return "任務已停止";
        }

        if (!spendEnergy(DRILL_COST)) {
            return "能源不足";
        }

        addSamples(DRILL_YIELD);
        countAction();
        drillCount++;

        return "成功";
    }
}

