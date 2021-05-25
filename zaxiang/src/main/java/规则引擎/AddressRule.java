package 规则引擎;

import static 规则引擎.RuleConstant.MATCH_ADDRESS_START;

public class AddressRule extends AbstractRule {

    @Override
    public boolean execute(RuleDto dto) {
        System.out.println("AddressRule invoke!");
        if (dto.getAddress().startsWith(MATCH_ADDRESS_START)) {
            return true;
        }
        return false;
    }
}
