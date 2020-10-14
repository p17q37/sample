package data;

public class NeuronDataLSTM {

    // ニューロン固有値
    private double[][] weightIn = null;
    private double[][] weightOut = null;
    private double[][] weightForget = null;
    private double[][] weightA = null;
    private double[][] recurrentIn = null;
    private double[][] recurrentOut = null;
    private double[][] recurrentForget = null;
    private double[][] recurrentA = null;
    private double[] peepholeIn = null;
    private double[] peepholeForget = null;
    private double[] peepholeOut = null;
    private double[] biasIn = null;
    private double[] biasOut = null;
    private double[] biasForget = null;
    private double[] biasA = null;

    public double[][] getWeightIn() {
        return weightIn;
    }

    public double getWeightIn(int i, int j) {
        return weightIn[i][j];
    }

    public double[][] getWeightOut() {
        return weightOut;
    }

    public double getWeightOut(int i, int j) {
        return weightOut[i][j];
    }

    public double[][] getWeightForget() {
        return weightForget;
    }

    public double getWeightForget(int i, int j) {
        return weightForget[i][j];
    }

    public double[][] getWeightA() {
        return weightA;
    }

    public double getWeightA(int i, int j) {
        return weightA[i][j];
    }

    public double[][] getRecurrentIn() {
        return recurrentIn;
    }

    public double getRecurrentIn(int i, int j) {
        return recurrentIn[i][j];
    }

    public double[][] getRecurrentOut() {
        return recurrentOut;
    }

    public double getRecurrentOut(int i, int j) {
        return recurrentOut[i][j];
    }

    public double[][] getRecurrentForget() {
        return recurrentForget;
    }

    public double getRecurrentForget(int i, int j) {
        return recurrentForget[i][j];
    }

    public double[][] getRecurrentA() {
        return recurrentA;
    }

    public double getRecurrentA(int i, int j) {
        return recurrentA[i][j];
    }

    public double[] getPeepholeIn() {
        return peepholeIn;
    }

    public double getPeepholeIn(int i) {
        return peepholeIn[i];
    }

    public double[] getPeepholeForget() {
        return peepholeForget;
    }

    public double getPeepholeForget(int i) {
        return peepholeForget[i];
    }

    public double[] getPeepholeOut() {
        return peepholeOut;
    }

    public double getPeepholeOut(int i) {
        return peepholeOut[i];
    }

    public double[] getBiasIn() {
        return biasIn;
    }

    public double getBiasIn(int i) {
        return biasIn[i];
    }

    public double[] getBiasOut() {
        return biasOut;
    }

    public double getBiasOut(int i) {
        return biasOut[i];
    }

    public double[] getBiasForget() {
        return biasForget;
    }

    public double getBiasForget(int i) {
        return biasForget[i];
    }

    public double[] getBiasA() {
        return biasA;
    }

    public double getBiasA(int i) {
        return biasA[i];
    }

    public void setWeightIn(double[][] weightIn) {
        this.weightIn = weightIn;
    }

    public void addWeightIn(int i, int j, double addWeightIn) {
        this.weightIn[i][j] += addWeightIn;
    }

    public void setWeightOut(double[][] weightOut) {
        this.weightOut = weightOut;
    }

    public void addWeightOut(int i, int j, double addWeightOut) {
        this.weightOut[i][j] += addWeightOut;
    }

    public void setWeightForget(double[][] weightForget) {
        this.weightForget = weightForget;
    }

    public void addWeightForget(int i, int j, double addWeightForget) {
        this.weightForget[i][j] += addWeightForget;
    }

    public void setWeightA(double[][] weightA) {
        this.weightA = weightA;
    }

    public void addWeightA(int i, int j, double addWeightA) {
        this.weightA[i][j] += addWeightA;
    }

    public void setRecurrentIn(double[][] recurrentIn) {
        this.recurrentIn = recurrentIn;
    }

    public void addRecurrentIn(int i, int j, double addRecurrentIn) {
        this.recurrentIn[i][j] += addRecurrentIn;
    }

    public void setRecurrentOut(double[][] recurrentOut) {
        this.recurrentOut = recurrentOut;
    }

    public void addRecurrentOut(int i, int j, double addRecurrentOut) {
        this.recurrentOut[i][j] += addRecurrentOut;
    }

    public void setRecurrentForget(double[][] recurrentForget) {
        this.recurrentForget = recurrentForget;
    }

    public void addRecurrentForget(int i, int j, double addRecurrentForget) {
        this.recurrentForget[i][j] += addRecurrentForget;
    }

    public void setRecurrentA(double[][] recurrentA) {
        this.recurrentA = recurrentA;
    }

    public void addRecurrentA(int i, int j, double addRecurrentA) {
        this.recurrentA[i][j] += addRecurrentA;
    }

    public void setPeepholeIn(double[] peepholeIn) {
        this.peepholeIn = peepholeIn;
    }

    public void addPeepholeIn(int i, double addPeepholeIn) {
        this.peepholeIn[i] += addPeepholeIn;
    }

    public void setPeepholeForget(double[] peepholeForget) {
        this.peepholeForget = peepholeForget;
    }

    public void addPeepholeForget(int i, double addPeepholeForget) {
        this.peepholeForget[i] += addPeepholeForget;
    }

    public void setPeepholeOut(double[] peepholeOut) {
        this.peepholeOut = peepholeOut;
    }

    public void addPeepholeOut(int i, double addPeepholeOut) {
        this.peepholeOut[i] += addPeepholeOut;
    }

    public void setBiasIn(double[] biasIn) {
        this.biasIn = biasIn;
    }

    public void addBiasIn(int i, double addBiasIn) {
        this.biasIn[i] += addBiasIn;
    }

    public void setBiasOut(double[] biasOut) {
        this.biasOut = biasOut;
    }

    public void addBiasOut(int i, double addBiasOut) {
        this.biasOut[i] += addBiasOut;
    }

    public void setBiasForget(double[] biasForget) {
        this.biasForget = biasForget;
    }

    public void addBiasForget(int i, double addBiasForget) {
        this.biasForget[i] += addBiasForget;
    }

    public void setBiasA(double[] biasA) {
        this.biasA = biasA;
    }

    public void addBiasA(int i, double addBiasA) {
        this.biasA[i] += addBiasA;
    }

    public void addLearningValue(NeuronDataLSTM learningValue) {

        // インプット重み更新
        for (int i = 0; i < learningValue.getWeightIn().length; i++) {
            for (int j = 0; j < learningValue.getWeightIn()[i].length; j++) {
                addWeightIn(i, j, learningValue.getWeightIn(i, j));
            }
        }

        // アウトプット重み更新
        for (int i = 0; i < learningValue.getWeightOut().length; i++) {
            for (int j = 0; j < learningValue.getWeightOut()[i].length; j++) {
                addWeightOut(i, j, learningValue.getWeightOut(i, j));
            }
        }

        // 忘却ゲート重み更新
        for (int i = 0; i < learningValue.getWeightForget().length; i++) {
            for (int j = 0; j < learningValue.getWeightForget()[i].length; j++) {
                addWeightForget(i, j, learningValue.getWeightForget(i, j));
            }
        }

        // Aゲート重み更新
        for (int i = 0; i < learningValue.getWeightA().length; i++) {
            for (int j = 0; j < learningValue.getWeightA()[i].length; j++) {
                addWeightA(i, j, learningValue.getWeightA(i, j));
            }
        }

        // インプットリカレント更新
        for (int i = 0; i < learningValue.getRecurrentIn().length; i++) {
            for (int j = 0; j < learningValue.getRecurrentIn()[i].length; j++) {
                addRecurrentIn(i, j, learningValue.getRecurrentIn(i, j));
            }
        }

        // アウトプットリカレント更新
        for (int i = 0; i < learningValue.getRecurrentOut().length; i++) {
            for (int j = 0; j < learningValue.getRecurrentOut()[i].length; j++) {
                addRecurrentOut(i, j, learningValue.getRecurrentOut(i, j));
            }
        }

        // 忘却リカレント更新
        for (int i = 0; i < learningValue.getRecurrentForget().length; i++) {
            for (int j = 0; j < learningValue.getRecurrentForget()[i].length; j++) {
                addRecurrentForget(i, j, learningValue.getRecurrentForget(i, j));
            }
        }

        // Aリカレント更新
        for (int i = 0; i < learningValue.getRecurrentA().length; i++) {
            for (int j = 0; j < learningValue.getRecurrentA()[i].length; j++) {
                addRecurrentA(i, j, learningValue.getRecurrentA(i, j));
            }
        }
    }
}
