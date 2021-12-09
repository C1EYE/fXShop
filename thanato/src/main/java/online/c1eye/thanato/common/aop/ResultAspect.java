package online.c1eye.thanato.common.aop;


import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import online.c1eye.thanato.common.configuration.CodeMessageConfiguration;
import online.c1eye.thanato.vo.UnifyResponseVO;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 处理返回结果为 UnifyResponseVO 的视图函数
 * 默认的返回均是英文，在此处通过code替换成中文
 *
 */
@Aspect
@Component
@Slf4j
public class ResultAspect {

    @Pointcut("execution(public * online.c1eye.thanato.controller..*.*(..))")
    public void handlePlaceholder() {
    }

    @AfterReturning(returning = "ret", pointcut = "handlePlaceholder()")
    public void doAfterReturning(Object ret) throws Throwable {
        if (ret instanceof UnifyResponseVO) {
            UnifyResponseVO result = (UnifyResponseVO) ret;
            int code = result.getCode();
            String message = CodeMessageConfiguration.getMessage(code);
            if (StrUtil.isNotBlank(message) && StrUtil.isBlank((CharSequence) result.getMessage())) {
                result.setMessage(message);
            }
        }
    }
}
