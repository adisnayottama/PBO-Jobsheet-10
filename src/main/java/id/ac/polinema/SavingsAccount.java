package id.ac.polinema;

// Implementasi interface InterestBearing
public class SavingsAccount extends Account implements InterestBearing {

    private static final double MINIMAL_BALANCE = 50000;
    private double interestRate;

    public SavingsAccount(String accountNumber, Customer owner, double balance, double interestRate) {
        super(accountNumber, owner, balance);
        this.interestRate = interestRate;
    }

    public double getInterestRate() {
        return interestRate;
    }

    @Override 
    protected boolean canWithdraw(double amount) {
        return amount > 0 && (getBalance() - amount) >= MINIMAL_BALANCE;
    }

    @Override 
    public void printInfo() {
        super.printInfo();
        System.out.println("Account type: Savings, interest rate: " + interestRate);
    }
    
    // Implementasi monthlyFee (tanpa biaya / 0)
    @Override
    public double monthlyFee() {
        return 0;
    } 

    // Implementasi wajib dari interface InterestBearing
    @Override 
    public void applyInterest() {
        double interest = getBalance() * interestRate;
        deposit(interest);
    }
}