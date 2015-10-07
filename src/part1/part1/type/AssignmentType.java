package part1.part1.type;

import part1.part1.assignment.BaseAssignment;

import java.util.Set;

public interface AssignmentType {

    AssignmentType clone();

    boolean propagateAssignment(Object variable, String value);

    Set<String> getOrderedDomainValues(Object variable, AssignmentType assignmentType);

    Object selectUnassignedVariable(AssignmentType assignmentType, BaseAssignment assignment);

}
