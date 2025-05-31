import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class Test_Channel {

    private Channel channel;
    private User user;

    @BeforeEach
    public void setUp() {
        user = new User("alice", "alice@students.mq.edu.au", "pass123");
        channel = new Channel("Alice's Channel", user, "Fun and Learning");
    }

    @Test
    public void testUploadVideo() {
        Video video = new Video("Intro Video", "Intro Description", 300, 20250601, channel);
        channel.uploadVideo(video);

        ArrayList<Video> videos = channel.getVideos();
        assertEquals(1, videos.size());
        assertEquals("Intro Video", videos.get(0).getTitle());
    }

    @Test
    public void testCreatePlaylist_UnderLimit() {
        Playlist playlist = channel.createPlaylist("My Favorites");
        assertNotNull(playlist);
        assertEquals(1, channel.getPlaylists().size());
        assertEquals("My Favorites", channel.getPlaylists().get(0).getPlaylistName());
    }

    @Test
    public void testCreatePlaylist_OverLimit() {
        for (int i = 1; i <= 5; i++) {
            channel.createPlaylist("Playlist " + i);
        }

        Playlist extra = channel.createPlaylist("Extra Playlist");
        assertNull(extra);
        assertEquals(5, channel.getPlaylists().size());
    }

    @Test
    public void testRemovePlaylist_Success() {
        channel.createPlaylist("To Delete");
        boolean removed = channel.removePlaylist("To Delete");

        assertTrue(removed);
        assertEquals(0, channel.getPlaylists().size());
    }

    @Test
    public void testRemovePlaylist_NotFound() {
        boolean removed = channel.removePlaylist("Nonexistent");
        assertFalse(removed);
    }

    @Test
    public void testRemoveVideo_Success() {
        Video video = new Video("Cool Video", "Nice video", 300, 20250601, channel);
        channel.uploadVideo(video);

        boolean removed = channel.removeVideo(video.getId());
        assertTrue(removed);
        assertEquals(0, channel.getVideos().size());
    }

    @Test
    public void testRemoveVideo_NotFound() {
        boolean removed = channel.removeVideo(999);
        assertFalse(removed);
    }

    @Test
    public void testGettersAndSetters() {
        assertEquals("Alice's Channel", channel.getChannelName());
        assertEquals(user, channel.getOwner());
        assertEquals("Fun and Learning", channel.getChannelDescription());

        channel.setChannelName("New Name");
        channel.setDescription("Updated Desc");

        assertEquals("New Name", channel.getChannelName());
        assertEquals("Updated Desc", channel.getChannelDescription());
    }
}
