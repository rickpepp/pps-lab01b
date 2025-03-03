package e1;

public class BankAccountFactory {
    public BankAccount createSilverBankAccount() {
        BankAccount coreAccount = new CoreBankAccount();
        coreAccount = new BaseDecoratorBankAccount(coreAccount);
        coreAccount = new StaticFeeDecorator(coreAccount, 1);
        coreAccount = new OverDraftDecorator(coreAccount, 0);
        return coreAccount;
    }
}
