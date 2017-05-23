package MusicUtil;


import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author eberh_000
 */
public class Player {
    
    static final double TWOPI = 6.28318530717958647692f;
    
    SourceDataLine line;
    
    public Player (SourceDataLine line) {
        this.line = line;
    }
    
    public void playMidiNote( int midiNote ) {
        double frequency = util.frequencyFromMidiNote(midiNote, 442.0 );
        
        try { 
            line.open();
        } catch (LineUnavailableException ex) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        line.write( sineGenerator( frequency, 44100, 70000 ), 0, 70000 );
        line.start();
        line.drain();
        line.stop();
        
        
    }
    
    private byte[] sineGenerator( double freq, int sampleRate, int length ) {
        byte[] buffer = new byte[ length ];
        for (int i = 0; i < length; i++) {
            buffer[i] = (byte)Math.floor( Math.sin( (double)i * freq / sampleRate * TWOPI ) * 255 + 125 ) ;
        }
        return buffer;
    } 
}
