# 第三週學生實作：火星探測車任務控制員

作業的必做範圍、繳交清單與驗收標準見 [assignment.md](assignment.md)。本講義另存後作為實作與測試報告；教師固定狀態示範與學生必寫程式的區別也列於作業說明。

姓名／暱稱：待填　上課日期：待填　JDK 版本：待填

本週 A–F 必做，G 選做。故事中的能源是遊戲規則，不是太空工程模型。已有 C# WinForms 經驗：將按鈕選擇行動的想法，轉為主控台整數指令；今天先使用現成 Scanner，不自行設計 Rover 類別。

## A｜取回成果與暖身（10 分鐘）

1. 還原後無 repo：GitHub Desktop clone `115_1_Java`。已有副本：先保留未提交工作，再 Fetch／Pull；衝突請教師協助，不覆蓋舊成果。
2. 建立 `YYYY_MM_DD/java-03/src`，日期由教師公告。複製 examples 的四個 Java 檔及 starter/RoverMission.java 至 src；同名教師答案不可一起複製。
3. 本講義另存當日 `LAB_REPORT.md`，AI 範本另存 `AI_USAGE.md`。沿用 repo 根 .gitignore；沒有時合併 starter/.gitignore 規則。
4. 在 java-03 執行 `javac --release 21 -encoding UTF-8 -d out src/*.java`，再 `java -cp out RoverMission`，輸入 0。TODO 程式應能返回，但探索等功能尚未完成。

| 診斷 | 我的預測／理由 | 執行核對 |
|---|---|---|
| `7 / 20 * 100.0` 與 `7 / 20.0 * 100` | 待填 | 待填 |
| energy=6、locked=false，`energy >= 6 && !locked` | 待填 | 待填 |
| `calculateBatteryPercent(6)` 回傳什麼？誰負責輸出？ | 待填 | 待填 |

本機 repo 路徑：待填。當日專案：待填。初版 commit：待填。

## B｜單次指令：先判斷，再更新（40 分鐘）

### B1 能源分類

閱讀 EnergyStatus.java。對照 C#：if 的條件仍為 boolean；本週一律寫大括號，避免 else 配錯。`else if` 只在前一條件為 false 時判斷。

| energy | 預測 INVALID／LOW／READY | 實際 | 是否一致 |
|---:|---|---|---|
| -1 | 待填 | 待填 | 待填 |
| 0 | 待填 | 待填 | 待填 |
| 5 | 待填 | 待填 | 待填 |
| 6 | 待填 | 待填 | 待填 |
| 20 | 待填 | 待填 | 待填 |
| 21 | 待填 | 待填 | 待填 |

如果先寫 `energy < 6`，再檢查是否小於 0，哪筆資料會被誤分類？理由：待填。

### B2 單次行動

執行 SingleAction。一次只改一個值並重新編譯，完成後恢復原版。

| 初始 energy／command | 預測 energy／samples／訊息 | 實際 |
|---|---|---|
| 6／1 | 待填 | 待填 |
| 5／1 | 待填 | 待填 |
| 6／2 | 待填 | 待填 |
| 6／9 | 待填 | 待填 |

`switch` 依 command 選 case；`break` 離開 switch；找不到符合值執行 default。暫時移除 case 1 的 break，用 20／1 預測再實測，隨即還原。一次指令做了幾種行動？待填。這與 C# 常見 switch 寫法有何需要特別留意的差異？待填。

## C｜迴圈讓任務持續（50 分鐘）

### C1 固定三輪充電（15 分鐘）

執行 ChargeForecast，先填表。`round=1` 只做一次；每輪先判斷 `round<=3`，執行主體，最後 `round++`。

| 起點 | 第 1 輪預測／實際 | 第 2 輪預測／實際 | 第 3 輪預測／實際 |
|---|---|---|---|
| 4 | 待填／待填 | 待填／待填 | 待填／待填 |
| 18 | 待填／待填 | 待填／待填 | 待填／待填 |

改成 `round < 3` 會跑幾次？待填。為何把 energy 初始化放在迴圈外？待填。迴圈結束後可以使用 round 嗎？待填。驗證後恢復原版。

### C2 讀取指令（10 分鐘）

教師提供的三行分別是：

```java
import java.util.Scanner; // 放 class 之前，讓程式能用 Scanner 短名稱
// 下列兩行放在方法內
Scanner input = new Scanner(System.in);
int command = input.nextInt();
```

Scanner 是現成輸入工具；System.in 是標準輸入；nextInt 取得一個整數，無資料時等待輸入。`input.close()` 放所有任務完成之後；此獨立程式結束後不再讀取標準輸入。今天輸入 Java int 範圍內整數，不混用 nextLine；非整數、輸入結束及例外處理留後續。

將 RoverMission 的 TODO 原版跑一次，輸入 9 再輸入 0。兩者訊息：待填。為何輸入 0 後仍會印最後一次儀表板？待填。

### C3 先判斷或後判斷、狀態追蹤（25 分鐘）

執行 LoopComparison 前預測 whileCount=待填、doCount=待填；實際=待填。兩者第一次條件為 false 時有何差異？待填。

RoverMission 的 while 條件：`running && energy > 0 && samples < 5`。各部分為 false 的停止原因依序是：待填／待填／待填。命令 0 的 break 只離 switch；真正讓下一輪停止的是：待填。

預測完成後程式的路線 1、1、2（此時先用紙筆，D 完成後回填實測）：

| 輪次／指令 | energy 前→後 | samples 前→後 | actionCount 前→後 | 下輪條件 |
|---|---|---|---|---|
| 1／1 | 待填 | 待填 | 待填 | 待填 |
| 2／1 | 待填 | 待填 | 待填 | 待填 |
| 3／2 | 待填 | 待填 | 待填 | 待填 |

energy、samples、actionCount 在 while 外建立，可跨輪累計；command 只用於本輪，宣告在 while 裡。若每輪開頭都執行 `samples = 0;` 會怎樣？待填。注意這是重新指定值；不可在其有效範圍內再宣告同名區域變數。

## D｜補完互動任務與 AI 除錯（25 分鐘）

起始 energy=20、samples=0、actionCount=0。目標至少 5 份樣本，滿電 20。0 能源立即結束，不能再充電；達標且同時耗盡時成功優先。

| TODO | 成功更新 | 拒絕時 |
|---|---|---|
| D1 探索 | energy 至少 6：扣 6、samples 加 2、actionCount 加 1 | 只顯示 NOT_ENOUGH_ENERGY |
| D2 掃描 | energy 至少 2：扣 2、samples 加 1、actionCount 加 1 | 同上 |
| D3 充電 | energy 小於 20：加 5，以 if 封頂 20、actionCount 加 1 | 滿電顯示 ALREADY_FULL，不計次 |

保留 case 0、default、輸入、顯示與停止骨架。由教師引導完成 D1，測能源界線；模仿完成 D2；獨立完成 D3。每個成功行動才計次，不能把 actionCount++ 無條件放到 switch 後。測試每列都重新啟動。

| 指令序列 | 預期最終 energy／samples／actionCount／結果 | 實測 |
|---|---|---|
| 0 | 20／0／0／RETURNED | 待填 |
| 1、1、2 | 6／5／3／SUCCESS | 待填 |
| 2、2、2、2、2 | 10／5／5／SUCCESS | 待填 |
| 3、9、0 | 20／0／0／RETURNED | 待填 |

以下三筆由教師操作固定狀態入口，學生先預測再觀察、填寫實測欄，**不要求每位學生逐筆修改 main**。課後自行複驗時，才暫改 `runMission(input, 20, 0)` 的後兩個引數，測後恢復 20、0。

| 初始 energy／samples | 指令 | 預期最終 energy／samples／actionCount／結果 | 實測 |
|---|---|---|---|
| 5／0 | 1、3、0 | 10／0／1／RETURNED | 待填 |
| 6／0 | 1 | 0／2／1／EXHAUSTED | 待填 |
| 2／4 | 2 | 0／5／1／SUCCESS | 待填 |

AI 除錯三選一，先保存人工初版。以下是**教師設計的模擬 AI 錯誤**，不是真實服務對話：

1. AI 將探索條件改成 `energy > 6`。
2. AI 認為 case 1 尾端 break 多餘，將它移除。
3. AI 在每次 while 開頭加上 `samples = 0;`。

選第待填題。預測會錯的輸入：待填。最小修正：待填。採納或拒絕原因：待填。整份紀錄可只使用同一個模擬 AI 錯誤，不必測完三種錯誤，也無須取得 AI 帳號。

對所選錯誤保留以下三筆證據：第一筆記錄錯誤重現與修正；後兩筆確認修正後仍可成功及返回。狀態順序為 energy／samples／actionCount；AI_USAGE 可直接引用本表，不必重抄，但不能只填「通過」。

| 證據 | 初始狀態／輸入 | 預期 | 實際結果 |
|---|---|---|---|
| 反例：修改前與修正後比較 | 待填 | 待填 | 修改前：待填；修正後：待填 |
| 修正後一般成功 | 20／0，2、2、2、2、2 | 10／5／5 SUCCESS | 待填 |
| 修正後返回／拒絕 | 20／0，3、9、0 | 20／0／0 RETURNED | 待填 |

當堂至少完成一筆人工反例、最小修正及重測。若 25 分鐘不足，後兩筆回歸測試可於課後補完，繳交前仍需三筆齊全；不得挪用最後 15 分鐘的保存時間，未測欄位先寫「待補測」。

## E｜課末檢核與下一週（10 分鐘）

1. 用一句話比較 1、1、2 與五次 2 的策略取捨：待填。
2. energy=2、samples=4 掃描後為何判定成功？待填。
3. 若只寫 case 0: break，下一輪會發生什麼？待填。
4. 只有最後三個狀態值，能否回放每一步？缺少什麼能力？待填。下週將用陣列與集合保存多筆資料，本週不實作。

## F｜保存、推送、取回驗證（15 分鐘）

- [ ] 存檔並重新編譯；LAB_REPORT、AI_USAGE、src、README 齊全，不交 out 或 .class。
- [ ] Desktop 檢查差異，commit 描述修改目的，Push origin；到 GitHub 核對當日來源與紀錄。
- [ ] 有家中副本，續作前 Fetch／Pull；CLI 選讀使用 `git pull --ff-only`，失敗先請教師診斷，不強制覆蓋。
- [ ] 以不同本機路徑建立全新 clone，在 java-03 重編譯並跑 1、1、2；不可刪原副本。未完成者標示待驗證。

最新 commit／遠端核對：待填。全新 clone 路徑、版本、指令、結果：待填。未完成事項：待填。推送失敗時保留完整 repo 到不受還原影響的位置、確認可讀並請教師協助，不直接關機。

## G｜課後選做

自訂探測車名稱與訊息；或新增一種行動，先寫成本、效果、拒絕條件與三筆邊界測試。不要加入隨機事件、GUI、地圖或背包。從空白重寫完整程式是延伸，不是當堂必做。
