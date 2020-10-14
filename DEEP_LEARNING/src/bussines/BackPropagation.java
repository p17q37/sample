package bussines;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import common.EnumCollections.ActivationFunctions;
import data.NeuronData;
import data.TeacherData;
import data.VectorData;

public class BackPropagation {

    private List<NeuronData> learningValueList = new ArrayList<>();
    private double error = 0.0;

    // バックプロパゲーション
    public BackPropagation(VectorData inputVector, List<NeuronData> neuronList,
            ActivationFunctions functionMiddle, ActivationFunctions functionLast, TeacherData teacher,
            double learningRate) {

        List<VectorData> vectorList = new ArrayList<>();
        vectorList.add(inputVector);

        // 中間層・出力層の入出力値を計算する
        for (int i = 0; i < neuronList.size(); i++) {
            if (i < neuronList.size() - 1) {
                vectorList
                        .add(CalculateNextVector.calcNextVector(vectorList.get(i), neuronList.get(i), functionMiddle));
            } else {
                vectorList
                        .add(CalculateNextVector.calcNextVector(vectorList.get(i), neuronList.get(i), functionLast));
            }
        }

        // バックプロパゲーション
        double[] nextDEDY = null;
        for (int i = 0; i < vectorList.size() - 1; i++) {

            NeuronDataUpdateValue updateValue = null;

            if (i == 0) {
                updateValue = new NeuronDataUpdateValue(vectorList.get(vectorList.size() - 2 - i),
                        neuronList.get(neuronList.size() - 1 - i), vectorList.get(vectorList.size() - 1 - i),
                        functionLast, teacher);
                nextDEDY = updateValue.returnNextDEDY();
            } else {
                updateValue = new NeuronDataUpdateValue(vectorList.get(vectorList.size() - 2 - i),
                        neuronList.get(neuronList.size() - 1 - i),
                        functionMiddle, nextDEDY);
                nextDEDY = updateValue.returnNextDEDY();
            }

            this.learningValueList.add(updateValue.getLearninValue(learningRate));
            this.error += updateValue.getErrorData().getError();
        }

        // ニューロンデータのリストを逆順にし入力順と同様にする
        Collections.reverse(learningValueList);
    }

    // バックプロパゲーション（ドロップアウトあり）
    public BackPropagation(VectorData inputVector, List<NeuronData> neuronList,
            ActivationFunctions functionMiddle, ActivationFunctions functionLast, TeacherData teacher,
            double learningRate, double dropOutRate) {

        List<VectorData> vectorList = new ArrayList<>();
        vectorList.add(inputVector);

        // 中間層・出力層の入出力値を計算する
        for (int i = 0; i < neuronList.size(); i++) {
            if (i < neuronList.size() - 1) {
                vectorList
                        .add(CalculateNextVector.calcNextVector(vectorList.get(i), neuronList.get(i), functionMiddle,
                                dropOutRate));
            } else {
                vectorList
                        .add(CalculateNextVector.calcNextVector(vectorList.get(i), neuronList.get(i), functionLast));
            }
        }

        // バックプロパゲーション
        double[] nextDEDY = null;
        for (int i = 0; i < vectorList.size() - 1; i++) {

            NeuronDataUpdateValue updateValue = null;

            if (i == 0) {
                updateValue = new NeuronDataUpdateValue(vectorList.get(vectorList.size() - 2 - i),
                        neuronList.get(neuronList.size() - 1 - i), vectorList.get(vectorList.size() - 1 - i),
                        functionLast, teacher);
                nextDEDY = updateValue.returnNextDEDY();
            } else {
                updateValue = new NeuronDataUpdateValue(vectorList.get(vectorList.size() - 2 - i),
                        neuronList.get(neuronList.size() - 1 - i),
                        functionMiddle, nextDEDY);
                nextDEDY = updateValue.returnNextDEDY();
            }

            this.learningValueList.add(updateValue.getLearninValue(learningRate));
            this.error += updateValue.getErrorData().getError();
        }

        // ニューロンデータのリストを逆順にし入力順と同様にする
        Collections.reverse(learningValueList);
    }

    public List<NeuronData> getLearningValueList() {
        return learningValueList;
    }

    public double getError() {
        return error;
    }

}
