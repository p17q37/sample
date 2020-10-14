package common;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ReadFile {

    public static BufferedReader readFile(String dir) throws IOException {
        return Files.newBufferedReader(Paths.get(dir), Charset.forName("SJIS"));
    }

    public static BufferedReader readFile(String dir, String charset) throws IOException {
        return Files.newBufferedReader(Paths.get(dir), Charset.forName(charset));
    }

    public static String getReadFileText(String dir) throws IOException {
        BufferedReader br = readFile(dir);
        try {
            return br.readLine();
        } finally {
            br.close();
        }
    }

    public static String getReadFileText(String dir, String charset) throws IOException {
        BufferedReader br = readFile(dir, charset);
        try {
            return br.readLine();
        } finally {
            br.close();
        }
    }

    public static List<String> getReadFileTextList(String dir) throws IOException {
        BufferedReader br = readFile(dir);
        try {
            List<String> list = new ArrayList<>();
            String line = "";
            while ((line = br.readLine()) != null) {
                list.add(line);
            }
            return list;
        } finally {
            br.close();
        }
    }

    public static List<String> getReadFileTextList(String dir, String charset) throws IOException {
        BufferedReader br = readFile(dir, charset);
        try {
            List<String> list = new ArrayList<>();
            String line = "";
            while ((line = br.readLine()) != null) {
                list.add(line);
            }
            return list;
        } finally {
            br.close();
        }
    }

    public static List<String> getReadFileTextListMini(String dir, String charset, ArrayList<Integer> numList)
            throws IOException {

        BufferedReader br = null;
        List<String> list = new ArrayList<>();

        try {
            br = readFile(dir, charset);
            String line = "";
            int i = 0;
            while ((line = br.readLine()) != null) {
                // ミニバッチの候補でなければスキップ
                if (!numList.contains(i)) {
                    i++;
                    continue;
                }
                list.add(line);
                i++;
            }

            return list;
        } finally {
            br.close();
        }
    }

    /**
     * 読み込みファイルの行数を取得する。
     * @param dir
     * @return
     * @throws IOException
     */
    public static int getReadFileTextNum(String dir) throws IOException {
        BufferedReader br = readFile(dir);
        try {
            int num = 0;
            while ((br.readLine()) != null) {
                num++;
            }
            return num;
        } finally {
            br.close();
        }
    }

    /**
     * ディレクトリを再帰的に読む
     * @param folderPath
     */
    public static ArrayList<String> readFolder(File dir) {

        ArrayList<String> list = new ArrayList<>();

        File[] files = dir.listFiles();
        try {
            if (files == null) {
                return null;
            }
            for (File file : files) {
                if (!file.exists()) {
                    continue;
                } else if (file.isDirectory()) {
                    readFolder(file);
                } else if (file.isFile()) {
                    list.add(file.getName());
                }
            }
            return list;
        } finally {
            files.clone();
        }
    }
}
