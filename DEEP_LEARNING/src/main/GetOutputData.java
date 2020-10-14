package main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import bussines.CalculateNextVector;
import bussines.UseNeuronData;
import bussines.UseVectorData;
import common.EnumCollections.ActivationFunctions;
import common.FormatConvertUtil;
import data.VectorData;

public class GetOutputData {

    // フォルダ
    private static String FOLDER = "";

    // 設定
    private final static String INPUT_FILE_DIR = FOLDER + "\\inputData\\InputData.csv";
    private final static String NEURON_WEIGHT_FILE_DIR = FOLDER + "\\neuronData\\weight";
    private final static String NEURON_BIAS_FILE_DIR = FOLDER + "\\neuronData\\bias";
    private final static ActivationFunctions functionMiddle = ActivationFunctions.SIGMOID;
    private final static ActivationFunctions functionLast = ActivationFunctions.IDENTITY;
    private final static double DROP_OUT_RATE = 0.2;

    public static void main(String[] args) throws IOException {

        FOLDER = args[0];

        // インプットデータを読み込む
        UseVectorData useInputData = new UseVectorData(INPUT_FILE_DIR);

        // ニューロンデータを読み込む
        UseNeuronData useNeuronData = new UseNeuronData(NEURON_WEIGHT_FILE_DIR, NEURON_BIAS_FILE_DIR, 0);

        for (int I = 0; I < useInputData.getVectorDataList().size(); I++) {
            List<VectorData> vectorList = new ArrayList<>();
            vectorList.add(useInputData.getVectorDataList().get(I));

            // 中間層・出力層の入出力値を計算する
            for (int i = 0; i < useNeuronData.getNeuronDataList().size(); i++) {
                if (i < useNeuronData.getNeuronDataList().size() - 1) {
                    vectorList
                            .add(CalculateNextVector.calcNextVectorVerify(vectorList.get(i),
                                    useNeuronData.getNeuronDataList().get(i), functionMiddle, DROP_OUT_RATE));
                } else {
                    vectorList
                            .add(CalculateNextVector.calcNextVector(vectorList.get(i),
                                    useNeuronData.getNeuronDataList().get(i), functionLast));
                }
            }

            String[] textArray = FormatConvertUtil
                    .doubleArrayToStringArray(vectorList.get(vectorList.size() - 1).getVectorData());
            for (int i = 0; i < textArray.length; i++) {
                if (i < textArray.length - 1) {
                    System.out.print(textArray[i] + ",");
                } else {
                    System.out.println(textArray[i]);
                }
            }
        }
    }

}
