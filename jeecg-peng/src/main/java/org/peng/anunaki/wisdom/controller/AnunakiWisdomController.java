package org.peng.anunaki.wisdom.controller;

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
import org.peng.anunaki.wisdom.entity.AnunakiWisdom;
import org.peng.anunaki.wisdom.service.IAnunakiWisdomService;

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
 * @Description: 人生感悟
 * @Author: jeecg-boot
 * @Date:   2023-04-11
 * @Version: V1.0
 */
@Api(tags="人生感悟")
@RestController
@RequestMapping("/anunakiWisdom")
@Slf4j
public class AnunakiWisdomController extends JeecgController<AnunakiWisdom, IAnunakiWisdomService> {
	@Autowired
	private IAnunakiWisdomService anunakiWisdomService;
	
	/**
	 * 分页列表查询
	 *
	 * @param anunakiWisdom
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "人生感悟-分页列表查询")
	@ApiOperation(value="人生感悟-分页列表查询", notes="人生感悟-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<AnunakiWisdom>> queryPageList(AnunakiWisdom anunakiWisdom,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<AnunakiWisdom> queryWrapper = QueryGenerator.initQueryWrapper(anunakiWisdom, req.getParameterMap());
		Page<AnunakiWisdom> page = new Page<AnunakiWisdom>(pageNo, pageSize);
		IPage<AnunakiWisdom> pageList = anunakiWisdomService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param anunakiWisdom
	 * @return
	 */
	@AutoLog(value = "人生感悟-添加")
	@ApiOperation(value="人生感悟-添加", notes="人生感悟-添加")
	//@RequiresPermissions("wisdom:anunaki_wisdom:add")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody AnunakiWisdom anunakiWisdom) {
		anunakiWisdomService.save(anunakiWisdom);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param anunakiWisdom
	 * @return
	 */
	@AutoLog(value = "人生感悟-编辑")
	@ApiOperation(value="人生感悟-编辑", notes="人生感悟-编辑")
	//@RequiresPermissions("wisdom:anunaki_wisdom:edit")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody AnunakiWisdom anunakiWisdom) {
		anunakiWisdomService.updateById(anunakiWisdom);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "人生感悟-通过id删除")
	@ApiOperation(value="人生感悟-通过id删除", notes="人生感悟-通过id删除")
	//@RequiresPermissions("wisdom:anunaki_wisdom:delete")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		anunakiWisdomService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "人生感悟-批量删除")
	@ApiOperation(value="人生感悟-批量删除", notes="人生感悟-批量删除")
	//@RequiresPermissions("wisdom:anunaki_wisdom:deleteBatch")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.anunakiWisdomService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "人生感悟-通过id查询")
	@ApiOperation(value="人生感悟-通过id查询", notes="人生感悟-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<AnunakiWisdom> queryById(@RequestParam(name="id",required=true) String id) {
		AnunakiWisdom anunakiWisdom = anunakiWisdomService.getById(id);
		if(anunakiWisdom==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(anunakiWisdom);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param anunakiWisdom
    */
    //@RequiresPermissions("wisdom:anunaki_wisdom:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, AnunakiWisdom anunakiWisdom) {
        return super.exportXls(request, anunakiWisdom, AnunakiWisdom.class, "人生感悟");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    //@RequiresPermissions("wisdom:anunaki_wisdom:importExcel")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, AnunakiWisdom.class);
    }

}
