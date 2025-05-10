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

    public static User createAccount(String username, String email, String password) {
        User newUser = new User(username, email, password);
        System.out.println("Account created for user: " + username);
        return newUser;
    }

    public boolean login(String identifier, String password) {
        if (this.username.equals(identifier) || this.email.equals(identifier)) {
            if (authenticate(password)) {
                System.out.println("Login Success");
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
        return new Channel(channelName, this, channelDescription);
    }
}
