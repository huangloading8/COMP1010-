import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;

/*
 * This is the main class, which handles the core logic for user management,
 * video uploading, playlist editing, and menu interaction.
 * In other words, this contains the code for all user to use, or interact, with the platform
 */

public class MQTubeManager {
    private ArrayList<User> users;
    private ArrayList<Channel> channels;
    private Scanner scanner;

    // COMMENT LATER
    public MQTubeManager() {
        users = new ArrayList<>();
        channels = new ArrayList<>();
        scanner = new Scanner(System.in);

        loadUsersFromCSV(); // Load and initialize users from the CSV data files
        initializeData();
    }

    /*
     * Add some pre-defined users and channels for demonstration and testing purposes
     * Sample Videos and Playlists are added to the channels 
     */
    private void initializeData() {
        // Predefined users
        users.add(User.createAccount("alice", "alice@students.mq.edu.au", "pass123"));
        users.add(User.createAccount("bob", "bob@mq.edu.au", "bobpass"));
        users.add(User.createAccount("charlie", "charlie@students.mq.edu.au", "charliepw"));
        users.add(User.createAccount("diana", "diana@students.mq.edu.au", "dianapass"));
        users.add(User.createAccount("evelane", "evelane@students.mq.edu.au", "trixieissocool"));

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

        // Create & Add Videos to Playlists
        // Alice's Playlist
        aliceChannel.createPlaylist("Beginner Coding");
        Playlist alicePlaylist = aliceChannel.getPlaylists().get(0);  // Get the newly created playlist
        alicePlaylist.addVideoToPlaylist(aliceChannel.getVideos().get(0)); // Intro to Python
        alicePlaylist.addVideoToPlaylist(aliceChannel.getVideos().get(1)); // Data Types in Java

        // Bob's Playlist
        bobChannel.createPlaylist("Bob's Favourites");
        Playlist bobPlaylist = bobChannel.getPlaylists().get(0);
        bobPlaylist.addVideoToPlaylist(bobChannel.getVideos().get(1)); // MySQL Basics

        // Charlie's Playlist
        charlieChannel.createPlaylist("Web Dev Essentials");
        Playlist charliePlaylist = charlieChannel.getPlaylists().get(0);
        charliePlaylist.addVideoToPlaylist(charlieChannel.getVideos().get(0)); // Intro to Web Dev
        charliePlaylist.addVideoToPlaylist(charlieChannel.getVideos().get(1)); // GitHub for Assignments
    }

    // Method for loading users from CSV data files to the program
    private void loadUsersFromCSV() {
    String csvFile = "data/users.csv";
    String line;
    String csvSplitBy = ",";

    File file = new File(csvFile);
    if (!file.exists()) {
        System.out.println("[INFO] No existing users.csv file found. Skipping load.");
        return; // Skip loading if the file doesn't exist
    }

    try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
        // Skip the header line if present
        br.readLine();
        while ((line = br.readLine()) != null) {
            String[] userData = line.split(csvSplitBy);
            if (userData.length == 3) {
                String username = userData[0].trim();
                String email = userData[1].trim();
                String password = userData[2].trim();

                // Create user and add to list
                User user = User.createAccount(username, email, password);
                users.add(user);
            }
        }
    } catch (IOException e) {
        System.out.println("Error reading users from CSV: " + e.getMessage());
    }
}

        // Login Feature
       //CSVUtils.exportAllVideosToCSV(channels); // added this: exports all videos from scratch, comment out affter first run

    /*
     * This is the entry point for running the program after opening
     * It will handle the display menu, as well as the users actions menu
     */
    public void run() {
        System.out.println("=== Welcome to MqTube! ===");
        System.out.println("Please log in with your username or email.");

        /*
         * Login method is called here
         * If logged in successfully, the actions menu will appear
         */
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
                editPlaylist(loggedInUser);
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

    /*
     * This class handles user login and sign-up flow with validation
     * Loops until successful login or account creation
     */   
    private User login() {
        User loggedInUser = null;

        while (loggedInUser == null) {
            System.out.println("Welcome to MQTube!");
            System.out.println("[1] Log In");
            System.out.println("[2] Sign Up");
            System.out.println("[3] Exit MQTube");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine().trim();

            if (choice.equals("1")) {
                // Users are required to enter their username or email and password to login
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
                
                // If the user can not be found, then an error message will appear
                if (loggedInUser == null) {
                    System.out.println("Login failed. Please try again.\n");
                }

            } else if (choice.equals("2")) {
                // Users can sign up using MQ email with a password
                System.out.print("Enter new username: ");
                String username = scanner.nextLine();

                System.out.print("Enter email (must end with @mq.edu.au or @students.mq.edu.au): ");
                String email = scanner.nextLine();

                if (!email.endsWith("@mq.edu.au") && !email.endsWith("@students.mq.edu.au")) {
                    System.out.println("Only Macquarie University emails are valid (@mq.edu.au or @students.mq.edu.au)");
                    continue;
                }

                System.out.print("Enter password: ");
                String password = scanner.nextLine();

                // Optional: Check if username or email is already taken
                boolean exists = false;
                for (User user : users) {
                    if (user.getUsername().equalsIgnoreCase(username) || 
                        user.getEmail().equalsIgnoreCase(email)) {
                        exists = true;
                        break;
                    }
                }

                if (exists) {
                    System.out.println("Username or email already exists. Please try again.\n");
                } else {
                    loggedInUser = User.createAccount(username, email, password);
                    users.add(loggedInUser);
                    saveUserToCSV(loggedInUser); // User information will be saved to CSV data files
                    System.out.println("Account created successfully. You are now logged in as " + username + "!\n");
                }

                } else if (choice.equals("3")) {
                    System.out.println("Goodbye from MQTube!");
                    System.exit(0); // exits the entire program
                } else {
                    System.out.println("Invalid option. Please enter 1, 2, or 3.\n");
                }
                        }

        return loggedInUser;
    }

    //  Appends a newly registered user's data to the CSV data file
    private void saveUserToCSV(User user) {
        try (FileWriter writer = new FileWriter("data/users.csv", true)) { // true = append mode
            String line = String.format("%s,%s,%s\n", user.getUsername(), user.getEmail(), user.getPassword());
            writer.write(line);
        } catch (IOException e) {
            System.out.println("Error saving user to file: " + e.getMessage());
        }
    }

    // This method displays the list of all user actions
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

    /*
     * This method displays all videos available across all channels
     * It shows video title, description, duration, upload date, channel and user who owns it
     */
    private void viewAllVideos() {
        System.out.println("\n=== All MqTube Videos ===");
        for (Channel c : channels) {
            User owner = c.getOwner();
            System.out.println("Channel: " + c.getChannelName() + 
                            " (Owner: " + owner.getUsername() + 
                            " - " + owner.getUserType() + ")");
            for (Video v : c.getVideos()) {
                System.out.println("VideoID: " + v.getId());
                System.out.println("Title: " + v.getTitle());
                System.out.println("Description: " + v.getDescription());
                System.out.println("Duration: " + v.getDuration() + " seconds");
                System.out.println("Uploaded on: " + v.getDateUploaded());
                System.out.println("-----------");
            }
        }
    }

    /*
     * This method shows all users' playlists, including which user and channel they belong to 
     * The number of videos in the playlist are shown, as well as videos information
     */
    private void viewPlaylists() {
        System.out.println("\n=== All MQTube Playlists ===");

        for (User u : users) {
            System.out.println("User: " + u.getUsername() + " (" + u.getUserType() + ")");

            Channel channel = u.getChannel();
            if (channel == null) {
                System.out.println("  No channel.");
                continue;
            }

            System.out.println("Channel: " + channel.getChannelName());
            ArrayList<Playlist> playlists = channel.getPlaylists();

            if (playlists.isEmpty()) {
                System.out.println("  No playlists.");
            } else {
                for (Playlist p : playlists) {
                    System.out.println("  Playlist: " + p.getPlaylistName() +
                   " (" + p.countPlaylistVideos(p.getStart()) + " videos)");

                    DNode current = p.getStart();
                    if (current == null) {
                        System.out.println("    (Empty Playlist)");
                    }

                    while (current != null) {
                        Video v = current.getVideo();
                        System.out.println("    VideoID: " + v.getId());
                        System.out.println("    Title: " + v.getTitle());
                        System.out.println("    Description: " + v.getDescription());
                        System.out.println("    Channel: " + v.getChannel().getChannelName());
                        System.out.println("    Duration: " + v.getDuration() + " seconds");
                        System.out.println("    Uploaded on: " + v.getDateUploaded());
                        System.out.println("    ---------");
                        current = current.getNext();
                    }
                }
            }
            System.out.println(); // space between users
        }
    }

    /*
     * This method allow a logged-in user to upload a video by entering video details
     * The video is then uploaded to the channel and will be loaded into the CSV data file
     */
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

    int duration = -1;
    while (duration < 0) {
        System.out.print("Enter video duration in seconds: ");
        try {
            duration = Integer.parseInt(scanner.nextLine());
            if (duration < 0) {
                System.out.println("Duration must be a positive number.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid number for duration.");
        }
    }

    int date = -1;
    while (date < 0) {
        System.out.print("Enter today's date (YYYYMMDD): ");
        try {
            date = Integer.parseInt(scanner.nextLine());
            if (date < 0) {
                System.out.println("Date must be a positive number.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid date (numbers only, e.g., 20250531).");
        }
    }

    Video newVideo = new Video(title, desc, duration, date, userChannel);
    userChannel.uploadVideo(newVideo);
    CSVUtils.appendVideoToCSV(newVideo);

    System.out.println("Video uploaded successfully with ID: " + newVideo.getId());
}


    /*
     * Users can delete a video from their channel
     * The deleted video will also be removed from the CSV data file
     */
    private void deleteVideo(User loggedInUser) {
        System.out.print("Enter the video ID to remove: ");
        int videoID = Integer.parseInt(scanner.nextLine());

        Channel userChannel = loggedInUser.getChannel();
        if (userChannel == null) {
            System.out.println("Video ID " + videoID + " not found.");
            return;
        }

        if (userChannel.removeVideoFromChannel(videoID)) {
            System.out.println("Video ID " + videoID + " has been removed.");
        } else {
            System.out.println("No video with ID " + videoID + " found in your channel.");
        }
    }

    /*
     * This is the option to show the menu for editing the playlist
     * Users can view playlists from their channels, as well as
     * adding or removing videos from the playlist
     */
    private void editPlaylist(User loggedInUser) {
        Channel userChannel = loggedInUser.getChannel();

        if (userChannel == null) {
            System.out.println("You need a channel before editing playlists.");
            return;
        }

        ArrayList<Playlist> playlists = userChannel.getPlaylists();

        System.out.println("\n=== Playlist Menu ===");
        System.out.println("1. View Playlists");
        System.out.println("2. Add Video to Playlist");
        System.out.println("3. Remove Video from Playlist");
        System.out.print("Enter your option (1/2/3): ");
        int option = Integer.parseInt(scanner.nextLine());

        if (option == 1) {
            System.out.println("\n--- View Playlists ---");
            if (playlists.isEmpty()) {
            System.out.println("There are no playlists.");
            } else {
                for (int i = 0; i < playlists.size(); i++) {
                    System.out.println((i + 1) + ". " + playlists.get(i).getPlaylistName());
                }
            }
        } else if (option == 2) {
            if (playlists.isEmpty()) {
                System.out.println("There is no playlist.");
                return;
            }
            System.out.println("\n--- Add Video to Playlist ---");
            // Show playlist choices
            System.out.println("Select a playlist by number:");

            for (int i = 0; i < playlists.size(); i++) {
                System.out.println((i + 1) + ". " + playlists.get(i).getPlaylistName());
            }

            System.out.print("Enter your choice: ");
            int choice = Integer.parseInt(scanner.nextLine());

            if (choice < 1 || choice > playlists.size()) {
                System.out.println("Invalid playlist choice.");
                return;
            }
            Playlist target = playlists.get(choice - 1);

            System.out.println("Available videos in your channel:");
            for (Video v : userChannel.getVideos()) {
                System.out.println("[" + v.getId() + "] " + v.getTitle());
            }

            System.out.print("Enter Video ID to add: ");
            int videoID = Integer.parseInt(scanner.nextLine());
            Video video = userChannel.getVideoById(videoID);

            if (video != null) {
                target.addVideoToPlaylist(video);
                System.out.println("Video added to playlist.");
            } else {
                System.out.println("Invalid video ID.");
            }
        } else if (option == 3) {
            if (playlists.isEmpty()) {
                System.out.println("There is no playlist.");
                return;
            }
            System.out.println("\n--- Remove Video from Playlist ---");
            // Show playlist choices
            System.out.println("Select a playlist by number:");

            for (int i = 0; i < playlists.size(); i++) {
                System.out.println((i + 1) + ". " + playlists.get(i).getPlaylistName());
            }

            System.out.print("Enter your choice: ");
            int choice = Integer.parseInt(scanner.nextLine());

            if (choice < 1 || choice > playlists.size()) {
                System.out.println("Invalid playlist choice.");
                return;
            }
            Playlist target = playlists.get(choice - 1);
            DNode current = target.getStart();
            
            System.out.println("Videos in this playlist:");
            if (current == null) {
                System.out.println("Playlist is empty.");
                return;
            }

            while (current != null) {
                Video v = current.getVideo();
                System.out.println("[" + v.getId() + "] " + v.getTitle());
                current = current.getNext();
            }

            System.out.print("Enter Video ID to remove: ");
            int videoID = Integer.parseInt(scanner.nextLine());

            // Find DNode with this video ID
            current = target.getStart();
            DNode toRemove = null;
            while (current != null) {
                if (current.getVideo().getId() == videoID) {
                    toRemove = current;
                    break;
                }
                current = current.getNext();
            }

            if (toRemove != null) {
                target.removeVideoFromPlaylist(toRemove);
                System.out.println("Video removed from playlist.");
            } else {
                System.out.println("Video ID not found in playlist.");
            }
        } else {
            System.out.println("Invalid option. Please enter 1, 2, or 3.");
        }
    }

    /*
     * This method allows user to edit their channel (name, description)
     * If they don't have a channel, this will create one
     */
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
    }

    /*
     * Users can search all videos and channels by keywords
     * It will match with title, description or channel names
     */
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

