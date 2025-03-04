package e1;

import java.util.Optional;

public class StaticFeeDecorator extends BaseDecoratorBankAccount {

    private final Integer fee;

    public StaticFeeDecorator(BankAccount base, Integer fee) {
        super(base);
        this.fee = fee;
    }

    @Override
    public void withdraw(int amount) {
        super.withdraw(amount + this.fee);
    }
}
