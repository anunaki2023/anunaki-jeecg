import {BasicColumn} from '/@/components/Table';
import {FormSchema} from '/@/components/Table';
import { rules} from '/@/utils/helper/validator';
import { render } from '/@/utils/common/renderUtils';
//列表数据
export const columns: BasicColumn[] = [
   {
    title: 'thedate',
    align:"center",
    dataIndex: 'thedate',
    customRender:({text}) =>{
      return !text?"":(text.length>10?text.substr(0,10):text)
    },
   },
   {
    title: '周月年标记(wmy)',
    align:"center",
    dataIndex: 'flag'
   },
   {
    title: 'content',
    align:"center",
    dataIndex: 'content'
   },
   {
    title: 'keyword',
    align:"center",
    dataIndex: 'keyword'
   },
];
//查询数据
export const searchFormSchema: FormSchema[] = [
	{
      label: "thedate",
      field: 'thedate',
      component: 'DatePicker',
      colProps: {span: 6},
 	},
];
//表单数据
export const formSchema: FormSchema[] = [
  {
    label: 'thedate',
    field: 'thedate',
    component: 'DatePicker',
  },
  {
    label: '周月年标记(wmy)',
    field: 'flag',
    component: 'Input',
  },
  {
    label: 'content',
    field: 'content',
    component: 'InputTextArea',
  },
  {
    label: 'keyword',
    field: 'keyword',
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