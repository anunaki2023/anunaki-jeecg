import {BasicColumn} from '/@/components/Table';
import {FormSchema} from '/@/components/Table';
import { rules} from '/@/utils/helper/validator';
import { render } from '/@/utils/common/renderUtils';
//列表数据
export const columns: BasicColumn[] = [
   {
    title: '用户名',
    align:"center",
    sorter: true,
    dataIndex: 'name'
   },
   {
    title: '性别',
    align:"center",
    sorter: true,
    dataIndex: 'sex_dictText'
   },
   {
    title: '年龄',
    align:"center",
    dataIndex: 'age'
   },
   {
    title: '描述',
    align:"center",
    dataIndex: 'descc',
    slots: { customRender: 'htmlSlot' },
   },
   {
    title: '生日',
    align:"center",
    dataIndex: 'birthday',
    customRender:({text}) =>{
      return !text?"":(text.length>10?text.substr(0,10):text)
    },
   },
   {
    title: '头像',
    align:"center",
    dataIndex: 'topPic',
    customRender:render.renderImage,
   },
   {
    title: '附件',
    align:"center",
    dataIndex: 'fileKk',
    slots: { customRender: 'fileSlot' },
   },
   {
    title: '城市',
    align:"center",
    dataIndex: 'chegnshi',
    slots: { customRender: 'pcaSlot' },
   },
   {
    title: '弹窗',
    align:"center",
    dataIndex: 'pop'
   },
   {
    title: 'checkbox',
    align:"center",
    dataIndex: 'ceck_dictText'
   },
   {
    title: '下拉多选',
    align:"center",
    dataIndex: 'xiamuti_dictText'
   },
   {
    title: '搜索下拉',
    align:"center",
    dataIndex: 'searchSel_dictText'
   },
   {
    title: '下拉字典表',
    align:"center",
    dataIndex: 'selTable_dictText'
   },
];
//查询数据
export const searchFormSchema: FormSchema[] = [
	{
      label: "用户名",
      field: 'name',
      component: 'Input',
      colProps: {span: 6},
 	},
	{
      label: "性别",
      field: 'sex',
      component: 'JDictSelectTag',
      componentProps:{
          dictCode:"sex"
      },
      colProps: {span: 6},
 	},
	{
      label: "生日",
      field: 'birthday',
      component: 'DatePicker',
      colProps: {span: 6},
 	},
	{
      label: "用户编码",
      field: 'userCode',
      component: 'Input',
      colProps: {span: 6},
 	},
	{
      label: "城市",
      field: 'chegnshi',
      component: 'JAreaLinkage',
      colProps: {span: 6},
 	},
	{
      label: "checkbox",
      field: 'ceck',
      component: 'JDictSelectTag',
      componentProps:{
          dictCode:"sex"
      },
      colProps: {span: 6},
 	},
	{
      label: "下拉多选",
      field: 'xiamuti',
      component: 'JSelectMultiple',
      componentProps:{
        dictCode:"sex",
        triggerChange: true
     },
      colProps: {span: 6},
 	},
	{
      label: "搜索下拉",
      field: 'searchSel',
      component: 'JSearchSelect',
      componentProps:{
         dict:"sys_role,role_name,role_code"
      },
      colProps: {span: 6},
 	},
];
//表单数据
export const formSchema: FormSchema[] = [
  {
    label: '主键',
    field: 'id',
    component: 'Input',
  },
  {
    label: '用户名',
    field: 'name',
    component: 'Input',
    dynamicRules: ({model,schema}) => {
          return [
                 { required: true, message: '请输入用户名!'},
          ];
     },
  },
  {
    label: '性别',
    field: 'sex',
    component: 'JDictSelectTag',
    componentProps:{
        dictCode:"sex"
     },
  },
  {
    label: '年龄',
    field: 'age',
    component: 'InputNumber',
  },
  {
    label: '描述',
    field: 'descc',
    component: 'JEditor',
  },
  {
    label: '生日',
    field: 'birthday',
    component: 'DatePicker',
  },
  {
    label: '用户编码',
    field: 'userCode',
    component: 'Input',
  },
  {
    label: '头像',
    field: 'topPic',
     component: 'JImageUpload',
     componentProps:{
      },
  },
  {
    label: '附件',
    field: 'fileKk',
    component: 'JUpload',
    componentProps:{
     },
  },
  {
    label: '城市',
    field: 'chegnshi',
    component: 'JAreaLinkage',
  },
  {
    label: '弹窗',
    field: 'pop',
    component: 'Input',
  },
  {
    label: 'checkbox',
    field: 'ceck',
    component: 'JSelectMultiple',
    componentProps:{
        dictCode:"sex"
     },
  },
  {
    label: '下拉多选',
    field: 'xiamuti',
    component: 'JSelectMultiple',
    componentProps:{
        dictCode:"sex"
     },
  },
  {
    label: '搜索下拉',
    field: 'searchSel',
    component: 'JSearchSelect',
    componentProps:{
       dict:"sys_role,role_name,role_code"
    },
  },
  {
    label: '下拉字典表',
    field: 'selTable',
    component: 'JSearchSelect',
    componentProps:{
       dict:"sys_user where username like 'a%',realname,username"
    },
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