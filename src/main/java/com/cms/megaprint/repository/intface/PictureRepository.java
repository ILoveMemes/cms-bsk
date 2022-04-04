package com.cms.megaprint.repository.intface;

import com.cms.megaprint.model.Picture;

import java.util.List;

public interface PictureRepository extends CrudRepo<Picture, Long> {

    List<Long> getIdList();

}
