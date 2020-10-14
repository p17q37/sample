package data;

public class TeacherData {

	private double[] teacherData = null;

	public TeacherData(int vectorSize) {
		this.teacherData = new double[vectorSize];
	}

	public TeacherData(double[] vector) {
		this.teacherData = new double[vector.length];
		this.teacherData = vector;
	}

	public TeacherData(String[] vector) throws NumberFormatException {
		this.teacherData = new double[vector.length];
		for (int i = 0; i < vector.length; i++) {
			this.teacherData[i] = Double.parseDouble(vector[i]);
		}
	}

	public double[] getTeacherData() {
		return this.teacherData;
	}

	public void setTeacherData(double[] vector) {
		this.teacherData = vector;
	}

}
