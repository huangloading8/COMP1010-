package src;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.util.ArrayList;

class CSVUtilsTest {
    private User testUser;
    private Channel testChannel;
    private Video testVideo;

    @BeforeEach
    void setUp() {
        testUser = new User("testuser", "test@example.com", "password");
        testChannel = new Channel("Test Channel", testUser, "Test Description");
        testVideo = new Video("Test Video", "Test Description", 120, 20250101, testChannel);
        
        // Clean up any existing test files
        File testFile = new File("testuser_videos.csv");
        if (testFile.exists()) {
            testFile.delete();
        }
    }

    @Test
    void testAppendAndLoadVideos() {
        // Append a video
        CSVUtils.appendVideoToCSV(testVideo);
        
        // Load videos
        ArrayList<Video> loadedVideos = CSVUtils.loadVideosForUser(testChannel);
        
        assertEquals(1, loadedVideos.size());
        Video loadedVideo = loadedVideos.get(0);
        
        assertEquals(testVideo.getTitle(), loadedVideo.getTitle());
        assertEquals(testVideo.getDescription(), loadedVideo.getDescription());
        assertEquals(testVideo.getDuration(), loadedVideo.getDuration());
        assertEquals(testVideo.getDateUploaded(), loadedVideo.getDateUploaded());
        assertEquals(testChannel.getChannelName(), loadedVideo.getChannel().getChannelName());
    }

    @Test
    void testRemoveVideoFromCSV() {
        // Add two videos
        CSVUtils.appendVideoToCSV(testVideo);
        Video secondVideo = new Video("Second Video", "Second Desc", 180, 20250102, testChannel);
        CSVUtils.appendVideoToCSV(secondVideo);
        
        // Remove first video
        CSVUtils.removeVideoFromCSV(testVideo);
        
        // Load remaining videos
        ArrayList<Video> loadedVideos = CSVUtils.loadVideosForUser(testChannel);
        
        assertEquals(1, loadedVideos.size());
        assertEquals(secondVideo.getTitle(), loadedVideos.get(0).getTitle());
    }

    @Test
    void testLoadVideosForNonExistentFile() {
        ArrayList<Video> loadedVideos = CSVUtils.loadVideosForUser(testChannel);
        assertTrue(loadedVideos.isEmpty());
    }
}