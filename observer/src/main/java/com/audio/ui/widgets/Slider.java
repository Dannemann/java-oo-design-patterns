package com.audio.ui.widgets;

import com.audio.data.Audio;
import com.audio.observer.Observer;
import com.audio.observer.Subject;

import java.util.Map;

/**
 * <p>Imagine that in our audio editing app, the slider widget is something like the image at the bottom.</p>
 * <p>This is a <b>concrete observer/subscriber</b> UML actor.</p>
 * <p>Check <code>Knob</code> for an example with no comments.</p>
 * <p><img src="https://guitargearfinder.com/wp-content/uploads/2021/03/daw-controller-faders.jpg"></p>
 */
public class Slider implements Observer {

    // ...project-specific slider implementation...

    /**
     * A Slider can modify audio properties, but it is not intended to alter the
     * project settings data object. Therefore, we store a reference only to the
     * <code>audio</code> subject, as it is the only data object this widget needs
     * to modify. By updating the state of <code>audio</code>, all other dependent
     * widgets are notified and updated accordingly.
     */
    private Audio audio;

    @Override // Observer implementation.
    public void subjectUpdated(Subject subject) {
        Object state = subject.getState();
        if (state instanceof Map projectSettings) {
            // Changes in the project settings by the user can trigger updates across
            // multiple UI widgets. For instance, if the user changes the main input device
            // for recording, certain components may require a render update to reflect the
            // new configuration.
            // ...fetch properties from projectSettings and update the widget...
            System.out.println("Slider.subjectUpdated: Project settings changes rendered.");
        } else {
            // ...fetch properties from audio and update the widget...
            System.out.println("Slider.subjectUpdated: Audio properties changes rendered.");
        }
    }

    /**
     * <p>Business method to move the slider by an integer offset.</p>
     * <p>Note that this method does not need to handle UI updates. It will be handled
     * by <code>subjectUpdated</code> after we modify the audio state.</p>
     */
    public void slide(int value) {
        System.out.println("Slider.slide : Moved to value " + value + ". Modifying audio...");
        audio.modify(Map.of("someProperty", value)); // Subject state changed.
    }

    public void setAudio(Audio audio) {
        System.out.println("Slider.setAudio: Now references subject " + audio);
        this.audio = audio;
    }

    // ...project-specific slider implementation...

}