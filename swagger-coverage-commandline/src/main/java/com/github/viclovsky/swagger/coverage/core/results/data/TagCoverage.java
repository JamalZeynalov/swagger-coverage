package com.github.viclovsky.swagger.coverage.core.results.data;

import com.github.viclovsky.swagger.coverage.core.model.OperationKey;
import io.swagger.models.Tag;

import java.util.HashSet;
import java.util.Set;

public class TagCoverage {

    protected Tag tag;
    protected Set<OperationKey> operations = new HashSet<>();
    protected CoverageCounter coverageCounter = new CoverageCounter();
    protected ConditionCounter conditionCounter = new ConditionCounter();
    protected long callCounts = 0;
    protected CoverageState state = CoverageState.EMPTY;

    public TagCoverage(Tag tag) {
        this.tag = tag;
    }

    public TagCoverage updateState(){
        if (conditionCounter.getCovered() == 0){
            state = CoverageState.EMPTY;
        } else {
            if (conditionCounter.getAll() == conditionCounter.getCovered()){
                state = CoverageState.FULL;
            } else {
                state = CoverageState.PARTY;
            }
        }

        return this;
    }

    public TagCoverage addOperation(OperationKey operation){
        this.operations.add(operation);
        return this;
    }

    public TagCoverage incrementByState(CoverageState state){
        this.callCounts++;
        this.coverageCounter.incrementByState(state);
        return this;
    }

    public TagCoverage updateAllConditionCount(long count){
        conditionCounter.updateAll(count);
        return this;
    }

    public TagCoverage updateCoveredConditionCount(long count){
        conditionCounter.updateCovered(count);
        return this;
    }

    public Tag getTag() {
        return tag;
    }

    public TagCoverage setTag(Tag tag) {
        this.tag = tag;
        return this;
    }

    public CoverageCounter getCoverageCounter() {
        return coverageCounter;
    }

    public TagCoverage setCoverageCounter(CoverageCounter coverageCounter) {
        this.coverageCounter = coverageCounter;
        return this;
    }

    public Set<OperationKey> getOperations() {
        return operations;
    }

    public TagCoverage setOperations(Set<OperationKey> operations) {
        this.operations = operations;
        return this;
    }

    public ConditionCounter getConditionCounter() {
        return conditionCounter;
    }

    public TagCoverage setConditionCounter(ConditionCounter conditionCounter) {
        this.conditionCounter = conditionCounter;
        return this;
    }

    public long getCallCounts() {
        return callCounts;
    }

    public TagCoverage setCallCounts(long callCounts) {
        this.callCounts = callCounts;
        return this;
    }

    public CoverageState getState() {
        return state;
    }

    public TagCoverage setState(CoverageState state) {
        this.state = state;
        return this;
    }

    @Override
    public String toString() {
        return "TagCoverage{" +
                "tag='" + tag.getName() + '\'' +
                ", operations=" + operations.toString() +
                ", coverageCounter=" + coverageCounter.toString() +
                ", conditionCounter=" + conditionCounter.toString() +
                '}';
    }
}
