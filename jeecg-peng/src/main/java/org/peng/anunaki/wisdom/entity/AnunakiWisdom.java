package org.peng.anunaki.wisdom.entity;

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
 * @Description: 人生感悟
 * @Author: jeecg-boot
 * @Date:   2023-04-11
 * @Version: V1.0
 */
@Data
@TableName("anunaki_wisdom")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="anunaki_wisdom对象", description="人生感悟")
public class AnunakiWisdom implements Serializable {
    private static final long serialVersionUID = 1L;

	/**id*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "id")
    private java.lang.String id;
	/**感言*/
	@Excel(name = "感言", width = 15)
    @ApiModelProperty(value = "感言")
    private java.lang.String wisdom;
	/**细说感言*/
	@Excel(name = "细说感言", width = 15)
    @ApiModelProperty(value = "细说感言")
    private java.lang.String detail;
	/**类别*/
	@Excel(name = "类别", width = 15)
    @ApiModelProperty(value = "类别")
    private java.lang.String typeflag;
}
