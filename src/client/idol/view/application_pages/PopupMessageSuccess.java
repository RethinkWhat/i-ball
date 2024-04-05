package client.idol.view.application_pages;

import shared.res.Stylesheet;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Error message when a vehicle already exists (via plate number).
 */
public class PopupMessageSuccess extends JFrame {
    /**
     * Exit button
     */
    private JButton btnExit;
    /**
     * Constructs a VehicleErrorDialog
     */
    public PopupMessageSuccess() {

        JFrame mainFrame = new JFrame();
        JDialog dialog = new JDialog(mainFrame, "I-BALL", true);
        dialog.setTitle("I-BALL");
        dialog.setLayout(new GridLayout(3, 1));
        dialog.setSize(500, 300);

        // Create pnlIcon panel
        JPanel pnlIcon = new JPanel();
        pnlIcon.setLayout(new BorderLayout());
        pnlIcon.setPreferredSize(new Dimension(600, 200));

        // Create and set ImageIcon for pnlIcon
        Stylesheet res = new Stylesheet();
        ImageIcon iconSuccess = res.iconSuccess;
        pnlIcon.add(new JLabel(iconSuccess), BorderLayout.CENTER);

        // Create pnlServerClosed panel
        JPanel pnlServerClosed = new JPanel(new GridBagLayout());
        pnlServerClosed.setPreferredSize(new Dimension(600, 170));

        // Labels for the pnlServerClosed
        JLabel lblServerMsg = res.createLblH1("BOOKING CONFIRMED", res.purple);
        JLabel lblClosedMsg = res.createLblP("You have successfully booked this idol. Thank you!", res.black);

        // Add labels to pnlServerClosed panel
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        pnlServerClosed.add(lblServerMsg, gbc);

        gbc.gridy = 1;
        pnlServerClosed.add(lblClosedMsg, gbc);

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
            PopupMessageSuccess popupMessage = new PopupMessageSuccess();
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
