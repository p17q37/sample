package common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import common.EnumCollections.DerivativeCount;

public class Calculation {

    //************************   活性化関数 START   ************************
    // 恒等写像（ベクトル）
    static double[] identity(double[] vector, DerivativeCount dCount) {

        double[] kekka = new double[vector.length];
        Arrays.fill(kekka, 0.0);

        switch (dCount) {
        case ZERO:
            kekka = vector.clone();
            break;
        case ONE:
            for (int i = 0; i < vector.length; i++) {
                kekka[i] = 1.0;
            }
            break;
        default:
            break;
        }
        return kekka;

    }

    // シグモイド（ベクトル）
    static double[] sigmoidVector(double[] vector, DerivativeCount dCount) {

        double[] result = new double[vector.length];
        Arrays.fill(result, 0.0);

        switch (dCount) {
        case ZERO:
            for (int i = 0; i < vector.length; i++) {
                result[i] = sigmoid(vector[i]);
            }
            break;
        case ONE:
            for (int i = 0; i < vector.length; i++) {
                result[i] = sigmoid(vector[i]) * (1 - sigmoid(vector[i]));
            }
            break;
        default:
            break;

        }

        return result;

    }

    // ReLU（ベクトル）
    static double[] ReLU(double[] vector, DerivativeCount dCount) {

        double[] kekka = new double[vector.length];
        Arrays.fill(kekka, 0.0);

        switch (dCount) {
        case ZERO:
            for (int i = 0; i < vector.length; i++) {
                if (vector[i] > 0) {
                    kekka[i] = vector[i];
                } else {
                    kekka[i] = 0.0;
                }
            }
            break;
        case ONE:
            for (int i = 0; i < vector.length; i++) {
                if (vector[i] > 0) {
                    kekka[i] = 1.0;
                } else {
                    kekka[i] = 0.0;
                }
            }
            break;
        default:
            break;
        }
        return kekka;
    }

    // ソフトマックス（ベクトル）
    static double[] softmax(double[] vector, DerivativeCount dCount) {

        double[] kekka = new double[vector.length];
        Arrays.fill(kekka, 0.0);

        double napierExponentialSum = 0.0;
        for (int i = 0; i < vector.length; i++) {
            napierExponentialSum += Math.pow(Math.E, vector[i]);
        }

        switch (dCount) {
        case ZERO:
            for (int i = 0; i < vector.length; i++) {
                kekka[i] = Math.pow(Math.E, vector[i]) / napierExponentialSum;
            }
            break;
        case ONE:
            for (int i = 0; i < vector.length; i++) {
                kekka[i] = (Math.pow(Math.E, vector[i]) / napierExponentialSum)
                        * (1 - (Math.pow(Math.E, vector[i]) / napierExponentialSum));
            }
            break;
        default:
            break;
        }
        return kekka;
    }

    // ハイポリックタンジェント（ベクトル）
    static double[] tanh(double[] vector, DerivativeCount dCount) {

        double[] kekka = new double[vector.length];
        Arrays.fill(kekka, 0.0);

        switch (dCount) {
        case ZERO:
            for (int i = 0; i < vector.length; i++) {
                kekka[i] = (Math.pow(Math.E, vector[i]) - Math.pow(Math.E, (-1) * vector[i]))
                        / (Math.pow(Math.E, vector[i]) + Math.pow(Math.E, (-1) * vector[i]));
            }
            break;
        case ONE:
            for (int i = 0; i < vector.length; i++) {
                kekka[i] = 1 - Math.pow((Math.pow(Math.E, vector[i]) - Math.pow(Math.E, (-1) * vector[i]))
                        / (Math.pow(Math.E, vector[i]) + Math.pow(Math.E, (-1) * vector[i])), 2);
            }
            break;
        default:
            break;
        }
        return kekka;
    }

    // シグモイドReLU（ベクトル）
    static double[] sigmoidReLU(double[] vector, DerivativeCount dCount) {

        double[] kekka = new double[vector.length];
        Arrays.fill(kekka, 0.0);

        switch (dCount) {
        case ZERO:
            for (int i = 0; i < vector.length; i++) {
                kekka[i] = vector[i] / (1 + Math.pow(Math.E, (-1) * vector[i]));
            }
            break;
        case ONE:
            // 未実装
            break;
        default:
            break;
        }
        return kekka;

    }
    //************************   活性化関数 END   ************************

    // シグモイド関数
    public static double sigmoid(double arg) {
        double result = 0.0;
        result = 1 / (1 + Math.pow(Math.E, (-1) * arg));
        if (result == 0.0) {
            result += 0.0000000001;
        }
        if (result == 1.0) {
            result -= 0.0000000001;
        }
        return result;
    }

    // 行列×ベクトル
    public static double[] calcMaltiVectorAndMatrix(double[][] matrix, double[] vector) {

        if (!(vector.length == matrix[0].length)) {
            System.out.print("ERROR:Calculation;calcMaltiVectorAndMatrix,");
            System.out.print("vector.length=" + vector.length + ",");
            System.out.println("matrix[0].length=" + matrix[0].length);
        }

        double[] kekka = new double[matrix.length];
        Arrays.fill(kekka, 0.0);

        for (int i = 0; i < kekka.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                kekka[i] += matrix[i][j] * vector[j];
            }
        }

        return kekka;
    }

    // 横ベクトル×行列
    public static double[] calcMaltiVectorAndMatrixT(double[] vector, double[][] matrix) {

        double[] kekka = new double[matrix[0].length];
        Arrays.fill(kekka, 0.0);

        for (int j = 0; j < kekka.length; j++) {
            for (int i = 0; i < matrix.length; i++) {
                kekka[j] += matrix[i][j] * vector[i];
            }
        }

        return kekka;
    }

    // ベクトル＋ベクトル
    public static double[] calcAddVectorAndVector(double[] vector1, double[] vector2) {

        double[] kekka = new double[vector1.length];
        Arrays.fill(kekka, 0.0);

        if (CheckUtil.vectorSizeCheck(vector1, vector2)) {
            for (int i = 0; i < kekka.length; i++) {
                kekka[i] = vector1[i] + vector2[i];
            }
        } else {
            System.out.println("ERROR:Calculation;calcAddVectorAndVector");
        }
        return kekka;
    }

    // ベクトル－ベクトル
    public static double[] calcMinusVectorAndVector(double[] vector1, double[] vector2) {

        double[] kekka = new double[vector1.length];
        Arrays.fill(kekka, 0.0);

        if (CheckUtil.vectorSizeCheck(vector1, vector2)) {
            for (int i = 0; i < kekka.length; i++) {
                kekka[i] = vector1[i] - vector2[i];
            }
        } else {
            System.out.println("ERROR:Calculation;calcMinusVectorAndVector");
        }
        return kekka;
    }

    // ２つのベクトルの距離の２乗
    public static double distanceVectorAndVector(double[] vector1, double[] vector2) {
        double kekka = 0.0;
        if (CheckUtil.vectorSizeCheck(vector1, vector2)) {
            for (int i = 0; i < vector1.length; i++) {
                kekka += Math.pow(vector1[i] - vector2[i], 2);
            }
        } else {
            System.out.println("ERROR:Calculation;distanceVectorAndVector");
        }
        return kekka;
    }

    // ２つのベクトルの要素同士の掛け算
    public static double[] multiVectorAndVector(double[] vector1, double[] vector2) {
        double kekka[] = new double[vector1.length];
        Arrays.fill(kekka, 0.0);
        if (CheckUtil.vectorSizeCheck(vector1, vector2)) {
            for (int i = 0; i < vector1.length; i++) {
                kekka[i] = vector1[i] * vector2[i];
            }
        } else {
            System.out.println("ERROR:Calculation;multiVectorAndVector");
        }
        return kekka;
    }

    // ２つのベクトルの要素同士の割り算
    public static double[] divVectorAndVector(double[] vector1, double[] vector2) {
        double result[] = new double[vector1.length];
        Arrays.fill(result, 0.0);
        if (CheckUtil.vectorSizeCheck(vector1, vector2)) {
            for (int i = 0; i < vector1.length; i++) {
                if (vector2[i] == 0.0) {
                    System.out.println("0では割れません！！");
                    System.exit(0);
                }
                result[i] = vector1[i] / vector2[i];
            }
        } else {
            System.out.println("ERROR:Calculation;divVectorAndVector");
        }
        return result;
    }

    // double配列を受け取り、logをとってdouble配列で返す
    public static double[] logVector(double[] vector) {
        double result[] = new double[vector.length];
        Arrays.fill(result, 0.0);
        for (int i = 0; i < vector.length; i++) {
            if (vector[i] <= 0.0) {
                System.out.println("log関数は「0」より大きい引数に対して定義されています。引数=" + result[i]);
                System.exit(0);
            }
            result[i] = Math.log(vector[i]);
        }
        return result;
    }

    // 受け取ったdouble型配列の合計値を返す
    public static double sumDoubleArray(double[] vector) {
        double result = 0.0;
        for (int i = 0; i < vector.length; i++) {
            result += vector[i];
        }
        return result;
    }

    // 引数１の自然数の中から引数２の自然数分の自然数をランダムに返す
    public static ArrayList<Integer> randNumFlyAway(int num, int cntMax) {

        // 無限ループを防ぐために、引数１＜引数２なら処理終了
        if (num < cntMax) {
            System.out.println("ERROR:Calculation;randNumFlyAway");
            System.exit(0);
        }

        int cnt = 0;
        ArrayList<Integer> kekka = new ArrayList<>();

        // 必要な数分ループ
        while (cnt < cntMax) {
            // 数を与えられた引数の範囲内でランダム生成
            int randNumKouho = (int) (Math.random() * num);
            // 戻り値のリストに含まれていない数なら追加
            if (!kekka.contains(randNumKouho)) {
                kekka.add(randNumKouho);
                cnt++;
            }
        }

        // リストをソート
        Collections.sort(kekka);
        return kekka;
    }

}
