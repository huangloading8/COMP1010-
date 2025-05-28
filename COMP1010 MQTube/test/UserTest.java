package test;

import org.junit.*;
import static org.junit.Assert.*;

import src.User;

class UserTest {
    private User testUser;



    @Test
    void testUserCreation() {
        assertNotNull(testUser);
        assertEquals("testuser", testUser.getUsername());
        assertEquals("test@example.com", testUser.getEmail());
        assertNull(testUser.getChannel());
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
        
    }


}