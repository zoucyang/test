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

 Date: 01/02/2024 13:59:55
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for pay_config
-- ----------------------------
DROP TABLE IF EXISTS pay_config
CREATE TABLE `pg_pay_config`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `pay_type` tinyint(3) UNSIGNED NOT NULL DEFAULT 0 COMMENT '支付类型：1微信；2支付宝',
  `trade_type` tinyint(3) UNSIGNED NOT NULL DEFAULT 0 COMMENT '交易类型：微信支付：1-JSAPI支付；2-小程序支付；3-Native支付；4-APP支付；5-H5支付；6-付款码支付；7-刷脸支付；支付宝支付：11-手机网站支付；12-电脑网站支付；',
  `config` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '配置内容：json格式',
  `create_time` datetime(0) NOT NULL DEFAULT '1000-01-01 00:00:00' COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL DEFAULT '1000-01-01 00:00:00' COMMENT '更新时间',
  `delete_flag` tinyint(3) UNSIGNED NOT NULL DEFAULT 0 COMMENT '删除状态：0-未删除；1-已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '支付配置表' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
