-- 部门表（包含总公司）
CREATE TABLE sys.sys_dept (
                              id int8 NOT NULL, -- 主键
                              parent_id int8 DEFAULT '0'::bigint NOT NULL, -- 父级部门主键，总公司默认为0
                              dept_name varchar(50) NOT NULL, -- 部门名称
                              dept_type varchar(1) NOT NULL, -- 部门类型，C：总公司 D：子部门
                              create_time timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL, -- 数据创建时间
                              update_time timestamp NULL, -- 数据更新时间
                              CONSTRAINT sys_dept_check CHECK (((dept_type)::text = ANY ((ARRAY['C'::character varying, 'D'::character varying])::text[]))),
	CONSTRAINT sys_dept_pk PRIMARY KEY (id),
	CONSTRAINT sys_dept_sys_dept_fk FOREIGN KEY (parent_id) REFERENCES sys.sys_dept(id) ON DELETE RESTRICT
);
COMMENT ON TABLE sys.sys_dept IS '部门表';

-- Column comments
COMMENT ON COLUMN sys.sys_dept.id IS '主键';
COMMENT ON COLUMN sys.sys_dept.parent_id IS '父级部门主键，总公司默认为0';
COMMENT ON COLUMN sys.sys_dept.dept_name IS '部门名称';
COMMENT ON COLUMN sys.sys_dept.dept_type IS '部门类型，C：总公司 D：子部门';
COMMENT ON COLUMN sys.sys_dept.create_time IS '数据创建时间';
COMMENT ON COLUMN sys.sys_dept.update_time IS '数据更新时间';

-- Add Data
INSERT INTO sys.sys_dept
(id, parent_id, dept_name, dept_type, create_time, update_time)
VALUES(0, 0, '总公司', 'C', '2025-02-23 11:06:04.585', NULL);
INSERT INTO sys.sys_dept
(id, parent_id, dept_name, dept_type, create_time, update_time)
VALUES(1, 0, '分公司', 'C', '2025-02-23 11:07:38.387', NULL);
INSERT INTO sys.sys_dept
(id, parent_id, dept_name, dept_type, create_time, update_time)
VALUES(101, 1, '市场部', 'D', '2025-02-23 11:08:37.450', NULL);
INSERT INTO sys.sys_dept
(id, parent_id, dept_name, dept_type, create_time, update_time)
VALUES(102, 1, '财务部', 'D', '2025-02-23 11:08:37.452', NULL);
INSERT INTO sys.sys_dept
(id, parent_id, dept_name, dept_type, create_time, update_time)
VALUES(103, 1, '技术部', 'D', '2025-02-23 11:08:37.453', NULL);
INSERT INTO sys.sys_dept
(id, parent_id, dept_name, dept_type, create_time, update_time)
VALUES(104, 1, '人力资源部', 'D', '2025-02-23 11:08:37.454', NULL);

-- 用户表
CREATE TABLE sys.sys_user (
                              id int8 NOT NULL, -- 主键
                              username varchar(30) NOT NULL, -- 用户名
                              "password" varchar(100) NOT NULL, -- 密码
                              dept_id int8 NOT NULL, -- 所属部门主键
                              company_id int8 NOT NULL, -- 所属公司主键
                              is_enabled bool DEFAULT true NULL, -- 是否可用
                              create_time timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL, -- 数据创建时间
                              update_time timestamp NULL, -- 数据更新时间
                              CONSTRAINT sys_user_company_check CHECK ((company_id <> 0)),
                              CONSTRAINT sys_user_dept_check CHECK ((dept_id <> 0)),
                              CONSTRAINT sys_user_pk PRIMARY KEY (id),
                              CONSTRAINT sys_user_unique UNIQUE (username),
                              CONSTRAINT sys_user_sys_company_fk FOREIGN KEY (company_id) REFERENCES sys.sys_dept(id) ON DELETE RESTRICT,
                              CONSTRAINT sys_user_sys_dept_fk FOREIGN KEY (dept_id) REFERENCES sys.sys_dept(id) ON DELETE RESTRICT
);
COMMENT ON TABLE sys.sys_user IS '用户表';

-- Column comments
COMMENT ON COLUMN sys.sys_user.id IS '主键';
COMMENT ON COLUMN sys.sys_user.username IS '用户名';
COMMENT ON COLUMN sys.sys_user."password" IS '密码';
COMMENT ON COLUMN sys.sys_user.dept_id IS '所属部门主键';
COMMENT ON COLUMN sys.sys_user.company_id IS '所属公司主键';
COMMENT ON COLUMN sys.sys_user.is_enabled IS '是否可用';
COMMENT ON COLUMN sys.sys_user.create_time IS '数据创建时间';
COMMENT ON COLUMN sys.sys_user.update_time IS '数据更新时间';

-- Add Data
INSERT INTO sys.sys_user
(id, username, "password", dept_id, company_id, is_enabled, create_time, update_time)
VALUES(1, 'admin', 'ec7805df356b9be271806d5de694c8f0', 101, 1, true, '2025-02-23 11:16:56.253', NULL);
INSERT INTO sys.sys_user
(id, username, "password", dept_id, company_id, is_enabled, create_time, update_time)
VALUES(2, '李华', 'ec7805df356b9be271806d5de694c8f0', 102, 1, true, '2025-02-23 11:18:49.832', NULL);
INSERT INTO sys.sys_user
(id, username, "password", dept_id, company_id, is_enabled, create_time, update_time)
VALUES(3, '刘强', 'ec7805df356b9be271806d5de694c8f0', 103, 1, true, '2025-02-23 11:18:49.833', NULL);
INSERT INTO sys.sys_user
(id, username, "password", dept_id, company_id, is_enabled, create_time, update_time)
VALUES(4, '张红', 'ec7805df356b9be271806d5de694c8f0', 104, 1, true, '2025-02-23 11:18:49.834', NULL);


-- 角色表
CREATE TABLE sys.sys_role (
                              id int8 NOT NULL, -- 主键
                              role_name varchar(20) NOT NULL, -- 角色名称
                              role_desc varchar(50) NULL, -- 角色描述
                              create_time timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL, -- 数据创建时间
                              update_time timestamp NULL, -- 数据更新时间
                              CONSTRAINT sys_role_pk PRIMARY KEY (id),
                              CONSTRAINT sys_role_unique UNIQUE (role_name)
);
COMMENT ON TABLE sys.sys_role IS '角色表';

-- Column comments
COMMENT ON COLUMN sys.sys_role.id IS '主键';
COMMENT ON COLUMN sys.sys_role.role_name IS '角色名称';
COMMENT ON COLUMN sys.sys_role.role_desc IS '角色描述';
COMMENT ON COLUMN sys.sys_role.create_time IS '数据创建时间';
COMMENT ON COLUMN sys.sys_role.update_time IS '数据更新时间';

-- Add Data
INSERT INTO sys.sys_role
(id, role_name, role_desc, create_time, update_time)
VALUES(1, 'supAdmin', '超级管理员', '2025-02-23 11:26:57.554', NULL);
INSERT INTO sys.sys_role
(id, role_name, role_desc, create_time, update_time)
VALUES(2, 'employee', '普通员工', '2025-02-23 11:26:57.556', NULL);
INSERT INTO sys.sys_role
(id, role_name, role_desc, create_time, update_time)
VALUES(3, 'manager', '部门经理', '2025-02-23 11:26:57.557', NULL);
INSERT INTO sys.sys_role
(id, role_name, role_desc, create_time, update_time)
VALUES(4, 'deputyManager', '部门副经理', '2025-02-23 11:26:57.558', NULL);

-- 权限表
CREATE TABLE sys.sys_permission (
                                    id int8 NOT NULL, -- 主键
                                    perm_name varchar(30) NOT NULL, -- 权限名称
                                    perm_key varchar(50) NOT NULL, -- 权限代码，如：user:create
                                    create_time timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL, -- 数据创建时间
                                    update_time timestamp NULL, -- 数据更新时间
                                    menu_type varchar(10) DEFAULT 'BUTTON'::character varying NOT NULL, -- 菜单类型
                                    parent_id int8 NULL, -- 父级菜单主键
                                    "path" varchar(100) NULL, -- 路由路径
                                    component varchar(100) NULL, -- 组件路径
                                    icon varchar(50) NULL, -- 菜单图标
                                    sort int4 DEFAULT 0 NULL, -- 排序
                                    visible bool DEFAULT true NULL, -- 是否显示
                                    CONSTRAINT sys_permission_pk PRIMARY KEY (id),
                                    CONSTRAINT sys_permission_unique UNIQUE (perm_name),
                                    CONSTRAINT sys_permission_sys_permission_fk FOREIGN KEY (parent_id) REFERENCES sys.sys_permission(id) ON DELETE RESTRICT
);
COMMENT ON TABLE sys.sys_permission IS '权限表';

-- Column comments

COMMENT ON COLUMN sys.sys_permission.id IS '主键';
COMMENT ON COLUMN sys.sys_permission.perm_name IS '权限名称';
COMMENT ON COLUMN sys.sys_permission.perm_key IS '权限代码，如：user:create';
COMMENT ON COLUMN sys.sys_permission.create_time IS '数据创建时间';
COMMENT ON COLUMN sys.sys_permission.update_time IS '数据更新时间';
COMMENT ON COLUMN sys.sys_permission.menu_type IS '菜单类型';
COMMENT ON COLUMN sys.sys_permission.parent_id IS '父级菜单主键';
COMMENT ON COLUMN sys.sys_permission."path" IS '路由路径';
COMMENT ON COLUMN sys.sys_permission.component IS '组件路径';
COMMENT ON COLUMN sys.sys_permission.icon IS '菜单图标';
COMMENT ON COLUMN sys.sys_permission.sort IS '排序';
COMMENT ON COLUMN sys.sys_permission.visible IS '是否显示';

-- Add Data
INSERT INTO sys.sys_permission
(id, perm_name, perm_key, create_time, update_time, menu_type, parent_id, "path", component, icon, sort, visible)
VALUES(1, '创建部门', 'dept:create', '2025-02-23 11:38:12.422', NULL, 'BUTTON', 101, NULL, NULL, NULL, 0, true);
INSERT INTO sys.sys_permission
(id, perm_name, perm_key, create_time, update_time, menu_type, parent_id, "path", component, icon, sort, visible)
VALUES(2, '查询部门', 'dept:read', '2025-02-23 11:38:12.424', NULL, 'BUTTON', 101, NULL, NULL, NULL, 0, true);
INSERT INTO sys.sys_permission
(id, perm_name, perm_key, create_time, update_time, menu_type, parent_id, "path", component, icon, sort, visible)
VALUES(3, '修改部门', 'dept:update', '2025-02-23 11:38:12.425', NULL, 'BUTTON', 101, NULL, NULL, NULL, 0, true);
INSERT INTO sys.sys_permission
(id, perm_name, perm_key, create_time, update_time, menu_type, parent_id, "path", component, icon, sort, visible)
VALUES(4, '删除部门', 'dept:delete', '2025-02-23 11:38:12.426', NULL, 'BUTTON', 101, NULL, NULL, NULL, 0, true);
INSERT INTO sys.sys_permission
(id, perm_name, perm_key, create_time, update_time, menu_type, parent_id, "path", component, icon, sort, visible)
VALUES(5, '创建用户', 'user:create', '2025-02-23 11:39:23.698', NULL, 'BUTTON', 102, NULL, NULL, NULL, 0, true);
INSERT INTO sys.sys_permission
(id, perm_name, perm_key, create_time, update_time, menu_type, parent_id, "path", component, icon, sort, visible)
VALUES(6, '查询用户', 'user:read', '2025-02-23 11:39:23.699', NULL, 'BUTTON', 102, NULL, NULL, NULL, 0, true);
INSERT INTO sys.sys_permission
(id, perm_name, perm_key, create_time, update_time, menu_type, parent_id, "path", component, icon, sort, visible)
VALUES(7, '修改用户', 'user:update', '2025-02-23 11:39:23.700', NULL, 'BUTTON', 102, NULL, NULL, NULL, 0, true);
INSERT INTO sys.sys_permission
(id, perm_name, perm_key, create_time, update_time, menu_type, parent_id, "path", component, icon, sort, visible)
VALUES(8, '删除用户', 'user:delete', '2025-02-23 11:39:23.701', NULL, 'BUTTON', 102, NULL, NULL, NULL, 0, true);
INSERT INTO sys.sys_permission
(id, perm_name, perm_key, create_time, update_time, menu_type, parent_id, "path", component, icon, sort, visible)
VALUES(9, '创建角色', 'role:create', '2025-02-23 11:40:18.033', NULL, 'BUTTON', 103, NULL, NULL, NULL, 0, true);
INSERT INTO sys.sys_permission
(id, perm_name, perm_key, create_time, update_time, menu_type, parent_id, "path", component, icon, sort, visible)
VALUES(10, '查询角色', 'role:read', '2025-02-23 11:40:18.034', NULL, 'BUTTON', 103, NULL, NULL, NULL, 0, true);
INSERT INTO sys.sys_permission
(id, perm_name, perm_key, create_time, update_time, menu_type, parent_id, "path", component, icon, sort, visible)
VALUES(11, '修改角色', 'role:update', '2025-02-23 11:40:18.035', NULL, 'BUTTON', 103, NULL, NULL, NULL, 0, true);
INSERT INTO sys.sys_permission
(id, perm_name, perm_key, create_time, update_time, menu_type, parent_id, "path", component, icon, sort, visible)
VALUES(12, '删除角色', 'role:delete', '2025-02-23 11:40:18.036', NULL, 'BUTTON', 103, NULL, NULL, NULL, 0, true);
INSERT INTO sys.sys_permission
(id, perm_name, perm_key, create_time, update_time, menu_type, parent_id, "path", component, icon, sort, visible)
VALUES(13, '创建权限', 'perm:create', '2025-02-23 11:41:04.008', NULL, 'BUTTON', 104, NULL, NULL, NULL, 0, true);
INSERT INTO sys.sys_permission
(id, perm_name, perm_key, create_time, update_time, menu_type, parent_id, "path", component, icon, sort, visible)
VALUES(14, '查询权限', 'perm:read', '2025-02-23 11:41:04.009', NULL, 'BUTTON', 104, NULL, NULL, NULL, 0, true);
INSERT INTO sys.sys_permission
(id, perm_name, perm_key, create_time, update_time, menu_type, parent_id, "path", component, icon, sort, visible)
VALUES(15, '修改权限', 'perm:update', '2025-02-23 11:41:04.010', NULL, 'BUTTON', 104, NULL, NULL, NULL, 0, true);
INSERT INTO sys.sys_permission
(id, perm_name, perm_key, create_time, update_time, menu_type, parent_id, "path", component, icon, sort, visible)
VALUES(16, '删除权限', 'perm:delete', '2025-02-23 11:41:04.012', NULL, 'BUTTON', 104, NULL, NULL, NULL, 0, true);
INSERT INTO sys.sys_permission
(id, perm_name, perm_key, create_time, update_time, menu_type, parent_id, "path", component, icon, sort, visible)
VALUES(0, '目录', '-', '2025-02-24 20:46:41.610', NULL, 'CATALOG', 0, NULL, NULL, NULL, 0, true);
INSERT INTO sys.sys_permission
(id, perm_name, perm_key, create_time, update_time, menu_type, parent_id, "path", component, icon, sort, visible)
VALUES(101, '部门管理', '-', '2025-02-24 20:48:30.669', NULL, 'SUB_MENU', 1001, NULL, NULL, NULL, 0, true);
INSERT INTO sys.sys_permission
(id, perm_name, perm_key, create_time, update_time, menu_type, parent_id, "path", component, icon, sort, visible)
VALUES(102, '用户管理', '-', '2025-02-24 20:48:30.670', NULL, 'SUB_MENU', 1001, NULL, NULL, NULL, 0, true);
INSERT INTO sys.sys_permission
(id, perm_name, perm_key, create_time, update_time, menu_type, parent_id, "path", component, icon, sort, visible)
VALUES(103, '角色管理', '-', '2025-02-24 20:48:30.671', NULL, 'SUB_MENU', 1001, NULL, NULL, NULL, 0, true);
INSERT INTO sys.sys_permission
(id, perm_name, perm_key, create_time, update_time, menu_type, parent_id, "path", component, icon, sort, visible)
VALUES(104, '权限管理', '-', '2025-02-24 20:48:30.672', NULL, 'SUB_MENU', 1001, NULL, NULL, NULL, 0, true);
INSERT INTO sys.sys_permission
(id, perm_name, perm_key, create_time, update_time, menu_type, parent_id, "path", component, icon, sort, visible)
VALUES(1001, '系统管理', '-', '2025-02-24 20:49:02.473', NULL, 'MAIN_MENU', 0, NULL, NULL, NULL, 0, true);

-- 用户-角色关联表
CREATE TABLE sys.sys_user_role (
                                   user_id int8 NOT NULL,
                                   role_id int8 NOT NULL,
                                   CONSTRAINT sys_user_role_pk PRIMARY KEY (user_id,role_id),
                                   CONSTRAINT sys_user_role_sys_user_fk FOREIGN KEY (user_id) REFERENCES sys.sys_user(id) ON DELETE RESTRICT,
                                   CONSTRAINT sys_user_role_sys_role_fk FOREIGN KEY (role_id) REFERENCES sys.sys_role(id) ON DELETE RESTRICT
);
COMMENT ON TABLE sys.sys_user_role IS '用户-角色关联表';

-- Column comments
COMMENT ON COLUMN sys.sys_user_role.user_id IS '用户主键';
COMMENT ON COLUMN sys.sys_user_role.role_id IS '角色主键';

-- Add Data
INSERT INTO sys.sys_user_role
(user_id, role_id)
VALUES(1, 1);
INSERT INTO sys.sys_user_role
(user_id, role_id)
VALUES(2, 3);
INSERT INTO sys.sys_user_role
(user_id, role_id)
VALUES(3, 3);
INSERT INTO sys.sys_user_role
(user_id, role_id)
VALUES(4, 3);


-- 角色-权限关联表
CREATE TABLE sys.sys_role_permission (
                                         role_id int8 NOT NULL,
                                         perm_id int8 NOT NULL,
                                         CONSTRAINT sys_role_permission_unique UNIQUE (role_id,perm_id),
                                         CONSTRAINT sys_role_permission_sys_role_fk FOREIGN KEY (role_id) REFERENCES sys.sys_role(id) ON DELETE RESTRICT,
                                         CONSTRAINT sys_role_permission_sys_permission_fk FOREIGN KEY (perm_id) REFERENCES sys.sys_permission(id) ON DELETE RESTRICT
);
COMMENT ON TABLE sys.sys_role_permission IS '角色-权限关联表';

-- Column comments

COMMENT ON COLUMN sys.sys_role_permission.role_id IS '角色主键';
COMMENT ON COLUMN sys.sys_role_permission.perm_id IS '权限主键';

-- Add Data
INSERT INTO sys.sys_role_permission
(role_id, perm_id)
VALUES(1, 1);
INSERT INTO sys.sys_role_permission
(role_id, perm_id)
VALUES(1, 2);
INSERT INTO sys.sys_role_permission
(role_id, perm_id)
VALUES(1, 3);
INSERT INTO sys.sys_role_permission
(role_id, perm_id)
VALUES(1, 4);
INSERT INTO sys.sys_role_permission
(role_id, perm_id)
VALUES(1, 5);
INSERT INTO sys.sys_role_permission
(role_id, perm_id)
VALUES(1, 6);
INSERT INTO sys.sys_role_permission
(role_id, perm_id)
VALUES(1, 7);
INSERT INTO sys.sys_role_permission
(role_id, perm_id)
VALUES(1, 8);
INSERT INTO sys.sys_role_permission
(role_id, perm_id)
VALUES(1, 9);
INSERT INTO sys.sys_role_permission
(role_id, perm_id)
VALUES(1, 10);
INSERT INTO sys.sys_role_permission
(role_id, perm_id)
VALUES(1, 11);
INSERT INTO sys.sys_role_permission
(role_id, perm_id)
VALUES(1, 12);
INSERT INTO sys.sys_role_permission
(role_id, perm_id)
VALUES(1, 13);
INSERT INTO sys.sys_role_permission
(role_id, perm_id)
VALUES(1, 14);
INSERT INTO sys.sys_role_permission
(role_id, perm_id)
VALUES(1, 15);
INSERT INTO sys.sys_role_permission
(role_id, perm_id)
VALUES(1, 16);