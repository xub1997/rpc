package com.xub.rpc_example.service.impl;

import com.xub.rpc_example.service.CalcService;

/**
 * @author xub
 * @Name: CalcServiceImpl
 * @Description: TODO
 * @date 2020/2/15  11:00
 */
public class CalcServiceImpl implements CalcService {

    @Override
    public int add(int a, int b) {
        return a + b;
    }

    @Override
    public int minus(int a, int b) {
        return a - b;
    }
}
