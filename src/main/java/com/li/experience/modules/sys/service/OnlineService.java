package com.li.experience.modules.sys.service;

import com.li.experience.modules.sys.domain.OnlineVO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: experience
 * @description:
 * @author: Liwei
 * @create: 2019-07-16 15:08
 **/
public interface OnlineService {

    List<OnlineVO> list();

    void remove(String sessionId);
}
