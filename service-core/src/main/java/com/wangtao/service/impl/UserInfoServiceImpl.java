package com.wangtao.service.impl;

import com.wangtao.pojo.entity.UserInfo;
import com.wangtao.mapper.UserInfoMapper;
import com.wangtao.service.UserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户基本信息 服务实现类
 * </p>
 *
 * @author wangtao
 * @since 2025-02-07
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {

}
