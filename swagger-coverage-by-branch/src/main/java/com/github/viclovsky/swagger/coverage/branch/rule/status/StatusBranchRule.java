package com.github.viclovsky.swagger.coverage.branch.rule.status;

import com.github.viclovsky.swagger.coverage.branch.model.Branch;
import com.github.viclovsky.swagger.coverage.branch.rule.core.BranchRule;
import io.swagger.models.Operation;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Base rule for status
 */
public abstract class StatusBranchRule extends BranchRule {

    public abstract List<Branch> processStatus(String statusCode);

    public List<Branch> createBranch(Operation operation){
        return operation.getResponses()
                .keySet()
                .stream()
                .map(this::processStatus)
                .filter(Objects::nonNull)
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }
}
