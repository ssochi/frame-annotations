package org.ssochi.fa.core.engine.interfaces;

import org.ssochi.fa.annotations.ViewModel;
import org.ssochi.fa.core.FAField;

import java.lang.reflect.Field;

public interface FAFieldGenerator {
	FAField generate(ViewModel viewModel, Field field);
}
