package data;

import common.Calculation;
import common.ChoiceActivationFunction;
import common.EnumCollections.ActivationFunctions;
import common.EnumCollections.DerivativeCount;

public class DataLSTM {

    private double[] xData = null;
    private double[] rnnBeroreData = null;
    private double[] rnnData = null;
    private double[] uInputData = null;
    private double[] uForgetData = null;
    private double[] uAData = null;
    private double[] outputData = null;
    private double[] inputData = null;
    private double[] forgetData = null;
    private double[] aData = null;
    private double[] uOutputData = null;
    private double[] cellBeforeData = null;
    private double[] cellData = null;
    private ActivationFunctions function1 = null;
    private ActivationFunctions function2 = null;

    public DataLSTM(VectorData inputData, double[] rnnBeroreData, double[] cellBeforeData, NeuronDataLSTM neuronData) {
        this.xData = inputData.getVectorData();
        this.rnnBeroreData = rnnBeroreData;
        this.cellBeforeData = cellBeforeData;
        this.uInputData = Calculation.calcAddVectorAndVector(Calculation.calcAddVectorAndVector(
                Calculation.calcMaltiVectorAndMatrix(neuronData.getWeightIn(), xData),
                Calculation.calcMaltiVectorAndMatrix(neuronData.getRecurrentIn(), this.rnnBeroreData)),
                Calculation.multiVectorAndVector(this.cellBeforeData, neuronData.getPeepholeIn()));
        this.uForgetData = Calculation.calcAddVectorAndVector(Calculation.calcAddVectorAndVector(
                Calculation.calcMaltiVectorAndMatrix(neuronData.getWeightForget(), xData),
                Calculation.calcMaltiVectorAndMatrix(neuronData.getRecurrentForget(), this.rnnBeroreData)),
                Calculation.multiVectorAndVector(this.cellBeforeData, neuronData.getPeepholeForget()));
        this.uOutputData = Calculation.calcAddVectorAndVector(Calculation.calcAddVectorAndVector(
                Calculation.calcMaltiVectorAndMatrix(neuronData.getWeightOut(), xData),
                Calculation.calcMaltiVectorAndMatrix(neuronData.getRecurrentOut(), this.rnnBeroreData)),
                Calculation.multiVectorAndVector(this.cellBeforeData, neuronData.getPeepholeOut()));
        this.uAData = Calculation.calcAddVectorAndVector(
                Calculation.calcMaltiVectorAndMatrix(neuronData.getWeightA(), xData),
                Calculation.calcMaltiVectorAndMatrix(neuronData.getRecurrentA(), this.rnnBeroreData));
    }

    public DataLSTM(VectorData inputData, double[] rnnBeroreData, double[] cellBeforeData, NeuronDataLSTM neuronData,
            ActivationFunctions function1, ActivationFunctions function2) {
        this.xData = inputData.getVectorData();
        this.rnnBeroreData = rnnBeroreData;
        this.cellBeforeData = cellBeforeData;
        this.function1 = function1;
        this.function2 = function2;
        this.uInputData = Calculation.calcAddVectorAndVector(Calculation.calcAddVectorAndVector(
                Calculation.calcMaltiVectorAndMatrix(neuronData.getWeightIn(), xData),
                Calculation.calcMaltiVectorAndMatrix(neuronData.getRecurrentIn(), this.rnnBeroreData)),
                Calculation.multiVectorAndVector(this.cellBeforeData, neuronData.getPeepholeIn()));
        this.uForgetData = Calculation.calcAddVectorAndVector(Calculation.calcAddVectorAndVector(
                Calculation.calcMaltiVectorAndMatrix(neuronData.getWeightForget(), xData),
                Calculation.calcMaltiVectorAndMatrix(neuronData.getRecurrentForget(), this.rnnBeroreData)),
                Calculation.multiVectorAndVector(this.cellBeforeData, neuronData.getPeepholeForget()));
        this.uOutputData = Calculation.calcAddVectorAndVector(Calculation.calcAddVectorAndVector(
                Calculation.calcMaltiVectorAndMatrix(neuronData.getWeightOut(), xData),
                Calculation.calcMaltiVectorAndMatrix(neuronData.getRecurrentOut(), this.rnnBeroreData)),
                Calculation.multiVectorAndVector(this.cellBeforeData, neuronData.getPeepholeOut()));
        this.uAData = Calculation.calcAddVectorAndVector(
                Calculation.calcMaltiVectorAndMatrix(neuronData.getWeightA(), xData),
                Calculation.calcMaltiVectorAndMatrix(neuronData.getRecurrentA(), this.rnnBeroreData));
        this.inputData = ChoiceActivationFunction.getActivationFunction(this.function1, uInputData,
                DerivativeCount.ZERO);
        this.forgetData = ChoiceActivationFunction.getActivationFunction(this.function1, uForgetData,
                DerivativeCount.ZERO);
        this.outputData = ChoiceActivationFunction.getActivationFunction(this.function1, uOutputData,
                DerivativeCount.ZERO);
        this.aData = ChoiceActivationFunction.getActivationFunction(this.function2, uAData, DerivativeCount.ZERO);
        this.cellData = Calculation.calcAddVectorAndVector(Calculation.multiVectorAndVector(forgetData, cellBeforeData),
                Calculation.multiVectorAndVector(this.inputData, aData));
        this.rnnData = Calculation.multiVectorAndVector(outputData,
                ChoiceActivationFunction.getActivationFunction(this.function2, cellData, DerivativeCount.ZERO));
    }

    public double[] getxData() {
        return xData;
    }

    public double[] getRnnBeroreData() {
        return rnnBeroreData;
    }

    public double[] getRnnData() {
        return rnnData;
    }

    public double[] getuInputData() {
        return uInputData;
    }

    public double[] getuForgetData() {
        return uForgetData;
    }

    public double[] getuAData() {
        return uAData;
    }

    public double[] getOutputData() {
        return outputData;
    }

    public double[] getInputData() {
        return inputData;
    }

    public double[] getForgetData() {
        return forgetData;
    }

    public double[] getaData() {
        return aData;
    }

    public double[] getuOutputData() {
        return uOutputData;
    }

    public double[] getCellBeforeData() {
        return cellBeforeData;
    }

    public double[] getCellData() {
        return cellData;
    }

    public ActivationFunctions getFunction1() {
        return function1;
    }

    public ActivationFunctions getFunction2() {
        return function2;
    }

}
