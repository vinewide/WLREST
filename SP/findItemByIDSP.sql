USE `PsProject`;
DROP procedure IF EXISTS `findItemByIDSP`;

DELIMITER $$
USE `PsProject`$$
CREATE DEFINER=`mysqldb2020`@`%` PROCEDURE `findItemByIDSP`(
IN IN_ItemID INT
)
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
	IF EXISTS(SELECT 1 FROM Items WHERE ItemID=IN_ItemID) THEN
		SET @status = 'TRUE';
		SELECT @status AS status;
		SELECT ItemID,ItemName FROM Items WHERE ItemID=IN_ItemID;
	ELSE
		SET @status = 'FALSE';
        SELECT @status as status;        
        SELECT 'Item not found'as ErrorMsg,'Item not found' as UserMsg;
	END IF;
LEAVE L_return;
END$$
DELIMITER ;
