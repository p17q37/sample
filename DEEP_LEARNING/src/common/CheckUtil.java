package common;

public class CheckUtil {

	public static boolean vectorSizeCheck(double[] vector1, double[] vector2) {
		if (vector1.length == vector2.length) {
			return true;
		}
		return false;
	}

}
