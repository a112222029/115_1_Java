# 第四週學生實作：火星探測車任務日誌

姓名：江芸芸　學號：A1122222029　實際上課日期：2026/10/30　實際 JDK：OpenJDK 27（Temurin-27+35）

另存為當日 LAB_REPORT.md。A–H 必做，最後的延伸選做。先預測再執行；教師示範觀察與本人實測分開。作業完整需求見 [assignment.md](assignment.md)。

## A｜取回與先備診斷（10 分鐘）

1. 無 repo 時 clone，有副本先保存再 Fetch/Pull；在 115_1_Java/YYYY_MM_DD 建 java-04/src。
2. 放五個核心 examples 與 starter/MissionLog.java，勿放 teacher 同名類別；日期下放本報告及 AI_USAGE。
3. 執行 java -version、javac -version，寫下完整版本：java version：openjdk version "27" 2026-09-15
javac version：javac 27
JDK：Eclipse Temurin 27+35。不限定特定 JDK 版本。
4. 在 java-04 編譯 `javac -encoding UTF-8 -d out src/*.java`；成功後跑 MissionLog。此時能跑任務但日誌與摘要未完成，是預期起始狀態。

診斷：第三週 {1,1,2} 的最終能源/樣本/成功行動次數：6／5／3。{2,1,1} 是否相同：相同。兩次探索和一次掃描的總消耗一樣，因此最後也是 6／5／3，結果為 SUCCESS。只有最終三個數字，能否分辨路線：不能，因為不同的執行順序可能得到相同結果，需要記錄每一步的指令及狀態變化，才能知道實際執行過程。

## B｜固定資料：陣列與計數（30 分鐘）

教科書 §7.2–7.4、§7.7。先讀 ArrayBasics，再執行。

| 項目 | 預測 | 本人實際結果 |
|---|---|---|
| script.length | 3 | 3 |
| new int[4] 內容 | [0, 0, 0, 0] | [0, 0, 0, 0] |
| new String[2] 內容 | [null, null] | [null, null] |
| 逐步指令輸出 |step 1: 1、step 2: 1、step 3: 2 | step 1: 1、step 2: 1、step 3: 2 |
| 指令數值加總（不是行動數） |  4 | 4 |

1. length 是欄位，不加括號；陣列合法索引範圍是：0 到 length - 1。這次 script.length = 3，所以合法索引是 0、1、2。
2. 在副本取消越界行註解，編譯並執行，記錯誤種類與索引：實測出現 ArrayIndexOutOfBoundsException，錯誤索引為 3，陣列長度為 3。因為合法索引只有 0～2，存取 script[3] 超出範圍，因此發生例外。隨即恢復。
3. 執行 CommandCounter：腳本 {3,9,0,1,1,-1}，預測 counts：[1, 2, 0, 1]；實際：counts=[1, 2, 0, 1]。9 和 -1 為何不得當索引：因為 counts 的合法索引只有 0～3，使用 9 或 -1 會超出陣列範圍。
4. 把 CommandCounter 的固定資料改為 {0,2,2,3}，預測：[1, 0, 2, 1]；實際：[1, 0, 2, 1]；恢復原示範。
5. 一般 for 適合需要步號/索引；加強版 for 適合只讀每筆值。若在 `for (int command : script)` 內寫 command=0，是否改掉 script 的元素：不會，因為 command 只是本輪取得的數值，重新指定不會修改原本陣列。用副本檢查：使用 JShell 建立 int[] script = {1, 1, 2}，並在加強版 for 迴圈內執行 command = 0。實測後陣列仍為 [1, 1, 2]，確認原本元素沒有被修改，符合預期。

## C｜參考與方法（10 分鐘）

教科書 §7.8–7.9。ArrayReference 的兩個變數先指向同一陣列；畫箭頭或用文字描述。

| 時間點 | 預測 energy 內容 | 本人實際 |
|---|---|---|
| alias[0]=19 後 | [19, 14, 8] | [19, 14, 8]，符合預期 |
| changeFirst 後 |  [18, 14, 8] | [18, 14, 8]，符合預期 |
| replaceLocal 內的新陣列 |[20, 20, 20] | [20, 20, 20]，符合預期 |
| 回到 main 後 | [18, 14, 8] | [18, 14, 8]，符合預期 |
| == 與 Arrays.equals 的兩個結果 | false、true | false、true，符合預期 |

Java 傳值；陣列參數取得的是：陣列參考值的副本。為何修改元素看得見，重新指定參數卻不改呼叫端：因為方法內外原本指向同一個陣列，所以修改元素會影響原陣列；但重新指定參數只會改變方法內的指向，不會影響外面的變數。

## D｜筆數會增加：ArrayList（15 分鐘）

教科書 §7.16、§16.3–16.4。C# List<T> 若尚未學過，以右欄 Java 為準。

先分清腳本與日誌：{1,1,1,1}有四筆預定指令，但從20/0出發第三次探索就達標，因此只記三筆。陣列可搭配有效筆數保存；ArrayList每記錄一步就add，size()直接取得已存筆數，本週用它練習逐筆記錄。

| 操作 | C# List<int> | Java ArrayList<Integer> |
|---|---|---|
| 筆數 | Count | size() |
| 讀第 i 筆 | list[i] | get(i) |
| 改第 i 筆 | list[i]=x | set(i,x) |
| 尾端新增 | Add(x) | add(x) |
| 刪索引 | RemoveAt(i) | remove(i) |

執行 ListBasics：

| 項目 | 預測 | 本人實際 |
|---|---|---|
| 原清單/size/first |  [1, 1, 2]／3／1 | [1, 1, 2]／3／1，符合預期 |
| set(1,3) 後 | [1, 3, 2] | [1, 3, 2]，符合預期 |
| remove(0) 後 | [3, 2] | [3, 2]，符合預期 |
| remove(Integer.valueOf(3)) 後 | [2] | [2]，符合預期 |
| isEmpty() | alse | false，符合預期 |

int 放入清單會裝箱，讀成 int 會拆箱；ArrayList<int> 為何不能編譯：因為 Java 的 ArrayList 泛型只能使用參考型態，不能直接使用 int 這種基本型態，所以要改用 ArrayList<Integer>。加入 int 時會自動裝箱成 Integer，取出時也能自動拆箱成 int。可以在副本重現編譯訊息，但必須恢復可編譯版本。remove 的兩種用途只需能分辨，不要求說明完整過載規則。

## E｜新增、回放、計數（35 分鐘）

教師提供第三週的行動規則、迴圈與方法骨架，先只完成 TODO **2→3→1**。每完成一項就編譯。F1 再完成 4、5。

- TODO 2：每步執行後把 command、energy、result 同時 add；拒絕、無效與返回也要保存。
- TODO 3：同一索引讀三條清單，格式 `step 1: command=1, result=OK, energy=14`。
- TODO 1：建立 int[4]，計算日誌中的 0–3 指令；越界指令不進 counts。

{3,9,0} 的逐步追蹤：

| 步 | 指令 | 預測結果/執行後能源 | 本人實際結果/能源 |
|---|---|---|---|
| 1 | 3 |  ALREADY_FULL / 20 | ALREADY_FULL / 20 |
| 2 | 9 | UNKNOWN_COMMAND / 20 | UNKNOWN_COMMAND / 20 |
| 3 | 0 | RETURN / 20 | RETURN / 20 |

此時 commandLog/energyLog/resultLog 各有幾筆：各 3 筆actionCount 是幾次：0 次。差異原因：日誌會記錄每一筆已處理的指令，包括拒絕、無效指令和返回；但 actionCount 只計算成功執行的行動，因此雖然有 3 筆日誌，成功行動次數仍是 0。

完成 F1 的摘要後，補齊六條本人測試。每列修改 main 固定腳本並重新編譯，初始值 20/0；將**完整輸出**貼在本表下方，包含回放、counts、摘要、最低能源、最終狀態與 battery。

| script | 預期狀態/結果 | 本人實際狀態/結果 | counts、摘要、每筆回放均相符？ |
|---|---|---|---|
| {0} | 20/0/0 RETURNED |20/0/0 RETURNED | 是，counts、摘要與每筆回放均相符 |
| {1,1,2} | 6/5/3 SUCCESS | 6/5/3 SUCCESS | 是，counts、摘要與每筆回放均相符 |
| {2,2,2,2,2} | 10/5/5 SUCCESS | 10/5/5 SUCCESS | 是，counts、摘要與每筆回放均相符 |
| {3,9,0} | 20/0/0 RETURNED | 20/0/0 RETURNED | 是，counts、摘要與每筆回放均相符 |
| {1,1,1,1} | 2/6/3 SUCCESS |  2/6/3 SUCCESS | 是，counts、摘要與每筆回放均相符 |
| {2,1} | 12/3/2 UNFINISHED |12/3/2 UNFINISHED | 是，counts、摘要與每筆回放均相符 |

**完整輸出：**

### 測試 1｜{0}

```text
=== replay ===
step 1: command=0, result=RETURN, energy=20
counts[0..3]=[1, 0, 0, 0]
steps=1, ok=0, rejected=0, ignored=0
lowest energy=20
final energy=20, samples=0, actionCount=0 -> RETURNED
battery=100.0
```

### 測試 2｜{1,1,2}

```text
=== replay ===
step 1: command=1, result=OK, energy=14
step 2: command=1, result=OK, energy=8
step 3: command=2, result=OK, energy=6
counts[0..3]=[0, 2, 1, 0]
steps=3, ok=3, rejected=0, ignored=0
lowest energy=6
final energy=6, samples=5, actionCount=3 -> SUCCESS
battery=30.0
```

### 測試 3｜{2,2,2,2,2}

```text
=== replay ===
step 1: command=2, result=OK, energy=18
step 2: command=2, result=OK, energy=16
step 3: command=2, result=OK, energy=14
step 4: command=2, result=OK, energy=12
step 5: command=2, result=OK, energy=10
counts[0..3]=[0, 0, 5, 0]
steps=5, ok=5, rejected=0, ignored=0
lowest energy=10
final energy=10, samples=5, actionCount=5 -> SUCCESS
battery=50.0
```

### 測試 4｜{3,9,0}

```text
=== replay ===
step 1: command=3, result=ALREADY_FULL, energy=20
step 2: command=9, result=UNKNOWN_COMMAND, energy=20
step 3: command=0, result=RETURN, energy=20
counts[0..3]=[1, 0, 0, 1]
steps=3, ok=0, rejected=2, ignored=0
lowest energy=20
final energy=20, samples=0, actionCount=0 -> RETURNED
battery=100.0
```

### 測試 5｜{1,1,1,1}

```text
=== replay ===
step 1: command=1, result=OK, energy=14
step 2: command=1, result=OK, energy=8
step 3: command=1, result=OK, energy=2
counts[0..3]=[0, 3, 0, 0]
steps=3, ok=3, rejected=0, ignored=1
lowest energy=2
final energy=2, samples=6, actionCount=3 -> SUCCESS
battery=10.0
```

### 測試 6｜{2,1}

```text
=== replay ===
step 1: command=2, result=OK, energy=18
step 2: command=1, result=OK, energy=12
counts[0..3]=[0, 1, 1, 0]
steps=2, ok=2, rejected=0, ignored=0
lowest energy=12
final energy=12, samples=3, actionCount=2 -> UNFINISHED
battery=60.0
```

繳交前恢復預設腳本 `{1,1,2}`。六組路線均已完成本人實測，結果符合預期。繳交前恢復 {1,1,2}。課堂沒完成的路線，課後補齊本人實測。

## F｜摘要、需求與反例（30 分鐘）

### F1 Collections 與 TODO 4–5（10 分鐘）

先執行 CollectionsDemo，預測 min/max/frequency：6／20／1；實際：6／20／1，符合預期。排序後 original：[14, 8, 20, 6]；sorted：[6, 8, 14, 20]。為何不能只排序 energyLog 來做回放：因為會破壞原本的執行順序，導致能源紀錄無法正確對應指令與結果。

- TODO 4：ok=frequency(resultLog,"OK")，returned=frequency(resultLog,"RETURN")；steps=size，rejected=steps-ok-returned，ignored=scriptLength-steps。
- TODO 5：isEmpty 為 true 時印 `lowest energy=N/A`；否則才用 min。
- 最低能源只看每步結束後的 energyLog，不含初始能源。

教師使用 FixedScriptMission 示範以下案例；學生填預測與觀察，不需另寫入口。未觀察請註明待補；自己另跑者再註明「本人複驗」。

| 初始 energy/samples；script | 預測結果/日誌筆數/最低能源 | 教師實際示範觀察 |
|---|---|---|
| 5/0；{1,3,0} | RETURNED／3 筆／5 | 未觀察（待補） |
| 2/4；{2} | SUCCESS／1 筆／0 | 未觀察（待補） |
| 0/0；{3,1} | EXHAUSTED／0 筆／N/A | 未觀察（待補） |
| 20/5；{1} | SUCCESS／0 筆／N/A | 未觀察（待補） |
| 20/0；{} | UNFINISHED／0 筆／N/A | 未觀察（待補） |

### F2 Prompt 設計（10 分鐘）

目的：把剛才實作的日誌轉成清楚的資料需求，作為判斷 AI 建議是否適合的依據。

先填下表，再自行組成可直接提問的一段 Prompt。接著用這份需求判讀下方兩個模擬建議。沿用原本的 MissionLog，本段產出為一段 Prompt 與兩項判斷；真實 AI 服務可選。

| 要交代的需求 | 本人的內容 |
|---|---|
| 欄位與型態 |  command 為 int、energy 為 int、result 為 String。 |
| 筆數是否事先固定 | 固定腳本有長度上限，但實際日誌筆數不一定固定，未來也可能改成互動輸入。 |
| 是否保留順序 | 需要保留順序，才能依照執行先後回放。 |
| 是否容許重複 | 允許重複，同一種指令可以執行多次。|
| 新增、回放、計數與最低值等操作 | 使用 add() 新增、get() 回放、int[4] 計數、Collections.frequency() 統計結果、Collections.min() 找最低能源。 |
| 允許的技術與驗證案例 | 使用陣列、ArrayList、Collections；驗證 {1,1,2}、{3,9,0}、{0}，並處理空日誌。 |

完整 Prompt：我正在使用 Java 製作火星探測車任務日誌，每筆資料包含指令 command（int）、執行後能源 energy（int）及執行結果 result（String）。日誌筆數不一定固定，需要保留順序、允許重複，也要記錄失敗和返回的指令。功能包含逐筆新增、依序回放、統計指令 0～3 的次數、計算成功與拒絕次數，以及找出最低能源。空日誌時需顯示 N/A。請使用目前學過的陣列、ArrayList 和 Collections 提出合適的資料結構，說明理由，並用 {1,1,2}、{3,9,0}、{0} 驗證。

判讀下列**模擬建議**：

| 建議 | 採納/拒絕/補充條件與理由 |
|---|---|
| 歷程最多用 int[100] 保存就好 | 有條件採納。若確定最多 100 筆，且有記錄有效筆數及容量超出時的處理方式，可以使用固定陣列。但日誌包含不同資料型態，不能只靠一個 int[100] 完整保存。 |
| ArrayList 保存日誌，HashSet 保存指令，避免重複 |  部分採納。ArrayList 適合保存日誌，但不採用 HashSet 取代指令紀錄，因為任務允許重複探索，也必須保留執行順序，而 HashSet 會去除重複值。 |

不要求實作 HashSet；依 List/Set/Map 的概觀判讀即可。使用固定腳本時筆數有上限，但日誌仍逐步成長且將來可能回到互動輸入；不能一概宣稱陣列無法記錄歷程。

### F3 模擬 AI 除錯二選一（10 分鐘）

先 commit 人工版，在副本測試，最後恢復正確版。

1. 刪掉 countCommands 的範圍檢查，理由「指令只有 0–3」；反例可用 {3,9,0}。
2. 把 printReplay 的 `< commandLog.size()` 改成 `<= commandLog.size()`，理由「最後一步也要印」；反例可用 {0}。

| 證據 | 本人紀錄 |
|---|---|
| 選哪種建議與反例；預期行為 | 選擇建議 1，刪除 countCommands 的範圍檢查。反例為 {3,9,0}，正確行為應忽略指令 9，counts=[1,0,0,1]。 |
| 修改前實際結果/錯誤訊息 | 正確版本已實測，結果為 counts=[1,0,0,1]。若移除範圍檢查，預期會發生 ArrayIndexOutOfBoundsException: Index 9 out of bounds for length 4；錯誤版本尚未實測。 |
| 最小修正與採納/拒絕理由 | 拒絕此建議，保留 if (command >= 0 && command < counts.length)，避免無效指令造成陣列索引越界。 |
| 修正後反例實際結果 | 正確版本已實測 {3,9,0}，counts=[1,0,0,1]，最終為 20/0/0 RETURNED，沒有發生例外；尚未完成錯誤版本修復後的重新測試。 |
| 修正後 {1,1,2} | 正確版本已實測：counts=[0,2,1,0]，最終 6/5/3 SUCCESS，可引用 E 區紀錄。 |
| 修正後 {3,9,0} | 正確版本已實測：counts=[1,0,0,1]，最終 20/0/0 RETURNED，可引用 E 區紀錄。 |

若時間不足，課後補測；AI_USAGE 的驗證欄可引用本表，不必重抄。

## G｜課末檢核與第五週入口（5 分鐘）

1. length、size()、length() 分別用於什麼：length 用於陣列長度；size() 用於 ArrayList 的元素數量；length() 用於 String 的字元數。
2. 三筆日誌是否必定代表三次成功行動：不一定。日誌包含成功、拒絕、無效及返回指令，actionCount 只計算成功行動。
3. 三條清單漏加其中一筆會發生什麼：三條清單的資料會錯位，可能導致回放內容對應錯誤，甚至因索引超出範圍而發生例外。
4. 為何固定四種指令計數用陣列，逐步增加的日誌用 ArrayList：指令只有固定的 0～3 四種類型，適合用 int[4] 計數；日誌需要逐筆增加且筆數不固定，使用 ArrayList 比較方便。
5. 下週可以建立一個類別，把同一步的 command、energy、result 整合成一個物件，再使用 ArrayList 保存每筆日誌，避免三條清單資料錯位。

## H｜保存、推送與取回（15 分鐘）

- 恢復預設腳本，重新編譯執行；LAB_REPORT、AI_USAGE、專案 README 完成。
- commit、push，核對 GitHub main 最新 commit 與來源檔。只 commit 或 Changes 空白不代表已推送。
- 在另一位置 clone，同一專案重新編譯並跑 MissionLog；課堂不足可課後完成。

GitHub repo：https://github.com/a112222029/115_1_Java
作業目錄：115_10_30/java-04
本人新副本 JDK/編譯/預設輸出：尚未在新位置 Clone 驗證（待補）。目前原專案使用 OpenJDK 27（Temurin-27+35），已編譯成功，預設腳本 {1,1,2} 輸出 6/5/3 SUCCESS、battery=30.0。
推送若失敗先請教師協助，仍無法推送時保存完整 repo 至不會還原的位置，確認可讀並後續補推。

## 課後選做

- ScriptArgs：執行 `java -cp out ScriptArgs 3 9 0`，說明 args 的元素型態；非整數例外不列必做。
- 新增自訂路線，比較達標時剩餘能源與成功行動數，不要求最佳化演算法。
- 只顯示成功步驟時，以 if 跳過顯示；保留原日誌，驗證 counts 和摘要仍相同。
