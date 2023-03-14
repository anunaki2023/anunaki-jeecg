package org.peng.anunaki.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.jeecg.common.system.base.controller.JeecgController;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.modules.demo.org.peng.anunaki.entity.AnunakiWiki;
import org.peng.anunaki.service.IAnunakiWikiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

 /**
 * @Description: 百科知识
 * @Author: pengweitao
 * @Date:   2023-03-10
 * @Version: V1.0
 */
@Api(tags="wiki")
@RestController
@RequestMapping("/anunakiWiki")
@Slf4j
public class AnunakiWikiController extends JeecgController<AnunakiWiki, IAnunakiWikiService> {
	@Autowired
	private IAnunakiWikiService anunakiWikiService;
	
	/**
	 * 分页列表查询
	 *
	 * @param anunakiWiki
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "百科知识-分页列表查询")
	@ApiOperation(value="百科知识-分页列表查询", notes="百科知识-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<AnunakiWiki>> queryPageList(AnunakiWiki anunakiWiki,
													@RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
													@RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
													HttpServletRequest req) {
		QueryWrapper<AnunakiWiki> queryWrapper = QueryGenerator.initQueryWrapper(anunakiWiki, req.getParameterMap());
		Page<AnunakiWiki> page = new Page<AnunakiWiki>(pageNo, pageSize);
		IPage<AnunakiWiki> pageList = anunakiWikiService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param anunakiWiki
	 * @return
	 */
	@AutoLog(value = "百科知识-添加")
	@ApiOperation(value="百科知识-添加", notes="百科知识-添加")
	//@RequiresPermissions("org.peng.anunaki:anunaki_wiki:add")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody AnunakiWiki anunakiWiki) {
		anunakiWikiService.save(anunakiWiki);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param anunakiWiki
	 * @return
	 */
	@AutoLog(value = "百科知识-编辑")
	@ApiOperation(value="百科知识-编辑", notes="百科知识-编辑")
	//@RequiresPermissions("org.peng.anunaki:anunaki_wiki:edit")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody AnunakiWiki anunakiWiki) {
		anunakiWikiService.updateById(anunakiWiki);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "百科知识-通过id删除")
	@ApiOperation(value="百科知识-通过id删除", notes="百科知识-通过id删除")
	//@RequiresPermissions("org.peng.anunaki:anunaki_wiki:delete")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		anunakiWikiService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "百科知识-批量删除")
	@ApiOperation(value="百科知识-批量删除", notes="百科知识-批量删除")
	//@RequiresPermissions("org.peng.anunaki:anunaki_wiki:deleteBatch")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.anunakiWikiService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "百科知识-通过id查询")
	@ApiOperation(value="百科知识-通过id查询", notes="百科知识-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<AnunakiWiki> queryById(@RequestParam(name="id",required=true) String id) {
		AnunakiWiki anunakiWiki = anunakiWikiService.getById(id);
		if(anunakiWiki==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(anunakiWiki);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param anunakiWiki
    */
    //@RequiresPermissions("org.peng.anunaki:anunaki_wiki:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, AnunakiWiki anunakiWiki) {
        return super.exportXls(request, anunakiWiki, AnunakiWiki.class, "百科知识");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    //@RequiresPermissions("org.peng.anunaki:anunaki_wiki:importExcel")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, AnunakiWiki.class);
    }

}
