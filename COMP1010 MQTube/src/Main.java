package src;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Predefined users (already registered): 
        ArrayList<User> users = new ArrayList<>();
        users.add(User.createAccount("alice", "alice@example.com", "pass123"));
        users.add(User.createAccount("bob", "bob@example.com", "bobpass"));
        users.add(User.createAccount("charlie", "charlie@example.com", "charliepw"));

        //Login Feature:
        System.out.println("=== Welcome to MqTube ===");
        System.out.println("Please log in with your username or email.");

        User loggedInUser = null;

        while (loggedInUser == null) {
            System.out.print("Enter username or email: ");
            String identifier = scanner.nextLine();

            System.out.print("Enter password: ");
            String password = scanner.nextLine();

            for (User user : users) {
                if (user.login(identifier, password)) {
                    loggedInUser = user;
                    break;
                }
            }

            if (loggedInUser == null) {
                System.out.println("Login failed. Please try again.\n");
            }
        }

        // After user is logged in: 
        System.out.print("Enter channel name: ");
        String channelName = scanner.nextLine();

        System.out.print("Enter channel description: ");
        String channelDescription = scanner.nextLine();

        Channel channel = loggedInUser.createChannel(channelName, channelDescription);
        System.out.println("Channel created.\n");

        // Upload video
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

        // Create playlist
        System.out.print("Enter playlist name: ");
        String playlistName = scanner.nextLine();

        Playlist playlist = new Playlist(playlistName, loggedInUser);
        playlist.addVideo(video);
        System.out.println("Playlist created and video added.\n");

        // Add comment
        System.out.print("Enter comment content: ");
        String commentContent = scanner.nextLine();

        Comment comment = new Comment("c1", loggedInUser.getUsername(), loggedInUser.getEmail(), commentContent, dateUploaded, video);
        video.addComment(comment);
        System.out.println("Comment added.\n");

        // Summary
        System.out.println("\n===== MqTube Summary =====");
        System.out.println("User: " + loggedInUser.getUsername());
        System.out.println("Channel: " + channel.getChannelName());
        System.out.println("Uploaded Video: " + video.getTitle());
        System.out.println("Playlist: " + playlist.getPlaylistName());
        System.out.println("Comment: " + comment.getContent());

        scanner.close();
    }
}

