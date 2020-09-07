import org.ssochi.fa.core.engine.LightFAEngine;
import org.ssochi.fa.core.engine.interfaces.FAEngine;
import org.ssochi.fa.models.PicUploadModel;
import org.ssochi.fa.models.PicUploadModelGroup;

import viewmodels.PendentViewModel;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class Test {
    public static void main(String[] args) throws IOException {
        FAEngine engine = new LightFAEngine();
        PendentViewModel vm = new PendentViewModel();
        vm.setId(1);
        vm.setName("我是米粉");
        vm.setUseTimeSelector(true);
        PicUploadModelGroup group = new PicUploadModelGroup();
        PicUploadModel model = new PicUploadModel();
        model.setFileName("测试");
        model.setUrl("https://ts.market.mi-img.com/download/MiVip/04613471bc2acfa20b8b1897fb704f7a4d541473f");
        group.getGroup().add(model);
        vm.setImage(group);
        String result = engine.generateHtmlContext(vm);
        FileOutputStream osm = new FileOutputStream(new File("auto.html"));
        osm.write(result.getBytes(StandardCharsets.UTF_8));
        osm.close();
    }
}
