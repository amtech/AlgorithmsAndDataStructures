package main.optimization.gradient;

import main.matrix.MatrixUtils;
import main.optimization.DerivativeFunction;
import main.optimization.Function;

public class GradientMethod {

    public static void main(String[] args) {

        DerivativeFunction derivativeFunction = new DerivativeFunction() {

            @Override
            public double count(double... params) {
                double x1 = params[0];
                double x2 = params[1];

                return 2 * Math.pow(x1, 2) + x1 * x2 + Math.pow(x2, 2);
            }

            @Override
            public double[][] getPartialDerivativesMatrix() {
                throw new UnsupportedOperationException();
            }

            @Override
            public double[] getFree() {
                throw new UnsupportedOperationException();
            }

            @Override
            public Function[] getPartialDerivatives() {
                Function[] derivates = {
                        new Function() {
                            @Override
                            public double count(double... params) {
                                final double x1 = params[0];
                                final double x2 = params[1];
                                return 4 * x1 + x2;
                            }
                        },
                        new Function() {
                            @Override
                            public double count(double... params) {
                                final double x1 = params[0];
                                final double x2 = params[1];
                                return x1 + 2 * x2;
                            }
                        }
                };
                return derivates;
            }
        };

        GradientMethod gradientMethod = new GradientMethod(derivativeFunction, 0.5, 0.1);
        double[] result = gradientMethod.solve(new double[]{0.5, 1});
        MatrixUtils.print(result);
    }

    private final DerivativeFunction derivativeFunction;
    private double alpha;
    private final double epsilon;

    public GradientMethod(DerivativeFunction derivativeFunction, double alpha,
                          double epsilon) {
        super();
        this.derivativeFunction = derivativeFunction;
        this.alpha = alpha;
        this.epsilon = epsilon;
    }

    public double[] solve(final double[] currentParams) {

        double[] nextParams = new double[currentParams.length];
        Function[] partialDerivatives = derivativeFunction.getPartialDerivatives();
        for (int i = 0; i < nextParams.length; i++) {
            nextParams[i] = currentParams[i] - alpha * partialDerivatives[i].count(currentParams);
        }
        if (derivativeFunction.count(nextParams) > derivativeFunction.count(currentParams)) {
            alpha /= 2;
            return solve(currentParams);
        } else {
            if (Math.abs(derivativeFunction.count(nextParams) - derivativeFunction.count(currentParams)) > epsilon) {
                return solve(nextParams);
            } else {
                return nextParams;
            }
        }
    }

}
