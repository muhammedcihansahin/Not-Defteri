package notlar_amaformlu;

import java.awt.Color;
import java.awt.Graphics;
import static notlar_amaformlu.NotePiece.NPH;
import static notlar_amaformlu.NotePiece.NPW;

public class Notes {

    NotePiece head;
    NotePiece tail;
    NotePiece bosNot;

    public Notes() {

        bosNot = new NotePiece();

        head = tail = bosNot;
        bosNot.setBounds(0, 0, NPW, NPH);

    }

}
