package id.ac.polinema;
//pananganan ketika saldo tidak mencukupi saat penarikan
public class InsufficientBalanceException extends Exception {
    public InsufficientBalanceException(String message) {
        super(message);
    }
}
