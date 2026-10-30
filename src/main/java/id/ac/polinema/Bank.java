package id.ac.polinema;

public class Bank {
    private Account[] accounts;
    private int count;

    public Bank(int capacity) {
        accounts = new Account[capacity];
        count = 0;
    }

    public boolean addAccount(Account account) {
        if (count >= accounts.length) {
            return false;
        }
        accounts[count] = account;
        count++;
        return true;
    }

    //mencari rekening berdasarkan nomor, melempar exception jika tidak ditemukan
    public Account findAccount(String accountNumber) throws AccountNotFoundException {
        for (int i = 0; i < count; i++) {
            if (accounts[i].getAccountNumber().equals(accountNumber)) {
                return accounts[i];
            }
        }
        // melempar exception kustom daripada mengembalikan nilai null
        throw new AccountNotFoundException("Account with number " + accountNumber + " not found.");
    }

    public void printAllAccounts() {
        for (int i = 0; i < count; i++) {
            accounts[i].printInfo();
        }
    }

    // method untuk mencetak rincian biaya bulanan setiap akun
    public void printMonthlyFees() {
        for (int i = 0; i < count; i++) {
            System.out.println(accounts[i].getAccountNumber() + " fee: " + accounts[i].monthlyFee());
        }
    }

    public void printAuditLogs() {
        for (int i = 0; i < count; i++) {
            if (accounts[i] instanceof Auditable) {
                Auditable auditable = (Auditable) accounts[i];
                System.out.println(auditable.auditLog());
            }
        }
    }

    public void printInterestRates() {
        for (int i = 0; i < count; i++) {
            if (accounts[i] instanceof SavingsAccount) {
                SavingsAccount sa = (SavingsAccount) accounts[i];
                System.out.println(sa.getAccountNumber() + " interest rate: " + sa.getInterestRate());
            }
        }
    }

    //perulangan untuk memproses bunga dan biaya bulanan secara polimorfik
    public void processMonthEnd() {
        for (int i = 0; i < count; i++) {
            Account acc = accounts[i];
            // Memeriksa akun mengimplementasikan InterestBearing menggunakan pattern matching instanceof
            if (acc instanceof InterestBearing bearing) {
                bearing.applyInterest();

                // menampilkan bunga telah diterapkan beserta nomor rekening dan saldo terbaru
                System.out.println(acc.getAccountNumber() + " interest applied, new balance " + acc.getBalance());
            }
            // menampilkan biaya bulanan (monthly fee) untuk setiap rekening
            System.out.println(acc.getAccountNumber() + " monthly fee: " + acc.monthlyFee());
        }
    }

    //perulangan untuk mencetak log audit khusus rekening yang Auditable
    public void printAuditLog() {
        for (int i = 0; i < count; i++) {
            Account acc = accounts[i];
            //memeriksa akun, mengimplementasikan interface Auditable menggunakan instanceof
            if (acc instanceof Auditable auditable) {
                System.out.println(auditable.auditLog());
            }
        }
    }
}
