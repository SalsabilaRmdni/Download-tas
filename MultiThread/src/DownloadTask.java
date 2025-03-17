import javax.swing.*;
import java.util.Random;

public class DownloadTask implements Runnable {
    private String fileName;
    private JProgressBar progressBar;

    public DownloadTask(String fileName, JProgressBar progressBar) {
        this.fileName = fileName;
        this.progressBar = progressBar;
    }

    @Override
    public void run() {
        Random random = new Random();
        int total = 100; // Total progress
        int downloaded = 0;

        while (downloaded < total) {
            try {
                // Simulate download time
                Thread.sleep(random.nextInt(1000) + 500); // Sleep between 500ms to 1500ms
                downloaded += random.nextInt(10) + 1; // Increase progress randomly
                if (downloaded > total) {
                    downloaded = total;
                }
                progressBar.setValue(downloaded);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        JOptionPane.showMessageDialog(null, fileName + " telah selesai diunduh!");
    }
}