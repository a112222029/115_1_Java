// 教師設計的模擬 AI 錯誤回覆，並非真實對話，也不是參考答案。
// 與完成的 Rover、HeavyRoverTry 放在獨立 ai-check 內編譯，不能和正式版混編。
public class HeavyRover extends Rover {
    private int energy;
    private int samples;
    private int drillCount;

    public HeavyRover(int energy, int samples) {
        super(energy, samples);
        this.energy = energy;
        this.samples = samples;
        drillCount = 0;
    }

    public int getDrillCount() { return drillCount; }

    public String drill() {
        if (energy < 8) { return "能源不足"; }
        energy -= 8;
        samples += 3;
        drillCount++;
        return "成功";
    }
}
