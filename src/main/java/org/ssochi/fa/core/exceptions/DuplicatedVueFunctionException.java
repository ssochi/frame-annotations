package org.ssochi.fa.core.exceptions;

public class DuplicatedVueFunctionException extends FARunningTimeException {
    public DuplicatedVueFunctionException(String functionName) {
        super("duplicated function find name = " + functionName);
    }
}
