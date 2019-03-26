# Host: localhost  (Version 5.5.5-10.1.35-MariaDB)
# Date: 2019-03-26 10:09:09
# Generator: MySQL-Front 6.1  (Build 1.26)


#
# Structure for table "controlvehiculo"
#

DROP TABLE IF EXISTS `controlvehiculo`;
CREATE TABLE `controlvehiculo` (
  `placa` varchar(6) NOT NULL DEFAULT '',
  `FechaHoraIngreso` datetime DEFAULT NULL,
  `FechaHoraSalida` datetime DEFAULT NULL,
  `idControlVehiculo` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`idControlVehiculo`)
) ENGINE=InnoDB AUTO_INCREMENT=62 DEFAULT CHARSET=latin1;

#
# Data for table "controlvehiculo"
#

INSERT INTO `controlvehiculo` VALUES ('abc123','2019-03-22 21:03:27','2019-03-25 14:23:25',17),('mwp34e','2019-03-25 14:16:03','2019-03-25 17:23:25',18),('abc12a','2019-03-25 17:20:37','2019-03-25 17:26:04',19),('des12a','2019-03-25 15:27:59','2019-03-25 17:37:36',20),('lnf678','2019-03-26 06:24:04',NULL,62),('ogt674','2019-03-26 08:17:34',NULL,63),('ltu901','2019-03-26 09:44:55',NULL,64),('opi761','2019-03-26 09:45:49','2019-03-26 09:53:41',65),('asd34r','2019-03-26 09:52:06',NULL,66),('kmn862','2019-03-26 10:01:18',NULL,67);

#
# Structure for table "organizacion"
#

DROP TABLE IF EXISTS `organizacion`;
CREATE TABLE `organizacion` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) NOT NULL DEFAULT '',
  `costoPorHora` float NOT NULL DEFAULT '0',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

#
# Data for table "organizacion"
#

INSERT INTO `organizacion` VALUES (1,'UniParking',2000);

#
# Structure for table "pago"
#

DROP TABLE IF EXISTS `pago`;
CREATE TABLE `pago` (
  `idControlVehiculo` int(11) NOT NULL,
  `placa` varchar(6) NOT NULL DEFAULT '',
  `NoTicket` varchar(255) DEFAULT NULL,
  `fechaHoraPago` datetime DEFAULT NULL,
  UNIQUE KEY `idControlVehiculo` (`idControlVehiculo`),
  CONSTRAINT `pago_ibfk_1` FOREIGN KEY (`idControlVehiculo`) REFERENCES `controlvehiculo` (`idControlVehiculo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Data for table "pago"
#

INSERT INTO `pago` VALUES (65,'opi761',NULL,'2019-03-26 09:53:26');
