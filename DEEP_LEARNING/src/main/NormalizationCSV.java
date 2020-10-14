package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;

import common.ReadFile;
import common.WriteFile;

public class NormalizationCSV {

    private static final int KETA = 7;

    // 引数１：標準化する入力ファイルディレクトリ
    // 引数２：パラメータ出力ファイルディレクトリ
    public static void main(String[] args) throws IOException {

        // 平均
        double[] ave = null;
        // 標準偏差
        double[] sd = null;

        // ファイル読み込み（平均用）
        BufferedReader br = ReadFile.readFile(args[0]);

        double cnt = 0.0;
        String line = "";
        String[] csv = null;

        // とりあえず1レコード読み込み
        csv = br.readLine().split(",");
        // 平均初期化
        ave = new double[csv.length];
        Arrays.fill(ave, 0.0);
        // 標準偏差初期化
        sd = new double[csv.length];
        Arrays.fill(sd, 0.0);
        // 最初の1レコードを平均に加算
        for (int i = 0; i < csv.length; i++) {
            ave[i] += Double.parseDouble(csv[i]);
        }
        // レコード数加算
        System.out.println(++cnt);

        // 平均算出のため全件読み込み
        while ((line = br.readLine()) != null) {
            csv = line.split(",");
            // 1レコードを平均に加算
            for (int i = 0; i < csv.length; i++) {
                ave[i] += Double.parseDouble(csv[i]);
            }
            // レコード数加算
            System.out.println(++cnt);
        }
        // 平均算出
        for (int i = 0; i < ave.length; i++) {
            ave[i] = ave[i] / cnt;
            WriteFile.writePlint(args[1], String.format("%." + KETA + "f", ave[i]), true);
            if (i != ave.length - 1) {
                WriteFile.writePlint(args[1], ",", true);
            }
        }
        // 改行
        WriteFile.writePlintln(args[1], "", true);

        // ファイル読み込み（標準偏差用）
        br = ReadFile.readFile(args[0]);

        int cnt2 = 0;
        // 標準偏差の計算
        while ((line = br.readLine()) != null) {
            csv = line.split(",");
            // 1レコードを標準偏差に加算
            for (int i = 0; i < csv.length; i++) {
                sd[i] += Math.pow(Double.parseDouble(csv[i]) - ave[i], 2.0) / cnt;
            }
            // レコード数加算
            System.out.println(++cnt2);
        }
        // 標準偏差算出
        for (int i = 0; i < sd.length; i++) {
            sd[i] = Math.pow(sd[i], 0.5);
            WriteFile.writePlint(args[1], String.format("%." + KETA + "f", sd[i]), true);
            if (i != sd.length - 1) {
                WriteFile.writePlint(args[1], ",", true);
            }
        }
        // 改行
        WriteFile.writePlintln(args[1], "", true);

    }

}
