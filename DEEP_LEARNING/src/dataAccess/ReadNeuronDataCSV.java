package dataAccess;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import common.ReadFile;

public class ReadNeuronDataCSV {

    private static String SJIS = "SJIS";

    public static List<String> readWeight(String dir) throws IOException {
        return ReadFile.getReadFileTextList(dir, SJIS);
    }

    public static String readBias(String dir) throws IOException {
        return ReadFile.getReadFileText(dir, SJIS);
    }

    public static List<String> readNeuronFolder(String dir) {
        File file = new File(dir);
        List<String> list = new ArrayList<>();
        for (String fileName : ReadFile.readFolder(file)) {
            list.add(dir + "\\" + fileName);
        }
        return list;
    }
}
