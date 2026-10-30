import java.util.Arrays;

// 選讀：命令列參數是字串，整數解析不是本週必做。
public class ScriptArgs {
    public static void main(String[] args) {
        // 指令編號：0＝返回、1＝探索、2＝掃描、3＝充電；其他整數為無效指令。
        // 例如參數 3 9 0 代表充電、無效指令、返回；本例只解析與列印，不執行動作。
        int[] script = new int[args.length];
        for (int i = 0; i < args.length; i++) {
            script[i] = Integer.parseInt(args[i]);
        }
        System.out.println("script=" + Arrays.toString(script));
        // 本例約定輸入 Java int 範圍內整數；非整數與超出範圍的參數留到例外處理。
    }
}
