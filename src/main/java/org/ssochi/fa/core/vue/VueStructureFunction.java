package org.ssochi.fa.core.vue;

import org.ssochi.fa.core.engine.interfaces.VueFunction;

public class VueStructureFunction implements VueFunction {
    private String functionName;
    private String[] functionArgs;
    private String functionBody;

    public VueStructureFunction(String functionName, String[] functionArgs, String functionBody) {
        this.functionName = functionName;
        this.functionArgs = functionArgs;
        this.functionBody = functionBody;
    }

    public VueStructureFunction(String functionName, String functionBody) {
        this.functionName = functionName;
        this.functionBody = functionBody;
        functionArgs = new String[0];
    }

    @Override
    public String render(){
        StringBuilder sb = new StringBuilder(functionName);
        sb.append('(');
        for (int i = 0; i < functionArgs.length; i++) {
            sb.append(functionArgs[i]);
            if (i != functionArgs.length - 1){
                sb.append(',');
            }
        }
        sb.append("){");
        sb.append(functionBody).append("}");
        return sb.toString();
    }

}
