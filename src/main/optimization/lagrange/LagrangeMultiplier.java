package main.optimization.lagrange;

import main.matrix.Gauss;
import main.matrix.MatrixUtils;
import main.optimization.Function;

public class LagrangeMultiplier {

    public static void main(String[] args) {

        LagrangeFunction lagrangeFunction = new LagrangeFunction() {

            @Override
            public double count(double... params) {
                throw new UnsupportedOperationException();
            }

            @Override
            public double[][] getPartialDerivativesMatrix() {
                double[][] mainMatrix = {
                        {2, 0, -1},
                        {0, 2, -1},
                        {1, 1, 0}
                };
                return mainMatrix;
            }

            @Override
            public double[] getFree() {
                return new double[]{-4, -8, 180};
            }

            @Override
            public Function[] getPartialDerivatives() {
                throw new UnsupportedOperationException();
            }

        };

        //double[] rez = Gauss.gauss(lagrangeFunction.getSlau(), lagrangeFunction.getFree());
        LagrangeMultiplier lagrangeMultiplier = new LagrangeMultiplier(lagrangeFunction);
        MatrixUtils.print(lagrangeMultiplier.solve());

    }

    private final LagrangeFunction lagrangeFunction;

    public LagrangeMultiplier(LagrangeFunction lagrangeFunction) {
        super();
        this.lagrangeFunction = lagrangeFunction;
    }

    public double[] solve() {
        double[] rez = Gauss.solve(lagrangeFunction.getPartialDerivativesMatrix(), lagrangeFunction.getFree());
        return rez;
    }


}
