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

**Video Manegment:**
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

This application simulates a basic video-sharing platform where users can:
    a. Create a user account and channel
    b. Upload, view, and manage videos
    c. Create playlists
    d. Post comments
    e. Search for videos by tags or channel names
The system enables efficient organization and simulation of content creation, sharing, and interaction within a simplified video platform model.

**2. Program Structure:**
The application is implemented in Java and follows an object-oriented design. The main components include:
    a. Main.java – Acts as the entry point of the application; handles user interaction via console.
    b. VideoManager.java – Controls the core logic, manages lists of users, videos, and playlists.
    c. User.java – Represents a user with login credentials and personal information.
    d. Channel.java – Represents a channel, tied to a user, where videos are uploaded.
    e. Video.java – Represents individual videos uploaded to channels.
    f. Playlist.java – Represents a playlist containing a list of videos.
    g. Comment.java – Represents a comment left on a video.

**3. How to run the program:**
     - Executable file: Client.java 
    a. Upon running the program: You'll be greeted with "Welcome to MqTube!"
    b. You'll need to log in using a predefined username or email and password.
        Predefined users include:
                -alice@example.com / pass123
                -bob@example.com / bobpass
                -charlie@example.com / charliepw
                -diana@example.com / dianapass
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
                5: Edit Playlist – (Feature not yet implemented.)
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






