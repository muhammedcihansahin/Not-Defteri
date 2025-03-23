package notlar_amaformlu;

import java.awt.Color;
import java.awt.Cursor;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

public class Frame extends javax.swing.JFrame {

    int x = 0, y = 0;
    static Notes notes;
    static ImageIcon deleteB;
    static int count = 0;
    static boolean buttonRight = false;
    static boolean darkMode = true;
    static Frame thisF;
    static boolean buttonLeft = false;

    static Color[] myColor = { // koyu renkler

       
       new Color(51, 51, 51),//başlık ve kartlar
            new Color(91, 91, 91),//boşluklar
            new Color(0, 0, 0),// FONTLAR
            new Color(0, 0, 0)// FON

    };

    public Frame() {

        initComponents();
        try {
            if (new File(("C:\\Users\\" + System.getProperty("user.name") + "\\Documents\\MyNotes")).createNewFile()) {
                
            }
        } catch (IOException ex) {}

        thisF = this;
        Operations.current = null;
        deleteB = new javax.swing.ImageIcon(getClass().getResource("/iconlar/deleteB.png"));
        Frame.this.setOpacity(0.0001f);

        File file = new File("C:\\Users\\" + System.getProperty("user.name") + "\\Documents\\MyNotes\\data.bin");

        try {
            if (!file.exists()) {
                try ( 
                        DataOutputStream dos = new DataOutputStream(new FileOutputStream(file))) {
                    dos.writeInt(1);
                }

            } else {}
        } catch (IOException e) {
        }

        try (DataInputStream dis = new DataInputStream(new FileInputStream("C:\\Users\\" + System.getProperty("user.name") + "\\Documents\\MyNotes\\data.bin"))) {

            int deger = dis.readInt();

            if (deger == 1) {
                darkMode = true;
                System.out.println("dark mod");
            } else {
                darkMode = false;
                System.out.println("aydınlık mod");
            }

        } catch (EOFException e) {
        } catch (IOException e) {
        }
        
        
        notes = new Notes();
        titleText.setEditable(false);

        try {
            Operations.anaEkraniCiz(notes);
        } catch (IOException ex) {
        }
        
        Operations.changeTheme();
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {

            public void run() {

                if (Frame.this.getOpacity() > 1.0f - (0.01f * 2.0f)) {
                    Frame.this.setOpacity(0.99f);
                    timer.cancel();
                }
                Frame.this.setOpacity(Frame.this.getOpacity() + 0.0075f);

            }
        };

        timer.schedule(task, 0, 1);
        
        
        
        
         if (darkMode) {
                themeSection.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconlar/light C.png")));
                Operations.changeTheme();
            } else {
                themeSection.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconlar/night B.png")));
                Operations.changeTheme();
            }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        top = new javax.swing.JPanel();
        closeButton = new javax.swing.JLabel();
        deleteButton = new javax.swing.JLabel();
        right = new javax.swing.JLabel();
        left = new javax.swing.JLabel();
        title = new notlar_amaformlu.Title();
        titleText = new javax.swing.JTextField();
        themeSection = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setName("myFrame"); // NOI18N
        setUndecorated(true);

        mainPanel.setBackground(new java.awt.Color(102, 102, 102));
        mainPanel.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                mainPanelFocusGained(evt);
            }
        });
        mainPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mainPanelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                mainPanelMouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                mainPanelMousePressed(evt);
            }
        });
        mainPanel.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                mainPanelİnputMethodTextChanged(evt);
            }
        });

        top.setBackground(new java.awt.Color(51, 51, 51));
        top.setPreferredSize(new java.awt.Dimension(0, 50));
        top.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                topMouseDragged(evt);
            }
        });
        top.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                topMousePressed(evt);
            }
        });

        closeButton.setBackground(new java.awt.Color(51, 51, 51));
        closeButton.setFont(new java.awt.Font("Segoe Script", 1, 18)); // NOI18N
        closeButton.setForeground(new java.awt.Color(255, 255, 255));
        closeButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        closeButton.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        closeButton.setText(" X ");
        closeButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                closeButtonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                closeButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                closeButtonMouseExited(evt);
            }
        });

        javax.swing.GroupLayout topLayout = new javax.swing.GroupLayout(top);
        top.setLayout(topLayout);
        topLayout.setHorizontalGroup(
            topLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, topLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(closeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        topLayout.setVerticalGroup(
            topLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(closeButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
        );

        deleteButton.setBackground(new java.awt.Color(153, 0, 102));
        deleteButton.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        deleteButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                deleteButtonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                deleteButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                deleteButtonMouseExited(evt);
            }
        });

        right.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rightMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                rightMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                rightMouseExited(evt);
            }
        });

        left.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                leftMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                leftMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                leftMouseExited(evt);
            }
        });

        titleText.setBackground(new java.awt.Color(51, 51, 51));
        titleText.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        titleText.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        titleText.setText("Notlarım");
        titleText.setBorder(null);
        titleText.setCaretPosition(0);
        titleText.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        titleText.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                titleTextFocusLost(evt);
            }
        });
        titleText.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                titleTextMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                titleTextMouseEntered(evt);
            }
        });
        titleText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                titleTextActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout titleLayout = new javax.swing.GroupLayout(title);
        title.setLayout(titleLayout);
        titleLayout.setHorizontalGroup(
            titleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(titleLayout.createSequentialGroup()
                .addGap(75, 75, 75)
                .addComponent(titleText, javax.swing.GroupLayout.PREFERRED_SIZE, 468, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(495, Short.MAX_VALUE))
        );
        titleLayout.setVerticalGroup(
            titleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(titleText, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
        );

        themeSection.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        themeSection.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconlar/night B.png"))); // NOI18N
        themeSection.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                themeSectionMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                themeSectionMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                themeSectionMouseExited(evt);
            }
        });

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(top, javax.swing.GroupLayout.DEFAULT_SIZE, 1300, Short.MAX_VALUE)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(left, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(deleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(46, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(themeSection, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(right, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(25, 25, 25))))
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addComponent(top, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(mainPanelLayout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(deleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 219, Short.MAX_VALUE)
                        .addComponent(right, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(left, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(187, 187, 187)
                .addComponent(themeSection, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void mainPanelİnputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_mainPanelİnputMethodTextChanged

    }//GEN-LAST:event_mainPanelİnputMethodTextChanged

    private void mainPanelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mainPanelMousePressed

    }//GEN-LAST:event_mainPanelMousePressed

    private void topMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_topMousePressed

        x = evt.getX();
        y = evt.getY();

    }//GEN-LAST:event_topMousePressed

    private void topMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_topMouseDragged

        this.setLocation(evt.getXOnScreen() - x, evt.getYOnScreen() - y);

    }//GEN-LAST:event_topMouseDragged

    private void closeButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeButtonMouseEntered

        closeButton.setForeground(Color.red);

    }//GEN-LAST:event_closeButtonMouseEntered

    private void closeButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeButtonMouseExited
        closeButton.setForeground(Color.white);
    }//GEN-LAST:event_closeButtonMouseExited

    private void closeButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeButtonMouseClicked

        if (Operations.current != null) {

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

                    FileWriter fw = new FileWriter(("C:\\Users\\" + System.getProperty("user.name") + "\\Documents\\MyNotes\\" + Frame.titleText.getText() + ".txt"));
                    fw.write(Operations.current.textArea.getText());
                    fw.close();
                    Operations.isCurrentLastNote = false;

                } catch (IOException ex) {
                }

                Timer timer = new Timer();
                TimerTask task = new TimerTask() {

                    public void run() {

                        Frame.this.setOpacity(Frame.this.getOpacity() - 0.008f);
                        if (Frame.this.getOpacity() < 0.015) {
                            Frame.this.setOpacity(0.0075f);
                            timer.cancel();
                            Frame.this.dispose();
                        }

                    }
                };

                timer.schedule(task, 0, 1);

            }

        } else {
            Timer timer = new Timer();
            TimerTask task = new TimerTask() {

                public void run() {

                    Frame.this.setOpacity(Frame.this.getOpacity() - 0.008f);
                    if (Frame.this.getOpacity() < 0.015) {
                        Frame.this.setOpacity(0.0075f);
                        timer.cancel();
                        Frame.this.dispose();
                    }

                }
            };

            timer.schedule(task, 0, 1);
            
            File file = new File("C:\\Users\\" + System.getProperty("user.name") + "\\Documents\\MyNotes\\data.bin");
        try ( // Dosya yoksa oluştur ve 1 yaz
                DataOutputStream dos = new DataOutputStream(new FileOutputStream(file))) {
            if (darkMode) {
                dos.writeInt(1);
            } else {
                dos.writeInt(2);
            }
        } catch (FileNotFoundException ex) {
        } catch (IOException ex) {
        }

        }
        


    }//GEN-LAST:event_closeButtonMouseClicked

    private void titleTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_titleTextActionPerformed

        titleText.setEditable(true);

    }//GEN-LAST:event_titleTextActionPerformed

    private void mainPanelFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_mainPanelFocusGained

    }//GEN-LAST:event_mainPanelFocusGained

    private void titleTextFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_titleTextFocusLost

        Frame.titleText.setCaretColor(Frame.titleText.getBackground());

    }//GEN-LAST:event_titleTextFocusLost

    private void mainPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mainPanelMouseClicked

    }//GEN-LAST:event_mainPanelMouseClicked

    private void mainPanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mainPanelMouseEntered

    }//GEN-LAST:event_mainPanelMouseEntered

    private void titleTextMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_titleTextMouseEntered


    }//GEN-LAST:event_titleTextMouseEntered

    private void titleTextMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_titleTextMouseClicked

        Frame.titleText.setCaretColor(new Color(30, 30, 30));

    }//GEN-LAST:event_titleTextMouseClicked

    private void deleteButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deleteButtonMouseEntered

        if (Operations.isNoteOpened) {
            deleteButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconlar/deleteR.png")));
        }

    }//GEN-LAST:event_deleteButtonMouseEntered

    private void deleteButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deleteButtonMouseExited

        if (Operations.isNoteOpened) {
            deleteButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconlar/deleteB.png")));
        }
    }//GEN-LAST:event_deleteButtonMouseExited

    private void deleteButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deleteButtonMouseClicked

        if (Operations.current.notName != null) {
            new File("C:\\Users\\" + System.getProperty("user.name") + "\\Documents\\MyNotes\\" + Operations.current.notName + ".txt").delete();
        }

        Operations.current.textArea.setBounds(0, 0, 0, 0);
        Operations.current.setBounds(0, 0, 0, 0);
        Operations.current.notName = Frame.titleText.getText();
        Frame.mainPanel.remove(Operations.current.backButton);
        try {
            Operations.anaEkraniCiz(Frame.notes);
        } catch (IOException ex) {
        }


    }//GEN-LAST:event_deleteButtonMouseClicked

    private void rightMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rightMouseEntered
        if (!Operations.isNoteOpened) {
            right.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconlar/sag.png")));
        }
    }//GEN-LAST:event_rightMouseEntered

    private void rightMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rightMouseExited

        if (!Operations.isNoteOpened) {
            right.setIcon(null);
        }

    }//GEN-LAST:event_rightMouseExited

    private void leftMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_leftMouseEntered

        if (!Operations.isNoteOpened) {
            left.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconlar/sol.png")));
        }

    }//GEN-LAST:event_leftMouseEntered

    private void leftMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_leftMouseExited

        if (!Operations.isNoteOpened) {
            left.setIcon(null);
        }

    }//GEN-LAST:event_leftMouseExited

    private void leftMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_leftMouseClicked
        if (notes.head.getX() < 125 && !buttonLeft) {
            buttonLeft = true;
            Timer timer = new Timer();
            count = 0;
            TimerTask task = new TimerTask() {

                public void run() {

                    NotePiece temp = Frame.notes.head;
                    if (count == ((NotePiece.NPW) + 49) / 5) {
                        timer.cancel();
                        buttonLeft = false;
                    }
                    while (temp != null) {
                        temp.setBounds(temp.getX() + 5, temp.getY(), NotePiece.NPW, NotePiece.NPH);
                        temp = temp.next;
                    }
                    count++;

                }
            };

            timer.schedule(task, 0, 1);

        }

    }//GEN-LAST:event_leftMouseClicked

    private void rightMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rightMouseClicked

        if (notes.tail.getX() > 125 && !buttonRight) {
            buttonRight = true;
            Timer timer = new Timer();
            count = 0;
            TimerTask task = new TimerTask() {

                public void run() {

                    NotePiece temp = Frame.notes.head;
                    if (count == ((NotePiece.NPW) + 49) / 5) {
                        timer.cancel();
                        buttonRight = false;
                    }
                    while (temp != null) {
                        temp.setBounds(temp.getX() - 5, temp.getY(), NotePiece.NPW, NotePiece.NPH);
                        temp = temp.next;
                    }
                    count++;

                }
            };

            timer.schedule(task, 0, 1);

        }

    }//GEN-LAST:event_rightMouseClicked

    private void themeSectionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_themeSectionMouseClicked

        if (!Operations.isNoteOpened) {
            if (darkMode) {
                themeSection.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconlar/light C.png")));
                Operations.changeTheme();
            } else {
                themeSection.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconlar/night B.png")));
                Operations.changeTheme();
            }
        }


    }//GEN-LAST:event_themeSectionMouseClicked

    private void themeSectionMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_themeSectionMouseEntered

        if (!Operations.isNoteOpened) {

            themeSection.setIcon(null);
            if (darkMode) {
                themeSection.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconlar/night C.png")));

            } else {
                themeSection.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconlar/light B.png")));
            }
        }

    }//GEN-LAST:event_themeSectionMouseEntered

    private void themeSectionMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_themeSectionMouseExited

        if (!Operations.isNoteOpened) {
            themeSection.setIcon(null);
            if (darkMode) {
                themeSection.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconlar/night B.png")));
            } else {
                themeSection.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconlar/light C.png")));
            }
        }


    }//GEN-LAST:event_themeSectionMouseExited

    public static void main(String args[]) {

        if (!new File(("C:\\Users\\" + System.getProperty("user.name") + "\\Documents\\MyNotes")).exists()) {
            new File(("C:\\Users\\" + System.getProperty("user.name") + "\\Documents\\MyNotes")).mkdir();
        }

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Frame().setVisible(true);
            }
        });

        System.out.println("11111111111main");

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel closeButton;
    public static javax.swing.JLabel deleteButton;
    private static javax.swing.JLabel left;
    public static javax.swing.JPanel mainPanel;
    private static javax.swing.JLabel right;
    public static javax.swing.JLabel themeSection;
    public static notlar_amaformlu.Title title;
    public static javax.swing.JTextField titleText;
    protected static javax.swing.JPanel top;
    // End of variables declaration//GEN-END:variables

}
