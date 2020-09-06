package org.ssochi.fa.core;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.ssochi.fa.annotations.PreCondition;
import org.ssochi.fa.annotations.ViewModel;
import org.ssochi.fa.annotations.views.ViewProperties;

import java.lang.annotation.Annotation;

@Getter
@Builder
@AllArgsConstructor
public class FAField {
    private Annotation[] contractAnnotations;
    private PreCondition preCondition;
    private ViewProperties viewProperties;
    private ViewModel viewModel;
    private Annotation view;
    private String fieldName;
    private Class<?> fieldType;
}
