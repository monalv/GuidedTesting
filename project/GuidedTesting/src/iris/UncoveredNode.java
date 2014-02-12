package iris;

import java.util.ArrayList;

import org.apache.bcel.generic.InstructionHandle;

/**
 * @author utambe, mvora
 * 
 *         This Class stores Uncovered Node's Data Structure.
 */
class UncoveredNode {

	InstructionHandle instHandle; /* Instruction handle for the node. */
	ArrayList<InstructionHandle> children; /* List of Node's Children. */
	ArrayList<InstructionHandle> parent; /* List of Node's Parent. */

	/* Constructor. */
	UncoveredNode() {
		children = new ArrayList<InstructionHandle>();
		parent = new ArrayList<InstructionHandle>();
	}
}
