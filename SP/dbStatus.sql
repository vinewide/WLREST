USE `PsProject`;
DROP procedure IF EXISTS `dbStatusSP`;

DELIMITER $$
USE `PsProject`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `dbStatusSP`()
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
        SET @status = 'TRUE';
	SELECT @status AS status;
	select TABLE_NAME,COLUMN_NAME,DATA_TYPE,COLUMN_TYPE from information_schema.columns where table_schema = 'PsProject' order by table_name,ordinal_position;	
LEAVE L_return;
END$$
DELIMITER ;
