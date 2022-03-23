package com.keepthinker.spring.springbootexample.dubbo;

import org.apache.dubbo.rpc.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DubboInvokeFilter implements Filter {
    private Logger logger = LoggerFactory.getLogger(DubboInvokeFilter.class);

    public DubboInvokeFilter(){
        logger.info("DubboInvokeFilter is constructed");
    }

    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        // before filter ...
        logger.info("before invoke|{}", invocation);
        Result result = invoker.invoke(invocation);
        logger.info("after invoke|{}", invocation);
        // after filter ...
        return result;
    }
}