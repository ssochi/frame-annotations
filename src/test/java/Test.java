import org.ssochi.fa.core.engine.LightFAEngine;
import org.ssochi.fa.core.engine.interfaces.FAEngine;
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
        group.getOld().add("https://fuss10.elemecdn.com/3/63/4e7f3a15429bfda99bce42a18cdd1jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/100");
        vm.setImage(group);
        String result = engine.generateHtmlContext(vm);
        FileOutputStream osm = new FileOutputStream(new File("auto.html"));
        osm.write(result.getBytes(StandardCharsets.UTF_8));
        osm.close();
    }
}
