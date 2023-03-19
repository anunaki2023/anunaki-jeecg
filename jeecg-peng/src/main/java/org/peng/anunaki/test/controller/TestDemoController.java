package org.peng.anunaki.test.controller;

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
import org.peng.anunaki.test.entity.TestDemo;
import org.peng.anunaki.test.service.ITestDemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

 /**
 * @Description: 测试用户表
 * @Author: jeecg-boot
 * @Date:   2023-03-19
 * @Version: V1.0
 */
@Api(tags="测试用户表")
@RestController
@RequestMapping("/test/testDemo")
@Slf4j
public class TestDemoController extends JeecgController<TestDemo, ITestDemoService> {
	@Autowired
	private ITestDemoService testDemoService;
	
	/**
	 * 分页列表查询
	 *
	 * @param testDemo
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "测试用户表-分页列表查询")
	@ApiOperation(value="测试用户表-分页列表查询", notes="测试用户表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<TestDemo>> queryPageList(TestDemo testDemo,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<TestDemo> queryWrapper = QueryGenerator.initQueryWrapper(testDemo, req.getParameterMap());
		Page<TestDemo> page = new Page<TestDemo>(pageNo, pageSize);
		IPage<TestDemo> pageList = testDemoService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param testDemo
	 * @return
	 */
	@AutoLog(value = "测试用户表-添加")
	@ApiOperation(value="测试用户表-添加", notes="测试用户表-添加")
	//@RequiresPermissions("test:test_demo:add")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody TestDemo testDemo) {
		testDemoService.save(testDemo);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param testDemo
	 * @return
	 */
	@AutoLog(value = "测试用户表-编辑")
	@ApiOperation(value="测试用户表-编辑", notes="测试用户表-编辑")
	//@RequiresPermissions("test:test_demo:edit")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody TestDemo testDemo) {
		testDemoService.updateById(testDemo);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "测试用户表-通过id删除")
	@ApiOperation(value="测试用户表-通过id删除", notes="测试用户表-通过id删除")
	//@RequiresPermissions("test:test_demo:delete")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		testDemoService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "测试用户表-批量删除")
	@ApiOperation(value="测试用户表-批量删除", notes="测试用户表-批量删除")
	//@RequiresPermissions("test:test_demo:deleteBatch")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.testDemoService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "测试用户表-通过id查询")
	@ApiOperation(value="测试用户表-通过id查询", notes="测试用户表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<TestDemo> queryById(@RequestParam(name="id",required=true) String id) {
		TestDemo testDemo = testDemoService.getById(id);
		if(testDemo==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(testDemo);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param testDemo
    */
    //@RequiresPermissions("test:test_demo:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, TestDemo testDemo) {
        return super.exportXls(request, testDemo, TestDemo.class, "测试用户表");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    //@RequiresPermissions("test:test_demo:importExcel")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, TestDemo.class);
    }

}
