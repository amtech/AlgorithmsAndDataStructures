package main.optimization;

public interface DerivativeFunction extends Function {

    Function[] getPartialDerivatives();

    double[][] getPartialDerivativesMatrix();

    public double[] getFree();
}
