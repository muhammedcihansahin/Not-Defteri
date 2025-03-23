package notlar_amaformlu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import static notlar_amaformlu.NotePiece.NPH;
import static notlar_amaformlu.NotePiece.NPW;
import static notlar_amaformlu.Frame.thisF;

public class Operations {

    static boolean isNoteOpened = false;
    static boolean alertFrame = false;
    static boolean isCurrentLastNote = false;
    static NotePiece current = null;
    ImageIcon addIcon = new javax.swing.ImageIcon(getClass().getResource("/iconlar/plus2.png"));
    Icon alert = new javax.swing.ImageIcon(getClass().getResource("/iconlar/son.png"));
    Icon lightC = new javax.swing.ImageIcon(getClass().getResource("/iconlar/light C.png"));
    Icon nightB = new javax.swing.ImageIcon(getClass().getResource("/iconlar/night B.png"));
    static File adres;
    static File[] files;
    static int noteCount = 0;
    static float f = 0.005f;

    static void anaEkraniCiz(Notes notlar) throws IOException {

        Frame.notes = null;

        Operations o = new Operations();

        if (Frame.darkMode) {
            Frame.themeSection.setIcon(o.nightB);
        } else {
            Frame.themeSection.setIcon(o.lightC);
        }

        adres = null;
        Operations.current = null;
        files = null;

        Frame.notes = new Notes();
        noteCount = 0;
        int xDegeri = 0;
        isNoteOpened = false;

        adres = new File(("C:\\Users\\" + System.getProperty("user.name") + "\\Documents\\MyNotes"));
        files = new File[adres.listFiles().length];
        files = adres.listFiles((dir, name) -> name.endsWith(".txt"));

        for (int i = 0; i < adres.listFiles().length - 1; i++) {

            files[i] = new File(files[i].getPath());
            System.out.println("isimleri " + files[i].getName().substring(0, files[i].getName().length() - 4));
            Frame.notes.tail.notName = files[i].getName().substring(0, files[i].getName().length() - 4);
            notEkle(Frame.notes);

        }

        NotePiece temp = Frame.notes.head;

        while (temp != null) {

            if (noteCount % 2 == 0) {//çiftse
                temp.setBounds(xDegeri * (NPW + 50) + 125, NPH - 20, NPW, NPH);

            } else { //tekse
                temp.setBounds(xDegeri * (NPW + 50) + 125, NPH * 2 + 20, NPW, NPH);

            }

            //*** *** *** *** *** *** *** *** *** *** *** geri tuşu *** *** *** *** *** *** *** *** *** *** *** *** 
//            temp.remove(temp.textArea);
            if (temp.backButton.isEnabled()) {
                Frame.mainPanel.remove(temp.backButton);
            }

            noteCount++;

            if (noteCount % 2 == 0) {
                xDegeri++;
            }

            if (temp.next != null) {
                temp.notePieceName.setVerticalAlignment(javax.swing.SwingConstants.CENTER);
                temp.colorNote = Frame.myColor[0];
                temp.notePieceName.setIcon(null);

            } else {
                temp.notePieceName.setVerticalAlignment(javax.swing.SwingConstants.CENTER);
                temp.colorNote = Frame.myColor[0];
                Operations i = new Operations();
                temp.notePieceName.setIcon(i.addIcon);

            }

            temp.notePieceName.setText(temp.notName);
            temp.add(temp.notePieceName);

            Frame.mainPanel.add(temp);
            temp = temp.next;
        }

        Frame.deleteButton.setIcon(null);
        Frame.titleText.setCaretColor(Frame.myColor[0]);

        Frame.titleText.getCaret().setBlinkRate(700);
        Frame.titleText.setEditable(false);
        Frame.titleText.setText("Notlarım");
        Frame.mainPanel.repaint();

    }

    static void notEkle(Notes n) {
        n = Frame.notes;
        NotePiece newNode = new NotePiece();
        n.tail.next = newNode;
        n.bosNot = n.tail.next;
        n.tail = n.tail.next;
    }

    static void notuCiz(Notes n, NotePiece c) throws FileNotFoundException {

        n = Frame.notes;
        current = c;

        NotePiece t = n.head;

        while (t != null) {

            if (t != c) {
                t.setBounds(0, 0, 0, 0);
            }
            t.remove(t.notePieceName);
            t = t.next;

        }

        if (c.next == null) {
            isCurrentLastNote = true;
            if (new File(("C:\\Users\\" + System.getProperty("user.name") + "\\Documents\\MyNotes\\" + "Boş Not " + noteCount + ".txt")).exists()) {

            }
            noteCount = 1;
            while (new File(("C:\\Users\\" + System.getProperty("user.name") + "\\Documents\\MyNotes\\" + "Boş Not " + noteCount + ".txt")).exists()) {
                noteCount++;
            }
            Frame.titleText.setText("Boş Not " + noteCount);

            c.notName = Frame.titleText.getText();
            Operations.notEkle(n);
            Frame.mainPanel.repaint();

        } else {
            String line = "";
            try (BufferedReader br = new BufferedReader(new FileReader(("C:\\Users\\" + System.getProperty("user.name") + "\\Documents\\MyNotes\\" + current.notName + ".txt")))) {

                while ((line = br.readLine()) != null) {
                    c.notContent += line + "\n";
                }
            } catch (IOException e) {
            }

        }

        Frame.themeSection.setIcon(null);
        c.setBounds(Frame.title.getX(), Frame.title.getY() + 130, Frame.title.getWidth(), 550);

        c.textArea = null;
        c.scrollPane = null;

        c.textArea = new JTextArea();
        c.textArea.setBackground(Frame.myColor[0]);
        c.textArea.setForeground(Frame.myColor[3]);
        c.textArea.setColumns(20);
        c.textArea.setBorder(null);
        c.textArea.setLineWrap(true);
        c.textArea.setRows(5);
        c.textArea.setFont(new java.awt.Font("Arial", 0, 20));

        c.textArea.setText(c.notContent);

        c.scrollPane = new JScrollPane(c.textArea);
        c.scrollPane.setBounds(15, 15, Frame.title.getWidth() - 30, c.getHeight() - 35);
        c.add(c.scrollPane);
        c.scrollPane.setBorder(BorderFactory.createEmptyBorder());

        c.scrollPane.getVerticalScrollBar().setUI(new javax.swing.plaf.basic.BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                thumbColor = Frame.myColor[1];
                trackColor = Frame.myColor[0];
            }

            @Override
            protected JButton createDecreaseButton(int orientation) {
                return createZeroButton();
            }

            @Override
            protected JButton createIncreaseButton(int orientation) {
                return createZeroButton();
            }

            private JButton createZeroButton() {
                JButton button = new JButton();
                button.setPreferredSize(new Dimension(0, 0));
                return button;
            }

            @Override
            protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
                if (!c.isEnabled()) {
                    return;
                }
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(thumbColor);
                g2.fillRoundRect(thumbBounds.x, thumbBounds.y + 10, thumbBounds.width, thumbBounds.height - 20, 20, 20);
                g2.dispose();
            }

            @Override
            protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(trackColor);
                g2.fillRoundRect(trackBounds.x, trackBounds.y, trackBounds.width, trackBounds.height, 0, 0);
                g2.dispose();
            }
        });

        Frame.deleteButton.setIcon(Frame.deleteB);

        Frame.mainPanel.add(c.backButton);

        Frame.titleText.setEditable(true);
        Frame.titleText.setText(c.notName);
        Frame.titleText.getCaret().setBlinkRate(500);

        Frame.titleText.setCaretPosition(Frame.titleText.getText().length());

        Frame.mainPanel.repaint();
        Operations.isNoteOpened = true;

    }

    static void uyariEkrani() {

        if (!alertFrame) {
            alertFrame = true;
            Operations i = new Operations();
            JFrame frameAlert = new JFrame("Scroll Örneği");
            frameAlert.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            JLabel label = new JLabel(i.alert);

            frameAlert.setSize(i.alert.getIconWidth(), i.alert.getIconHeight());
            frameAlert.setLocation(thisF.getX() + thisF.getWidth() / 2 - frameAlert.getWidth() / 2, thisF.getY() + 500);
            Dimension d = new Dimension(i.alert.getIconWidth(), i.alert.getIconHeight());
            frameAlert.setMinimumSize(d);
            frameAlert.setAlwaysOnTop(true);
            frameAlert.setUndecorated(true);

            label.setBackground(new Color(0, 0, 0, 0));
            label.setVisible(true);

            frameAlert.pack();
            frameAlert.setBackground(new Color(0, 0, 0, 0));

            frameAlert.add(label);
            label.setLocation(0, 0);

            frameAlert.setOpacity(0.05f);

            frameAlert.setVisible(true);

            Thread t = new Thread();

            Timer timer = new Timer();
            TimerTask task = new TimerTask() {

                public void run() {

                    alertFrame = true;
                    frameAlert.setOpacity(frameAlert.getOpacity() + f);

                    if (frameAlert.getOpacity() < 0.01f) {
                        frameAlert.setOpacity(0.0f);
                        frameAlert.dispose();
                        f *= -1;
                        timer.cancel();
                        alertFrame = false;
                    }
                    if (frameAlert.getOpacity() > 0.995f) {

                        f *= -1;

                        try {
                            Thread.sleep(1650);
                        } catch (InterruptedException ex) {
                        }
                    }

                }

            };

            timer.schedule(task, 0, 1);

        }
    }

    static void changeTheme() {

        if (Frame.darkMode) {

            Frame.darkMode = false;
            Frame.myColor[0] = new Color(220, 210, 200);
            Frame.myColor[1] = new Color(255, 249, 243);
            Frame.myColor[2] = new Color(30, 30, 30);
            Frame.myColor[3] = new Color(20, 20, 20);

        } else {

            Frame.darkMode = true;
            Frame.myColor[0] = new Color(51, 51, 51);//başlık ve kartlar
            Frame.myColor[1] = new Color(91, 91, 91);//boşluklar
            Frame.myColor[2] = new Color(0, 0, 0);// FONTLAR
            Frame.myColor[3] = new Color(0, 0, 0);// FONTLAR
        }

        Frame.top.setBackground(Frame.myColor[0]);
        Frame.mainPanel.setBackground(Frame.myColor[1]);
        Frame.titleText.setBackground(Frame.myColor[0]);
        Frame.titleText.setForeground(Frame.myColor[3]);
        
        
        
        Frame.titleText.setCaretColor(Frame.titleText.getBackground());
       
        NotePiece temp = Frame.notes.head;
        while (temp != null) { 
            temp.colorNote = Frame.myColor[0];
            temp.notePieceName.setForeground(Frame.myColor[3]);
            temp = temp.next;
        }

    }

}
