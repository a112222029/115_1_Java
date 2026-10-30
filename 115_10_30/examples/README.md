# 第四週示範程式

把五個核心檔複製到 java-04/src，與 starter/MissionLog.java 一起編譯。每次從 java-04 執行 `java -cp out 類別名稱`。

| 檔案 | 核心用途 | 講義 |
|---|---|---|
| [ArrayBasics.java](ArrayBasics.java) | 長度、預設值、索引式/加強版 for | B |
| [CommandCounter.java](CommandCounter.java) | 0..3 計數與無效指令 | B |
| [ArrayReference.java](ArrayReference.java) | 共用參考、改元素、重新指定 | C |
| [ListBasics.java](ListBasics.java) | add/get/size/set/remove/isEmpty、Integer | D |
| [CollectionsDemo.java](CollectionsDemo.java) | min/max/frequency、排序另一條清單 | F |
| [ScriptArgs.java](ScriptArgs.java) | 選讀：String[] args → int[] | 課後延伸 |

ScriptArgs 可執行 `java -cp out ScriptArgs 3 9 0`，預期 `script=[3, 9, 0]`；無參數為 `script=[]`。它只示範解析，不會替 MissionLog 傳入路線。主作業請修改 main 的固定 script。

越界與 ArrayList<int> 練習只在副本短暫重現，隨即恢復；不要提交不能編譯的示範副本。Integer 快取、走訪中刪除、Iterator 與 List.of 不納入核心程式。
