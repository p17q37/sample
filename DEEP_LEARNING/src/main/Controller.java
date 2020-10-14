package main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import bussines.BackPropagation;
import bussines.UseNeuronData;
import bussines.UseTeacherData;
import bussines.UseVectorData;
import common.Calculation;
import common.EnumCollections.ActivationFunctions;
import common.ReadFile;
import data.NeuronData;
import data.TeacherData;
import data.VectorData;
import dataAccess.WriteNeuronDataCSV;

public class Controller {

    // フォルダ設定
    private static String FOLDER = "";

    // ファイル設定
    private final static String INPUT_FILE_DIR = FOLDER + "\\inputData\\InputData.csv";
    private final static String NEURON_WEIGHT_FILE_DIR = FOLDER + "\\neuronData\\weight";
    private final static String NEURON_BIAS_FILE_DIR = FOLDER + "\\neuronData\\bias";
    private final static String TEACHER_FILE_DIR = FOLDER + "\\teacherData\\TeacherData.csv";
    // 学習率
    private final static double LEARNING_RATE = 0.015;
    // 正規化パラメータ
    private final static double NORMALIZATION_PARAMETER = 0.0;
    // ドロップアウト率
    private final static double DROP_OUT_RATE = 0.2;
    // エポック数
    private final static int EPOCH_NUM = 1000;
    // ミニバッチ数
    private final static int MINI_BAT_NUM = 32;
    // 重み上限
    private final static double WEIGHT_LIMIT = 100000.0;
    // 書き込み時の桁数
    private final static int DIGIT = 10;

    public static void main(String[] args) throws IOException {

        // ファイルのフォルダ指定
        FOLDER = args[0];

        for (int i = 0; i < EPOCH_NUM; i++) {
            double error = learningMini(i + 1);
            System.out.println(i + 1 + "エポック目学習完了！：E=" + error);
        }

    }

    private static double learning(int epockNum) throws IOException {

        double error = 0.0;

        // インプットデータを読み込む
        UseVectorData useVectorData = new UseVectorData(INPUT_FILE_DIR);

        // ニューロンデータを読み込む
        UseNeuronData useNeuronData = new UseNeuronData(NEURON_WEIGHT_FILE_DIR, NEURON_BIAS_FILE_DIR, 0);
        UseNeuronData useNeuronDataNew = new UseNeuronData(NEURON_WEIGHT_FILE_DIR, NEURON_BIAS_FILE_DIR, 0);

        // 教師データを読み込む
        UseTeacherData useTeacherData = new UseTeacherData(TEACHER_FILE_DIR);

        if (!(useVectorData.getVectorDataList().size() == useTeacherData.getTeacherDataList().size())) {
            System.out.println("ERROR:Controller;learning;01");
            System.exit(0);
        }

        int inputDataSize = useVectorData.getVectorDataList().size();

        // バックプロパゲーション
        for (int i = 0; i < inputDataSize; i++) {

            VectorData vector = useVectorData.getVectorData(i);
            TeacherData teacher = useTeacherData.getTeacherData(i);

            BackPropagation backPropagation = new BackPropagation(vector, useNeuronData.getNeuronDataList(),
                    ActivationFunctions.SIGMOID, ActivationFunctions.SIGMOID, teacher, LEARNING_RATE / inputDataSize,
                    DROP_OUT_RATE);
            List<NeuronData> updateValueList = backPropagation.getLearningValueList();
            for (int j = 0; j < updateValueList.size(); j++) {
                error += useNeuronDataNew.getNeuronData(j).addLearningValue(updateValueList.get(j),
                        NORMALIZATION_PARAMETER * LEARNING_RATE / inputDataSize);
            }
            error += backPropagation.getError();
        }

        // 重み上限を設定
        useNeuronDataNew.upperWeightLimit(WEIGHT_LIMIT);

        // 書き込み
        for (int i = 0; i < useNeuronDataNew.getNeuronDataList().size(); i++) {
            WriteNeuronDataCSV.writeNeuronData(useNeuronDataNew.getNeuronData(i),
                    useNeuronDataNew.getNeuronWeightDirList().get(i),
                    useNeuronDataNew.getNeuronBiasDirList().get(i), DIGIT);
        }

        return error;
    }

    synchronized private static double learningMini(int epockNum) throws IOException {

        double error = 0.0;
        int inputNum = ReadFile.getReadFileTextNum(INPUT_FILE_DIR);
        // とりあえず、インプットデータ数から割合でループ件数を取得する
        int cnt = inputNum / MINI_BAT_NUM;

        // 1エポック分ループ
        for (int I = 0; I < cnt; I++) {
            double miniError = 0.0;
            // ミニバッチ候補の作成（インプットデータの行数を取得する）
            ArrayList<Integer> numList = Calculation.randNumFlyAway(inputNum, MINI_BAT_NUM);

            // インプットデータを読み込む（１ミニバッチ）
            UseVectorData useVectorData = new UseVectorData(INPUT_FILE_DIR, numList);

            // ニューロンデータを読み込む
            UseNeuronData useNeuronData = new UseNeuronData(NEURON_WEIGHT_FILE_DIR, NEURON_BIAS_FILE_DIR, 0);
            UseNeuronData useNeuronDataNew = new UseNeuronData(NEURON_WEIGHT_FILE_DIR, NEURON_BIAS_FILE_DIR, 0);

            // 教師データを読み込む（１ミニバッチ）
            UseTeacherData useTeacherData = new UseTeacherData(TEACHER_FILE_DIR, numList);

            if (!(useVectorData.getVectorDataList().size() == useTeacherData.getTeacherDataList().size())) {
                System.out.println("ERROR:Controller;learning;01");
                System.exit(0);
            }

            // バックプロパゲーション
            for (int i = 0; i < useVectorData.getVectorDataList().size(); i++) {

                VectorData vector = useVectorData.getVectorData(i);
                TeacherData teacher = useTeacherData.getTeacherData(i);

                BackPropagation backPropagation = new BackPropagation(vector, useNeuronData.getNeuronDataList(),
                        ActivationFunctions.SIGMOID, ActivationFunctions.IDENTITY, teacher,
                        LEARNING_RATE / MINI_BAT_NUM,
                        DROP_OUT_RATE);
                List<NeuronData> updateValueList = backPropagation.getLearningValueList();
                for (int j = 0; j < updateValueList.size(); j++) {
                    //                    error += useNeuronDataNew.getNeuronData(j).addLearningValue(updateValueList.get(j),
                    //                            NORMALIZATION_PARAMETER * LEARNING_RATE / MINI_BAT_NUM);
                    miniError += useNeuronDataNew.getNeuronData(j).addLearningValue(updateValueList.get(j),
                            NORMALIZATION_PARAMETER * LEARNING_RATE / MINI_BAT_NUM);
                }
                //                error += backPropagation.getError();
                miniError += backPropagation.getError();
                if (Double.isNaN(miniError) || Double.isInfinite(miniError)) {
                    throw new IOException();
                }
            }

            // 重み上限を設定
            useNeuronDataNew.upperWeightLimit(WEIGHT_LIMIT);

            // 書き込み（１ミニバッチ分）
            for (int i = 0; i < useNeuronDataNew.getNeuronDataList().size(); i++) {
                WriteNeuronDataCSV.writeNeuronData(useNeuronDataNew.getNeuronData(i),
                        useNeuronDataNew.getNeuronWeightDirList().get(i),
                        useNeuronDataNew.getNeuronBiasDirList().get(i), DIGIT);
            }
            System.out.println(epockNum + " エポック目：" + (I + 1) + "/" + cnt + " 終了！ E=" + miniError);
        }
        return error;
    }

}
