package org.ssochi.fa.core.engine.interfaces;

import org.ssochi.fa.annotations.ViewModel;
import org.ssochi.fa.core.FAView;

import java.util.List;

public interface HtmlRender {
	String render(List<FAView> view, ViewModel viewModel);
}
