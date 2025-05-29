package src;

import java.io.*;
import java.util.ArrayList;

public class CSVUtils {

    // Method to append a new video to the user's CSV file
    public static void appendVideoToCSV(Video video) {
        // Use the channel owner's username for the file name
        String filename = video.getChannel().getOwner().getUsername() + "_videos.csv";
        
        try (FileWriter writer = new FileWriter(filename, true)) {
            // Append the video details in CSV format
            writer.append(video.getVideoID() + ",");
            writer.append(video.getTitle() + ",");
            writer.append(video.getDescription() + ",");
            writer.append(video.getDuration() + ",");
            writer.append(video.getDateUploaded() + ",");
            writer.append(video.getChannel().getChannelName());
            writer.append("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to load videos from the user's CSV file
    public static ArrayList<Video> loadVideosForUser(Channel channel) {
        ArrayList<Video> videos = new ArrayList<>();
        String filename = channel.getOwner().getUsername() + "_videos.csv";

        // Check if the file exists
        File file = new File(filename);
        if (!file.exists()) return videos; // If no file exists, return an empty list

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            // Read each line from the CSV file
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                String title = data[1];
                String description = data[2];
                int duration = Integer.parseInt(data[3]);
                int date = Integer.parseInt(data[4]);
                
                // Add the video to the list
                videos.add(new Video(title, description, duration, date, channel));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return videos;
    }
    
    // Method to export all videos of all channels to CSV (Optional)
    public static void exportAllVideosToCSV(ArrayList<Channel> channels) {
        for (Channel channel : channels) {
            // Use the channel owner's username for the file name
            String filename = channel.getOwner().getUsername() + "_videos.csv";
            
            try (FileWriter writer = new FileWriter(filename)) {
                // Write header row in the CSV
                writer.append("VideoID,Title,Description,Duration,DateUploaded,ChannelName\n");

                // Iterate through each video in the channel
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

     // Method to remove videos from the user's CSV file
    public static void removeVideoFromCSV(Video video) {
        String filename = video.getChannel().getOwner().getUsername() + "_videos.csv";
        File inputFile = new File(filename);
        File tempFile = new File("temp_" + filename);
    
        try (
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))
        ) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Skip the line if it matches the video ID
                if (line.startsWith(video.getVideoID() + ",")) {
                    continue;
                }
                writer.write(line + System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    
        // Replace original file with the updated temp file
        if (!inputFile.delete()) {
            System.err.println("Could not delete original file.");
            return;
        }
    
        if (!tempFile.renameTo(inputFile)) {
            System.err.println("Could not rename temp file.");
        }
    }
}
