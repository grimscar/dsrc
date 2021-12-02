package script.working.vegvisir;

import script.obj_id;

public class kneel_easter_egg extends script.base_script {
    public static final String WHISPER = new String("You hear a faint whisper from Mother Jungle in your mind...");

    public kneel_easter_egg() {
    }

    public int OnChangedPosture(obj_id self, int oldPosture, int newPosture) throws InterruptedException {
        obj_id target = getIntendedTarget(self);
        int targetSpecies = getSpecies(target);

        if (newPosture == POSTURE_CROUCHED && oldPosture != POSTURE_CROUCHED && target != self
                && targetSpecies == SPECIES_ITHORIAN) {
            sendSystemMessage(self, WHISPER, null);
            return SCRIPT_OVERRIDE;
        }
        return SCRIPT_CONTINUE;
    }
}