public class Calculator {
    private ICalculator calculator;

    public Calculator(ICalculator calculator) {
        this.calculator = calculator;
    }

    public int sum(int x, int y) {
        return this.calculator.sum(x, y);
    }

    public int minus(int x, int y) {
        return this.calculator.minus(x, y);
    }
}
