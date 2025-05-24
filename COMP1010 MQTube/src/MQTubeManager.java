package src;

import java.util.ArrayList;
import java.util.Scanner;

public class MQTubeManager {
    private ArrayList<User> users;
    private ArrayList<Channel> channels;
    private Scanner scanner;

    public MQTubeManager() {
        users = new ArrayList<>();
        channels = new ArrayList<>();
        scanner = new Scanner(System.in);
        initializeData();
    }

    private void initializeData() {
        // Predefined users
        users.add(User.createAccount("alice", "alice@example.com", "pass123"));
        users.add(User.createAccount("bob", "bob@example.com", "bobpass"));
        users.add(User.createAccount("charlie", "charlie@example.com", "charliepw"));
        users.add(User.createAccount("diana", "diana@example.com", "dianapass"));
        users.add(User.createAccount("evelane", "evelane@example.com", "trixieissocool"));

        // Channels & videos
        Channel aliceChannel = users.get(0).createChannel("Alice's Code Corner", "Tutorials from a Macquarie IT student");
        aliceChannel.uploadVideo(new Video("Intro to Python Programming", "COMP1010 crash course", 600, 20250401, aliceChannel));
        aliceChannel.uploadVideo(new Video("Data Types in Java", "COMP1250 examples", 540, 20250402, aliceChannel));
        channels.add(aliceChannel);

        Channel bobChannel = users.get(1).createChannel("Bob Learns Tech", "Computing at Macquarie");
        bobChannel.uploadVideo(new Video("Data Science with R", "STAT1170 tips", 480, 20250403, bobChannel));
        bobChannel.uploadVideo(new Video("MySQL Basics", "COMP1350 queries", 420, 20250404, bobChannel));
        channels.add(bobChannel);

        Channel charlieChannel = users.get(2).createChannel("Charlie Builds Stuff", "Web Dev, Projects, Tutorials");
        charlieChannel.uploadVideo(new Video("Intro to Web Dev", "COMP115 – HTML, CSS", 550, 20250405, charlieChannel));
        charlieChannel.uploadVideo(new Video("GitHub for Assignments", "Version control tips", 360, 20250406, charlieChannel));
        channels.add(charlieChannel);
    }

    public void run() {
        System.out.println("=== Welcome to MqTube! ===");
        System.out.println("Please log in with your username or email.");

        User loggedInUser = login();

        boolean exit = false;
        
        while (!exit) {
            showMenu();
            int action = getUserAction();
        
            if (action == 1) {
                viewAllVideos();
            } else if (action == 2) {
                viewPlaylists();
            } else if (action == 3) {
                uploadVideo(loggedInUser);
            } else if (action == 4) {
                deleteVideo(loggedInUser);
            } else if (action == 5) {
                System.out.println("Edit playlist feature not yet implemented.");
            } else if (action == 6) {
                editChannel(loggedInUser);
            } else if (action == 7) {
                searchVideos();
            } else if (action == 8) {
                System.out.println("Goodbye from MqTube!");
                exit = true;
            } else {
                System.out.println("Invalid option. Try again.");
            }
        }
        
        scanner.close();
    }

    private User login() {
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

        return loggedInUser;
    }

    private void showMenu() {
        System.out.println("\nWhat would you like to do?");
        System.out.println(" 1. View Videos");
        System.out.println(" 2. View Playlist");
        System.out.println(" 3. Upload Video");
        System.out.println(" 4. Delete Video");
        System.out.println(" 5. Edit Playlist");
        System.out.println(" 6. Edit Channel");
        System.out.println(" 7. Search Videos");
        System.out.println(" 8. Exit MqTube");
        System.out.print("Enter the number for your action: ");
    }

    private int getUserAction() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
            return -1;
        }
    }

    private void viewAllVideos() {
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
    }

    private void viewPlaylists() {
        System.out.println("\n===My Playlists===");
        for (User u : users) {
            System.out.println("User: " + u.getUsername());
            for (Channel c : channels) {
                System.out.println("Channel: " + c.getChannelName());
                for (Playlist p : u.getPlaylists()) {
                    System.out.println("Playlist: " + p.getPlaylistName());
                    DNode current = p.getStart();
                    while (current != null) {
                        Video v = current.getVideo();
                        System.out.println("  Title: " + v.getTitle());
                        System.out.println("  Description: " + v.getDescription());
                        System.out.println("  Channel: " + v.getChannel().getChannelName());
                        System.out.println("  Duration: " + v.getDuration() + " seconds");
                        System.out.println("  Uploaded on: " + v.getDateUploaded());
                        System.out.println("  ---------");
                        current = current.getNext();
                    }
                }
            }
        }
    }

    private void uploadVideo(User loggedInUser) {
        Channel userChannel = loggedInUser.getChannel();

        if (userChannel == null) {
            System.out.println("You need to create a channel before uploading a video.");
            return;
        }

        System.out.print("Enter video title: ");
        String title = scanner.nextLine();

        System.out.print("Enter video description: ");
        String desc = scanner.nextLine();

        System.out.print("Enter video duration in seconds: ");
        int duration = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter today's date (YYYYMMDD): ");
        int date = Integer.parseInt(scanner.nextLine());

        Video newVideo = new Video(title, desc, duration, date, userChannel);
        userChannel.uploadVideo(newVideo);
        CSVUtils.appendVideoToCSV(newVideo);

        System.out.println("Video uploaded successfully with ID: " + newVideo.getId());
    }

    private void deleteVideo(User loggedInUser) {
        System.out.print("Enter the video ID to remove: ");
        int videoID = Integer.parseInt(scanner.nextLine());

        for (Channel c : channels) {
            if (c.getOwner().equals(loggedInUser)) {
                Video videoToRemove = c.getVideoById(videoID); // assumes this method exists
                if (videoToRemove != null) {
                    c.removeVideo(videoID);
                    CSVUtils.removeVideoFromCSV(videoToRemove);
                    System.out.println("Video deleted and CSV updated.");
                    return;
                }
            }
        }

        System.out.println("No video with ID " + videoID + " found in your channel.");
    }

    private void editChannel(User loggedInUser) {
        Channel userChannel = loggedInUser.getChannel();
        if (userChannel == null) {
            System.out.println("You don't have a channel yet. Let's create one.");
            System.out.print("Enter channel name: ");
            String newChannelName = scanner.nextLine();
            System.out.print("Enter channel description: ");
            String newChannelDesc = scanner.nextLine();
            Channel newChannel = loggedInUser.createChannel(newChannelName, newChannelDesc);
            channels.add(newChannel);
            System.out.println("Channel created successfully.");
        } else {
            System.out.println("Editing your channel: " + userChannel.getChannelName());
            System.out.print("Enter new channel name (leave blank to keep current): ");
            String newName = scanner.nextLine();
            if (!newName.isEmpty()) userChannel.setChannelName(newName);

            System.out.print("Enter new channel description (leave blank to keep current): ");
            String newDesc = scanner.nextLine();
            if (!newDesc.isEmpty()) userChannel.setDescription(newDesc);

            System.out.println("Channel updated successfully.");
        }
    }

    private void searchVideos() {
        System.out.print("Enter keyword to search: ");
        String keyword = scanner.nextLine().toLowerCase();

        boolean found = false;
        System.out.println("\n=== Search Results for: \"" + keyword + "\" ===");
        for (Channel c : channels) {
            for (Video v : c.getVideos()) {
                if (v.getTitle().toLowerCase().contains(keyword) ||
                    v.getDescription().toLowerCase().contains(keyword) ||
                    c.getChannelName().toLowerCase().contains(keyword)) {

                    System.out.println("Title: " + v.getTitle());
                    System.out.println("Description: " + v.getDescription());
                    System.out.println("Channel: " + c.getChannelName());
                    System.out.println("Duration: " + v.getDuration() + " seconds");
                    System.out.println("Uploaded on: " + v.getDateUploaded());
                    System.out.println("-----------");
                    found = true;
                }
            }
        }

        if (!found) {
            System.out.println("No videos found matching your keyword.");
        }
    }
}
