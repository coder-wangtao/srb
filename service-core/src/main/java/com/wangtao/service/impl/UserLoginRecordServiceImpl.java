package com.wangtao.service.impl;

import com.wangtao.pojo.entity.UserLoginRecord;
import com.wangtao.mapper.UserLoginRecordMapper;
import com.wangtao.service.UserLoginRecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户登录记录表 服务实现类
 * </p>
 *
 * @author wangtao
 * @since 2025-02-07
 */
@Service
public class UserLoginRecordServiceImpl extends ServiceImpl<UserLoginRecordMapper, UserLoginRecord> implements UserLoginRecordService {

}
