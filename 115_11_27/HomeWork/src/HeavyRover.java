// 類別：HeavyRover（重型探測車，第 8 週版本）
// 意義：一種探測車（extends Rover），探索一次得 3 份樣本（一般車得 2 份），另外能鑽探。
// 作用：示範兩種覆寫：覆寫 exploreYield()，沿用 Rover 的探索流程卻得到不同產量；
//       覆寫 execute()，讓任務腳本可以下指令 4（鑽探），其他指令交回父類別處理。鑽探規則沿用前週。
public class HeavyRover extends Rover {
    // 已提供的具名常數，避免把成本和產量兩個數字搞混；不是本週新增語法驗收。
    // DRILL_COST：鑽探一次消耗的能源格數。
    public static final int DRILL_COST = 8;
    // DRILL_YIELD：鑽探一次取得的樣本份數。
    public static final int DRILL_YIELD = 3;

    // drillCount：這台重型車成功鑽探的次數；是子類別自己才有的欄位。
    // 能源、樣本、行動次數由父類別 Rover 保存，這裡不再重複宣告。
    private int drillCount;

    // 建構子：能源與樣本交給父類別 Rover 保存，鑽探次數從 0 開始。
    public HeavyRover(int energy, int samples) {
        super(energy, samples);
        drillCount = 0;
    }

    // TODO 2：自行寫出覆寫方法，使用 @Override，探索產量固定回傳3。
    // 只回傳產量，不額外扣能源、增加樣本或計次。
    @Override
    protected int exploreYield() {
        return 3;
    }

    // TODO 4：自行寫出覆寫 execute(int command) 的方法，讓任務腳本可以下指令 4（鑽探）。
    // 指令 4 交給下方已提供的 drill()；其他指令交回父類別原本的 execute 處理，
    // 不重抄父類別的 switch，也不修改 Rover 或 Mission。
    // 完成後執行 DrillMissionTry，應全部顯示「相符」。
    @Override
    public String execute(int command) {
        if (command == 4) {
            return drill();
        }
        return super.execute(command);
    }

    // getDrillCount：回傳成功鑽探的次數。
    public int getDrillCount() { return drillCount; }

    // drill：鑽探一次，回傳結果字串（「成功」「能源不足」或「任務已停止」）。
    // 可以直接呼叫；完成講義 D6 後也會由 execute(4) 呼叫。這條路不經過父類別 execute 的停止檢查，
    // 所以要自行檢查停止；只有成功才更新狀態。
    public String drill() {
        if (isStopped()) { return "任務已停止"; }
        if (!spendEnergy(DRILL_COST)) { return "能源不足"; }
        addSamples(DRILL_YIELD);
        countAction();
        drillCount++;
        return "成功";
    }
}
