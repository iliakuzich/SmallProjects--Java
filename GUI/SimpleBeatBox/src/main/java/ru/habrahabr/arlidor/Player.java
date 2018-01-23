package ru.habrahabr.arlidor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Track;
import javax.swing.JCheckBox;

/**
 *
 * @author Tony
 */
public class Player {

    private Sequencer sequencer;
    private Sequence sequence;
    private Track track;

    private int[] instruments = {35, 42, 46, 38, 49, 39, 50, 60, 70, 72, 64, 56,
        58, 47, 67, 63};
    private String[] instrumentNames = {"Bass Drum", "Closed Hi-Hat",
        "Open Hi-Hat", "Acoustic Snare", "Crash Cymbal", "Hand Clap",
        "High Tom", "Hi Bongo", "Maracas", "Whistle", "Low Conga",
        "Cowbell", "Vibraslap", "Low-mid Tom", "High Agogo",
        "Open Hi Conga"};
    private Map<String, boolean[]> otherSeqsMap = new HashMap<>();

    public void setUpMidi() {
        try {
            sequencer = MidiSystem.getSequencer();
            sequencer.open();
            sequence = new Sequence(Sequence.PPQ, 4);
            track = sequence.createTrack();
            sequencer.setTempoInBPM(120);
        } catch (InvalidMidiDataException | MidiUnavailableException ex) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public MidiEvent makeEvent(int comd, int chan, int one, int two, int tick) {
        MidiEvent event = null;
        try {
            ShortMessage a = new ShortMessage();
            a.setMessage(comd, chan, one, two);
            event = new MidiEvent(a, tick);

        } catch (InvalidMidiDataException ex) {
            Logger.getLogger(Player.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return event;
    }

    public void buildTrackAndStart(List<JCheckBox> checkboxList) {
        try {
            List<Integer> trackList;
            sequence.deleteTrack(track);
            track = sequence.createTrack();
            for (int i = 0; i < 16; i++) {
                trackList = new ArrayList<>();
                for (int j = 0; j < 16; j++) {
                    JCheckBox jc = (JCheckBox) checkboxList.get(j + (16 * i));
                    if (jc.isSelected()) {
                        int key = instruments[i];
                        trackList.add(key);
                    } else {
                        trackList.add(null);
                    }
                }
                makeTracks(trackList);
            }
            track.add(makeEvent(192, 9, 1, 0, 15));
            sequencer.setSequence(sequence);
            sequencer.setLoopCount(sequencer.LOOP_CONTINUOUSLY);
            sequencer.start();
            sequencer.setTempoInBPM(120);

        } catch (InvalidMidiDataException ex) {
            Logger.getLogger(Player.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void makeTracks(List<Integer> list) {
        Iterator it = list.iterator();
        for (int i = 0; i < 16; i++) {
            Integer num = (Integer) it.next();
            if (num != null) {
                int numKey = num;
                track.add(makeEvent(144, 9, numKey, 100, i));
                track.add(makeEvent(128, 9, numKey, 100, i + 1));
            }
        }
    }

    public Sequencer getSequencer() {
        return sequencer;
    }

    public void setSequencer(Sequencer sequencer) {
        this.sequencer = sequencer;
    }

    public Sequence getSequence() {
        return sequence;
    }

    public void setSequence(Sequence sequence) {
        this.sequence = sequence;
    }

    public Track getTrack() {
        return track;
    }

    public void setTrack(Track track) {
        this.track = track;
    }

    public int[] getInstruments() {
        return instruments;
    }

    public void setInstruments(int[] instruments) {
        this.instruments = instruments;
    }

    public String getInstrumentNames(int index) {
        return instrumentNames[index];
    }

    public void setInstrumentNames(String[] instrumentNames) {
        this.instrumentNames = instrumentNames;
    }

    public Map<String, boolean[]> getOtherSeqsMap() {
        return otherSeqsMap;
    }

    public void setOtherSeqsMap(Map<String, boolean[]> otherSeqsMap) {
        this.otherSeqsMap = otherSeqsMap;
    }

}
