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
package com.accright.blog.business.service.impl;

import com.accright.blog.business.entity.ArticleArchives;
import com.accright.blog.business.service.BizArticleArchivesService;
import com.accright.blog.persistence.beans.BizArticleArchives;
import com.accright.blog.persistence.mapper.BizArticleArchivesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 归档目录
 *

 * @date 2018/4/16 16:26
 * @since 1.0
 */
@Service
public class BizArticleArchivesServiceImpl implements BizArticleArchivesService {

    @Autowired
    private BizArticleArchivesMapper articleArchivesMapper;

    @Override
    public Map<String, List> listArchives() {
        List<BizArticleArchives> articleArchivesList = articleArchivesMapper.listArchives();
        if (CollectionUtils.isEmpty(articleArchivesList)) {
            return null;
        }
        Map<String, List> resultMap = new HashMap<String, List>();
        List<String> years = new LinkedList<>();
        for (BizArticleArchives bizArticleArchives : articleArchivesList) {
            String datetime = bizArticleArchives.getDatetime();
            String[] datetimeArr = datetime.split("-");
            String year = datetimeArr[0];
            String month = datetimeArr[1];
            String day = datetimeArr[2];
            String yearMonth = year + "-" + month;
            // 保存年
            addToList(years, year, null, null);
            // 保存月
            addToList(resultMap.get(year), month, resultMap, year);
            // 保存日
            addToList(resultMap.get(yearMonth), day, resultMap, yearMonth);
            // 保存文章
            addToList(resultMap.get(datetime), new ArticleArchives(bizArticleArchives), resultMap, datetime);
        }
        resultMap.put("years", years);
        return resultMap;
    }

    private <T> void addToList(List<T> list, T value, Map<String, List> map, String key) {
        if (null == list) {
            // 初始化后保存
            list = new LinkedList<>();
            list.add(value);
            if (null != map && !StringUtils.isEmpty(key)) {
                map.put(key, list);
            }
        } else {
            // 去重
            if (!list.contains(value)) {
                list.add(value);
            }
        }
    }
}
