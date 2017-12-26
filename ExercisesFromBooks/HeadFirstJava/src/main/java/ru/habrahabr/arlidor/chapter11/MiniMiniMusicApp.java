package ru.habrahabr.arlidor.chapter11;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.midi.*;

public class MiniMiniMusicApp {   // this is the first one

    public static void main(String[] args) {
        MiniMiniMusicApp mini = new MiniMiniMusicApp();
        mini.play();
    }

    public void play() {

        try {
            try (Sequencer sequencer = MidiSystem.getSequencer()) {
                sequencer.open();

                Sequence seq = new Sequence(Sequence.PPQ, 4);
                Track track = seq.createTrack();

                MidiEvent event = null;

                ShortMessage a = new ShortMessage();
                a.setMessage(144, 1, 44, 100);
                MidiEvent noteOn = new MidiEvent(a, 1);
                track.add(noteOn);

                ShortMessage b = new ShortMessage();
                b.setMessage(128, 1, 44, 100);
                MidiEvent noteOff = new MidiEvent(b, 16);
                track.add(noteOff);

                sequencer.setSequence(seq);

                sequencer.start();
                Thread.sleep(1000);
            }
            System.exit(0);
        } catch (MidiUnavailableException | InvalidMidiDataException | InterruptedException ex) {
            Logger.getLogger(MiniMiniMusicApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
