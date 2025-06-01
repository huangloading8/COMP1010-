# ** MQ TUBE **
# COMP1010 Group

## **Project Overview**
To simulate a simplified version of a video sharing platform (like YouTube) where users can create channels, upload videos, view and comment on videos.

## **Functional Requirements**
**User and Channel Requirements**
- A user can:
    - Create an account.
    - Create a channel (only one per user).
    - Edit their channel (e.g. change name or description).

**Video Management:**
 - A user can:
        - Upload a video (title, description, duration), if a channel exixts 
        - ideos are linked to their channel.
        - Each video has a unique ID

**Video Persistence:**
    - All uploaded videos are saved to a CSV file (e.g. videos.csv).
    - The system can load videos from the CSV file when starting.

### **Persistence and Compatibility**

- **CSV Format:**
  - Schedules are saved in **CSV format**, ensuring compatibility with various applications like Microsoft Excel, Google Sheets, and LibreOffice Calc.

- **File Accessibility:**
  - Saved files are **easily accessible and transferable**, facilitating sharing and backup.

### **2. Performance and Scalability**

- **Efficient Processing:**
  - The application efficiently handles schedule generation and modifications with minimal latency.

- **Resource Management:**
  - Optimized to run on systems with **limited resources**, ensuring a broad user base.


**1.What problem does our application solve?**

This program simulates a basic video-sharing platform, similar to YouTube. It allows users to:
    a. Create accounts
    b. Make one channel per user
    c. Upload, edit, and view videos
    d. Organize videos into playlists
    e. Comment on videos

The system supports persistence by saving user and video data to CSV files. This enables users to continue where they left off even after restarting the program.

**2. Program Structure:**
The application is implemented in Java and follows an object-oriented design. The main components include:
    a. Client.java – Acts as the entry point of the application; handles user interaction via console.
    b. MQTubeManager.java – Controls the core logic, manages lists of users, videos, and playlists.
    c. User.java – Represents a user with login credentials and personal information.
    d. Channel.java – Represents a channel, tied to a user, where videos are uploaded.
    e. Video.java – Represents individual videos uploaded to channels.
    f. Playlist.java – Represents a playlist containing a list of videos.
    g. Comment.java – Represents a comment left on a video.
    h. Test_*.java - Various test classes for validating logic.

**3. How to run the program:**
     - Executable file: Client.java 
    a. Upon running the program: You'll be greeted with "Welcome to MqTube!"
    b. You'll need to log in using a predefined username or email and password.
        Predefined users include:
            -alice@students.mq.edu.au / pass123
            -bob@mq.edu.au / bobpass
            -charlie@students.mq.edu.au / charliepw
    c. Main Menu Options
        After logging in, you'll see a menu with the following options: 
                1: View Videos – Displays all uploaded videos across channels.
                2: View Playlist – Lists all user playlists and their videos.
                3: Upload Video – Upload a new video to your channel (requires a channel)
                    -You cannot upload a video without creating a channel. If a channel is not created, you will be
                    prompted to create a new channel first. You may do so by selecting option 6. 
                    -Uploaded videos will be shown on the user's CSV file. 
                4: Delete Video – Remove one of your uploaded videos by ID.
                    -Deleting a video will update the user's CSV file. 
                5: Edit Playlist – Lets you rename a playlist or remove videos from it.
                6: Edit Channel – Create or edit your channel name and description.
                    -If creating a new channel, the channel will not show on the CSV file unless a video is first uploaded. 
                7: Search Videos – Search for videos by keyword (title, description, or channel name).
                8: Exit MqTube – End the session with a goodbye message.

**4. Task allocation**

Beatrice Annika Lim (48396966) : 33% 
    -Set up classes 
    -UML diagram 
    -Created the CSV functionality
    -Created the README.txt 
    -Command line inputs 
    -Methods for Video, Channel, and User
    -Functionality for Client 
    -JUNIT tetsing functionality 


Trieu Long Vu (SID) : 33% 
    -Set up classes 
    -UML diagram 
    -Created playlist functionality 
    -User class 
    -Comments
    -Methods for User, Dnode, and Playlist


Yongwen Huang (SID) : 33% 
    -Set up classes 
    -UML diagram 
    -Created the JUnit tests 
    -Delegation
    -Methods for Video 
    -CSV for removing videos 

**5. Method Analysis >**

    a. CSVUtils.appendVideoToCSV(Video video)

    What it does:
    -Appends a single video's details as a new line to a user-specific CSV file.
    Why it’s efficient:
    -It writes directly to the file without needing to load or rewrite the entire CSV.
    -The use of FileWriter(filename, true) appends data efficiently line-by-line.
    -This uses very little memory, as only one Video object is handled at a time.

    Alternative approach:
    -Load the entire CSV file into a list, modify the list, and then rewrite the entire file.
    -This would be slower for larger files and consume more memory.
    Conclusion:
    -The current approach is better suited for append-only operations and is faster and more memory-efficient in this context.

    b. MQTubeManager.searchVideos(String keyword)

    What it does:
    -Searches all uploaded videos to find matches in the title, description, or channel name that contain the given keyword.

    Current implementation approach:
    -Iterates over every video in the system.
    -For each video, it checks:
        if the title contains the keyword,
        if the description contains the keyword,
        if the channel name contains the keyword.

    Time Complexity:
    -Let n = number of videos.
    -Each .contains() call is O(m) where m is the average string length.
    -Worst-case complexity = O(n * m).
    -This is acceptable for small datasets, but performance decreases linearly as the number of videos grows.

    Alternative approach:
   

   
    



