import java.io.*;
import java.util.ArrayList;

/*
 * Utility class for handling CSV file operations.
 * This class provides methods to save, load, and modify video data stored in CSV files.
 */

public class CSVUtils {

    // Appends a new video entry to the CSV file specific to the user/channel
    public static void appendVideoToCSV(Video video) {
        File directory = new File("data");

        // Create the data directory if it doesn't exist
        if (!directory.exists()) {
            boolean created = directory.mkdirs();
            if (!created) {
                System.err.println("Failed to create 'data' directory.");
                return;
            }
        }

        // Construct the filename based on the channel owner's username
        String filename = "data/" + video.getChannel().getOwner().getUsername() + "_videos.csv";

        try (FileWriter writer = new FileWriter(filename, true)) {
            // Build the CSV line for the video
            String line = video.getVideoID() + "," +
                          video.getTitle() + "," +
                          video.getDescription() + "," +
                          video.getDuration() + "," +
                          video.getDateUploaded() + "," +
                          video.getChannel().getChannelName() + "\n";

            writer.append(line);

            // Debug/confirmation output
            System.out.println("Appended to file: " + filename);
            System.out.println("Line written: " + line);

        } catch (IOException e) {
            System.err.println("Error writing to CSV: " + e.getMessage());
        }
    }

    // Loads all videos from the CSV file associated with a user's channel
    public static ArrayList<Video> loadVideosForUser(Channel channel) {
        ArrayList<Video> videos = new ArrayList<>();
        String filename = "data/" + channel.getOwner().getUsername() + "_videos.csv";

        File file = new File(filename);
        if (!file.exists()) return videos;  // Return empty list if no file exists

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;

            // Parse each line and construct Video objects
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                String title = data[1];
                String description = data[2];
                int duration = Integer.parseInt(data[3]);
                int date = Integer.parseInt(data[4]);

                videos.add(new Video(title, description, duration, date, channel));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return videos;
    }

    // Exports all videos for all users/channels into individual CSV files (used at startup or debugging)
    public static void exportAllVideosToCSV(ArrayList<Channel> channels) {
        for (Channel channel : channels) {
            String filename = "data/" + channel.getOwner().getUsername() + "_videos.csv";

            try (FileWriter writer = new FileWriter(filename)) {
                // Write the header row
                writer.append("VideoID,Title,Description,Duration,DateUploaded,ChannelName\n");

                // Write each video's data
                for (Video video : channel.getVideos()) {
                    writer.append(video.getVideoID() + ",");
                    writer.append(video.getTitle() + ",");
                    writer.append(video.getDescription() + ",");
                    writer.append(video.getDuration() + ",");
                    writer.append(video.getDateUploaded() + ",");
                    writer.append(video.getChannel().getChannelName());
                    writer.append("\n");
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // Removes a video entry from a user's CSV file based on the video ID
    public static void removeVideoFromCSV(Video video) {
        String username = video.getChannel().getOwner().getUsername();
        String filename = "data/" + username + "_videos.csv";
        File inputFile = new File(filename);
        File tempFile = new File("data/temp_" + username + "_videos.csv");

        try (
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))
        ) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Skip the line if it starts with the video ID (meaning it's the one to delete)
                if (line.startsWith(video.getVideoID() + ",")) {
                    continue;
                }
                writer.write(line + System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Replace original file with the updated temporary file
        if (!inputFile.delete()) {
            System.err.println("Could not delete original file.");
            return;
        }

        if (!tempFile.renameTo(inputFile)) {
            System.err.println("Could not rename temp file.");
        }
    }
}
