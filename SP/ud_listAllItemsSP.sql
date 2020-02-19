USE `PsProject`;
DROP procedure IF EXISTS `listAllItemsSP`;

DELIMITER $$
USE `PsProject`$$
CREATE DEFINER=`mysqldb2020`@`%` PROCEDURE `listAllItemsSP`()
L_return:
BEGIN
	DECLARE sqlstate_code CHAR(5) DEFAULT '00000';
    DECLARE message_text TEXT;
    DECLARE mysql_errno INT;    
	DECLARE status VARCHAR(10);   
	DECLARE EXIT HANDLER FOR SQLEXCEPTION
	BEGIN
		GET DIAGNOSTICS CONDITION 1
		sqlstate_code = RETURNED_SQLSTATE,
		mysql_errno = MYSQL_ERRNO,
		message_text = MESSAGE_TEXT;
		IF sqlstate_code <> '00000' THEN
			SET @status = 'FATAL';
			SELECT @status as status;
			SELECT CONCAT(mysql_errno,': ',message_text) as ErrorMsg, 'Our System is down. We are working on it. Please come back in few minutes' as UserMsg;
		END IF;
	END;	
	IF EXISTS(SELECT 1 FROM Items) THEN
		SET @status = 'TRUE';
		SELECT @status AS status;
		SELECT ItemID,ItemName FROM Items;
	ELSE
		SET @status = 'FALSE';
        SELECT @status as status;        
        SELECT 'No data in Items'as ErrorMsg,'No data in Items' as UserMsg;
	END IF;
LEAVE L_return;
END$$
DELIMITER ;
