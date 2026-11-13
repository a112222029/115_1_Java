# 火星探測車任務控制員

## 作業資訊

- 課程：物件導向程式設計
- 實作日期：2026/10/16
- 使用語言：Java
- 編譯目標版本：Java 21

## 程式說明

本次作業練習 Java 的條件判斷、switch、for 迴圈、while 迴圈及 Scanner 輸入。

主要程式為 `RoverMission.java`，使用者可以輸入指令控制探測車：

- `1`：探索，消耗 6 格能源，取得 2 份樣本。
- `2`：掃描，消耗 2 格能源，取得 1 份樣本。
- `3`：充電，增加最多 5 格能源，上限為 20。
- `0`：返回，結束任務。

取得至少 5 份樣本時顯示 `SUCCESS`，能源耗盡時顯示 `EXHAUSTED`。

## 執行方式

在 `java-03` 資料夾開啟終端機，輸入：

```cmd
javac --release 21 -encoding UTF-8 -d out src\*.java
java -cp out RoverMission
```

## 測試與紀錄

- `LAB_REPORT.md`：實作過程、測試結果及課堂問題。
- `AI_USAGE.md`：AI 使用與除錯驗證紀錄。
- `src/`：Java 程式原始碼。

編譯產生的 `.class` 檔案與 `out` 資料夾不列入提交。