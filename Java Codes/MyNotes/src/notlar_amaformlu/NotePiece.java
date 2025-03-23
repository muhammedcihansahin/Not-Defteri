package notlar_amaformlu;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException; 
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea; 

public class NotePiece extends JPanel {

    NotePiece next;

    static int NPW = 400, NPH = 250;

    //Kapalı Not Kısmı 
    Color colorNote = Color.BLUE;
    String notName = null;
    String notContent = "";

    //Text Area kısmı
    JTextArea textArea;
    JScrollPane scrollPane;

    JLabel backButton;
    JLabel notePieceName;
    Icon geriIconu = new javax.swing.ImageIcon(getClass().getResource("/iconlar/back2.png"));

    public NotePiece() {

        setOpaque(false);
        this.setLayout(null);

        notePieceName = new JLabel("Boş Not");
        notePieceName.setBounds(0, 0, NPW, NPH);

        this.add(notePieceName);

        this.backButton = new JLabel(this.geriIconu);

        this.backButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); //farenin el gibi olması için 
        notePieceName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        notePieceName.setVerticalAlignment(javax.swing.SwingConstants.CENTER);
        this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        notePieceName.setFont(new java.awt.Font("Arial", 1, 22));

        this.backButton.setBounds(20, 86, 100, 100);
        this.backButton.setText("");

        this.backButton.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                Operations.current.remove(Operations.current.scrollPane);

                if ((new File(("C:\\Users\\" + System.getProperty("user.name") + "\\Documents\\MyNotes\\" + Frame.titleText.getText() + ".txt")).exists() && Operations.isCurrentLastNote)
                        || (new File(("C:\\Users\\" + System.getProperty("user.name") + "\\Documents\\MyNotes\\" + Frame.titleText.getText() + ".txt")).exists() && !(Frame.titleText.getText().contains(Operations.current.notName)))) {
                    Operations.uyariEkrani();
                } else {
                    try {

                        if (Operations.current.notName != null) {
                            new File("C:\\Users\\" + System.getProperty("user.name") + "\\Documents\\MyNotes\\" + Operations.current.notName + ".txt").delete();
                        }

                        File f = new File(("C:\\Users\\" + System.getProperty("user.name") + "\\Documents\\MyNotes\\" + Frame.titleText.getText() + ".txt"));
                        f.createNewFile();

                        try (FileWriter fw = new FileWriter(("C:\\Users\\" + System.getProperty("user.name") + "\\Documents\\MyNotes\\" + Frame.titleText.getText() + ".txt"))) {
                            fw.write(Operations.current.textArea.getText());
                        }
                        Operations.isCurrentLastNote = false;

                    } catch (IOException ex) {
                    }

                    Operations.current.textArea.setBounds(0, 0, 0, 0);
                    Operations.current.setBounds(0, 0, 0, 0);

                    Operations.current.notName = Frame.titleText.getText();
                    Frame.mainPanel.remove(Operations.current.backButton);
                    try {
                        Operations.anaEkraniCiz(Frame.notes);
                    } catch (IOException ex) {
                    }

                }

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {
 
            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
            
              

        });

        this.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {

                if (!Operations.isNoteOpened) {
                    try {
                        Operations.notuCiz(Frame.notes, NotePiece.this);
                    } catch (FileNotFoundException ex) {
                    }
                }
                Operations.isNoteOpened = true;

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                if (!Operations.isNoteOpened) {//Not açık değilse...   
                    setBounds(NotePiece.this.getX() - 1, NotePiece.this.getY() - 1, NotePiece.this.getWidth() + 2, NotePiece.this.getHeight() + 2);
                    notePieceName.setBounds(0, 0, NotePiece.this.getWidth(), NotePiece.this.getHeight());

                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (!Operations.isNoteOpened) {
                    setBounds(NotePiece.this.getX() + 1, NotePiece.this.getY() + 1, NotePiece.this.getWidth() - 2, NotePiece.this.getHeight() - 2);
                    notePieceName.setBounds(0, 0, NotePiece.this.getWidth(), NotePiece.this.getHeight());
                }
            }

        });

        next = null;

    }

    @Override
    protected void paintComponent(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        g2d.setColor(colorNote);
        g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 50, 50);

        super.paintComponent(g);

    }

}
