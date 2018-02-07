package com.free.service.impl;

import org.springframework.stereotype.Service;

import com.free.common.BaseServiceImpl;
import com.free.model.Test;
import com.free.service.ITestService;

@Service
public class TestServiceImpl extends BaseServiceImpl<Test, String> implements ITestService{

}
