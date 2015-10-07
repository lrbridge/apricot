package part1.part1.type;

import part1.Assignment;

import java.util.Set;

public interface AssignmentType {

    AssignmentType clone();

    boolean propagateAssignment(int variable, String value);

    Set<String> getOrderedDomainValues(int indexInSolution, AssignmentType assignmentType);

    int selectUnassignedVariable(AssignmentType assignmentType, Assignment assignment);

}
