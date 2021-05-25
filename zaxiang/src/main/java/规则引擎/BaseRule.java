package 规则引擎;

public interface BaseRule {

    boolean execute(RuleDto dto);
}