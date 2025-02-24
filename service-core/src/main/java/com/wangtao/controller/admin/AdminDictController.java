package com.wangtao.controller.admin;
import com.alibaba.excel.EasyExcel;
import com.wangtao.exception.SrbException;
import com.wangtao.pojo.entity.Dict;
import com.wangtao.pojo.vo.DictExcelVo;
import com.wangtao.result.ResponseEnum;
import com.wangtao.result.ResponseVo;
import com.wangtao.service.DictService;
import com.wangtao.util.SrbAssert;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <p>
 * 数据字典 前端控制器
 * </p>
 *
 * @author wangtao
 * @since 2025-02-07
 */
@RestController
@RequestMapping("/admin/core/dict")
@Api(tags = "数据字典管理模块")
public class AdminDictController {
    @Resource
    DictService dictService;

    @ApiOperation("新增数据字典")
    @PostMapping
    public ResponseVo save(@RequestBody Dict dict) {
        dictService.save(dict);
        return ResponseVo.ok().message("新增成功");
    }

    @ApiOperation("更新数据字典")
    @PutMapping
    public ResponseVo updateById(@RequestBody Dict dict) {
        dictService.updateById(dict);
        return ResponseVo.ok().message("更新成功");
    }
    @ApiOperation("根据id查询数据字典")
    @GetMapping("{id}")
    public ResponseVo getById(@PathVariable("id") Long id) {
        return ResponseVo.ok().data("item",dictService.getById(id));
    }

    @ApiOperation("根据id删除")
    @DeleteMapping("{id}")
    public ResponseVo deleteById(@PathVariable("id") Long id) {
        //如果以要删除的数据字典有下一级 不能删除
        SrbAssert.assertTrue(dictService.hasChildren(id),ResponseEnum.HAS_CHILDREN_ERROR);
        //删除
        SrbAssert.assertNotTrue(dictService.removeById(id),ResponseEnum.ERROR);
        return ResponseVo.ok().message("删除成功");
    }

    @ApiOperation("查询数据字典和它是否有下一级数据字典的集合")
    @GetMapping("parent/{pid}")  //使用pid查询一组数据字典（以及数据字典pid=0）
    public ResponseVo parent(@PathVariable("pid") Long pid) {
        List<Dict> dicts = dictService.parent(pid);
        return ResponseVo.ok().data("items", dicts);
    }

    //处理上传的数组字典excel:解析excel数据保存到数据库
    @ApiOperation("导入数据字典")
    @PostMapping("import")
    public ResponseVo importDict(MultipartFile file) {
        dictService.importDict(file);
        return ResponseVo.ok().message("文件上传成功");
    }

    @ApiOperation("导出数据字典")
    @GetMapping("export")
    public void exportDict(HttpServletResponse response) {
       try {
           response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
           response.setCharacterEncoding("utf-8");
           // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
           String fileName= URLEncoder.encode("数据字典_" + UUID.randomUUID(),"UTF-8").replaceAll("\\+","%20");
           response.setHeader("Content-disposition","attachment;filename*=utf-8''"+fileName+".xlsx");
           List<DictExcelVo> dictExcelVo = dictService.list().stream().map(dict -> {
               DictExcelVo vo = new DictExcelVo();
               BeanUtils.copyProperties(dict, vo);
               return vo;
           }).collect(Collectors.toList());
           EasyExcel.write(response.getOutputStream()).sheet("数据字典").head(DictExcelVo.class).doWrite(dictExcelVo);
       }catch (Exception e){
           e.printStackTrace();
           throw new SrbException(ResponseEnum.EXPORT_DATA_ERROR);
       }
//        dictService.exportDict(file);
    }


}

