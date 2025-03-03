package e1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BankAccountTest {

    private BankAccount silverAccount;

    @BeforeEach
    void init(){
        BankAccountFactory accountFactory = new BankAccountFactory();
        silverAccount = accountFactory.createSilverBankAccount();
    }

    @Test
    public void testInitiallyEmpty() {
        assertEquals(0, this.silverAccount.getBalance());
    }

    @Test
    public void testCanDeposit() {
        this.silverAccount.deposit(1000);
        assertEquals(1000, this.silverAccount.getBalance());
    }

    @Test
    public void testCanWithdraw() {
        this.silverAccount.deposit(1000);
        this.silverAccount.withdraw(200);
        assertEquals(799, this.silverAccount.getBalance());
    }

    @Test
    public void testCannotWithdrawMoreThanAvailable(){
        this.silverAccount.deposit(1000);
        assertThrows(IllegalStateException.class, () -> this.silverAccount.withdraw(1200));
    }

}
