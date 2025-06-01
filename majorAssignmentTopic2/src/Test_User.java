import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Test_User {
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

    @Test
    void testSetChannelManually() {
        Channel manualChannel = new Channel("Manual Channel", testUser, "Manual Description");
        testUser.setChannel(manualChannel); // Assuming setChannel exists
        assertEquals(manualChannel, testUser.getChannel());
    }


    @Test
    void testEmptyVideoList() {
        Channel channel = testUser.createChannel("Empty Channel", "No videos");
        assertTrue(channel.getVideos().isEmpty()); // Assuming getVideos() returns a list
    }

    @Test
    void testCreateChannelWithNulls() {
        Channel channel = testUser.createChannel(null, null);
        assertNotNull(channel); // Assuming nulls are allowed
        assertNull(channel.getChannelName());
        assertNull(channel.getChannelDescription());
    }


    @Test
    void testHasChannelAfterUnset() {
        Channel channel = testUser.createChannel("Initial Channel", "Desc");
        assertTrue(testUser.hasChannel());

        testUser.setChannel(null); // Assuming this is allowed
        assertFalse(testUser.hasChannel());
    }

    @Test
    void testChannelOwnerIsConsistent() {
        Channel sharedChannel = new Channel("Shared Channel", testUser, "Description");
        User secondUser = new User("seconduser", "second@example.com", "pass123");

        secondUser.setChannel(sharedChannel); // If allowed
        assertEquals(testUser, sharedChannel.getOwner()); // Owner should still be testUser
        assertEquals(sharedChannel, secondUser.getChannel());
    }

}