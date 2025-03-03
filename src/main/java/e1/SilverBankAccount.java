package e1;

public class SilverBankAccount implements BankAccount {

    private final BankAccount base = new CoreBankAccount();

    public int getBalance() {
        return base.getBalance();
    }

    public void deposit(int amount) {
        base.deposit(amount);
    }

    public void withdraw(int amount) {
        if (this.getBalance() < amount){
            throw new IllegalStateException();
        }
        base.withdraw(amount + 1);
    }
}
