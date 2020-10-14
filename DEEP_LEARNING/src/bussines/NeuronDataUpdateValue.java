package bussines;

import java.util.Arrays;

import common.Calculation;
import common.ChoiceActivationFunction;
import common.EnumCollections.ActivationFunctions;
import common.EnumCollections.DerivativeCount;
import data.ErrorData;
import data.NeuronData;
import data.TeacherData;
import data.VectorData;

class NeuronDataUpdateValue {

    private NeuronData neuronData = null;
    private VectorData inputVectorData = null;
    private double[] deltaEdeltaY = null;
    private double[] delta = null;
    private ErrorData errorData = new ErrorData();

    // 中間層のニューロンデータ
    NeuronDataUpdateValue(VectorData inputVector, NeuronData neuron, ActivationFunctions function,
            double[] deltaEdeltaY) {

        double[] differntialActivation = null;
        double[] uData = null;

        this.neuronData = neuron;
        this.inputVectorData = inputVector;
        // （入力ベクトル）×（重み）＋（バイアス）の計算
        uData = Calculation.calcAddVectorAndVector(
                Calculation.calcMaltiVectorAndMatrix(neuron.getWeight(), inputVector.getVectorData()),
                neuron.getBias());
        differntialActivation = ChoiceActivationFunction.getActivationFunction(function, uData, DerivativeCount.ONE);
        this.deltaEdeltaY = deltaEdeltaY;
        this.delta = Calculation.multiVectorAndVector(deltaEdeltaY, differntialActivation);
    }

    // 出力層のニューロンデータ
    NeuronDataUpdateValue(VectorData inputVector, NeuronData neuron, VectorData outputVector,
            ActivationFunctions function,
            TeacherData teacher) {

        double[] differntialActivation = null;
        double[] uData = null;

        this.neuronData = neuron;
        this.inputVectorData = inputVector;

        double[] outputData = outputVector.getVectorData();

        // （入力ベクトル）×（重み）＋（バイアス）の計算
        uData = Calculation.calcAddVectorAndVector(
                Calculation.calcMaltiVectorAndMatrix(neuron.getWeight(), inputVector.getVectorData()),
                neuron.getBias());

        // 出力層の活性化関数がシグモイドなら、コスト関数にクロスエントロピーを用いる
        if (function.equals(ActivationFunctions.SIGMOID)) {
            this.delta = Calculation.calcMinusVectorAndVector(outputData, teacher.getTeacherData());
            // すべて「1」のdouble型配列を作成
            double[] oneVector = new double[outputData.length];
            Arrays.fill(oneVector, 1.0);
            this.deltaEdeltaY = Calculation.divVectorAndVector(
                    Calculation.calcMinusVectorAndVector(outputData, teacher.getTeacherData()),
                    Calculation.multiVectorAndVector(outputData,
                            Calculation.calcMinusVectorAndVector(oneVector, outputData)));
            this.errorData.setErrorCrossEntropy(outputData, teacher.getTeacherData());
        } else {
            differntialActivation = ChoiceActivationFunction.getActivationFunction(function, uData,
                    DerivativeCount.ONE);
            this.delta = Calculation.multiVectorAndVector(
                    Calculation.calcMinusVectorAndVector(outputData, teacher.getTeacherData()), differntialActivation);
            this.deltaEdeltaY = Calculation.calcMinusVectorAndVector(outputData, teacher.getTeacherData());
            this.errorData.setError(outputData, teacher.getTeacherData());
        }
    }

    // 今回学習分の伝播する更新用値（誤差を入力値で偏微分）
    double[] returnNextDEDY() {
        double[] nextDEDY = new double[this.neuronData.getWeight()[0].length];
        for (int i = 0; i < nextDEDY.length; i++) {
            for (int j = 0; j < neuronData.getWeight().length; j++) {
                nextDEDY[i] += this.deltaEdeltaY[j] * neuronData.getWeight()[j][i];
            }
        }
        return nextDEDY;
    }

    // ニューロンデータの学習値取得
    NeuronData getLearninValue(double learningRate) {
        NeuronData learningValue = new NeuronData(delta.length, inputVectorData.getVectorData().length);
        for (int i = 0; i < delta.length; i++) {
            for (int j = 0; j < inputVectorData.getVectorData().length; j++) {
                // ニューロン重み更新値
                learningValue.setWeight(i, j,
                        (-1.0) * learningRate * this.delta[i] * this.inputVectorData.getVectorData()[j]);
            }
            // ニューロンバイアス更新値
            learningValue.setBias(i, (-1.0) * learningRate * this.delta[i]);
        }
        return learningValue;
    }

    ErrorData getErrorData() {
        return errorData;
    }
}
