package org.peng.modules.anunaki.test.service.impl;

import org.peng.modules.anunaki.test.entity.TestDemo;
import org.peng.modules.anunaki.test.mapper.TestDemoMapper;
import org.peng.modules.anunaki.test.service.ITestDemoService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 测试用户表
 * @Author: jeecg-boot
 * @Date:   2023-03-19
 * @Version: V1.0
 */
@Service
public class TestDemoServiceImpl extends ServiceImpl<TestDemoMapper, TestDemo> implements ITestDemoService {

}
