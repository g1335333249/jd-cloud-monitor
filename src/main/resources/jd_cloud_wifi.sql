/*
 Navicat Premium Data Transfer

 Source Server         : openwrt
 Source Server Type    : MySQL
 Source Server Version : 80020
 Source Host           : www.jsj1304.com:3399
 Source Schema         : jd_cloud_wifi

 Target Server Type    : MySQL
 Target Server Version : 80020
 File Encoding         : 65001

 Date: 26/07/2021 09:54:41
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for app_device_info
-- ----------------------------
DROP TABLE IF EXISTS `app_device_info`;
CREATE TABLE `app_device_info` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `product_id` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '产品ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Table structure for app_point_income
-- ----------------------------
DROP TABLE IF EXISTS `app_point_income`;
CREATE TABLE `app_point_income` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `device_id` bigint DEFAULT NULL,
  `event_date` datetime DEFAULT NULL,
  `today_point_income` int DEFAULT NULL COMMENT '今日积分',
  `can_use_point_income` int DEFAULT NULL COMMENT '可用积分',
  `all_point_income` int DEFAULT NULL COMMENT '历史总积分',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Table structure for app_router_pcdn_status
-- ----------------------------
DROP TABLE IF EXISTS `app_router_pcdn_status`;
CREATE TABLE `app_router_pcdn_status` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `device_id` bigint DEFAULT NULL COMMENT '设备ID',
  `event_time` datetime DEFAULT NULL COMMENT '采集时间',
  `plugin_run_pos` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `plugin_is_ext` tinyint(1) DEFAULT NULL COMMENT '是否外置存储',
  `cache_size` int DEFAULT NULL COMMENT '缓存容量',
  `nickname` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '插件昵称',
  `name` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '插件名称',
  `status` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '插件状态',
  PRIMARY KEY (`id`),
  KEY `idx_eventTime` (`event_time`),
  KEY `idx_deviceId` (`device_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Table structure for app_router_status_detail
-- ----------------------------
DROP TABLE IF EXISTS `app_router_status_detail`;
CREATE TABLE `app_router_status_detail` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `device_id` bigint DEFAULT NULL,
  `event_time` datetime DEFAULT NULL,
  `upload` int DEFAULT NULL COMMENT '上传速度',
  `download` int DEFAULT NULL COMMENT '下载速度',
  `cpu` double(11,2) DEFAULT NULL COMMENT 'CPU负载（%）',
  `mem` int DEFAULT NULL COMMENT '内存占用（%）',
  `rom` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `rom_type` int DEFAULT NULL,
  `wan_ip` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `model_name` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `online_time` int DEFAULT NULL COMMENT '在线时间',
  `model` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `ap_mode` int DEFAULT NULL,
  `sn` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Table structure for device_info
-- ----------------------------
DROP TABLE IF EXISTS `device_info`;
CREATE TABLE `device_info` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `cost` double(10,2) DEFAULT NULL COMMENT '成本',
  `login_host` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '登录IP地址',
  `login_port` int DEFAULT NULL COMMENT '登录端口',
  `username` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '登录用户名',
  `password` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '登录密码',
  `feed_id` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '设备ID',
  `platform` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '平台',
  `product_name` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '产品名称',
  `version` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '版本',
  `ip_addr` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'IP地址',
  `wan_dns` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'wan口DNS',
  `mac_addr` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '设备MAC地址',
  `mac` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'MAC编号',
  `update_alidns` tinyint(1) DEFAULT '0' COMMENT '是否更新阿里云DNS',
  `is_valid` tinyint(1) DEFAULT '1' COMMENT '是否有效',
  `storage` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '容量',
  `sn` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'sn',
  `online_time` int DEFAULT NULL COMMENT '在线时间',
  `router_version` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '固件版本',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `product_id` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `config_type` int DEFAULT NULL,
  `product_uuid` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `device_name` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '设备名称',
  `own_flag` int DEFAULT NULL COMMENT '是否所有者',
  `cname` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `device_page_type` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `access_key` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Table structure for network_statistics
-- ----------------------------
DROP TABLE IF EXISTS `network_statistics`;
CREATE TABLE `network_statistics` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `device_id` bigint DEFAULT NULL COMMENT '设备ID',
  `event_time` datetime DEFAULT NULL COMMENT '事件时间',
  `upstream_traffic` double(20,6) DEFAULT NULL COMMENT '上行流量',
  `downstream_traffic` double(20,6) DEFAULT NULL COMMENT '下行流量',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Table structure for storage_statistics
-- ----------------------------
DROP TABLE IF EXISTS `storage_statistics`;
CREATE TABLE `storage_statistics` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `device_id` bigint DEFAULT NULL COMMENT '设备ID',
  `mode` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '模式',
  `event_time` datetime DEFAULT NULL COMMENT '事件时间',
  `total` bigint DEFAULT NULL COMMENT '总量',
  `used` bigint DEFAULT NULL COMMENT '使用',
  `avail` bigint DEFAULT NULL COMMENT '空闲',
  `status` int DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5110 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

SET FOREIGN_KEY_CHECKS = 1;
