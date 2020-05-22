package me.wubc.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @author wbc
 * @date 2020/5/22
 * @desc 前置过滤器，在路由到目标微服务前进行对请求打印
 */
@Slf4j
@Component
public class ZuulPreLogFilter extends ZuulFilter {
    /**
     * 1、前置过滤器
     * 2、路由过滤器：路由到目标微服务时执行
     * 3、POST过滤器：路由到目标微服务后执行
     * 3、错误过滤器：在发送错误时执行
     */
    public static final String FILTER_TYPE_PRE = "pre";
    public static final String FILTER_TYPE_ROUTE = "route";
    public static final String FILTER_TYPE_POST = "post";
    public static final String FILTER_TYPE_ERROR = "error";


    /**
     * 指定过滤器类型
     *
     * @return
     */
    @Override
    public String filterType() {
        return FILTER_TYPE_PRE;
    }

    /**
     * 过滤器执行顺序，数字越小，优先级越高
     *
     * @return
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 是否执行过滤： 通常可以设置哪些请求需要过滤
     *
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 具体执行过滤器的逻辑
     *
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        // 获取上下文
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();
        log.info("remote host: {}, method: {}, uri:{} ", request.getRemoteHost(), request.getMethod(), request.getRequestURI());

        return null;
    }
}
