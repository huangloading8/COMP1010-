package src;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Predefined users (already registered)
        ArrayList<User> users = new ArrayList<>();
        users.add(User.createAccount("alice", "alice@example.com", "pass123"));
        users.add(User.createAccount("bob", "bob@example.com", "bobpass"));
        users.add(User.createAccount("charlie", "charlie@example.com", "charliepw"));

        // Create and store predefined channels
        ArrayList<Channel> channels = new ArrayList<>();

        // Predefined channels and educational videos
        Channel aliceChannel = users.get(0).createChannel("Alice's Code Corner", "Tutorials from a Macquarie IT student");
        aliceChannel.uploadVideo(new Video("Intro to Python Programming", "COMP1010 crash course for beginners", 600, 20250401, aliceChannel));
        aliceChannel.uploadVideo(new Video("Understanding Data Types in Java", "COMP1250 explained with examples", 540, 20250402, aliceChannel));
        channels.add(aliceChannel);

        Channel bobChannel = users.get(1).createChannel("Bob Learns Tech", "Documenting my journey through computing at Macquarie");
        bobChannel.uploadVideo(new Video("Data Science with R", "STAT1170 tips and tricks", 480, 20250403, bobChannel));
        bobChannel.uploadVideo(new Video("MySQL Basics", "COMP1350 – Writing your first queries", 420, 20250404, bobChannel));
        channels.add(bobChannel);

        Channel charlieChannel = users.get(2).createChannel("Charlie Builds Stuff", "Web Dev, Projects, and Tutorials");
        charlieChannel.uploadVideo(new Video("Intro to Web Development", "COMP115 – HTML, CSS, and JS basics", 550, 20250405, charlieChannel));
        charlieChannel.uploadVideo(new Video("How to Use GitHub for Assignments", "Version control tips for COMP units", 360, 20250406, charlieChannel));
        channels.add(charlieChannel);

      // CSVUtils.exportAllVideosToCSV(channels); // added this: exports all videos from scratch, comment out affter first run

        // Login Feature
        System.out.println("=== Welcome to MqTube! ===");
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

        // After login
        boolean exit = false;
        while (!exit) {
            System.out.println("\nWhat would you like to do?");
            System.out.println(" 1. View Videos");
            System.out.println(" 2. View Playlist");
            System.out.println(" 3. Upload Video");
            System.out.println(" 4. Delete Video");
            System.out.println(" 5. Edit Playlist");
            System.out.println(" 6. Exit MqTube");

            System.out.print("Enter the number for your action: ");
            String answer = scanner.nextLine();
            int action = Integer.parseInt(answer);

            if (action == 1) {
                // View Videos
                System.out.println("\n=== All MqTube Videos ===");
                for (Channel c : channels) {
                    for (Video v : c.getVideos()) {
                        System.out.println("Title: " + v.getTitle());
                        System.out.println("Description: " + v.getDescription());
                        System.out.println("Channel: " + c.getChannelName());
                        System.out.println("Duration: " + v.getDuration() + " seconds");
                        System.out.println("Uploaded on: " + v.getDateUploaded());
                        System.out.println("-----------");
                    }
                }
            } else if (action == 2) {
                System.out.println("Playlist feature not yet implemented.");
            } else if (action == 3) {
                // Upload video
                System.out.print("Enter channel name: ");
                String channelName = scanner.nextLine();
                System.out.print("Enter channel description: ");
                String channelDesc = scanner.nextLine();
                Channel newChannel = loggedInUser.createChannel(channelName, channelDesc);
                channels.add(newChannel);

                System.out.print("Enter video title: ");
                String title = scanner.nextLine();
                System.out.print("Enter video description: ");
                String desc = scanner.nextLine();
                System.out.print("Enter video duration in seconds: ");
                int duration = Integer.parseInt(scanner.nextLine());
                System.out.print("Enter today's date (YYYYMMDD): ");
                int date = Integer.parseInt(scanner.nextLine());

                int newId = newChannel.getVideos().size() + 1 + 100; // simple ID logic
                Video newVideo = new Video(title, desc, duration, date, newChannel);
                newChannel.uploadVideo(newVideo);

               CSVUtils.appendVideoToCSV(newVideo); // added this: adds newly uploaded video to exisitng CSV 

                System.out.println("Video uploaded.");


            } else if (action == 4) {
                System.out.println("Enter the video ID to remove:");
                int videoID = Integer.parseInt(scanner.nextLine());
            
                boolean videoFound = false;
                for (Channel c : channels) {
                    // Only allow deletion if the logged-in user owns the channel
                    if (c.getOwner().equals(loggedInUser)) {
                        if (c.removeVideo(videoID)) {
                            videoFound = true;
                            break;
                        }
                    }
                }
            
                if (!videoFound) {
                    System.out.println("No video with ID " + videoID + " found in your channels.");
                }
            } else if (action == 5) {
                System.out.println("Edit playlist feature not yet implemented.");
            } else if (action == 6) {
                exit = true;
                System.out.println("Goodbye from MqTube!");
            } else {
                System.out.println("Invalid option. Try again.");
            }
        }

        scanner.close();
    }
}

