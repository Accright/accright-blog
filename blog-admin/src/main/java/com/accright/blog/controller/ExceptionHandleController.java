/**
 * MIT License
 * Copyright (c) 2018 yadong.zhang
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.accright.blog.controller;

import com.accright.blog.business.consts.CommonConst;
import com.accright.blog.business.enums.ResponseStatus;
import com.accright.blog.framework.exception.ZhydArticleException;
import com.accright.blog.framework.exception.ZhydCommentException;
import com.accright.blog.framework.exception.ZhydFileException;
import com.accright.blog.framework.exception.ZhydLinkException;
import com.accright.blog.framework.object.ResponseVO;
import com.accright.blog.util.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.reflect.UndeclaredThrowableException;

/**
 * 统一异常处理类<br>
 * 捕获程序所有异常，针对不同异常，采取不同的处理方式
 *
 * @date 2018/4/24 14:37
 * @since 1.0
 */
@Slf4j
@ControllerAdvice
public class ExceptionHandleController {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResponseVO handle(Throwable e) {
        if (e instanceof ZhydArticleException
                || e instanceof ZhydCommentException
                || e instanceof ZhydFileException
                || e instanceof ZhydLinkException) {
            return ResultUtil.error(e.getMessage());
        }
        if (e instanceof UndeclaredThrowableException) {
            e = ((UndeclaredThrowableException) e).getUndeclaredThrowable();
        }
        ResponseStatus responseStatus = ResponseStatus.getResponseStatus(e.getMessage());
        if (responseStatus != null) {
            log.error(responseStatus.getMessage());
            return ResultUtil.error(responseStatus.getCode(), responseStatus.getMessage());
        }
        e.printStackTrace(); // 打印异常栈
        return ResultUtil.error(CommonConst.DEFAULT_ERROR_CODE, ResponseStatus.ERROR.getMessage());
    }

}
