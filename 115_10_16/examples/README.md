# 示範順序

1. `EnergyStatus.java`：固定六個邊界，先排除無效範圍，再分類。
2. `SingleAction.java`：固定 command=1、energy=6；輸出 energy=0、samples=2。改 energy=5 檢查拒絕；改 command=2 與 9 比較分派。
3. `ChargeForecast.java`：4 起點為 9、14、19；18 起點為 20、20、20。本例是固定三輪預測，不計任務 actionCount。
4. `LoopComparison.java`：while=0、do-while=1。只比較先判斷或後判斷，不增加大型任務。

複製到學生專案 src 後，用 README 中的 javac 指令編譯。先預測輸出，再執行；修改示範前保留原版。英文訊息對照：INVALID 無效、LOW 低電量、READY 可探索、NOT_ENOUGH_ENERGY 能源不足、UNKNOWN_COMMAND 無效指令。
