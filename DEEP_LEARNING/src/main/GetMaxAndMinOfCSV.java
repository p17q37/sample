package main;

import java.io.BufferedReader;
import java.io.IOException;

import common.ReadFile;

public class GetMaxAndMinOfCSV {

    /**
     * 説明：対象csvの最大値、最小値を出力する
     * 引数１：csvのディレクトリ
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {

        double max = 0.0;
        double min = 0.0;

        BufferedReader br = ReadFile.readFile(args[0]);
        String line = "";
        int cnt = 1;
        while ((line = br.readLine()) != null) {
            String[] lineSplit = line.split(",");
            for (String var : lineSplit) {
                double varD = Double.parseDouble(var);
                if (cnt == 1) {
                    max = varD;
                    min = varD;
                }
                if (varD > max) {
                    max = varD;
                }
                if (varD < min) {
                    min = varD;
                }
                cnt++;
            }
        }
        System.out.println("最大値：" + max);
        System.out.println("最小値：" + min);
    }

}
