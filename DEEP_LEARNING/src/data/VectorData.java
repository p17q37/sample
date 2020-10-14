package data;

import common.FormatConvertUtil;

public class VectorData {

	private double[] vectorData = null;

	public VectorData(int vectorSize) {
		this.vectorData = new double[vectorSize];
	}

	public VectorData(double[] vector) {
		this.vectorData = new double[vector.length];
		this.vectorData = vector;
	}

	public VectorData(String[] vector) throws NumberFormatException {
		this.vectorData = FormatConvertUtil.stringArrayToDoubleArray(vector);
	}

	public double[] getVectorData() {
		return vectorData;
	}

}
