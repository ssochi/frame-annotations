package org.ssochi.fa.models;

import lombok.Data;

import java.util.LinkedList;
import java.util.List;

@Data
public class PicUploadModelGroup {
	List<String> old = new LinkedList<>();
	List<String> upload = new LinkedList<>();
}
