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

    private BankAccount silverAccount;
    private BankAccount goldAccount;

    @BeforeEach
    void init(){
        BankAccountFactory accountFactory = new BankAccountFactory();
        silverAccount = accountFactory.createSilverBankAccount();
        goldAccount = accountFactory.createGoldBankAccount();
    }

    @Test
    public void testSilverAccountInitiallyEmpty() {
        assertEquals(0, this.silverAccount.getBalance());
    }

    @Test
    public void testSilverAccountCanDeposit() {
        this.silverAccount.deposit(STARTING_DEPOSIT_VALUE);
        assertEquals(STARTING_DEPOSIT_VALUE, this.silverAccount.getBalance());
    }

    @Test
    public void testSilverAccountCanWithdraw() {
        this.silverAccount.deposit(STARTING_DEPOSIT_VALUE);
        this.silverAccount.withdraw(LEGIT_WITHDRAW_VALUE);
        assertEquals(STARTING_DEPOSIT_VALUE - LEGIT_WITHDRAW_VALUE - SILVER_FEE_WITHDRAW, this.silverAccount.getBalance());
    }

    @Test
    public void testSilverAccountCannotWithdrawMoreThanAvailable(){
        this.silverAccount.deposit(STARTING_DEPOSIT_VALUE);
        assertThrows(IllegalStateException.class, () -> this.silverAccount.withdraw(STARTING_DEPOSIT_VALUE + DELTA));
    }

    @Test
    public void testGoldAccountInitiallyEmpty() {
        assertEquals(0, this.goldAccount.getBalance());
    }

    @Test
    public void testGoldAccountCanDeposit() {
        this.goldAccount.deposit(STARTING_DEPOSIT_VALUE);
        assertEquals(STARTING_DEPOSIT_VALUE, this.goldAccount.getBalance());
    }

    @Test
    public void testGoldAccountCanWithdraw() {
        this.goldAccount.deposit(STARTING_DEPOSIT_VALUE);
        this.goldAccount.withdraw(LEGIT_WITHDRAW_VALUE);
        assertEquals(STARTING_DEPOSIT_VALUE - LEGIT_WITHDRAW_VALUE, this.goldAccount.getBalance());
    }

    @Test
    public void testGoldAccountCanOverdraft() {
        this.goldAccount.withdraw(GOLD_OVERDRAFT_VALUE);
        assertEquals(- GOLD_OVERDRAFT_VALUE, this.goldAccount.getBalance());
    }

    @Test
    public void testGoldAccountCantOverdraftTooMuch() {
        assertThrows(IllegalStateException.class,
                () -> this.goldAccount.withdraw(GOLD_OVERDRAFT_VALUE + DELTA));
    }

}
