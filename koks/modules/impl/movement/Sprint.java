package koks.modules.impl.movement;

import koks.Koks;
import koks.event.Event;
import koks.event.impl.AnimationEvent;
import koks.event.impl.EventUpdate;
import koks.modules.Module;
import koks.modules.impl.visuals.Animations;
import koks.modules.impl.world.ScaffoldWalk;
import koks.utilities.TimeUtil;
import koks.utilities.value.Value;
import koks.utilities.value.values.BooleanValue;
import koks.utilities.value.values.ModeValue;
import koks.utilities.value.values.NumberValue;
import koks.utilities.value.values.TitleValue;
import net.minecraft.client.Minecraft;

/**
 * @author avox | lmao | kroko
 * @created on 02.09.2020 : 21:08
 */
public class Sprint extends Module {

    public ModeValue<String> mode = new ModeValue<String>("Mode", "Intave",new String[] {"Intave","Legit"},this);

    public Sprint() {
        super("Sprint", Category.MOVEMENT);
    }

    @Override
    public void onEvent(Event event) {

        if (event instanceof AnimationEvent && Koks.getKoks().moduleManager.getModule(Animations.class).isToggled() && mc.thePlayer.isSprinting()) {
            AnimationEvent a = (AnimationEvent) event;

            a.setBody(0.4F, 0, 0);
            a.setLeftLegPos(0, -0.1F, 0.27F);
            a.setRightLegPos(0, -0.1F, 0.27F);
            a.setLeftArm(2F, 0, 0);
            a.setRightArm(2F, 0, 0);
            a.setHead(0.8F, 0, 0);
            a.setRightLeg(a.getRightLeg()[0] + 0.3F, a.getRightLeg()[1], a.getRightLeg()[2]);
            a.setLeftLeg(a.getLeftLeg()[0] + 0.3F, a.getLeftLeg()[1], a.getLeftLeg()[2]);

        }

        if (event instanceof EventUpdate) {
            if (mc.thePlayer.moveForward != 0 && !mc.gameSettings.keyBindBack.isKeyDown() && (mode.getSelectedMode().equalsIgnoreCase("Intave") || canSprint())) {
                mc.thePlayer.setSprinting(true);
            }else{
                mc.thePlayer.setSprinting(false);
            }
        }
    }

    public boolean canSprint() {
        ScaffoldWalk scaffoldWalk = Koks.getKoks().moduleManager.getModule(ScaffoldWalk.class);
        if(scaffoldWalk.isToggled() && scaffoldWalk.sprint.isToggled())
            return false;
        return true;
    }

    @Override
    public void onEnable() {
    }

    @Override
    public void onDisable() {

    }

}