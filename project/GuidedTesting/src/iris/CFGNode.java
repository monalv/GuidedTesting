package iris;

import java.util.ArrayList;

import org.apache.bcel.generic.InstructionHandle;

/**
 * @author utambe, mvora
 * 
 *         This class stores CFG node's Data Structure.
 */
class CFGNode {
	InstructionHandle node; /* Instruction Handle for the node. */
	CFGNode parent; /* Node's Parent. */
	CFGNode next; /* Next node. */
	ArrayList<InstructionHandle> children; /* List of Node's children. */

	/* Constructor. */
	CFGNode() {
		children = new ArrayList<InstructionHandle>();
	}
}
