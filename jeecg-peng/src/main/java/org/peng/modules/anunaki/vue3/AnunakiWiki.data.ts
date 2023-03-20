import {BasicColumn} from '/@/components/Table';
import {FormSchema} from '/@/components/Table';
import { rules} from '/@/utils/helper/validator';
import { render } from '/@/utils/common/renderUtils';
//列表数据
export const columns: BasicColumn[] = [
   {
    title: 'content',
    align:"center",
    dataIndex: 'content'
   },
   {
    title: 'thedate',
    align:"center",
    dataIndex: 'thedate'
   },
   {
    title: '类型标记',
    align:"center",
    dataIndex: 'flag'
   },
   {
    title: '标题',
    align:"center",
    dataIndex: 'title'
   },
];
//查询数据
export const searchFormSchema: FormSchema[] = [
	{
      label: "类型标记",
      field: 'flag',
      component: 'Input',
      colProps: {span: 6},
 	},
	{
      label: "标题",
      field: 'title',
      component: 'Input',
      colProps: {span: 6},
 	},
];
//表单数据
export const formSchema: FormSchema[] = [
  {
    label: 'content',
    field: 'content',
    component: 'Input',
  },
  {
    label: 'thedate',
    field: 'thedate',
    component: 'DatePicker',
    componentProps: {
       showTime: true,
       valueFormat: 'YYYY-MM-DD HH:mm:ss'
     },
  },
  {
    label: '类型标记',
    field: 'flag',
    component: 'Input',
  },
  {
    label: '标题',
    field: 'title',
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