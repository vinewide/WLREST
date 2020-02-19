USE `PsProject`;
DROP procedure IF EXISTS `listAllUsersSP`;

DELIMITER $$
USE `PsProject`$$
CREATE PROCEDURE `listAllUsersSP`()
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
	IF EXISTS(SELECT 1 FROM Users) THEN
		SET @status = 'TRUE';
		SELECT @status AS status;
		SELECT UserID,UserName FROM Users;
	ELSE
		SET @status = 'FALSE';
        SELECT @status as status;        
        SELECT 'No data in Users'as ErrorMsg,'No data in Users' as UserMsg;
	END IF;
LEAVE L_return;
END$$
DELIMITER ;
