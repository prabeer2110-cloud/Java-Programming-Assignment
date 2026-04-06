import java.io.File;

public class DirectoryListerDemo {

    public static void main(String[] args) {
        // Specify the path to the directory you want to inspect.
        // You can change this to "C:\\" on Windows or "/" on macOS/Linux.
        String directoryPath = "."; // "." refers to the current working directory

        displayFiles(directoryPath);
    }

    /**
     * Displays all files and folders immediately inside the given directory path.
     */
    public static void displayFiles(String directoryPath) {
        // 1. Create a File object representing the directory
        File directory = new File(directoryPath);

        // 2. Verify that the path exists and is actually a directory
        if (!directory.exists()) {
            System.out.println("Error: The specified path does not exist.");
            return;
        }
        
        if (!directory.isDirectory()) {
            System.out.println("Error: The specified path is a file, not a directory.");
            return;
        }

        System.out.println("=== Contents of Directory: " + directory.getAbsolutePath() + " ===");

        // 3. Retrieve an array of File objects inside the directory
        File[] contents = directory.listFiles();

        // listFiles() can return null if you don't have permission to read the directory
        if (contents == null) {
            System.out.println("Error: Cannot access the contents of this directory (Permission denied).");
            return;
        }

        if (contents.length == 0) {
            System.out.println("The directory is empty.");
            return;
        }

        // 4. Iterate through the array and display each item
        for (File item : contents) {
            if (item.isDirectory()) {
                // It's a folder
                System.out.printf("[FOLDER] %s%n", item.getName());
            } else if (item.isFile()) {
                // It's a file. We can also print its size.
                System.out.printf("[FILE]   %-20s (Size: %d bytes)%n", item.getName(), item.length());
            } else {
                // It's something else (like a system-specific special file)
                System.out.printf("[OTHER]  %s%n", item.getName());
            }
        }
        
        System.out.println("======================================================");
    }
}
