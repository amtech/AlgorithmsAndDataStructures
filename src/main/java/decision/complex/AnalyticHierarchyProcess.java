package decision.complex;

import matrix.MatrixUtils;

public class AnalyticHierarchyProcess {
	
	public static void main(String[] args) {
		
		double[][] alternatives = {
			{300, 0.9930, 3},
			{400, 0.9950, 4},
			{800, 0.9990, 6},
			{900, 0.9998, 6}
		};
		
		double[][] criteriaComparisonMatrix = {
				{1, 	5, 3},
				{1/5., 	1, 1/3.},
				{1/3., 	3, 1}
		};
		
		double[][][] relativeCriteriaImportanceOfAlternatives = new double[alternatives[0].length][alternatives.length][alternatives.length];
		
		double[][] costCriteriaImportance = {
				{1,		3, 		7, 		9},
				{1/3.,	1, 		5, 		7},
				{1/7.,	1/3., 	1, 		3},
				{1/9.,	1/7.,	1/3.,	1},
		};
		
		double[][] reliabilityCriteriaImportance = {
				{1,		1/3., 	1/5., 	1/7.},
				{3,		1, 		1/5., 	1/5.},
				{5,		5,		1,		1/3.},
				{7,		5,		3,		1},
		};

		double[][] expluatationTermCriteriaImportance = {
				{1,		1/3., 	1/3., 	1/3.},
				{3,		1, 		1/5., 	1/5.},
				{3,		5,		1,		1},
				{3,		5,		1,		1},
		};
		
		relativeCriteriaImportanceOfAlternatives[0] = costCriteriaImportance;
		relativeCriteriaImportanceOfAlternatives[1] = reliabilityCriteriaImportance;
		relativeCriteriaImportanceOfAlternatives[2] = expluatationTermCriteriaImportance;
		
		AnalyticHierarchyProcess analyticHierarchyProcess = new AnalyticHierarchyProcess();
		analyticHierarchyProcess.setCriteriaComparisonMatrix(criteriaComparisonMatrix);
		analyticHierarchyProcess.setRelativeCriteriaImportanceOfAlternatives(relativeCriteriaImportanceOfAlternatives);
		analyticHierarchyProcess.findBestAlternative();
	}
	
	private double[][] criteriaComparisonMatrix;
	
	private double[][][] relativeCriteriaImportanceOfAlternatives;
	
	public void findBestAlternative() {
		final int rows = criteriaComparisonMatrix.length; // 3
		final int alternatives = relativeCriteriaImportanceOfAlternatives[0].length; // 4
		final int criteriaComparisonWeightColumn = criteriaComparisonMatrix[0].length - 1; // 4
		final int relativeCriteriaImportanceOfAlternativesWeightColumn = relativeCriteriaImportanceOfAlternatives[0][0].length - 1; // 5
		
		double[] alternativesResult = new double[alternatives]; 
		for (int i = 0; i < alternatives; i++) {
			double current = 0;
			for (int j = 0; j < rows; j++) {
				current += criteriaComparisonMatrix[j][criteriaComparisonWeightColumn] * relativeCriteriaImportanceOfAlternatives[j][i][relativeCriteriaImportanceOfAlternativesWeightColumn];
			}
			alternativesResult[i] = current;
			System.out.println(String.format("%d: %.3f", i + 1, current));
		}
		
		int bestAlternativeIndex = 0;
		for (int i = 1; i < alternatives; i++) {
			if (alternativesResult[i] > alternativesResult[bestAlternativeIndex]) {
				bestAlternativeIndex = i;
			}
		}
		
		System.out.println(String.format("Best variant: %d with result %.3f", bestAlternativeIndex + 1, alternativesResult[bestAlternativeIndex]));
	}
	
	public void setCriteriaComparisonMatrix(double[][] criteriaComparisonMatrix) {
		this.criteriaComparisonMatrix = createExtendedMatrix(criteriaComparisonMatrix);
		findOwnVectorAndCriteriaWeight(this.criteriaComparisonMatrix);
		MatrixUtils.print(this.criteriaComparisonMatrix);
	}
	
	public void setRelativeCriteriaImportanceOfAlternatives(double[][][] relativeCriteriaImportanceOfAlternatives) {
		for (int i=0; i < relativeCriteriaImportanceOfAlternatives.length; i++) {
			relativeCriteriaImportanceOfAlternatives[i] = createExtendedMatrix(relativeCriteriaImportanceOfAlternatives[i]);
			findOwnVectorAndCriteriaWeight(relativeCriteriaImportanceOfAlternatives[i]);
			MatrixUtils.print(relativeCriteriaImportanceOfAlternatives[i]);
		}
		this.relativeCriteriaImportanceOfAlternatives = relativeCriteriaImportanceOfAlternatives;
	}
	
	private double[][] createExtendedMatrix(double[][] original) {
		final int rows = original.length;
		final int columns = original[0].length;
		double[][] extended = new double[rows][columns + 2];
		for (int i = 0; i < rows; i++) {
			System.arraycopy(original[i], 0, extended[i], 0, columns);
		}
		return extended;
	}
	
	private void findOwnVectorAndCriteriaWeight(double[][] matrix) {
		findOwnVector(matrix);
		findCriteriaWeight(matrix);
	}
	
	private void findOwnVector(double[][] matrix) {
		final int criterias = matrix.length;
		for (int i = 0; i < criterias; i++) {
			double rowMul = 1;
			for (int j = 0; j < criterias; j++) {
				rowMul *= matrix[i][j];
			}
			matrix[i][criterias] = nthroot(rowMul, criterias);
		}
	}
	
	private void findCriteriaWeight(double[][] matrix) {
		final int criterias = matrix.length;
		double ownVectorSum = 0;
		for (int i = 0; i < criterias; i++) {
			ownVectorSum += matrix[i][criterias];
		}
		for (int i = 0; i < criterias; i++) {
			matrix[i][criterias + 1] = matrix[i][criterias] / ownVectorSum;
		}
	}

	// TODO: move to utils
	public static double nthroot(double x, int n) {
		assert (n > 1 && x > 0);
		int np = n - 1;
		double g1 = x;
		double g2 = iter(g1, np, n, x);
		while (g1 != g2) {
			g1 = iter(g1, np, n, x);
			g2 = iter(iter(g2, np, n, x), np, n, x);
		}
		return g1;
	}
			 
	private static double iter(double g, int np, int n, double x) {
		return (np * g + x / Math.pow(g, np)) / n;
	}
	
}
