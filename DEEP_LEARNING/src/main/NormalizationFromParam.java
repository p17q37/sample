package main;

import java.io.IOException;
import java.util.List;

import common.FormatConvertUtil;
import common.WriteFile;
import dataAccess.ReadInputDataCSV;

public class NormalizationFromParam {

    private static final int KETA = 7;

    // 引数１：標準化するファイルディレクトリ（フォルダのみ）
    // 引数２：標準化するファイル名
    // 引数３：出力するディレクトリ（フォルダのみ）
    // 引数４：標準化する平均、標準偏差のパラメータディレクトリ
    public static void main(String[] args) throws IOException {

        // 標準化するファイル
        List<String[]> list = ReadInputDataCSV.readCSVList(args[0] + "\\" + args[1]);
        List<String[]> paramList = ReadInputDataCSV.readCSVList(args[3]);
        double[][] data = new double[list.size()][list.get(0).length];

        // 標準化されたデータの作成
        for (int i = 0; i < list.size(); i++) {
            double[] line = FormatConvertUtil.stringArrayToDoubleArray(list.get(i));
            for (int j = 0; j < line.length; j++) {
                if (Double.parseDouble(paramList.get(1)[j + 1]) == 0.0) {
                    data[i][j] = line[j] - Double.parseDouble(paramList.get(0)[j + 1]);
                } else {
                    data[i][j] = (line[j] - Double.parseDouble(paramList.get(0)[j + 1]))
                            / Double.parseDouble(paramList.get(1)[j + 1]);
                }

            }
        }

        // 標準化された出力ファイル
        WriteFile.writeStringArray2ToCSV(args[2] + "\\" + "Normalization_" + args[1],
                FormatConvertUtil.doubleArray2ToStringArray2(data, KETA));
    }

}
