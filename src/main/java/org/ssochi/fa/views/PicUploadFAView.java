package org.ssochi.fa.views;

import org.jsoup.nodes.Element;
import org.ssochi.fa.annotations.views.PicUploadView;
import org.ssochi.fa.core.FAField;
import org.ssochi.fa.core.exceptions.FARunningTimeException;
import org.ssochi.fa.core.engine.interfaces.DrawableVue;
import org.ssochi.fa.core.vue.VueStructureFunction;
import org.ssochi.fa.models.PicUploadModelGroup;
import org.ssochi.fa.utils.GsonUtil;
import org.jsoup.nodes.Document;


import static org.ssochi.fa.utils.Constants.*;

public class PicUploadFAView extends FormItemView {
    public static final PicUploadModelGroup group = new PicUploadModelGroup();
    public PicUploadFAView(FAField field) {
        super(field);
    }

    @Override
    protected void drawViewVue(DrawableVue vue) {
        vue.addForm(getFieldName(),GsonUtil.from(fieldValue,group));

        vue.add(dialogVisible(),false);
        vue.add(dialogImageUrl(),EMPTY_STRING);

        vue.addFunction(getPreviewFunction());
        vue.addFunction(getRemoveFunction());
    }

    private VueStructureFunction getRemoveFunction() {
        String body = "this." + fileList() + " = [];";
        return new VueStructureFunction(getRemoveMethodName(),new String[]{"file","fileList"},body);
    }
    private String fileList(){
        return FORM_SUFFIX + getFieldName() + ".group";
    }
    private String dialogVisible(){
        return getFieldName() + "dialogVisible";
    }
    private String dialogImageUrl(){
        return getFieldName() + "dialogImageUrl";
    }
    private VueStructureFunction getPreviewFunction() {
        String body = "this." + dialogVisible() + "=true;" +
                "this." + dialogImageUrl() + "=file.url;";
        return new VueStructureFunction(getPreviewMethodName(),new String[]{"file"}, body);
    }

    public String getPreviewMethodName(){
        return "handle_" + getFieldName() + "_preview";
    }
    public String getRemoveMethodName(){
        return "handle_" + getFieldName() + "_remove";
    }
    public PicUploadView picUploadView(){
        return (PicUploadView) getView();
    }

    @Override
    protected void drawFormItem(Document doc, Element formItem) {
        Element upload = doc.createElement(EL_UPLOAD);
        upload.attr(CLASS,"upload-demo");
        upload.attr(ACTION,"");
        upload.attr(V_ON_PREVIEW,getPreviewMethodName());
        upload.attr(V_ON_REMOVE,getRemoveMethodName());
        upload.attr(V_FILE_LIST,fileList());
        upload.attr(LIST_TYPE,"picture");

        Element button = doc.createElement(EL_BUTTON);
        button.attr(SIZE,"small");
        button.attr(TYPE,"primary");
        if (picUploadView().maxPictureCount() >= 0){
            button.attr(V_IF,fileList() + ".length <= " + (picUploadView().maxPictureCount() - 1));
        }
        button.text("点击上传");
        upload.appendChild(button);

        Element div = doc.createElement(DIV);
        div.attr(SLOT,"tip");
        div.attr(CLASS,EL_UPLOAD__TIP);
        div.text("只能上传jpg/png文件");
        upload.appendChild(div);

        formItem.appendChild(upload);

        Element dialog = doc.createElement(EL_DIALOG);
        dialog.attr(V_VISIBLE_SYNC, dialogVisible());
        Element img = doc.createElement(IMG);
        img.attr(WIDTH,"100%");
        img.attr(V_SRC,dialogImageUrl());
        dialog.appendChild(img);
        formItem.appendChild(dialog);
    }

    @Override
    public void check() {
        if (PicUploadModelGroup.class.isAssignableFrom(getFieldType()))
            return;
        throw new FARunningTimeException("PicUploadView 不支持这种Field类型(%s)",getFieldType().getSimpleName());
    }
}
