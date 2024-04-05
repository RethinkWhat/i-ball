package client.idol.view.application_pages;

import shared.res.Stylesheet;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Error message when a vehicle already exists (via plate number).
 */
public class PopupMessageFailed extends JFrame {
    /**
     * Exit button
     */
    private JButton btnExit;
    /**
     * Constructs a VehicleErrorDialog
     */
    public PopupMessageFailed() {

        JFrame mainFrame = new JFrame();
        JDialog dialog = new JDialog(mainFrame, "I-BALL", true);
        dialog.setTitle("I-BALL");
        dialog.setLayout(new GridLayout(3, 1));
        dialog.setSize(500, 300);

        // Create pnlIcon panel
        JPanel pnlIcon = new JPanel();
        pnlIcon.setLayout(new BorderLayout());
        pnlIcon.setPreferredSize(new Dimension(600, 200));

        Stylesheet res = new Stylesheet();
        ImageIcon originalIcon = res.iconFailed;
        Image originalImage = originalIcon.getImage();
        int width = 75;
        int height = 75;

        Image scaledImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        JLabel iconLabel = new JLabel(scaledIcon);
        pnlIcon.add(iconLabel, BorderLayout.CENTER);



        // Create pnlServerClosed panel
        JPanel pnlServerClosed = new JPanel(new GridBagLayout());
        pnlServerClosed.setPreferredSize(new Dimension(600, 170));

        // Labels for the pnlServerClosed
        JLabel lblServerMsg = res.createLblH1("BOOKING FAILED", res.red);
        JLabel lblClosedMsg = res.createLblP("There has been an error in booking. ", res.black);
        JLabel lblClosedMsg1 = res.createLblP("Please check booking details and try again.", res.black);

        // Add labels to pnlServerClosed panel
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        pnlServerClosed.add(lblServerMsg, gbc);

        gbc.gridy = 1;
        pnlServerClosed.add(lblClosedMsg, gbc);
        gbc.gridy =2;
        pnlServerClosed.add(lblClosedMsg1,gbc);

        // Create pnlExit panel
        JPanel pnlExit = new JPanel(new FlowLayout());
        pnlExit.setPreferredSize(new Dimension(600, 30));

        // Create the exit button
        btnExit = res.createBtnRounded("CLOSE", res.purple, res.white, 10);

        btnExit.addActionListener(e -> {
            dialog.dispose();
            dialog.setVisible(false);
        });

        pnlExit.add(btnExit);

        // Add panels to the dialog
        dialog.add(pnlIcon);
        dialog.add(pnlServerClosed);
        dialog.add(pnlExit);

        dialog.setLocationRelativeTo(null);
        dialog.setResizable(false);
        dialog.setVisible(true);
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PopupMessageFailed popupMessage = new PopupMessageFailed();
            popupMessage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        });
    }

    /**
     * Sets a specified action listener for btnExit
     * @param actionListener The specified action listener.
     */
    public void setExitListener(ActionListener actionListener) {
        btnExit.addActionListener(actionListener);
    }
}
