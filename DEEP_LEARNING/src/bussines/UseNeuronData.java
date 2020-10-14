package bussines;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import data.NeuronData;
import dataAccess.ReadNeuronDataCSV;

public class UseNeuronData {

    private List<NeuronData> neuronDataList = new ArrayList<>();
    List<String> neuronWeightDirList = null;
    List<String> neuronBiasDirList = null;

    public UseNeuronData(UseNeuronData useNeuronData) {
        this.neuronDataList.get(0);
    }

    public UseNeuronData(String neuronWeightDataDir, String neuronBiasDataDir) throws IOException {

        // 重みデータ読み込み
        List<String> neuronWeightDataList = ReadNeuronDataCSV.readWeight(neuronWeightDataDir);

        // バイアスデータ読み込み
        String lineNeuronBiasData = ReadNeuronDataCSV.readBias(neuronBiasDataDir);

        if (!(neuronWeightDataList.size() == lineNeuronBiasData.split(",").length)) {
            System.out.println("ERROR:UseNeuronData;UseNeuronData;01");
            System.exit(0);
        }

        // ニューロンデータインスタンス生成
        NeuronData neuronData = new NeuronData(neuronWeightDataList.size(),
                neuronWeightDataList.get(0).split(",").length);

        // 重みをセット
        for (int i = 0; i < neuronWeightDataList.size(); i++) {
            neuronData.setWeight(i, neuronWeightDataList.get(i).split(","));
        }

        // バイアスをセット
        neuronData.setBias(lineNeuronBiasData.split(","));

        this.neuronDataList.add(neuronData);
    }

    // ニューロンデータが存在するフォルダを読み、ニューロンデータのリストを作成
    public UseNeuronData(String neuronWeightPath, String neuronBiasPath, int dummy) throws IOException {

        this.neuronWeightDirList = ReadNeuronDataCSV.readNeuronFolder(neuronWeightPath);
        this.neuronBiasDirList = ReadNeuronDataCSV.readNeuronFolder(neuronBiasPath);

        if (!(neuronWeightDirList.size() == neuronBiasDirList.size())) {
            System.out.println("ERROR:UseNeuronData;UseNeuronData;01");
            System.exit(0);
        }

        for (int i = 0; i < neuronWeightDirList.size(); i++) {

            // 重みデータ読み込み
            List<String> neuronWeightDataList = ReadNeuronDataCSV
                    .readWeight(neuronWeightDirList.get(i));

            // バイアスデータ読み込み
            String lineNeuronBiasData = ReadNeuronDataCSV.readBias(neuronBiasDirList.get(i));

            if (!(neuronWeightDataList.size() == lineNeuronBiasData.split(",").length)) {
                System.out.println("ERROR:UseNeuronData;UseNeuronData;02");
                System.exit(0);
            }

            // ニューロンデータインスタンス生成
            NeuronData neuronData = new NeuronData(neuronWeightDataList.size(),
                    neuronWeightDataList.get(0).split(",").length);

            // 重みをセット
            for (int j = 0; j < neuronWeightDataList.size(); j++) {
                neuronData.setWeight(j, neuronWeightDataList.get(j).split(","));
            }

            // バイアスをセット
            neuronData.setBias(lineNeuronBiasData.split(","));

            this.neuronDataList.add(neuronData);
        }
    }

    public NeuronData getNeuronData(int i) {
        return this.neuronDataList.get(i);
    }

    public List<NeuronData> getNeuronDataList() {
        return this.neuronDataList;
    }

    public List<String> getNeuronWeightDirList() {
        return this.neuronWeightDirList;
    }

    public List<String> getNeuronBiasDirList() {
        return this.neuronBiasDirList;
    }

    // 重み上限を設定する
    public void upperWeightLimit(double c) {
        for (int I = 0; I < neuronDataList.size(); I++) {
            NeuronData neuron = neuronDataList.get(I);
            for (int i = 0; i < neuron.getWeight().length; i++) {
                // 重み2乗の合計値を計算する
                double weightSum = 0.0;
                double biasSum = 0.0;
                for (int j = 0; j < neuron.getWeight()[0].length; j++) {
                    weightSum += Math.pow(neuron.getWeight(i, j), 2);
                }
                // 重み上限を超えていた場合は超えないよう設定
                if (weightSum > c) {
                    System.out.println("重み上限オーバー！>" + weightSum);
                    for (int j = 0; j < neuron.getWeight()[0].length; j++) {
                        neuron.setWeight(i, j, neuron.getWeight(i, j) / Math.pow(weightSum, 0.5));
                    }
                }
                // バイアスは重み上限を設けないのが一般だが設けてみる
                biasSum += Math.pow(neuron.getBias(i), 2);
                if (biasSum > c) {
                    System.out.println("バイアス重み上限オーバー！>" + biasSum);
                    neuron.setBias(i, neuron.getBias(i) / Math.pow(biasSum, 0.5));
                }
            }
        }
    }

}
