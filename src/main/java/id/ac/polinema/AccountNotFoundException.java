package id.ac.polinema;
//menangani kesalahan ketika nomor rekening tidak ditemukan dalam sistem bank
public class AccountNotFoundException extends Exception {
    public AccountNotFoundException(String message) {
        super(message);
    }
}