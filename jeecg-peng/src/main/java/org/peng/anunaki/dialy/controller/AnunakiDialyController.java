package org.peng.anunaki.dialy.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
import org.peng.anunaki.dialy.entity.AnunakiDialy;
import org.peng.anunaki.dialy.service.IAnunakiDialyService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.jeecg.common.system.base.controller.JeecgController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.apache.shiro.authz.annotation.RequiresPermissions;

 /**
 * @Description: 日记
 * @Author: jeecg-boot
 * @Date:   2023-03-21
 * @Version: V1.0
 */
@Api(tags="日记")
@RestController
@RequestMapping("/anunakiDialy")
@Slf4j
public class AnunakiDialyController extends JeecgController<AnunakiDialy, IAnunakiDialyService> {
	@Autowired
	private IAnunakiDialyService anunakiDialyService;
	
	/**
	 * 分页列表查询
	 *
	 * @param anunakiDialy
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "日记-分页列表查询")
	@ApiOperation(value="日记-分页列表查询", notes="日记-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<AnunakiDialy>> queryPageList(AnunakiDialy anunakiDialy,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<AnunakiDialy> queryWrapper = QueryGenerator.initQueryWrapper(anunakiDialy, req.getParameterMap());
		Page<AnunakiDialy> page = new Page<AnunakiDialy>(pageNo, pageSize);
		IPage<AnunakiDialy> pageList = anunakiDialyService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param anunakiDialy
	 * @return
	 */
	@AutoLog(value = "日记-添加")
	@ApiOperation(value="日记-添加", notes="日记-添加")
	//@RequiresPermissions("org.peng.anunaki.dialy:anunaki_dialy:add")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody AnunakiDialy anunakiDialy) {
		anunakiDialyService.save(anunakiDialy);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param anunakiDialy
	 * @return
	 */
	@AutoLog(value = "日记-编辑")
	@ApiOperation(value="日记-编辑", notes="日记-编辑")
	//@RequiresPermissions("org.peng.anunaki.dialy:anunaki_dialy:edit")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody AnunakiDialy anunakiDialy) {
		anunakiDialyService.updateById(anunakiDialy);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "日记-通过id删除")
	@ApiOperation(value="日记-通过id删除", notes="日记-通过id删除")
	//@RequiresPermissions("org.peng.anunaki.dialy:anunaki_dialy:delete")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		anunakiDialyService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "日记-批量删除")
	@ApiOperation(value="日记-批量删除", notes="日记-批量删除")
	//@RequiresPermissions("org.peng.anunaki.dialy:anunaki_dialy:deleteBatch")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.anunakiDialyService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "日记-通过id查询")
	@ApiOperation(value="日记-通过id查询", notes="日记-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<AnunakiDialy> queryById(@RequestParam(name="id",required=true) String id) {
		AnunakiDialy anunakiDialy = anunakiDialyService.getById(id);
		if(anunakiDialy==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(anunakiDialy);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param anunakiDialy
    */
    //@RequiresPermissions("org.peng.anunaki.dialy:anunaki_dialy:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, AnunakiDialy anunakiDialy) {
        return super.exportXls(request, anunakiDialy, AnunakiDialy.class, "日记");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    //@RequiresPermissions("org.peng.anunaki.dialy:anunaki_dialy:importExcel")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, AnunakiDialy.class);
    }

}
