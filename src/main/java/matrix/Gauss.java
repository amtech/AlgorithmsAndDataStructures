package matrix;

import java.util.Arrays;

public class Gauss {
	
	public static void main(String[] args) {
		double[][] mainMatrix = {
				{2, 0, 1},
				{0, 2, 1},
				{1, 1, 0}
		};
		double[] free = {-9, -6, 150};
		double[] rez = solve(mainMatrix, free);
		/*Double[] rezz = new Double[rez.length];
		for (int i = 0; i < rez.length; i++) {
			rezz[i] = rez[i];
		}
		System.out.println(Arrays.asList(rezz));*/
	}

	public static double[] solve(double[][] a, double[] b) {
		int n = a.length;
		for (int row = 0; row < n; row++) {
			int best = row;
			for (int i = row + 1; i < n; i++) {
				if (Math.abs(a[best][row]) < Math.abs(a[i][row])) {
					best = i;
				}
			}
			double[] tt = a[row];
			a[row] = a[best];
			a[best] = tt;
			double t = b[row];
			b[row] = b[best];
			b[best] = t;
			for (int i = row + 1; i < n; i++) {
				a[row][i] /= a[row][row];
			}
			b[row] /= a[row][row];
			// a[row][row] = 1;
			for (int i = 0; i < n; i++) {
				double x = a[i][row];
				if (i != row && x != 0) {
					// row + 1 instead of row is an optimization
					for (int j = row + 1; j < n; j++) {
						a[i][j] -= a[row][j] * x;
					}
					b[i] -= b[row] * x;
				}
			}
		}
		return b;
	}
}
