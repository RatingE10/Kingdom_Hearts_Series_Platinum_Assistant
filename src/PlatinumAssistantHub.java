import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class PlatinumAssistantHub {
    public static void main(String[] args) throws Exception {

        JFrame frame = new JFrame();
        //JOptionPane.showMessageDialog(frame, "We would suggest putting this program in a folder of its own, so we can make the text files needed. If you haven't done so, close the program and do so now.", "KH Series Platinum Assistant", JOptionPane.PLAIN_MESSAGE);
        String[] gameNames = {"Kingdom Hearts", "Kingdom Hearts\nre:Chain of Memories", "Kingdom Hearts 2", "Kingdom Hearts:\nBirth By Sleep", "Kingdom Hearts:\nDream Drop Distance", "Kingdom Hearts 0.2:\nA Fragmentary Passage", "Kingdom Hearts 3"};
        JPanel panel = new JPanel();
        for (String rawName : gameNames) {
            String name = rawName;
            String[] names = name.split("\n");
            if (names.length == 2)
                name = "<html>" + names[0] + "<p>" + names[1] + "</html>";
            else
                name = "<html>" + name + "</html>";
            JButton button = new JButton(name);
            button.setName(rawName);
            button.setEnabled(false);
            button.setFont(new Font("", Font.BOLD, 16));
            ActionListener actionListener = ActiveEvent -> {
                try {
                    frame.setVisible(false);
                    openGamePage(button.getName(), frame, new Font("", Font.BOLD, 16));
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            };
            button.addActionListener(actionListener);
            if (rawName.equalsIgnoreCase("Kingdom Hearts 3")) {
                JButton button1 = new JButton();
                button1.setVisible(false);
                panel.add(button1);
                button.setEnabled(true);
            }
            panel.add(button);
        }

        frame.add(panel);
        GridLayout layout = new GridLayout(3, 3);
        layout.setHgap(30);
        layout.setVgap(30);
        panel.setLayout(layout);
        JButton close = new JButton("Close");
        frame.add(close, BorderLayout.SOUTH);
        frame.pack();
        frame.setSize(800, 800);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);


    }

    public static void openGamePage(String gameName, JFrame parent, Font font) throws Exception {
        JFrame frame = new JFrame();
        switch (gameName) {
            case "Kingdom Hearts":
            case "Kingdom Hearts re:Chain of Memories":
            case "Kingdom Hearts 2":
            case "Kingdom Hearts: Birth By Sleep":
            case "Kingdom Hearts: Dream Drop Distance":
            case "Kingdom Hearts 0.2: A Fragmentary Passage":
                JOptionPane.showMessageDialog(frame, "If you are seeing this message, something has gone very wrong. Please notify the developer.");
                break;
            case "Kingdom Hearts 3":
                frame.setName("Kingdom Hearts Platinum Assistant: Kingdom Hearts 3");
                JPanel panel = new JPanel();
                GridLayout layout = new GridLayout(1, 3);
                layout.setHgap(10);
                panel.setLayout(layout);
                JButton synth = new JButton("Synthesis Tool");
                synth.addActionListener(e -> {
                    try {
                        frame.setVisible(false);
                        new KH3SynthesisTool();
                    } catch (Exception ex) {

                        throw new RuntimeException(ex);
                    }
                });
                synth.setFont(font);
                panel.add(synth);
                JButton kh3tips = new JButton("General Tips");
                kh3tips.setEnabled(false);
                kh3tips.setFont(font);
                panel.add(kh3tips);
                frame.add(panel, BorderLayout.CENTER);
                frame.pack();
                frame.setLocationRelativeTo(parent);
                frame.setSize(600, 300);
                frame.setVisible(true);
                break;
            default:
                JOptionPane.showMessageDialog(frame, "If you are seeing this message, something has gone very wrong. Please notify the developer.");
                break;

        }
        frame.isVisible();
    }


}