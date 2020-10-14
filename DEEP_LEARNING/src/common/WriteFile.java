package common;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

public class WriteFile {

    private static PrintWriter writeFile(String dir, boolean flg)
            throws FileNotFoundException, UnsupportedEncodingException {
        return new PrintWriter(
                new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(dir), flg), "SJIS")));
    }

    /**
     * 改行して書き込み
     * flg=true:追記
     * @param dir
     * @param text
     * @param flg
     * @throws FileNotFoundException
     * @throws UnsupportedEncodingException
     */
    public static void writePlintln(String dir, String text, boolean flg)
            throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter pw = writeFile(dir, flg);
        try {
            pw.println(text);
        } finally {
            pw.close();
        }
    }

    /**
     * 改行無しで書き込み
     * flg=true:追記
     * @param dir
     * @param text
     * @param flg=true:追記
     * @throws FileNotFoundException
     * @throws UnsupportedEncodingException
     */
    public static void writePlint(String dir, String text, boolean flg)
            throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter pw = writeFile(dir, flg);
        try {
            pw.print(text);
        } finally {
            pw.close();
        }
    }

    public static void writeString(String dir, String text) throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter pw = writeFile(dir, false);
        try {
            pw.println(text);
        } finally {
            pw.close();
        }
    }

    public static void writeStringArrayToCSV(String dir, String[] strArray)
            throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter pw = writeFile(dir, false);
        try {
            for (int i = 0; i < strArray.length; i++) {
                pw.print(strArray[i]);
                if (i != strArray.length - 1) {
                    pw.print(",");
                }
            }
            pw.println();
        } finally {
            pw.close();
        }
    }

    public static void writeStringArray2ToCSV(String dir, String[][] strArray2)
            throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter pw = writeFile(dir, false);
        try {
            for (int i = 0; i < strArray2.length; i++) {
                for (int j = 0; j < strArray2[i].length; j++) {
                    pw.print(strArray2[i][j]);
                    if (j != strArray2[i].length - 1) {
                        pw.print(",");
                    }
                }
                pw.println();
            }
        } finally {
            pw.close();
        }
    }

    public static void writeStringList(String dir, List<String> list)
            throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter pw = writeFile(dir, false);
        try {
            for (String text : list) {
                pw.println(text);
            }
        } finally {
            pw.close();
        }
    }
}
