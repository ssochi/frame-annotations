package org.ssochi.fa.enums;

import org.ssochi.fa.annotations.PreCondition;

import static org.ssochi.fa.utils.Constants.*;

public enum PreConditionAction {
    visible(V_IF),disable(V_DISABLE);

    String attr;
    PreConditionAction(String attr){
        this.attr = attr;
    }

    public String attr(){
        return attr;
    }
}
