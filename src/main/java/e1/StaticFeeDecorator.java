package e1;

public class StaticFeeDecorator extends BaseDecoratorBankAccount {

    private final static int STATIC_FEE = 1;

    public StaticFeeDecorator(BankAccount base) {
        super(base);
    }

    @Override
    public void withdraw(int amount) {
        super.withdraw(amount + STATIC_FEE);
    }
}
