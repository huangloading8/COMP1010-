package src;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CSVUtils {

    private static final String FILE_NAME = "videos.csv";

    // Call this once to export all existing videos at start
    public static void exportAllVideosToCSV(List<Channel> channels) {
        try (FileWriter writer = new FileWriter(FILE_NAME)) {
            writer.append("VideoID,Title,Description,Duration,DateUploaded,ChannelName\n");

            for (Channel channel : channels) {
                for (Video video : channel.getVideos()) {
                    writer.append(String.valueOf(video.getVideoID())).append(",");
                    writer.append(escape(video.getTitle())).append(",");
                    writer.append(escape(video.getDescription())).append(",");
                    writer.append(String.valueOf(video.getDuration())).append(",");
                    writer.append(String.valueOf(video.getDateUploaded())).append(",");
                    writer.append(escape(channel.getChannelName())).append("\n");
                }
            }

            System.out.println("All videos exported to videos.csv.");
        } catch (IOException e) {
            System.out.println("Error writing CSV: " + e.getMessage());
        }
    }

    // Appends a single video after upload
    public static void appendVideoToCSV(Video video) {
        try (FileWriter writer = new FileWriter(FILE_NAME, true)) {
            writer.append(String.valueOf(video.getVideoID())).append(",");
            writer.append(escape(video.getTitle())).append(",");
            writer.append(escape(video.getDescription())).append(",");
            writer.append(String.valueOf(video.getDuration())).append(",");
            writer.append(String.valueOf(video.getDateUploaded())).append(",");
            writer.append(escape(video.getChannel().getChannelName())).append("\n");
        } catch (IOException e) {
            System.out.println("Error appending to CSV: " + e.getMessage());
        }
    }

    // Escape commas and quotes in text
    private static String escape(String text) {
        if (text.contains(",") || text.contains("\"")) {
            text = text.replace("\"", "\"\"");
            return "\"" + text + "\"";
        }
        return text;
    }
}
