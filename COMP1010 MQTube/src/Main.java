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
        users.add(User.createAccount("diana", "diana@example.com", "dianapass"));

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

        // Predefined playlists & adding videos to playlists - example with aliceChannel
        

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
            System.out.println(" 6. Edit Channel");
            System.out.println(" 7. Search Videos");
            System.out.println(" 8. Exit MqTube");

            System.out.print("Enter the number for your action: ");
            
            String answer = scanner.nextLine();

            int action;
            try {
                action = Integer.parseInt(answer);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number (e.g. 1, 2, 3...).");
                continue; // skip the rest of the loop and ask again
            }

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
            } else if (action == 3) {
                Channel userChannel = loggedInUser.getChannel();
    
                if (userChannel == null) {
                    System.out.println("You need to create a channel before uploading a video.");
                } else {
                    System.out.print("Enter video title: ");
                    String title = scanner.nextLine();
            
                    System.out.print("Enter video description: ");
                    String desc = scanner.nextLine();
            
                    System.out.print("Enter video duration in seconds: ");
                    int duration = Integer.parseInt(scanner.nextLine());
            
                    System.out.print("Enter today's date (YYYYMMDD): ");
                    int date = Integer.parseInt(scanner.nextLine());
            
                    int newId = userChannel.getVideos().size() + 1 + 100; // simple ID logic
                    Video newVideo = new Video(title, desc, duration, date, userChannel);
                    userChannel.uploadVideo(newVideo);
            
                    CSVUtils.appendVideoToCSV(newVideo);
            
                    System.out.println("Video uploaded successfully with ID: " + newVideo.getId());
                }

            } else if (action == 4) {
                System.out.println("Enter the video ID to remove:");
                int videoID = Integer.parseInt(scanner.nextLine());
            
                boolean videoFound = false;
                for (Channel c : channels) {
                    // Only allow deletion if the logged-in user owns the channel
                    if (c.getOwner().equals(loggedInUser)) {
                        Video videoToRemove = c.getVideoById(videoID); // You must implement this method
                        if (videoToRemove != null) {
                            c.removeVideo(videoID);
                            CSVUtils.removeVideoFromCSV(videoToRemove);
                            System.out.println("Video deleted and CSV updated.");
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
            } else if (action == 8) {
                exit = true;
                System.out.println("Goodbye from MqTube!");
            } else if (action == 6) {
                // Edit Channel (create or update)
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
                    if (!newName.isEmpty()) {
                        userChannel.setChannelName(newName);
                    }
                    System.out.print("Enter new channel description (leave blank to keep current): ");
                    String newDesc = scanner.nextLine();
                    if (!newDesc.isEmpty()) {
                        userChannel.setDescription(newDesc);
                    }
                    System.out.println("Channel updated successfully.");
                }
            } else if (action == 7) {
                // Search Videos
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
            } else if (action == 8) {
                // Exit
                exit = true;
                System.out.println("Goodbye from MqTube!");
            } else {
                System.out.println("Invalid option. Try again.");
            }
        }

        scanner.close();
    }
}

