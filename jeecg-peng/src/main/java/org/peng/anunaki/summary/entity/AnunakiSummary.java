package org.peng.anunaki.summary.entity;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecg.common.aspect.annotation.Dict;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @Description: 生活总结
 * @Author: jeecg-boot
 * @Date:   2023-04-04
 * @Version: V1.0
 */
@Data
@TableName("anunaki_summary")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="anunaki_summary对象", description="生活总结")
public class AnunakiSummary implements Serializable {
    private static final long serialVersionUID = 1L;

	/**id*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "id")
    private java.lang.String id;
	/**thedate*/
	@Excel(name = "thedate", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "thedate")
    private java.util.Date thedate;
	/**周月年标记(wmy)*/
	@Excel(name = "周月年标记(wmy)", width = 15)
    @ApiModelProperty(value = "周月年标记(wmy)")
    private java.lang.String flag;
	/**content*/
	@Excel(name = "content", width = 15)
    @ApiModelProperty(value = "content")
    private java.lang.String content;
	/**keyword*/
	@Excel(name = "keyword", width = 15)
    @ApiModelProperty(value = "keyword")
    private java.lang.String keyword;
}
