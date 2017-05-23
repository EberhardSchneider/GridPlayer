package MusicUtil;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author eberh_000
 */
public class util {
    
    final static String[] NAMES = { "C", "C#", "D", "Eb", "E", "F", "F#", "G", "Ab", "A", "Bb","B" };
    
    public static double frequencyFromMidiNote( int midiNote, double concertA ) {
        if ( midiNote < 0 || midiNote > 127)
            return -1;
        
        double semitoneRatio = Math.pow( 2, 1/12.0 );
        double lowA = concertA / 2.0;
        double c5 = lowA * Math.pow( semitoneRatio, 3);
        double c0 = c5 * Math.pow( .5, 5);
        
        return c0 * Math.pow( semitoneRatio, midiNote );
    }
    
    public static int midiNoteFromFrequency( int frequency, double concertA ) {
        double semitoneRatio = Math.pow( 2, 1/12.0 );
        double lowA = concertA / 2.0;
        double c5 = lowA * Math.pow( semitoneRatio, 3);
        double c0 = c5 * Math.pow( .5, 5);
        
        double  fracMidiNote = Math.log( frequency / c0 ) / Math.log( semitoneRatio );
        int midiNote = (int) ( fracMidiNote + .5 );
        
        return midiNote;
    }
    
    public static String noteStringFromMidiNote( int midiNote ) {
        String result = "";
        
        int nameIndex = midiNote % 12;
        String noteName = NAMES[ nameIndex ];
        
        int octaveIndex = (int) Math.floor( (double)midiNote / 12.0 ) - 2;
        
        return noteName + String.valueOf( octaveIndex );
    }
    
}
