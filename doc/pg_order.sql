/*
 Navicat Premium Data Transfer

 Source Server         : 新商保_test
 Source Server Type    : MySQL
 Source Server Version : 50724
 Source Host           : testw.dhjyib.cc:3306
 Source Schema         : payment_gateway

 Target Server Type    : MySQL
 Target Server Version : 50724
 File Encoding         : 65001

 Date: 01/02/2024 14:00:12
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for pg_order
-- ----------------------------
DROP TABLE IF EXISTS `pay_order`;
CREATE TABLE ``pay_order`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `out_trade_no` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '内部订单号',
  `transaction_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '微信支付订单号或者支付宝交易流水号',
  `pay_type` tinyint(3) UNSIGNED NOT NULL DEFAULT 0 COMMENT '支付类型：1微信；2支付宝',
  `trade_type` tinyint(3) UNSIGNED NOT NULL DEFAULT 0 COMMENT '交易类型：微信支付：1-JSAPI支付；2-小程序支付；3-Native支付；4-APP支付；5-H5支付；6-付款码支付；7-刷脸支付；支付宝支付：11-手机网站支付；12-电脑网站支付；',
  `trade_state` tinyint(3) UNSIGNED NOT NULL DEFAULT 0 COMMENT '交易状态：微信支付：1-未支付；2-已关闭；3-用户支付中（付款码支付）；4-已撤销（付款码支付）；5-支付失败（其他原因，如银行返回失败）；6-支付成功；7-转入退款；支付宝支付：11-交易创建，等待买家付款；12-未付款交易超时关闭，或支付完成后全额退款；13-交易支付成功；14-交易结束，不可退款；',
  `total_fee` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '订单总金额，单位为分',
  `remark` varchar(2048) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '备注',
  `create_time` datetime(0) NOT NULL DEFAULT '1000-01-01 00:00:00' COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL DEFAULT '1000-01-01 00:00:00' COMMENT '更新时间',
  `delete_flag` tinyint(3) UNSIGNED NOT NULL DEFAULT 0 COMMENT '删除状态：0-未删除；1-已删除',
  `pay_config_id` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '支付配置id',
  `notify_url` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '支付结果异步通知地址',
  `type` tinyint(4) UNSIGNED NOT NULL DEFAULT 1 COMMENT '订单类型：1-支付，2-退款',
  `out_refund_no` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '内部退款订单号',
  `refund_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '微信退款订单号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2583 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '订单表' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
