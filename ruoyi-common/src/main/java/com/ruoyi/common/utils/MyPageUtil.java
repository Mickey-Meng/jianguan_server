package com.ruoyi.common.utils;

import cn.jimmyshi.beanquery.BeanQuery;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ruoyi.common.core.domain.object.MyPageData;
import com.ruoyi.common.core.domain.object.Tuple2;
import com.ruoyi.common.core.domain.vo.NewBaseVo;
import com.ruoyi.common.core.mapper.BaseModelMapper;
import org.apache.commons.collections4.CollectionUtils;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 生成带有分页信息的数据列表
 *
 * @author Jerry
 * @date 2021-06-06
 */
public class MyPageUtil {

    private static final String DATA_LIST_LITERAL = "dataList";
    private static final String TOTAL_COUNT_LITERAL = "totalCount";

    /**
     * 用户构建带有分页信息的数据列表。
     *
     * @param dataList      数据列表，该参数必须是调用PageMethod.startPage之后，立即执行mybatis查询操作的结果集。
     * @param includeFields 结果集中需要返回到前端的字段，多个字段之间逗号分隔。
     * @return 返回只是包含includeFields字段的数据列表，以及结果集TotalCount。
     */
    public static <T> JSONObject makeResponseData(List<T> dataList, String includeFields) {
        JSONObject pageData = new JSONObject();
        pageData.put(DATA_LIST_LITERAL, BeanQuery.select(includeFields).from(dataList).execute());
        if (dataList instanceof Page) {
            pageData.put(TOTAL_COUNT_LITERAL, ((Page<?>)dataList).getTotal());
        }
        return pageData;
    }

    /**
     * 用户构建带有分页信息的数据列表。
     *
     * @param dataList 数据列表，该参数必须是调用PageMethod.startPage之后，立即执行mybatis查询操作的结果集。
     * @return 返回分页数据对象。
     */
    public static <T> MyPageData<T> makeResponseData(List<T> dataList) {
        MyPageData<T> pageData = new MyPageData<>();
        pageData.setDataList(dataList);
        if (dataList instanceof Page) {
            pageData.setTotalCount(((Page<?>)dataList).getTotal());
        }
        return pageData;
    }

    /**
     * 用户构建带有分页信息的数据列表。
     *
     * @param dataList   数据列表，该参数必须是调用PageMethod.startPage之后，立即执行mybatis查询操作的结果集。
     * @param totalCount 总数量。
     * @return 返回分页数据对象。
     */
    public static <T> MyPageData<T> makeResponseData(List<T> dataList, Long totalCount) {
        MyPageData<T> pageData = new MyPageData<>();
        pageData.setDataList(dataList);
        if (totalCount != null) {
            pageData.setTotalCount(totalCount);
        }
        return pageData;
    }

    /**
     * 用户构建带有分页信息的数据列表。
     *
     * @param dataList    实体对象数据列表。
     * @param modelMapper 实体对象到DomainVO对象的数据映射器。
     * @param <D>         DomainVO对象类型。
     * @param <T>         实体对象类型。
     * @return 返回分页数据对象。
     */
    public static <D, T> MyPageData<D> makeResponseData(List<T> dataList, BaseModelMapper<D, T> modelMapper) {
        long totalCount = 0L;
        if (CollectionUtils.isEmpty(dataList)) {
            // 这里需要构建分页数据对象，统一前端数据格式
            return MyPageData.emptyPageData();
        }
        if (dataList instanceof Page) {
            totalCount = ((Page<T>) dataList).getTotal();
        }
        return MyPageUtil.makeResponseData(modelMapper.fromModelList(dataList), totalCount);
    }

    /**
     * 用户构建带有分页信息的数据列表。
     *
     * @param responseData 第一个数据时数据列表，第二个是列表数量。
     * @param <T>          源数据类型。
     * @return 返回分页数据对象。
     */
    public static <T> MyPageData<T> makeResponseData(Tuple2<List<T>, Long> responseData) {
        return makeResponseData(responseData.getFirst(), responseData.getSecond());
    }

    /**
     * 私有构造函数，明确标识该常量类的作用。
     */
    private MyPageUtil() {
    }

    public static PageInfo getPageInfo(Collection<? extends NewBaseVo> dataCollection, int pageSize, int currentPageNum ){
        //计算总记录数
        int total = dataCollection.size();
        //pageHelper无效，手动分页，流操作：sorted排序、skip跳记录和limit限制显示记录数
        List<?> streamDataCollectionCollect = dataCollection
                .stream()
                .sorted(Comparator.comparing(NewBaseVo::getUpdateTime))
                .skip((currentPageNum - 1) * pageSize)
                .limit( pageSize)
                .collect(Collectors.toList());
        //计算总页数
        int pageSum = total %  pageSize == 0 ? total /  pageSize : total /  pageSize + 1;
        PageInfo resultPageInfo = new PageInfo<>(streamDataCollectionCollect);
        //总记录数
        resultPageInfo.setTotal(total);
        //总页数
        resultPageInfo.setPages(pageSum);
        // 当前页
        resultPageInfo.setPageNum(currentPageNum);
        //清除分页缓存
        PageHelper.clearPage();
        return resultPageInfo;
    }

    public static PageInfo getPageInfo(Stream<? extends NewBaseVo> dataStream, int total, int pageSize, int currentPageNum ){
        //计算总记录数
        //pageHelper无效，手动分页，流操作：sorted排序、skip跳记录和limit限制显示记录数
        List<?> streamDataCollectionCollect = dataStream.skip((currentPageNum - 1) * pageSize)
                .limit( pageSize)
                .collect(Collectors.toList());
        //计算总页数
        int pageSum = total %  pageSize == 0 ? total /  pageSize : total /  pageSize + 1;
        PageInfo resultPageInfo = new PageInfo<>(streamDataCollectionCollect);
        //总记录数
        resultPageInfo.setTotal(total);
        //总页数
        resultPageInfo.setPages(pageSum);
        // 当前页
        resultPageInfo.setPageNum(currentPageNum);
        //清除分页缓存
        PageHelper.clearPage();
        return resultPageInfo;
    }
}
