package part1.type;

import part1.assignment.Assignment;

import java.util.Set;

public interface VariableValueSelection {

    VariableValueSelection clone();

    boolean propagateAssignment(Object variable, String value, Assignment assignment);

    Set<String> getOrderedDomainValues(Object variable, VariableValueSelection assignmentType);

    Object selectUnassignedVariable(VariableValueSelection assignmentType, Assignment assignment);

}
