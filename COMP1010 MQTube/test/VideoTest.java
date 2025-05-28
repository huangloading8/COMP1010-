package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import src.Channel;
import src.User;
import src.Video;

public class VideoTest {
    private Channel testChannel;
    private Video testVideo;

   
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
                "\nChannel: " + testVideo.getChannel().getChannelName() +
                "\nViews: " + testVideo.getViewCount() +
                "\nLikes: " + testVideo.getLikeCount() +
                "\nComments: " + testVideo.getComments().size();
        
        assertEquals(expectedInfo, testVideo.getVideoInfo());
    }
}