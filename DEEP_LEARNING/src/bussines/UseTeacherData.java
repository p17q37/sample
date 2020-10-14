package bussines;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import data.TeacherData;
import dataAccess.ReadInputDataCSV;

public class UseTeacherData {

    private List<TeacherData> teacherDataList = new ArrayList<>();

    public UseTeacherData(String dir) throws IOException {

        for (String[] vector : ReadInputDataCSV.readCSVList(dir)) {
            this.teacherDataList.add(new TeacherData(vector));
        }
    }

    public UseTeacherData(String dir, ArrayList<Integer> numList) throws IOException {

        for (String[] vector : ReadInputDataCSV.readCSVListMini(dir, numList)) {
            this.teacherDataList.add(new TeacherData(vector));
        }
    }

    public TeacherData getTeacherData(int i) {
        return this.teacherDataList.get(i);
    }

    public List<TeacherData> getTeacherDataList() {
        return this.teacherDataList;
    }

}
