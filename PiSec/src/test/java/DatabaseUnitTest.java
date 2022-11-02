import dao.DatabaseAccess;
import models.Account;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static dao.DatabaseAccess.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static utils.PasswordUtils.generateSalt;
import static utils.PasswordUtils.preparePassword;

public class DatabaseUnitTest {

    /**
     * Test for account storage in database
     */
    @Test
    public void testAccountDatabase() {
        String username = "test12345";
        String salt = generateSalt();
        String password = preparePassword("password", salt);
        Account account = new Account(username, password, salt);
        try {
            addAccount(account, password, salt);
        } catch (SQLException e) {
//            errorCode 19 stands for unique constraint failed
            if (e.getErrorCode() != 19)
                e.printStackTrace();
        }
//        checks if account was added
        assertEquals("test12345", getAccount("test12345").getUsername());
    }

    /**
     * Test alert storage in database
     */
    @Test
    public void testAlertDatabase() {
        Long id = 0L;
        Long date = 1666793754726L;
//        checks if alert datetime file is stored correct
        assertEquals(date, getAlert(id).getDateTime().getTime());
    }
}
