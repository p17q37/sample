package data;

import common.Calculation;
import common.ChoiceActivationFunction;
import common.EnumCollections.DerivativeCount;

public class DeltaDataLSTM {

    private double[] deltaOut = null;
    private double[] deltaTilde = null;
    private double[] deltaCell = null;
    private double[] deltaForget = null;
    private double[] deltaInput = null;
    private double[] deltaA = null;
    private double[] epsilon = null;

    public DeltaDataLSTM(DeltaDataLSTM nextDeltaDataLSTM, DataLSTM dataLSTM, DataLSTM nextDataLSTM, double[] delta,
            NeuronData neuron, NeuronDataLSTM neuronLSTM) {
        this.epsilon = Calculation.calcAddVectorAndVector(
                Calculation.calcMaltiVectorAndMatrixT(delta, neuron.getWeight()),
                Calculation.calcMaltiVectorAndMatrixT(nextDeltaDataLSTM.getDeltaA(), neuronLSTM.getWeightA()));
        this.deltaOut = Calculation.multiVectorAndVector(Calculation.multiVectorAndVector(
                ChoiceActivationFunction.getActivationFunction(dataLSTM.getFunction1(), dataLSTM.getuOutputData(),
                        DerivativeCount.ONE),
                ChoiceActivationFunction.getActivationFunction(dataLSTM.getFunction2(), dataLSTM.getCellData(),
                        DerivativeCount.ZERO)),
                this.epsilon);
        this.deltaTilde = Calculation.multiVectorAndVector(
                Calculation.multiVectorAndVector(dataLSTM.getOutputData(), ChoiceActivationFunction
                        .getActivationFunction(dataLSTM.getFunction2(), dataLSTM.getCellData(), DerivativeCount.ONE)),
                this.epsilon);
        this.deltaCell = Calculation
                .calcAddVectorAndVector(
                        Calculation
                                .calcAddVectorAndVector(
                                        Calculation.calcAddVectorAndVector(
                                                Calculation.calcAddVectorAndVector(this.deltaTilde,
                                                        Calculation.multiVectorAndVector(nextDataLSTM.getForgetData(),
                                                                nextDeltaDataLSTM.getDeltaCell())),
                                                Calculation.multiVectorAndVector(neuronLSTM.getPeepholeForget(),
                                                        nextDeltaDataLSTM.getDeltaInput())),
                                        Calculation.multiVectorAndVector(neuronLSTM.getPeepholeIn(),
                                                nextDeltaDataLSTM.getDeltaForget())),
                        Calculation.multiVectorAndVector(neuronLSTM.getPeepholeOut(), this.deltaOut));
        this.deltaA = Calculation.multiVectorAndVector(Calculation.multiVectorAndVector(dataLSTM.getInputData(),
                ChoiceActivationFunction.getActivationFunction(dataLSTM.getFunction2(),
                        dataLSTM.getuAData(), DerivativeCount.ONE)),
                this.deltaCell);
        this.deltaForget = Calculation.multiVectorAndVector(Calculation.multiVectorAndVector(
                ChoiceActivationFunction.getActivationFunction(
                        dataLSTM.getFunction1(), dataLSTM.getuForgetData(), DerivativeCount.ONE),
                dataLSTM.getCellBeforeData()), this.deltaCell);
        this.deltaInput = Calculation.multiVectorAndVector(Calculation.multiVectorAndVector(
                ChoiceActivationFunction.getActivationFunction(
                        dataLSTM.getFunction1(), dataLSTM.getuInputData(), DerivativeCount.ONE),
                dataLSTM.getaData()), this.deltaCell);
    }

    public double[] getDeltaOut() {
        return deltaOut;
    }

    public double[] getDeltaTilde() {
        return deltaTilde;
    }

    public double[] getDeltaCell() {
        return deltaCell;
    }

    public double[] getDeltaForget() {
        return deltaForget;
    }

    public double[] getDeltaInput() {
        return deltaInput;
    }

    public double[] getDeltaA() {
        return deltaA;
    }

    public double[] getEpsilon() {
        return epsilon;
    }

}
