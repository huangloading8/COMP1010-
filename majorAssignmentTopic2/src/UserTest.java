import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {
    private User testUser;

    @BeforeEach
    public void setUp() {
        testUser = new User("testuser", "password", "test@example.com");
    }

    @Test
    public void testUserCreation() {
        assertNotNull(testUser);
        assertEquals("testuser", testUser.getUsername());
        assertEquals("test@example.com", testUser.getEmail());
        assertNull(testUser.getChannel());
    }

    @Test
    public void testLogin() {
        assertTrue(testUser.login("testuser", "password"));
        assertTrue(testUser.login("test@example.com", "password"));
        assertFalse(testUser.login("testuser", "wrongpassword"));
        assertFalse(testUser.login("wronguser", "password"));
    }

    @Test
    public void testCreateChannel() {
        Channel channel = testUser.createChannel("Test Channel", "Test Description");

        assertNotNull(channel);
        assertEquals("Test Channel", channel.getChannelName());
        assertEquals("Test Description", channel.getChannelDescription());
        assertEquals(testUser, channel.getOwner());
        assertEquals(channel, testUser.getChannel());
    }
}
