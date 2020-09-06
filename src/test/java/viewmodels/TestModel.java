package viewmodels;

import lombok.Data;
import org.ssochi.fa.annotations.ViewModel;
import org.ssochi.fa.annotations.views.InputView;
import org.ssochi.fa.annotations.views.ViewProperties;

@ViewModel(title = "挂件配置")
@Data
public class TestModel {
    @ViewProperties(title = "挂件ID")
    @InputView(disable = true)
    private long id;
}
