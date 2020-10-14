package main;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import common.WriteFile;

public class CreateNumberMatrixCSV {

    private final static double AVERAGE = 0.0;
    private final static int KETA = 7;

    // 引数１：出力ディレクトリ（フォルダ）
    // 引数２：行数
    // 引数３：列数
    // 引数４：「0→すべて0」「それ以外→ランダム」
    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {

        LocalDateTime d = LocalDateTime.now();
        DateTimeFormatter df1 = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String dateTime = df1.format(d);

        int row = Integer.parseInt(args[1]);
        int column = Integer.parseInt(args[2]);
        String[][] matrix = new String[row][column];

        // 標準偏差
        double sd = 1.0 / Math.pow(column, 0.5);

        if (args[3].equals("0")) {
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < column; j++) {
                    matrix[i][j] = "0";
                }
            }
        } else {
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < column; j++) {
                    // matrix[i][j] = BigDecimal.valueOf(((MAX - MIN) * Math.random() + MIN)).toPlainString();
                    matrix[i][j] = String.format("%." + KETA + "f", randomNormal(AVERAGE, sd));
                }
            }
        }

        WriteFile.writeStringArray2ToCSV(args[0] + "\\" + dateTime + "_" + args[1] + "×" + args[2] + ".csv", matrix);

    }

    /**
     * 引数の平均と標準偏差のランダムな数を返す
     * @param average
     * @param sd
     * @return
     */
    private static double randomNormal(double average, double sd) {
        double xw = 0.0;
        double x;
        int n;
        for (n = 1; n <= 12; n++) { /* 12個の一様乱数の合計 */
            xw = xw + Math.random();
        }
        x = sd * (xw - 6.0) + average;
        return (x);
    }

}
