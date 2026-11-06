
# 第四週 Java 實作：火星探測車任務日誌

## 作業內容
本週延續第三週的火星探測車任務，練習 Java 陣列、ArrayList、Collections，以及任務日誌的記錄與統計。

## 程式功能
- 記錄每一步的指令、執行後能源與結果
- 依照執行順序回放任務日誌
- 統計指令 0～3 的出現次數
- 計算成功、拒絕及未執行的指令數
- 顯示最低能源與最終任務狀態

## 編譯與執行
在 java-04 資料夾開啟終端機：

```cmd
javac -encoding UTF-8 -d out src\*.java
java -cp out MissionLog
```

## 預設測試
腳本：{1,1,2}

預期結果：
- final energy=6
- samples=5
- actionCount=3
- SUCCESS
- battery=30.0

## 測試紀錄
已完成六組指定腳本的測試，詳細過程與結果記錄於上一層的 LAB_REPORT.md。

AI 使用方式記錄於上一層的 AI_USAGE.md。
