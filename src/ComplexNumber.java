public class ComplexNumber {
    private double imaginary, real;

    public ComplexNumber(double real, double imaginary) {
        this.imaginary = imaginary;
        this.real = real;
    }

    public double argument() {
        return Math.sqrt(this.real * this.real + this.imaginary * this.imaginary);
    }

    @Override
    public String toString() {
        return this.real + " + " + this.imaginary + "i";
    }

    public ComplexNumber times(ComplexNumber number) {

        double a = this.real * number.real;
        double b = this.imaginary * number.real;
        double c = this.real * number.imaginary;
        double d = this.imaginary * number.imaginary * -1;

        double newReal = a + d;
        double newImaginary = b + c;


        return new ComplexNumber(newReal, newImaginary);
    }

    public ComplexNumber add(ComplexNumber number) {

        double newReal = this.real + number.real;
        double newImaginary = this.imaginary + number.imaginary;

        return new ComplexNumber(newReal, newImaginary);

    }

    public double getImaginary() {
        return imaginary;
    }

    public void setImaginary(double imaginary) {
        this.imaginary = imaginary;
    }

    public double getReal() {
        return real;
    }

    public void setReal(double real) {
        this.real = real;
    }
}
