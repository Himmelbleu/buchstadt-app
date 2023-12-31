package com.buchstadt.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.buchstadt.exception.JdbcErrorException;
import com.buchstadt.mapper.BuchMapper;
import com.buchstadt.pojo.Buch;
import com.buchstadt.service.IBuchService;
import com.buchstadt.utils.Http;
import com.buchstadt.utils.R;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @description:
 * @package: com.buchstadt.service.impl
 * @author: zheng
 * @date: 2023/8/25
 */
@Service
@RequiredArgsConstructor
public class BuchServiceImpl extends ServiceImpl<BuchMapper, Buch> implements IBuchService {

    private final BuchMapper mapper;

    @Override
    public R<Buch> queryOne(Integer id) {
        return R.build(Http.OK, mapper.queryOne(id));
    }

    @Override
    public R<PageInfo<Buch>> queryAllByCondition(Map<String, Object> map) {
        try {
            Integer currPage = (Integer) map.get("currPage");
            Integer pageSize = (Integer) map.get("pageSize");

            if (Objects.isNull(currPage) && Objects.isNull(pageSize)) {
                currPage = 1;
                pageSize = 5;
            }

            PageHelper.startPage(currPage, pageSize);
            List<Buch> list = mapper.queryAll(map);
            return R.build(Http.OK, new PageInfo<>(list, pageSize));
        } finally {
            PageHelper.clearPage();
        }
    }

    @Transactional
    @Override
    public R<Void> updateOne(Buch data) {
        try {
            Integer f = mapper.updateOne(data);

            if (f == 0) return R.build(Http.NO, "更新书籍表单失败！");

            var as = data.getAuthors();
            var ps = data.getPreviews();
            var ts = data.getTags();

            if (as == null && ps == null && ts == null) return R.build(Http.OK, "更新书籍表单成功！");

            Integer f1 = mapper.updateTags(ts, data.getId());
            Integer f2 = mapper.updatePreviews(ps, data.getId());
            Integer f3 = mapper.updateAuthors(as, data.getId());

            if (f1 == 1 && f2 == 1 && f3 == 1)
                return R.build(Http.OK, "更新书籍表单成功！");
            else
                return R.build(Http.NO, "更新书籍表单失败！");
        } catch (Exception e) {
            throw new JdbcErrorException();
        }
    }

    @Transactional
    @Override
    public R<Void> insertOne(Buch data) {
        try {
            Integer f = mapper.insertOne(data);
            if (f == 0) return R.build(Http.NO, "插入书籍失败！");

            Integer id = data.getId();

            Integer f1 = mapper.insertAuthors(data.getAuthors(), id);
            Integer f2 = mapper.insertTags(data.getTags(), id);
            Integer f3 = mapper.insertPreviews(data.getPreviews(), id);

            if (f1 != 0 && f2 != 0 && f3 != 0)
                return R.build(Http.OK, "插入书籍成功！");
            else
                return R.build(Http.NO, "插入书籍失败！");
        } catch (Exception e) {
            throw new JdbcErrorException();
        }
    }

    @Transactional
    @Override
    public R<Void> insertOneAttach(Buch data) {
        Integer id = data.getId();

        var as = data.getAuthors();
        var ts = data.getTags();
        var ps = data.getPreviews();

        try {
            if (!as.isEmpty()) mapper.insertAuthors(as, id);
            if (!ts.isEmpty()) mapper.insertTags(ts, id);
            if (!ps.isEmpty()) mapper.insertPreviews(ps, id);

            return R.build(Http.OK, "插入附表数据成功！");
        } catch (Exception e) {
            throw new JdbcErrorException();
        }
    }

    @Override
    public R<Void> deleteOneTag(Integer tagId, Integer buchId) {
        Integer integer = mapper.deleteOneTag(tagId, buchId);
        if (integer != 0) {
            return R.build(Http.OK, "删除标签成功");
        } else return R.build(Http.NO, "删除标签失败");
    }

    @Override
    public R<Void> deleteOneAuthor(Integer authorId, Integer buchId) {
        Integer integer = mapper.deleteOneAuthor(authorId, buchId);
        if (integer != 0) {
            return R.build(Http.OK, "删除作者成功");
        } else return R.build(Http.NO, "删除做这个失败");
    }

    @Override
    public R<Void> deleteOnePreview(Integer previewId, Integer buchId) {
        Integer integer = mapper.deleteOnePreview(previewId, buchId);
        if (integer != 0) {
            return R.build(Http.OK, "删除预览图成功");
        } else return R.build(Http.NO, "删除预览图失败");
    }

    @Override
    public R<Void> deleteOne(Integer id) {
        Integer integer = mapper.deleteOne(id);
        if (integer != 0) {
            return R.build(Http.OK, "删除书籍成功");
        } else return R.build(Http.NO, "删除书籍失败");
    }

    @Override
    public R<PageInfo<Buch>> queryAllByPage(Integer pageSize, Integer currPage) {
        try {
            PageHelper.startPage(currPage, pageSize);
            List<Buch> list = super.query().list();
            PageInfo<Buch> pageInfo = new PageInfo<>(list, pageSize);
            return R.build(Http.OK, pageInfo);
        } finally {
            PageHelper.clearPage();
        }
    }
}
