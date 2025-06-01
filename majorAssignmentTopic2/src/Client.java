/*
 * Entry point for the MQTube application.
 * This class is responsible for starting the system by initializing the manager.
 */

public class Client {
    public static void main(String[] args) {
        // Create an instance of MQTubeManager which handles all core functionality
        MQTubeManager manager = new MQTubeManager();

        // Start the application by running the main program loop
        manager.run();
    }
}
