import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {
    private JTextField fileNameField;
    private JButton downloadButton;
    private JPanel downloadPanel;

    public MainFrame() {
        setTitle("Aplikasi Pengunduhan File");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel Input
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());

        fileNameField = new JTextField(20);
        downloadButton = new JButton("Mulai Unduh");

        inputPanel.add(new JLabel("Nama File:"));
        inputPanel.add(fileNameField);
        inputPanel.add(downloadButton);

        add(inputPanel, BorderLayout.NORTH);

        // Panel untuk menampilkan progress
        downloadPanel = new JPanel();
        downloadPanel.setLayout(new BoxLayout(downloadPanel, BoxLayout.Y_AXIS));
        add(downloadPanel, BorderLayout.CENTER);

        downloadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fileName = fileNameField.getText();
                if (!fileName.isEmpty()) {
                    startDownload(fileName);
                } else {
                    JOptionPane.showMessageDialog(MainFrame.this, "Masukkan nama file!");
                }
            }
        });
    }

    private void startDownload(String fileName) {
        JProgressBar progressBar = new JProgressBar(0, 100);
        progressBar.setStringPainted(true);
        downloadPanel.add(progressBar);
        downloadPanel.revalidate();
        downloadPanel.repaint();

        Thread downloadThread = new Thread(new DownloadTask(fileName, progressBar));
        downloadThread.start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        });
    }
}