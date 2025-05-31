import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class VideoTest {
    private Channel testChannel;
    private Video testVideo;

    @BeforeEach
    public void setUp() {
        // Replace 'null' with a real User if your Channel constructor requires it
        User testOwner = new User("testuser", "password", "test@example.com");
        testChannel = new Channel("Test Channel", testOwner, "A test channel");
        testVideo = new Video("Test Video", "Test Description", 120, 20250101, testChannel);
    }

    @Test
    public void testVideoCreation() {
        assertNotNull(testVideo);
        assertEquals("Test Video", testVideo.getTitle());
        assertEquals("Test Description", testVideo.getDescription());
        assertEquals(120, testVideo.getDuration());
        assertEquals(20250101, testVideo.getDateUploaded());
        assertEquals(testChannel, testVideo.getChannel());
        assertEquals(0, testVideo.getViewCount());
        assertEquals(0, testVideo.getLikeCount());
    }

   @Test
public void testVideoInfo() {
    String expectedInfo = "Video ID: " + testVideo.getVideoID() +
            "\nTitle: " + testVideo.getTitle() +
            "\nDescription: " + testVideo.getDescription() +
            "\nDuration: " + testVideo.getDuration() + " seconds" +
            "\nUploaded on: " + testVideo.getDateUploaded() +
            "\nChannel: " + testChannel.getChannelName() +
            "\nViews: " + testVideo.getViewCount() +
            "\nLikes: " + testVideo.getLikeCount() +
            "\nComments: " + testVideo.getComments().size();

    assertEquals(expectedInfo, testVideo.getVideoInfo());
}
}
