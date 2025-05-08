package src;
public class User {
    private String username;
    private String email;
    private String password;

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
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

    public boolean createAccount() {
        
        System.out.println("Account created for user: " + username);
        return true;
    }

    public boolean login(String identifier, String password) {
        return (this.username.equals(identifier) || this.email.equals(identifier)) && authenticate(password);
    }

    
    public Channel createChannel(String channelName, String channelDescription) {
        return new Channel(channelName, channelDescription, );
    }
}
