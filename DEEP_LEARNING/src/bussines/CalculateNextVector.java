package bussines;

import java.util.Arrays;

import common.Calculation;
import common.ChoiceActivationFunction;
import common.EnumCollections.ActivationFunctions;
import common.EnumCollections.DerivativeCount;
import data.NeuronData;
import data.VectorData;

public class CalculateNextVector {

    /**
     * 入力ベクトル、ニューロン、活性化関数より出力ベクトルを返す
     * @param inputVector
     * @param neuron
     * @param function
     * @return
     */
    public static VectorData calcNextVector(VectorData inputVector, NeuronData neuron, ActivationFunctions function) {
        double[] uData = Calculation.calcAddVectorAndVector(
                Calculation.calcMaltiVectorAndMatrix(neuron.getWeight(), inputVector.getVectorData()),
                neuron.getBias());
        double[] outputData = ChoiceActivationFunction.getActivationFunction(function, uData, DerivativeCount.ZERO);
        VectorData nextVector = new VectorData(outputData);
        return nextVector;
    }

    /**
     * 入力ベクトル、ニューロン、活性化関数、ドロップアウト率より出力ベクトルを返す（ドロップアウトした場合の検証データ計算）
     * @param inputVector
     * @param neuron
     * @param function
     * @return
     */
    public static VectorData calcNextVectorVerify(VectorData inputVector, NeuronData neuron,
            ActivationFunctions function,
            double dropOutRate) {
        double[] uData = Calculation.calcAddVectorAndVector(
                Calculation.calcMaltiVectorAndMatrix(neuron.getWeight(), inputVector.getVectorData()),
                neuron.getBias());
        // ドロップアウトした分、出力の値を差し引く
        double[] dropRateVec = new double[uData.length];
        Arrays.fill(dropRateVec, 1.0 - dropOutRate);
        double[] outputData = Calculation.multiVectorAndVector(
                ChoiceActivationFunction.getActivationFunction(function, uData, DerivativeCount.ZERO), dropRateVec);
        VectorData nextVector = new VectorData(outputData);
        return nextVector;
    }

    /**
     * 入力ベクトル、ニューロン、活性化関数、ドロップアウト率より出力ベクトルを返す
     * @param inputVector
     * @param neuron
     * @param function
     * @param dropOutRate
     * @return
     */
    public static VectorData calcNextVector(VectorData inputVector, NeuronData neuron, ActivationFunctions function,
            double dropOutRate) {
        double[] uData = Calculation.calcAddVectorAndVector(
                Calculation.calcMaltiVectorAndMatrix(neuron.getWeight(), inputVector.getVectorData()),
                neuron.getBias());
        double[] outputData = ChoiceActivationFunction.getActivationFunction(function, uData, DerivativeCount.ZERO);
        VectorData nextVector = new VectorData(outputData);
        return nextVector;
    }

}
