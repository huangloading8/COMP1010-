package src;

import java.util.ArrayList;

/*
 * This class represents a MQTube User
 * It contains methods for operating on a user account
 */

public class User {
    private String username;
    private String email;
    private String password;
    private Channel channel;

    // This is the User constructor with essential information for a user
    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.channel = null;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    /*
     * Checks if the given password matches the user's password.
     * 
     * @param password password to check
     * @return true if passwords match, false otherwise
     */
    public boolean authenticate(String password) {
        return this.password.equals(password); // Simplified
    }

    // Create a new user account
    public static User createAccount(String username, String email, String password) {
        User newUser = new User(username, email, password);
        return newUser;
    }

    /*
     * This method is used to login the user using username or email and password.
     * It will print login status messages.
     * 
     * @param identifier username or email
     * @param password password to authenticate
     * @return true if login successful, false otherwise
     */
    public boolean login(String identifier, String password) {
        if (this.username.equals(identifier) || this.email.equals(identifier)) {
            if (authenticate(password)) {
                System.out.println("Login Success");
                System.out.println("=== Welcome, " + this.username + "!" + " ===");
                return true;
            } else {
                System.out.println("Wrong Password");
                return false;
            }
        }
        System.out.println("Username or Email not valid");
        return false;
    }
    
    /*
     * This method creates a new channel for the user if one doesn't already exist.
     * 
     * @param channelName name of the channel
     * @param channelDescription description of the channel
     * @return the created or existing Channel object
     */
    public Channel createChannel(String channelName, String channelDescription) {
        if (this.channel == null) {
            this.channel = new Channel(channelName, this, channelDescription);
        } else {
            System.out.println("You already have a channel: " + channel.getChannelName());
        }
        return this.channel;
    }
    
    public Channel getChannel() {
        return this.channel;
    }
    
    // This method checks if the user has any channels. If the user has none, it returns null
    public boolean hasChannel() {
        return this.channel != null;
    }

    public ArrayList<Playlist> playlists = new ArrayList<>();

    // This method creates a new playlist and add to the user's playlist list
    public Playlist createPlaylist(String playlistName) {
    Playlist newPlaylist = new Playlist(playlistName, this);
    playlists.add(newPlaylist);
    return newPlaylist;
    }

    public ArrayList<Playlist> getPlaylists() {
        return playlists;
    }

    public String getPassword() {
        return password;
    }
}