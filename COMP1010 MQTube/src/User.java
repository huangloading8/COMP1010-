package src;
public class User {
    private String username;
    private String email;
    private String password;
    private Channel channel;

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

    public boolean authenticate(String password) {
        return this.password.equals(password); // Simplified
    }

    public static User createAccount(String username, String email, String password) {
        User newUser = new User(username, email, password);
        return newUser;
    }

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
    public boolean hasChannel() {
        return this.channel != null;
    }
}