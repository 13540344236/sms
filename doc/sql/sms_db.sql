-- 数据库：sms_db
-- shujuku
DROP TABLE IF EXISTS sms_role_menu;
create table sms_role_menu
(
    id      int unsigned auto_increment comment '记录id',
    role_id int           null comment '角色id',
    menu_id int           null comment '菜单id',
    primary key (id)
);

-- 菜单表
DROP TABLE IF EXISTS `sms_menu`;
CREATE TABLE `sms_menu`
(
    `id`          int(11)                                                       NOT NULL AUTO_INCREMENT COMMENT 'id',
    `name`        varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '名称',
    `path`        varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '路径',
    `icon`        varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '图标',
    `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '描述',
    `pid`         int(11)                                                       NULL DEFAULT NULL COMMENT '父级id',
    `page_path`   varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '页面路径',
    `sort_num`    int(11)                                                       NULL DEFAULT NULL COMMENT '排序',
    PRIMARY KEY (`id`) USING BTREE
)
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_unicode_ci
  ROW_FORMAT = Dynamic;

-- 用户表（管理员表）
DROP TABLE IF EXISTS sms_user;
CREATE TABLE sms_user  (
    `id` int(15) NOT NULL AUTO_INCREMENT COMMENT 'id',
    `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '用户名',
    `password` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '密码',
    `nickname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '昵称',
    `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '邮箱',
    `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '电话',
    `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '地址',
    `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
     `avatar_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '头像',
     `role` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '角色',
      enable      tinyint unsigned default 0 comment '是否启用，1=启用，0=未启用',
     PRIMARY KEY (`id`) USING BTREE
) CHARACTER SET = utf8mb4 ;

-- 员工表
drop table if exists sms_admin;
create table sms_admin
(
    id          bigint unsigned auto_increment comment '记录id',
    staff_name  varchar(15) default null comment '姓名',
    password    varchar(10) default null comment '密码(密文)',
    gender      varchar(5) default null comment '性别',
    phone       varchar(15) default null comment '电话号码',
    id_number   varchar(20) default null comment '身份证号码',
    on_duty      tinyint unsigned default 0 comment '是否在岗，1=是，0=否',
    email       varchar(50) default null comment '电子邮箱',
    description varchar(255) default null comment '描述',
    enable      tinyint unsigned default 0 comment '是否启用，1=启用，0=未启用',
    last_login_ip varchar(50) default null comment '最后登录IP地址（冗余）',
    login_count int unsigned default 0 comment '累计登录次数（冗余）',
    gmt_last_login datetime default null comment '最后登录时间（冗余）',
    gmt_create  datetime default null comment '数据创建时间',
    gmt_modified datetime default null comment '数据最后修改时间',
    primary key (id)
)comment '管理员(员工)表' charset utf8;

-- 管理员-角色关联表：创建数据表
drop table if exists sms_user_role;
create table sms_user_role
(
     id             bigint unsigned auto_increment,
     user_id       bigint unsigned default null comment '管理员id',
     role_id        bigint unsigned default null comment '角色id',
     gmt_create     datetime default null comment '数据创建时间',
     gmt_modified   datetime default null comment '数据最后修改时间',
     primary key (id)
) comment '管理员角色关联表' charset utf8mb4;

-- 角色表：创建数据表
drop table if exists sms_role;
create table sms_role
(
    id              bigint unsigned auto_increment,
    name            varchar(50) default null comment '名称',
    description     varchar(255) default null comment '描述',
    sort            tinyint unsigned default 0 comment '自定义排序序号',
    gmt_create      datetime default null comment '数据创建时间',
    gmt_modified    datetime default null comment '数据最后修改时间',
    primary key (id)
) comment '角色表' charset utf8mb4;

-- 角色权限关联表：创建数据表
drop table if exists sms_role_permission;
create table sms_role_permission
(
    id             bigint unsigned auto_increment,
    role_id        bigint unsigned default null comment '角色id',
    permission_id  bigint unsigned default null comment '权限id',
    gmt_create     datetime default null comment '数据创建时间',
    gmt_modified   datetime default null comment '数据最后修改时间',
    primary key (id)
) comment '角色权限关联表' charset utf8mb4;

-- 权限表：创建数据表
drop table if exists sms_permission;
create table sms_permission
(
    id          bigint unsigned auto_increment,
    name        varchar(50) default null comment '名称',
    value       varchar(255) default null comment '值',
    description varchar(255) default null comment '描述',
    sort        tinyint unsigned default 0 comment '自定义排序序号',
    gmt_create   datetime default null comment '数据创建时间',
    gmt_modified datetime default null comment '数据最后修改时间',
    primary key (id)
) comment '权限表' charset utf8mb4;

-- 管理员登录日志表：创建数据表
drop table if exists sms_log;
CREATE TABLE `sms_log` (
                       `id` bigint(20) NOT NULL AUTO_INCREMENT,
                       `username` varchar(50) DEFAULT NULL COMMENT '用户名',
                       `operation` varchar(50) DEFAULT NULL COMMENT '用户操作',
                       `method` varchar(200) DEFAULT NULL COMMENT '请求方法',
                       `params` varchar(5000) DEFAULT NULL COMMENT '请求参数',
                       `time` bigint(20) NOT NULL COMMENT '执行时长(毫秒)',
                       `ip` varchar(64) DEFAULT NULL COMMENT 'IP地址',
                       `created_time` datetime DEFAULT NULL COMMENT '创建时间',
                       `status` int(11) DEFAULT '1',
                       `error` varchar(500) DEFAULT NULL,
                       PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='用户日志';


-- 以上为管理员（员工）相关表 --

drop table if exists sms_member;
create table sms_member
(
    id             bigint unsigned auto_increment comment 'id',
    member_id      varchar(50)      default null comment '会员号',
    name           varchar(50)      default null comment '会员姓名',
    phone         bigint      default null comment '电话号码',
    integral       tinyint unsigned default null comment '可用积分',
    money          double           default null comment '余额',
    payment_method varchar(10)      default null comment '支付方式',
    address        varchar(50)      default null comment '地址',
    primary key (id)
)comment '会员管理表' charset utf8;
create index idx_member_name on sms_member (name);

-- 供应商管理表
drop table if exists sms_supplier;
create table sms_supplier
(
     id             bigint unsigned auto_increment,
     supplier       varchar(20) default null comment '供应商',
     supplier_name  varchar(10) default null comment '供应商姓名',
     supplier_phone varchar(15)  default null comment '供应商电话',
     supplier_site  varchar(50)  default null comment '供应商地址',
     remark         varchar(50)  default null comment '备注',
     gmt_create     datetime default null comment '录入数据时间',
     gmt_modified   datetime default null comment '数据最后修改时间',
     primary key (id)
)comment '供应商管理表' charset utf8mb4;

-- 相册表：创建数据表
drop table if exists sms_album;
create table sms_album
(
    id           bigint unsigned auto_increment comment '记录id',
    name         varchar(50)      default null comment '相册名称',
    description  varchar(255)     default null comment '相册简介',
    sort         tinyint unsigned default 0 comment '自定义排序序号',
    gmt_create   datetime         default null comment '数据创建时间',
    gmt_modified datetime         default null comment '数据最后修改时间',
    primary key (id)
) comment '相册' charset utf8mb4;

-- 相册表：为相册名称字段添加索引
create index idx_album_name on sms_album (name);

-- 商品和类别关联表
drop table if exists sms_goods_category;
create table sms_goods_category
(
    id           bigint unsigned auto_increment comment '记录id',
    goods_id     bigint unsigned default null comment '商品id',
    category_id  bigint unsigned default null comment '类别id',
    gmt_create   datetime        default null comment '数据创建时间',
    gmt_modified datetime        default null comment '数据最后修改时间',
    primary key (id)
) comment '商品与类别关联' charset utf8mb4;

-- 图片表：创建数据表
drop table if exists sms_picture;
create table sms_picture
(
    id           bigint unsigned auto_increment comment '记录id',
    name         varchar(255)      default null comment '图片名字',
    url          varchar(255)      default null comment '图片url',
    description  varchar(255)      default null comment '图片简介',
    width        smallint unsigned default null comment '图片宽度，单位：px',
    height       smallint unsigned default null comment '图片高度，单位：px',
    sort         tinyint unsigned  default 0 comment '自定义排序序号',
    gmt_create   datetime          default null comment '数据创建时间',
    gmt_modified datetime          default null comment '数据最后修改时间',
    primary key (id)
) comment '图片' charset utf8mb4;

-- 进货表：创建数据表
drop table if exists sms_purchase;
create table sms_purchase
(
    id                     bigint unsigned auto_increment comment '记录id',
    name                   varchar(50)      default null comment '商品名称',
    goods_category         varchar(255)     default null comment '商品类别',
    goods_specification    varchar(255)     default null comment '商品规格',
    warehousing_quantity   int unsigned     default 0 comment '入库数量',
    supplier               varchar(255)     default null comment '供应商',
    operator               varchar(255)     default null comment '经办人',
    purchase_document_picture  varchar(255)     default null comment '进货单据图片',
    logo                   varchar(255)     default null comment '商品logo的URL',
    gmt_create             datetime         default null comment '操作时间',
    gmt_modified           datetime         default null comment '数据最后修改时间',
    primary key (id)
) comment '进货' charset utf8mb4;

-- 进货表：为进货表名称字段添加索引
create index idx_purchase_name on sms_purchase (name);

-- 退货表：创建数据表
drop table if exists sms_refund;
create table sms_refund
(
    id                     bigint unsigned auto_increment comment '记录id',
    name                   varchar(50)      default null comment '商品名称',
    category         varchar(255)     default null comment '商品类别',
    goods_specification    varchar(255)     default null comment '商品规格',
    warehousing_quantity   int unsigned     default 0 comment '退货出库库数量',
    amount_payable         decimal(10,3)    default null comment '金额',
    is_pay                 tinyint unsigned  default 0 comment '是否付款，1=是，0=否',
    supplier               varchar(255)     default null comment '供应商',
    operator               varchar(255)     default null comment '经办人',
    return_document_picture  varchar(255)     default null comment '退货单据图片',
    logo                   varchar(255)     default null comment '商品logo的URL',
    gmt_create_purchase    datetime         default null comment '进货时间',
    gmt_create_return      datetime         default null comment '退货时间',
    gmt_modified           datetime         default null comment '数据最后修改时间',
    primary key (id)
) comment '退货' charset utf8mb4;

-- 退货表：为退货表名称字段添加索引
create index idx_refund_name on sms_refund (name);

-- 销售表：创建数据表
drop table if exists sms_sale;
create table sms_sale
(
    id                     bigint unsigned auto_increment comment '记录id',
    name                   varchar(50)      default null comment '商品名称',
    goods_specification    varchar(255)     default null comment '商品规格',
    sale_quantity          int unsigned     default 0 comment '销售数量',
    purchase_price      decimal(10, 3)  default null  comment '采购价格',
    sale_price          decimal(10, 3)  default null  comment '销售价格',
    customer_name          varchar(255)     default null comment '客户姓名',
    customer_phone         varchar(255)     default null comment '客户电话',
    operating_staff        varchar(255)     default null comment '操作员工',
    purchase_document_picture  varchar(255)     default null comment '进货单据图片',
    logo                   varchar(255)     default null comment '商品logo的URL',
    gmt_create             datetime         default null comment '操作时间',
    gmt_modified           datetime         default null comment '数据最后修改时间',
    primary key (id)
) comment '销售' charset utf8mb4;

-- 销售表：为销售表名称字段添加索引
create index idx_sale_name on sms_sale (name);

-- 类别表：创建数据表
drop table if exists sms_category;
create table sms_category
(
    id           bigint unsigned auto_increment comment '记录id',
    name         varchar(50)      default null comment '类别名称',
    parent_id    bigint unsigned  default 0 comment '父级类别id，如果无父级，则为0',
    depth        tinyint unsigned default 1 comment '深度，最顶级类别的深度为1，次级为2，以此类推',
    keywords     varchar(255)     default null comment '关键词列表，各关键词使用英文的逗号分隔',
    sort         tinyint unsigned default 0 comment '自定义排序序号',
    icon         varchar(255)     default null comment '图标图片的URL',
    enable       tinyint unsigned default 0 comment '是否启用，1=启用，0=未启用',
    is_parent    tinyint unsigned default 0 comment '是否为父级（是否包含子级），1=是父级，0=不是父级',
    is_display   tinyint unsigned default 0 comment '是否显示在导航栏中，1=启用，0=未启用',
    gmt_create   datetime         default null comment '数据创建时间',
    gmt_modified datetime         default null comment '数据最后修改时间',
    primary key (id)
) comment '类别' charset utf8mb4;

-- 类别表：为类别名称字段添加索引
create index idx_category_name on sms_category (name);

-- 品牌表：创建数据表
drop table if exists sms_brand;
create table sms_brand
(
    id                     bigint unsigned auto_increment comment '记录id',
    name                   varchar(50)      default null comment '品牌名称',
    pinyin                 varchar(50)      default null comment '品牌名称的拼音',
    logo                   varchar(255)     default null comment '品牌logo的URL',
    description            varchar(255)     default null comment '品牌简介',
    keywords               varchar(255)     default null comment '关键词列表，各关键词使用英文的逗号分隔',
    sort                   tinyint unsigned default 0 comment '自定义排序序号',
    sales                  int unsigned     default 0 comment '销量（冗余）',
    product_count          int unsigned     default 0 comment '商品种类数量总和（冗余）',
    enable                 tinyint unsigned default 0 comment '是否启用，1=启用，0=未启用',
    gmt_create             datetime         default null comment '数据创建时间',
    gmt_modified           datetime         default null comment '数据最后修改时间',
    primary key (id)
) comment '品牌' charset utf8mb4;

-- 品牌类别关联表：创建数据表
drop table if exists sms_brand_category;
create table sms_brand_category
(
    id           bigint unsigned auto_increment comment '记录id',
    brand_id     bigint unsigned default null comment '品牌id',
    category_id  bigint unsigned default null comment '类别id',
    gmt_create   datetime        default null comment '数据创建时间',
    gmt_modified datetime        default null comment '数据最后修改时间',
    primary key (id)
) comment '品牌与类别关联' charset utf8mb4;

-- 商品表
drop table if exists sms_goods;
create table sms_goods
(
    id                  bigint unsigned auto_increment comment '编号',
    url                 varchar(255) default null  comment '图片地址',
    name                varchar(50)  default null  comment '名称',
    category            varchar(50)  default null  comment '类别',
    purchase_price      decimal(10, 3)  default null  comment '采购价格',
    sale_price          decimal(10, 3)  default null  comment '销售价格',
    goods_specification varchar(50)  default null  comment '规格',
    current_stock       bigint  default null  comment '当前库存',
    low_limit_stock     bigint  default null  comment '库存下限',
    primary key (id)
)
    comment '商品表' charset utf8mb4;

-- 商品报溢表
drop table if exists sms_goods_bad;
create table sms_goods_bad
(
    id                  bigint unsigned auto_increment comment '编号'
        primary key,
    url                 varchar(255)   null comment '图片地址',
    name                varchar(50)    null comment '名称',
    category            varchar(50)    null comment '类别',
    purchase_price      decimal(10, 3) null comment '采购价格',
    sale_price          decimal(10, 3) null comment '销售价格',
    goods_specification varchar(50)    null comment '规格',
    current_stock       bigint         null comment '当前库存',
    low_limit_stock     bigint         null comment '库存下限',
    reported_loss       bigint         null comment '损坏数量'
)
    comment '商品报损表' charset = utf8mb4;
create index idx_goods_bad_name on sms_goods_bad (name);

-- 商品报溢表
drop table if exists sms_goods_max;
create table sms_goods_max
(
    id                  bigint unsigned auto_increment comment '编号'
        primary key,
    url                 varchar(255)   null comment '图片地址',
    name                varchar(50)    null comment '名称',
    category            varchar(50)    null comment '类别',
    purchase_price      decimal(10, 3) null comment '采购价格',
    sale_price          decimal(10, 3) null comment '销售价格',
    goods_specification varchar(50)    null comment '规格',
    current_stock       bigint         null comment '当前库存',
    the_overflow        bigint         null comment '溢出商品'
)
    comment '商品报溢表' charset = utf8mb4;
create index idx_goods_max_name on sms_goods_max (name);

-- 商品与品牌关联表
-- 商品与品牌关联表：创建数据表
drop table if exists sms_goods_brand;
create table sms_goods_brand
(
    id           bigint unsigned auto_increment comment '记录id',
    goods_id     bigint unsigned default null comment '类别id',
    brand_id     bigint unsigned default null comment '品牌id',
    gmt_create   datetime        default null comment '数据创建时间',
    gmt_modified datetime        default null comment '数据最后修改时间',
    primary key (id)
) comment '商品与品牌关联' charset utf8mb4;





