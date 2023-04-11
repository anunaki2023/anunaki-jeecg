import {BasicColumn} from '/@/components/Table';
import {FormSchema} from '/@/components/Table';
import { rules} from '/@/utils/helper/validator';
import { render } from '/@/utils/common/renderUtils';
//列表数据
export const columns: BasicColumn[] = [
   {
    title: '感言',
    align:"center",
    dataIndex: 'wisdom'
   },
   {
    title: '细说感言',
    align:"center",
    dataIndex: 'detail'
   },
   {
    title: '类别',
    align:"center",
    dataIndex: 'typeflag'
   },
];
//查询数据
export const searchFormSchema: FormSchema[] = [
	{
      label: "感言",
      field: 'wisdom',
      component: 'Input',
      colProps: {span: 6},
 	},
	{
      label: "类别",
      field: 'typeflag',
      component: 'Input',
      colProps: {span: 6},
 	},
];
//表单数据
export const formSchema: FormSchema[] = [
  {
    label: '感言',
    field: 'wisdom',
    component: 'Input',
  },
  {
    label: '细说感言',
    field: 'detail',
    component: 'InputTextArea',
  },
  {
    label: '类别',
    field: 'typeflag',
    component: 'Input',
  },
	// TODO 主键隐藏字段，目前写死为ID
	{
	  label: '',
	  field: 'id',
	  component: 'Input',
	  show: false
	},
];



/**
* 流程表单调用这个方法获取formSchema
* @param param
*/
export function getBpmFormSchema(_formData): FormSchema[]{
  // 默认和原始表单保持一致 如果流程中配置了权限数据，这里需要单独处理formSchema
  return formSchema;
}