package com.wangtao.service.impl;

import com.wangtao.pojo.entity.UserAccount;
import com.wangtao.mapper.UserAccountMapper;
import com.wangtao.service.UserAccountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户账户 服务实现类
 * </p>
 *
 * @author wangtao
 * @since 2025-02-07
 */
@Service
public class UserAccountServiceImpl extends ServiceImpl<UserAccountMapper, UserAccount> implements UserAccountService {

}
