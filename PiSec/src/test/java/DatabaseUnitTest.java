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
            e.printStackTrace();
            assertEquals("test12345", getAccount("test12345").getUsername());
        }
    }
}
