import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Test_User {
    private User testUser;

    @BeforeEach
    void setUp() {
        testUser = new User("testuser", "test@example.com", "password");
    }

    @Test
    void testUserCreation() {
        assertNotNull(testUser);
        assertEquals("testuser", testUser.getUsername());
        assertEquals("test@example.com", testUser.getEmail());
        assertNull(testUser.getChannel());
    }

    @Test
    void testAuthenticate() {
        assertTrue(testUser.authenticate("password"));
        assertFalse(testUser.authenticate("wrongpassword"));
    }

    @Test
    void testLogin_edgeCases() {
        // Blank username/email
        assertFalse(testUser.login("", "password"));
        assertFalse(testUser.login("testuser", ""));

        // Null input (if your code handles it)
        assertFalse(testUser.login(null, "password"));
        assertFalse(testUser.login("testuser", null));

        // Case sensitivity
        assertFalse(testUser.login("TestUser", "password"));
        assertFalse(testUser.login("TEST@EXAMPLE.COM", "password"));
    }

   @Test
    void testCreateChannel() {
        Channel channel = testUser.createChannel("Test Channel", "Test Description");

        assertNotNull(channel);
        assertEquals("Test Channel", channel.getChannelName());
        assertEquals(testUser, channel.getOwner());
        assertEquals("Test Description", channel.getChannelDescription());
        assertEquals(channel, testUser.getChannel());

        // Try to create another channel
        Channel channel2 = testUser.createChannel("Second Channel", "Second Desc");
        
        // Should still be the same channel object
        assertSame(channel, channel2);
        assertEquals(channel, testUser.getChannel());
    }

    @Test
    void testHasChannel() {
        assertFalse(testUser.hasChannel());
        testUser.createChannel("Test Channel", "Test Description");
        assertTrue(testUser.hasChannel());
    }
}