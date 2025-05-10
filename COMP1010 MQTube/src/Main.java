package src;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 1. Register user
        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        System.out.print("Enter email: ");
        String email = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        User newUser = User.createAccount(username, email, password);
        System.out.println("User account created.\n");

        // 2. Create channel
        System.out.print("Enter channel name: ");
        String channelName = scanner.nextLine();

        System.out.print("Enter channel description: ");
        String channelDescription = scanner.nextLine();

        Channel channel = new Channel(channelName, newUser, channelDescription);
        System.out.println("Channel created.\n");

        // 3. Upload a video
        System.out.print("Enter video title: ");
        String videoTitle = scanner.nextLine();

        System.out.print("Enter video description: ");
        String videoDesc = scanner.nextLine();

        System.out.print("Enter video duration in seconds: ");
        int duration = scanner.nextInt();

        System.out.print("Enter today's date (YYYYMMDD): ");
        int dateUploaded = scanner.nextInt();
        scanner.nextLine(); // clear newline

        Video video = new Video(1, videoTitle, videoDesc, duration, dateUploaded, channel);
        channel.uploadVideo(video);
        System.out.println("Video uploaded.\n");

        // 4. Create playlist and add video
        System.out.print("Enter playlist name: ");
        String playlistName = scanner.nextLine();

        Playlist playlist = new Playlist(playlistName, user);
        playlist.addVideo(video);
        System.out.println("Playlist created and video added.\n");

        // 5. Add a comment
        System.out.print("Enter comment content: ");
        String commentContent = scanner.nextLine();

        Comment comment = new Comment("c1", user.getUsername(), user.getEmail(), commentContent, dateUploaded, video);
        video.addComment(comment);
        System.out.println("Comment added.\n");

        // Summary output
        System.out.println("\n===== MqTube Summary =====");
        System.out.println("User: " + user.getUsername());
        System.out.println("Channel: " + channel.getChannelName());
        System.out.println("Uploaded Video: " + video.getTitle());
        System.out.println("Playlist: " + playlist.getPlaylistName());
        System.out.println("Comment: " + comment.getContent());

        scanner.close();
    }
}
