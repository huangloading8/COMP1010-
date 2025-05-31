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
        return new User(username, email, password);
    }

    //getting user type base on their email
     public String getUserType() {
        if (email.endsWith("@mq.edu.au")) {
            return "Staff";
        } else if (email.endsWith("@students.mq.edu.au")) {
            return "Student";
        }
        throw new IllegalStateException("Invalid user");
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
        if (Authentication.login(this, identifier, password)) {
            System.out.println("=== Welcome, " + username + " (" + getUserType() + ")! ===");
            return true;
        }
        return false;
    }

    public class Authentication {
        public static boolean login(User user, String identifier, String password) {
            if (!user.getUsername().equals(identifier) && !user.getEmail().equals(identifier)) {
                System.out.println("Username/Email not valid");
                return false;
            }
            if (!user.authenticate(password)) {
                System.out.println("Wrong Password");
                return false;
            }
            return true;
        }
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
            return this.channel;
        }
        System.out.println("You already have a channel: " + channel.getChannelName());
        return this.channel;
    }

    /* try to see if it works
    public void setChannel(Channel channel) {
        this.channel = channel;
    }*/
    
    public Channel getChannel() {
        return this.channel;
    }
    
    // This method checks if the user has any channels. If the user has none, it returns null
    public boolean hasChannel() {
        return this.channel != null;
    }

    public String getPassword() {
        return password;
    }
    
}