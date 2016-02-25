package betess;

/**
 *
 * @author Bruno Pereira
 */
public class Odd {

    private double oddA;
    private double oddB;
    private double oddEmpate;

    public Odd() {
        this.oddA = 0.0;
        this.oddB = 0.0;
        this.oddEmpate = 0.0;

    }

    public Odd(double oA, double oB, double em) {

        this.oddA = oA;
        this.oddB = oB;
        this.oddEmpate = em;

    }

    double getOddA() {
        return this.oddA;
    }

    double getOddB() {
        return this.oddB;
    }

    double getOddEmpate() {
        return this.oddEmpate;
    }

    public void setOddA(double oddA) {
        this.oddA = oddA;
    }

    public void setOddB(double oddB) {
        this.oddB = oddB;
    }

    public void setOddEmpate(double oddEmpate) {
        this.oddEmpate = oddEmpate;
    }

}
