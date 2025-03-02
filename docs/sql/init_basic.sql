-- 创建库房信息表
CREATE TABLE basic.td_warehouse (
                                    id int8 NOT NULL, -- 主键
                                    warehouse_name varchar(255) NOT NULL, -- 库房名称
                                    address text NULL, -- 库房地址
                                    manager int8 NULL, -- 库管员主键
                                    capacity numeric(12, 2) NULL, -- 库房容量
                                    status varchar(20) DEFAULT '1'::character varying NULL, -- 库房可用状态——0 不可用，1 可用
                                    create_time timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL, -- 数据创建时间
                                    update_time timestamp NULL, -- 数据更新时间
                                    CONSTRAINT td_warehouse_pkey PRIMARY KEY (id),
                                    CONSTRAINT td_warehouse_status_check CHECK (((status)::text = ANY ((ARRAY['0'::character varying, '1'::character varying])::text[]))),
                                    CONSTRAINT td_warehouse_sys_user_fk FOREIGN KEY (manager) REFERENCES sys.sys_user(id)
);
COMMENT ON TABLE basic.td_warehouse IS '库房信息表';

-- Column comments

COMMENT ON COLUMN basic.td_warehouse.id IS '主键';
COMMENT ON COLUMN basic.td_warehouse.warehouse_name IS '库房名称';
COMMENT ON COLUMN basic.td_warehouse.address IS '库房地址';
COMMENT ON COLUMN basic.td_warehouse.manager IS '库管员主键';
COMMENT ON COLUMN basic.td_warehouse.capacity IS '库房容量';
COMMENT ON COLUMN basic.td_warehouse.status IS '库房可用状态——0 不可用，1 可用';
COMMENT ON COLUMN basic.td_warehouse.create_time IS '数据创建时间';
COMMENT ON COLUMN basic.td_warehouse.update_time IS '数据更新时间';

-- 创建库位表
CREATE TABLE basic.td_storage_location (
                                           id int8 NOT NULL, -- 主键
                                           warehouse_id int8 NOT NULL, -- 库房主键
                                           zone_type varchar(50) NULL, -- 库区类型
                                           location_code varchar(50) NOT NULL, -- 库位编号
                                           status varchar(20) DEFAULT 'EMPTY'::character varying NULL, -- 库位状态，如空闲/占用/锁定
                                           max_volume numeric(12, 2) NULL, -- 最大存储体积
                                           max_weight numeric(12, 2) NULL, -- 最大承重
                                           create_time timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL, -- 数据创建时间
                                           update_time timestamp NULL, -- 数据修改时间
                                           CONSTRAINT td_storage_location_location_code_key UNIQUE (location_code),
                                           CONSTRAINT td_storage_location_pkey PRIMARY KEY (id),
                                           CONSTRAINT td_storage_location_status_check CHECK (((status)::text = ANY ((ARRAY['EMPTY'::character varying, 'OCCUPIED'::character varying, 'LOCKED'::character varying])::text[]))),
                                           CONSTRAINT td_storage_location_warehouse_id_fkey FOREIGN KEY (warehouse_id) REFERENCES basic.td_warehouse(id) ON DELETE RESTRICT
);
COMMENT ON TABLE basic.td_storage_location IS '库位表';

-- Column comments

COMMENT ON COLUMN basic.td_storage_location.id IS '主键';
COMMENT ON COLUMN basic.td_storage_location.warehouse_id IS '库房主键';
COMMENT ON COLUMN basic.td_storage_location.zone_type IS '库区类型';
COMMENT ON COLUMN basic.td_storage_location.location_code IS '库位编号';
COMMENT ON COLUMN basic.td_storage_location.status IS '库位状态，如空闲/占用/锁定';
COMMENT ON COLUMN basic.td_storage_location.max_volume IS '最大存储体积';
COMMENT ON COLUMN basic.td_storage_location.max_weight IS '最大承重';
COMMENT ON COLUMN basic.td_storage_location.create_time IS '数据创建时间';
COMMENT ON COLUMN basic.td_storage_location.update_time IS '数据修改时间';

-- 创建商品信息表
CREATE TABLE basic.td_product (
                                  id int8 NOT NULL, -- 主键
                                  product_code varchar(50) NOT NULL, -- 商品编号
                                  product_name varchar(255) NOT NULL, -- 商品名称
                                  specification text NULL, -- 商品规格信息
                                  category varchar(100) NULL, -- 商品种类
                                  unit varchar(20) NULL, -- 商品包装单位
                                  weight numeric(10, 2) NULL, -- 商品重量
                                  volume numeric(10, 2) NULL, -- 商品体积
                                  safety_stock int4 NULL, -- 安全库存数量
                                  create_time timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL, -- 数据创建时间
                                  update_time timestamp NULL, -- 数据修改时间
                                  CONSTRAINT td_product_pkey PRIMARY KEY (id)
);
COMMENT ON TABLE basic.td_product IS '商品信息表';

-- Column comments

COMMENT ON COLUMN basic.td_product.id IS '主键';
COMMENT ON COLUMN basic.td_product.product_code IS '商品编号';
COMMENT ON COLUMN basic.td_product.product_name IS '商品名称';
COMMENT ON COLUMN basic.td_product.specification IS '商品规格信息';
COMMENT ON COLUMN basic.td_product.category IS '商品种类';
COMMENT ON COLUMN basic.td_product.unit IS '商品包装单位';
COMMENT ON COLUMN basic.td_product.weight IS '商品重量';
COMMENT ON COLUMN basic.td_product.volume IS '商品体积';
COMMENT ON COLUMN basic.td_product.safety_stock IS '安全库存数量';
COMMENT ON COLUMN basic.td_product.create_time IS '数据创建时间';
COMMENT ON COLUMN basic.td_product.update_time IS '数据修改时间';

-- 创建客商信息表（补充缺失的外键依赖）
CREATE TABLE basic.td_customer_supplier (
                                            id int8 NOT NULL, -- 主键
                                            "name" varchar(255) NOT null unique, -- 客商名称
                                            "type" varchar(10) NULL, -- 客商类型，客户/供应商
                                            create_time timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL, -- 数据创建时间
                                            update_time timestamp NULL, -- 数据修改时间
                                            CONSTRAINT td_customer_supplier_pkey PRIMARY KEY (id),
                                            CONSTRAINT td_customer_supplier_type_check CHECK (((type)::text = ANY ((ARRAY['CUSTOMER'::character varying, 'SUPPLIER'::character varying])::text[])))
);
COMMENT ON TABLE basic.td_customer_supplier IS '客商信息表';

-- Column comments

COMMENT ON COLUMN basic.td_customer_supplier.id IS '主键';
COMMENT ON COLUMN basic.td_customer_supplier."name" IS '客商名称';
COMMENT ON COLUMN basic.td_customer_supplier."type" IS '客商类型，客户/供应商';
COMMENT ON COLUMN basic.td_customer_supplier.create_time IS '数据创建时间';
COMMENT ON COLUMN basic.td_customer_supplier.update_time IS '数据修改时间';