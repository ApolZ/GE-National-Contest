-- MySQL dump 10.13  Distrib 5.6.24, for Win64 (x86_64)
--
-- Host: localhost    Database: ge
-- ------------------------------------------------------
-- Server version	5.6.27-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `t_onsite`
--

DROP TABLE IF EXISTS `t_onsite`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_onsite` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `toolkeeperID` varchar(255) DEFAULT NULL,
  `toolboxID` varchar(255) DEFAULT NULL,
  `engineerID` varchar(255) DEFAULT NULL,
  `toolID` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `lending_time` varchar(255) DEFAULT NULL,
  `return_time` varchar(255) DEFAULT NULL,
  `return_status` varchar(255) DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  `orderID` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_onsite`
--

LOCK TABLES `t_onsite` WRITE;
/*!40000 ALTER TABLE `t_onsite` DISABLE KEYS */;
INSERT INTO `t_onsite` VALUES (3,'2201114238','356002078','2201414038','1014091234','seiee','2016-04-28 14:33:05','2016-04-28 15:07:40','Return',NULL,'3400230080'),(4,'2201114238','356002078','2201414038','1014091236','seiee','2016-04-28 14:33:05','2016-04-28 15:08:10','Broken','The tool is broken because that','3400230080'),(5,'2201114218','356032679','2201014037','1014091234','Beijing, China','2016-04-28 17:00:16',NULL,NULL,NULL,'34002500838'),(6,'2201114218','356032679','2201014037','1014091234','Beijing, China','2016-04-28 17:03:00',NULL,NULL,NULL,'34002500838'),(7,'2201114218','356032679','2201014037','5214091410','Beijing, China','2016-04-28 17:03:00','2016-04-28 17:06:33','Broken','The tool is broken because it was used to saw steal','34002500838'),(8,'2201114218','356032679','2201014037','8314091410','Beijing, China','2016-04-28 17:03:00','2016-04-28 17:04:16','Return',NULL,'34002500838');
/*!40000 ALTER TABLE `t_onsite` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_order`
--

DROP TABLE IF EXISTS `t_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `orderID` varchar(255) DEFAULT NULL,
  `onsite_time` varchar(255) DEFAULT NULL,
  `location` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `reservation_time` varchar(255) DEFAULT NULL,
  `clientID` varchar(255) DEFAULT NULL,
  `request_accept_time` varchar(255) DEFAULT NULL,
  `managerID` varchar(255) DEFAULT NULL,
  `engineer_confirm_time` varchar(255) DEFAULT NULL,
  `duty_engineerid` varchar(255) DEFAULT NULL,
  `predicted_return_time` varchar(255) DEFAULT NULL,
  `whether_express` varchar(255) DEFAULT NULL,
  `other_engineer` varchar(255) DEFAULT NULL,
  `tools_reservation_time` varchar(255) DEFAULT NULL,
  `tools_reservationid` varchar(2550) DEFAULT NULL,
  `value` varchar(255) DEFAULT NULL,
  `keeper_confirm_time` varchar(255) DEFAULT NULL,
  `toolkeeperid` varchar(255) DEFAULT NULL,
  `task_begin_time` varchar(255) DEFAULT NULL,
  `task_end_time` varchar(255) DEFAULT NULL,
  `task_status` varchar(255) DEFAULT NULL,
  `client_confirm_time` varchar(255) DEFAULT NULL,
  `client_status` varchar(255) DEFAULT NULL,
  `manager_confirm_time` varchar(255) DEFAULT NULL,
  `manager_status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_order`
--

LOCK TABLES `t_order` WRITE;
/*!40000 ALTER TABLE `t_order` DISABLE KEYS */;
INSERT INTO `t_order` VALUES (13,'34002100842','2016-05-06','Minhang, Shanghai, China','Our generator doesn\'t work well','We bought this Generator 10 years ago form GE. Now it doesn\'t work well. I think it need some maintenance.','2016-04-28 16:22:15','340021008','2016-04-28 16:27:11','2201614038','2016-04-28 16:29:23','2200314038','2016-05-07','True','2201414038|2201314036','2016-04-28 16:43:28','2114091210|6512091610','Low',NULL,NULL,'2016-04-28 16:45:27','2016-04-28 16:46:58','True','2016-04-28 16:49:01','True','2016-04-28 16:49:47','True'),(14,'34002100876','2016-05-03','Minhang, Shanghai','There are some problems with the motor','It can not rotate anymore. Maybe because we have used it for too long.','2016-04-28 16:24:03','340021008','2016-04-28 16:27:31','2201614038','2016-04-28 19:10:17','2201414038',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(15,'34002500838','2016-06-03','Beijing, China','The engine of our plane doesn\'t work well','The engine of our plane is made by GE. It has worked for too long and the noise seems to be too loud when it works.','2016-04-28 16:26:12','340025008','2016-04-28 16:27:49','2201614038','2016-04-28 16:51:10','2200114098','2016-05-10',NULL,'2201014037','2016-04-28 16:52:35','1014091236|6123101213|5214091410|8314091410|8116091210','High','2016-04-28 16:54:32','2201114218','2016-04-28 16:55:03','2016-04-28 17:08:15','True','2016-04-28 17:09:18','True','2016-04-28 17:10:12','True');
/*!40000 ALTER TABLE `t_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_staff`
--

DROP TABLE IF EXISTS `t_staff`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_staff` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `staffID` varchar(25) NOT NULL,
  `name` varchar(25) DEFAULT NULL,
  `hire_date` varchar(25) DEFAULT NULL,
  `password` varchar(25) DEFAULT NULL,
  `picture` varchar(45) DEFAULT NULL,
  `position` varchar(12) DEFAULT NULL,
  `remarks` varchar(45) DEFAULT NULL,
  `phone` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `staffID_index` (`staffID`),
  KEY `name_index` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_staff`
--

LOCK TABLES `t_staff` WRITE;
/*!40000 ALTER TABLE `t_staff` DISABLE KEYS */;
INSERT INTO `t_staff` VALUES (1,'2201414038','Abigail Bush','2000-02-12 14:23:11','2201414038','/static/images/staff/r1.jpg','Engineer','aggressive creative ','13912282645'),(2,'2201413058','Adela Jobs','2005-03-12 14:23:11','2201413058','/static/images/staff/r2.jpg','Engineer','aggressive candid ','13812282456'),(3,'2201314036','Agatha Gates','2008-02-13 14:23:11','2201314036','/static/images/staff/r3.jpg','Engineer','cooperative creative ','13912281036'),(4,'2201414098','Alberta Jackson','2010-05-12 14:23:11','2201414098','/static/images/staff/r4.jpg','Engineer','trustworthy understanding','13912288714'),(5,'2201414738','Althea  Logan','2013-07-12 14:23:11','2201414738','/static/images/staff/r5.jpg','Engineer','tireless thoughtful ','13912281563'),(6,'2201014037','Amelia James','2016-02-12 14:23:11','2201014037','/static/images/staff/r6.jpg','Engineer','self-conscious sensible','13912282569'),(7,'2200914338','Anastasia Michael','2008-02-13 14:23:11','2200914338','/static/images/staff/r7.jpg','Engineer','sociable  sincere','13912281456'),(8,'2201114238','Annabelle Kevin','2010-05-12 14:23:11','2201114238','/static/images/staff/r8.jpg','ToolKeeper','spirited steady','13912210365'),(9,'2201114198','Belinda Elijah','2013-07-12 14:23:11','2201114198','/static/images/staff/r9.jpg','ToolKeeper','straightforward supportive','13912265445'),(10,'2201614038','Bernice Gates','2009-02-12 14:23:11','2201614038','/static/images/staff/r10.jpg','Manager','aggressive aggressive ','13912212365'),(11,'2200314038','Bess Alexander','2000-02-12 14:23:11','2200314038','/static/images/staff/r11.jpg','Engineer','aggressive candid ','13912282645'),(12,'2201413678','Beverly Logan','2005-03-12 14:23:11','2201413678','/static/images/staff/r12.jpg','Engineer','cooperative creative ','13812282456'),(13,'2201314036','Breenda Gates','2008-02-13 14:23:11','2201314036','/static/images/staff/r13.jpg','Engineer','trustworthy understanding','13912281036'),(14,'2200114098','Candance Jobs','2010-05-12 14:23:11','2200114098','/static/images/staff/r14.jpg','Engineer','tireless thoughtful ','13912288714'),(15,'2200214738','Caroline Bush','2013-07-12 14:23:11','2200214738','/static/images/staff/r15.jpg','Engineer','self-conscious sensible','13912281563'),(16,'2200514037','Cherry Alexander','2016-02-12 14:23:11','2200514037','/static/images/staff/r16.jpg','Engineer','sociable  sincere','13912282569'),(17,'2200914038','Deirdre William','2008-02-13 14:23:11','2200914038','/static/images/staff/r17.jpg','Engineer','spirited steady','13912281456'),(18,'2201114218','Dorothy Landon','2010-05-12 14:23:11','2201114218','/static/images/staff/r18.jpg','ToolKeeper','straightforward supportive','13912210365'),(19,'2201114178','Evangeline Bush','2013-07-12 14:23:11','2201114178','/static/images/staff/r19.jpg','ToolKeeper','trustworthy understanding','13912265445'),(20,'2201614138','Geraldine Bush','2009-02-12 14:23:11','2201614138','/static/images/staff/r20.jpg','Manager','tireless thoughtful ','13912212365'),(21,'340322008','Deirdre Bush','2000-02-12 14:23:11','340322008','/static/images/staff/c1.jpg','Client','spirited steady','15112282645'),(22,'340021008','Dorothy Jobs','2005-03-12 14:23:11','340021008','/static/images/staff/c2.jpg','Client','straightforward supportive','13712282445'),(23,'340025008','Evangeline David','2008-02-13 14:23:11','340025008','/static/images/staff/c3.jpg','Client','trustworthy understanding','13512282785'),(24,'340023008','Geraldine  Daniel','2010-05-12 14:23:11','340023008','/static/images/staff/c4.jpg','Client','tireless thoughtful ','13712282634');
/*!40000 ALTER TABLE `t_staff` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_tool`
--

DROP TABLE IF EXISTS `t_tool`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_tool` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `toolID` varchar(45) DEFAULT NULL,
  `name` varchar(25) DEFAULT NULL,
  `purchase_date` varchar(45) DEFAULT NULL,
  `service_life` varchar(12) DEFAULT NULL,
  `picture` varchar(45) DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `toolcenterID` varchar(45) DEFAULT NULL,
  `min_quant` varchar(45) DEFAULT NULL,
  `price` varchar(45) DEFAULT NULL,
  `broken_or_lost_date` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `toolID_index` (`toolID`),
  KEY `toolcenterID_index` (`toolcenterID`),
  KEY `minQuant_index` (`min_quant`),
  KEY `price_index` (`price`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_tool`
--

LOCK TABLES `t_tool` WRITE;
/*!40000 ALTER TABLE `t_tool` DISABLE KEYS */;
INSERT INTO `t_tool` VALUES (1,'2114091210','spanner','2000-02-12 14:23:11','365*25','/static/images/tool/t1.jpg','Broken','a wrench that has a hole, projection, or hook at one or both ends of the head for engaging with a corresponding device on the object that is to be turned','16','2','5','2016-04-28'),(2,'2114091211','spanner','2000-02-12 14:23:11','365*25','/static/images/tool/t1.jpg','OnUsing','a wrench that has a hole, projection, or hook at one or both ends of the head for engaging with a corresponding device on the object that is to be turned','16','2','5','2016-04-28'),(3,'3214092311','electrodrill','2005-03-12 14:23:11','365*26','/static/images/tool/t2.jpg','Good','an instrument with an edged or pointed end for making holes in hard substances by revolving or by a succession of blows','16','1','5',NULL),(4,'3414091218',' Fast Screwdrivers','2008-02-13 14:23:11','365*27','/static/images/tool/t3.jpg','Good',' a tool that is used for turning screws','16','1','10',NULL),(5,'4114091214','Ruler','2010-05-12 14:23:11','365*28','/static/images/tool/t4.jpg','Good','a straight piece of plastic, wood, or metal that has marks on it to show units of length and that is used to measure things','16','1','10',NULL),(6,'5214091410','Saw','2013-07-12 14:23:11','365*29','/static/images/tool/t5.jpg','OnUsing','a hand tool for cutting wood or other materials, typically with a long, thin serrated steel blade and operated using a backward and forward movement.','16','1','20',NULL),(7,'5612091610','Inspection Mirror','2016-02-12 14:23:11','365*30','/static/images/tool/t6.jpg','Good',' something that shows what another thing is like in a very clear and accurate way','10','1','20',NULL),(8,'6112091245','Chisels','2008-02-13 14:23:11','365*18','/static/images/tool/t7.jpg','Good','a metal tool with a flat, sharp end that is used to cut and shape a solid material','10','1','35',NULL),(9,'2312101213',' Needle Nose Pliers  ','2010-05-12 14:23:11','365*19','/static/images/tool/t8.jpg','Good','pliers with long slender jaws used for grasping small or thin objects\n','10','1','35',NULL),(10,'3014091234',' Diagonal Cutters','2013-07-12 14:23:11','365*20','/static/images/tool/t9.jpg','Good','a machine, or tool that cuts something','10','1','40',NULL),(11,'8116091210',' electron probe','2009-02-12 14:23:11','365*21','/static/images/tool/t10.jpg','OnUsing','a microprobe that uses an electron beam to induce X-ray emissions in a sample\n','10','1','40',NULL),(12,'9014091210','Pin Removal Tools','2000-02-12 14:23:11','365*22','/static/images/tool/t11.jpg','Good','Pin removaltool','18','1','5',NULL),(13,'1214092311',' Receptacle','2005-03-12 14:23:11','365*23','/static/images/tool/t12.jpg','Good','a container that is used to hold something','18','1','5',NULL),(14,'7114091218',' Electronic Runner','2008-02-13 14:23:11','365*24','/static/images/tool/t13.jpg','Good','electronic runner','18','1','10',NULL),(15,'7014091214',' Outside Micrometer','2010-05-12 14:23:11','365*15','/static/images/tool/t14.jpg','Good','an instrument used with a telescope or microscope for measuring minute distances','18','1','10',NULL),(16,'8314091410',' Multi Meter','2008-02-13 14:23:11','365*16','/static/images/tool/t15.jpg','OnUsing','an instrument designed to measure electric current, voltage, and usually resistance, typically over several ranges of value.','18','1','20',NULL),(17,'6512091610',' Scraper Knife','2016-02-12 14:23:11','365*17','/static/images/tool/t16.jpg','Return','a usually sharp blade attached to a handle that is used for cutting or as a weapon','18','1','20',NULL),(18,'8112091245',' Copper Hammer','2008-02-13 14:23:11','365*18','/static/images/tool/t17.jpg','Good','a tool that has a heavy metal head attached to a handle and that is used for hitting nails or breaking things apart','18','1','35',NULL),(19,'6123101213',' Pneumatic Drills','2010-05-12 14:23:11','365*19','/static/images/tool/t18.jpg','OnUsing','to fix something in the mind or habit pattern of by repetitive instruction','18','1','35',NULL),(20,'1014091234',' Jack','2013-07-12 14:23:11','365*20','/static/images/tool/t19.jpg','OnUsing',' a device used for lifting something heavy','18','3','40',NULL),(21,'1014091235',' Jack','2013-07-12 14:23:11','365*20','/static/images/tool/t19.jpg','OnUsing',' a device used for lifting something heavy','18','3','40',NULL),(22,'1014091236',' Jack','2013-07-12 14:23:11','365*20','/static/images/tool/t19.jpg','OnUsing',' a device used for lifting something heavy','18','3','40',NULL),(23,'8916091210',' Adhesive Tape','2009-02-12 14:23:11','365*21','/static/images/tool/t20.jpg','Good','tape coated on one side with an adhesive mixture','18','3','40',NULL),(24,'8916091211',' Adhesive Tape','2009-02-12 14:23:11','365*21','/static/images/tool/t20.jpg','Good','tape coated on one side with an adhesive mixture','18','3','40',NULL),(25,'8916091212',' Adhesive Tape','2009-02-12 14:23:11','365*21','/static/images/tool/t20.jpg','Good','tape coated on one side with an adhesive mixture','18','3','40',NULL);
/*!40000 ALTER TABLE `t_tool` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_toolbox`
--

DROP TABLE IF EXISTS `t_toolbox`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_toolbox` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `toolboxID` varchar(25) DEFAULT NULL,
  `box_model` varchar(25) DEFAULT NULL,
  `purchase_date` varchar(25) DEFAULT NULL,
  `service_life` varchar(12) DEFAULT NULL,
  `picture` varchar(45) DEFAULT NULL,
  `status` varchar(25) DEFAULT NULL,
  `description` varchar(45) DEFAULT NULL,
  `price` varchar(25) DEFAULT NULL,
  `broken_or_lost_date` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `price_index` (`price`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_toolbox`
--

LOCK TABLES `t_toolbox` WRITE;
/*!40000 ALTER TABLE `t_toolbox` DISABLE KEYS */;
INSERT INTO `t_toolbox` VALUES (1,'356002078',' QM10-26R-CFI  ','2016-02-12 14:23:11','365*20','/static/images/toolbox/b1.jpg','Good','This box can contain 50  tools','100',NULL),(2,'356032678',' QM10-27R-CFI','2008-02-13 14:23:11','365*20','/static/images/toolbox/b2.jpg','Good','This box can contain 20  tools','20',NULL),(3,'356032679',' QM10-28R-CFI','2010-05-12 14:23:11','365*15','/static/images/toolbox/b3.jpg','Good','This box can contain 20  tools','20',NULL),(4,'356220078',' QM10-29R-CFI','2013-07-12 14:23:11','365*25','/static/images/toolbox/b4.jpg','Good','This box can contain 35  tools','50',NULL),(5,'356220079',' QM10-30R-CFI','2009-02-12 14:23:11','365*25','/static/images/toolbox/b5.jpg','Good','This box can contain 35  tools','50',NULL);
/*!40000 ALTER TABLE `t_toolbox` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-04-28 21:18:11
