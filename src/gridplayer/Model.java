/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gridplayer;

import MusicUtil.Player;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Line;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.SourceDataLine;

/**
 *
 * @author Eberhard Schneider
 */
class Model {
    
    
    
    Mixer.Info[] devices;
    SourceDataLine playbackLine;
    
    public Model() {
        
        devices = AudioSystem.getMixerInfo();
        
    }

    public ObservableList<Mixer.Info> getDevices() {
        ObservableList<Mixer.Info> list = FXCollections.observableArrayList();
                for ( Mixer.Info info : devices ) {
                    list.add( info );
                }
        return list;
    }

    public void getSourceDataLine( Mixer.Info info )  {
        
        SourceDataLine line = null;
        
        try {
            Mixer mixer = AudioSystem.getMixer( info );
            Line.Info[] lineInfos = mixer.getSourceLineInfo();
            AudioFormat format = new AudioFormat( 44100, 8, 2, false, false);
            line = AudioSystem.getSourceDataLine(format, info);
        } catch( LineUnavailableException e ) {
            System.err.println( " Couldn't get line for audio output. ");
        }
        
        playbackLine = line;
    }
    
    public void playMidiNote( int midiNote ) {
        if ( playbackLine == null ) {
            System.err.println("No audio line initialized");
            return;
        }
        
        Player player = new Player( playbackLine );
        player.playMidiNote( midiNote );
        
    }
    
     
    
}
