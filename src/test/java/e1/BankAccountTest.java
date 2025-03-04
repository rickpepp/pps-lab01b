package e1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BankAccountTest {

    private static final int GOLD_OVERDRAFT_VALUE = 500;
    private static final int STARTING_DEPOSIT_VALUE = 1000;
    private static final int LEGIT_WITHDRAW_VALUE = 200;
    private static final int DELTA = 1;
    private static final int SILVER_FEE_WITHDRAW = 1;
    private static final int BRONZE_FEE_WITHDRAW = 1;
    private static final int SMALL_WITHDRAW = 1;

    private BankAccountFactory accountFactory;

    @BeforeEach
    void init(){
        this.accountFactory = new BankAccountFactory();
    }

    @Test
    public void testSilverAccountInitiallyEmpty() {
        BankAccount silverAccount = accountFactory.createSilverBankAccount();
        assertEquals(0, silverAccount.getBalance());
    }

    @Test
    public void testSilverAccountCanDeposit() {
        BankAccount silverAccount = accountFactory.createSilverBankAccount();
        silverAccount.deposit(STARTING_DEPOSIT_VALUE);
        assertEquals(STARTING_DEPOSIT_VALUE, silverAccount.getBalance());
    }

    @Test
    public void testSilverAccountCanWithdraw() {
        BankAccount silverAccount = accountFactory.createSilverBankAccount();
        depositAndWithdraw(silverAccount, STARTING_DEPOSIT_VALUE, LEGIT_WITHDRAW_VALUE);
        assertEquals(STARTING_DEPOSIT_VALUE - LEGIT_WITHDRAW_VALUE - SILVER_FEE_WITHDRAW,
                silverAccount.getBalance());
    }

    private static void depositAndWithdraw(BankAccount silverAccount, int depositValue, int withdrawValue) {
        silverAccount.deposit(depositValue);
        silverAccount.withdraw(withdrawValue);
    }

    @Test
    public void testSilverAccountCannotWithdrawMoreThanAvailable(){
        BankAccount silverAccount = accountFactory.createSilverBankAccount();
        silverAccount.deposit(STARTING_DEPOSIT_VALUE);
        assertThrows(IllegalStateException.class, () -> silverAccount.withdraw(STARTING_DEPOSIT_VALUE + DELTA));
    }

    @Test
    public void testGoldAccountInitiallyEmpty() {
        BankAccount goldAccount = accountFactory.createGoldBankAccount();
        assertEquals(0, goldAccount.getBalance());
    }

    @Test
    public void testGoldAccountCanDeposit() {
        BankAccount goldAccount = accountFactory.createGoldBankAccount();
        goldAccount.deposit(STARTING_DEPOSIT_VALUE);
        assertEquals(STARTING_DEPOSIT_VALUE, goldAccount.getBalance());
    }

    @Test
    public void testGoldAccountCanWithdraw() {
        BankAccount goldAccount = accountFactory.createGoldBankAccount();
        depositAndWithdraw(goldAccount, STARTING_DEPOSIT_VALUE, LEGIT_WITHDRAW_VALUE);
        assertEquals(STARTING_DEPOSIT_VALUE - LEGIT_WITHDRAW_VALUE, goldAccount.getBalance());
    }

    @Test
    public void testGoldAccountCanOverdraft() {
        BankAccount goldAccount = accountFactory.createGoldBankAccount();
        goldAccount.withdraw(GOLD_OVERDRAFT_VALUE);
        assertEquals(- GOLD_OVERDRAFT_VALUE, goldAccount.getBalance());
    }

    @Test
    public void testGoldAccountCantOverdraftTooMuch() {
        BankAccount goldAccount = accountFactory.createGoldBankAccount();
        assertThrows(IllegalStateException.class,
                () -> goldAccount.withdraw(GOLD_OVERDRAFT_VALUE + DELTA));
    }

    @Test
    public void testBronzeAccountInitiallyEmpty() {
        BankAccount bronzeAccount = accountFactory.createBronzeBankAccount();
        assertEquals(0, bronzeAccount.getBalance());
    }

    @Test
    public void testBronzeAccountCanDeposit() {
        BankAccount bronzeAccount = accountFactory.createBronzeBankAccount();
        bronzeAccount.deposit(STARTING_DEPOSIT_VALUE);
        assertEquals(STARTING_DEPOSIT_VALUE, bronzeAccount.getBalance());
    }

    @Test
    public void testBronzeAccountCanWithdrawWithFee() {
        BankAccount bronzeAccount = accountFactory.createBronzeBankAccount();
        depositAndWithdraw(bronzeAccount, STARTING_DEPOSIT_VALUE, LEGIT_WITHDRAW_VALUE);
        assertEquals(STARTING_DEPOSIT_VALUE - LEGIT_WITHDRAW_VALUE - BRONZE_FEE_WITHDRAW,
                bronzeAccount.getBalance());
    }

    @Test
    public void testBronzeAccountCanWithdrawWithoutFee() {
        BankAccount bronzeAccount = accountFactory.createBronzeBankAccount();
        depositAndWithdraw(bronzeAccount, STARTING_DEPOSIT_VALUE, SMALL_WITHDRAW);
        assertEquals(STARTING_DEPOSIT_VALUE - SMALL_WITHDRAW, bronzeAccount.getBalance());
    }

    @Test
    public void testBronzeAccountCannotWithdrawMoreThanAvailable(){
        BankAccount bronzeAccount = accountFactory.createBronzeBankAccount();
        bronzeAccount.deposit(STARTING_DEPOSIT_VALUE);
        assertThrows(IllegalStateException.class, () -> bronzeAccount.withdraw(STARTING_DEPOSIT_VALUE + DELTA));
    }


}
