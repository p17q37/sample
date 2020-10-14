package dataAccess;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import common.ReadFile;

public class ReadInputDataCSV {

    private static String SJIS = "SJIS";

    public static String[] readCSV(String dir) throws IOException {
        return ReadFile.getReadFileText(dir, SJIS).split(",");
    }

    public static List<String[]> readCSVList(String dir) throws IOException {

        List<String[]> list = new ArrayList<>();
        List<String> readList = ReadFile.getReadFileTextList(dir, SJIS);

        for (String text : readList) {
            list.add(text.split(","));
        }
        return list;
    }

    public static List<String[]> readCSVListMini(String dir, ArrayList<Integer> numList) throws IOException {

        List<String[]> list = new ArrayList<>();
        List<String> readList = ReadFile.getReadFileTextListMini(dir, SJIS, numList);

        for (String text : readList) {
            list.add(text.split(","));
        }
        return list;
    }
}
