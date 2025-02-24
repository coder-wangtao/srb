package com.wangtao.service;

import com.wangtao.pojo.entity.Dict;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 数据字典 服务类
 * </p>
 *
 * @author wangtao
 * @since 2025-02-07
 */
@Service
public interface DictService extends IService<Dict> {

    void importDict(MultipartFile file);

    List<Dict> parent(Long pid);

    public boolean hasChildren(Long id);
}
