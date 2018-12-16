USE `airbnbdb` ;



INSERT INTO `airbnbdb`.`User`
VALUES ('mateusz@gmail.com', 'Mateusz', 'Kobierski','mateuszpass','048998007465');

INSERT INTO `airbnbdb`.`User`
VALUES ('dima@gmail.com', 'Dima', 'Bliakharchuk','dimapass','048546338067');

INSERT INTO `airbnbdb`.`User`
VALUES ('piotrducki@gmail.com', 'Piotr', 'Ducki','piotrduckipass','048987445372');

INSERT INTO `airbnbdb`.`User`
VALUES ('piotrszylar@gmail.com', 'Piotr', 'Szylar','piotrszylarpass','048763991076');

INSERT INTO `airbnbdb`.`User`
VALUES ('customer@gmail.com', 'Grzegorz', 'Brzeczyszczykiewicz','grzegorzpass','048927499287');

INSERT INTO `airbnbdb`.`User`
VALUES ('admin', 'Admin', 'Airbnb','admin123','0487557');


# insertion of images depend on running operating system therefore I commented out values with images and replaced with not nulls

INSERT INTO `airbnbdb`.`Apartment`
VALUES('mateusz@gmail.com',30.0,2,'ENTIRE_APARTMENT','Pretty Apartment in Plaza Mayor','Apartment in Sol is a cozy accommodation right in the center of Madrid, where on every corner there is fun due to the innumerable bars, restaurants, shops and markets in the area. The apartment has a kitchenette, free wifi in all areas, television, oven and microwave, washing machine, hair dryer, coffee maker and toaster. Sheets and towels are provided upon arrival.',1,'1','Plaza Mayor','Madrid','Spain','34', '');
# VALUES('mateusz@gmail.com',30.0,2,'ENTIRE_APARTMENT','Pretty Apartment in Plaza Mayor','Apartment in Sol is a cozy accommodation right in the center of Madrid, where on every corner there is fun due to the innumerable bars, restaurants, shops and markets in the area. The apartment has a kitchenette, free wifi in all areas, television, oven and microwave, washing machine, hair dryer, coffee maker and toaster. Sheets and towels are provided upon arrival.',1,'1','Plaza Mayor','Madrid','Spain','34', LOAD_FILE('C:\\ProgramData\\MySQL\\MySQL Server 8.0\\Uploads\\images\\img1.jpg'));

INSERT INTO `airbnbdb`.`Apartment`
VALUES('mateusz@gmail.com',50.0,1,'ENTIRE_APARTMENT','Stunning Apartment in Sol','Apartment in Sol is a cozy accommodation right in the center of Madrid, where on every corner there is fun due to the innumerable bars, restaurants, shops and markets in the area. The apartment has a kitchenette, free wifi in all areas, television, oven and microwave, washing machine, hair dryer, coffee maker and toaster. Sheets and towels are provided upon arrival.',0,'2','Calle del Sol','Madrid','Spain','16', '');
# VALUES('mateusz@gmail.com',50.0,1,'ENTIRE_APARTMENT','Stunning Apartment in Sol','Apartment in Sol is a cozy accommodation right in the center of Madrid, where on every corner there is fun due to the innumerable bars, restaurants, shops and markets in the area. The apartment has a kitchenette, free wifi in all areas, television, oven and microwave, washing machine, hair dryer, coffee maker and toaster. Sheets and towels are provided upon arrival.',0,'2','Calle del Sol','Madrid','Spain','16',LOAD_FILE('C:\\ProgramData\\MySQL\\MySQL Server 8.0\\Uploads\\images\\img2.jpg'));

INSERT INTO `airbnbdb`.`Apartment`
VALUES('dima@gmail.com',65.0,3,'ENTIRE_APARTMENT','Central and quiet','Apartment in Sol is a cozy accommodation right in the center of Madrid, where on every corner there is fun due to the innumerable bars, restaurants, shops and markets in the area. The apartment has a kitchenette, free wifi in all areas, television, oven and microwave, washing machine, hair dryer, coffee maker and toaster. Sheets and towels are provided upon arrival.',4,'15','Calle Central','Madrid','Spain','5', '');
# VALUES('dima@gmail.com',65.0,3,'ENTIRE_APARTMENT','Central and quiet','Apartment in Sol is a cozy accommodation right in the center of Madrid, where on every corner there is fun due to the innumerable bars, restaurants, shops and markets in the area. The apartment has a kitchenette, free wifi in all areas, television, oven and microwave, washing machine, hair dryer, coffee maker and toaster. Sheets and towels are provided upon arrival.',4,'15','Calle Central','Madrid','Spain','5',LOAD_FILE('C:\\ProgramData\\MySQL\\MySQL Server 8.0\\Uploads\\images\\img3.jpg'));

INSERT INTO `airbnbdb`.`Apartment`
VALUES('piotrducki@gmail.com',80.0,1,'ENTIRE_APARTMENT','Flat in front of the Royal Palace','Apartment in Sol is a cozy accommodation right in the center of Madrid, where on every corner there is fun due to the innumerable bars, restaurants, shops and markets in the area. The apartment has a kitchenette, free wifi in all areas, television, oven and microwave, washing machine, hair dryer, coffee maker and toaster. Sheets and towels are provided upon arrival.',2,'2','Calle Royal','Madrid','Spain','1', '');
# VALUES('piotrducki@gmail.com',80.0,1,'ENTIRE_APARTMENT','Flat in front of the Royal Palace','Apartment in Sol is a cozy accommodation right in the center of Madrid, where on every corner there is fun due to the innumerable bars, restaurants, shops and markets in the area. The apartment has a kitchenette, free wifi in all areas, television, oven and microwave, washing machine, hair dryer, coffee maker and toaster. Sheets and towels are provided upon arrival.',2,'2','Calle Royal','Madrid','Spain','1',LOAD_FILE('C:\\ProgramData\\MySQL\\MySQL Server 8.0\\Uploads\\images\\img4.jpg'));

INSERT INTO `airbnbdb`.`Apartment`
VALUES('piotrszylar@gmail.com',70.0,2,'ENTIRE_APARTMENT','Apartment near Gran Via','Apartment in Sol is a cozy accommodation right in the center of Madrid, where on every corner there is fun due to the innumerable bars, restaurants, shops and markets in the area. The apartment has a kitchenette, free wifi in all areas, television, oven and microwave, washing machine, hair dryer, coffee maker and toaster. Sheets and towels are provided upon arrival.',2,'34','Gran Via','Madrid','Spain','2A', '');
# VALUES('piotrszylar@gmail.com',70.0,2,'ENTIRE_APARTMENT','Apartment near Gran Via','Apartment in Sol is a cozy accommodation right in the center of Madrid, where on every corner there is fun due to the innumerable bars, restaurants, shops and markets in the area. The apartment has a kitchenette, free wifi in all areas, television, oven and microwave, washing machine, hair dryer, coffee maker and toaster. Sheets and towels are provided upon arrival.',2,'34','Gran Via','Madrid','Spain','2A',LOAD_FILE('C:\\ProgramData\\MySQL\\MySQL Server 8.0\\Uploads\\images\\img5.jpg'));

INSERT INTO `airbnbdb`.`Apartment`
VALUES('dima@gmail.com',60.0,2,'ENTIRE_APARTMENT','Apartment in Sol','Apartment in Sol is a cozy accommodation right in the center of Madrid, where on every corner there is fun due to the innumerable bars, restaurants, shops and markets in the area. The apartment has a kitchenette, free wifi in all areas, television, oven and microwave, washing machine, hair dryer, coffee maker and toaster. Sheets and towels are provided upon arrival.',1,'4','Calle de Preciados','Madrid','Spain','56', '');
# VALUES('dima@gmail.com',60.0,2,'ENTIRE_APARTMENT','Apartment in Sol','Apartment in Sol is a cozy accommodation right in the center of Madrid, where on every corner there is fun due to the innumerable bars, restaurants, shops and markets in the area. The apartment has a kitchenette, free wifi in all areas, television, oven and microwave, washing machine, hair dryer, coffee maker and toaster. Sheets and towels are provided upon arrival.',1,'4','Calle de Preciados','Madrid','Spain','56',LOAD_FILE('C:\\ProgramData\\MySQL\\MySQL Server 8.0\\Uploads\\images\\img6.jpg'));



INSERT INTO `airbnbdb`.`Reservation`
VALUES('customer@gmail.com','2018-8-21', 'dima@gmail.com','4','Calle de Preciados','56','Madrid','Accepted');

INSERT INTO `airbnbdb`.`Reservation`
VALUES('customer@gmail.com','2018-8-21', 'piotrszylar@gmail.com','34','Gran Via','2A','Madrid','Accepted');



INSERT INTO `airbnbdb`.`Message`
VALUES('customer@gmail.com','dima@gmail.com','Hello','2018-8-11', true);

