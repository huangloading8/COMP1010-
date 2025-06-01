# MQ TUBE
# COMP1010 Group Anything

## 1. Project Overview

This project simulates a simplified version of a video-sharing platform (like YouTube), where users can:

- Create accounts
- Create and edit channels (one per user)
- Upload, view, and delete videos
- Comment on videos
- Organize videos into playlists

The system also supports saving and loading data via CSV files, allowing data persistence between sessions.

---

## 2. Functional Requirements

**User and Channel Features:**
- A user can:
  - Create an account
  - Create a channel (only one)
  - Edit their channel name and description

**Video Management:**
- A user can:
  - Upload a video (title, description, duration) if a channel exists
  - Videos are linked to the user’s channel
  - Each video has a unique ID

**Video Persistence:**
- All videos are saved to user-specific CSV files (e.g., bob_videos.csv)
- Videos are loaded from CSV when the program starts

**Compatibility:**
- Files are saved in CSV format, compatible with Excel, Google Sheets, etc.
- Files are easily accessible for sharing and backups

**Performance and Scalability:**
- Efficient processing with minimal delay
- Runs on systems with limited resources

---

## 3. What problem does our application solve?

This program simulates a video-sharing platform that allows users to:

- Register and log in
- Create and manage channels
- Upload and manage video content
- Organize content via playlists
- Leave and view comments

Data is saved using CSV files, enabling easy persistence and portability of content.

---

## 4. Program Structure

Implemented in Java using object-oriented design. Key components:

- `Client.java` – Launches the application; handles user interaction
- `MQTubeManager.java` – Main controller managing users, channels, playlists
- `User.java` – Represents a user account
- `Channel.java` – Represents a user's video channel
- `Video.java` – Represents a single video
- `Playlist.java` – Represents a collection of videos
- `Comment.java` – Represents a user comment on a video
- `CSVUtils.java` – Handles file read/write operations
- `Test_*.java` – Unit tests for functionality

---

## 5. How to run the program

**Executable file:** `Client.java`

### Step-by-step:

1. Run the program – you'll be greeted with: Welcome to MQTube!

2. Log in with one of the predefined users:
   - alice@students.mq.edu.au / pass123
   - bob@mq.edu.au / bobpass
   - charlie@students.mq.edu.au / charliepw
   - diana@students.mq.edu.au / dianapass

3. Once logged in, you will see the Main Menu:

   1: **View Videos** – Displays all uploaded videos  
   2: **View Playlist** – Shows all your playlists  
   3: **Upload Video** – Upload a video (must have a channel)  
      - If no channel exists, you’ll be prompted to create one via option 6  
      - Videos are saved to your CSV file  
   4: **Delete Video** – Remove one of your uploaded videos  
      - Updates your CSV file  
   5: **Edit Playlist** – Rename playlist or remove videos  
   6: **Edit Channel** – Create/edit your channel info  
      - Channel won’t appear in CSV until a video is uploaded  
   7: **Search Videos** – Search videos by keyword (title, description, or channel)  
   8: **Exit MQTube** – Exit the program

---

## 6. Task Allocation

**Beatrice Annika Lim (48396966)** – 33%  
- Set up classes, UML diagram  
- CSV functionality, Command line inputs  
- Methods for Video, Channel, User  
- Client logic, README, JUnit testing

**Trieu Long Vu (SID)** – 33%  
- Set up classes, UML diagram  
- Playlist functionality, User class  
- Comment features, Methods for User, DNode, Playlist

**Yongwen Huang (SID)** – 33%  
- Set up classes, UML diagram  
- JUnit tests, Delegation  
- Video methods, CSV for video removal

---

## 7. Method Analysis

### a. `CSVUtils.appendVideoToCSV(Video video)`

**What it does:**  
Appends a single video's details as a new line in a user-specific CSV file.

**Why it’s efficient:**  
- Writes directly without needing to read or rewrite the full file  
- Uses `FileWriter(filename, true)` to append line-by-line  
- Handles only one object at a time (low memory usage)

**Alternative approach:**  
- Load entire CSV into memory, modify, and rewrite  
- Slower and more memory-intensive for large files

**Conclusion:**  
The current method is faster and more memory-efficient for append-only operations.

---

### b. `MQTubeManager.searchVideos(String keyword)`

**What it does:**  
Searches all uploaded videos for a keyword match in title, description, or channel name.

**Current implementation:**  
- Iterative loop over all videos  
- For each video: checks `.contains()` on 3 fields

**Time Complexity:**  
- Let `n` = number of videos, `m` = average string length  
- Total: **O(n × m)**

**Alternative: Recursive Search**

```java
public void recursiveSearch(ArrayList<Video> videos, String keyword, int index) {
    if (index >= videos.size()) return;
    Video video = videos.get(index);
    if (video.getTitle().contains(keyword) ||
        video.getDescription().contains(keyword) ||
        video.getChannel().getChannelName().contains(keyword)) {
        System.out.println(video);
    }
    recursiveSearch(videos, keyword, index + 1);
}
```

**Conclusion:**  
Recursive method has the same time complexity but higher memory usage (call stack). Iterative is preferred for larger datasets.

---

## 8. Assessment Criteria Justification

### ✅ Unit Testing (2 marks)

- We created **JUnit test classes** (e.g., `Test_User.java`, `Test_Video.java`, `Test_Channel.java`, `Test_CSVUtils.java`) to test:
  - Valid and invalid inputs
  - Edge cases (e.g., empty title, max duration videos, non-existent users)
  - Logical correctness of getters, setters, and file operations

---

### ✅ Functionality (3 marks)

- The program achieves all core functions of a simple video-sharing platform:
  - Register/login
  - Channel creation/editing
  - Video upload/view/delete
  - Playlist creation/editing
  - Commenting and searching
  - Persistent storage with CSV

---

### ✅ Scope (5 marks)

- **(1 mark)** Uses object of another user-defined class:  
  - `Channel` has a `User` object (`private User owner;`)

- **(1 mark)** Uses ArrayList of user-defined objects:  
  - `MQTubeManager` has `ArrayList<User>` and `ArrayList<Channel>`  
  - `Playlist` has `ArrayList<Video>`

- **(2 marks)** Recursive structure:  
  - `DNode` class represents a recursive doubly-linked list  
  - We use a recursive method countPlaylistVideos(DNode) in Playlist.java to traverse and count videos stored in a doubly-linked list (DNode), satisfying the recursive data structure requirement.

- **(1 mark)** File I/O implemented:  
  - CSV file reading with `BufferedReader`, writing with `FileWriter`  
  - One CSV per user and for all videos

---
