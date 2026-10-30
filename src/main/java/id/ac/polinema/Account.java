package id.ac.polinema;
// ubah kelas menjadi abstrak
public abstract class Account {
    private String accountNumber;
    private Customer owner;
    private double balance;

    public Account(String accountNumber, Customer owner, double balance) {
        this.accountNumber = accountNumber;
        this.owner = owner;
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public Customer getOwner() {
        return owner;
    }

    public double getBalance() {
        return balance;
    }

    public boolean deposit(double amount) {
        if (amount <= 0) {
            return false;
        }
        balance += amount;
        return true;
    }

    public boolean deposit(double amount, String note) {
        // memanggil method deposit(double) yang sudah ada terlebih dahulu
        if (!deposit(amount)) {
            return false;
        }
        // jika penyetoran berhasil, cetak catatan transaksi
        System.out.println(accountNumber + " deposit note: " + note);
        return true;
    }

    // mengubah return type withdraw() menjadi void dan menambahkan throws InsufficientBalanceException
    public void withdraw(double amount) throws InsufficientBalanceException {
        // validasi saldo minimum dan melempar exception jika gagal
        if (!canWithdraw(amount)) {
            throw new InsufficientBalanceException(
                accountNumber + ": insufficent balance for a withdrawal of " + amount);
        }
        balance -= amount;
    }

    protected boolean canWithdraw(double amount) {
        return amount > 0 && amount <= balance;
    }

    public void printInfo() {
        System.out.println(accountNumber + " - " + owner.getName() + " - " + " balance: " + balance);
    }
    // menambahkan method abtrak
    public abstract double monthlyFee();
}