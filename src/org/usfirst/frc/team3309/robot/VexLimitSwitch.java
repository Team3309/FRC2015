/* For specifically Vex Limit Switches only.  Reverses get to matched pressed and not pressed*/
package org.usfirst.frc.team3309.robot;

import edu.wpi.first.wpilibj.DigitalInput;

public class VexLimitSwitch extends DigitalInput {

	public VexLimitSwitch(int channel) {
		super(channel);
		// TODO Auto-generated constructor stub
	}

	public boolean isPressed() {
		return !get();
	}

}
