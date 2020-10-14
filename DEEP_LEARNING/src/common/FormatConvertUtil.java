package common;

public class FormatConvertUtil {

    /**
     * double型をString型に変換する。
     * @param arg
     * @return
     */
    public static String doubleToString(double arg) {
        return String.valueOf(arg);

    }

    /**
     * double型を指定された桁数までのString型に変換する。
     * @param arg
     * @return
     */
    public static String doubleToString(double arg, int digit) {
        return String.format("%." + digit + "f", arg);

    }

    public static double stringToDouble(String arg) {
        return Double.parseDouble(arg);
    }

    public static String[] doubleArrayToStringArray(double[] arg) {
        String[] kekka = new String[arg.length];
        for (int i = 0; i < arg.length; i++) {
            kekka[i] = doubleToString(arg[i]);
        }
        return kekka;
    }

    public static String[] doubleArrayToStringArray(double[] arg, int digit) {
        String[] kekka = new String[arg.length];
        for (int i = 0; i < arg.length; i++) {
            kekka[i] = doubleToString(arg[i], digit);
        }
        return kekka;
    }

    public static double[] stringArrayToDoubleArray(String[] arg) {
        double[] kekka = new double[arg.length];
        for (int i = 0; i < arg.length; i++) {
            kekka[i] = stringToDouble(arg[i]);
        }
        return kekka;
    }

    // double型の2次元配列をString型の2次元配列に変換する
    public static String[][] doubleArray2ToStringArray2(double[][] arg) {
        String[][] kekka = new String[arg.length][arg[0].length];
        for (int i = 0; i < arg.length; i++) {
            for (int j = 0; j < arg[i].length; j++) {
                kekka[i][j] = doubleToString(arg[i][j]);
            }
        }
        return kekka;
    }

    // double型の2次元配列を指定された桁数のString型の2次元配列に変換する
    public static String[][] doubleArray2ToStringArray2(double[][] arg, int digit) {
        String[][] kekka = new String[arg.length][arg[0].length];
        for (int i = 0; i < arg.length; i++) {
            for (int j = 0; j < arg[i].length; j++) {
                kekka[i][j] = doubleToString(arg[i][j], digit);
            }
        }
        return kekka;
    }

    /**
     * String型の配列をcsv形式文字列にして返す。
     * @param strArray
     * @return
     */
    public static String strArrayToCSVstr(String[] strArray) {
        String result = "";
        for (String str : strArray) {
            result += str + ",";
        }
        return result.substring(0, result.length() - 1);
    }

    public static String[] strCSVToStringArray(String strCSV) {
        return strCSV.split(",");
    }
}
