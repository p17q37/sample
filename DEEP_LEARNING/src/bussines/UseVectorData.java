package bussines;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import data.VectorData;
import dataAccess.ReadInputDataCSV;

public class UseVectorData {

    private List<VectorData> vectorDataList = new ArrayList<>();

    public UseVectorData(double[] vector) throws IOException {
        vectorDataList.add(new VectorData(vector));
    }

    public UseVectorData(String dir) throws IOException {
        for (String[] vector : ReadInputDataCSV.readCSVList(dir)) {
            vectorDataList.add(new VectorData(vector));
        }
    }

    /**
     * リストに含まれた数の行のみデータを取得する
     * @param dir
     * @param numList
     * @throws IOException
     */
    public UseVectorData(String dir, ArrayList<Integer> numList) throws IOException {
        for (String[] vector : ReadInputDataCSV.readCSVListMini(dir, numList)) {
            vectorDataList.add(new VectorData(vector));
        }
    }

    public UseVectorData(String[] vector) {
        vectorDataList.add(new VectorData(vector));
    }

    public void addVector(VectorData data) {
        this.vectorDataList.add(data);
    }

    public VectorData getVectorData(int i) {
        return this.vectorDataList.get(i);
    }

    public List<VectorData> getVectorDataList() {
        return this.vectorDataList;
    }

}
