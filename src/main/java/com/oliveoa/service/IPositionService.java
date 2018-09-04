package com.oliveoa.service;

import com.oliveoa.common.ServerResponse;
import com.oliveoa.pojo.Position;

/**
 * Created by Lee on 2018/5/24.
 */
public interface IPositionService {
    ServerResponse add_position(Position position);
    ServerResponse get_position(String dcid);
    ServerResponse update_position(Position position);
    ServerResponse delete_position(String pcid);
    ServerResponse get_position_dateils(String pcid);
}
