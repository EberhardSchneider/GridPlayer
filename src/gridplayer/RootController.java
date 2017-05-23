/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gridplayer;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.Observable;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Mixer;
import MusicUtil.util;
import MusicUtil.Player;

/**
 *
 * @author eberh_000
 */
public class RootController implements Initializable {
    
    
    
    @FXML
    ChoiceBox cb_devices;
    
    @FXML
    private TextArea ta_output;
    
    @FXML
    Slider slider_midiNote;
    
    @FXML
    Label label_midiNote;
    
    
    
    Model model;
    
    public RootController() {
        
        model = new Model();
        
    }
   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        ArrayList<Mixer.Info>  playbackDevices = new ArrayList<>();
        
        slider_midiNote.valueProperty().addListener( (observable) -> {
            int midiNote = (int)slider_midiNote.getValue();
            label_midiNote.setText( util.noteStringFromMidiNote( midiNote) );
        });
        
        for (Mixer.Info info : model.getDevices() ) {
            Mixer mixer = AudioSystem.getMixer( info );
            if ( mixer.getSourceLineInfo().length != 0) {
                playbackDevices.add(info);
            }
        }
        
        cb_devices.getItems().setAll( playbackDevices );
       
        cb_devices.valueProperty().addListener( ( Observable observable ) -> {
            ta_output.setText( cb_devices.getSelectionModel().getSelectedItem().toString() );
            model.getSourceDataLine( (Mixer.Info) cb_devices.getSelectionModel().getSelectedItem() );
        });
    }    
    
    @FXML
    public void handlePlayButtonClicked() {
        int midiNote = (int)slider_midiNote.getValue();
        model.playMidiNote( midiNote );

    }
    
   
    
}
