package com.wangtao.service.impl;

import com.wangtao.pojo.entity.BorrowInfo;
import com.wangtao.mapper.BorrowInfoMapper;
import com.wangtao.service.BorrowInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 借款信息表 服务实现类
 * </p>
 *
 * @author wangtao
 * @since 2025-02-07
 */
@Service
public class BorrowInfoServiceImpl extends ServiceImpl<BorrowInfoMapper, BorrowInfo> implements BorrowInfoService {
    //@Service 标注的类 容器初始化会创建对象添加到容器

}
