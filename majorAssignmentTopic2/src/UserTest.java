package src;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserTest {
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
    void testLogin() {
        // Test with username
        assertTrue(testUser.login("testuser", "password"));
        
        // Test with email
        assertTrue(testUser.login("test@example.com", "password"));
        
        // Test wrong credentials
        assertFalse(testUser.login("testuser", "wrongpassword"));
        assertFalse(testUser.login("wronguser", "password"));
    }

    @Test
    void testCreateChannel() {
        Channel channel = testUser.createChannel("Test Channel", "Test Description");
        
        assertNotNull(channel);
        assertEquals("Test Channel", channel.getChannelName());
        assertEquals(testUser, channel.getOwner());
        assertEquals("Test Description", channel.getChannelDescription());
        
        // Verify the user's channel reference is set
        assertEquals(channel, testUser.getChannel());
        
        // Test creating second channel (should not be allowed)
        Channel channel2 = testUser.createChannel("Second Channel", "Second Desc");
        assertEquals(channel, testUser.getChannel()); // Should still reference first channel
    }

    @Test
    void testHasChannel() {
        assertFalse(testUser.hasChannel());
        testUser.createChannel("Test Channel", "Test Description");
        assertTrue(testUser.hasChannel());
    }
}