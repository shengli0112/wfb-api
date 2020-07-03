package shop.xianbao.modules.news.article.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.xianbao.common.exception.ErrorCode;
import shop.xianbao.common.exception.XianbaoException;
import shop.xianbao.common.page.PageData;
import shop.xianbao.common.service.impl.BaseServiceImpl;
import shop.xianbao.common.utils.ConvertUtils;
import shop.xianbao.modules.news.article.dao.ArticleDao;
import shop.xianbao.modules.news.article.dto.ArticleDTO;
import shop.xianbao.modules.news.article.dto.ArticleListDTO;
import shop.xianbao.modules.news.article.dto.ArticleUpdateDTO;
import shop.xianbao.modules.news.article.dto.ScrollingScreenDTO;
import shop.xianbao.modules.news.article.entity.ArticleEntity;
import shop.xianbao.modules.news.article.enums.ArticleStatusEnum;
import shop.xianbao.modules.news.article.service.ArticleService;
import shop.xianbao.modules.news.articleclass.dao.ArticleClassDao;
import shop.xianbao.modules.news.articleclass.entity.ArticleClassEntity;

import javax.annotation.Resource;
import java.util.*;

/**
 * 文章管理
 *
 * @author wangliangyuan wangliangyuan@ruitukeji.com
 * @since 1.0.0 2018-12-28
 */
@Service
public class ArticleServiceImpl extends BaseServiceImpl<ArticleDao, ArticleEntity> implements ArticleService {

    @Resource
    private ArticleClassDao articleClassDao;

    @Override
    public void save(ArticleDTO dto) {

        ArticleEntity entity = ConvertUtils.sourceToTarget(dto, ArticleEntity.class);

        String[] ids = dto.getClassIds().split(",");
        entity.setClassId(Long.parseLong(ids[ids.length - 1]));

        entity.setPublicTime(new Date());

        insert(entity);
    }

    @Override
    public ArticleUpdateDTO get(Long id) {

        ArticleEntity entity = baseDao.selectById(id);
        if (entity == null) {
            throw new XianbaoException(ErrorCode.DB_RECORD_NOT_EXISTS);
        }
        synchronized (entity) {
            entity.setClick(entity.getClick() + 1);
            baseDao.updateById(entity);
        }
        Long classId = entity.getClassId();
        ArticleClassEntity articleClassEntity = articleClassDao.selectById(classId);

        Long pid = articleClassEntity.getPid();
        if (pid != 0) {
            entity.setClassIds(pid + "," + classId);
        } else {
            entity.setClassIds(String.valueOf(classId));
        }
        return ConvertUtils.sourceToTarget(entity, ArticleUpdateDTO.class);
    }

    @Override
    public void update(ArticleUpdateDTO updateDTO) {
        ArticleEntity entity = ConvertUtils.sourceToTarget(updateDTO, ArticleEntity.class);

        String[] ids = updateDTO.getClassIds().split(",");
        entity.setClassId(Long.parseLong(ids[ids.length - 1]));

        updateById(entity);
    }

    @Override
    public PageData<ArticleListDTO> page(Map<String, Object> params) {

        //分页,由于是文章表和分类表的关联查询, wa 是SQL中表的别名
        IPage<ArticleEntity> page = getPage(params, "wa.sort", true);

        //查询
        List<ArticleEntity> list = baseDao.getList(params);

        // 遍历List转换枚举的值(很蠢的方法！暂时还没想到优雅的方法)
        if (CollectionUtils.isNotEmpty(list)) {
            for (int i = 0, size = list.size(); i < size; i++) {
                ArticleEntity entity = list.get(i);
                if (entity.getStatus().equals(ArticleStatusEnum.YES.getCode())) {
                    entity.setStatusValue(ArticleStatusEnum.YES.getMsg());
                } else {
                    entity.setStatusValue(ArticleStatusEnum.NO.getMsg());
                }

                String className = entity.getClassName();
                String pClassName = entity.getPClassName();
                if (StringUtils.isNotBlank(pClassName)) {
                    entity.setFinalClassName(pClassName + "-" + className);
                } else {
                    entity.setFinalClassName(className);
                }
            }
        }

        return getPageData(list, page.getTotal(), ArticleListDTO.class);
    }

    @Override
    @Transactional(rollbackFor = {RuntimeException.class})
    public void delete(Long[] ids) {
        baseDao.deleteBatchIds(Arrays.asList(ids));
    }

    @Override
    public List<ScrollingScreenDTO> getscrollingScreenList(Map<String, Object> params, String configNewsUrl) {

        List<ArticleEntity> entityList = baseDao.getList(params);
        List<ScrollingScreenDTO> dtoList = Lists.newArrayList();

        for (ArticleEntity entity : entityList) {
            ScrollingScreenDTO dto = new ScrollingScreenDTO();
            dto.setTitle(entity.getTitle());
            dto.setUrl(configNewsUrl + entity.getId());
            dtoList.add(dto);
        }
        return dtoList;
    }

    @Override
    public List<ArticleListDTO> getRecommendArticle() {
        QueryWrapper<ArticleEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("is_deleted", 0);
        wrapper.eq("status", 1);
        wrapper.eq("commend_flag", 1);
        wrapper.orderByDesc("click");
        List<ArticleEntity> list = this.selectList(wrapper);
        List<ArticleListDTO> articleListDTO = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(list)) {
            articleListDTO = ConvertUtils.sourceToTarget(list, ArticleListDTO.class);
        }
        return articleListDTO;
    }

}