import org.ssochi.fa.core.engine.LightFAEngine;
import org.ssochi.fa.core.engine.interfaces.FAEngine;
import viewmodels.PendentViewModel;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class Test {
    public static void main(String[] args) throws IOException {
        FAEngine engine = new LightFAEngine();
        PendentViewModel vm = new PendentViewModel();
//        vm.setId(1);
        String result = engine.generateHtmlContext(vm);
        FileOutputStream osm = new FileOutputStream(new File("auto.html"));
        osm.write(result.getBytes(StandardCharsets.UTF_8));
        osm.close();
    }
}
