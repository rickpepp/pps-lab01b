package e1;

public class BankAccountFactory {
    public BankAccount createSilverBankAccount() {
        BankAccount coreAccount = createBasicAccount();
        coreAccount = new WithdrawFunctionFeeDecorator(coreAccount, amountWithdraw -> 1);
        coreAccount = new OverDraftDecorator(coreAccount, 0);
        return coreAccount;
    }

    private static BankAccount createBasicAccount() {
        BankAccount coreAccount = new CoreBankAccount();
        coreAccount = new BaseDecoratorBankAccount(coreAccount);
        return coreAccount;
    }

    public BankAccount createGoldBankAccount() {
        BankAccount coreAccount = createBasicAccount();
        coreAccount = new OverDraftDecorator(coreAccount, 500);
        return coreAccount;
    }

    public BankAccount createBronzeBankAccount() {
        BankAccount coreAccount = createBasicAccount();
        coreAccount = new WithdrawFunctionFeeDecorator(coreAccount,
                amountWithdraw -> (amountWithdraw < 100) ? 0 : 1);
        coreAccount = new OverDraftDecorator(coreAccount, 0);
        return coreAccount;
    }
}
