package com.cms.megaprint.service.intface;

import com.cms.megaprint.model.Picture;
import com.cms.megaprint.service.common.CrudService;

import java.util.List;

public interface PictureService extends CrudService<Picture, Long> {

    List<Long> getIdList();

}
