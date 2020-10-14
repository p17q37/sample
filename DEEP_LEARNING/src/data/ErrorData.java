package data;

import java.util.Arrays;

import common.Calculation;

public class ErrorData {

    private double error = 0.0;

    public void setError(double[] outputData, double[] teacherData) {
        this.error += (Calculation.distanceVectorAndVector(outputData, teacherData)) / 2;
    }

    public void setErrorCrossEntropy(double[] outputData, double[] teacherData) {
        double[] oneVector = new double[outputData.length];
        Arrays.fill(oneVector, 1.0);
        this.error -= Calculation.sumDoubleArray(
                Calculation.calcAddVectorAndVector(
                        Calculation.multiVectorAndVector(teacherData, Calculation.logVector(outputData)),
                        Calculation.multiVectorAndVector(Calculation.calcMinusVectorAndVector(oneVector, teacherData),
                                Calculation.logVector(Calculation.calcMinusVectorAndVector(oneVector, outputData)))));
    }

    public double getError() {
        return this.error;
    }
}
