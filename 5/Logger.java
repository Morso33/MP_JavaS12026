import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {

    public static final String DEFAULT_FILE_NAME = "log.txt";

    private static final DateTimeFormatter TIMESTAMP_FORMAT =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private static Logger instance;

    private PrintWriter writer;
    private String fileName;

    private Logger() {
        openFile(DEFAULT_FILE_NAME, false);
    }


    public static synchronized Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }


    public synchronized void setFileName(String fileName) {
        if (fileName == null || fileName.isEmpty()) {
            System.err.println("Logger: file name cannot be empty, keeping " + this.fileName + ".");
            return;
        }
        if (fileName.equals(this.fileName) && this.writer != null) {
            return;
        }
        close();
        openFile(fileName, false);
    }


    public synchronized void write(String message) {
        if (this.writer == null) {
            openFile(this.fileName != null ? this.fileName : DEFAULT_FILE_NAME, true);
            if (this.writer == null) {
                System.err.println("Logger: cannot write, no log file is open.");
                return;
            }
        }
        this.writer.println(LocalDateTime.now().format(TIMESTAMP_FORMAT) + " " + message);
        if (this.writer.checkError()) {
            System.err.println("Logger: writing to " + this.fileName + " failed.");
        }
    }


    public synchronized void close() {
        if (this.writer != null) {
            this.writer.close();
            this.writer = null;
        }
        //Multiple calls do not throw an ex.
    }

    public synchronized String getFileName() {
        return this.fileName;
    }


    private void openFile(String fileName, boolean append) {
        try {
            this.writer = new PrintWriter(new BufferedWriter(new FileWriter(fileName, append)));
            this.fileName = fileName;
        } catch (IOException e) {
            this.writer = null;
            this.fileName = fileName;
            System.err.println("Logger: could not open file " + fileName + ": " + e.getMessage());
        }
    }
}
