package shop.xianbao.modules.news.articleclass.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Service;
import shop.xianbao.common.exception.ErrorCode;
import shop.xianbao.common.exception.XianbaoException;
import shop.xianbao.common.service.impl.BaseServiceImpl;
import shop.xianbao.common.utils.ConvertUtils;
import shop.xianbao.common.utils.TreeUtils;
import shop.xianbao.modules.news.article.dao.ArticleDao;
import shop.xianbao.modules.news.article.entity.ArticleEntity;
import shop.xianbao.modules.news.articleclass.dao.ArticleClassDao;
import shop.xianbao.modules.news.articleclass.dto.ArticleClassDTO;
import shop.xianbao.modules.news.articleclass.dto.ArticleClassDetailDTO;
import shop.xianbao.modules.news.articleclass.dto.ArticleClassListDTO;
import shop.xianbao.modules.news.articleclass.dto.ArticleClassTreeDTO;
import shop.xianbao.modules.news.articleclass.entity.ArticleClassEntity;
import shop.xianbao.modules.news.articleclass.service.ArticleClassService;

import javax.annotation.Resource;
import java.util.List;

/**
 * 文章分类管理
 *
 * @author wangliangyuan wangliangyuan@ruitukeji.com
 * @since 1.0.0 2018-12-27
 */
@Service
public class ArticleClassServiceImpl extends BaseServiceImpl<ArticleClassDao, ArticleClassEntity> implements ArticleClassService {

    @Resource
    private ArticleDao articleDao;

    @Resource
    private ArticleClassDao articleClassDao;

    @Override
    public List<ArticleClassListDTO> list(Long pid) {
        QueryWrapper<ArticleClassEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(pid != null, "pid", pid);
        wrapper.orderByAsc("sort");
        List<ArticleClassEntity> list = baseDao.selectList(wrapper);

        return ConvertUtils.sourceToTarget(list, ArticleClassListDTO.class);
    }

    @Override
    public void save(ArticleClassDTO dto) {

        ArticleClassEntity entity = ConvertUtils.sourceToTarget(dto, ArticleClassEntity.class);
        insert(entity);
    }

    @Override
    public ArticleClassDetailDTO get(Long id) {
        ArticleClassEntity entity = baseDao.selectById(id);
        if (entity == null) {
            throw new XianbaoException(ErrorCode.DB_RECORD_NOT_EXISTS);
        }

        return ConvertUtils.sourceToTarget(entity, ArticleClassDetailDTO.class);
    }

    @Override
    public void update(ArticleClassDetailDTO dto) {
        ArticleClassEntity entity = ConvertUtils.sourceToTarget(dto, ArticleClassEntity.class);
        boolean result = updateById(entity);
        checkResult(result);
    }

    @Override
    public void delete(Long[] ids) {

        // 检查是否有文章属于该分类
        QueryWrapper<ArticleEntity> wrapper = new QueryWrapper<>();
        wrapper.in("class_id", ids);
        Integer articleCount = articleDao.selectCount(wrapper);
        if (articleCount > 0) {
            throw new XianbaoException(ErrorCode.CHILD_RECORD_EXISTS);
        } else {

            // 如果没有文章属于该分类,检查该分类是否存在二级分类
            QueryWrapper<ArticleClassEntity> queryWrapper = new QueryWrapper<>();
            queryWrapper.in("pid", ids);
            Integer count = baseDao.selectCount(queryWrapper);

            checkDelete(count, ids);
        }
    }

    @Override
    public List<ArticleClassTreeDTO> treeList() {
        QueryWrapper<ArticleClassEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("is_deleted", 0);
        wrapper.orderByAsc("sort");
        List<ArticleClassEntity> list = articleClassDao.selectList(wrapper);
        if (list == null || list.size() == 0) {
            return null;
        }

        List<ArticleClassTreeDTO> dtoList = ConvertUtils.sourceToTarget(list, ArticleClassTreeDTO.class);
        return TreeUtils.build(dtoList);
    }
}