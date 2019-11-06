package com.sugon.gsq.scs.authorization.resolvers;

import com.sugon.gsq.scs.authorization.annotation.CurrentUser;
import com.sugon.gsq.scs.authorization.security.SecurityUtils;
import com.sugon.gsq.scs.entity.IdentifyUserEntity;
import com.sugon.gsq.scs.service.IdentifyUserService;
import com.sugon.gsq.scs.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

/**
 * 增加方法注入，将含有CurrentUser注解的方法参数注入当前登录用户
 * @see com.sugon.gsq.scs
 * @author sugon
 * @date 2017/9/03.
 */
@Component
public class CurrentUserMethodArgumentResolver implements HandlerMethodArgumentResolver {

    @Autowired
    private IdentifyUserService identifyUserService;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        //如果参数类型是User并且有CurrentUser注解则支持
        if (parameter.getParameterType().isAssignableFrom(IdentifyUserEntity.class) &&
                parameter.hasParameterAnnotation(CurrentUser.class)) {
            return true;
        }
        return false;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        //取出鉴权时存入的登录用户Id
        String currentUserLogin = SecurityUtils.getCurrentUserLogin();
        if (currentUserLogin != null) {
            //从数据库中查询并返回
            return identifyUserService.findByUsername(currentUserLogin);
        }
        throw new MissingServletRequestPartException(Constants.CURRENT_USER_LOGIN);
    }
}
