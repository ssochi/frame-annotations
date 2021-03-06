package viewmodels;

import lombok.Data;
import org.ssochi.fa.annotations.PreCondition;
import org.ssochi.fa.annotations.views.VisibleMidView;
import org.ssochi.fa.annotations.views.*;
import org.ssochi.fa.annotations.ViewModel;
import org.ssochi.fa.enums.PreConditionAction;
import org.ssochi.fa.models.PicUploadModelGroup;

@ViewModel(title = "挂件配置",submitUrl = "233")
@Data
public class PendentViewModel {
    @ViewProperties(title = "挂件ID")
    @InputView(disable = true)
    private long id;

    @ViewProperties(title = "挂件名称", prev = "id")
    @InputView
    private String name;

    @ViewProperties(title = "挂件描述", prev = "name")
    @InputView(textArea = true)
    private String description;

    @ViewProperties(title = "挂件图片", prev = "description")
    @PicUploadView(maxPictureCount = 3,width = 300,height = 150)
    private PicUploadModelGroup image;

    @ViewProperties(title = "是否设置有效时间", prev = "image")
    @SwitchView
    private boolean useTimeSelector;

    @ViewProperties(title = "起始时间", prev = "useTimeSelector")
    @TimeSelectorView
    @PreCondition(previous = "useTimeSelector", condition = " == true", action = PreConditionAction.visible)
    private String startTime;

    @ViewProperties(title = "结束时间", prev = "startTime")
    @TimeSelectorView
    @PreCondition(previous = "useTimeSelector", condition = " == true", action = PreConditionAction.visible)
    private String endTime;

    @ViewProperties(title = "允许访问的mids", prev = "endTime")
    @VisibleMidView
    private String miIds;
}
