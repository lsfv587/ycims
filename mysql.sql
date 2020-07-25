DROP TABLE IF EXISTS pub_batch;
DROP TABLE IF EXISTS pub_channel;
DROP TABLE IF EXISTS pub_subline;
DROP TABLE IF EXISTS pub_line;
DROP TABLE IF EXISTS pub_channelbind;
DROP TABLE IF EXISTS pub_product;
CREATE TABLE IF NOT EXISTS pub_batch(
	id VARCHAR(32) NOT NULL,
	batchcode VARCHAR(5) NOT NULL COMMENT '批次号',
	batchname VARCHAR(15) NOT NULL COMMENT '批次名称',
	batchSequence TINYINT NOT NULL COMMENT '批次顺序',
	TYPE TINYINT NOT NULL DEFAULT 1 COMMENT '批次类型 1：分拣 2：清仓',
	PRIMARY KEY(id),
	KEY(batchcode,batchSequence)
) DEFAULT CHARSET=utf8 COMMENT = '基础数据-批次信息表';

CREATE TABLE IF NOT EXISTS pub_line(
	id VARCHAR(32) NOT NULL,
	linecode TINYINT NOT NULL,
	linename VARCHAR(25) NOT NULL,
	stockmodel TINYINT NOT NULL DEFAULT 0 COMMENT '库存模式：0：无库存模式 1:有库存模式',
	PRIMARY KEY(id),
	KEY(linecode)
)DEFAULT CHARSET=utf8 COMMENT = '基础数据-分拣线信息表';

CREATE TABLE IF NOT EXISTS pub_subline(
	id VARCHAR(32) NOT NULL,
	linecode TINYINT NOT NULL COMMENT '分拣线id',
	sublinecode TINYINT NOT NULL COMMENT '子线代码',
	sublinename VARCHAR(25) NOT NULL COMMENT '子线名称',
	sublinetype TINYINT NOT NULL DEFAULT 1 COMMENT '子线类型 1:常规烟子线 2：异型烟子线',
	maxcachenum TINYINT NOT NULL COMMENT '子线最大缓存数',
	PRIMARY KEY(id),
	KEY(linecode,sublinecode)
)DEFAULT CHARSET=utf8 COMMENT = '基础数据-分拣子线信息表';

CREATE TABLE IF NOT EXISTS pub_product(
	id VARCHAR(32) NOT NULL,
	productcode VARCHAR(32) NOT NULL,
	productname VARCHAR(32) NOT NULL,
	producttype VARCHAR(2)  COMMENT '产品类型（根据产品尺寸划分的）: 1:标烟 2：细烟 3：异烟 4：特异烟',
	productwidth SMALLINT NOT NULL DEFAULT 0,
	productheight SMALLINT NOT NULL DEFAULT 0,
	productlength SMALLINT NOT NULL DEFAULT 0,
	isabnor TINYINT NOT NULL DEFAULT '1' COMMENT '是否异型烟 0：不是 1：是',
	isoverseas TINYINT NOT NULL DEFAULT '0' COMMENT '是否海外烟：0：不是 1：是',
	barcode VARCHAR(32) COMMENT '条烟码',
	jbarcode VARCHAR(32) COMMENT '件烟码',
	jqty TINYINT COMMENT '件烟条数',
	stackqty TINYINT COMMENT '一跺烟条数',
	buyprice DECIMAL(5,4) COMMENT '进价',
	tradeprice DECIMAL(5,4) COMMENT '卖价',
	retailprice DECIMAL(5,4) COMMENT '零售价',
	costprice DECIMAL(5,4) COMMENT '成本价' ,
	PRIMARY KEY(id)	,
	KEY(productcode)
)DEFAULT CHARSET=utf8 COMMENT = '基础数据-产品信息表';

CREATE TABLE IF NOT EXISTS pub_channel(
	id VARCHAR(32) NOT NULL,
	linecode TINYINT NOT NULL COMMENT '烟仓对应的分拣线代码',
	sublinecode TINYINT NOT NULL COMMENT '烟仓对应的子线代码',
	channelcode VARCHAR(20) NOT NULL,
	channelname VARCHAR(50) NOT NULL,
	channeltype TINYINT NOT NULL DEFAULT 1 COMMENT '烟仓类型 1：常规立式烟仓 2：常规卧式烟仓 3：常规通道烟仓 4：异型立式烟仓 5：异型卧式烟仓 6：异型通道烟仓 7：电子标签烟仓',
	stockModel TINYINT NOT NULL DEFAULT 0 COMMENT '库存模式：0: 无库存模式 1：有库存模式',
	carcode TINYINT NOT NULL DEFAULT 0 COMMENT '对应补货小车id',
	mcsdownno TINYINT NOT NULL COMMENT '下单时对应的plc烟仓编号',
	isdynamic TINYINT NOT NULL DEFAULT 0 COMMENT '是否动态烟仓 0：不是 1：是',
	tagaddr TINYINT NOT NULL DEFAULT 0 COMMENT '电子标签addr，非电子标签烟仓默认为0',
	tagid TINYINT NOT NULL DEFAULT 0 COMMENT '电子标签烟仓对应的id，非电子标签烟仓默认为0',
	safestockqty SMALLINT NOT NULL DEFAULT 15 COMMENT '安全库存',
	maxwidth SMALLINT NOT NULL DEFAULT 0,
	maxlength SMALLINT NOT NULL DEFAULT 0,
	maxheight SMALLINT NOT NULL DEFAULT 0,
	minwidth SMALLINT NOT NULL DEFAULT 0,
	minlength SMALLINT NOT NULL DEFAULT 0,
	minheight SMALLINT NOT NULL DEFAULT 0,
        state TINYINT NOT NULL DEFAULT 0 COMMENT '状态：0:正常 1：禁用',
        PRIMARY KEY(id),
        KEY(channelcode,sublinecode)
) DEFAULT CHARSET=utf8 COMMENT = '基础数据-烟仓信息表';

CREATE TABLE IF NOT EXISTS pub_channelbind
(
	id VARCHAR(32) NOT NULL,
	channelcode VARCHAR(32) NOT NULL COMMENT '烟仓代码',
	productcode VARCHAR(32) NOT NULL COMMENT '产品名称',
	priority TINYINT NOT NULL COMMENT '绑定优先级',
	PRIMARY KEY(id),
	KEY(channelcode,productcode)
)DEFAULT CHARSET=utf8 COMMENT = '基础数据-烟仓绑定产品信息';



DROP TABLE IF EXISTS dl_order;
DROP TABLE IF EXISTS dl_cust;
DROP TABLE IF EXISTS dl_path;
DROP TABLE IF EXISTS dl_area;
DROP TABLE IF EXISTS dl_product;

CREATE TABLE IF NOT EXISTS dl_area(
	id VARCHAR(32) NOT NULL,
	areacode VARCHAR(32) NOT NULL,
	linecode TINYINT NOT NULL COMMENT '分拣线代码',
	areaname VARCHAR(128) NOT NULL,
	areasequence INT NOT NULL,
	PRIMARY KEY(id),
	KEY(areacode)
)  DEFAULT CHARSET=utf8 COMMENT ='物流配送中心信息表';
CREATE TABLE IF NOT EXISTS dl_path (
	id VARCHAR(32) NOT NULL,
	pathcode VARCHAR(32) NOT NULL COMMENT '线路代码',
	linecode TINYINT NOT NULL COMMENT '分拣线代码',
	areacode VARCHAR(32) NOT NULL COMMENT '区域代码',
	pathname VARCHAR(128) NOT NULL COMMENT '线路名称',
	pathsequence INT NULL COMMENT '线路顺序',
	isSort TINYINT NOT NULL DEFAULT 0 COMMENT '当前线路是否分拣 1：选择分拣 0：未选择不分拣',
	PRIMARY KEY(id),
	KEY(pathcode,areacode)
) DEFAULT CHARSET=utf8 COMMENT = '下载-线路信息表';
CREATE TABLE IF NOT EXISTS dl_cust(
	id VARCHAR(32) NOT NULL,
	custcode VARCHAR(32) NOT NULL COMMENT '客户代码',
	linecode TINYINT NOT NULL COMMENT '分拣线代码',
	pathcode VARCHAR(32) NOT NULL COMMENT '线路代码',
	custname VARCHAR(128) NOT NULL COMMENT '客户名称',
	manager VARCHAR(64) COMMENT '客户管理者',
	address VARCHAR(128) COMMENT '客户地址',
	licensecode VARCHAR(32) COMMENT '客户序列号',
	phone VARCHAR(30) COMMENT '手机号码',
	custsequence INT NOT NULL COMMENT '客户顺序',
	isSort TINYINT NOT NULL DEFAULT 1 COMMENT '当前客户是否分拣 1：选择分拣 0：未选择不分拣',
	PRIMARY KEY(id),
	KEY(custcode,pathcode)
) DEFAULT CHARSET=utf8 COMMENT = '下载-客户信息表';
CREATE TABLE IF NOT EXISTS dl_product
(
	id VARCHAR(32) NOT NULL,
	productcode VARCHAR(32) NOT NULL,
	linecode TINYINT NOT NULL COMMENT '分拣线代码',
	productname VARCHAR(50) NOT NULL,
	productwidth SMALLINT ,
	productlength SMALLINT,
	productheight SMALLINT,
	isabnor TINYINT COMMENT '是否异型烟 1：是 0：否',
	isoverseas TINYINT COMMENT '是否海外烟 1：是 0：否',
	barcode VARCHAR(32) COMMENT '条烟码',
	jbarcode VARCHAR(32) COMMENT '件烟码',
	jqty SMALLINT COMMENT '件烟条数',
	buyprice DECIMAL(5,4) COMMENT '进价',
	tradeprice DECIMAL(5,4) COMMENT '卖价',
	retailprice DECIMAL(5,4) COMMENT '零售价',
	costprice DECIMAL(5,4) COMMENT '成本价' ,
	isSort TINYINT NOT NULL DEFAULT 1 COMMENT '当前品牌是否分拣',
	PRIMARY KEY(id),
	KEY(productcode)
) DEFAULT CHARSET=utf8 COMMENT = '下载-产品信息表';
CREATE TABLE IF NOT EXISTS dl_order(
	id VARCHAR(32) NOT NULL,
	ordercode VARCHAR(32) NOT NULL COMMENT '订单代码',
	linecode TINYINT NOT NULL COMMENT '分拣线代码',
	orderdate VARCHAR(20) NOT NULL COMMENT '订单日期',
	custcode VARCHAR(32) NOT NULL COMMENT '客户代码',
	pathcode VARCHAR(32) NOT NULL COMMENT '线路代码',
	productcode VARCHAR(32) NOT NULL COMMENT '产品代码',
	qty INT NOT NULL COMMENT '产品数量',
	PRIMARY KEY(id),
	KEY(ordercode,custcode)
) DEFAULT CHARSET=utf8 COMMENT = '下载-订单数据';



DROP TABLE IF EXISTS op_channel;
DROP TABLE IF EXISTS op_orderChannel;
DROP TABLE IF EXISTS op_order;
DROP TABLE IF EXISTS op_cust;
DROP TABLE IF EXISTS op_path;
DROP TABLE IF EXISTS op_area;
DROP TABLE IF EXISTS op_product;
DROP TABLE IF EXISTS op_productload;
DROP TABLE IF EXISTS op_batch;

CREATE TABLE IF NOT EXISTS op_batch(
	id VARCHAR(32) NOT NULL,
	orderdate VARCHAR(20) NOT NULL COMMENT '订单日期',
	pickdate VARCHAR(20) NOT NULL COMMENT '分件日期',
	batchcode VARCHAR(5) NOT NULL COMMENT '批次号',
	linecode TINYINT NOT NULL COMMENT '分拣线代码',
	batchsequence SMALLINT NOT NULL COMMENT '批次顺序',
	qty INT NOT NULL COMMENT '分拣总量',
	custnum INT NOT NULL COMMENT '客户数量',
	custavgqty INT NOT NULL COMMENT '客户平均订单数',
	PRIMARY KEY(id)
)DEFAULT CHARSET=utf8 COMMENT = '操作数据-批次信息表';
CREATE TABLE IF NOT EXISTS op_area(
	id VARCHAR(32) NOT NULL,
	areacode VARCHAR(32) NOT NULL,
	batchid VARCHAR(32) NOT NULL,
	areaname VARCHAR(128) NOT NULL,
	areasequence INT NOT NULL,
	linecode TINYINT NOT NULL,
	PRIMARY KEY(id),
	KEY(areacode)
)DEFAULT CHARSET=utf8 COMMENT ='优化-物流配送中心信息表';
CREATE TABLE IF NOT EXISTS op_path (
	id VARCHAR(32) NOT NULL,
	pathcode VARCHAR(32) NOT NULL COMMENT '线路代码',
	batchid VARCHAR(32) NOT NULL COMMENT '批次id',
	areacode VARCHAR(32) NOT NULL COMMENT '区域代码',
	pathname VARCHAR(128) NOT NULL COMMENT '线路名称',
	pathsequence INT NULL COMMENT '线路顺序',
	linecode TINYINT NOT NULL,
	PRIMARY KEY(id),
	KEY(pathcode,areacode)
)DEFAULT CHARSET=utf8 COMMENT = '优化-线路信息表';

CREATE TABLE IF NOT EXISTS op_cust(
	id VARCHAR(32) NOT NULL,
	custcode VARCHAR(32) NOT NULL COMMENT '客户代码',
	batchid VARCHAR(32) NOT NULL COMMENT '批次id',
	pathcode VARCHAR(32) NOT NULL COMMENT '线路代码',
	custname VARCHAR(128) NOT NULL COMMENT '客户名称',
	manager VARCHAR(64) COMMENT '客户管理者',
	address VARCHAR(128) COMMENT '客户地址',
	licensecode VARCHAR(32) COMMENT '客户序列号',
	phone VARCHAR(30) COMMENT '手机号码',
	custsequence INT NOT NULL COMMENT '客户顺序',
	linecode TINYINT NOT NULL,
	PRIMARY KEY(id),
	KEY(custcode,pathcode)
)DEFAULT CHARSET=utf8 COMMENT = '优化数据--客户信息表';
CREATE TABLE IF NOT EXISTS op_product(
	id VARCHAR(32) NOT NULL,
	batchid VARCHAR(32) NOT NULL,
	productcode VARCHAR(32) NOT NULL,
	linecode INT NOT NULL COMMENT '分拣线代码',
	productname VARCHAR(50) NOT NULL,
	productwidth INT ,
	productlength INT,
	productheight INT,
	isabnor TINYINT COMMENT '是否异型烟 1：是 0：否',
	isoverseas TINYINT COMMENT '是否海外烟 1：是 0：否',
	barcode VARCHAR(32) COMMENT '条烟码',
	jbarcode VARCHAR(32) COMMENT '件烟码',
	jqty SMALLINT COMMENT '件烟条数',
	buyprice DECIMAL(5,4) COMMENT '进价',
	tradeprice DECIMAL(5,4) COMMENT '卖价',
	retailprice DECIMAL(5,4) COMMENT '零售价',
	costprice DECIMAL(5,4) COMMENT '成本价' ,
	PRIMARY KEY(id),
	KEY(productcode)
)DEFAULT CHARSET=utf8 COMMENT = '优化数据--产品分拣信息表';
CREATE TABLE IF NOT EXISTS op_order(
	id VARCHAR(32) NOT NULL,
	ordercode VARCHAR(32) NOT NULL COMMENT '订单代码',
	orderdate VARCHAR(32) NOT NULL COMMENT '订单如期',
	batchid VARCHAR(32) NOT NULL COMMENT '批次id',
	custcode VARCHAR(32) NOT NULL COMMENT '客户代码',
	pathcode VARCHAR(32) NOT NULL COMMENT '线路代码',
	productcode VARCHAR(32) NOT NULL COMMENT '产品代码',
	qty INT NOT NULL COMMENT '产品数量',
	linecode TINYINT NOT NULL,
	PRIMARY KEY(id),
	KEY(ordercode,custcode)
)DEFAULT CHARSET=utf8 COMMENT = '优化-订单数据';

CREATE TABLE IF NOT EXISTS op_channel
(
	id VARCHAR(32) NOT NULL,
	batchid VARCHAR(32) NOT NULL,
	channelcode VARCHAR(32) NOT NULL,
	productcode VARCHAR(32) NOT NULL,
	sublinecode TINYINT NOT NULL COMMENT '子线代码',
	stockqty INT NOT NULL DEFAULT 0 COMMENT '库存数量',
	qty INT NOT NULL COMMENT '上烟数量',
	linecode TINYINT NOT NULL,
	PRIMARY KEY(id),
	KEY(channelcode)
)DEFAULT CHARSET=utf8 COMMENT = '优化-烟仓产品分配信息表';

CREATE TABLE IF NOT EXISTS op_orderChannel(
	id VARCHAR(32) NOT NULL,
	orderid VARCHAR(32) NOT NULL COMMENT '优化-订单id',
	channelcode VARCHAR(32) NOT NULL COMMENT '烟仓代码',
	sublinecode TINYINT NOT NULL COMMENT '子线代码',
	channelqty INT NOT NULL COMMENT  '烟仓出烟条数',
	PRIMARY KEY(id),
	FOREIGN KEY(orderid) REFERENCES op_order(id)
)DEFAULT CHARSET=utf8 COMMENT = '优化-订单烟仓信息表';

CREATE TABLE IF NOT EXISTS op_productload(
	id VARCHAR(32) NOT NULL,
	productcode VARCHAR(32) NOT NULL,
	batchid VARCHAR(32) NOT NULL COMMENT '分拣批次id',
	qty INT NOT NULL COMMENT '分拣数量',
	prepareqty INT NOT NULL COMMENT '备货数量',
	linecode TINYINT NOT NULL,
	PRIMARY KEY(id)
)DEFAULT CHARSET=utf8 COMMENT = '优化-产品备货信息表';


DROP TABLE IF EXISTS st_orderDet;
DROP TABLE IF EXISTS st_orderMas;
DROP TABLE IF EXISTS st_product;
DROP TABLE IF EXISTS st_channel;
DROP TABLE IF EXISTS st_cust;
DROP TABLE IF EXISTS st_path;
DROP TABLE IF EXISTS st_batch;

CREATE TABLE IF NOT EXISTS st_batch
(
	id VARCHAR(32) NOT NULL,
	orderdate VARCHAR(32) NOT NULL,
	pickdate VARCHAR(32) NOT NULL,
	linecode TINYINT NOT NULL COMMENT '分拣线代码',
	batchcode VARCHAR(2) NOT NULL COMMENT '批次代码',
	state TINYINT NOT NULL DEFAULT 0 COMMENT '批次状态 0：初始 1：开始作业 2：分拣作业 9：完成作业 8：任务作废',
	qty INT NOT NULL COMMENT '批次分拣数量',
	custnum INT NOT NULL COMMENT '客户数量',
	ordernum INT NOT NULL COMMENT '订单数量',
	custavgqty INT NOT NULL COMMENT '客户订单平均数量',
	orderavgqty INT NOT NULL COMMENT '订单平均数量',
	begintm DATE NULL COMMENT '开始分拣时间',
	endtm DATE NULL COMMENT '完成分拣时间',
	sorttm SMALLINT NOT NULL DEFAULT 0 COMMENT '分拣总时间（分钟）',
	faulttm SMALLINT NOT NULL DEFAULT 0 COMMENT '设备告警时间（分钟）',
	pausetm SMALLINT NOT NULL DEFAULT 0 COMMENT '分拣暂停时间',
	PRIMARY KEY(id)
)DEFAULT CHARSET=utf8 COMMENT = '分拣--批次信息表';
CREATE TABLE IF NOT EXISTS st_path(
	id VARCHAR(32) NOT NULL,
	pathcode VARCHAR(32) NOT NULL COMMENT '线路代码',
	pathname VARCHAR(32) NOT NULL COMMENT '线路名称',
	pathsequence INT NOT NULL COMMENT '线路顺序',
	qty INT NOT NULL DEFAULT 0 COMMENT '线路分拣总量',
	batchid VARCHAR(32) NOT NULL COMMENT '批次id',
	PRIMARY KEY(id),
	KEY(pathcode,batchid)
)DEFAULT CHARSET=utf8 COMMENT = '分拣-线路信息表';

CREATE TABLE IF NOT EXISTS st_cust(
	id VARCHAR(32) NOT NULL,
	custcode VARCHAR(32) NOT NULL COMMENT '客户代码',
	custname VARCHAR(128) NOT NULL COMMENT '客户名称',
	custsequence SMALLINT NOT NULL COMMENT '客户顺序',
	serialNumber SMALLINT NOT NULL COMMENT '客户序列',
	address VARCHAR(128) NULL COMMENT '地址',
	licensecode VARCHAR(32) NULL COMMENT '客户许可证号',
	phone VARCHAR(32) NULL COMMENT '电话',
	manager VARCHAR(32) NULL COMMENT '负责人',
	pathcode VARCHAR(32) NOT NULL COMMENT '线路id',
	batchid VARCHAR(32) NOT NULL COMMENT '批次id',
	PRIMARY KEY(id),
	KEY(custcode,pathcode,batchid)
)DEFAULT CHARSET=utf8 COMMENT = '分拣-客户信息表';

CREATE TABLE IF NOT EXISTS st_channel(
	id VARCHAR(32) NOT NULL,
	channelcode VARCHAR(32) NOT NULL,
	productcode VARCHAR(32) NOT NULL,
	entryqty SMALLINT NOT NULL COMMENT '烟仓入库量',
	stockqty SMALLINT NOT NULL DEFAULT 0 COMMENT '烟仓库存量',
	batchid VARCHAR(32) NOT NULL COMMENT '批次id',
	PRIMARY KEY(id),
	KEY(channelcode,productcode,batchid)
)DEFAULT CHARSET=utf8 COMMENT = '分拣-烟仓信息表';

CREATE TABLE IF NOT EXISTS st_product
(
	id VARCHAR(32) NOT NULL,
	productcode VARCHAR(32) NOT NULL,
	qty INT NOT NULL COMMENT '分拣数量',
	prepareqty INT NOT NULL COMMENT '备货数量',
	batchid VARCHAR(32) NOT NULL COMMENT '批次id',
	PRIMARY KEY(id),
	KEY(productcode,batchid)
)DEFAULT CHARSET=utf8 COMMENT = '分拣-产品信息表';

CREATE TABLE IF NOT EXISTS st_orderMas
(
	id VARCHAR(32) NOT NULL,
	ordercode VARCHAR(32) NOT NULL COMMENT '订单代码',
	serialNo INT NOT NULL COMMENT '订单流水号',
	sublinecode SMALLINT NOT NULL COMMENT '子线代码',
	sublineqty SMALLINT NOT NULL COMMENT '子线产品数量',
	totalQty INT NOT NULL COMMENT '订单产品总量',
	exportno SMALLINT NOT NULL DEFAULT 0 COMMENT '出口号',
	state INT NOT NULL DEFAULT 0 COMMENT '订单状态 0：初始 1：下单完成',
	sorttm DATE NULL COMMENT '下单时间',
	custcode VARCHAR(32) NOT NULL COMMENT '客户id',
	batchid VARCHAR(32) NOT NULL COMMENT '批次id',
	PRIMARY KEY(id),
	KEY(serialNo,custcode,batchid)
)DEFAULT CHARSET=utf8 COMMENT = '分拣--订单主表信息';

CREATE TABLE IF NOT EXISTS st_orderDet
(
	id VARCHAR(32) NOT NULL,
	channelcode VARCHAR(32) NOT NULL,
	productcode VARCHAR(32) NOT NULL,
	qty SMALLINT NOT NULL,
	ordermasid VARCHAR(32) NOT NULL,
	PRIMARY KEY(id),
	FOREIGN KEY(ordermasid)	REFERENCES st_orderMas(id),
	KEY(channelcode,productcode)
)DEFAULT CHARSET=utf8 COMMENT = '分拣-订单明细表';



CREATE TABLE IF NOT EXISTS wm_stock(
	id VARCHAR(32) NOT NULL,
	stockcode VARCHAR(32) NOT NULL COMMENT '库区代码',
	productcode VARCHAR(32) NOT NULL COMMENT '产品代码',
	qty SMALLINT NOT NULL COMMENT '产品数量',
	PRIMARY KEY(id),
	KEY(stockcode,productcode)
)DEFAULT CHARSET=utf8 COMMENT = '仓库数据-库存信息表';
