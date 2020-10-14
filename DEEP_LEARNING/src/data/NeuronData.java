package data;

public class NeuronData {

    // ニューロン固有値
    private double[][] weight = null;
    private double[] bias = null;

    public NeuronData(int row, int column) {
        this.weight = new double[row][column];
        this.bias = new double[row];
    }

    public NeuronData(NeuronData neuron) {
        this.weight = neuron.getWeight().clone();
        this.bias = neuron.getBias().clone();
    }

    public double[][] getWeight() {
        return this.weight;
    }

    public double getWeight(int i, int j) {
        return this.weight[i][j];
    }

    public void setWeight(double[][] weight) {
        this.weight = weight.clone();
    }

    public void setWeight(String[][] weight) {
        for (int i = 0; i < weight.length; i++) {
            for (int j = 0; j < weight[0].length; j++) {
                this.weight[i][j] = Double.parseDouble(weight[i][j]);
            }
        }
    }

    public void setWeight(int i, String[] rowData) {
        for (int j = 0; j < rowData.length; j++) {
            this.weight[i][j] = Double.parseDouble(rowData[j]);
        }
    }

    public void setWeight(int i, int j, double weight) {
        this.weight[i][j] = weight;
    }

    public void addWeight(int i, int j, double addWeight) {
        this.weight[i][j] += addWeight;
    }

    public double[] getBias() {
        return this.bias;
    }

    public double getBias(int i) {
        return this.bias[i];
    }

    public void setBias(double[] bias) {
        this.bias = bias.clone();
    }

    public void setBias(String[] bias) {
        for (int i = 0; i < bias.length; i++) {
            this.bias[i] = Double.parseDouble(bias[i]);
        }
    }

    public void setBias(int i, double bias) {
        this.bias[i] = bias;
    }

    public void addBias(int i, double addBias) {
        this.bias[i] += addBias;
    }

    public double addLearningValue(NeuronData learningValue, double normalizationParam) {
        double normalizationError = 0.0;
        if (!((learningValue.getWeight().length == this.weight.length)
                && (learningValue.getWeight()[0].length == this.weight[0].length)
                && (learningValue.getBias().length == this.bias.length))) {
            System.out.println("ERROR:NeuronData;addLearningValue");
            System.exit(0);
        }

        for (int i = 0; i < learningValue.getWeight().length; i++) {
            for (int j = 0; j < learningValue.getWeight()[i].length; j++) {
                // 重み更新値-正規化（重み減衰）
                addWeight(i, j, learningValue.getWeight(i, j) - normalizationParam * getWeight(i, j));
                normalizationError += normalizationParam * Math.pow(getWeight(i, j), 2) / 2;
            }
            addBias(i, learningValue.getBias(i));
        }
        return normalizationError;
    }
}
