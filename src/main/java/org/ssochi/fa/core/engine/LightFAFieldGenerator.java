package org.ssochi.fa.core.engine;

import org.ssochi.fa.annotations.PreCondition;
import org.ssochi.fa.annotations.ViewModel;
import org.ssochi.fa.annotations.views.View;
import org.ssochi.fa.annotations.views.ViewProperties;
import org.ssochi.fa.core.FAField;
import org.ssochi.fa.core.engine.interfaces.FAFieldGenerator;
import org.ssochi.fa.core.exceptions.FARunningTimeException;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class LightFAFieldGenerator implements FAFieldGenerator {
    @Override
    public FAField generate(ViewModel viewModel, Field field) {
        Annotation[] annotations = field.getAnnotations();

        PreCondition preCondition = null;
        ViewProperties viewProperties = null;
        Annotation view = null;
        String fieldName = field.getName();
        Class<?> fieldType = field.getType();

        for (Annotation annotation : annotations) {
            if (annotation instanceof PreCondition){
                preCondition = (PreCondition) annotation;
            }
            if (annotation instanceof ViewProperties){
                viewProperties = (ViewProperties) annotation;
            }
            Annotation[] subAnnotations = annotation.annotationType().getAnnotations();
            for (Annotation sa : subAnnotations) {
                if (sa instanceof View){
                    view = annotation;
                    break;
                }
            }
        }

        if (viewProperties == null){
            throw new FARunningTimeException("ViewProperties not find in viewModel(%s) field(%s)",viewModel.title(),fieldName);
        }
        if (view == null){
            throw new FARunningTimeException("view not find in viewModel(%s) field(%s)," +
                    "make sure your annotation marked with annotation View",viewModel.title(),fieldName);
        }
        return FAField.builder().contractAnnotations(annotations).fieldName(fieldName)
                .fieldType(fieldType).preCondition(preCondition).view(view)
                .viewModel(viewModel).viewProperties(viewProperties).build();
    }
}
