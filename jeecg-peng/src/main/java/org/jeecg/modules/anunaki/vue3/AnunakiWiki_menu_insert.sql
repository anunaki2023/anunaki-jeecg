-- 注意：该页面对应的前台目录为views/org.peng.anunaki文件夹下
-- 如果你想更改到其他目录，请修改sql中component字段对应的值


INSERT INTO sys_permission(id, parent_id, name, url, component, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_route, is_leaf, keep_alive, hidden, hide_tab, description, status, del_flag, rule_flag, create_by, create_time, update_by, update_time, internal_or_external) 
VALUES ('2023031010104900150', NULL, '百科知识', '/org.peng.anunaki/anunakiWikiList', 'org.peng.anunaki/AnunakiWikiList', NULL, NULL, 0, NULL, '1', 0.00, 0, NULL, 1, 0, 0, 0, 0, NULL, '1', 0, 0, 'admin', '2023-03-10 22:10:15', NULL, NULL, 0);

-- 权限控制sql
-- 新增
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('2023031010104910151', '2023031010104900150', '添加百科知识', NULL, NULL, 0, NULL, NULL, 2, 'org.peng.anunaki:anunaki_wiki:add', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2023-03-10 22:10:15', NULL, NULL, 0, 0, '1', 0);
-- 编辑
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('2023031010104920152', '2023031010104900150', '编辑百科知识', NULL, NULL, 0, NULL, NULL, 2, 'org.peng.anunaki:anunaki_wiki:edit', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2023-03-10 22:10:15', NULL, NULL, 0, 0, '1', 0);
-- 删除
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('2023031010104920153', '2023031010104900150', '删除百科知识', NULL, NULL, 0, NULL, NULL, 2, 'org.peng.anunaki:anunaki_wiki:delete', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2023-03-10 22:10:15', NULL, NULL, 0, 0, '1', 0);
-- 批量删除
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('2023031010104920154', '2023031010104900150', '批量删除百科知识', NULL, NULL, 0, NULL, NULL, 2, 'org.peng.anunaki:anunaki_wiki:deleteBatch', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2023-03-10 22:10:15', NULL, NULL, 0, 0, '1', 0);
-- 导出excel
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('2023031010104920155', '2023031010104900150', '导出excel_百科知识', NULL, NULL, 0, NULL, NULL, 2, 'org.peng.anunaki:anunaki_wiki:exportXls', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2023-03-10 22:10:15', NULL, NULL, 0, 0, '1', 0);
-- 导入excel
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('2023031010104920156', '2023031010104900150', '导入excel_百科知识', NULL, NULL, 0, NULL, NULL, 2, 'org.peng.anunaki:anunaki_wiki:importExcel', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2023-03-10 22:10:15', NULL, NULL, 0, 0, '1', 0);