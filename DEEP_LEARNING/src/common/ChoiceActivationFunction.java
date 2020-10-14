package common;

import java.util.Arrays;

import common.EnumCollections.ActivationFunctions;
import common.EnumCollections.DerivativeCount;

public class ChoiceActivationFunction {

    public static double[] getActivationFunction(ActivationFunctions function, double[] vector,
            DerivativeCount dCount) {

        double[] result = null;
        switch (function) {
        case IDENTITY:
            result = Calculation.identity(vector, dCount);
            break;
        case SIGMOID:
            result = Calculation.sigmoidVector(vector, dCount);
            break;
        case ReLU:
            result = Calculation.ReLU(vector, dCount);
            break;
        case SOFTMAX:
            result = Calculation.softmax(vector, dCount);
            break;
        case TANH:
            result = Calculation.tanh(vector, dCount);
            break;
        default:
            result = new double[vector.length];
            Arrays.fill(result, 0.0);
            break;
        }
        return result;
    }

}
