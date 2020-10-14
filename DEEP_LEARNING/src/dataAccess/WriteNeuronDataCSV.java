package dataAccess;

import java.io.FileNotFoundException;
import java.io.IOException;

import common.FormatConvertUtil;
import common.WriteFile;
import data.NeuronData;

public class WriteNeuronDataCSV {

    public static void writeNeuronData(NeuronData neuronData, String weightDir, String biasDir)
            throws FileNotFoundException, IOException {

        WriteFile.writeStringArray2ToCSV(weightDir,
                FormatConvertUtil.doubleArray2ToStringArray2(neuronData.getWeight()));
        WriteFile.writeStringArrayToCSV(biasDir, FormatConvertUtil.doubleArrayToStringArray(neuronData.getBias()));
    }

    public static void writeNeuronData(NeuronData neuronData, String weightDir, String biasDir, int digit)
            throws FileNotFoundException, IOException {

        WriteFile.writeStringArray2ToCSV(weightDir,
                FormatConvertUtil.doubleArray2ToStringArray2(neuronData.getWeight(), digit));
        WriteFile.writeStringArrayToCSV(biasDir,
                FormatConvertUtil.doubleArrayToStringArray(neuronData.getBias(), digit));
    }
}
